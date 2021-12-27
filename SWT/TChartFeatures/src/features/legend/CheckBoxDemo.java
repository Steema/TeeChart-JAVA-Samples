/*
 * CheckBoxDemo.java
 *
 * <p>Copyright: (c) 2004-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Bubble;
import com.steema.teechart.styles.ErrorBar;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.HighLow;
import com.steema.teechart.styles.Histogram;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Points;
import com.steema.teechart.styles.Volume;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class CheckBoxDemo extends ChartSample implements SelectionListener {

	public CheckBoxDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == showCheckBoxButton) {
	            chart1.getLegend().setCheckBoxes(isSelected);
	        }
		}
	}
	
	protected void createContent() {
		super.createContent();	
		showCheckBoxButton = addCheckButton("Legend Checkboxes", "", this);
	}

	protected void initContent() {
		super.initContent();
		showCheckBoxButton.setSelection(chart1.getLegend().getCheckBoxes());
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setChart3DPercent(10);
        chart1.getAspect().setElevation(352);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(55);
        chart1.getAspect().setRotation(342);
        chart1.getLegend().setCheckBoxes(true);
        
        new Histogram(chart1.getChart());
        new Bar(chart1.getChart());
        new Area(chart1.getChart());
        new Points(chart1.getChart());
        new FastLine(chart1.getChart());
        new Bubble(chart1.getChart());
        new Volume(chart1.getChart());
        new Line(chart1.getChart());
        new ErrorBar(chart1.getChart());
        new HighLow(chart1.getChart());

        chart1.getSeries().fillSampleValues();        
	}
    private Button showCheckBoxButton;	
}
