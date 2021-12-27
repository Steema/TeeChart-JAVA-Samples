
package features.legend;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Candle;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class LegendWidthsDemo extends ChartSample implements ModifyListener, SelectionListener {

	public LegendWidthsDemo(Composite c) {
		super(c);
        width1Spinner.addModifyListener(this);
        width2Spinner.addModifyListener(this);		
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		int width = ((Spinner)source).getSelection();
        if (source == width1Spinner) {
            chart1.getLegend().setColumnWidth(0, width);
        } else {
            chart1.getLegend().setColumnWidth(1, width);
        }
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == autoWidthsButton) {
	            chart1.getLegend().setColumnWidthAuto(isSelected);
	            syncUI();
	        }
		}
	}
	
	protected void createContent() {
		super.createContent();	

        autoWidthsButton = addCheckButton("Auto widths", "", this);
        addLabel(SWT.LEFT, "Column 1 width:");
        width1Spinner = addSpinner(SWT.READ_ONLY | SWT.BORDER, 0, 100, 1);
        addLabel(SWT.LEFT, "Column 2 width:");        
        width2Spinner = addSpinner(SWT.READ_ONLY | SWT.BORDER, 0, 100, 1);
	}

	protected void initContent() {
		super.initContent();
        autoWidthsButton.setSelection(chart1.getLegend().getColumnWidthAuto());
        width1Spinner.setSelection(chart1.getLegend().getColumnWidth(0));
        width2Spinner.setSelection(chart1.getLegend().getColumnWidth(1));        
        syncUI();        
	}

	protected void initChart() {
		super.initChart();
        Candle series = new Candle(chart1.getChart());
        series.fillSampleValues(20);
	}
	
    protected void syncUI() {
        width1Spinner.setEnabled(!autoWidthsButton.getSelection());
        width2Spinner.setEnabled(!autoWidthsButton.getSelection());
    }

    private Button autoWidthsButton;
    private Spinner width1Spinner, width2Spinner;	
}
