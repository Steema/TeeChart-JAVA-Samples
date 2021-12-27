/*
 * ShapeDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.shape;

import org.eclipse.swt.widgets.Label;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.Shape;
import com.steema.teechart.styles.ShapeStyle;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class ShapeDemo extends ChartSample implements SelectionListener {

	public ShapeDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == transparentButton) {
	            for (int t=0; t < chart1.getSeriesCount(); t++) {
	                if (chart1.getSeries(t) instanceof Shape) {
	                    ((Shape)chart1.getSeries(t)).setTransparent(isSelected);
	                }
	            }
	        }			
		}
	}
	
	protected void createContent() {
		super.createContent();
        transparentButton = addCheckButton("Transparent", "", this);		
        clickedShapeLabel = addLabel(SWT.LEFT, "Shape Clicked: ");
	}

	protected void initContent() {
		super.initContent();
        transparentButton.setSelection(false);
	}

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getHeader().setVisible(true);
        chart1.getHeader().getFont().setSize(13);
        chart1.getHeader().getFont().setBold(true);
        chart1.getHeader().getFont().setColor(Color.BLUE);
        chart1.getHeader().getGradient().setEndColor(Color.WHITE);
        chart1.getHeader().getGradient().setStartColor(Color.SILVER);
        chart1.getHeader().getGradient().setDirection(GradientDirection.HORIZONTAL);
        chart1.getHeader().getGradient().setVisible(true);
        chart1.getHeader().getShadow().setColor(Color.GRAY);
        chart1.getHeader().getPen().setColor(Color.ORANGE);
        chart1.getHeader().getPen().setStyle(DashStyle.DOT);
        chart1.getHeader().getPen().setWidth(2);
        chart1.getHeader().setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        chart1.setText("Different Shape Styles");
        chart1.getHeader().setTransparent(false);
        chart1.getAspect().setView3D(false);
        chart1.addMouseListener( new MouseAdapter() {
            public void mouseDown(MouseEvent e) {
                if (e.count == 1) {
                    Shape clickedShape = null;
                    for (int t=chart1.getSeriesCount()-1; t >= 0; t--) {
                        if (((Shape)chart1.getSeries(t)).clicked(e.x, e.y) != -1) {
                            clickedShape = (Shape)chart1.getSeries(t);
                            break;
                        }
                    }
                    if (clickedShape != null) {
                        //TODO ChartEditor.editSeries(clickedShape);
                    	clickedShapeLabel.setText("Shape Clicked: " + clickedShape.getText(0));
                    	clickedShapeLabel.getParent().layout();
                    }
                    else
                    	clickedShapeLabel.setText("Shape Clicked: ");
                }
            }
        });
        initSeries();
	}   
	
    protected void initSeries() {
        Shape tmpSeries;

        //shape1
        tmpSeries = new Shape(chart1.getChart());
        tmpSeries.getMarks().setVisible(false);
        tmpSeries.setColor(Color.WHITE);
        tmpSeries.getBrush().setColor(Color.WHITE);
        tmpSeries.getFont().setColor(Color.NAVY);
        tmpSeries.getFont().getShadow().setHorizSize(1);
        tmpSeries.getFont().getShadow().setVertSize(1);
        tmpSeries.getGradient().setDirection(GradientDirection.HORIZONTAL);
        tmpSeries.getGradient().setMiddleColor(Color.GREEN_YELLOW);
        tmpSeries.getGradient().setUseMiddle(true);
        tmpSeries.getGradient().setVisible(true);
        tmpSeries.setStyle(ShapeStyle.CIRCLE);
        tmpSeries.setText(new String[] {"This is an ellipse"});
        tmpSeries.setX0(60);
        tmpSeries.setX1(100);
        tmpSeries.setY1(100);

        //shape2
        tmpSeries = new Shape(chart1.getChart());
        tmpSeries.getMarks().setVisible(false);
        tmpSeries.setColor(Color.PURPLE);
        tmpSeries.getBrush().setColor(Color.PURPLE);
        tmpSeries.setText(new String[] {"This is a round rectangle."});
        tmpSeries.setStyle(ShapeStyle.RECTANGLE);
        tmpSeries.getFormat().setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        tmpSeries.setX0(12.5);
        tmpSeries.setX1(47.5);
        tmpSeries.setY0(140);
        tmpSeries.setY1(80);

        //shape3
        tmpSeries = new Shape(chart1.getChart());
        tmpSeries.getMarks().setVisible(false);
        tmpSeries.setColor(Color.LIME);
        tmpSeries.getBrush().setColor(Color.LIME);
        tmpSeries.getFont().setColor(Color.OLIVE);
        tmpSeries.getFont().setBold(true);
        tmpSeries.setStyle(ShapeStyle.TRIANGLE);
        tmpSeries.setText(new String[] {"A triangle shape."});
        tmpSeries.setX0(12.5);
        tmpSeries.setX1(77.5);
        tmpSeries.setY0(13);
        tmpSeries.setY1(110);

        //shape4
        tmpSeries = new Shape(chart1.getChart());
        tmpSeries.getMarks().setVisible(false);
        tmpSeries.setColor(Color.YELLOW);
        tmpSeries.getBrush().setColor(Color.YELLOW);
        tmpSeries.getFont().setItalic(true);
        tmpSeries.setStyle(ShapeStyle.INVERTTRIANGLE);
        tmpSeries.setText(new String[] {"Hello"});
        tmpSeries.setX0(72.5);
        tmpSeries.setX1(87.5);
        tmpSeries.setY0(71);
        tmpSeries.setY1(184);

        //shape5
        tmpSeries = new Shape(chart1.getChart());
        tmpSeries.getMarks().setVisible(false);
        tmpSeries.setColor(Color.ORANGE);
        tmpSeries.getBrush().setColor(Color.ORANGE);
        tmpSeries.setStyle(ShapeStyle.DIAMOND);
        tmpSeries.setText(new String[] {"Diamond", "shape"});
        tmpSeries.setX0(12.5);
        tmpSeries.setX1(87.5);
        tmpSeries.setY0(113);
        tmpSeries.setY1(302);
    }	
	
    private Button transparentButton;
    private Label clickedShapeLabel;
}
