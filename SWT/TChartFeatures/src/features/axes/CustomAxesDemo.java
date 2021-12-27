/*
 * CustomAxesDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.axes;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Line;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class CustomAxesDemo extends ChartSample implements SelectionListener {

	public CustomAxesDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == editButton) {
            if (chart1.getAxes().getCustom().size()==0) {
                //TODO DialogFactory.showModal(chart1.getAxes().getLeft());
            } else {
                //TODO DialogFactory.showModal(chart1.getAxes().getCustom().getAxis(0));
            }
        } else if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == showButton) {
	            for (int t=0; t < chart1.getAxes().getCustom().size(); t++) {
	                chart1.getAxes().getCustom().getAxis(t).setVisible(isSelected);
	            }
	        }		
		}
	}
	
	protected void createContent() {
		super.createContent();
		showButton = addCheckButton("Show custom axes", "", this);
		editButton = addPushButton("Edit", "", this);				
	}

	protected void initContent() {
		super.initContent();
		showButton.setSelection(true);
        editButton.setEnabled(false);
	}

    protected void initAxes() {
        Axis tmpAxis;
        tmpAxis = chart1.getAxes().getCustom().getNew();
        tmpAxis.setHorizontal(false);
        tmpAxis.setOtherSide(false);
        tmpAxis.getLabels().setColor(Color.GREEN);
        tmpAxis.setStartPosition(50.0);

        tmpAxis = chart1.getAxes().getCustom().getNew();
        tmpAxis.setHorizontal(true);
        tmpAxis.setOtherSide(false);
        tmpAxis.getLabels().setColor(Color.RED);
        tmpAxis.setRelativePosition(50.0);

        tmpAxis = chart1.getAxes().getCustom().getNew();
        tmpAxis.getGrid().setVisible(false);
        tmpAxis.setHorizontal(true);
        tmpAxis.setOtherSide(false);
        tmpAxis.getLabels().setColor(Color.YELLOW);
        tmpAxis.setStartPosition(55.0);

        tmpAxis = chart1.getAxes().getCustom().getNew();
        tmpAxis.getGrid().setVisible(false);
        tmpAxis.setHorizontal(false);
        tmpAxis.setOtherSide(true);
        tmpAxis.getLabels().setColor(Color.YELLOW);
        tmpAxis.setStartPosition(50.0);
     }
    
	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        Axis tmpAxis;
        tmpAxis = chart1.getAxes().getBottom();
        tmpAxis.getGrid().setVisible(false);
        tmpAxis.getLabels().getFont().setColor(Color.GREEN);
        tmpAxis.setEndPosition(50.0);
        tmpAxis = chart1.getAxes().getLeft();
        tmpAxis.getLabels().getFont().setColor(Color.RED);
        tmpAxis.setEndPosition(50.0);
        initAxes();
        Line tmpSeries;
        for (int t=0; t<3; t++) {
            tmpSeries = new Line(chart1.getChart());
            tmpSeries.fillSampleValues(20);
            switch (t) {
                case 0: tmpSeries.setCustomHorizAxis(1); break;
                case 1: tmpSeries.setCustomVertAxis(0); break;
                case 2: {
                    tmpSeries.setCustomHorizAxis(2);
                    tmpSeries.setCustomVertAxis(3);
                    break;
                }
            }
        }        
	}   				

    private Button editButton;
    private Button showButton;	
}
