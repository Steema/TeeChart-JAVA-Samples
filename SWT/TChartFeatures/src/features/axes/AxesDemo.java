/*
 * AxesDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.axes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axes;
import com.steema.teechart.styles.HorizontalAxis;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.VerticalAxis;

import features.ChartSample;
import features.WidgetFactory;
import features.utils.EnumStrings;

/**
 * @author tom
 *
 */
public class AxesDemo extends ChartSample implements SelectionListener {

    private Line series;
    
	public AxesDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
        if (source == vertAxisList) {
            switch (vertAxisList.getSelectionIndex()) {
                case 0: series.setVerticalAxis(VerticalAxis.LEFT) ; break;
                case 1: series.setVerticalAxis(VerticalAxis.RIGHT); break;
                default: series.setVerticalAxis(VerticalAxis.BOTH);
            }
        } else if (source == horizAxisList) {
            switch (horizAxisList.getSelectionIndex()) {
                case 0: series.setHorizontalAxis(HorizontalAxis.TOP) ; break;
                case 1: series.setHorizontalAxis(HorizontalAxis.BOTTOM); break;
                default: series.setHorizontalAxis(HorizontalAxis.BOTH);
            }
        } else if (source == editButton) {
            //TODO DialogFactory.showModal(chart1.getAxes());
        } else if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == showButton) {
	            chart1.getAxes().setVisible(isSelected);
	        } else if (source == gridLinesButton) {
	            for (int t=0; t < chart1.getAxes().getCount(); t++) {
	                chart1.getAxes().getAxis(t).getGrid().setVisible(isSelected);
	            }
	            behindButton.setEnabled(isSelected);
	        } else if (source == depthButton) {
	            chart1.getAxes().getDepth().setVisible(isSelected);
	            if (isSelected) {
	                chart1.getPanel().setMarginRight(15);
	            } else {
	                chart1.getPanel().setMarginRight(3);
	            }
	        } else if (source == view3DButton) {
	            chart1.getAspect().setView3D(isSelected);
	        } else if (source == behindButton) {
	            chart1.getAxes().setDrawBehind(isSelected);
	        }			
		}
	}
	
	protected void createContent() {
		super.createContent();
		
		getButtonPane().setLayout(new FillLayout(SWT.VERTICAL));
		Composite topComposite = new Composite(getButtonPane(), SWT.NULL);
		topComposite.setLayout(new RowLayout());
		WidgetFactory.createLabel(topComposite, SWT.LEFT, "Horiz: ");
		horizAxisList = WidgetFactory.createCombo(topComposite, SWT.READ_ONLY|SWT.BORDER|SWT.SINGLE, this);
		WidgetFactory.createLabel(topComposite, SWT.LEFT, "Vert: ");
		vertAxisList = WidgetFactory.createCombo(topComposite, SWT.READ_ONLY|SWT.BORDER|SWT.SINGLE, this);
		editButton = WidgetFactory.createPushButton(topComposite, "Edit...", "", this);
		Composite bottomComposite = new Composite(getButtonPane(), SWT.NULL);
		bottomComposite.setLayout(new RowLayout());
		showButton = WidgetFactory.createCheckButton(bottomComposite, "Show Axes", "", this);
		gridLinesButton = WidgetFactory.createCheckButton(bottomComposite, "Grid lines", "", this);
		depthButton = WidgetFactory.createCheckButton(bottomComposite, "Show depth axis", "", this);
		view3DButton = WidgetFactory.createCheckButton(bottomComposite, "3D", "", this);
		behindButton = WidgetFactory.createCheckButton(bottomComposite, "Behind", "", this);
	}

	protected void initContent() {
		super.initContent();
		Axes tmpAxes = chart1.getAxes();
        showButton.setSelection(tmpAxes.getVisible());
        gridLinesButton.setSelection(tmpAxes.getLeft().getGrid().getVisible());
        depthButton.setSelection(tmpAxes.getDepth().getVisible());
        view3DButton.setSelection(chart1.getAspect().getView3D());
        behindButton.setSelection(tmpAxes.getDrawBehind());
        horizAxisList.setItems(EnumStrings.VERT_ALIGNMENT);
        horizAxisList.select(1);
        vertAxisList.setItems(EnumStrings.HORIZ_ALIGNMENT);
        vertAxisList.select(0);
        editButton.setEnabled(false);
	}

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setVisible(false);
        series = new Line(chart1.getChart());
        series.fillSampleValues(10);        
	}   				
    private Button showButton, gridLinesButton, depthButton, view3DButton, behindButton;
    private Combo horizAxisList, vertAxisList;
    private Button editButton;
}
