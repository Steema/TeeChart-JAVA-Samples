package features.tool.drawline;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.steema.teechart.tools.DrawLineStyle;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Line;
import com.steema.teechart.tools.DrawLine;

import features.ChartSample;

/**
 * @author tom
 * 
 */
public class DrawLineStyleDemo extends ChartSample implements SelectionListener {

	// TODO private ButtonPen penButton;

	public DrawLineStyleDemo(Composite c) {
		super(c);
	}

	protected void initContent() {
		super.initContent();

	}

	protected void initChart() {
		super.initChart();
		chart1.getAspect().setView3D(false);
		Line tmpLine = new com.steema.teechart.styles.Line(chart1.getChart());
		tmpLine.fillSampleValues(20);

		tool = new com.steema.teechart.tools.DrawLine(chart1.getChart());
		tool.getPen().setColor(Color.BLUE);
		label1 = addLabel(0, "New Line Style: ");
		jStyles = addCombo(0, this);
		jStyles.add("Ellipse");
		jStyles.add("HorizParallel");
		jStyles.add("Line");
		jStyles.add("Rectangle");
		jStyles.add("VertParallel");
		jStyles.setVisibleItemCount(2);
		label2 = addLabel(0, "(Click and drag left mouse button)");

		tool.getPen().setColor(Color.BLUE);
		tool.getPen().setWidth(2);

	}

	private Label label2;
	private Combo jStyles;
	private Label label1;
	private DrawLine tool;

	
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

	
	public void widgetSelected(SelectionEvent arg0) {
		switch (jStyles.getSelectionIndex()) {
		case 0:
			tool.setDrawLineStyle(DrawLineStyle.ELLIPSE);
			break;
		case 1:
			tool.setDrawLineStyle(DrawLineStyle.HORIZPARALLEL);
			break;
		case 2:
			tool.setDrawLineStyle(DrawLineStyle.LINE);
			break;
		case 3:
			tool.setDrawLineStyle(DrawLineStyle.RECTANGLE);
			break;
		case 4:
			tool.setDrawLineStyle(DrawLineStyle.VERTPARALLEL);
			break;
		}

	}

}
