/*
 * TextStyleDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.legend.LegendTextStyle;
import com.steema.teechart.styles.Pie;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class TextStyleDemo extends ChartSample implements SelectionListener {

	public TextStyleDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == styleList) {
            switch (styleList.getSelectionIndex()) {
                case 0: chart1.getLegend().setTextStyle(LegendTextStyle.PLAIN); break;
                case 1: chart1.getLegend().setTextStyle(LegendTextStyle.LEFTVALUE); break;
                case 2: chart1.getLegend().setTextStyle(LegendTextStyle.RIGHTVALUE); break;
                case 3: chart1.getLegend().setTextStyle(LegendTextStyle.LEFTPERCENT); break;
                case 4: chart1.getLegend().setTextStyle(LegendTextStyle.RIGHTPERCENT); break;
                case 5: chart1.getLegend().setTextStyle(LegendTextStyle.XVALUE); break;
                case 6: chart1.getLegend().setTextStyle(LegendTextStyle.VALUE); break;
                case 7: chart1.getLegend().setTextStyle(LegendTextStyle.PERCENT); break;
                case 8: chart1.getLegend().setTextStyle(LegendTextStyle.XANDVALUE); break;
                case 9: chart1.getLegend().setTextStyle(LegendTextStyle.XANDPERCENT);
            }
        }
	}
	
	protected void createContent() {
		super.createContent();	
		addLabel(SWT.LEFT, "Legend styles: ");
        styleList = addCombo(SWT.READ_ONLY | SWT.BORDER | SWT.SINGLE, this);
	}

	protected void initContent() {
		super.initContent();
        styleList.setItems(new String[]{
                    "Plain",
                    "Left Value",
                    "Right Value",
                    "Left Percent",
                    "Right Percent",
                    "X Value",
                    "Value",
                    "Percent",
                    "X and Value",
                    "X and Percent"
                });
       styleList.select(chart1.getLegend().getTextStyle().getValue());
	}

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setTextStyle(LegendTextStyle.PERCENT);
        
        new Pie(chart1.getChart()).fillSampleValues(5);        
	}   	

    private Combo styleList;	
}
