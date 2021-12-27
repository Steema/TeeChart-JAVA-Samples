/*
 * SeriesAnimationDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.tool;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.tools.SeriesAnimation;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class SeriesAnimationDemo extends ChartSample implements SelectionListener {

    private SeriesAnimation tool;
    
	public SeriesAnimationDemo(Composite c) {
		super(c);
		stepsSlider.addSelectionListener(this);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;			
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == animateButton) {
	            animateButton.setEnabled(false);
	            try {
	                tool.execute();
	            } catch (Exception ex) {
	                System.out.println(ex.getMessage());
	            }
	            animateButton.setEnabled(true);
	        } else if (source == editButton) {
	            //TODO ChartEditor.editTool(tool);
	            // cosmetic... just in case "steps" has changed
	            stepsSlider.setSelection(tool.getSteps());
	            stepsLabel.setText(Integer.toString(stepsSlider.getSelection()));
	        } else if (source == drawEveryButton) {
	            tool.setDrawEvery(isSelected?1:0);
	        } 
	   } else {
           int step = ((Slider)source).getSelection();
           tool.setSteps(step);
           stepsLabel.setText(Integer.toString(step));		   
	   }
	}
	
	protected void createContent() {
		super.createContent();	

        animateButton = addPushButton("Animate !", "", this);
        stepsSlider = addSlider(SWT.HORIZONTAL, 1, 1000, 100);
        stepsLabel = addLabel(SWT.LEFT, "100");
        drawEveryButton = addCheckButton("One by one", "", this);
        editButton = addPushButton("Edit...", "", this);
	}

	protected void initContent() {
		super.initContent();
	}

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.setText("Series Animation tool");
        chart1.getHeader().setVisible(true);
        
        Bar barSeries = new com.steema.teechart.styles.Bar(chart1.getChart());
        barSeries.setColorEach(true);
        barSeries.getMarks().setArrowLength(20);
        barSeries.getMarks().getCallout().getBrush().setColor(Color.BLACK);
        barSeries.getMarks().getCallout().setLength(20);
        barSeries.getMarks().setVisible(false);
        barSeries.setValueFormat("000");
        barSeries.fillSampleValues(6);
        tool = new com.steema.teechart.tools.SeriesAnimation(chart1.getChart());
        tool.setStartAtMin(false);
        tool.setSeries(barSeries);        
	}   	

    private Button animateButton, editButton;
    private Button drawEveryButton;
    private Label stepsLabel;
    private Slider stepsSlider;	
}
