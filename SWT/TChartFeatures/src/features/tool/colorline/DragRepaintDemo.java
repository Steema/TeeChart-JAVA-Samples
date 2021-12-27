/*
 * DragRepaintDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.tool.colorline;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.tools.ColorLine;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class DragRepaintDemo extends ChartSample implements SelectionListener {

	public DragRepaintDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source instanceof Button) {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == repaintButton) {
                for (int t=0; t < chart1.getTools().size(); t++) {
                    if (chart1.getTools().getTool(t) instanceof ColorLine) {
                        ((ColorLine)chart1.getTools().getTool(t)).setDragRepaint(isSelected);
                    }
                }
            } else if (source == view3DButton) {
                chart1.getAspect().setView3D(isSelected);
            } else if (source == drawBehindButton) {
                for (int t=0; t < chart1.getTools().size(); t++) {
                    if (chart1.getTools().getTool(t) instanceof ColorLine) {
                        ((ColorLine)chart1.getTools().getTool(t)).setDrawBehind(isSelected);
                    }
                }
            }
        };
	}
	
	protected void createContent() {
		super.createContent();	  
		view3DButton = addCheckButton("View 3D", "", this);       
		drawBehindButton = addCheckButton("Draw Behind", "", this);	
		repaintButton = addCheckButton("Repaint all when dragging", "", this);  
	}

	protected void initContent() {
		super.initContent();	
		view3DButton.setSelection(chart1.getAspect().getView3D());    	
	}

	protected void initChart() {
		super.initChart();
        chart1.setText("3 Color-Line tools, drag them !");
        chart1.getAspect().setView3D(false);
        
        Bar tmpSeries = new com.steema.teechart.styles.Bar(chart1.getChart());
        tmpSeries.add(new int[] {40,220,140,512,256,310,60,100,600});

        ColorLine tmpTool;

        //tool1
        tmpTool = new com.steema.teechart.tools.ColorLine(chart1.getChart());
        tmpTool.getPen().setColor(Color.YELLOW);
        tmpTool.getPen().setWidth(2);
        tmpTool.setValue(100.0);
        tmpTool.setAxis(chart1.getAxes().getLeft());

        //tool2
        tmpTool = new com.steema.teechart.tools.ColorLine(chart1.getChart());
        tmpTool.getPen().setStyle(DashStyle.DOT);
        tmpTool.getPen().setWidth(3);
        tmpTool.setValue(300.0);
        tmpTool.setAxis(chart1.getAxes().getLeft());

        //tool3
        tmpTool = new com.steema.teechart.tools.ColorLine(chart1.getChart());
        tmpTool.getPen().setColor(Color.LIME);
        tmpTool.setValue(500.0);
        tmpTool.setAxis(chart1.getAxes().getLeft());
	}   		
	
    Button view3DButton, drawBehindButton, repaintButton;	
}
