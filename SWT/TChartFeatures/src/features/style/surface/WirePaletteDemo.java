/*
 * WirePaletteDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.surface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Surface;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class WirePaletteDemo extends ChartSample implements SelectionListener {

    private Surface series;
	
	public WirePaletteDemo(Composite c) {
		super(c);
        colorStyleList.addSelectionListener(this);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == colorStyleList) {
            switch (colorStyleList.getSelectionIndex()) {
                case 0: {
                    series.setUseColorRange(false);
                    series.setUsePalette(false);
                    break;
                }
                case 1: {
                    series.setUseColorRange(true);
                    series.setUsePalette(false);
                    break;
                }
                case 2: {
                    series.setUseColorRange(false);
                    series.setUsePalette(true);
                    break;
                }
            }
        } else if (source instanceof Button) {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == wireFrameButton) {
                series.setWireFrame(isSelected);
            }
        };
	}
	
	protected void createContent() {
		super.createContent();	
		
        addLabel(SWT.LEFT, "Wire-frame Color Style: ");
        colorStyleList = addCombo(SWT.DROP_DOWN | SWT.READ_ONLY);        
		wireFrameButton = addCheckButton("Wire-frame", "", this);       
	}

	protected void initContent() {
		super.initContent();
        colorStyleList.setItems(new String[] {"Single", "Range", "Palette"});
        colorStyleList.select(2);	
        wireFrameButton.setSelection(series.getWireFrame());    	
	}

	protected void initChart() {
		super.initChart();
        chart1.getWalls().getBottom().setTransparent(true);
        chart1.getWalls().getLeft().setTransparent(true);   
        series = new com.steema.teechart.styles.Surface(chart1.getChart());
        series.setWireFrame(true);
        series.setUsePalette(true);
        series.setUseColorRange(false);
        series.getPen().setWidth(1);
        series.fillSampleValues(10);        
	}   	
	
    Button wireFrameButton;
    Combo colorStyleList;    
}
