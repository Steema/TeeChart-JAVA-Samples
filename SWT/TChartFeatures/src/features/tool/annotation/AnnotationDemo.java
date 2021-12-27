/*
 * AnnotationDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.annotation;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.tools.Annotation;
import com.steema.teechart.tools.AnnotationPosition;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class AnnotationDemo extends ChartSample implements SelectionListener {

    private Annotation tool1, tool2, tool3;
	
	public AnnotationDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;				
	    if (source == editButton) {
	    	//TODO ChartEditor.editTool(tool1);
	    } else if (source == visibleButton) {
	    	boolean isSelected =  ((Button)source).getSelection();
            tool1.setActive(isSelected);
            tool2.setActive(isSelected);
            tool3.setActive(isSelected);
        }
	}
	
	protected void createContent() {
		super.createContent();	

        editButton = addPushButton("Edit...", "", this);
        visibleButton = addCheckButton("Visible", "", this);        
	}

	protected void initContent() {
		super.initContent();
		editButton.setEnabled(false);
		visibleButton.setSelection(true);
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Annotation Tool Example");
        chart1.getLegend().setVisible(false);
        
        FastLine tmpSeries = new com.steema.teechart.styles.FastLine(chart1.getChart());
        tmpSeries.fillSampleValues(30);


        tool1 = new Annotation(chart1.getChart());
        tool1.setText("Annotation 1");
        tool1.setLeft(65);
        tool1.setTop(125);

        tool2 = new Annotation(chart1.getChart());
        tool2.setText("Annotation 2");
        tool2.setLeft(130);
        tool2.setTop(70);
        tool2.getShape().setColor(Color.PURPLE);
        tool2.getShape().getFont().setColor(Color.BLUE);

        tool3 = new Annotation(chart1.getChart());
        tool3.setText("Annother one\nwith multiple\nlines of text.");
        tool3.setPosition(AnnotationPosition.RIGHTTOP);
        tool3.getShape().setColor(Color.YELLOW);        
	}   
	
	private Button editButton;
	private Button visibleButton;
}
