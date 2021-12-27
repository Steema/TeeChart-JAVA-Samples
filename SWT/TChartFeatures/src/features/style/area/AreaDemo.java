/*
 * AreaDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.area;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.CustomStack;

import features.ChartSample;
import features.WidgetFactory;
import features.utils.EnumStrings;

/**
 * @author tom
 *
 */
public class AreaDemo extends ChartSample implements SelectionListener {

	public AreaDemo(Composite c) {
		super(c);
		stairsButton.addSelectionListener(this);        
        view3DButton.addSelectionListener(this);
        editButton.addSelectionListener(this);
        areaStyleList.addSelectionListener(this);		
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
		if (source == areaStyleList) {
            CustomStack stackType = CustomStack.NONE;
            switch (areaStyleList.getSelectionIndex()) {
                case 0: stackType = CustomStack.NONE; break;
                case 1: stackType = CustomStack.STACK; break;
                case 2: stackType = CustomStack.STACK100; break;
            }
            setAreaSeriesStyle(stackType);			
		} else if (source == editButton) {
			//TODO  ChartEditor.editSeries(myChart.getSeries(0));
		} else {
			boolean isSelected = ((Button)source).getSelection();
			if (source == stairsButton) {       	
	            for(int i=0; i < chart1.getSeriesCount(); i++) {
	                ((Area)chart1.getSeries(i)).setStairs(isSelected);
	            };
			} else if (source == view3DButton) {
	            chart1.getAspect().setView3D(isSelected);
	        }        
		}
	}		
	
    protected void createContent() {
    	super.createContent();
    	WidgetFactory.createLabel(getButtonPane(), SWT.LEFT, "Layout: ");
    	areaStyleList = new Combo(getButtonPane(), SWT.DROP_DOWN | SWT.READ_ONLY);
    	stairsButton = WidgetFactory.createCheckButton(getButtonPane(), "Stairs", "", this);    	
    	view3DButton = WidgetFactory.createCheckButton(getButtonPane(), "3D", "", this);
    	editButton = WidgetFactory.createPushButton(
    			getButtonPane(), 
    			"Edit",
    			"Show the series editor",
    			null
    	);     	      
    }
    
    protected void initContent() {
    	super.initContent();
    	stairsButton.setSelection(false);
    	view3DButton.setSelection(chart1.getAspect().getView3D());    	
        areaStyleList.setItems(EnumStrings.AREA_STYLES);
        areaStyleList.select(0);
        setAreaSeriesStyle(CustomStack.NONE);    
        editButton.setEnabled(false);
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.getHeader().setText("'Self Stacked' bar series");

        Area areaSeries = null;
        for (int i=0; i < 3; i++) {
            areaSeries = new com.steema.teechart.styles.Area(chart1.getChart());
            areaSeries.setStairs(false);
            areaSeries.fillSampleValues(6);
        }      
    }   		

    private void setAreaSeriesStyle(CustomStack stackType) {
        for(int i=0; i < chart1.getSeriesCount(); i++) {
            ((Area)chart1.getSeries(i)).setStacked(stackType);
            ((Area)chart1.getSeries(i)).getMarks().setVisible( stackType == CustomStack.NONE );
        };
    }
    
    private Button stairsButton, view3DButton;
    private Button editButton;
    private Combo areaStyleList;
}
