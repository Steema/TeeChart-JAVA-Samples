/*
 * WorldDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.world;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.styles.World;
import com.steema.teechart.styles.WorldMapType;

import features.ChartSample;

/**
 * @author yeray
 * 
 */
public class WorldDemo extends ChartSample implements SelectionListener {

	private Combo mapList;
	private World series;
	private Button showAxes;

	public WorldDemo(Composite c) {
		super(c);

	}

	protected void initContent() {
		super.initContent();

		addLabel(0, "Map: ");
		mapList = addCombo(0, this);
		mapList.setItems(new String[] { "World", "Africa", "Asia", "Australia", "CentralAmerica", "Europe", "Europe (EU 15)", "Europe (EU 27)", "Spain", "Middle East", "North America",
				"South America", "USA", "USA with Hawaii and Alaska" });
		showAxes = this.addCheckButton("Show Axes", "", this);
		showAxes.setSelection(true);
	}

	protected void initChart() {
		super.initChart();

		chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        series = new World(chart1.getChart());
        series.setUseColorRange(false);
        series.setUsePalette(false);
        series.getShadow().setVisible(false);
        chart1.getAxes().setVisible(false);
	}

	
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

	
	public void widgetSelected(SelectionEvent arg0) {
		Object aux = arg0.getSource();
		if (aux == mapList) {
			try {
				series.setMap(WorldMapType.fromInt(mapList.getSelectionIndex()));
			} catch (FileNotFoundException e) {
				Logger.getLogger(WorldDemo.class.getName()).log(Level.SEVERE, null, e);
			} catch (IOException e) {
				Logger.getLogger(WorldDemo.class.getName()).log(Level.SEVERE, null, e);
			} catch (ClassNotFoundException e) {
				Logger.getLogger(WorldDemo.class.getName()).log(Level.SEVERE, null, e);
			} catch (Exception e) {
				Logger.getLogger(WorldDemo.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		else if (aux == showAxes) {
			chart1.getAxes().setVisible(showAxes.getSelection());
		}
	}
}
