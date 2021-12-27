/*
 * AnnotationCalloutDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.annotation;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.Cursor;
import com.steema.teechart.TextShapePosition;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.misc.MathUtils;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Points;
import com.steema.teechart.styles.Series;
import com.steema.teechart.tools.Annotation;
import com.steema.teechart.tools.AnnotationCallout;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class AnnotationCalloutDemo extends ChartSample implements SelectionListener {

    private Annotation tool1;
    private Points series1;
    //TODO private ButtonPen borderButton;
    
	public AnnotationCalloutDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;				
	    if (source == editButton) {
	    	//TODO ChartEditor.editTool(tool1);
	    } else if (source == followMouseButton) {
	    	boolean isSelected =  ((Button)source).getSelection();
	    	chart1.getHeader().setText("");
            if (isSelected) {
                chart1.setText("Move the mouse over points !");
                series1.setCursor(Cursor.DEFAULT);
            } else {
                chart1.setText("Click a point !");
                series1.setCursor(Cursor.HAND);
            }        
        }  /* TODO else if (source instanceof ButtonPen) {
            ((ButtonPen)source).editPen();
        } */
	}
	
	protected void createContent() {
		super.createContent();	

        editButton = addPushButton("Edit...", "", this);
        //TODO borderButton = new ButtonPen(tool1.getCallout().getArrow(), "Border...");
        
        followMouseButton = addCheckButton("Follow mouse", "", this);
       
	}

	protected void initContent() {
		super.initContent();
		editButton.setEnabled(false);
        followMouseButton.setSelection(true);
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Move the mouse over points !");
        chart1.getLegend().setVisible(true);
        chart1.getAspect().setChart3DPercent(15);
        
        //TODO 
        chart1.addMouseListener( new MouseAdapter() {
             public void mouseDown(MouseEvent e) {
                 if(!followMouseButton.getSelection()) {
                     int index = series1.clicked(e.x, e.y);
                     if(index != -1) {
                         setCallout(series1, index);
                     }
                 }
             }
        });

        chart1.addMouseMoveListener( new MouseMoveListener() {
            public void mouseMove(MouseEvent e) {
                if(followMouseButton.getSelection()) {
                    int tmp = getNearestPoint(series1, e.x, e.y);
                    if(tmp != -1) {
                        setCallout(series1, tmp);
                    }
                }
            }
        });    
        
        
        series1 = new com.steema.teechart.styles.Points(chart1.getChart());
        series1.fillSampleValues();
        series1.setColorEach(true);
        series1.getMarks().setVisible(false);
        series1.getPointer().setVisible(true);

        // force a first-time chart redrawing, to obtain valid
        // coordinates (Series1.CalcYPos, etc).
        chart1.refreshControl();

        tool1 = new Annotation(chart1.getChart());
        tool1.setText(" Move the mouse ! ");
        tool1.setLeft(160);
        tool1.setTop(35);
        AnnotationCallout tmpCallout = tool1.getCallout();
        tmpCallout.getBrush().setColor(Color.RED);
        tmpCallout.setHorizSize(6);
        tmpCallout.getPen().setColor(Color.LIME);
        tmpCallout.getPen().setWidth(2);
        tmpCallout.setStyle(PointerStyle.CIRCLE);
        tmpCallout.setVertSize(6);
        tmpCallout.setVisible(true);
        tmpCallout.getArrow().setColor(Color.BLUE);
        tmpCallout.getArrow().setStyle(DashStyle.DOT);
        tmpCallout.getArrow().setWidth(2);
        tmpCallout.getArrow().setVisible(true);
        tmpCallout.setXPosition(50);
        tmpCallout.setXPosition(90);

        TextShapePosition tmpShape = tool1.getShape();
        tmpShape.getGradient().setDirection(GradientDirection.HORIZONTAL);
        tmpShape.getGradient().setEndColor(Color.WHITE);
        tmpShape.getGradient().setStartColor(Color.SILVER);
        tmpShape.getShadow().setHorizSize(4);
        tmpShape.getShadow().setTransparency(75);
        tmpShape.getShadow().setVertSize(4);
        tmpShape.setLeft(160);
        tmpShape.setTop(35);        
	}   	
    /**
     * Returns Series point index that is nearest to xy position.
     */
    private int getNearestPoint(Series aSeries, int x, int y) {
        int tmpResult = -1;
        int difference = -1;
        int tmpDif, tmpX, tmpY;

        for (int t=0; t < aSeries.getCount(); t++) {

            tmpX = aSeries.calcXPos(t) - x;
            tmpY = aSeries.calcYPos(t) - y;
            tmpDif = MathUtils.round(Math.sqrt(MathUtils.sqr(tmpX) + MathUtils.sqr(tmpY)));

            if ((difference==-1) || (tmpDif<difference)) {
                difference = tmpDif;
                tmpResult = t;
            }
        }

        return tmpResult;
    }

    private void setCallout(Series aSeries, int aIndex) {
        // Change annotation text
        tool1.setText("Point: "+ aIndex +"\n"+
                      " Value: "+ aSeries.getValueMarkText(aIndex));

        // Re-position annotation callout
        AnnotationCallout tmpCallout = tool1.getCallout();
        tmpCallout.setVisible(true);
        tmpCallout.setXPosition(series1.calcXPos(aIndex));
        tmpCallout.setYPosition(series1.calcYPos(aIndex));
        tmpCallout.setZPosition(0);
    }

    private Button editButton;
    private Button followMouseButton;	
}
