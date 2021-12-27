/*
 * PointFigure.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.pointfigure;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.PointFigure;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.SeriesPointer;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class PointFigureDemo extends ChartSample implements SelectionListener {

    private PointFigure series1;
    
	public PointFigureDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == editButton) {
            //TODO ChartEditor.editSeries(series1);
        }
	}
	
	protected void createContent() {
		super.createContent();
		editButton = addPushButton("Edit", "Edit Series", this);
	}

	protected void initContent() {
		super.initContent();
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setText("Point & Figure");
        chart1.getHeader().setVisible(true);
        
        series1 = new com.steema.teechart.styles.PointFigure(chart1.getChart());
        series1.fillSampleValues();
        series1.setBoxSize(5);
        series1.getMarks().setVisible(false);

        SeriesPointer tmpPointer;
        tmpPointer = series1.getDownSymbol();
        tmpPointer.getBrush().setColor(Color.RED);
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.CIRCLE);
        tmpPointer.setVisible(true);
        tmpPointer = series1.getUpSymbol();
        tmpPointer.getBrush().setColor(Color.GREEN);
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.DIAGCROSS);
        tmpPointer.setVisible(true);
   
	}   	

    private Button editButton;	
}
