/*
 * PolarNullsDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.polar;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.ChartFont;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Polar;
import com.steema.teechart.styles.SeriesPointer;
import com.steema.teechart.styles.TreatNullsStyle;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class PolarNullsDemo extends ChartSample implements SelectionListener {

	private Polar series;
	   
	public PolarNullsDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source instanceof Button) {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == nullsButton) {
                series.setTreatNulls(isSelected ? TreatNullsStyle.DONOTPAINT : TreatNullsStyle.IGNORE);
            }
        };
	}	
	
	protected void createContent() {
		super.createContent();	

		nullsButton = addCheckButton("Hide null points", "", this);
	}

	protected void initContent() {
		super.initContent();
        nullsButton.setSelection(series.getTreatNulls() == TreatNullsStyle.DONOTPAINT);
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        
        series = new com.steema.teechart.styles.Polar(chart1.getChart());
        series.setCircleLabels(true);
        series.setCircled(true);

        ChartFont tmpFont = series.getCircleLabelsFont();
        tmpFont.setColor(Color.YELLOW);
        tmpFont.setSize(12);
        tmpFont.setItalic(true);

        series.getCirclePen().setColor(Color.BLUE);
        series.getCirclePen().setWidth(2);

        series.getPen().setColor(Color.RED);
        series.getPen().setWidth(2);
        series.getPen().setStyle(DashStyle.SOLID);

        series.getBrush().setColor(Color.WHITE);
        series.getBrush().setVisible(false);
        
        series.setTreatNulls(TreatNullsStyle.DONOTPAINT);

        SeriesPointer tmpPointer = series.getPointer();
        tmpPointer.getBrush().setColor(Color.LIME);
        tmpPointer.setHorizSize(2);
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.RECTANGLE);
        tmpPointer.setVertSize(2);
        tmpPointer.setVisible(true);

        FillSeries();    
	}  
	
    private void FillSeries() {
        series.add(0, 67);
        series.add(30, 45);
        series.add(60, 50);
        series.add(75, 90);
        series.add(90, 90, Color.TRANSPARENT); // <- Null point .. It lies between angles of 75 and 100
        series.add(100, 75);
        series.add(120, 45);
        series.add(150, 80);
        series.add(160, 80, Color.TRANSPARENT); // <- Null point .. It lies between angles of 150 and 180
        series.add(180, 70);
        series.add(210, 35);
        series.add(240, 33);
        series.add(270, 55);
        series.add(300, 65);
        series.add(330, 23);        
    }
    
	private Button nullsButton;
}
