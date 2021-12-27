/*
 * AreaSeries.java
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

import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.HorizArea;

import features.ChartSample;
import features.utils.EnumStrings;

/**
 * @author tom
 *
 */
public class HorizAreaDemo extends ChartSample implements SelectionListener {

	public HorizAreaDemo(Composite c) {
		super(c);
		areaStyleList.addSelectionListener(this);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {				        	
	        if (source == editButton) {
	            //TODO ChartEditor.editSeries(myChart.getSeries(0));
	        } else {
	        	boolean isSelected = ((Button)source).getSelection();	
	            if (source == stairsButton) {
	                for(int i=0; i < chart1.getSeriesCount(); i++) {
	                    ((HorizArea)chart1.getSeries(i)).setStairs(isSelected);
	                };
	            } else if (source == view3DButton) {
	            	chart1.getAspect().setView3D(isSelected);
	            }	        	
	        }	        
		} else if (source == areaStyleList) {
            CustomStack stackType = CustomStack.NONE;
            switch (areaStyleList.getSelectionIndex()) {
                case 0: stackType = CustomStack.NONE; break;
                case 1: stackType = CustomStack.STACK; break;
                case 2: stackType = CustomStack.STACK100; break;
            }
            setAreaSeriesStyle(stackType);
        }
	}

	protected void createContent() {
		super.createContent();
		addLabel(SWT.LEFT, "Layout: ");
		areaStyleList = addCombo(SWT.SINGLE | SWT.READ_ONLY);
		stairsButton = addCheckButton("Stairs", "", this);       
		view3DButton = addCheckButton("3D", "", this);	
		editButton = addPushButton("Edit...", "Edit Series", this);
	}

	protected void initContent() {
		super.initContent();  
		stairsButton.setSelection(false);
		view3DButton.setSelection(true);
		areaStyleList.setItems(EnumStrings.AREA_STYLES);
	    areaStyleList.select(0);	
	}

	protected void initChart() {
		super.initChart();
		
        HorizArea areaSeries = null;
        for (int i=0; i < 3; i++) {
            areaSeries = new com.steema.teechart.styles.HorizArea(chart1.getChart());
            areaSeries.setStairs(false);
            areaSeries.getPointer().setVisible(false);
            areaSeries.fillSampleValues(6);
        }	
    }
	
    private void setAreaSeriesStyle(CustomStack stackType) {
        for(int i=0; i < chart1.getSeriesCount(); i++) {
            ((HorizArea)chart1.getSeries(i)).setStacked(stackType);
            ((HorizArea)chart1.getSeries(i)).getMarks().setVisible( stackType == CustomStack.NONE );
        };
    }	

    private Button stairsButton;
    private Button view3DButton;	
    private Button editButton;
    private Combo areaStyleList;
}
