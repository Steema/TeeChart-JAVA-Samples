/*
 * KnobGaugeDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.circulargauge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.styles.KnobGauge;

import features.ChartSample;

/**
 * 
 * @author tom
 */
public class KnobGaugeDemo extends ChartSample implements ActionListener, SelectionListener, DisposeListener {

	private KnobGauge knobGauge1;

	/** Creates a new instance of WaterfallDemo */
	public KnobGaugeDemo(Composite c) {
		super(c);
	}

	protected void initChart() {

		super.initChart();
		super.addDisposeListener(this);
		knobGauge1 = new KnobGauge(chart1.getChart());
		jCheckBox1 = addCheckButton("Animate", "", this);
		jCheckBox1.setSelection(true);
		t = new Timer(100, this);
		t.addActionListener(this);
		t.start();
	}

	private Button jCheckBox1;
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
						knobGauge1.setValue(value2);
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
	}

	
	public void widgetDisposed(DisposeEvent arg0) {
		jCheckBox1.setSelection(false);
		if (!jCheckBox1.getSelection()) {
			t.stop();
		}
	}
}
