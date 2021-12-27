/*
 * EMADemo.java
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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.ExpMovAverage;
import com.steema.teechart.languages.Language;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.Line;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class EMADemo extends ChartSample implements ModifyListener, SelectionListener {

    private Candle candleSeries;
    private ExpMovAverage function;
    
	public EMADemo(Composite c) {
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
		Widget source = se.widget;				
        if (source == rsiCombo) {
            String valuesrc = rsiCombo.getText();
            switch (rsiCombo.getSelectionIndex()) {
                case 0: valuesrc = Language.getString("ValuesOpen"); break;
                case 1: valuesrc = Language.getString("ValuesHigh"); break;
                case 2: valuesrc = Language.getString("ValuesLow"); break;
                case 3: valuesrc = Language.getString("ValuesClose"); break;
            }
            function.getSeries().getYValues().setDataMember(valuesrc);
        } else if (source == animateButton) {
        	boolean isSelected = ((Button)source).getSelection();
            if (isSelected) {
                getDisplay().timerExec(ONE_MILLISECOND, timer);
            } else {
                getDisplay().timerExec(-1, timer);
            }
        };
	}	

	protected void createContent() {
		super.createContent();	
		animateButton = addCheckButton("Animate", "", this);        
                
        addLabel(SWT.LEFT, "Style: ");
        rsiCombo = addCombo(SWT.READ_ONLY | SWT.BORDER | SWT.SINGLE); 
        
        addLabel(SWT.LEFT, "Period: ");
        periodSpinner = addSpinner(SWT.BORDER, 1, 100, 1);                 
	}

	protected void initContent() {
		super.initContent();
		periodSpinner.setSelection(10);
        animateButton.setSelection(false);
        rsiCombo.setItems(RSIstyleStrings);
        rsiCombo.select(3);
        
        //Create a timer.
        timer = new Runnable() {
            public void run() {
                int pos = periodSpinner.getSelection();

                pos += delta;
                periodSpinner.setSelection(pos);
                if ((pos < 2) || (pos > 19)) {
                    delta = -delta;
                }
               
                /* re-enable timer again */
              getDisplay().timerExec(ONE_MILLISECOND, this);
            }
          };    
    }

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Exponential Moving Average Example");
        chart1.getAspect().setView3D(false);
        
		candleSeries = new com.steema.teechart.styles.Candle(chart1.getChart());
		candleSeries.setTitle("Candle");
        candleSeries.getVertAxis().getTitle().setCaption("Candle");
        candleSeries.fillSampleValues(40);
        
        function = new ExpMovAverage(chart1.getChart());
        function.setPeriod(10);

        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.getPointer().setVisible(true);
        functionSeries.getPointer().setHorizSize(2);
        functionSeries.getPointer().setVertSize(2);
        functionSeries.getPointer().getBrush().setColor(Color.WHITE);
        functionSeries.getPointer().getBrush().setVisible(true);
        functionSeries.getPointer().getPen().setColor(Color.RED);
        functionSeries.setTitle("Exponential MA");
        functionSeries.setColor(Color.GREEN);
        functionSeries.setDataSource(candleSeries);
        functionSeries.getYValues().setDataMember(Language.getString("ValuesClose"));
        functionSeries.setFunction(function);       
	}   		

    private Combo rsiCombo;
    private Spinner periodSpinner;
    private Button animateButton;
    private Runnable timer;

    private static final String[] RSIstyleStrings = { "Open", "High", "Low", "Close" };
    private final static int ONE_MILLISECOND = 1;	
    private int delta = 1;	
}
