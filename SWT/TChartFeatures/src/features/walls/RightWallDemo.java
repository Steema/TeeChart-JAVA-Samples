/*
 * RightWallDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.walls;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bar;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class RightWallDemo extends ChartSample implements SelectionListener {


	public RightWallDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
		boolean isSelected = ((Button)source).getSelection();
        if (source == showRightWallButton) {
            chart1.getWalls().getRight().setVisible(isSelected);
        }
	}	

	protected void createContent() {
		super.createContent();    	       	
		showRightWallButton = addCheckButton("Show Right wall", "", this);	
	}
	
	protected void initContent() {
		super.initContent();   
        showRightWallButton.setSelection(chart1.getWalls().getRight().getVisible());
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setChart3DPercent(100);
        chart1.getAspect().setElevation(360);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(35);
        chart1.getAspect().setRotation(360);

        Wall wall;
        wall = chart1.getWalls().getBack();
        wall.setSize(10);
        wall.setTransparent(false);
        wall = chart1.getWalls().getBottom();
        wall.setSize(10);
        wall = chart1.getWalls().getLeft();
        wall.setSize(10);
        wall = chart1.getWalls().getRight();
        wall.setColor(Color.PINK);
        wall.setSize(4);
        wall.setVisible(true);
        
        Bar series = new Bar(chart1.getChart());
        series.fillSampleValues(6);        
	}   	

	private Button showRightWallButton;
}
