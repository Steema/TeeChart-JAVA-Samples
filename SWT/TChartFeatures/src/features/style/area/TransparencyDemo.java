/*
 * TransparencyDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.area;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.CustomStack;

import features.ChartSample;
import features.WidgetFactory;

/**
 * @author tom
 *
 */
public class TransparencyDemo extends ChartSample implements SelectionListener {

	public TransparencyDemo(Composite c) {
		super(c);
        for (int i=0; i < transparencySliders.length; i++) {
            transparencySliders[i].addSelectionListener(this);
        };		
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source instanceof Button) {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == transparencyButton) {
                for(int i=0; i < chart1.getSeriesCount(); i++) {
                    if (isSelected) {
                        ((Area)chart1.getSeries(i)).setTransparency(transparencySliders[i].getSelection());
                    } else {
                        ((Area)chart1.getSeries(i)).setTransparency(0);
                    }
                }
            } else if (source == view3DButton) {
            	chart1.getAspect().setView3D(isSelected);
            }        	
        } else if (source instanceof Slider) {
            for (int i=0; i < transparencySliders.length; i++) {
                if (transparencySliders[i] == source) {
                    ((Area)chart1.getSeries(i)).setTransparency(transparencySliders[i].getSelection());
                    ((Area)chart1.getSeries(i)).repaint();
                    break;
                }
            };            
        }
	}		
	
	protected void createContent() {
		super.createContent();

        view3DButton = WidgetFactory.createCheckButton(getButtonPane(), "3D", "", this);		
        transparencyButton = WidgetFactory.createCheckButton(getButtonPane(), "Transparency %: ", "", this);
        transparencySliders = new Slider[3];
        for (int i=0; i < transparencySliders.length; i++) {
            transparencySliders[i] = WidgetFactory.createSlider(getButtonPane(), SWT.HORIZONTAL, 0, 100, 56);
        };
 	}
	
	protected void initContent() {
		super.initContent();		  	
        transparencyButton.setSelection(true);
        view3DButton.setSelection(false);
	}
	
	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);	
        Area areaSeries = null;
        for (int i=0; i < 3; i++) {
            areaSeries = new com.steema.teechart.styles.Area(chart1.getChart());
            /* colors must be set before setting transparency */
            switch (i) {
                case 0: areaSeries.setColor(Color.RED); break;
                case 1: areaSeries.setColor(Color.GREEN); break;
                case 2: areaSeries.setColor(Color.NAVY); break;
            };
            areaSeries.setStacked(CustomStack.NONE);
            areaSeries.setStairs(false);
            areaSeries.setTransparency(56);
            areaSeries.fillSampleValues(20);

        }
	}   		

    private Slider[] transparencySliders;
    private Button transparencyButton;
    private Button view3DButton;    
}
