/*
 * BarDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.bar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.BarStyle;
import com.steema.teechart.styles.MultiBars;

import features.ChartSample;
import features.WidgetFactory;
import features.utils.EnumStrings;

/**
 * @author tom
 *
 */
public class BarDemo extends ChartSample implements SelectionListener {

	public BarDemo(Composite c) {
		super(c);
		
		editButton.addSelectionListener(this);
		barStyleList.addSelectionListener(this);
		layoutList.addSelectionListener(this);		
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source instanceof Combo) {
            Bar series = (Bar)chart1.getSeries(0);        	
            int index = ((Combo)se.widget).getSelectionIndex();
            if (se.widget == layoutList) {
                MultiBars layout = MultiBars.NONE;
                switch (index) {
                    case 0: layout = MultiBars.NONE; break;
                    case 1: layout = MultiBars.SIDE; break;
                    case 2: layout = MultiBars.STACKED; break;
                    case 3: layout = MultiBars.STACKED100; break;
                    case 4: layout = MultiBars.SIDEALL; break;
                    case 5: layout = MultiBars.SELFSTACK; break;
                }

                boolean tmpVisible = (layout != MultiBars.STACKED)
                && (layout != MultiBars.STACKED100)
                && (layout != MultiBars.SELFSTACK);

                series.setMultiBar(layout);
                for (int i=0; i < chart1.getSeriesCount(); i++) {
                	chart1.getSeries(i).getMarks().setVisible(tmpVisible);
                }
            } else if (se.widget == barStyleList) {
                BarStyle barStyle = BarStyle.RECTANGLE;
                switch (index) {
                    case 0: barStyle = BarStyle.RECTANGLE; break;
                    case 1: barStyle = BarStyle.PYRAMID; break;
                    case 2: barStyle = BarStyle.INVPYRAMID; break;
                    case 3: barStyle = BarStyle.CYLINDER; break;
                    case 4: barStyle = BarStyle.ELLIPSE; break;
                    case 5: barStyle = BarStyle.ARROW; break;
                    case 6: barStyle = BarStyle.RECTGRADIENT; break;
                    case 7: barStyle = BarStyle.CONE; break;
                }
                series.setBarStyle(barStyle);                
            }
        } else {
        	//TODO ChartEditor.editSeries(chart1.getSeries(0));
        }
	}	
	
    protected void createContent() {
    	super.createContent();
    	   	
    	WidgetFactory.createLabel(getButtonPane(), SWT.LEFT, "Layout: ");
    	layoutList = new Combo(getButtonPane(), SWT.DROP_DOWN | SWT.READ_ONLY);
    	
    	WidgetFactory.createLabel(getButtonPane(), SWT.LEFT, "Style Bar 1: ");
    	barStyleList = new Combo(getButtonPane(), SWT.DROP_DOWN | SWT.READ_ONLY);
    	
    	editButton = WidgetFactory.createPushButton(getButtonPane(), "Edit...", "Edit Series", this);
    }
    
    protected void initContent() {
    	super.initContent();
    	
    	layoutList.setItems(EnumStrings.SERIES_LAYOUTS);
    	layoutList.select(0);    	
    	barStyleList.setItems(EnumStrings.BAR_STYLES);
    	barStyleList.select(0);
    	
    	editButton.setEnabled(false);
    }
    
    protected void initChart() {
    	super.initChart();

    	chart1.getHeader().setVisible(true);
        chart1.setText( "Bar" );
        
        Bar barSeries = null;
        for (int i=0; i < 3; i++) {
            barSeries = new Bar(chart1.getChart());
            barSeries.setBarStyle(BarStyle.RECTANGLE);
            barSeries.setMultiBar(MultiBars.NONE);
            barSeries.fillSampleValues(6);
        }  	    
    }   	

    private Button editButton;
    private Combo barStyleList;
    private Combo layoutList;	
}
