/*
 * LineDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */package features.style.line;

import java.util.Random;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.Line;

import features.ChartSample;

/**
*
* @author tom
*/
public class LineDemo extends ChartSample implements SelectionListener {
	    
	/** Creates a new instance of LineDemo */
	public LineDemo(Composite c) {
		super(c);			
	}
	
	public void widgetDefaultSelected(SelectionEvent se) { }

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
		if (source==editButton) {
			//TODO
		}
	}	
		
    protected void createContent() {
    	super.createContent();    

    	SelectionAdapter optionsSelector = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent se) {	
				Widget source = se.widget;
				boolean isSelected = ((Button)source).getSelection();
				if (source == optionButtons[0]) {
					for (int i=0; i < chart1.getSeriesCount(); i++) {
						((Line)chart1.getSeries(i)).setStairs(isSelected);
			        }
			    } else if (source == optionButtons[1]) {
			    	for (int i=0; i < chart1.getSeriesCount(); i++) {
			    		chart1.getSeries(i).getMarks().setVisible(isSelected);
			        }
			    } else if (source == optionButtons[2]) {
			    	for (int i=0; i < chart1.getSeriesCount(); i++) {
			    		((Line)chart1.getSeries(i)).getPointer().setVisible(isSelected);
			        }
			    } else if (source == optionButtons[3]) {
			    	((Line)chart1.getSeries(0)).setStacked(isSelected ? CustomStack.STACK : CustomStack.NONE);
			    } else if (source == optionButtons[4]) {
			    	chart1.getAspect().setView3D(isSelected);
			    }					    				
			}				
		};
		
        optionButtons  = new Button[5];

        optionButtons[0] = addCheckButton(
        		"Stairs",
        		"",
        		optionsSelector);
        optionButtons[1] = addCheckButton(
        		"Marks",
        		"",
        		optionsSelector);
        optionButtons[2] = addCheckButton(
        		"Pointers",
        		"",
        		optionsSelector);
        optionButtons[3] = addCheckButton(
        		"Stacked",
        		"",
        		optionsSelector);
        optionButtons[4] = addCheckButton(
        		"3D",
        		"",
        		optionsSelector);
   	
    	editButton = addPushButton(
    			"Edit",
    			"Show the chart editor",
    			null
    	);     	       	
    }
    
    protected void initContent() {
    	super.initContent();

    	optionButtons[4].setSelection(true);    	
    	editButton.setEnabled(false);    	
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Lines with some null points");
        
    	Random generator = new Random();
        Line lineSeries;
        for (int i=0; i<2; i++) {
            lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
            lineSeries.setStairs(false);
            lineSeries.getMarks().setVisible(false);
            lineSeries.getPointer().setVisible(false);
            for (int t=0; t < 20; t++) {
                lineSeries.add(generator.nextInt(100));
            }
        }
        chart1.getSeries(0).setNull(3); // <-- null point example
        chart1.getSeries(1).setNull(10); // <-- null point example    
    }
    
    private Button editButton;
    private Button[] optionButtons;
}
