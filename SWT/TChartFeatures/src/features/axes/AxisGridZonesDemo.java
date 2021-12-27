/*
 * AxisOffsetDemo.java
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

import com.steema.teechart.axis.Axis;
import com.steema.teechart.axis.CustomAxes;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.ArrowHeadStyle;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.ValueListOrder;
import com.steema.teechart.themes.ThemesList;
import features.ChartSample;

/**
 * @author tom
 * 
 */
public class AxisGridZonesDemo extends ChartSample implements
	SelectionListener {

    private Axis axis1;
    private Axis axis2;
    private Axis axis3;
    private Axis axis4;
    private Line line1;
    private Line line2;
    private Line line3;
    
    private Button checkBox1;
    
	public AxisGridZonesDemo(Composite c) {
		super(c);
	}

	protected void initContent() {
		super.initContent();

		checkBox1 = this.addCheckButton("Show custom axes", "", this);
		checkBox1.setSelection(true);
	}

	protected void initChart() {
		super.initChart();
		
		 axis1 = new Axis(chart1.getChart());
	        axis2 = new Axis(chart1.getChart());
	        axis3 = new Axis(chart1.getChart());
	        axis4 = new Axis(chart1.getChart());
	        line1 = new Line(chart1.getChart());
	        line2 = new Line(chart1.getChart());
	        line3 = new Line(chart1.getChart());
	        chart1.getAspect().setView3D(false);
	        chart1.getAxes().getBottom().getMinorTicks().setVisible(false);
	        chart1.getAxes().getBottom().getTicksInner().setVisible(true);
	        chart1.getAxes().getCustom().add(axis1);
	        chart1.getAxes().getCustom().add(axis2);
	        chart1.getAxes().getCustom().add(axis3);
	        chart1.getAxes().getCustom().add(axis4);
	        chart1.getHeader().setText("Axis Grid Zoning");
	        chart1.getHeader().setVisible(true);
	        chart1.getAxes().getDepth().getMinorTicks().setVisible(false);
	        chart1.getAxes().getDepth().getTicksInner().setVisible(true);
	        chart1.getAxes().getDepthTop().getMinorTicks().setVisible(false);
	        chart1.getAxes().getDepthTop().getTicksInner().setVisible(true);
	        chart1.getAxes().getBottom().setEndPosition(50);
	        chart1.getAxes().getLeft().setEndPosition(50);
	        chart1.getAxes().getLeft().setLogarithmicBase(2);
	        chart1.getAxes().getLeft().getMinorTicks().setVisible(false);
	        chart1.getAxes().getLeft().getTicksInner().setVisible(true);
	        chart1.getAxes().getRight().getMinorTicks().setVisible(false);
	        chart1.getAxes().getRight().getTicksInner().setVisible(true);
	        chart1.getAxes().getTop().getMinorTicks().setVisible(false);
	        chart1.getAxes().getTop().getTicksInner().setVisible(true);


	        axis1.setHorizontal(false);
	        axis1.setLogarithmicBase(2);
	        axis1.getMinorTicks().setVisible(false);
	        axis1.setOtherSide(false);
	        axis1.getTicksInner().setVisible(true);
	        axis1.setStartPosition(62);

	        axis2.setHorizontal(true);
	        axis2.setLogarithmicBase(2);
	        axis2.getMinorTicks().setVisible(false);
	        axis2.setOtherSide(false);
	        axis2.setRelativePosition(50);
	        axis2.getTicksInner().setVisible(true);


	        axis3.setHorizontal(true);
	        axis3.setLogarithmicBase(2);
	        axis3.getMinorTicks().setVisible(false);
	        axis3.setOtherSide(false);
	        axis3.setStartPosition(55);
	        axis3.getTicksInner().setVisible(true);

	        axis4.setHorizontal(false);
	        axis4.setLogarithmicBase(2);
	        axis4.getMinorTicks().setVisible(false);
	        axis4.setOtherSide(true);
	        axis4.setStartPosition(62);
	        axis4.getTicksInner().setVisible(true);
	        axis4.setZPosition(0);

	        line1.setColorEach(false);
	        line1.getMarks().getCallout().setArrowHead(ArrowHeadStyle.NONE);
	        line1.getMarks().getCallout().setArrowHeadSize(8);
	        line1.getMarks().getCallout().getBrush().setColor(Color.BLACK);
	        line1.getMarks().getCallout().setDistance(0);
	        line1.getMarks().getCallout().setDraw3D(false);
	        line1.getMarks().getCallout().setLength(10);
	        line1.getMarks().getCallout().setStyle(PointerStyle.RECTANGLE);
	        line1.getMarks().getCallout().setVisible(false);

	        line2.getMarks().getCallout().setArrowHead(ArrowHeadStyle.NONE);
	        line2.getMarks().getCallout().setArrowHeadSize(8);
	        line2.getMarks().getCallout().getBrush().setColor(Color.BLACK);
	        line2.getMarks().getCallout().setDistance(0);
	        line2.getMarks().getCallout().setDraw3D(false);
	        line2.getMarks().getCallout().setLength(10);
	        line2.getMarks().getCallout().setStyle(PointerStyle.RECTANGLE);
	        line2.getMarks().getCallout().setVisible(false);
	        line2.getMarks().setTransparent(true);
	        line2.getPointer().setStyle(PointerStyle.RECTANGLE);
	        line2.getXValues().setOrder(ValueListOrder.ASCENDING);


	        line3.getMarks().getCallout().setArrowHead(ArrowHeadStyle.NONE);
	        line3.getMarks().getCallout().setArrowHeadSize(8);
	        line3.getMarks().getCallout().getBrush().setColor(Color.BLACK);
	        line3.getMarks().getCallout().setDistance(0);
	        line3.getMarks().getCallout().setDraw3D(false);
	        line3.getMarks().getCallout().setLength(10);
	        line3.getMarks().getCallout().setStyle(PointerStyle.RECTANGLE);
	        line3.getMarks().getCallout().setVisible(false);
	        line3.getMarks().setTransparent(true);
	        line3.getPointer().setStyle(PointerStyle.RECTANGLE);
	        line3.getXValues().setOrder(ValueListOrder.ASCENDING);

	        line1.setCustomHorizAxis(axis2);
	        line2.setCustomVertAxis(axis1);
	        line3.setCustomHorizAxis(axis3);
	        line3.setCustomVertAxis(axis4);
	        //Use of elected Partner Axes to limit GridLine zone
	        chart1.getAxes().getBottom().setUsePartnerAxis(true);
	        chart1.getAxes().getBottom().setPartnerAxis(chart1.getAxes().getCustom().getAxis(0));
	        chart1.getAxes().getCustom().getAxis(0).setUsePartnerAxis(true);
	        chart1.getAxes().getCustom().getAxis(0).setPartnerAxis(chart1.getAxes().getBottom());

	        chart1.getAxes().getCustom().getAxis(1).setUsePartnerAxis(true);
	        chart1.getAxes().getCustom().getAxis(1).setPartnerAxis(chart1.getAxes().getLeft());

	        chart1.getAxes().getCustom().getAxis(3).setUsePartnerAxis(true);
	        chart1.getAxes().getCustom().getAxis(3).setPartnerAxis(chart1.getAxes().getCustom().getAxis(2));
	        chart1.getAxes().getCustom().getAxis(2).setUsePartnerAxis(true);
	        chart1.getAxes().getCustom().getAxis(2).setPartnerAxis(chart1.getAxes().getCustom().getAxis(3));

	        for (int i = 0; i < chart1.getSeriesCount(); i++)
	        {
	            chart1.getSeries(i).fillSampleValues(20);
	        }
	        ThemesList.applyTheme(chart1.getChart(), 1);

	}
	
	
	public void widgetDefaultSelected(SelectionEvent arg0) {

	}
	
	
	public void widgetSelected(SelectionEvent arg0) {
        Object aux = arg0.getSource();
        if (aux == checkBox1) {
        	CustomAxes axes = chart1.getAxes().getCustom();
            for (int t = 0; t < axes.size(); t++) {
                 axes.getAxis(t).setVisible(checkBox1.getSelection());
            }
        }
	}

}
