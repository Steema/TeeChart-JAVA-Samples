/*
 * DownSamplingDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.ZoomDirections;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.DownSampling;
import com.steema.teechart.functions.DownSamplingMethod;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.Points;
import com.steema.teechart.styles.TreatNullsStyle;

import features.ChartSample;

/**
 * @author tom
 * 
 */
public class DownSamplingDemo extends ChartSample implements ModifyListener,
		SelectionListener {

	private FastLine fastL;
	private DownSampling sFunction;

	public DownSamplingDemo(Composite c) {
		super(c);
		displaySpinner.addModifyListener(this);
		downSampleCombo.addSelectionListener(this);
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source == displaySpinner) {
			int dCount = ((Spinner) source).getSelection();
			if ((dCount > -1) && (dCount < 2001)) {
				if (source == displaySpinner) {
					sFunction.setDisplayedPointCount(dCount);
					chart1.getSeries(1).checkDataSource();
				}
			}
		}
	}

	public void widgetDefaultSelected(SelectionEvent se) {
	}

	public void widgetSelected(SelectionEvent se) {
		Widget source = se.widget;
		if (source == downSampleCombo) {
			DownSamplingMethod dMethod;

			switch (downSampleCombo.getSelectionIndex()) {
			case 0:
				dMethod = DownSamplingMethod.MAX;
				break;
			case 1:
				dMethod = DownSamplingMethod.MIN;
				break;
			case 2:
				dMethod = DownSamplingMethod.MINMAX;
				break;
			case 3:
				dMethod = DownSamplingMethod.MINMAXFIRSTLAST;
				break;
			case 4:
				dMethod = DownSamplingMethod.MINMAXFIRSTLASTNULL;
				break;
			case 5:
				dMethod = DownSamplingMethod.AVERAGE;
				break;
			default:
				dMethod = DownSamplingMethod.MAX;
				break;
			}

			sFunction.setMethod(dMethod);
			chart1.getSeries(1).clear();
			chart1.getSeries(1).checkDataSource();
			chart1.getSeries(1).invalidate();
		}
	}

	protected void createContent() {
		super.createContent();
		addLabel(SWT.LEFT, "Style: ");
		downSampleCombo = addCombo(SWT.READ_ONLY | SWT.SINGLE | SWT.BORDER);
		addLabel(SWT.LEFT, "Display count: ");
		displaySpinner = addSpinner(SWT.BORDER, 0, 2000, 100);
	}

	protected void initContent() {
		super.initContent();
		downSampleCombo.setItems(DownSampleMethodStrings);
		downSampleCombo.select(4);
		displaySpinner.setSelection(1000);
	}

	protected void initChart() {
		super.initChart();

		createArrays();
		chart1.getAspect().setView3D(false);
		chart1.getZoom().setDirection(ZoomDirections.HORIZONTAL);
		pointS = new Points(chart1.getChart());
		fastL = new FastLine(chart1.getChart());

		sFunction = new DownSampling(chart1.getChart());
		pointS.add(xValues, yValues);
		pointS.getPointer().setHorizSize(0);
		pointS.getPointer().setVertSize(0);
		pointS.setColor(Color.TRANSPARENT);
		sFunction.setDisplayedPointCount(1000);
		sFunction.setMethod(DownSamplingMethod.MINMAXFIRSTLASTNULL);
		fastL.setTreatNulls(TreatNullsStyle.DONOTPAINT);
		fastL.setDataSource(pointS);
		fastL.setFunction(sFunction);
		downSampleCombo.setVisibleItemCount(4);

	}

	private void createArrays() {
		int length = 2000;

		xValues = new double[length * 2];
		yValues = new double[length * 2];

		for (int i = 0; i < length; i++) {
			xValues[i] = i;
			if (i % 20 == 0) {
				yValues[i] = 0.0;
			} else {
				yValues[i] = Math.round(Math.random() * 100);
			}
		}

		for (int i = 0; i < length; i++) {
			xValues[i + length] = i;
			if (i % 20 == 0) {
				yValues[i + length] = 0.0;
			} else {
				yValues[i + length] = Math.round(Math.random() * 100);
			}
		}
	}

	private void unZoomed(com.steema.teechart.events.ChartEvent evt) {
		// series 0 is the original series, although you could use any value to
		// set the maximum
		chart1.getAxes().getBottom()
				.setMinMax(0, chart1.getSeries(0).getXValues().getMaximum());
		chart1.getSeries(1).checkDataSource();
	}

	private void onZoom(com.steema.teechart.events.ChartEvent evt) {
		chart1.getSeries(1).checkDataSource();
	}

	private Combo downSampleCombo;
	private Spinner displaySpinner;
	private static final String[] DownSampleMethodStrings = { "MAX", "MIN",
			"MINMAX", "MINMAXFIRSTLAST", "MINMAXFIRSTLASTNULL", "AVERAGE" };

	private Points pointS;
	private double[] xValues, yValues;
}
