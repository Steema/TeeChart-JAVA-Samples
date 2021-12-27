package features.style.line;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.drawing.ChartPen;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Line;

import features.ChartSample;

/**
*
* @author tom
*/
public class OutlineDemo extends ChartSample implements SelectionListener {

    //private ButtonPen editLineButton; TODO
    //private ButtonPen editOutlineButton; 	
    private Line lineSeries;
    
	public OutlineDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent arg0) { }

	public void widgetSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub		
	}
	
    protected void createContent() {
    	super.createContent();
    }
    
    protected void initContent() {
    	super.initContent();
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Line Series with outline");
        chart1.getLegend().setVisible(false);

        lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries.getMarks().setVisible(false);
        lineSeries.fillSampleValues(25);
        ChartPen tmpPen;
        tmpPen = lineSeries.getLinePen();
        tmpPen.setColor(Color.RED);
        tmpPen.setWidth(2);
        tmpPen = lineSeries.getOutLine();
        tmpPen.setColor(Color.YELLOW);
        tmpPen.setVisible(true);
    }     
}