/*
 * Points3DDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.points3d;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.Aspect;
import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.styles.Points3D;
import com.steema.teechart.tools.Rotate;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class Points3DDemo extends ChartSample implements SelectionListener {

	private Points3D series;

	public Points3DDemo(Composite c) {
		super(c);		
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
		boolean isSelected=((Button)source).getSelection();
		if (source == linesButton) {
			series.getLinePen().setVisible(isSelected);
		} else if (source == wallsButton) {
			chart1.getWalls().setVisible(isSelected);
		} else if (source == axesButton) {
			chart1.getAxes().setVisible(isSelected);
		} else if (source == pointsButton) {
			series.getPointer().setVisible(isSelected);
		} else if (source == colorEachButton) {
			series.setColorEach(isSelected);
		} else if (source == animateButton) {
			if (isSelected) {
				getDisplay().timerExec(ONE_MILLISECOND, timer);
			} else {
				getDisplay().timerExec(-1, timer);;
			}
		}
	}	

	protected void createContent() {
		super.createContent();

		linesButton = addCheckButton("Lines", "", this);
		wallsButton = addCheckButton("3D Walls", "", this);
		axesButton = addCheckButton("Axes", "", this);
		pointsButton = addCheckButton("Points", "", this);
		colorEachButton = addCheckButton("Color each", "", this);
		animateButton = addCheckButton("Animate", "", this); 

		//Create a timer.
		timer = new Runnable() {
			public void run() {
				Aspect tmpAspect = chart1.getAspect();
				tmpAspect.setRotation((tmpAspect.getRotation()+rotateDelta) % 360);
				tmpAspect.setElevation(tmpAspect.getElevation()+elevateDelta);
				if ((tmpAspect.getElevation() < 280) || (tmpAspect.getElevation() > 350)) {
					elevateDelta = -elevateDelta;
				}
				getDisplay().timerExec(ONE_MILLISECOND, this);
			}
		};        
	}

	protected void initContent() {
		super.initContent();

		linesButton.setSelection(series.getLinePen().getVisible());
		wallsButton.setSelection(chart1.getWalls().getVisible());
		axesButton.setSelection(chart1.getAxes().getVisible());
		pointsButton.setSelection(series.getPointer().getVisible());
		colorEachButton.setSelection(series.getColorEach());        
	}

	protected void initChart() {
		super.initChart();
		Wall tmpWall;
		tmpWall = chart1.getWalls().getBack();
		tmpWall.getBrush().setColor(Color.WHITE);
		tmpWall.setColor(Color.WHITE);
		tmpWall.setSize(10);
		tmpWall = chart1.getWalls().getBottom();
		tmpWall.getBrush().setColor(Color.WHITE);
		tmpWall.setColor(Color.SILVER);
		tmpWall.setSize(10);
		tmpWall = chart1.getWalls().getLeft();
		tmpWall.getBrush().setColor(Color.WHITE);
		tmpWall.setSize(10);

		chart1.getLegend().setVisible(false);

		chart1.getPanel().setMarginBottom(9);
		chart1.getPanel().setMarginTop(9);

		chart1.getAxes().getDepth().setVisible(true);
		chart1.getAspect().setChart3DPercent(100);
		chart1.getAspect().setOrthogonal(false);
		chart1.getAspect().setPerspective(45);
		chart1.getAspect().setZoom(81);

		series = new com.steema.teechart.styles.Points3D(chart1.getChart());
		series.setColorEach(true);
		series.getLinePen().setStyle(DashStyle.DOT);
		series.getPointer().setVisible(true);
		series.getBaseLine().setVisible(true);
		series.setDepthSize(3.0);
		series.fillSampleValues(100);

		Rotate tmpTool = new Rotate(chart1.getChart());
		tmpTool.getPen().setColor(Color.WHITE);       
	}   

	private Button linesButton, wallsButton, axesButton, pointsButton, colorEachButton, animateButton;
	private Runnable timer;
	private int rotateDelta = -5;
	private int elevateDelta = -4;
	private final static int ONE_MILLISECOND = 1;
}
