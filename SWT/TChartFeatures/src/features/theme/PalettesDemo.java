/*
 * PalettesDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */package features.theme;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.themes.ColorPalettes;
import com.steema.teechart.themes.Theme;

import features.ChartSample;

/**
*
* @author tom
*/
public class PalettesDemo extends ChartSample implements SelectionListener {

    private Color[] redPalette;
    private Color[] bluePalette;
    private Color[] greenPalette;
    
	public PalettesDemo(Composite c) {
		super(c);
		
		customList.addSelectionListener(this);
		defaultList.addSelectionListener(this);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
        if (source instanceof Combo) {
            int index = ((Combo)source).getSelectionIndex();
            if (se.widget == customList) {
                // Set a custom color palette
                switch (index) {
                    case 1: ColorPalettes.applyPalette(chart1.getChart(), redPalette); break;
                    case 2: ColorPalettes.applyPalette(chart1.getChart(), bluePalette); break;
                    case 3: ColorPalettes.applyPalette(chart1.getChart(), greenPalette); break;
                    default : ColorPalettes.applyPalette(chart1.getChart(), Theme.DefaultPalette);
                }
            } else if (source == defaultList) {
                ColorPalettes.applyPalette(chart1.getChart(), index);
            }
        }		
	}	
	
    protected void createContent() {
    	super.createContent();
    	   	
    	addLabel(SWT.LEFT, "Custom Palettes: ");
    	customList = addCombo(SWT.DROP_DOWN | SWT.READ_ONLY);
    	
    	addLabel(SWT.LEFT, "Default Palettes: ");
    	defaultList = addCombo(SWT.DROP_DOWN | SWT.READ_ONLY);    	   	
    }
    
    protected void initContent() {
    	super.initContent();
    	
    	customList.setItems(new String[] {"Default", "Red", "Blue", "Green"});
    	customList.setText(customList.getItem(0));    	
    	defaultList.setItems(ColorPalettes.PaletteNames);
    	defaultList.setText(ColorPalettes.PaletteNames[0]);
    	
    	redPalette = new Color[15];
        bluePalette = new Color[15];
        greenPalette = new Color[15];
        float tmp = (float)(128.0/15.0);
        for (int t=0; t<15; t++) {
            redPalette[t] = Color.fromArgb(128+Math.round(t*tmp),0,0);
            bluePalette[t] = Color.fromArgb(0,0,128+Math.round(t*tmp));
            greenPalette[t] = Color.fromArgb(0,128+Math.round(t*tmp),0);
        }           
    }
    
    protected void initChart() {
    	super.initChart();

        chart1.getHeader().setVisible(true);
        chart1.setText("TChart - Color Palettes");
        
        Bar series = new Bar(chart1.getChart());
        series.fillSampleValues(15);
        series.setColorEach(true);  // <-- IMPORTANT, USE COLOR PALETTE   	    
    }   
		
    private Combo customList, defaultList;    
}
