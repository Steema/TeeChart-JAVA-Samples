/*
 * ArrowDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.arrow;

import java.util.Random;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.Aspect;
import com.steema.teechart.Walls.BackWall;
import com.steema.teechart.Walls.BottomWall;
import com.steema.teechart.Walls.LeftWall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.Arrow;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class ArrowDemo extends ChartSample implements SelectionListener {

    private Arrow arrowSeries;
    
	public ArrowDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == animateButton) {
	            if (isSelected) {
	            	getDisplay().timerExec(ONE_MILLISECOND, timer);
	            } else {
	            	getDisplay().timerExec(-1, timer);
	            }
	        } else if (source == view3DButton) {
	            chart1.getAspect().setView3D(isSelected);
	        } else if (source == colorEachButton) {
	            arrowSeries.setColorEach(isSelected);
	        }
		}
	}
	
	protected void createContent() {
		super.createContent();	

        colorEachButton = addCheckButton("Color each", "", this);
        animateButton = addCheckButton("Animate", "", this);
        view3DButton = addCheckButton("3D", "", this);

        //Create a timer.
        timer = new Runnable() {
            public void run() {
            	// add a new point to each series
                 Random generator = new Random();

                for (int t = 0; t < arrowSeries.getCount()-1; t++) {
                    arrowSeries.getStartXValues().setValue(
                            t,
                            arrowSeries.getStartXValues().getValue(t)+generator.nextDouble()*100-50.0
                            );
                    arrowSeries.getStartYValues().setValue(
                            t,
                            arrowSeries.getStartYValues().getValue(t)+generator.nextDouble()*100-50.0
                            );
                    arrowSeries.getEndXValues().setValue(
                            t,
                            arrowSeries.getEndXValues().getValue(t)+generator.nextDouble()*100-50.0
                            );
                    arrowSeries.getEndYValues().setValue(
                            t,
                            arrowSeries.getEndYValues().getValue(t)+generator.nextDouble()*100-50.0)
                            ;
                    arrowSeries.repaint();
                }
              getDisplay().timerExec(ONE_MILLISECOND, this);
            }
          };
	}

	protected void initContent() {
		super.initContent();
        colorEachButton.setSelection(true);
        animateButton.setSelection(false);
        view3DButton.setSelection(true);
	}

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setVisible(false);

        Aspect tmpAspect = chart1.getAspect();
        tmpAspect.setElevation(353);
        tmpAspect.setOrthogonal(false);
        tmpAspect.setPerspective(55);
        tmpAspect.setRotation(334);
        tmpAspect.setZoom(97);

        BackWall backWall = chart1.getWalls().getBack();
        backWall.getBrush().setColor(Color.WHITE);
        backWall.setColor(Color.LIME);
        backWall.setSize(20);
        backWall.setTransparent(true);
        backWall.getGradient().setDirection(GradientDirection.FORWARDDIAGONAL);
        backWall.getGradient().setEndColor(Color.LIME);
        backWall.getGradient().setVisible(true);

        BottomWall bottomWall = chart1.getWalls().getBottom();
        bottomWall.setColor(Color.GREEN);
        bottomWall.setSize(20);

        LeftWall leftWall = chart1.getWalls().getLeft();
        leftWall.setColor(Color.SKY_BLUE);
        leftWall.setSize(20);
        
        arrowSeries = new com.steema.teechart.styles.Arrow(chart1.getChart());
        arrowSeries.setArrowWidth(32);
        arrowSeries.setArrowHeight(32);
        arrowSeries.getXValues().setDateTime(false);
        arrowSeries.getYValues().setDateTime(false);
        arrowSeries.setColorEach(true);
        addRandomArrows();        
	}   
	
    private void addRandomArrows() {
        arrowSeries.clear();
        Random generator = new Random();

        double x0, y0, x1, y1;
        for (int t=0; t < 40; t++) {
            x0 = generator.nextDouble()*1000;
            y0 = generator.nextDouble()*1000;

            x1 = generator.nextDouble()*300 - 150.0;
            if (x1<50) { x1 = 50; };
            x1 = x1 + x0;

            y1 = generator.nextDouble()*300 - 150.0;
            if (y1<50) { y1 = 50; };
            y1 = y1 + y0;
            arrowSeries.add(x0,y0,x1,y1, "");
        }
    }
    
    private Button animateButton;
    private Button colorEachButton;
    private Button view3DButton;
    private Runnable timer;

    private final static int ONE_MILLISECOND = 1;		
}
