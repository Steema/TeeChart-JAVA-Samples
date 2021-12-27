package mypackage;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;

import com.steema.teechart.*;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.DrawAllPointsStyle;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.Pie;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Points;
import com.steema.teechart.styles.TreatNullsStyle;
import com.steema.teechart.themes.*;

import com.steema.*;




/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */
public final class MyScreen extends MainScreen
{
	TChart tChart1;
	TChart tChart2;
	TChart tChart3;
	
    /**
     * Creates a new MyScreen object
     */
    public MyScreen()
    {        
        // Set the displayed title of the screen       
        setTitle("TeeChart for BlackBerry");
        
        // Creates the Chart object
        tChart1 = new TChart(); 
        
        // Setting its bounds
        tChart1.setHeight(220);
        tChart1.setWidth(240);
        
        // Applying a specific theme to the Chart - Opera theme
		ThemesList.applyTheme(tChart1.getChart(), 1);
		
		// Setting Header characteristics
        tChart1.getHeader().setVisible(false);
        
        // Changing aspect to 2D
		tChart1.getAspect().setView3D(false);
		
		// Setting Legend characteristics
		tChart1.getLegend().setVisible(false);
		
		// Setting Walls characteristics
		tChart1.getWalls().getBack().getPen().setVisible(false);
		
		// Setting Panel characteristics
		tChart1.getPanel().getBorderPen().setVisible(false);
        tChart1.getPanel().setMarginBottom(0);
        tChart1.getPanel().setMarginTop(0);
        tChart1.getPanel().setMarginLeft(0);
        tChart1.getPanel().setMarginRight(0);
        
        // Setting Bevel Width
        tChart1.getPanel().setBevelWidth(0);        
        
		Pie pie1 = new Pie(tChart1.getChart());
		pie1.setCircled(true);
		pie1.getMarks().setVisible(false);
		pie1.fillSampleValues(4);
		
		// Chart 2
        tChart2 = new TChart();
        tChart2.setHeight(220);
        tChart2.setWidth(400);
        
        tChart2.getPanel().setMarginBottom(0);
        tChart2.getPanel().setMarginTop(0);
        tChart2.getPanel().setMarginLeft(0);
        tChart2.getPanel().setMarginRight(10);
        tChart2.getFooter().setVisible(false);
        
		tChart2.getAspect().setView3D(false);
		tChart2.getLegend().setVisible(false);
        tChart2.getHeader().setVisible(false);
        
		ThemesList.applyTheme(tChart2.getChart(), 1);
        tChart2.getWalls().getBack().setTransparent(true);
		tChart2.getWalls().getBack().getPen().setVisible(false);        
		tChart2.getPanel().getGradient().setVisible(true);
		tChart2.getPanel().getBorderPen().setVisible(false);
        tChart2.getPanel().setBevelWidth(0);

		Bar bar = new Bar(tChart2.getChart());		
		bar.getMarks().setVisible(false);		
		bar.fillSampleValues(10);

		// Chart 3
        tChart3 = new TChart();
        tChart3.setHeight(230);
        tChart3.setWidth(640); 
        ThemesList.applyTheme(tChart3.getChart(), 1);
        
        tChart3.getPanel().setMarginBottom(0);
        tChart3.getPanel().setMarginTop(0);
        tChart3.getFooter().setVisible(false);
        
        tChart3.getAspect().setView3D(false);
        tChart3.getLegend().setVisible(false);
        tChart3.getHeader().setVisible(false);
        
        tChart3.getWalls().getBack().setTransparent(true);
        tChart3.getWalls().getBack().getPen().setVisible(false);
		
		tChart3.getPanel().getGradient().setVisible(true);
		tChart3.getChart().getPanel().getBorderPen().setVisible(false);
        tChart3.getPanel().setBevelWidth(0);		
		
		/* FastLine Series */
		FastLine fast1 = new FastLine(tChart3.getChart());
		fast1.fillSampleValues(100);
		fast1.setTreatNulls(TreatNullsStyle.DONOTPAINT);
		fast1.setDrawAllPointsStyle(DrawAllPointsStyle.MINMAX);
		fast1.setDrawAllPoints(false);
		
		FastLine fast2 = new FastLine(tChart3.getChart());
		fast2.fillSampleValues(100);
		fast2.setTreatNulls(TreatNullsStyle.DONOTPAINT);
		fast2.setDrawAllPointsStyle(DrawAllPointsStyle.MINMAX);
		fast2.setDrawAllPoints(false);

		FastLine fast3 = new FastLine(tChart3.getChart());
		fast3.fillSampleValues(100);
		fast3.setTreatNulls(TreatNullsStyle.DONOTPAINT);
		fast3.setDrawAllPointsStyle(DrawAllPointsStyle.MINMAX);
		fast3.setDrawAllPoints(false);
		
        VerticalFieldManager verticalFieldManager = new VerticalFieldManager(Field.FIELD_HCENTER);
        HorizontalFieldManager horizontalFieldManager2 = new HorizontalFieldManager(Field.FIELD_HCENTER);
        horizontalFieldManager2.add(tChart1);
        horizontalFieldManager2.add(tChart2);       
        verticalFieldManager.setBackground(BackgroundFactory.createSolidTransparentBackground(0x111111,180));
        horizontalFieldManager2.setBackground(BackgroundFactory.createSolidTransparentBackground(0x111111,180));
        verticalFieldManager.add(horizontalFieldManager2);
        verticalFieldManager.add(new SeparatorField());
        verticalFieldManager.add(tChart3);     
        horizontalFieldManager2.getExtent().width=0;
        add(verticalFieldManager);        
    }
}


