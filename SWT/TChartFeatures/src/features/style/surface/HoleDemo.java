/*
 * HoleDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.surface;

import java.io.IOException;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.Aspect;
import com.steema.teechart.ImageMode;
import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.Image;
import com.steema.teechart.misc.ImageUtils;
import com.steema.teechart.styles.Surface;
import com.steema.teechart.styles.ValueList;
import com.steema.teechart.tools.Rotate;

import features.ChartSample;
import features.CommandSample;

/**
 * @author tom
 * 
 */
public class HoleDemo extends CommandSample implements SelectionListener {

	private Surface series;

	public HoleDemo(Composite c) {
		super(c);

		// Create a timer.
		timer = new Runnable() {
			public void run() {

				angle = angle + 5;
				if (angle > 359) {
					angle = 0;
				}
				Aspect tmpAspect = chart1.getAspect();
				tmpAspect.setChart3DPercent(tmpAspect.getChart3DPercent()
						+ delta3D);
				if ((tmpAspect.getChart3DPercent() < 5)
						|| (tmpAspect.getChart3DPercent() > 60)) {
					delta3D = -delta3D;
				}

				ValueList tmpValues = series.getXValues();
				double tmpX = Math.sin(angle * Math.PI / 180.0);
				chart1.getAxes()
						.getBottom()
						.setMinMax(tmpValues.getMinimum() - tmpX,
								tmpValues.getMaximum() - tmpX);

				tmpValues = series.getYValues();
				double tmpY = Math.cos(angle * Math.PI / 180.0)
						* ((tmpValues.getMaximum() - tmpValues.getMinimum()) / 10.0);
				chart1.getAxes()
						.getLeft()
						.setMinMax(tmpValues.getMinimum() - tmpY,
								tmpValues.getMaximum() - tmpY);

				/* re-enable timer again */
				getDisplay().timerExec(ONE_MILLISECOND, this);
			}
		};
	}

	public void widgetDefaultSelected(SelectionEvent se) {
	}

	public void widgetSelected(SelectionEvent se) {
		Widget source = se.widget;
		if (source instanceof Button) {
			boolean isSelected = ((Button) source).getSelection();
			if (source == normalButton) {
				drawSeries(false);
			} else if (source == withHoleButton) {
				drawSeries(true);
			} else if (source == animateButton) {
				if (isSelected) {
					getDisplay().timerExec(ONE_MILLISECOND, timer);
				} else {
					getDisplay().timerExec(-1, timer);
				}
			} else if (source == backWallButton) {
				chart1.getWalls().getBack().setTransparent(!isSelected);
			}
		}
		;
	}

	protected void createContent() {
		super.createContent();
		normalButton = addPushButton("Normal", "", this);
		withHoleButton = addPushButton("With hole", "", this);
		animateButton = addCheckButton("Animate", "", this);
		backWallButton = addCheckButton("Back wall", "", this);
	}

	protected void initContent() {
		super.initContent();
	}

	protected void initChart() {
		super.initChart();
		chart1.getLegend().setVisible(false);
		chart1.getAxes().getLeft().setIncrement(0.1);
		chart1.getAxes().getBottom().setIncrement(1);
		chart1.getAxes().getBottom().getLabels().setSeparation(0);
		chart1.getAspect().setChart3DPercent(70);
		chart1.getAspect().setOrthogonal(false);
		chart1.getAspect().setPerspective(55);
		chart1.getAspect().setZoom(70);
		
		try {
			chart1.getPanel().setImage(new Image(
							ChartSample.class.getResource(URL_BACKIMAGE),
							getDisplay()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		chart1.getPanel().setImageMode(ImageMode.TILE);
		
		Wall tmpWall;
		tmpWall = chart1.getWalls().getBack();
		tmpWall.setTransparent(true);
		tmpWall.getBrush().setColor(Color.WHITE);
		tmpWall.setSize(10);
		tmpWall = chart1.getWalls().getBottom();
		tmpWall.setColor(new Color(8454016));
		tmpWall.setSize(6);
		tmpWall = chart1.getWalls().getLeft();
		tmpWall.setColor(new Color(16777088));
		tmpWall.setSize(6);

		series = new com.steema.teechart.styles.Surface(chart1.getChart());
		series.setNumXValues(10);
		series.setNumZValues(10);
		series.setEndColor(new Color(16744703));
		series.setMidColor(new Color(8453888));
		series.getSideBrush().setColor(Color.WHITE);
		series.getSideBrush().setStyle(null);
		drawSeries(true);

		Rotate tmpTool = new Rotate(chart1.getChart());
		tmpTool.getPen().setColor(Color.WHITE);

	}

	private void drawSeries(boolean withHole) {
		double tmpValue;
		series.clear();
		for (int x = 1; x < 11; x++) {
			for (int z = 1; z < 11; z++) {
				tmpValue = Math.cos(x / 10.0) * Math.sin(z / 10.0);
				/* apply hole at cells (5,5) to (6,6) */
				if ((withHole) && ((x == 5) || (x == 6))
						&& ((z == 5) || (z == 6))) {
					series.add(x, tmpValue, z, "", Color.TRANSPARENT); // <--
																		// NULL
																		// cell
				} else {
					series.add(x, tmpValue, z);
				}
			}
		}
	}

	private Button normalButton, withHoleButton;
	private Button animateButton, backWallButton;

	private int angle, delta3D;
	private Runnable timer;

	private final static int ONE_MILLISECOND = 1;
	private final static String URL_BACKIMAGE = "images/honeycomb.jpg";
}
