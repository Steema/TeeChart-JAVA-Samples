/*
 * SymbolsDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.legend;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.legend.LegendSymbolPosition;
import com.steema.teechart.legend.LegendSymbolSize;
import com.steema.teechart.styles.Contour;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class SymbolsDemo extends ChartSample implements ModifyListener, SelectionListener {

	public SymbolsDemo(Composite c) {
		super(c);
	}

	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source == widthSpinner) {
			chart1.getLegend().getSymbol().setWidth(widthSpinner.getSelection());    		
		}
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == positionList) {
            switch (positionList.getSelectionIndex()) {
                case 0: chart1.getLegend().getSymbol().setPosition(LegendSymbolPosition.LEFT) ; break;
                case 1: chart1.getLegend().getSymbol().setPosition(LegendSymbolPosition.RIGHT); break;
                default: chart1.getLegend().getSymbol().setPosition(LegendSymbolPosition.LEFT);
            }
        } else if (source == continuousButton) {
            chart1.getLegend().getSymbol().setContinuous(continuousButton.getSelection());
        }
	}	
	
	protected void createContent() {
		super.createContent();	
		addLabel(SWT.LEFT, "Symbol width:");
        widthSpinner = addSpinner(SWT.READ_ONLY | SWT.BORDER, 0, 100, 1, this);
		addLabel(SWT.LEFT, "Position:");
		positionList = addCombo(SWT.READ_ONLY | SWT.BORDER | SWT.SINGLE, this);
        continuousButton = addCheckButton("Continuous", "", this);
	}

	protected void initContent() {
		super.initContent();
        widthSpinner.setSelection(chart1.getLegend().getSymbol().getWidth());
        positionList.setItems(new String[]{"Left", "Right"});
        positionList.select(chart1.getLegend().getSymbol().getPosition().getValue());
        continuousButton.setSelection(chart1.getLegend().getSymbol().getContinuous());
	}

	protected void initChart() {
		super.initChart();
        chart1.getWalls().getBack().setColor(Color.AQUA);
        chart1.getWalls().getBack().setSize(10);
        chart1.getWalls().getBack().setTransparent(false);
        chart1.getWalls().getBottom().setSize(10);
        chart1.getWalls().getLeft().setSize(10);

        chart1.getAxes().getBottom().setInverted(true);
        chart1.getAxes().getDepth().setInverted(true);
        chart1.getAxes().getDepth().setVisible(true);
        chart1.getAxes().getLeft().setInverted(true);

        chart1.getLegend().getSymbol().setContinuous(true);
        chart1.getLegend().getSymbol().setWidthUnits(LegendSymbolSize.PIXELS);

        chart1.getAspect().setElevation(300);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(74);
        chart1.getAspect().setRotation(360);
        chart1.getAspect().setZoom(65);
        chart1.getAspect().setChart3DPercent(70);
        
        Contour series = new Contour(chart1.getChart());
        series.setValueFormat("0.000");
        series.fillSampleValues(10);        
	}	
	
    private Button continuousButton;
    private Combo positionList;
    private Spinner widthSpinner;	
}
