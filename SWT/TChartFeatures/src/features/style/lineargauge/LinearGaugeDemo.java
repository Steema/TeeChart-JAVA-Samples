/*
 * CircularGaugeDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.lineargauge;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.styles.LinearGauge;

import features.ChartSample;

/**
 * 
 * @author tom
 */
public class LinearGaugeDemo extends ChartSample implements ActionListener,SelectionListener,DisposeListener {

	private LinearGauge series, series2;

	/** Creates a new instance of WaterfallDemo */
	public LinearGaugeDemo(Composite c) {
		super(c);
	}

	protected void initChart() {
		super.initChart();

		series = new LinearGauge(chart1.getChart());
		series2 = new LinearGauge(chart1.getChart());
		jCheckBox1 = addCheckButton("Animate", "", this);
		jCheckBox1.setSelection(true);
		t = new Timer(100, this);
		t.addActionListener(this);
		t.start();

	}

	@SuppressWarnings("static-access")
	public void actionPerformed(ActionEvent e) {

		try
		{
		super.getDisplay().getDefault().syncExec(new Runnable() {
			public void run() {
				try
				{
				Random rnd = new Random();
				double value2 = rnd.nextDouble() * 100;

				series.setValue(value2);
				while (value2 == series.getValue()) {
					value2 = rnd.nextDouble() * 100;
				}
				series2.setValue(value2);
	
			}
				catch(SWTException e2)
				{
					t.stop();
				}
			}
		});
		}
		catch(SWTException e2)
		{
			t.stop();
		}

	}

	private Button jCheckBox1;
	private Timer t;

	
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
			t.stop();
	}
}
