/*
 * Points3DTreatNullsDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.points3d;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.misc.Utils;
import com.steema.teechart.styles.Points3D;
import com.steema.teechart.styles.TreatNullsStyle;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class Points3DTreatNullsDemo extends ChartSample implements SelectionListener {

	private Points3D points3D1;

	public Points3DTreatNullsDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;	
		if (source == dontPaint) {
			points3D1.setTreatNulls(TreatNullsStyle.DONOTPAINT);
		} else if (source == skip) {
			points3D1.setTreatNulls(TreatNullsStyle.SKIP);
		} else if (source == ignore) {
			points3D1.setTreatNulls(TreatNullsStyle.IGNORE);
		} else if (source == refreshButton) {
			points3D1.clear();
			addPoints();            
		} else if (source == view3D) {
			chart1.getAspect().setView3D(((Button)source).getSelection());
		} 
	}	

	protected void createContent() {
		super.createContent();

		addLabel(SWT.LEFT, "Treat nulls: ");

        dontPaint = addRadioButton("Don't Paint", "",this);
        skip = addRadioButton("Skip", "", this);
        ignore = addRadioButton("Ignore", "", this);
        
		view3D = addCheckButton("View 3D", "", this);
		refreshButton = addPushButton("Refresh", "", this);
	}

	protected void initContent() {
		super.initContent();
        dontPaint.setSelection(true);
		view3D.setSelection(chart1.getAspect().getView3D());    	
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(true);
        chart1.getAspect().setSmoothingMode(true);
        chart1.getHeader().setVisible(true);
        chart1.getHeader().getFont().setColor(Color.NAVY);
        chart1.setText("Null points support");
        
        chart1.getPanel().getGradient().setEndColor(Color.fromArgb(254,21,60,89));
        chart1.getPanel().getGradient().setStartColor(Color.fromArgb(254,255,255,255));
        chart1.getPanel().getGradient().setUseMiddle(false);
        chart1.getPanel().getGradient().setVisible(true);
            
        chart1.getWalls().getBack().setTransparent(false);
        chart1.getWalls().getBack().getGradient().setVisible(true);
        chart1.getWalls().getBack().getGradient().setUseMiddle(false);
        chart1.getWalls().getBack().getGradient().setStartColor(Color.fromArgb(234, 234, 234));
        chart1.getWalls().getBack().getGradient().setEndColor(Color.WHITE);

		points3D1 = new com.steema.teechart.styles.Points3D(chart1.getChart());
		points3D1.setColor(Color.fromArgb(224,77,44));
		addPoints(); 
	}   

	private void addPoints() {
		int length = 55;

		double[] xValues = new double[length], yValues = new double[length],
		zValues = new double[length];
		Color[] colorValues = new Color[length];

		Random rnd = new Random();
		double tmp, oldTemp = 0;

		points3D1.setDefaultNullValue(0);

		for (int i = 0; i < length; i++) {
			tmp = rnd.nextDouble();
			while (tmp == oldTemp) {
				tmp = rnd.nextDouble();
			}

			if ((i > 0) && (i % 5 == 0)) {
				xValues[i] = i;
				yValues[i] = 0;
				zValues[i] = tmp;
				colorValues[i] = Color.TRANSPARENT;
			}
			else {
				xValues[i] = i;
				yValues[i] = tmp;
				zValues[i] = tmp;
				colorValues[i] = Color.fromArgb(255 - 
						Utils.round(255 * tmp),
						Utils.round(255 * tmp), 
						255 - Utils.round(255 * tmp));
			}

			oldTemp = tmp;
		}

		points3D1.add(xValues, yValues, zValues, colorValues);
	}

    private Button dontPaint, skip, ignore;
    private Button view3D;
    private Button refreshButton;		
}
