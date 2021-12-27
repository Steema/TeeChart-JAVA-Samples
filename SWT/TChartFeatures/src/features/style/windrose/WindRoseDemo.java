/*
 * WindRoseDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.windrose;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.WindRose;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class WindRoseDemo extends ChartSample implements SelectionListener {

	private WindRose series1, series2;	
	
	public WindRoseDemo(Composite c) {
		super(c);
		anglesList.addSelectionListener(this);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source == anglesList) {
            switch (anglesList.getSelectionIndex()) {
                case 0: series1.setAngleIncrement(15); break;
                case 1: series1.setAngleIncrement(30); break;
                case 2: series1.setAngleIncrement(45); break;
                case 3: series1.setAngleIncrement(90); break;
            }
        } else if (source == editButton) {
            //TODO ChartEditor.editSeries(series1);
        } else {		
        	boolean isSelected = ((Button)source).getSelection();
            if (source == visibleButton) {
                chart1.getAxes().setVisible(isSelected);
            } else if (source == circledButton) {
                series1.setCircled(isSelected);
            }     	
        }
	}	
	
    protected void createContent() {
    	super.createContent();
    	   	
    	visibleButton = addCheckButton("Visible", "", this);    	
    	addLabel(SWT.LEFT, "Labels: ");    	
    	anglesList = addCombo(SWT.READ_ONLY | SWT.SINGLE);    	
    	editButton = addPushButton("Edit...", "", this);
    	circledButton = addCheckButton("Circled", "", this);    	
    }
    
    protected void initContent() {
    	super.initContent();    	   	
        anglesList.setItems(new String[] {"15", "30", "45", "90"});
        anglesList.select(0);
        visibleButton.setSelection(chart1.getAxes().getVisible());
        circledButton.setSelection(series1.getCircled());  
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Wind-Rose series");
        chart1.getAxes().getBottom().setIncrement(10.0);
        chart1.getLegend().setVisible(false);
        chart1.getPanel().setColor(Color.GRAY);
        
        series1 = new WindRose(chart1.getChart());
        series1.clear();
        series1.add( 30, 100, "", Color.EMPTY );
        series1.add( 60, 200, "", Color.EMPTY );
        series1.add( 90,  50, "", Color.EMPTY );
        series1.add( 120, 150, "", Color.EMPTY );
        series1.setCircled(true);
        series1.setAngleIncrement(15);
        series1.getBrush().setVisible(false);
        series1.getMarks().setVisible(false);
        series1.setCircleBackColor(Color.SILVER);
        series1.getCircleLabelsFont().setColor(Color.YELLOW);
        series1.getCircleLabelsFont().setBold(true);
        series1.getPointer().getBrush().setColor(Color.BLUE);
        series1.getPointer().setHorizSize(2);
        series1.getPointer().setVertSize(2);
        series1.getPointer().setVisible(true);

        series2 = new WindRose(chart1.getChart());
        series2.clear();
        series2.add( 130, 100, "", Color.EMPTY );
        series2.add( 160, 200, "", Color.EMPTY );
        series2.add( 190,  50, "", Color.EMPTY );
        series2.add( 220, 150, "", Color.EMPTY );
        series2.setAngleIncrement(10);
        series2.getBrush().setVisible(false);
        series2.getMarks().setVisible(false);
        series2.getPointer().setStyle(PointerStyle.RECTANGLE);
        series2.getPointer().setHorizSize(3);
        series2.getPointer().setVertSize(3);
        series2.getPointer().setVisible(true);
    }   			
	
    private Button editButton;
    private Combo anglesList;
    private Button visibleButton;
    private Button circledButton;	
}
