/*
 * PatternDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.line;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Line;

import features.ChartSample;
import features.utils.FileExtensions;

/**
 * @author tom
 *
 */
public class PatternDemo extends ChartSample implements SelectionListener {

	public PatternDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) { }

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
		FileDialog dialog = new FileDialog(getShell(), SWT.OPEN | SWT.SINGLE);
		dialog.setFilterExtensions(IMAGE_EXTENSIONS);
		dialog.setText("Select pattern file");
		if (dialog.open() != null) {
			String root = dialog.getFilterPath() + File.separatorChar;
			String filename = root + dialog.getFileName();
			 for (int i=0; i<3; i++) {
                 if (source == patternButton[i]) {
                     ((Line)chart1.getSeries(i)).getBrush().loadImage(filename);	//TODO
                     disposeImage(patternButton[i].getImage());
                     patternButton[i].setImage(new Image(getDisplay(), filename));
                     break;
                 }
             }			
		}
	}
	
    protected void createContent() {
    	super.createContent();    

        patternButton = new Button[3];
        for (int i=0; i<3; i++) {
            patternButton[i] = addPushButton(
            		"", 
            		"", 
            		this);
            	
            patternButton[i].setImage(new Image(getDisplay(), ChartSample.class.getResourceAsStream(URL_PATTERN[i])));
            patternButton[i].setSize(100, 100);
            
            patternButton[i].addDisposeListener(new DisposeListener() {
            	public void widgetDisposed(DisposeEvent e) {
            		disposeImage(((Button)e.widget).getImage());
            	}
            });
        }   	
    }
    
    protected void disposeImage(Image image) {
		if (image != null && !image.isDisposed()) {
			image.dispose();
		}    	
    }
    
    protected void initContent() {
    	super.initContent();
    }
    
    protected void initSeries() {
        Line lineSeries;
        for (int i=0; i<3; i++) {
            lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
            lineSeries.setStairs(false);
            lineSeries.getMarks().setVisible(false);
            lineSeries.getPointer().setVisible(false);
            lineSeries.getBrush().loadImage(ChartSample.class.getResourceAsStream(URL_PATTERN[i]));
            lineSeries.getBrush().setVisible(true);
        }
    }    
    
    protected void initChart() {
    	super.initChart();
        chart1.getLegend().getSymbol().setWidth(30);
        chart1.getLegend().setColorWidth(30);    	
        initSeries();  
        chart1.getSeries().fillSampleValues(8);
    }

    private final static String[] URL_PATTERN = new String[]{
        "images/pattern1.jpg",
        "images/pattern2.jpg",
        "images/pattern3.jpg"
    };
    
    private final static String[] IMAGE_EXTENSIONS = new String[] {
    	"*."+FileExtensions.GIF,
    	"*."+FileExtensions.JPG,
    	"*."+FileExtensions.PNG
    };

    private Button patternButton[]; 	
}
