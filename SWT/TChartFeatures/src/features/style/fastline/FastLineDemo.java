/*
 * FastLineDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.fastline;

import java.util.Random;

import javax.swing.JOptionPane;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.HorizontalAxis;
import com.steema.teechart.styles.VerticalAxis;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class FastLineDemo extends ChartSample implements SelectionListener {

	private FastLine lineSeries1, lineSeries2;	

	public FastLineDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			if (source == speedButton) {
				chart1.getZoom().setAnimated(false);

				long startTime = System.currentTimeMillis();

				chart1.getZoom().zoomPercent(95);
				for (int t=0; t < 30; t++) {
					chart1.getZoom().zoomPercent(95); // 5% zoom in
					//TODO chart1.paintImmediately(chart1.getBounds());
				}

				for (int t=0; t < 30; t++) {
					chart1.getZoom().zoomPercent(105); // 5% zoom out
					//TODO chart1.paintImmediately(chart1.getBounds());
				}

				long endTime = System.currentTimeMillis();
				chart1.getZoom().setAnimated(true);
				chart1.getZoom().undo();

				JOptionPane.showMessageDialog(null, "Time to plot 2000 points\n61 times:\n"+String.valueOf(endTime-startTime)+" milliseconds." );
			} else {
				boolean isSelected = ((Button)source).getSelection();	        	
				if (source == optionButtons[0]) {
					//@TODO myChart.setBufferedDisplay(isSelected);
				} else if (source == optionButtons[1]) {
					chart1.getAxes().setVisible(isSelected);
				} else if (source == optionButtons[2]) {
					//TODO chart1.setClipPoints(isSelected);
				}
			}
		}
	}

	protected void createContent() {
		super.createContent();
		optionButtons  = new Button[3];
		optionButtons[0] = addCheckButton("Buffered", "", this);
		optionButtons[1] = addCheckButton("Draw Axes", "", this);
		optionButtons[2] = addCheckButton("Clip Points", "", this);
		speedButton = addPushButton("Test Speed", "", this);		
	}

	protected void initContent() {
		super.initContent();  
		for (int i=0; i < optionButtons.length; i++) {
			optionButtons[i].setSelection(true);
		}			
	}

	protected void initSeries() {
		Random generator = new Random();
		int tmpRandom;

		lineSeries1 = new com.steema.teechart.styles.FastLine(chart1.getChart());
		lineSeries2 = new com.steema.teechart.styles.FastLine(chart1.getChart());
		for (int t=0; t < 1000; t++) {
			if ( t != 500) {
				tmpRandom = generator.nextInt(Math.abs(500-t))-(Math.abs(500-t) / 2);
				lineSeries1.add(1000-t+tmpRandom);
				lineSeries2.add(t+tmpRandom);
			}
		}

		lineSeries1.getLinePen().setStyle(DashStyle.DOT);
		lineSeries1.setVerticalAxis(VerticalAxis.RIGHT);
		lineSeries1.setHorizontalAxis(HorizontalAxis.TOP);        
	}	

	protected void initChart() {
		super.initChart();
		chart1.getAxes().setVisible(true);
		//TODO chart1.setClipPoints(true);

		// hide things for better speed
		chart1.getAspect().setView3D(false);
		chart1.getLegend().setVisible(false);
		chart1.getFooter().setVisible(false);
		chart1.getHeader().setVisible(false);

		chart1.getZoom().setAnimated(true);
		chart1.getZoom().setAnimatedSteps(15);
		
		initSeries();

		Axis tmpAxis;
		tmpAxis = chart1.getAxes().getBottom();
		tmpAxis.getGrid().setColor(Color.BLUE);
		tmpAxis.getLabels().getFont().setColor(Color.FUCHSIA);
		tmpAxis.getTicks().setColor(Color.LIME);
		tmpAxis = chart1.getAxes().getLeft();
		tmpAxis.getGrid().setColor(Color.BLUE);
		tmpAxis.getLabels().getFont().setColor(Color.NAVY);
		tmpAxis.getTicks().setColor(Color.RED);
		tmpAxis = chart1.getAxes().getRight();
		tmpAxis.getGrid().setVisible(false);
		tmpAxis.getLabels().getFont().setColor(Color.RED);
		tmpAxis.getTicks().setColor(Color.BLUE);
		tmpAxis = chart1.getAxes().getTop();
		tmpAxis.getGrid().setVisible(false);
		tmpAxis.getLabels().getFont().setColor(Color.GREEN);
		tmpAxis.getTicks().setColor(Color.YELLOW);
	}   	

	private Button speedButton;
	private Button[] optionButtons;	
}
