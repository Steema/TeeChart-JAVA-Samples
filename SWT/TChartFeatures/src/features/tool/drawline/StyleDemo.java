/*
 * StyleDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.drawline;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.events.ChangeEvent;
import com.steema.teechart.tools.DrawLine;
import com.steema.teechart.tools.DrawLineItem;
import com.steema.teechart.tools.DrawLineStyle;
import features.ChartSample;
import features.utils.EnumStrings;

/**
 * @author tom
 *
 */
public class StyleDemo extends ChartSample {

    private DrawLine tool;
	
	public StyleDemo(Composite c) {
		super(c);
	}
	
	protected void createContent() {
		super.createContent();	
        addLabel(SWT.LEFT, "New line style:");
		addCombo(SWT.READ_ONLY | SWT.SINGLE | SWT.BORDER);
        addLabel(SWT.LEFT, "(click and drag left mouse button)");
	}

	protected void initContent() {
		super.initContent();
        lineStyleList.setItems(EnumStrings.DRAWLINE_STYLES);
        lineStyleList.select(1);        
	
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setText("DrawLine style example");
        chart1.getLegend().setVisible(false);
        chart1.getAxes().getLeft().setMinMax(0,100);
        chart1.getAxes().getBottom().setMinMax(0,100);
        
        tool = new com.steema.teechart.tools.DrawLine(chart1.getChart());
        tool.getPen().setColor(Color.BLUE);
        tool.getPen().setWidth(3);
        tool.addDrawLineListener( tool.new DrawLineAdapter() {
            public void lineNew(ChangeEvent e) {
                // Change line style for new added lines:
                DrawLineItem tmpItem = tool.getLines().getLast();
                switch (lineStyleList.getSelectionIndex()) {
                    case 0: tmpItem.setDrawLineStyle(DrawLineStyle.LINE); break;
                    case 1: tmpItem.setDrawLineStyle(DrawLineStyle.HORIZPARALLEL); break;
                    case 2: tmpItem.setDrawLineStyle(DrawLineStyle.VERTPARALLEL); break;
                }
                //TODO chart1.redraw();
            }
        });
	} 	
	
    private Combo lineStyleList;	
}
