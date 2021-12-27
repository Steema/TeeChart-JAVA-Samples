/*
 * LegendTitleDemo.java
 *
 * <p>Copy.getRight(): Copy.getRight() (c) 2004-2007 by Steema Software SL. All .getRight()s
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.styles.Line;
import features.ChartSample;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
/**
 *
 * @author tom
 */
public class LegendTextGapDemo extends ChartSample
implements SelectionListener ,ModifyListener {

	/** Creates a new instance of LegendTitleDemo */
	public LegendTextGapDemo(Composite c) {
		super(c);
		jCheckBox1.addSelectionListener(this);
		jSpinner1.addModifyListener(this);
		jSpinner2.addModifyListener(this);
		jSpinner3.addModifyListener(this);   
	}

	protected void initChart() {
		super.initChart();
		Legend legend = chart1.getLegend();
		/*legend.getGradient().setDirection(GradientDirection.HORIZONTAL);
        legend.getGradient().setStartColor(Color.GRAY);
        legend.getGradient().setMiddleColor(Color.WHITE);
        legend.getGradient().setEndColor(Color.GRAY);
        legend.getGradient().setVisible(true);*/
		legend.getTitle().setText("Values");
		legend.getTitle().setTransparent(false);
		legend.getTitle().getFont().setColor(Color.BLUE);
	}

	protected void createContent() {
		super.createContent();
		Line tmpSeries = new Line(chart1.getChart());
		tmpSeries.getPointer().setVisible(false);

		tmpSeries.fillSampleValues(20);
		jCheckBox1 = this.addCheckButton("Automatic widths", "", this);
		jLabel3 = this.addLabel(0, "        ");
		jLabel1 = this.addLabel(0, "Column 1 width: ");
		jSpinner1 = addSpinner(30, 0, 100, 1);

		//org.eclipse.swt.graphics.Color c;

		//Image image = new Image(getDisplay(), System.getProperty("user.dir")
		//		+ "/features/images/searchbg.jpg");
		//jSpinner1.setBackgroundImage(image);

		jLabel3 = this.addLabel(0, "        ");
		jLabel2 = this.addLabel(0, "Column 2 width: ");	
		jSpinner2 = addSpinner(14, 0, 100, 1);
		//jSpinner2.setBackgroundImage(image);
		jLabel3 = this.addLabel(0, "        ");
		jLabel3 = addLabel(0, "Adjust gap Symbol-Text: ");
		jSpinner3 = addSpinner(2, -5, 30, 1);
		jSpinner1.setSelection(30);
		jSpinner2.setSelection(14);
		jSpinner3.setSelection(2);
		jSpinner1.setToolTipText("");
		jSpinner1.setEnabled(false);
		jCheckBox1.setSelection(true);
		jCheckBox1.setText("Automatic widths");
		jLabel1.setText("Column 1 width: ");
		jSpinner2.setToolTipText("");
		jSpinner2.setEnabled(false);
		jLabel2.setText("Column 2 width: ");
		jLabel3.setText("Adjust gap Symbol-Text:");
		jSpinner3.setToolTipText("");
		chart1.getAxes().getBottom().getLabels().setDateTimeFormat("dd/MM/yyyy");
		chart1.getAxes().getBottom().setMaximumOffset(5);
		chart1.getAxes().getBottom().setMinimumOffset(5);
		chart1.getAxes().getLeft().setMaximumOffset(5);
		chart1.getAxes().getLeft().setMinimumOffset(5);
		chart1.getHeader().getFont().setColor(new Color(64,64,64));
		chart1.getHeader().getFont().setSize(11);
		chart1.getHeader().setLines(new String[] {"Adjusting Legend spacing"});
		// 
		// 
		// 
		chart1.getLegend().setBorderRound(8);
		// 
		// 
		// 
		chart1.getLegend().getPen().setColor(new Color(255,128,0));
		// 
		// 
		// 
		chart1.getLegend().getShadow().setVisible(false);
		// 
		// 
		// 
		// 
		// 
		// 
		chart1.getPanel().getBrush().setColor(new Color(254,255,255,255));
		// 
		// 
		// 
		chart1.getPanel().getBrush().getGradient().setEndColor(new Color(185,185,225));
		chart1.getPanel().getBrush().getGradient().setStartColor(new Color(234,234,254));
		chart1.getSeries().add(tmpSeries);
		chart1.setSize(466,133);
		// 
		// 
		// 
		// 
		// 
		// 
		// 
		// 
		// 
		chart1.getWalls().getBack().getPen().setVisible(false);
		// 
		// 
		// 
		// 
		// 
		// 
		chart1.getWalls().getBottom().getPen().setVisible(false);
		chart1.getWalls().getBottom().setSize(5);
		// 
		// 
		// 
		// 
		// 
		// 
		chart1.getWalls().getLeft().getPen().setVisible(false);
		chart1.getWalls().getLeft().setSize(5);
	}
	
	// Variables declaration - do not modify
	Button jCheckBox1;
	Label jLabel1;
	Label jLabel2;
	Spinner jSpinner1;
	Spinner jSpinner2;
	Spinner jSpinner3;
	Label jLabel3,jLabel4,jLabel5,jLabel6;

	
	public void widgetDefaultSelected(SelectionEvent arg0) {}

	
	public void widgetSelected(SelectionEvent arg0) {
		if(jCheckBox1.getSelection())
		{
			jSpinner1.setEnabled(false);
			jSpinner2.setEnabled(false);
			chart1.getLegend().setColumnWidthAuto(true);
		}
		else
		{
			jSpinner1.setEnabled(true);
			jSpinner2.setEnabled(true);
			chart1.getLegend().setColumnWidthAuto(false);
			jSpinner1.setSelection(chart1.getLegend().getColumnWidth(0));
			jSpinner2.setSelection(chart1.getLegend().getColumnWidth(1));
		}
	}

	
	public void modifyText(ModifyEvent arg0) {
		Object aux = arg0.getSource();
		if(aux == jSpinner1)
		{
			int value = jSpinner1.getSelection();
			if(value<0)
			{
				jSpinner1.setSelection(0);
				chart1.refreshControl();
			}
			else if(value>100)
			{
				jSpinner1.setSelection(100);
				chart1.refreshControl();
			}
			else
			{
				chart1.getLegend().setColumnWidth(0, value);
				chart1.refreshControl();
			}
		}
		else if(aux== jSpinner2)
		{
			int value = jSpinner2.getSelection();
			if(value<0)
			{
				jSpinner2.setSelection(0);
				chart1.refreshControl();
			}    
			else if(value>100)
			{
				jSpinner2.setSelection(100);
				chart1.refreshControl();
			}
			else
			{
				chart1.getLegend().setColumnWidth(1, value);
				chart1.refreshControl();
			}
		}
		else if(aux==jSpinner3)
		{
			int value = jSpinner3.getSelection();
			if(value<-5)
			{
				jSpinner3.setSelection(-5);
				chart1.refreshControl();
			}    
			else if(value>30)
			{
				jSpinner3.setSelection(30);
				chart1.refreshControl();
			}
			else
			{
				chart1.getLegend().setTextSymbolGap(value);
				chart1.refreshControl();
			}
		}
	}
	// End of variables declaration
}
