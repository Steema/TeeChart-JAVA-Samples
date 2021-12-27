/*
 * AxisTitleDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.axes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.AxisTitle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Points;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class AxisTitleDemo extends ChartSample implements ModifyListener, SelectionListener {

	public AxisTitleDemo(Composite c) {
		super(c);
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
        if (source == titleField) {
            chart1.getAxes().getLeft().getTitle().setCaption(titleField.getText());
        }
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == visibleButton) {
	            chart1.getAxes().getLeft().getTitle().setVisible(isSelected);
	        }		
		}
	}
	
	protected void createContent() {
		super.createContent();
        visibleButton = addCheckButton("Visible axis title: ", "", this);
		titleField = addText(SWT.BORDER, "", this);			
	}

	protected void initContent() {
		super.initContent();
        visibleButton.setSelection(chart1.getAxes().getLeft().getTitle().getVisible());
        titleField.setText(chart1.getAxes().getLeft().getTitle().getCaption());
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        AxisTitle title = chart1.getAxes().getLeft().getTitle();
        title.setCaption("Temperature");
        title.getFont().setSize(15);
        title.getFont().setBold(true);
        title.getFont().getShadow().setColor(Color.WHITE);
        
        Points series = new Points(chart1.getChart());
        series.fillSampleValues(15);        
	} 

    private Button visibleButton;
    private Text titleField;	
}
