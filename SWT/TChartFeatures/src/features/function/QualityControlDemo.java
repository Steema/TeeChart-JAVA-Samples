/*
 * QualityControlDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.VerticalAxis;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class QualityControlDemo extends ChartSample implements ModifyListener {

	private Line goodSeries, badSeries;
	private FastLine upperSeries, lowerSeries;

	public QualityControlDemo(Composite c) {
		super(c);
		upperField.addModifyListener(this);        
		lowerField.addModifyListener(this);		
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		double tmpValue=-1;
		Series tmpSeries=null;
		try {
			tmpValue = Double.parseDouble(((Text)source).getText());
			if (source == upperField) {
					tmpSeries = upperSeries;
			} else if (source == lowerField) {
				tmpSeries = lowerSeries;
			}
			if (tmpSeries != null) {
				tmpSeries.clear();
				tmpSeries.add(0,tmpValue);
				tmpSeries.add(19,tmpValue);
			}	
		} catch (NumberFormatException e) {
		}        
	}	


	protected void createContent() {
		super.createContent();
		addLabel(SWT.LEFT, "Upper limit: ");
		upperField = addText(SWT.SINGLE | SWT.BORDER, "");
		addLabel(SWT.LEFT, "Lower limit: ");
		lowerField = addText(SWT.SINGLE | SWT.BORDER, "");
	}

	protected void initContent() {
		super.initContent();
		upperField.setText(Double.toString(upperLimit));
		lowerField.setText(Double.toString(lowerLimit));
	}

	protected void initChart() {
		super.initChart();
		chart1.setText("Quality Control Chart");
		chart1.getHeader().getFont().setSize(16);
		chart1.getHeader().getFont().setBold(true);
		chart1.getHeader().setVisible(true);

		/* format legend */
		Legend tmpLegend = chart1.getLegend();
		tmpLegend.setAlignment(LegendAlignment.BOTTOM);
		tmpLegend.setColor(Color.AQUA);
		tmpLegend.getSymbol().setWidth(37);

		/* format Axes */
		Axis tmpAxis;
		tmpAxis= chart1.getAxes().getBottom();
		tmpAxis.getGrid().setVisible(false);
		tmpAxis.getTitle().setCaption("Samples");
		tmpAxis = chart1.getAxes().getLeft();
		tmpAxis.getGrid().setStyle(DashStyle.SOLID);
		tmpAxis.getLabels().getFont().setColor(Color.RED);
		tmpAxis.getTitle().setCaption("Production (number of pieces)");
		tmpAxis = chart1.getAxes().getRight();
		tmpAxis.getLabels().setValueFormat("#,##0.###%");
		tmpAxis.getGrid().setVisible(false);
		tmpAxis.getLabels().getFont().setColor(Color.GREEN);
		tmpAxis.getLabels().getFont().setBold(true);
		tmpAxis.getTitle().setCaption("SPC %");

		chart1.getZoom().setAnimated(true);
		chart1.getAspect().setChart3DPercent(10);

		initSeries();        
	}   	

	protected void initSeries() {
		Random generator = new Random();

		goodSeries = new com.steema.teechart.styles.Line(chart1.getChart());
		goodSeries.getMarks().setVisible(false);
		goodSeries.setColor(Color.RED);
		goodSeries.getPointer().setInflateMargins(true);
		goodSeries.getPointer().setStyle(PointerStyle.RECTANGLE);
		goodSeries.getPointer().setVisible(true);

		badSeries = new com.steema.teechart.styles.Line(chart1.getChart());
		badSeries.setTitle("Bad % on Good");
		badSeries.setVerticalAxis(VerticalAxis.RIGHT);
		badSeries.getMarks().setVisible(false);
		badSeries.setColor(Color.GREEN);
		badSeries.getPointer().setInflateMargins(true);
		badSeries.getPointer().setStyle(PointerStyle.RECTANGLE);
		badSeries.getPointer().setVisible(true);

		upperSeries = new com.steema.teechart.styles.FastLine(chart1.getChart());
		upperSeries.getMarks().setVisible(false);
		upperSeries.setColor(Color.YELLOW);
		upperSeries.setTitle("Upper %");
		upperSeries.setVerticalAxis(VerticalAxis.RIGHT);
		upperSeries.getLinePen().setColor(Color.YELLOW);
		upperSeries.getLinePen().setWidth(3);

		lowerSeries = new com.steema.teechart.styles.FastLine(chart1.getChart());
		lowerSeries.getMarks().setVisible(false);
		lowerSeries.setColor(Color.BLUE);
		lowerSeries.setTitle("Lower %");
		lowerSeries.setVerticalAxis(VerticalAxis.RIGHT);
		lowerSeries.getLinePen().setColor(Color.BLUE);
		lowerSeries.getLinePen().setWidth(3);

		for (int t=0; t<20; t++) {
			goodSeries.add(800+generator.nextInt(200),"",Color.EMPTY);
		}
		for (int t=0; t<20; t++) {
			badSeries.add(4+generator.nextInt(4),"",Color.EMPTY);
		}

		calculateSPC(goodSeries, badSeries);
	}

	private void calculateSPC(Series good, Series bad) {
		upperLimit = 0.0;
		lowerLimit = 0.0;

		double numTotal = 0.0;
		double percent = 0.0;
		double sum = 0.0;
		double sumN = 0.0;
		int n = 0;

		for (int t = 0; t < goodSeries.getCount(); t++) {
			percent = badSeries.getYValues().getValue(t)*goodSeries.getYValues().getValue(t) / 100.0;
			numTotal = goodSeries.getYValues().getValue(t)+percent;
			if (numTotal > 0) {
				sum = sum + percent/numTotal;
				sumN = sumN + numTotal;
				n++;
			}
		}

		double lcp = sum / n;
		double lcn = sumN / n;

		double tmp = (lcp * (1-lcp)) / lcn;
		if (tmp>0) {
			double aux = 3 * Math.sqrt(tmp); // <-- 3 by square root
			upperLimit =100.0*(lcp+aux);
			lowerLimit =100.0*(lcp-aux);
		}
	}

	private double upperLimit, lowerLimit;
	private Text  upperField, lowerField;	
}
