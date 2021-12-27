/*
 * ContourDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.contour;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.Aspect;
import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.styles.Contour;
import com.steema.teechart.styles.Surface;
import com.steema.teechart.styles.Contour.LevelProps;
import features.ChartSample;

/**
 * @author tom
 *
 */
public class ContourDemo extends ChartSample implements SelectionListener {

    private Contour contourSeries;
    private Surface surfaceSeries;
    
	public ContourDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source instanceof Button) {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == view2DButton) {
                chart1.getAspect().setView3D(!isSelected);
            } else if (source == showWallsButton) {
                chart1.getWalls().setView3D(isSelected);
            } else if (source == colorEachButton) {
                contourSeries.setColorEach(isSelected);
            } else if (source == posLevelButton) {
                contourSeries.setYPositionLevel(isSelected);
            } else if (source == surfaceButton) {
                surfaceSeries.setActive(isSelected);
            }
        };
	}	
	
	protected void createContent() {
		super.createContent();	

		view2DButton = addCheckButton("2D", "", this);
		showWallsButton = addCheckButton("Show Walls", "", this);		
		colorEachButton = addCheckButton("Color each", "", this);		
		posLevelButton = addCheckButton("Levels at Y", "", this);		
		surfaceButton = addCheckButton("Surface", "", this);		
	}

	protected void initContent() {
		super.initContent();
		view2DButton.setSelection(chart1.getAspect().getView3D());
        showWallsButton.setSelection(chart1.getWalls().getView3D());
        colorEachButton.setSelection(contourSeries.getColorEach());
        posLevelButton.setSelection(contourSeries.getYPositionLevel());
        surfaceButton.setSelection(true);
        surfaceButton.setEnabled(false);
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setChart3DPercent(100);
        chart1.getAxes().getDepth().setVisible(true);

        Wall tmpWall;
        tmpWall = chart1.getWalls().getBack();
        tmpWall.getBrush().setColor(Color.WHITE);
        tmpWall.setSize(10);
        tmpWall.setColor(new Color(8454143));
        tmpWall = chart1.getWalls().getBottom();
        tmpWall.setSize(10);
        tmpWall.setColor(new Color(16777088));
        tmpWall = chart1.getWalls().getLeft();
        tmpWall.setSize(10);

        Legend tmpLegend = chart1.getLegend();
        tmpLegend.setAlignment(LegendAlignment.LEFT);
        //Legend.ColorWidth = 30
        tmpLegend.setLegendStyle(LegendStyle.VALUES);
        tmpLegend.getSymbol().setWidth(30);

        chart1.getPanel().setMarginLeft(5);
        chart1.getPanel().setMarginTop(5);

        Aspect tmpAspect = chart1.getAspect();
        tmpAspect.setElevation(342);
        tmpAspect.setOrthogonal(false);
        tmpAspect.setPerspective(55);
        tmpAspect.setRotation(317);
        tmpAspect.setZoom(72);
        
        initSeries();
        
        chart1.getTools().add(new com.steema.teechart.tools.Rotate());
	}   		
	
	protected void initSeries() {

        // First we add XYZ points to the Contour series...
        contourSeries = new com.steema.teechart.styles.Contour(chart1.getChart());
        contourSeries.clear();
        
        contourSeries.fillSampleValues();
//      // We add values from 0 to 1000.  21x21 cells = 441
//      for (int x=-10; x <= 10; x++) {
//          for (int z=-10; z <=10; z++) {
//              contourSeries.add(x, getRandomXYZ(x,z), z, "", Color.EMPTY);
//          }
//      }
//
//      // Then we specify how many "contour levels" we want
//      contourSeries.setNumLevels(10);
//
//      // Here we specify the 10 Level values
//      ContourLevels tmpLevels = new ContourLevels();
//      tmpLevels.ensureCapacity(10);

     /* ContourLevel tmpLevel;
      tmpLevel = new ContourLevel(contourSeries,tmpLevel);
      tmpLevel.setUpToValue(0);
      tmpLevels.add(tmpLevel);
      tmpLevel = new ContourLevel(contourSeries,tmpLevel);
      tmpLevel.setUpToValue(100);
      tmpLevels.add(tmpLevel);
      tmpLevel = new ContourLevel(contourSeries,tmpLevel);
      tmpLevel.setUpToValue(200);
      tmpLevels.add(tmpLevel);
      tmpLevel = new ContourLevel(contourSeries,tmpLevel);
      tmpLevel.setUpToValue(300);
      tmpLevels.add(tmpLevel);
      tmpLevel = new ContourLevel(contourSeries,tmpLevel);
      tmpLevel.setUpToValue(400);
      tmpLevels.add(tmpLevel);
      tmpLevel = new ContourLevel(contourSeries,tmpLevel);
      tmpLevel.setUpToValue(500);
      tmpLevels.add(tmpLevel);
      tmpLevel = new ContourLevel(contourSeries,tmpLevel);
      tmpLevel.setUpToValue(600);
      tmpLevels.add(tmpLevel);
      tmpLevel = new ContourLevel(contourSeries,tmpLevel);
      tmpLevel.setUpToValue(700);
      tmpLevels.add(tmpLevel);
      tmpLevel = new ContourLevel(contourSeries,tmpLevel);
      tmpLevel.setUpToValue(800);
      tmpLevels.add(tmpLevel);
      tmpLevel = new ContourLevel(contourSeries,tmpLevel);
      tmpLevel.setUpToValue(900);
      tmpLevels.add(tmpLevel);
      contourSeries.setLevels( tmpLevels );*/

      // We specify the Y levels position to the "middle"
      contourSeries.setYPosition(
              (contourSeries.getYValues().getMaximum()
               + contourSeries.getYValues().getMinimum() )/2.0
              );

      contourSeries.setContourLevelResolver( new Contour.LevelResolver() {
          public LevelProps getLevel(int levelIndex, double value, Color color) {
              Contour.LevelProps result = contourSeries.new LevelProps(value, color);
              // Here we specify the 10 Level values
              switch (levelIndex)  {
                  case 0: result.setLevelValue(0); break;
                  case 1: result.setLevelValue(100); break;
                  case 2: result.setLevelValue(200); break;
                  case 3: result.setLevelValue(300); break;
                  case 4: result.setLevelValue(400); break;
                  case 5: result.setLevelValue(500); break;
                  case 6: result.setLevelValue(600); break;
                  case 7: result.setLevelValue(700); break;
                  case 8: result.setLevelValue(800); break;
                  case 9: result.setLevelValue(900); break;
                  default: result.setLevelValue(levelIndex*100); break;
              }

              /* We can also specify here the exact Color for each level.
                 For example:

                 if (result.getLevelValue()==500) {
                     result.setColor(Color.RED);
                 }
               */
              return result;
          }
      });

      // We can also use a Surface series...
//      surfaceSeries = new com.steema.teechart.styles.Surface(chart1.getChart());
//      surfaceSeries.setDataSource(contourSeries);
//      surfaceSeries.setShowInLegend(false);      	
	}
	
    private Button view2DButton, showWallsButton, colorEachButton, posLevelButton, surfaceButton;	
}
