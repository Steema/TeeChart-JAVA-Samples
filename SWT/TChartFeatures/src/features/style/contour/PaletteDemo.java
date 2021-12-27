/*
 * PaletteDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.contour;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Contour;
import com.steema.teechart.styles.PaletteStyle;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class PaletteDemo extends ChartSample implements SelectionListener {

    private Contour series;

	public PaletteDemo(Composite c) {
		super(c);
        colorList.addSelectionListener(this);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == colorList) {
        	switch (colorList.getSelectionIndex()) {
            case 0: {                                       //single color
                series.setUseColorRange(false);
                series.setUsePalette(false);
                series.setColor(Color.YELLOW);
                break;
            }
            case 1: {                                  //gradient 2 colors
                series.setUseColorRange(true);
                series.setUsePalette(false);
                series.setStartColor(Color.BLUE);
                series.setEndColor(Color.RED);
                series.setMidColor(Color.TRANSPARENT);
                break;
            }
            case 2: {                                  //gradient 3 colors
                series.setUseColorRange(true);
                series.setUsePalette(false);
                series.setStartColor(Color.BLUE);
                series.setEndColor(Color.RED);
                series.setMidColor(Color.YELLOW);
                break;
            }
            case 3: {                                 // palette "pale"
                series.setUseColorRange(false);
                series.setUsePalette(true);
                series.setPaletteStyle(PaletteStyle.PALE);
                break;
            }
            case 4: {                                 // palette  "strong"
                series.setUseColorRange(false);
                series.setUsePalette(true);
                series.setPaletteStyle(PaletteStyle.STRONG);
                break;
            }
            case 5: {                            // palette  "gray levels"
                series.setUseColorRange(false);
                series.setUsePalette(true);
                series.setPaletteStyle(PaletteStyle.GRAYSCALE);
                break;
            }
            case 6: {                            // palette  "inverted gray levels"
                series.setUseColorRange(false);
                series.setUsePalette(true);
                series.setPaletteStyle(PaletteStyle.INVERTED_GRAYSCALE);
                break;
            }
            case 7: {                            // palette  "rainbow"
                series.setUseColorRange(false);
                series.setUsePalette(true);
                series.setPaletteStyle(PaletteStyle.RAINBOW);
                break;
            }
        }
    }
	}
	
	protected void createContent() {
		super.createContent();	
		
        addLabel(SWT.LEFT, "Colors: ");
        colorList = addCombo(SWT.DROP_DOWN | SWT.READ_ONLY);    
	}

	protected void initContent() {
		super.initContent();
        colorList.setItems(new String[] {
        		"Single color",
                "Gradient 2 colors",
                "Gradient 3 colors",
                "Palette Pale",
                "Palette Strong",
                "Palette Grayscale",
                "Inverted Gray",
                "Rainbow"});
        colorList.select(2);	
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getAxes().getDepth().setVisible(true);
        chart1.getLegend().getSymbol().setContinuous(true);
        chart1.getLegend().setTransparent(true);		
        series = new com.steema.teechart.styles.Contour(chart1.getChart());
        series.fillSampleValues(25);
        series.getMarks().setVisible(true);
        series.setYPosition(0.19);
        series.setStartColor(Color.BLUE);
        series.setEndColor(Color.YELLOW);
        series.setMidColor(Color.RED);
        series.setPaletteSteps(34);      
	}   	
	
    Button wireFrameButton;
    Combo colorList;    
}
