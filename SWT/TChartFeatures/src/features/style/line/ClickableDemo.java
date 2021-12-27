/*
 * ClickableDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.line;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.SeriesPointer;

import features.ChartSample;
import features.WidgetFactory;

/**
 * @author tom
 *
 */
public class ClickableDemo extends ChartSample implements SelectionListener {
	
    private Line lineSeries;
   
	/** Creates a new instance of ClickableDemo */
	public ClickableDemo(Composite c) {
		super(c);
        chart1.addMouseMoveListener( new MouseMoveListener() { //TODO Check
            public void mouseMove(MouseEvent me) {
                int isClickable = lineSeries.clicked(me.x, me.y);
                if (isClickable == -1) {
                    coordinateLabel.setText("");
                } else {
                    coordinateLabel.setText("Point: "+String.valueOf(isClickable));
                }
                coordinateLabel.getParent().layout();
            }
        });		
	}
	
	public void widgetDefaultSelected(SelectionEvent se) { }

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
		boolean isSelected = ((Button)source).getSelection();
		if (source==clickableLineButton) {
			lineSeries.setClickableLine(isSelected);
		}
	}	
    protected void createContent() {
    	super.createContent();    

    	clickableLineButton = WidgetFactory.createCheckButton(
        		getButtonPane(),
        		"Clickable line",
        		"",
        		this);
    	
        coordinateLabel = WidgetFactory.createLabel(
        		getButtonPane(),
        		SWT.LEFT,
        		"");	
    }
    
    protected void initContent() {
    	super.initContent(); 	
    }
    
    protected void initChart() {
    	super.initChart();
        lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries.setClickableLine(false);

        SeriesPointer tmpPointer = lineSeries.getPointer();
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.RECTANGLE);
        tmpPointer.setVisible(true);
        lineSeries.fillSampleValues(8); 
    }
    	
    private Button clickableLineButton;
    private Label coordinateLabel;

}
