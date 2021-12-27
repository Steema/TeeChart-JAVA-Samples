/*
 * IrregularDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.surface.floatingpoint;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.styles.PaletteStyle;
import com.steema.teechart.styles.Surface;
import com.steema.teechart.tools.Rotate;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class IrregularDemo extends ChartSample implements SelectionListener {

    private Surface surfaceSeries;
	
	public IrregularDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source instanceof Button) {
        	boolean isSelected = ((Button)source).getSelection();
        	if (source == irregularButton) {       	
        		surfaceSeries.setIrregularGrid(isSelected);
        	} else if (source == view2DButton) {
        		if (isSelected) {
        			chart1.getAspect().setElevation(270);
        			chart1.getAspect().setRotation(360);
                }  else {
                	chart1.getAspect().setElevation(345);
                	chart1.getAspect().setRotation(345);
                }
        	}
        };
	}
	
	protected void createContent() {
		super.createContent();	
		irregularButton = addCheckButton("Irregular surface", "", this);
        view2DButton = addCheckButton("2D", "", this);        
	}

	protected void initContent() {
		super.initContent();
		irregularButton.setSelection(surfaceSeries.getIrregularGrid());
	}

	protected void initChart() {
		super.initChart();
        Legend tmpLegend = chart1.getLegend();
        tmpLegend.setColorWidth(30);
        tmpLegend.getSymbol().setWidth(30);
        tmpLegend.getSymbol().setContinuous(true);
        tmpLegend.getSymbol().setDefaultPen(false);

        //TODO chart1.setClipPoints(false);
        chart1.getAspect().setChart3DPercent(85);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(55);
        chart1.getAspect().setZoom(60);   
        
        surfaceSeries = new com.steema.teechart.styles.Surface(chart1.getChart());
        surfaceSeries.getMarks().setVisible(false);
        surfaceSeries.setIrregularGrid(true);          // <---------- VERY IMPORTANT !!!
        surfaceSeries.getVertAxis().setMinMax(-2,2);   // axis scale for Y values
        surfaceSeries.setUseColorRange(false);
        surfaceSeries.setUsePalette(true);
        surfaceSeries.setPaletteStyle(PaletteStyle.STRONG);

        /* Arrays of X and Z values with sample points...
         * The values have floating point decimals and define
         * an irregular grid
         */

        double[] xval = {0.1,0.2,0.3,0.5,0.8,1.1,1.5,2.0,2.2,3.0};
        double[] zval = {0.5,0.6,0.7,0.75,0.8,1.1,1.5,2.0,2.2,5.6};

        // Now add all "Y" points...
        surfaceSeries.clear();

        // An irregular grid of 10 x 10 cells
        surfaceSeries.setNumXValues(10);
        surfaceSeries.setNumZValues(10);

        double y;
        for (int x=0; x<10; x++) {                          // = 10 rows
            for (int z=0; z <10; z++) {                     // = 10 columns
                y = Math.sin(z*Math.PI/10.0)*Math.cos(x*Math.PI/5.0);               // example Y value
                surfaceSeries.add(xval[x], y, zval[z]);
            }
        }

        Rotate tool = new com.steema.teechart.tools.Rotate(chart1.getChart());
        tool.getPen().setColor(Color.WHITE);        
	}   	
    
    private Button irregularButton, view2DButton;    
}

