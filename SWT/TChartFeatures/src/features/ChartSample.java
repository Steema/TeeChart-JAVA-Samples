/*
 * ChartSample.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.TChart;
import com.steema.teechart.drawing.Gradient;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.themes.ColorPalettes;

/**
*
* @author tom
*/
public class ChartSample extends BaseSample {

	protected TChart chart1;
	
	/** Creates a new instance of ChartSample */	
	public ChartSample(Composite c) {
		super(c);
	}

	/**
	 * initializes TChart stuff
	 * @throws IOException 
	 */
	protected void initChart() {
		chart1.getFooter().setVisible(false);
        chart1.getHeader().setVisible(false);
        chart1.getAspect().setView3D(true);

        Gradient g=chart1.getPanel().getGradient();

        g.setDirection(GradientDirection.VERTICAL);

        // Smooth gray-like colors that adapt correctly to all demos:
        g.setEndColor(new com.steema.teechart.drawing.Color(109,109,109));
        g.setMiddleColor(new com.steema.teechart.drawing.Color(149,202,255));
        g.setStartColor(new com.steema.teechart.drawing.Color(0,115,230));

        g.setVisible(true);

        chart1.getAxes().getLeft().getAxisPen().setWidth(1);
        chart1.getAxes().getBottom().getAxisPen().setWidth(1);

        chart1.getHeader().getFont().setColor(new com.steema.teechart.drawing.Color(255,255,128));
        
        ColorPalettes.applyPalette(chart1.getChart(),1); // Excel color palette	
	}
	
    /**
     * Creates the UI components.
     */
    protected void createContent() {
    	super.createContent();
    	
    	chart1 = new TChart(getSamplePane(), SWT.NONE);    	
    	
    	// Trick: Show the Chart editor modal dialog when double-clicking the "description" text area    
    	// sampleDescription.addMouseListener( new MouseAdapter() {
    	//	public void mouseDoubleClick(MouseEvent me) {
    	//		//TODO ChartEditor.editChart(chart1.getChart());
    	//	}
    	//});    
    }
    
    /**
     * Initializes and configures the UI components.
     */
    protected void initContent() {
    	super.initContent();
    	initChart();
    }
}
