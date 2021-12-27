/*
 * BrushImageDemo.java
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

import com.steema.teechart.ImageMode;
import com.steema.teechart.styles.ImageBar;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class BrushImageDemo extends ChartSample implements SelectionListener {

    private ImageBar series;
    
	public BrushImageDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == editButton) {
            //TODO BrushEditor.edit(chart1.getWalls().getBack().getBrush());
        } else {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == backImageButton) {
                chart1.getWalls().getBack().setTransparent(!isSelected);
                chart1.refreshControl();
//                chart1.invalidate();
//                chart1.repaint();
            }   	
        }
	}
	
	protected void createContent() {
		super.createContent();	

		backImageButton = addCheckButton("Back wall image", "", this);
        editButton = addPushButton("Edit...", "", this);  
	}

	protected void initContent() {
		super.initContent();
		backImageButton.setSelection(!chart1.getWalls().getBack().getTransparent());
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Back wall with image");
        chart1.getWalls().getBack().setTransparent(false);
        chart1.getWalls().getBack().getBrush().loadImage(ChartSample.class.getResourceAsStream(URL_BACKIMAGE));
        chart1.getWalls().getBack().getBrush().setImageMode(ImageMode.TILE);       
	}   		

    private Button backImageButton;
    private Button editButton;
    private final static String URL_BACKIMAGE = "images/backwall.jpg";	
}
