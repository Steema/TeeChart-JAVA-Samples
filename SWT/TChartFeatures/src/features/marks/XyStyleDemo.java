/*
 * XyStyleDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.marks;

import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.legend.LegendTextStyle;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.Points;
import com.steema.teechart.styles.Series.MarkTextResolver;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class XyStyleDemo extends ChartSample {

    private Points series;
    
	public XyStyleDemo(Composite c) {
		super(c);
		
        // Customizing Series marks...
        series.setMarkTextResolver( new MarkTextResolver() {
            public String getMarkText(int valueIndex, String markText) {
                int i=markText.indexOf(' ');
                StringBuffer buffer = new StringBuffer(markText.length());
                buffer.append(markText);
                buffer.replace(i,i+1,"-");
                return buffer.toString();
            }
        });		
	}
	
	protected void createContent() {
		super.createContent();				
	}

	protected void initContent() {
		super.initContent();
        getButtonPane().setVisible(false);
	}

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setTextStyle(LegendTextStyle.XANDVALUE);
        chart1.getAspect().setChart3DPercent(15);
        chart1.getAspect().setElevation(315);
        chart1.getAspect().setPerspective(0);
        chart1.getAspect().setRotation(360);
        
        series = new Points(chart1.getChart());
        series.getMarks().setStyle(MarksStyle.XY);
        series.getMarks().setArrowLength(6);
        series.getMarks().setVisible(true);
        series.getPointer().setVisible(true);
        series.fillSampleValues(6);
	}   				

}
