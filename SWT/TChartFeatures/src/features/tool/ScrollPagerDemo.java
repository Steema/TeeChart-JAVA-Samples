/*
 * ScrollPagerDemo.java
 *
 * <p>Copyright: (c) 2005-2011 by Steema Software SL.
 All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;

import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.StringAlignment;
import com.steema.teechart.events.ChangeEvent;
import com.steema.teechart.events.ChangeListener;
import com.steema.teechart.misc.Utils;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.themes.ThemesList;
import com.steema.teechart.tools.Annotation;
import com.steema.teechart.tools.AnnotationPosition;
import com.steema.teechart.tools.NearestPoint;
import com.steema.teechart.tools.NearestPointDirection;
import com.steema.teechart.tools.ScrollPager;

import features.ChartSample;

/**
 * @author Steema Software 2011
 * 
 */
public class ScrollPagerDemo extends ChartSample implements ChangeListener {

	ScrollPager tool1;
	FastLine tmpSeries;
	Annotation annotate;
	NearestPoint point;

	public ScrollPagerDemo(Composite c) {
		super(c);
	}

	protected void initChart() {
		super.initChart();
		
		chart1.getChart().getAspect().setView3D(false);
		chart1.getChart().getLegend().setVisible(false);

		tmpSeries = new com.steema.teechart.styles.FastLine(chart1.getChart());
		tmpSeries.setColor(Color.GREEN_YELLOW);
		tmpSeries.fillSampleValues(1000);
		tmpSeries.getMarks().setVisible(false);

		chart1.setBounds(Utils.fromLTRB(0, 0, 570, 400));
		tool1 = new ScrollPager(chart1.getChart(), chart1.getParent(), 0);
		tool1.setSeries(tmpSeries);

		annotate = new Annotation(chart1.getChart());
		annotate.setPosition(AnnotationPosition.RIGHTTOP);
		annotate.setText("YValue:");
		annotate.getShape().getShadow().setVisible(false);
		annotate.getShape().getFont().setColor(chart1.getHeader().getColor());
		annotate.getShape().setColor(tool1.getPointerHighlightColor());
		annotate.getShape().getPen().setVisible(false);
		annotate.setTextAlign(StringAlignment.CENTER);

		point = new NearestPoint(chart1.getChart());
		point.getBrush().setVisible(true);
		point.getBrush().setColor(tool1.getPointerHighlightColor());
		point.setDrawLine(false);
		point.setSize(6);
		point.setDirection(NearestPointDirection.HORIZONTAL);
		point.setSeries(tmpSeries);
		point.addChangeListener(this);
		
		ThemesList.applyTheme(chart1.getChart(), 1);
		ThemesList.applyTheme(tool1.getSubChartTChart().getChart(), 1);
	}

	
	public void stateChanged(ChangeEvent e) {
		if (point.point > -1) {
			annotate.setText("YValue: " + tmpSeries.getYValues().getValue(point.point));
		}
	}
}
