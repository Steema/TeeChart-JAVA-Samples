/*
 * PaletteDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.surface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.Aspect;
import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.legend.LegendSymbolPosition;
import com.steema.teechart.styles.PaletteStyle;
import com.steema.teechart.styles.Surface;

import features.ChartSample;
import features.utils.EnumStrings;

/**
 * @author tom
 *
 */
public class PaletteDemo extends ChartSample implements SelectionListener {

	private Surface series;

	public PaletteDemo(Composite c) {
		super(c);
		paletteStyleList.addSelectionListener(this);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
		if (source == paletteStyleList) {
			switch (paletteStyleList.getSelectionIndex()) {
			case 0: series.setPaletteStyle(PaletteStyle.PALE); break;
			case 1: series.setPaletteStyle(PaletteStyle.STRONG); break;
			case 2: series.setPaletteStyle(PaletteStyle.GRAYSCALE); break;
			case 3: series.setPaletteStyle(PaletteStyle.INVERTED_GRAYSCALE); break;
			case 4: series.setPaletteStyle(PaletteStyle.RAINBOW); break;        
			}
		}
	}

	protected void createContent() {
		super.createContent();	

		addLabel(SWT.LEFT, "Wire-frame Color Style: ");
		paletteStyleList = addCombo(SWT.DROP_DOWN | SWT.READ_ONLY);      
	}

	protected void initContent() {
		super.initContent();
		paletteStyleList.setItems(EnumStrings.PALETTE_STYLES);
		paletteStyleList.select(1);	
	}

	protected void initChart() {
		super.initChart();
		Wall tmpWall = chart1.getWalls().getBack();
		tmpWall.getGradient().setEndColor(Color.GRAY);
		tmpWall.getGradient().setVisible(true);
		tmpWall.setTransparent(false);

		chart1.getLegend().getSymbol().setContinuous(true);
		chart1.getLegend().getSymbol().setPosition(LegendSymbolPosition.RIGHT);

		chart1.getAxes().getDepth().setVisible(true);

		Aspect tmpAspect = chart1.getAspect();
		tmpAspect.setElevation(305);
		tmpAspect.setOrthogonal(false);
		tmpAspect.setPerspective(63);
		tmpAspect.setRotation(325);
		tmpAspect.setZoom(45);
		tmpAspect.setZoomText(false);
		tmpAspect.setChart3DPercent(100);
		tmpAspect.setClipPoints(false);

		series = new com.steema.teechart.styles.Surface(chart1.getChart());
		series.setPaletteStyle(PaletteStyle.STRONG);
		series.setUsePalette(true);
		series.getPen().setVisible(false);
		series.setPaletteSteps(15);
		series.setUseColorRange(false);
		series.fillSampleValues(30);        
	}   	

	Combo paletteStyleList;    
}
