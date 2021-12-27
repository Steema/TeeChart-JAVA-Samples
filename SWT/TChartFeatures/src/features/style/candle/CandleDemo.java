/*
 * CandleDemo.java
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

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.CandleStyle;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.tools.MarksTip;

import features.ChartSample;
import features.utils.EnumStrings;

/**
 * @author tom
 *
 */
public class CandleDemo extends ChartSample implements SelectionListener {

    private Candle candleSeries;
    //TODO private ButtonColor upColorButton, downColorButton;
    
	public CandleDemo(Composite c) {
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
        } else if (source == editButton) {
            //TODO ChartEditor.editSeries(candleSeries);
        //} else if (source == upColorButton) {
            //TODO candleSeries.setUpCloseColor(new Color(upColorButton.getColor())); //TODO: change getColor in ButtonColor?
        //} else if (source == downColorButton) {
            //TODO candleSeries.setDownCloseColor(new Color(downColorButton.getColor()));
        } else {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == borderButton) {
                candleSeries.getPen().setVisible(isSelected);
            } else if (source == view3DButton) {
                candleSeries.getPointer().setDraw3D(true);
                chart1.getAspect().setView3D(isSelected);
            }   	
        }
	}
	
	protected void createContent() {
		super.createContent();
		addLabel(SWT.LEFT, "Style: ");
		candleStyleList = addCombo(SWT.READ_ONLY | SWT.SINGLE);
		borderButton = addCheckButton("Border", "", this);
		view3DButton = addCheckButton("Draw 3D", "", this);
		editButton = addPushButton("Edit...", "Edit Series", this);
		
        /* TODO
        upColorButton = new ButtonColor(candleSeries.getUpCloseColor());
        upColorButton.setText("UpCloseColor");

        downColorButton = new ButtonColor(candleSeries.getDownCloseColor());
        downColorButton.setText("DownCloseColor");
        */
	}

	protected void initContent() {
		super.initContent();
        candleStyleList.setItems(EnumStrings.CANDLE_STYLES);
        candleStyleList.select(0);
        setCandleSeriesStyle(CandleStyle.CANDLESTICK);
        borderButton.setSelection(candleSeries.getPen().getVisible());
        view3DButton.setSelection(candleSeries.getPointer().getDraw3D());        
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getAspect().setChart3DPercent(10);
        chart1.getLegend().setVisible(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Candle OHCL");
        chart1.getHeader().getFont().setColor(Color.WHITE);
        chart1.getPanel().setColor(Color.GRAY);

        Axis tmpAxis;
        tmpAxis = chart1.getAxes().getBottom();
        tmpAxis.getGrid().setColor(Color.BLACK);
        tmpAxis.getGrid().setStyle(DashStyle.SOLID);
        tmpAxis = chart1.getAxes().getLeft();
        tmpAxis.getGrid().setColor(Color.SILVER);
        
        candleSeries = new Candle(chart1.getChart());
        candleSeries.fillSampleValues(20);

        MarksTip tool = new MarksTip();
        tool.setSeries(candleSeries);
        tool.setStyle(MarksStyle.XVALUE);        
	}   
	
    private void setCandleSeriesStyle(CandleStyle candleStyle) {
        candleSeries.setStyle(candleStyle);
    }
    
    private Button editButton;
    private Combo candleStyleList;
    private Button borderButton;
    private Button view3DButton;
}
