/*
 * PointsTreatNullsDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.pointer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Points;
import com.steema.teechart.styles.TreatNullsStyle;

import features.ChartSample;


/**
*
* @author tom
*/
public class PointsTreatNullsDemo extends ChartSample implements SelectionListener {

    private Points pointsSeries;
	
	public PointsTreatNullsDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
        if (source == dontPaintButton) {
        	pointsSeries.setTreatNulls(TreatNullsStyle.DONOTPAINT);
        } else if (source == skipButton) {
        	pointsSeries.setTreatNulls(TreatNullsStyle.SKIP);
        } else if (source == ignoreButton) {
        	pointsSeries.setTreatNulls(TreatNullsStyle.IGNORE);
        } else {		
        	boolean isSelected=((Button)source).getSelection();
        	if (source == autoVertAxisButton) {
        		pointsSeries.getVertAxis().setAutomatic(isSelected);
                if (!pointsSeries.getVertAxis().getAutomatic()) {
                	pointsSeries.getVertAxis().setMinMax(-10,30);
                }
        	} 
        }
	}
	
	protected void createContent() {
		super.createContent();

		addLabel(SWT.LEFT, "Treat Nulls:");
		dontPaintButton = addRadioButton("Don't Paint", "", this);
		skipButton = addRadioButton("Skip", "", this); 
		ignoreButton = addRadioButton("Ignore", "", this);
		
        autoVertAxisButton = addCheckButton("Automatic vertical axis", "", this);	
    }
	

    private void fillSeries() {
        boolean  done = false;
        double y;
        for (int x = 0; x <= 5; x++) {
            y = (x % 2 == 0) ? 10 : 20;
            if (x == 3) {
                if (!done) {
                    pointsSeries.add(x, 0, Color.TRANSPARENT);
                }
                done = true;
            }
            else { 
            	pointsSeries.add(x, y);
            }
        }        
    }
    
    private void initSeries() {
		pointsSeries = new com.steema.teechart.styles.Points(chart1.getChart());
        pointsSeries.setColor(Color.fromArgb(224,77,44));
        pointsSeries.setTreatNulls(TreatNullsStyle.DONOTPAINT);
        fillSeries();
        pointsSeries.getVertAxis().setMinMax(-10, 30);    	
    }
	
	protected void initContent() {
		super.initContent();
		initSeries();
        dontPaintButton.setSelection(true);
        autoVertAxisButton.setSelection(pointsSeries.getVertAxis().getAutomatic());        
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getAspect().setSmoothingMode(true);
        chart1.getHeader().setVisible(true);
        chart1.getHeader().getFont().setColor(Color.NAVY);
        chart1.setText("Null points support");
        
        chart1.getPanel().getGradient().setEndColor(Color.fromArgb(254,21,60,89));
        chart1.getPanel().getGradient().setStartColor(Color.fromArgb(254,255,255,255));
        chart1.getPanel().getGradient().setUseMiddle(false);
        chart1.getPanel().getGradient().setVisible(true);
            
        chart1.getWalls().getBack().setTransparent(false);
        chart1.getWalls().getBack().getGradient().setVisible(true);
        chart1.getWalls().getBack().getGradient().setUseMiddle(false);
        chart1.getWalls().getBack().getGradient().setStartColor(Color.fromArgb(234, 234, 234));
        chart1.getWalls().getBack().getGradient().setEndColor(Color.WHITE);
	}   	
	
    private Button dontPaintButton, skipButton, ignoreButton, autoVertAxisButton;    
}
