/*
 * PieFocusDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.tool;

import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.BarStyle;
import com.steema.teechart.styles.HorizBar;

import com.steema.teechart.styles.CustomBar.MarksLocation;
import com.steema.teechart.themes.BlackIsBackTheme;
import com.steema.teechart.themes.ThemesList;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class MarksOnBarDemo extends ChartSample {


    
	public MarksOnBarDemo(Composite c) {
		super(c);
	
	}

	


	protected void initContent() {
		super.initContent();	
	}

	protected void initChart() {
		super.initChart();
		HorizBar bar1 = new HorizBar(chart1.getChart());
		bar1.fillSampleValues(6);
		bar1.setAutoMarkPosition(false);
		bar1.getPen().setVisible(false);
		chart1.getAspect().setView3D(false);
		bar1.setMarksOnBar(true);
		bar1.setMarksLocation(MarksLocation.Center);
		ThemesList.applyTheme(chart1.getChart(), 1);
		bar1.setBarStyle(BarStyle.RECTGRADIENT);
		bar1.getGradient().setStartColor(Color.fromArgb(230, 200, 90));
		bar1.getGradient().setMiddleColor(Color.fromArgb(226, 242, 170));
		bar1.getGradient().setEndColor(Color.fromArgb(230, 200, 90));
		bar1.getGradient().setVisible(true);
		


     	}   		

}
