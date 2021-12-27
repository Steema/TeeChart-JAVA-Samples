/*
 * XYZGriddingDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.misc.MathUtils;
import com.steema.teechart.styles.Custom3D;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Points3D;
import com.steema.teechart.styles.Surface;
import com.steema.teechart.tools.Rotate;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class XYZGriddingDemo extends ChartSample implements SelectionListener {

	private Points3D series1;
	private Surface series2;

	public XYZGriddingDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
        if ((source == griddingButton) || (source == surfaceButton)) {        	
            series1.setVisible(((Button)griddingButton).getSelection());
            series2.setVisible(((Button)surfaceButton).getSelection());
        }
	}	

	protected void createContent() {
		super.createContent();
		addLabel(SWT.LEFT, "View: ");    	
		griddingButton = addRadioButton("Original XYZ", "", this);
		surfaceButton = addRadioButton("Surface", "", this);  		
	}

	protected void initContent() {
		super.initContent();    	   	

        surfaceButton.setSelection(true);
	}

	protected void initChart() {
		super.initChart();
        chart1.getWalls().getBack().setTransparent(false);
        chart1.getLegend().setVisible(false);
		chart1.getAxes().getDepth().getLabels().setSeparation(60);

		chart1.getAxes().getDepth().setVisible(true);

		chart1.getAspect().setChart3DPercent(90);
		chart1.getAspect().setOrthogonal(false);
		chart1.getAspect().setPerspective(56);
		chart1.getAspect().setZoom(68);   

        new Rotate(chart1.getChart());

        series1 = new Points3D(chart1.getChart());
        series1.getLinePen().setVisible(false);
        series1.getPointer().setHorizSize(1);
        series1.getPointer().setVertSize(1);
        series1.getPointer().setInflateMargins(true);
        series1.getPointer().setStyle(PointerStyle.RECTANGLE);
        series1.getPointer().setVisible(true);

        series2 = new Surface(chart1.getChart());
        
        series1.setVisible(((Button)griddingButton).getSelection());
        series2.setVisible(((Button)surfaceButton).getSelection());

        fill();
        gridding(series1, series2, 15);
	}   		

	/**
	 * Add many random XYZ points to Series1
	 */
	 private void fill() {
		series1.clear();
		int m = 50;
		double tmpX, tmpZ;

		for (double x=-m; x <= +m; x++) {
			tmpX = MathUtils.sqr(x/30);
			for (double z=-m; z <= +m; z++) {
				tmpZ = MathUtils.sqr(z/30);
				tmpZ = Math.sqrt(tmpX+tmpZ);
				series1.add(x, 4*Math.cos(3*tmpZ)*Math.exp(-0.5*tmpZ), z);
			}
		}
	 }

	 private static double getClosestValue(Custom3D source, double x, double z) {
		 double tmpResult = 0;
		 double maxDist = MAX_DISTANCE;
		 int closer = -1;

		 double tmpX, tmpZ, dist;
		 for (int t=0; t < source.getCount(); t++) {
			 tmpX = source.getXValues().getValue(t)-x;
			 tmpZ = source.getZValues().getValue(t)-z;
			 dist = Math.sqrt(MathUtils.sqr(tmpX)+MathUtils.sqr(tmpZ));
			 if (dist < maxDist) {
				 maxDist = dist;
				 closer = t;
			 }
		 }
		 if (closer != -1) {
			 tmpResult = source.getYValues().getValue(closer);
		 }

		 return tmpResult;
	 }

	 private static void gridding(Custom3D source, Custom3D dest, int gridSize) {
		 double tmpMinX = source.getXValues().getMinimum();
		 double tmpMinZ = source.getXValues().getMinimum();

		 double tmpXFactor = source.getXValues().getRange() / gridSize;
		 double tmpZFactor = source.getXValues().getRange() / gridSize;

		 dest.clear();

		 /* loop all grid cells */
		 double tmpX, tmpZ;
		 for (int x=1; x <= gridSize; x++) {
			 tmpX = tmpMinX+((x-1)*tmpXFactor);
			 for (int z=1; z <= gridSize; z++) {
				 tmpZ = tmpMinZ+ ((z-1)*tmpZFactor);
				 dest.add(x, getClosestValue(source, tmpX, tmpZ) , z);
			 }
		 }
	 }

	 private Button griddingButton, surfaceButton;
	 private final static double MAX_DISTANCE=1E+300;    
}
