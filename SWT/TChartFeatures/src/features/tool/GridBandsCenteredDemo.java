/*
 * PieFocusDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.tool;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.HorizBar;
import com.steema.teechart.tools.GridBand;

import features.ChartSample;

/**
 * @author tom
 * 
 */
public class GridBandsCenteredDemo extends ChartSample implements
		SelectionListener {

	private GridBand tool;
	private HorizBar horizBar1;

	public GridBandsCenteredDemo(Composite c) {
		super(c);
	}

	protected void initContent() {
		super.initContent();
		horizBar1 = new HorizBar(chart1.getChart());
		horizBar1.fillSampleValues();
		horizBar1.setBarHeightPercent(55);
		horizBar1.getGradient().setVisible(true);
		horizBar1.getGradient().setDirection(GradientDirection.HORIZONTAL);
		horizBar1.getMarks().setTransparent(true);
		horizBar1.setMarksOnBar(true);
		horizBar1.getPen().setVisible(false);

		// Create GridBandTool...
		tool = new com.steema.teechart.tools.GridBand(chart1.getChart());
		tool.setAxis(chart1.getAxes().getLeft());

		// cosmetic examples:
		// GetTeeBrush(0,Band1.Image.Bitmap);
		tool.getBand1().setColor(Color.fromArgb(224, 224, 224));
		tool.getBand2().setColor(Color.fromArgb(192, 192, 192));

		// Change Left axis grid
		chart1.getAxes().getLeft().getGrid()
				.setColor(Color.fromArgb(254, 255, 0, 0));

		gridCentered = this.addCheckButton("Axes Left grid centered", "", this);
		gridCentered.setSelection(true);
		view3DButton = this.addCheckButton("View 3D", "", this);
		chart1.getAxes().getLeft().setGridCentered(true);
	}

	protected void initChart() {
		super.initChart();
		chart1.setText("Axis Grid Bands");
		chart1.getAspect().setView3D(false);

	}

	private Button view3DButton, gridCentered;

	
	public void widgetDefaultSelected(SelectionEvent arg0) {
		

	}

	
	public void widgetSelected(SelectionEvent arg0) {
        Object aux = arg0.getSource();
        if (aux == gridCentered) {
            if (gridCentered.getSelection()) {
                chart1.getAxes().getLeft().setGridCentered(true);
            } else {
                chart1.getAxes().getLeft().setGridCentered(false);
            }
        }
        else if( aux==view3DButton)
        {
            if (view3DButton.getSelection()) {
                chart1.getAspect().setView3D(true);
            } else {
            	  chart1.getAspect().setView3D(false);
            }
        }

	}
}
