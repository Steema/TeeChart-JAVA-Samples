package TeeChart.Features;

import com.steema.teechart.TChart;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.misc.Utils;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Series;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;


/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */
public final class Home extends MainScreen implements FieldChangeListener
{
	TChart chart;
	
    private String[] _chartStyles;
    private ObjectChoiceField _stylesChoiceField; 
    private CheckboxField _view3DField;
    private CheckboxField _legendField;
    private CheckboxField _headerField;
    
    private void AddChartStyles()
    {
    	_chartStyles = new String[com.steema.teechart.misc.Utils.SERIESTYPESCOUNT];
    	
        for (int t = 0; t <  com.steema.teechart.misc.Utils.SERIESTYPESCOUNT; t++) { 
        	String tmpSt = com.steema.teechart.misc.Utils.seriesTypesOf[t].getName();
        	int stPos = tmpSt.lastIndexOf('.');
        	   _chartStyles[t]=tmpSt.substring(stPos+1,tmpSt.length());
        }
    	
        // Initialize choice field for font selection and add to screen
    	_stylesChoiceField = new ObjectChoiceField("Style:", _chartStyles, 0,  Field.FIELD_HCENTER); 
    	
    	_stylesChoiceField.setEditable(true);        
    	_stylesChoiceField.setChangeListener(this);
    }
    
    private void AddView3D()
    {
        _view3DField = new CheckboxField("3D ", true,_view3DField.NO_USE_ALL_WIDTH);
        _view3DField.setChangeListener(this);   	
    }

    private void AddLegendCheck()
    {
        _legendField = new CheckboxField("Legend ", true,_view3DField.NO_USE_ALL_WIDTH);
        _legendField.setChangeListener(this);
    }

    private void AddHeaderCheck()
    {
        _headerField = new CheckboxField("Header ", true);
        _headerField.setChangeListener(this);
    }
    
    private void ApplyChartSettings()
    {
    	chart.removeAllSeries();
        chart.getPanel().getGradient().setStartColor(Color.WHITE);
        chart.getPanel().getGradient().setEndColor(new Color(188,200,254));
        chart.getPanel().getGradient().setVisible(true);
        chart.getHeader().setVisible(_headerField.getChecked());
        chart.getLegend().setVisible(_legendField.getChecked());
        chart.getAspect().setView3D(_view3DField.getChecked());
        chart.getLegend().setAlignment(LegendAlignment.RIGHT);
        
        chart.setHeight(chart.getHeight()-75);
    }
    
    /**
     * Lays out the UI elements on the screen
     */
    private void createLayout()
    {
        VerticalFieldManager verticalFieldManager = new VerticalFieldManager(Field.FIELD_HCENTER);
        HorizontalFieldManager horizontalFieldManager2 = new HorizontalFieldManager(Field.FIELD_HCENTER);
        horizontalFieldManager2.add(_stylesChoiceField);
        horizontalFieldManager2.add(_view3DField);
        horizontalFieldManager2.add(_legendField);
        horizontalFieldManager2.add(_headerField);
        verticalFieldManager.add(horizontalFieldManager2);
        verticalFieldManager.add(new SeparatorField());        
        verticalFieldManager.add(chart);               
        add(verticalFieldManager);
    }    
    /**
     * Creates a new StartScreen object
     */
    public Home()
    {    
    	// Set the displayed title of the screen       
        setTitle("TeeChart Features");

        AddChartStyles();
        AddView3D();
        AddLegendCheck();
        AddHeaderCheck();

        chart = new TChart();
        
        // Set Chart Settings
        ApplyChartSettings();
        DoLineSeries();  
        
    	createLayout();    	
    }
    
    /**
     * @see FieldChangeListener#fieldChanged(Field, int)
     */
    public void fieldChanged(Field field, int context)
    {            
        if (field instanceof ObjectChoiceField)
        {
        	Series tmpClass;
        	
            // Get the chart style selected in the ObjectChoiceField 
            int selectedIndex = ((ObjectChoiceField)field).getSelectedIndex();                    
            chart.removeAllSeries();
            ApplyChartSettings();
            try {
				tmpClass=Series.newFromType(Utils.seriesTypesOf[selectedIndex]);
				
				if (tmpClass.getClass() == com.steema.teechart.styles.Bar.class)
				{
					DoBarSeries();
				}
				else
				{					
					chart.addSeries(tmpClass);
					tmpClass.fillSampleValues();
					if ((tmpClass.getClass() == com.steema.teechart.styles.Pie.class) || 
							(tmpClass.getClass() == com.steema.teechart.styles.Pie.class) )
					{
						chart.getLegend().setVisible(false);
					}					
				}
				
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        if (field instanceof CheckboxField)
        {        	
        	chart.getAspect().setView3D(_view3DField.getChecked());
        	chart.getLegend().setVisible(_legendField.getChecked());
        	chart.getHeader().setVisible(_headerField.getChecked());
        }
    }   
    
    private void DoLineSeries()
    {
        Line l = new Line();
        l.fillSampleValues();
        chart.addSeries(l);
    }
    
    private void DoBarSeries()
    {
        chart.removeAllSeries();    	
        Bar series1 = new Bar();
        series1.setColor(new Color(236,63,19));
        series1.getPen().setVisible(false);
        series1.getMarks().setVisible(false);
        
        Bar series2 = new Bar();
        series2.getPen().setVisible(false);
        series2.setColor(new Color(128,128,255));
        series2.getMarks().setVisible(false);
        
        //series1.setColorEach(true);
        chart.getChart().addSeries(series1);      
        chart.getSeries(0).fillSampleValues(12);
        chart.getChart().addSeries(series2);      
        chart.getSeries(1).fillSampleValues(12);   
        
        chart.getLegend().setTransparent(true);        
        chart.getLegend().setAlignment(LegendAlignment.BOTTOM);     
//        chart.getAxes().getLeft().getTitle().setText("Left Axis");
    }
}
