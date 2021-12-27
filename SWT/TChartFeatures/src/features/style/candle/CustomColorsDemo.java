/*
 * CustomColorsDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.candle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.CandleStyle;

import features.ChartSample;
import features.utils.EnumStrings;

/**
 * @author tom
 *
 */
public class CustomColorsDemo extends ChartSample implements SelectionListener {

    private Candle candleSeries;
    
	public CustomColorsDemo(Composite c) {
		super(c);
		candleStyleList.addSelectionListener(this);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == candleStyleList) {
            CandleStyle candleStyle = CandleStyle.CANDLESTICK;
            switch (candleStyleList.getSelectionIndex()) {
                case 0: candleStyle = CandleStyle.CANDLESTICK; break;
                case 1: candleStyle = CandleStyle.CANDLEBAR; break;
                case 2: candleStyle = CandleStyle.OPENCLOSE; break;
            }
            setCandleSeriesStyle(candleStyle);
        } else {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == customColorsButton) {
                if (isSelected) {
                    setCustomColors();
                } else {
                    for(int i=0; i < candleSeries.getCount(); i++) {
                        candleSeries.getColors().setColor(i, Color.EMPTY);
                    };
                    candleSeries.repaint();
                }
            }	
        }
	}
	
	protected void createContent() {
		super.createContent();
        customColorsButton = addCheckButton("Custom colors", "", this);
		candleStyleList = addCombo(SWT.READ_ONLY | SWT.SINGLE);
	}

	protected void initContent() {
		super.initContent();
        customColorsButton.setSelection(true);      
        candleStyleList.setItems(EnumStrings.CANDLE_STYLES);
        candleStyleList.select(0);
        setCandleSeriesStyle(CandleStyle.CANDLESTICK);
        setCustomColors();
	}

	protected void initChart() {
		super.initChart();
		candleSeries = new com.steema.teechart.styles.Candle(chart1.getChart());
        candleSeries.fillSampleValues(30);      
	}   
		
    private void setCandleSeriesStyle(CandleStyle candleStyle) {
        candleSeries.setStyle(candleStyle);
    }
    
    private void setCustomColors() {
        /* some custom colors... */
        candleSeries.getColors().setColor(11, Color.YELLOW);
        candleSeries.getColors().setColor(15, Color.LIME);
        candleSeries.getColors().setColor(16, Color.BLUE);
        candleSeries.repaint();
    }    
    
    private Combo candleStyleList;
    private Button customColorsButton;
}
