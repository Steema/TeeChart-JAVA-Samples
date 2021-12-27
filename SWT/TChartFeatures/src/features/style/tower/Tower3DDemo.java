/*
 * Tower3DDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.tower;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.PaletteStyle;
import com.steema.teechart.styles.Tower;
import com.steema.teechart.styles.TowerStyle;

import features.ChartSample;
import features.utils.EnumStrings;

/**
 * @author tom
 *
 */
public class Tower3DDemo extends ChartSample implements SelectionListener {

    private Tower series;
    
	public Tower3DDemo(Composite c) {
		super(c);
		styleList.addSelectionListener(this);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
        if (source == editButton) {
            //TODO ChartEditor.editSeries(series);
        } else if (source == styleList) {
            switch (styleList.getSelectionIndex()) {
                case 0: series.setTowerStyle(TowerStyle.CUBE); break;
                case 1: series.setTowerStyle(TowerStyle.RECTANGLE); break;
                case 2: series.setTowerStyle(TowerStyle.COVER); break;
                case 3: series.setTowerStyle(TowerStyle.CYLINDER); break;
                case 4: series.setTowerStyle(TowerStyle.ARROW); break;
                case 5: series.setTowerStyle(TowerStyle.CONE); break;
            }
        } else if (source == useOriginButton) {
            series.setUseOrigin(((Button)source).getSelection());
        }
	}	
	
    protected void createContent() {
    	super.createContent();
    	   	
    	editButton = addPushButton("Edit...", "Edit series", this);
    	
    	addLabel(SWT.LEFT, "Style: ");
    	styleList = addCombo(SWT.DROP_DOWN | SWT.READ_ONLY);
    	
    	useOriginButton = addCheckButton("Use origin", "", this);
    }
    
    protected void initContent() {
    	super.initContent();
    	
    	styleList.setItems(EnumStrings.TOWER_STYLES);
    	styleList.select(0);
    	
        useOriginButton.setSelection(series.getUseOrigin());    	
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Tower 3D Series");
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setChart3DPercent(55);
        chart1.getAxes().getDepth().setVisible(true);
        chart1.getAspect().setPerspective(100);
        
        series = new Tower(chart1.getChart());
        series.setOrigin(500.0);
        series.setUseOrigin(true);
        series.setPaletteStyle(PaletteStyle.STRONG);
        series.setUseColorRange(false);
        series.setUsePalette(true);
        series.clear();
        Random generator = new Random();
        for (int x=1; x <= 10; x++) {
            for (int z=1; z <= 10; z++) {
                series.add(x, generator.nextInt(1000)-100, z);
            }
        }        
    }   
    
    private Button editButton;
    private Button useOriginButton;
    private Combo styleList;	
}
