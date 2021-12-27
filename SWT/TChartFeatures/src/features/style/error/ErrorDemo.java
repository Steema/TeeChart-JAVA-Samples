/*
 * ErrorDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.error;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.StringAlignment;
import com.steema.teechart.legend.LegendSymbolSize;
import com.steema.teechart.styles.ErrorStyle;
import com.steema.teechart.styles.Error;

import features.ChartSample;
import features.utils.EnumStrings;

/**
 * @author tom
 *
 */
public class ErrorDemo extends ChartSample implements SelectionListener {

    private Error series;
    
	public ErrorDemo(Composite c) {
		super(c);
		styleList.addSelectionListener(this);
		sizeSlider.addSelectionListener(this);
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source == editButton) {
           //TODO ChartEditor.editSeries(series);
        } else if (source == styleList) {
            switch (styleList.getSelectionIndex()) {
                case 0: series.setErrorStyle(ErrorStyle.LEFT); break;
                case 1: series.setErrorStyle(ErrorStyle.TOP); break;
                case 2: series.setErrorStyle(ErrorStyle.RIGHT); break;
                case 3: series.setErrorStyle(ErrorStyle.BOTTOM); break;
                case 4: series.setErrorStyle(ErrorStyle.LEFTRIGHT); break;
                case 5: series.setErrorStyle(ErrorStyle.TOPBOTTOM); break;
            }
        } else if (source == sizeSlider) {
            int size = ((Slider)source).getSelection();
            series.setErrorWidth(size);        	
        }
	}	
	
    protected void createContent() {
    	super.createContent();    	   	
        editButton = addPushButton("Edit...", "Edit series", this);
        addLabel(SWT.LEFT, "Style: ");
        styleList = addCombo(SWT.READ_ONLY | SWT.SINGLE);        
        addLabel(SWT.LEFT, "Size: ");
        sizeSlider = addSlider(SWT.HORIZONTAL, 0, 200, 100);   	    	    	
    }
    
    protected void initContent() {
    	super.initContent();    	   	       
        styleList.setItems(EnumStrings.ERROR_STYLES);
        styleList.select(5);
        sizeSlider.setSelection(series.getErrorWidth());        
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.getHeader().setAlignment(StringAlignment.NEAR);
        chart1.setText("Error Series");
        chart1.getLegend().getSymbol().setWidth(15);
        chart1.getLegend().getSymbol().setWidthUnits(LegendSymbolSize.PIXELS);    	
        initSeries();
    }   			

    private void initSeries() {
        series = new Error(chart1.getChart());
    	series.clear();
        /* Index Value Error Label Color */
        series.add(0, 50, 10, "A", Color.RED);
        series.add(1, 80, 20, "B", Color.YELLOW);
        series.add(2, 20,  8, "C", Color.GREEN);
        series.add(3, 60, 30, "D", Color.BLUE);
        series.add(4, 40,  5, "E", Color.WHITE);
    }
    
    private Button editButton;
    private Combo styleList;
    private Slider sizeSlider;	
}
