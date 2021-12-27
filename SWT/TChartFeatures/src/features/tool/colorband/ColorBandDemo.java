/*
 * ColorBandDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.colorband;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.drawing.HatchStyle;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.tools.ColorBand;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class ColorBandDemo extends ChartSample implements ModifyListener, SelectionListener {

	public ColorBandDemo(Composite c) {
		super(c);
		bandSpinner.addModifyListener(this);	
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
        if (source == bandSpinner) {
            refreshBandProperties();
        };
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;				
        ColorBand selTool = getSelectedBand();

        boolean isSelected = ((Button)source).getSelection();
        if (source == invertAxisButton) {
            chart1.getAxes().getLeft().setInverted(isSelected);
        } else if (source == showBandsButton) {
            for (int t=0; t < chart1.getTools().size(); t++) {
                if (chart1.getTools().getTool(t) instanceof ColorBand) {
                    chart1.getTools().getTool(t).setActive(isSelected);
                }
            }
        } else if (source == gradientButton) {
            selTool.getGradient().setVisible(isSelected);
        } else if (source == drawBehindButton) {
            selTool.setDrawBehind(isSelected);
        } else if (source == drawBehindAxesButton) {
            //@TODO        	
        }
	}
	
	protected void createContent() {
		super.createContent();	
		addLabel(SWT.LEFT, "Band: ");        
        bandSpinner = addSpinner(SWT.READ_ONLY | SWT.BORDER, 0, chart1.getTools().size()-1, 1);                

        gradientButton = addCheckButton("Gradient", "", this);
        drawBehindButton = addCheckButton("Draw Behind", "", this);
        drawBehindAxesButton = addCheckButton("Draw Behind Axes", "", this);
        invertAxisButton = addCheckButton("Invert Axis", "", this);
        showBandsButton = addCheckButton("Show Bands", "", this);
	}

	protected void initContent() {
		super.initContent();
		showBandsButton.setSelection(true);
		bandSpinner.setSelection(1);
		refreshBandProperties();        		
	}

	protected void initChart() {
		super.initChart();
        chart1.getLegend().setVisible(false);
        
        Bar tmpSeries = new com.steema.teechart.styles.Bar(chart1.getChart());
        tmpSeries.add(new int[] {30,150,75,280,600});

        ColorBand tmpTool;

        // tool1
        tmpTool = new com.steema.teechart.tools.ColorBand(chart1.getChart());
        tmpTool.getBrush().setStyle(HatchStyle.BACKWARDDIAGONAL);
        tmpTool.setEnd(100.0);
        tmpTool.getPen().setVisible(false);
        tmpTool.setAxis(chart1.getAxes().getLeft());

        //tool2
        tmpTool = new com.steema.teechart.tools.ColorBand(chart1.getChart());
        tmpTool.getBrush().setColor(Color.BLUE);
        tmpTool.setEnd(200.0);
        tmpTool.setStart(100.0);
        tmpTool.getPen().setVisible(false);
        tmpTool.setAxis(chart1.getAxes().getLeft());

        //tool3
        tmpTool = new com.steema.teechart.tools.ColorBand(chart1.getChart());
        tmpTool.getBrush().setColor(Color.YELLOW);
        tmpTool.getGradient().setDirection(GradientDirection.HORIZONTAL);
        tmpTool.getGradient().setEndColor(Color.GRAY);
        tmpTool.getGradient().setMiddleColor(Color.WHITE);
        tmpTool.setEnd(300.0);
        tmpTool.setStart(200.0);
        tmpTool.getPen().setVisible(false);
        tmpTool.setAxis(chart1.getAxes().getLeft());

        //tool4
        tmpTool = new com.steema.teechart.tools.ColorBand(chart1.getChart());
        tmpTool.getBrush().setColor(Color.LIME);
        tmpTool.setEnd(400.0);
        tmpTool.setStart(300.0);
        tmpTool.getPen().setVisible(false);
        tmpTool.setAxis(chart1.getAxes().getLeft());

        //tool5
        tmpTool = new com.steema.teechart.tools.ColorBand(chart1.getChart());
        tmpTool.getBrush().setColor(Color.WHITE);
        tmpTool.setEnd(700.0);
        tmpTool.setStart(500.0);
        tmpTool.getPen().setVisible(false);
        tmpTool.setAxis(chart1.getAxes().getLeft());
	}   	
	

    private ColorBand getSelectedBand() {        
        int selBand = bandSpinner.getSelection();
        return (ColorBand)chart1.getTools().getTool(selBand); //assume only ColorBand tools
    }

    private void refreshBandProperties() {
        ColorBand tmpTool = getSelectedBand();
        gradientButton.setSelection(tmpTool.getGradient().getVisible());
        drawBehindButton.setSelection(tmpTool.getDrawBehind());
        //todo  drawBehindAxis
    }
    
    private Button gradientButton, drawBehindButton, drawBehindAxesButton, invertAxisButton, showBandsButton;
    private Spinner bandSpinner;    
}
