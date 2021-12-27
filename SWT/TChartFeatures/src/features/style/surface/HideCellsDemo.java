/*
 * HideCellsDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.surface;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.styles.Surface;
import com.steema.teechart.tools.Rotate;

import features.ChartSample;

/**
 * @author yeray
 * 
 */
public class HideCellsDemo extends ChartSample implements SelectionListener {

	private Button checkBox1;
	private Surface series;
	private Rotate rotate;

	public HideCellsDemo(Composite c) {
		super(c);
	}

	protected void initContent() {
		super.initContent();

		checkBox1 = this.addCheckButton("Hide Cells", "", this);
		checkBox1.setSelection(true);
	}

	protected void initChart() {
		super.initChart();

		series = new Surface(chart1.getChart());
		series.fillSampleValues(10);
		chart1.getAspect().setChart3DPercent(60);
		chart1.getAspect().setElevation(349);
		chart1.getAspect().setOrthogonal(false);
		chart1.getAspect().setPerspective(123);
		chart1.getAspect().setRotation(350);
		chart1.getAspect().setZoom(80);
		chart1.getHeader().setText("");
		series.setHideCells(true);

		chart1.getTools().add(rotate = new Rotate());
		rotate.setChart(chart1.getChart());
	}

	
	public void widgetDefaultSelected(SelectionEvent arg0) {

	}

	
	public void widgetSelected(SelectionEvent arg0) {
		Object aux = arg0.getSource();
		if (aux == checkBox1) {
			series.setHideCells(checkBox1.getSelection());
		}
	}

}
