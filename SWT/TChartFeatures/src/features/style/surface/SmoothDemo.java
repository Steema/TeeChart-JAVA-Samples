package features.style.surface;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Surface;

import features.ChartSample;

public class SmoothDemo extends ChartSample implements SelectionListener {

	private Surface surfaceSeries;
    
	public SmoothDemo(Composite c) {
		super(c);
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source instanceof Button) {
        	boolean isSelected = ((Button)source).getSelection();
        	if (source == smoothButton) {       	
        		surfaceSeries.setSmoothPalette(isSelected);
        	}
        };
	}
	
	protected void createContent() {
		super.createContent();	
        smoothButton = addCheckButton("Smooth Palette", "", this);
	}

	protected void initContent() {
		super.initContent();
        smoothButton.setSelection(surfaceSeries.getSmoothPalette());	
	}

	protected void initChart() {
		super.initChart();
		surfaceSeries = new com.steema.teechart.styles.Surface(chart1.getChart());
		surfaceSeries.fillSampleValues(10);
		surfaceSeries.setSmoothPalette(true);
		surfaceSeries.getSideBrush().setVisible(false);
		surfaceSeries.getSideBrush().setColor(Color.WHITE);
		surfaceSeries.setStartColor(new Color(4210816));   
	}   	
    
    private Button smoothButton;    
}
