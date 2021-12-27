/*
 * ImageBarDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.bar;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.ImageBar;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class ImageBarDemo extends ChartSample implements SelectionListener {

    private ImageBar series;
    
	public ImageBarDemo(Composite c) {
		super(c);
		// TODO Auto-generated constructor stub
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source == imageButton) {            
    		FileDialog dialog = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE);
    		dialog.setText("Open");
    		if (dialog.open() != null) {
    			String root = dialog.getFilterPath() + File.separatorChar;
    			String filename = root + dialog.getFileName();    			
    			setSeriesImage(new Image(getDisplay(), filename));
    		}
        } else {
            boolean isSelected = ((Button)source).getSelection();
            if (source == tiledButton) {
                series.setImageTiled(isSelected);
            } else if (source == colorEachButton) {
                series.setColorEach(isSelected);
            }        	
        }
	}	
	
    protected void createContent() {
    	super.createContent();
    	   	
    	addLabel(SWT.LEFT, "Image: ");    	
    	imageButton = addPushButton("", "", this);
        tiledButton = addCheckButton("Tiled", "", this);        
        colorEachButton = addCheckButton("Color each", "", this);
        //TODO colorButton = new ButtonColor("Color...", series.getColor());        
    }
    
    protected void initContent() {
    	super.initContent();    	   	
    	tiledButton.setSelection(series.getImageTiled());
    	colorEachButton.setSelection(series.getColorEach());
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Image Bar series");
        series = new ImageBar(chart1.getChart());
        series.setColor(Color.RED);
        series.fillSampleValues(6);
        series.getMarks().setArrowLength(20);
        series.getMarks().getCallout().getBrush().setColor(Color.BLACK);
        series.getMarks().getCallout().setLength(20);
        series.getMarks().setVisible(true);
        series.setImageTiled(true);

        setSeriesImage(new Image(getDisplay(), ChartSample.class.getResourceAsStream(URL_IMAGEBAR)));        
    }   			
    
    protected void setSeriesImage(Image image) {
        imageButton.setImage(image);
        //TODO series.setImage(image);
    }
    
    //private ButtonColor colorButton;
    private Button tiledButton, colorEachButton;
    private Button imageButton;
    private final static String URL_IMAGEBAR = "images/imagebar.png";
}
