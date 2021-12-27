/*
 * LabelsResolverDemo.java
 *
 * <p>Copyright: Copyright (c) 2006-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.axes;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.axis.AxisLabelResolver;
import com.steema.teechart.axis.NextAxisLabelValue;
import com.steema.teechart.events.ScrollModEventArgs;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.ISeries;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class LabelResolverDemo extends ChartSample implements SelectionListener {

	public LabelResolverDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source == editButton) {
            //TODO ChartEditor.editChart(chart1.getChart());			
		}
	}
	
	protected void createContent() {
		super.createContent();
        editButton = addPushButton("Edit chart...", "", this);				
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        
        Bar series = new Bar(chart1.getChart());
        series.fillSampleValues();		
        // Customizing axis labels
        chart1.setAxisLabelResolver(new AxisLabelResolver() {
            public String getLabel(Axis axis, ISeries s, int valueIndex, String labelText) {
                if (axis==chart1.getAxes().getBottom())
                    labelText+= " x";
                return labelText;
            }

            public NextAxisLabelValue getNextLabel(Axis axis, int labelIndex, NextAxisLabelValue nextLabel) {
                return nextLabel;
            }

			
			public void scrollModHandler(Axis a, ScrollModEventArgs e) {
				// TODO Auto-generated method stub
				
			}
        });
	}

    private Button editButton;    
}
