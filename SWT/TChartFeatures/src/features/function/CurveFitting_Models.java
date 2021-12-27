/*
 * CurveFitting_Models.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.functions.Regression;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Points;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class CurveFitting_Models extends ChartSample implements SelectionListener {

	public final class TwoArrays {
		public double[] yhat;
		public double[] coeffs;

		public TwoArrays(double[] yhat, double[] coeffs)
		{
			this.yhat = yhat;
			this.coeffs = coeffs;
		}
	}

	private Points pointS;
	private Line line1;
	private double[] yhat;
	private double[] coeffs;

	public CurveFitting_Models(Composite c) {
		super(c);
	}
	
    private boolean FitModel(com.steema.teechart.styles.Series source, TwoArrays yhatcoeff, int modelindex)
    {
        double[] y = new double[source.getCount()];
        double[] x = new double[source.getCount()];
        double[] w = new double[source.getCount()];
        boolean validmodel = true;

        switch (modelindex)
        {
            case 0:
                // y(x)=a*Exp(b*x)
                // linear model: ln(y) = b*x + ln(a)
                for (int i = 0; i < source.getCount(); i++)
                {
                    x[i] = source.getNotMandatory().getValues()[i];
                    y[i] = Math.log(source.getMandatory().getValues()[i]);
                    w[i] = source.getMandatory().getValues()[i] * 
                                      source.getMandatory().getValues()[i];
                }
                yhatcoeff.coeffs = Regression.LinearRegression(source.getCount(), x, y, w);
                yhatcoeff.coeffs[0] = Math.exp(yhatcoeff.coeffs[0]);
                for (int i = 0; i < source.getCount(); i++)
                {
                    yhatcoeff.yhat[i] = yhatcoeff.coeffs[0] * Math.exp(yhatcoeff.coeffs[1] * x[i]);
                }
                break;
            case 1: 
                // Power model y=a*x^b
                // linear model : ln(y) = b*ln(x) + ln(a)
                for (int i = 0; i < source.getCount(); i++)
                {
                    x[i] = Math.log(source.getNotMandatory().getValues()[i]);
                    y[i] = Math.log(source.getMandatory().getValues()[i]);
                    w[i] = source.getMandatory().getValues()[i] * source.getMandatory().getValues()[i];
                }
                yhatcoeff.coeffs = Regression.LinearRegression(source.getCount(), x, y, w);
                yhatcoeff.coeffs[0] = Math.exp(yhatcoeff.coeffs[0]);
                for (int i = 0; i < source.getCount(); i++)
                {
                    yhatcoeff.yhat[i] = yhatcoeff.coeffs[0] * Math.pow(source.getNotMandatory().getValues()[i],yhatcoeff.coeffs[1]);
                }
                break;
            case 2:
                // Logarithmic model y = b*ln(x)+a
                for (int i = 0; i < source.getCount(); i++)
                {
                    x[i] = Math.log(source.getNotMandatory().getValues()[i]);
                    y[i] = source.getMandatory().getValues()[i];
                    w[i] = source.getMandatory().getValues()[i] * source.getMandatory().getValues()[i];
                }
                yhatcoeff.coeffs = Regression.LinearRegression(source.getCount(), x, y, w);
                for (int i = 0; i < source.getCount(); i++)
                {
                    yhatcoeff.yhat[i] = yhatcoeff.coeffs[1] * Math.log(source.getNotMandatory().getValues()[i])+yhatcoeff.coeffs[0];
                }
                break;
            default: yhatcoeff.coeffs = new double[0]; break;
        }

        return validmodel;
    }	

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == fittingCombo) {
            refresh();
          }
	}

    protected void createContent() {
        super.createContent();
        addLabel(SWT.LEFT, "Model:");
        fittingCombo = addCombo(SWT.READ_ONLY | SWT.SINGLE | SWT.BORDER);
    }

    protected void initContent() {
        super.initContent();
        fittingCombo.setItems(CurveFitOptionStrings);
        fittingCombo.select(0);        
    }    
    
	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText(CurveFitOptionStrings[0]);
        chart1.getAspect().setView3D(false);		
        pointS = new com.steema.teechart.styles.Points(chart1.getChart());
        line1 = new com.steema.teechart.styles.Line(chart1.getChart());

        pointS.add(1.1, 1);
        pointS.add(1.2, 2.5);
        pointS.add(1.25, 4);
        pointS.add(1.3, 3);
        pointS.add(1.9, 5.5);
        pointS.add(2.1, 6.2);
        pointS.add(2.2, 6.6);
        pointS.add(2.4, 7.2);
        pointS.add(3, 8);
        pointS.add(3.5, 12);

        refresh();        
	}
	
    public void refresh()
    {
    
      yhat = new double[pointS.getXValues().getCount()];
      line1.clear();

      TwoArrays yhatcoeffs=new TwoArrays(yhat,coeffs);

      if (FitModel(pointS, yhatcoeffs, fittingCombo.getSelectionIndex()))
      {
          line1.getXValues().setCount(pointS.getXValues().getCount());
          line1.getXValues().value = pointS.getXValues().value;
          line1.getYValues().setCount(pointS.getYValues().getCount());
          line1.getYValues().value = yhatcoeffs.yhat;
          switch (fittingCombo.getSelectionIndex())
          {
              case 0:
                  chart1.getHeader().setText("y=a*Exp(b*x)\r\na=" + Double.toString(yhatcoeffs.coeffs[0])+"  b="+ Double.toString(yhatcoeffs.coeffs[1])); break;
              case 1:
                  chart1.getHeader().setText("y=ax^b\r\na=" + Double.toString(yhatcoeffs.coeffs[0]) + "  b=" + Double.toString(yhatcoeffs.coeffs[1])); break;
              case 2:
                  chart1.getHeader().setText("y=b*ln(x)+a\r\na=" + Double.toString(yhatcoeffs.coeffs[0]) + "  b=" + Double.toString(yhatcoeffs.coeffs[1]));break;
          }
      }
      line1.repaint();
    }	
      
    private Combo fittingCombo;
    private static final String[] CurveFitOptionStrings = 
                          { "y(x)=a*Exp[b*x]", "y(x)=a*x^b", "y(x)=a+b*Ln[x]" };    
}
