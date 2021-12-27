/*
 * CircularGaugeDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.circulargauge;


import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.CircularGauge;
import com.steema.teechart.styles.ValueListOrder;

import features.ChartSample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.Timer;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

/**
 * 
 * @author tom
 */
public class CircularGaugeDemo extends ChartSample implements ActionListener,
		SelectionListener, ModifyListener, DisposeListener {

	private CircularGauge series;

	/** Creates a new instance of WaterfallDemo */
	public CircularGaugeDemo(Composite c) {
		super(c);
	}

	protected void initChart() {

		super.initChart();
		super.addDisposeListener(this);
		series = new CircularGauge(chart1.getChart());
		series.getFrame().getOuterBand()
				.setColor(Color.fromArgb(255, 255, 255));
		series.getFrame().getMiddleBand().getGradient().setVisible(true);
		series.getFrame().getMiddleBand().getGradient()
				.setStartColor(Color.fromArgb(80, 80, 80));
		series.getFrame().getMiddleBand().getGradient()
				.setEndColor(Color.WHITE);
		series.getFrame().getInnerBand()
				.setColor(Color.fromArgb(213, 213, 213));
		series.getFaceBrush().setForegroundColor(Color.GREEN);
		// series.fillSampleValues();
		series.setCircled(true);
		series.setColorEach(false);
		series.setMinimum(0);
		series.setMaximum(100);
		series.setGreenLineStartValue(0);
		series.setGreenLineEndValue(70);
		series.setRedLineStartValue(80);
		series.setRedLineEndValue(100);
		series.setShowInLegend(false);
		series.setTotalAngle(300);
		series.getXValues().setDataMember("Angle");
		series.getXValues().setOrder(ValueListOrder.ASCENDING);
		series.getYValues().setDataMember("Y");
		jCheckBox1 = addCheckButton("Animate", "", this);
		editButton = addPushButton("Edit Circular Gauge", "",this);
		jLabel4 = addLabel(0, "Frame  Width %: ");
		jLabel4.setText("Frame  Width %: ");
		jWidth = addSpinner(30, 0, 100, 1, this);
		jCheckBox1.setSelection(true);
		jWidth.setSelection(series.getFrame().getWidth());

		t = new Timer(100, this);
		t.addActionListener(this);
		t.start();

	}


	private Button jCheckBox1,editButton;

	private Label jLabel4;

	private Spinner jWidth;
	private Timer t;
	private double value2 = 0.0;
	private boolean up = true;

	@SuppressWarnings("static-access")
	
	public void actionPerformed(ActionEvent e) {
		try {
			super.getDisplay().getDefault().syncExec(new Runnable() {
				public void run() {
					try {

						if (up) {

							value2 += 1;
						} else {

							value2 -= 1;
						}
						if (value2 > 99) {

							up = false;
						} else if (value2 < 1) {

							up = true;
						}

						series.setValue(value2);
					} catch (SWTException e2) {
						t.stop();
					}

				}
			});
		} catch (SWTException e2) {
			t.stop();
		}
	}

	
	public void widgetDefaultSelected(SelectionEvent arg0) {
		Object aux = arg0.getSource();
		if (aux == jCheckBox1) {
			if (jCheckBox1.getSelection()) {
				t.start();
			} else {
				t.stop();

			}
				
		}
		else if(aux == editButton)
		{
			
		}

	}

	
	public void widgetSelected(SelectionEvent arg0) {
		Object aux = arg0.getSource();
		if (aux == jCheckBox1) {
			if (jCheckBox1.getSelection()) {
				t.start();

			} else {
				t.stop();

			}

		}
		else if(aux == editButton)
		{
			
		}

	}

	
	public void modifyText(ModifyEvent arg0) {
		Object aux = arg0.getSource();
		if (aux == jWidth) {

			series.getFrame().setWidth(jWidth.getSelection());
			series.setColor(Color.gold);

		}

	}

	
	public void widgetDisposed(DisposeEvent arg0) {
		jCheckBox1.setSelection(false);
		if (!jCheckBox1.getSelection()) {

			t.stop();

		}
	}

}
