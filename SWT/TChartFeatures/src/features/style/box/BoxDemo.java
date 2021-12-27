
package features.style.box;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.Chart;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.styles.Box;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.SeriesPointer;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class BoxDemo extends ChartSample implements SelectionListener {

	private Box series1, series2, series3;

	public BoxDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
		boolean isSelected = ((Button)source).getSelection();
		if (source == verticalButton) {
			try {
				Class tmp;
				
				if (isSelected) {
					tmp = Class.forName( "com.steema.teechart.styles.Box" );
				} else {
					tmp = Class.forName( "com.steema.teechart.styles.HorizBox" );
				}
				
				Chart.changeAllSeriesType(chart1.getChart(),tmp);
				
			} catch (ClassNotFoundException ex) {
				Logger.getLogger(BoxDemo.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IllegalArgumentException ex) {
				Logger.getLogger(BoxDemo.class.getName()).log(Level.SEVERE, null, ex);
			} catch (NoSuchMethodException ex) {
				Logger.getLogger(BoxDemo.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InvocationTargetException ex) {
				Logger.getLogger(BoxDemo.class.getName()).log(Level.SEVERE, null, ex);
			}
		}      	

	}	

	protected void createContent() {
		super.createContent();
		verticalButton = addCheckButton("Vertical", "", this);  	
	}

	protected void initContent() {
		super.initContent();    	   	
		verticalButton.setSelection(true); 
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
		series1 = new com.steema.teechart.styles.Box(chart1.getChart());
		series1.add(new int[] {3,6,8,15,19,21});
		series2 = new com.steema.teechart.styles.Box(chart1.getChart());
		series2.add(new int[] {3,6,8,15,19,21});
		series3 = new com.steema.teechart.styles.Box(chart1.getChart());
		series3.add(new int[] {3,6,8,15,19,21});

		initSeries();
	}   			

    private void initSeries() {
        SeriesPointer tmpPointer;

        tmpPointer = series1.getPointer();
        tmpPointer.getBrush().setColor(Color.YELLOW);
        tmpPointer.setDraw3D(false);
        tmpPointer.setHorizSize(15);
        tmpPointer.setVertSize(15);
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.RECTANGLE);
        tmpPointer.setVisible(true);
        tmpPointer = series1.getExtrOut();
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.STAR);
        tmpPointer.setVisible(true);
        tmpPointer = series1.getMildOut();
        tmpPointer.setHorizSize(6);
        tmpPointer.setVertSize(6);
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.RECTANGLE);
        tmpPointer.setVisible(true);
        series1.getMedianPen().setStyle(DashStyle.DASH);
        series1.setWhiskerLength(1.5);
        series1.getWhiskerPen().setColor(Color.BLUE);
        series1.getSampleValues().setDateTime(false);


        tmpPointer = series2.getPointer();
        tmpPointer.getBrush().setColor(Color.RED);
        tmpPointer.setDraw3D(false);
        tmpPointer.setHorizSize(15);
        tmpPointer.setVertSize(15);
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.RECTANGLE);
        tmpPointer.setVisible(true);
        tmpPointer = series2.getExtrOut();
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.STAR);
        tmpPointer.setVisible(true);
        tmpPointer = series2.getMildOut();
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.CIRCLE);
        tmpPointer.setVisible(true);
        series2.getMedianPen().setStyle(DashStyle.DASH);
        series2.setWhiskerLength(1.5);
        series2.getWhiskerPen().setColor(Color.BLUE);
        series2.setPosition(1);

        tmpPointer = series3.getPointer();
        tmpPointer.getBrush().setColor(Color.WHITE);
        tmpPointer.setDraw3D(false);
        tmpPointer.setHorizSize(15);
        tmpPointer.setVertSize(15);
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.RECTANGLE);
        tmpPointer.setVisible(true);
        tmpPointer = series3.getExtrOut();
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.STAR);
        tmpPointer.setVisible(true);
        tmpPointer = series3.getMildOut();
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.CIRCLE);
        tmpPointer.setVisible(true);
        series3.getMedianPen().setStyle(DashStyle.DASH);
        series3.setWhiskerLength(1.5);
        series3.getWhiskerPen().setColor(Color.BLUE);
        series3.setPosition(2);
    }
    
    private Button verticalButton;
}
