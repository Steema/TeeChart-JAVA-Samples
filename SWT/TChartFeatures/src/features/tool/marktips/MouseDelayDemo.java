/*
 * MouseDelayDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.tool.marktips;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Bar;
import com.steema.teechart.tools.MarksTip;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class MouseDelayDemo extends ChartSample implements ModifyListener, SelectionListener {

    private MarksTip tool;
    private Bar series1, series2, series3;
	
	public MouseDelayDemo(Composite c) {
		super(c);
        mouseDelaySpinner.addModifyListener(this);
        seriesList.addSelectionListener(this);		
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
        if (source == mouseDelaySpinner) {
            int pause = mouseDelaySpinner.getSelection();
            tool.setMouseDelay(pause);
        };		
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == seriesList) {
            switch (seriesList.getSelectionIndex()) {
                case 0: tool.setSeries(null); break;
                case 1: tool.setSeries(series1); break;
                case 2: tool.setSeries(series2); break;
                case 3: tool.setSeries(series3); break;
            }
        }
	}
	
	protected void createContent() {
		super.createContent();	 
		addLabel(SWT.LEFT, "Mouse delay: ");
		mouseDelaySpinner = addSpinner(SWT.BORDER, 0, 1000, 100);
		
		addLabel(SWT.LEFT, "Series: ");
		seriesList = addCombo(SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);	
	}

	protected void initContent() {
		super.initContent();	
		mouseDelaySpinner.setSelection(tool.getMouseDelay());
		seriesList.setItems(new String[] {"(all)", "Series1", "Series2", "Series3"});   	
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(false);
        
        tool = new com.steema.teechart.tools.MarksTip(chart1.getChart());

        series1 = new com.steema.teechart.styles.Bar(chart1.getChart());
        series1.getMarks().setVisible(false);
        series1.fillSampleValues(4);

        series2 = new com.steema.teechart.styles.Bar(chart1.getChart());
        series2.getMarks().setVisible(false);
        series2.fillSampleValues(4);

        series3 = new com.steema.teechart.styles.Bar(chart1.getChart());
        series3.getMarks().setVisible(false);
        series3.fillSampleValues(4);
	}   	

    private Combo seriesList;
    private Spinner mouseDelaySpinner;	
}
