/*
 * ConeDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.bar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.events.ChartDrawEvent;
import com.steema.teechart.events.ChartPaintAdapter;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.BarStyle;
import com.steema.teechart.styles.MarksStyle;

import features.ChartSample;
import features.WidgetFactory;

/**
 * @author tom
 *
 */
public class ConeDemo extends ChartSample implements SelectionListener {

    private Bar barSeries;
    
	public ConeDemo(Composite c) {
		super(c);
		barStyleList.addSelectionListener(this);
		percentSlider.addSelectionListener(this);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source instanceof Combo) {    	
            int index = ((Combo)source).getSelectionIndex();
            if (se.widget == barStyleList) {         
                switch (index) {
                    case 0: barSeries.setBarStyle(BarStyle.CONE); break;
                    case 1: barSeries.setBarStyle(BarStyle.PYRAMID); break;                    
                }
                percentSlider.setEnabled(barSeries.getBarStyle()==BarStyle.CONE);
            }
        } else if (source instanceof Slider) {
            int percent = ((Slider)source).getSelection();
            percentLabel.setText(String.valueOf(percent)+"%");
            barSeries.setConePercent(percent);        	
        }
	}	

	protected void createContent() {
		super.createContent();

		percentSlider = WidgetFactory.createSlider(getButtonPane(), SWT.HORIZONTAL, 1, 100, 1);
		
		WidgetFactory.createLabel(getButtonPane(), SWT.LEFT, "Cone Percent: ");	
		percentLabel = WidgetFactory.createLabel(getButtonPane(), SWT.LEFT, "");
		   	
		barStyleList = new Combo(getButtonPane(), SWT.DROP_DOWN | SWT.READ_ONLY);
	}
	
	protected void initContent() {
		super.initContent();		  	
		barStyleList.setItems(BAR_STYLES);
		barStyleList.select(0);
		percentSlider.setSelection(barSeries.getConePercent());
		percentLabel.setText(String.valueOf(percentSlider.getSelection())+"%");
	}
	
	protected void initChart() {
		super.initChart();
	
		chart1.getPanel().setMarginTop(15);
		chart1.addChartPaintListener( new ChartPaintAdapter() {
            public void chartPainted(ChartDrawEvent pce) {
            	chart1.getGraphics3D().cone(
                        true,
                        30,30,80,50,0,20,
                        true,
                        percentSlider.getSelection()
                );
            };
        });
	    
        barSeries = new Bar(chart1.getChart());
        barSeries.setBarStyle(BarStyle.CONE);
        barSeries.getMarks().setVisible(true);
        barSeries.getMarks().setStyle(MarksStyle.VALUE);
        barSeries.fillSampleValues(5);
        barSeries.setConePercent(30);
	}   	
	
    private Label percentLabel;
    private Slider percentSlider;
    private Combo barStyleList;	

    private static final String[] BAR_STYLES = { "Cone", "Pyramid" };    
}
