/*
 * Resolver.java
 *
 * <p>Copyright: Copyright (c) 2006-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.Rectangle;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendItemCoordinates;
import com.steema.teechart.legend.LegendResolver;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.styles.Bar;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class ResolverDemo extends ChartSample implements SelectionListener {

	public ResolverDemo(Composite c) {
		super(c);
		
        // Customizing the Legend
        chart1.setLegendResolver(new LegendResolver() {

            public Rectangle getBounds(Legend legend, Rectangle rectangle) {
                return rectangle;
            }

            public LegendItemCoordinates getItemCoordinates(Legend legend, LegendItemCoordinates coordinates) {
                return coordinates;
            }

            public String getItemText(Legend legend, LegendStyle legendStyle, int index, String text) {
                if (index==0)
                    text += " Custom";

                return text;
            }
        });		
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == editButton) {
            //TODO TChartEditor.editChart(chart1.getChart());
        }
	}
	
	protected void createContent() {
		super.createContent();	

        editButton = addPushButton("Edit Chart...", "", this);
	}

	protected void initContent() {
		super.initContent();
		editButton.setEnabled(false);
	}

	protected void initChart() {
		super.initChart();
        Bar series = new Bar(chart1.getChart());

        chart1.getAspect().setView3D(false);
        series.fillSampleValues();      
	}
	
    private Button editButton;   	
}
