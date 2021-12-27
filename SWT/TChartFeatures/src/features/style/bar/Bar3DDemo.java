/*
 * Bar3DDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bar;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.Bar3D;
import com.steema.teechart.styles.BarStyle;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class Bar3DDemo extends ChartSample implements SelectionListener {

    private Bar3D series1;
	
	public Bar3DDemo(Composite c) {
		super(c);
		styleList.addSelectionListener(this);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source == styleList) {
            switch (styleList.getSelectionIndex()) {
                case 0: series1.setBarStyle(BarStyle.RECTANGLE); break;
                case 1: series1.setBarStyle(BarStyle.PYRAMID); break;
                case 2: series1.setBarStyle(BarStyle.INVPYRAMID); break;
                case 3: series1.setBarStyle(BarStyle.CYLINDER); break;
                case 4: series1.setBarStyle(BarStyle.ELLIPSE); break;
                case 5: series1.setBarStyle(BarStyle.ARROW); break;
                case 6: series1.setBarStyle(BarStyle.RECTGRADIENT); break;
                case 7: series1.setBarStyle(BarStyle.CONE); break;
            }
        } else if (source == editButton) {
            //TODO ChartEditor.editSeries(series1);
        }
	}	
	
    protected void createContent() {
    	super.createContent();    	       	
    	addLabel(SWT.LEFT, "Styles: ");    	
    	styleList = addCombo(SWT.READ_ONLY | SWT.SINGLE);
    	editButton = addPushButton("Edit...", "", this);
    }
    
    protected void initContent() {
    	super.initContent();   
        styleList.setItems(
                new String[] {
                    "Rectangle",
                    "Pyramid",
                    "Inverted Pyramid",
                    "Cylinder",
                    "Ellipse",
                    "Arrow",
                    "Rect. Gradient",
                    "Cone"});
        styleList.select(6);
        editButton.setEnabled(false);
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Bar 3D series");    	
        series1 = new Bar3D(chart1.getChart());
        series1.add( 0, 250, 200, "A", Color.RED );
        series1.add( 1,  10, 200, "B", Color.GREEN );
        series1.add( 2,  90, 100, "C", Color.YELLOW );
        series1.add( 3,  30,  50, "D", Color.BLUE );
        series1.add( 4,  70, 150, "E", Color.WHITE );
        series1.add( 5, 120, 150, "F", Color.SILVER );
        series1.setColorEach(true);
        series1.getMarks().setArrowLength(20);
        series1.getMarks().setVisible(true);
        series1.setBarStyle(BarStyle.RECTGRADIENT);
        series1.setBarWidthPercent(90);
        series1.getGradient().setDirection(GradientDirection.HORIZONTAL);
        series1.getGradient().setStartColor(Color.YELLOW);
    }   			
    
    private Button editButton;    
    private Combo styleList;	
}
