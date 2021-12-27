/*
 * CustomPolarTreatNullsDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.polar;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.CustomPolar;
import com.steema.teechart.styles.Polar;
import com.steema.teechart.styles.Radar;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.TreatNullsStyle;
import com.steema.teechart.styles.WindRose;

import features.ChartSample;

public class CustomPolarTreatNullsDemo extends ChartSample implements SelectionListener {

    private CustomPolar series;
    
	public CustomPolarTreatNullsDemo(Composite c) {
		super(c);
		treatNullsList.addSelectionListener(this);
		styleList.addSelectionListener(this);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
        if (source instanceof Combo) {
            int index = ((Combo)source).getSelectionIndex();
            if (source == treatNullsList) {                                       
                if (index == 0) {
                    series.setTreatNulls(TreatNullsStyle.DONOTPAINT);
                } else {
                    if (index == 1) {
                        series.setTreatNulls(TreatNullsStyle.SKIP);
                    } else {
                        series.setTreatNulls(TreatNullsStyle.IGNORE);
                    }
                }
            }    
            else  if (source == styleList) {                                        
                Series s = chart1.getSeries(0);
                
                if (index == 1) {
                	try {
						Series.changeType(s, Radar.class);
					} catch (IllegalArgumentException ex) {
						Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
					} catch (InstantiationException ex) {
						Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
					} catch (IllegalAccessException ex) {
						Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
					} catch (NoSuchMethodException ex) {
						Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
					} catch (InvocationTargetException ex) {
						Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
					}
                } else {
                    if (index == 2) {
                    	try {
							Series.changeType(s, WindRose.class);
						} catch (IllegalArgumentException ex) {
							Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
						} catch (InstantiationException ex) {
							Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
						} catch (IllegalAccessException ex) {
							Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
						} catch (NoSuchMethodException ex) {
							Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
						} catch (InvocationTargetException ex) {
							Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
						}
                    } else {
                    	try {
							Series.changeType(s, Polar.class);
						} catch (IllegalArgumentException ex) {
							Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
						} catch (InstantiationException ex) {
							Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
						} catch (IllegalAccessException ex) {
							Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
						} catch (NoSuchMethodException ex) {
							Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
						} catch (InvocationTargetException ex) {
							Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
						}
                    }
                }
                
                chart1.getAspect().setView3D(false);
            } 
        } else if (source == nullButton) {
            series.setNull(5,nullButton.getSelection());
            series.repaint();
         } else if (source == filledButton) {
             series.getBrush().setVisible(filledButton.getSelection());
         }		
	}	
	
    protected void createContent() {
    	super.createContent();
    	   	
    	addLabel(SWT.LEFT, "Style: ");
    	styleList = addCombo(SWT.DROP_DOWN | SWT.READ_ONLY);
    	
    	addLabel(SWT.LEFT, "Treat Nulls: ");
    	treatNullsList = addCombo(SWT.DROP_DOWN | SWT.READ_ONLY); 
    	
    	nullButton = addCheckButton("Point 5 is null", "", this);
    	filledButton = addCheckButton("Filled", "", this);
    }
    
    protected void initContent() {
    	super.initContent();
    	
    	styleList.setItems(new String[] {"Polar", "Radar", "Windrose"});
    	styleList.select(0);    	
    	treatNullsList.setItems(new String[] {"Don't paint", "Skip", "Ignore"});
    	treatNullsList.select(2);
    	
    	nullButton.setSelection(true);
    	filledButton.setSelection(true);    	
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getAspect().setSmoothingMode(true);
        chart1.getSeries().add(new Polar());
        series = (CustomPolar)chart1.getSeries(0);
        series.setColor(Color.fromArgb(224,77,44));
        series.fillSampleValues(10);
        series.setTreatNulls(TreatNullsStyle.IGNORE);        
        series.setNull(5,nullButton.getSelection());        
    }   
		
    private Combo treatNullsList, styleList;
    private Button nullButton, filledButton;   
}
