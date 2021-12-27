/*
 * SubHeaderFooterDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.headfoot;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.Footer;
import com.steema.teechart.Header;
import com.steema.teechart.drawing.Color;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class SubHeaderFooterDemo extends ChartSample implements SelectionListener {

	public SubHeaderFooterDemo(Composite c) {
		super(c);		
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source instanceof Button) {
			boolean isSelected = ((Button)source).getSelection();
	        if (source == showSubHeaderButton) {
	            chart1.getSubHeader().setVisible(isSelected);
	        } else if (source == showSubFooterButton) {
	            chart1.getSubFooter().setVisible(isSelected);
	        }
		}
	}
	
	protected void createContent() {
		super.createContent();	

		showSubHeaderButton = addCheckButton("Show subheader", "", this);
		showSubFooterButton = addCheckButton("Show subfooter", "", this);
	}

	protected void initContent() {
		super.initContent();
        showSubHeaderButton.setSelection(chart1.getSubHeader().getVisible());
        showSubFooterButton.setSelection(chart1.getSubFooter().getVisible());
	}

	protected void initChart() {
		super.initChart();
        Header header;
        Footer footer;

        header = chart1.getSubHeader();
        header.setVisible(true);
        header.setText("Hello");
        header.getFont().setColor(Color.GREEN);
        header.getFont().setSize(12);
        header.getFont().setBold(true);
        header.getGradient().setVisible(true);
        header.setTransparent(false);

        footer = chart1.getSubFooter();
        footer.setVisible(true);
        footer.setText("World!");

        header = chart1.getHeader();
        header.setVisible(true);
        header.setText("TChart");

        footer = chart1.getFooter();
        footer.setVisible(true);
        footer.setText("This is the Chart Foot");
        footer.getFont().setColor(Color.YELLOW);
        footer.getFont().setSize(13);
        footer.getFont().setBold(true);    
	} 

    private Button showSubHeaderButton,  showSubFooterButton;	
}
