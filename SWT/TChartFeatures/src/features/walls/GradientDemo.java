/*
 * GradientDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.walls;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.Aspect;
import com.steema.teechart.ImageMode;
import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.tools.Rotate;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class GradientDemo extends ChartSample implements SelectionListener {
    
	public GradientDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == editButton) {
           //TODO DialogFactory.showModal(chart1.getWalls().getBack().getGradient());
        } else {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == animateButton) {
                if (isSelected) {
                	getDisplay().timerExec(ONE_MILLISECOND, timer);
                } else {
                	getDisplay().timerExec(-1, timer);
                }
            } else if (source == backWallButton) {
                chart1.getWalls().getBack().getGradient().setVisible(isSelected);
            }        	
        }
	}
	
	protected void createContent() {
		super.createContent();	

		backWallButton = addCheckButton("Show gradient", "", this);
        editButton = addPushButton("Edit...", "", this);
        
        animateButton = addCheckButton("Animate", "", this);        

        //Create a timer.
        timer = new Runnable() {
            public void run() {
                Aspect aspect = chart1.getAspect();
                aspect.setRotation(aspect.getRotation()+deltaR);
                if ((aspect.getRotation() > 358) || (aspect.getRotation() < 272)) {
                    deltaR=-deltaR;
                }

                aspect.setElevation(aspect.getElevation()+deltaE);

                if ((aspect.getElevation() > 358) || (aspect.getElevation() < 272)) {
                    deltaE=-deltaE;
                }
              getDisplay().timerExec(ONE_MILLISECOND, this);
            }
          };
	}

	protected void initContent() {
		super.initContent();
        backWallButton.setSelection(chart1.getWalls().getBack().getGradient().getVisible());
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.getHeader().setText("Back wall and gradient");
        chart1.getAspect().setChart3DPercent(80);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(75);
        chart1.getPanel().getGradient().setEndColor(Color.SILVER);
        chart1.getPanel().getGradient().setVisible(true);

        Wall tmpWall;
        tmpWall = chart1.getWalls().getBack();
        tmpWall.setTransparent(false);
        tmpWall.getGradient().setEndColor(Color.GREEN);
        tmpWall.getGradient().setMiddleColor(Color.ORANGE);
        tmpWall.getGradient().setStartColor(Color.SKY_BLUE);
        tmpWall.getGradient().setUseMiddle(true);
        tmpWall.getGradient().setVisible(true);
        tmpWall.getBrush().setColor(Color.WHITE);
        tmpWall.setSize(10);
        tmpWall = chart1.getWalls().getBottom();
        tmpWall.getBrush().loadImage(ChartSample.class.getResourceAsStream(URL_BRUSHIMAGE));
        tmpWall.getBrush().setImageMode(ImageMode.TILE);
        tmpWall.setSize(10);
        chart1.getWalls().getLeft().setSize(10);
        chart1.getWalls().getRight().setSize(10);
        
        new Rotate(chart1.getChart());  //add rotate tool        
	}   	
	
    private Button editButton;
    private Button animateButton, backWallButton;

    private Runnable timer;
    private int deltaR = 1;
    private int deltaE = 1;
    private final static String URL_BRUSHIMAGE = "images/zigzag.png";
    private final static int ONE_MILLISECOND = 1;	
}
