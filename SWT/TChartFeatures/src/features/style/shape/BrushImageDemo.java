/*
 * BrushImageDemo.java
 *
 * <p>Copyright: (c) 2005-2008
 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.shape;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.ImageMode;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.legend.LegendSymbolSize;
import com.steema.teechart.styles.Shape;
import com.steema.teechart.styles.ShapeStyle;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class BrushImageDemo extends ChartSample implements SelectionListener {

    private Shape[] shape;	
	
	public BrushImageDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == transparentButton) {
	            shape[0].setTransparent(isSelected);
	            shape[1].setTransparent(isSelected);
	        }
		}
	}
	
	protected void createContent() {
		super.createContent();
        transparentButton = addCheckButton("Transparent Shapes", "", this);				
	}

	protected void initContent() {
		super.initContent();
	}

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setColorWidth(15);
        chart1.getLegend().getFont().setSize(16);
        chart1.getLegend().getFont().setBold(true);
        chart1.getLegend().getSymbol().setWidth(15);
        chart1.getLegend().getSymbol().setWidthUnits(LegendSymbolSize.PIXELS);
        initSeries();
	}   
	
    protected void initSeries() {
        Shape tmpShape;
        shape = new Shape[2];

        //shape1
        shape[0] = new Shape(chart1.getChart());
        tmpShape = shape[0];
        tmpShape.getMarks().setVisible(false);
        tmpShape.setColor(Color.WHITE);
        tmpShape.getBrush().setColor(Color.WHITE);
        tmpShape.getBrush().setImageMode(ImageMode.TILE);
        tmpShape.getBrush().loadImage(ChartSample.class.getResourceAsStream(URL_IMAGE1));

        tmpShape.setStyle(ShapeStyle.CIRCLE);
        tmpShape.getPen().setColor(Color.RED);
        tmpShape.getPen().setWidth(2);
        tmpShape.setX1(50);
        tmpShape.setY1(50);

        //shape2
        shape[1] = new Shape(chart1.getChart());
        tmpShape = shape[1];
        tmpShape.getMarks().setVisible(false);
        tmpShape.setColor(Color.WHITE);
        tmpShape.getBrush().setColor(Color.WHITE);
        tmpShape.getBrush().setImageMode(ImageMode.TILE);
        tmpShape.getBrush().loadImage(ChartSample.class.getResourceAsStream(URL_IMAGE2));
        tmpShape.setStyle(ShapeStyle.RECTANGLE);
        tmpShape.getPen().setColor(Color.BLUE);
        tmpShape.getPen().setWidth(2);
        tmpShape.setX0(62.5);
        tmpShape.setX1(87.5);
        tmpShape.setY0(17.5);
        tmpShape.setY1(126);
    }	
	
    private Button transparentButton;	
    
    private final static String URL_IMAGE1 = "images/shapeimage1.png";
    private final static String URL_IMAGE2 = "images/shapeimage2.png";    

}
