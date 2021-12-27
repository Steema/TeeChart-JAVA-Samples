/*
 * RSIDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.RSI;
import com.steema.teechart.functions.RSIStyle;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.Line;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class RSIDemo extends ChartSample implements ModifyListener, SelectionListener {

    private Candle candleSeries;
    private RSI function;
    
	public RSIDemo(Composite c) {
		super(c);
        rsiCombo.addSelectionListener(this);
        periodSpinner.addModifyListener(this);		
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
        if (source instanceof Spinner) {
            int period = ((Spinner)source).getSelection();
            if ((period > 0) && (period < 101)) {
                if (source == periodSpinner) {
                    function.setPeriod(period);
                }
            }
        }
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {
        RSIStyle rsistyle;
        if (rsiCombo.getSelectionIndex()==0) rsistyle = RSIStyle.OPENCLOSE;
        else rsistyle = RSIStyle.CLOSE;
        function.SetRSIStyle(rsistyle);
	}	

	protected void createContent() {
		super.createContent();	
        addLabel(SWT.LEFT, "Style: ");
        rsiCombo = addCombo(SWT.READ_ONLY | SWT.BORDER | SWT.SINGLE); 
        
        addLabel(SWT.LEFT, "Period: ");
        periodSpinner = addSpinner(SWT.BORDER, 1, 100, 1);                 
	}

	protected void initContent() {
		super.initContent();
		periodSpinner.setSelection(10);
        rsiCombo.setItems(RSIstyleStrings);
        rsiCombo.select(0);    
    }

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("RSI Function Example");
        chart1.getAspect().setView3D(false);
        
        candleSeries = new com.steema.teechart.styles.Candle(chart1.getChart());
        candleSeries.getMarks().setVisible(false);
        candleSeries.getPointer().setVisible(true);
        candleSeries.setTitle("Candle");
        candleSeries.getVertAxis().setStartPosition(0);
        candleSeries.getVertAxis().setEndPosition(50);
        candleSeries.getVertAxis().getTitle().setCaption("Candle");
        candleSeries.fillSampleValues(50);
        
        function = new com.steema.teechart.functions.RSI(chart1.getChart());
        function.setPeriod(10);

        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setTitle("RSI");
        functionSeries.setColor(Color.GREEN);
        Axis tmpAxis = chart1.getAxes().getCustom().getNew();
        tmpAxis.getTitle().setText("RSI");
        tmpAxis.getTitle().setAngle(90);
        tmpAxis.setHorizontal(false);
        tmpAxis.setOtherSide(false);
        tmpAxis.setStartPosition(60);
        tmpAxis.setEndPosition(100);
        functionSeries.setCustomVertAxis(tmpAxis);
        functionSeries.setDataSource(candleSeries);
        functionSeries.setFunction(function);    
	}   		

    private Combo rsiCombo;
    private Spinner periodSpinner;
    private static final String[] RSIstyleStrings = { "OpenClose", "Close" };
}
