

package features.zoom;

import com.steema.teechart.styles.Line;
import features.ChartSample;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;


public class ZoomHistoryDemo extends ChartSample implements SelectionListener
{
 
	public ZoomHistoryDemo(Composite c) {
		super(c);
	}
	
   //private JButton button1;
   //private JLabel label1;
   //private GridBand gridBand1;

	protected void createContent() {
		super.createContent();
		CBHistory = addCheckButton("", "", this);
	        CBHistory.setText("Historical unzoom steps");
		}

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setVisible(false);
        Line series = new Line(chart1.getChart());
        series.fillSampleValues(50);        
	}   
       Button CBHistory;


	public void widgetDefaultSelected(SelectionEvent arg0) {}

	
	public void widgetSelected(SelectionEvent arg0) {
        if(CBHistory.getSelection())
        {
            
               chart1.getZoom().setHistory(true);
             
        }
        else
        {
            chart1.getZoom().setHistory(false);
           
            
        }    
		
	}




}


