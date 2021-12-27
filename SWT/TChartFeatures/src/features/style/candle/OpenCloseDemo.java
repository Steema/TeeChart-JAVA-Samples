/*
 * OpenCloseDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.candle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.CandleStyle;

import features.ChartSample;
import features.WidgetFactory;

/**
 * @author tom
 *
 */
public class OpenCloseDemo extends ChartSample implements SelectionListener {

    private Candle candleSeries;
    
	public OpenCloseDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == stickButton) {
            candleSeries.setStyle(CandleStyle.CANDLESTICK);
        } else if (source == barButton) {
            candleSeries.setStyle(CandleStyle.CANDLEBAR);
        } else if (source == openCloseButton) {
            candleSeries.setStyle(CandleStyle.OPENCLOSE);
        } else {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == view3DButton) {
                candleSeries.getPointer().setDraw3D(isSelected);
            }
        }
	}
	
	protected void createContent() {
		super.createContent();
		Group radioButtons = new Group(getButtonPane(), SWT.NONE);
		radioButtons.setLayout(new RowLayout());
		radioButtons.setText("Candle Style: ");
		stickButton = WidgetFactory.createCheckButton(radioButtons, "Stick", "", this);
		barButton = WidgetFactory.createCheckButton(radioButtons, "Bar", "", this);
		openCloseButton = WidgetFactory.createCheckButton(radioButtons, "Open Close", "", this);
		view3DButton = addCheckButton("3D", "", this);
	}

	protected void initContent() {
		super.initContent();
		stickButton.setSelection(true);
        view3DButton.setSelection(chart1.getAspect().getView3D());
	}

	protected void initChart() {
		super.initChart();
        candleSeries = new com.steema.teechart.styles.Candle(chart1.getChart());
        candleSeries.fillSampleValues(20);
        candleSeries.setStyle(CandleStyle.CANDLESTICK);
        candleSeries.getPointer().setDraw3D(true);
   
	}   	

    private Button stickButton, barButton, openCloseButton;
    private Button view3DButton;	
}
