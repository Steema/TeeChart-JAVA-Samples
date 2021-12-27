/*
 * PieDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.pie;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Pie;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class PieDemo extends ChartSample implements SelectionListener {

	private Pie pieSeries;

	public PieDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			if (source == editButton) {
				//TODO    ChartEditor.editSeries(pieSeries);
			} else {
				boolean isSelected = ((Button)source).getSelection();	        	
				if (source == optionButtons[0]) {
					chart1.getAspect().setView3D(isSelected);
				} else if (source == optionButtons[1]) {
					if (isSelected) {
						pieSeries.setExplodeBiggest(30);
					} else {
						pieSeries.setExplodeBiggest(0);
					}
				} else if (source == optionButtons[2]) {
					pieSeries.getMarks().setVisible(isSelected);
				} else if (source == optionButtons[3]) {
					if (isSelected) {
						pieSeries.getShadow().setVisible(true);
					} else {
						pieSeries.getShadow().setVisible(false);
					}
					pieSeries.repaint();
				} else if (source == optionButtons[4]) {
					if (isSelected) {
						pieSeries.setAngleSize(180);
						optionButtons[3].setSelection(false);
					} else {
						pieSeries.setAngleSize(360);
					}
				}
			}
		}
	}

	protected void createContent() {
		super.createContent();
		optionButtons  = new Button[5];
		optionButtons[0] = addCheckButton("3D", "", this);        
		optionButtons[1] = addCheckButton("Exploded", "", this);
		optionButtons[2] = addCheckButton("Marks", "", this);
		optionButtons[3] = addCheckButton("Shadow", "", this);
		optionButtons[4] = addCheckButton("Partial", "", this);       
	}

	protected void initContent() {
		super.initContent();
		optionButtons[0].setSelection(true);
		optionButtons[2].setSelection(pieSeries.getMarks().getVisible());
		optionButtons[3].setSelection(pieSeries.getShadow().getVisible());        
	}

	protected void initChart() {
		super.initChart();
		chart1.getHeader().setVisible(true);
		chart1.setText("Pie");
		chart1.getAspect().setElevation(315);
		chart1.getAspect().setOrthogonal(false);
		chart1.getAspect().setPerspective(0);
		chart1.getAspect().setRotation(360);		
		
		pieSeries = new com.steema.teechart.styles.Pie(chart1.getChart());
		pieSeries.getMarks().setVisible(true);
		pieSeries.getShadow().setVisible(false);
		pieSeries.getShadow().setHorizSize(20);
		pieSeries.getShadow().setVertSize(20);
		pieSeries.fillSampleValues(8);
		
		pieSeries.setRotationAngle(75);
		
    pieSeries.setBevelPercent(10);
    pieSeries.getPen().setVisible(false);
	}   

	private Button editButton;
	private Button[] optionButtons;	
}
