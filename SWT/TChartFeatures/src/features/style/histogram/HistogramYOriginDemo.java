/*
 * HistogramDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.histogram;

import javax.swing.JLabel;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;

import com.steema.teechart.styles.Histogram;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class HistogramYOriginDemo extends ChartSample implements SelectionListener, ModifyListener{

    //TODO private ButtonPen lineButton, linesButton;
    private Button useOrigin;
    private JLabel yOrigin;
    private Spinner jValue;
    private Histogram series;
	public HistogramYOriginDemo(Composite c) {
		super(c);
	}
	
	protected void createContent() {
		super.createContent();
	}
    
    protected void initChart() {
    	super.initChart();
    	
    	  series = new Histogram(chart1.getChart());
          useOrigin = addCheckButton("Use YOrigin", "", this);
          useOrigin.setText("use YOrigin");
          yOrigin = new JLabel();
          yOrigin.setText("YOrigin: ");
          jValue = addSpinner(0, -10000, 10000, 1,this);
          useOrigin.setSelection(true);
          series.setUseYOrigin(true);
          series.setYOrigin(0);
          series.add(0, 10);
          series.add(1, 20);
          series.add(2, -30);
          series.add(3, -10);
          series.add(4, 10);
          series.add(5, 20);
          series.add(6, 30);
          series.add(7, 10);        
    }



	
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	
	public void widgetSelected(SelectionEvent arg0) {
		   Object aux = arg0.getSource();
	        if (aux == useOrigin) {
	            if (useOrigin.getSelection()) {
	                series.setUseYOrigin(true);
	                jValue.setEnabled(true);
	                series.setYOrigin(Integer.valueOf(jValue.getText()));
	            } else {
	                jValue.setEnabled(false);
	                series.setUseYOrigin(false);
	            }
	        }
	    
		
	}



	
	public void modifyText(ModifyEvent arg0) {
		Object aux = arg0.getSource();
		if (aux == jValue)
        {
          series.setYOrigin(Integer.valueOf(jValue.getText()));
        }
		
	}   			
}
