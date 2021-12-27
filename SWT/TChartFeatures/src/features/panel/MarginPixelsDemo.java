/*
 * MarginPixelsDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.panel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.PanelMarginUnits;

import features.ChartSample;
import features.utils.EnumStrings;

/**
 * @author tom
 *
 */
public class MarginPixelsDemo extends ChartSample implements ModifyListener, SelectionListener {

	public MarginPixelsDemo(Composite c) {
		super(c);
        marginUnitList.addSelectionListener(this);
        pixelSpinner.addModifyListener(this);		
	}
	
	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
        if (source == pixelSpinner) {
            int pixels = ((Spinner)source).getSelection();
            chart1.getPanel().setMarginBottom(pixels);
            chart1.getPanel().setMarginTop(pixels);
            chart1.getPanel().setMarginLeft(pixels);
            chart1.getPanel().setMarginRight(pixels);
        }
	}		

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
        if (source == marginUnitList) {
            switch (marginUnitList.getSelectionIndex()) {
                case 0: chart1.getPanel().setMarginUnits(PanelMarginUnits.PERCENT); break;
                case 1: chart1.getPanel().setMarginUnits(PanelMarginUnits.PIXELS); break;
            }
            syncUI();
        }
	}		
	
	protected void createContent() {
		super.createContent();
        addLabel(SWT.LEFT, "Margin Units:");
        marginUnitList = addCombo(SWT.READ_ONLY | SWT.BORDER | SWT.SINGLE);
        addLabel(SWT.LEFT, "Value:");
        pixelSpinner = addSpinner(SWT.READ_ONLY | SWT.BORDER, 0, 100, 1);
        unitLabel = addLabel(SWT.LEFT, "");
        syncUI();		
 	}
	
	protected void initContent() {
		super.initContent();
        marginUnitList.setItems(EnumStrings.PANEL_MARGIN_UNITS);
        marginUnitList.select(chart1.getPanel().getMarginUnits().getValue());
        pixelSpinner.setSelection((int)chart1.getPanel().getMarginLeft());
	}
	
	protected void initChart() {
		super.initChart();
        chart1.getHeader().setText("Margin Units");
        chart1.getHeader().setVisible(true);
        chart1.getPanel().setMarginBottom(10);
        chart1.getPanel().setMarginTop(10);
        chart1.getPanel().setMarginLeft(10);
        chart1.getPanel().setMarginRight(10);
        chart1.getPanel().setMarginUnits(PanelMarginUnits.PIXELS);
	}	

    private void syncUI() {
        switch (marginUnitList.getSelectionIndex()) {
            case 0: unitLabel.setText("%"); break;
            case 1: unitLabel.setText("percent"); break;
        }
    }

    private Combo marginUnitList;
    private Label unitLabel;
    private Spinner pixelSpinner;		
}
