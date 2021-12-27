/*
 * MapDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.map;

import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.BevelStyle;
import com.steema.teechart.axis.AxisLabelStyle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.events.SeriesMouseAdapter;
import com.steema.teechart.events.SeriesMouseEvent;
import com.steema.teechart.styles.Map;
import com.steema.teechart.styles.PaletteStyle;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class MapDemo extends ChartSample implements SelectionListener {

    private Map series;
    
	public MapDemo(Composite c) {
		super(c);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {
		Widget source = se.widget;		
        if (source == editButton) {
            //TODO ChartEditor.editChart(chart1.getChart());
        } else {
        	 boolean isSelected = ((Button)source).getSelection();
             if (source == axesButton) {
                 chart1.getAxes().setVisible(isSelected);
             } else if (source == marksButton) {
                 series.getMarks().setVisible(isSelected);
             } else if (source == paletteButton) {
                 /* change the color palette style... */
                 if (isSelected) {
                     series.setUsePalette(true);
                     series.setUseColorRange(false);
                 } else {
                     series.setUseColorRange(true);
                 }
             }        	
        }
	}
		
    protected void createContent() {
    	super.createContent();    	   	
    	editButton = addPushButton("Edit...", "Edit map series", this);    	    	
    	axesButton = addCheckButton("Axes", "", this);
    	marksButton = addCheckButton("Marks", "", this);
    	paletteButton = addCheckButton("Palette", "", this);
    	shape = addLabel(SWT.BORDER, "     ");      	
    }
    
    protected void initContent() {
    	super.initContent();    
        axesButton.setSelection(chart1.getAxes().getVisible());
        marksButton.setSelection(series.getMarks().getVisible());
        paletteButton.setSelection(series.getUsePalette());        
    	editButton.setEnabled(false);
    }
    
    protected void initChart() {
    	super.initChart();
        chart1.setText("Map Series");
        chart1.getHeader().setVisible(true);
        chart1.getFrame().setVisible(false);
        chart1.getAxes().getLeft().getLabels().setStyle(AxisLabelStyle.VALUE);
        chart1.getAspect().setView3D(false);
        //TODO
        /*
        chart1.addMouseMotionListener( new MouseMotionAdapter() {
             public void mouseMoved(MouseEvent e) {
                // which map polygon is under the mouse ?
                int tmp=-1;
                tmp = series.clicked(e.getX(), e.getY());
                if (tmp==-1) {
                    // none ?
                    shape.setVisible(false); // hide color shape
                } else {
                    // show color shape
                    shape.setBackground(series.getValueColor(tmp));
                    shape.setVisible(true);
                }
             }
        });
        */
        
        initSeries();

        shape.setVisible(false);
    }   	
    
    protected void initSeries() {
        series = new Map(chart1.getChart());
        addSampleShapes();

        /* Set the color palette "strong" */
        series.setPaletteStyle(PaletteStyle.STRONG);
        series.setUsePalette(true);
        series.setUseColorRange(false);

        /* Marks... */
        series.getMarks().setBevelInner(BevelStyle.RAISED);
        series.getMarks().setColor(Color.SILVER);
        series.getMarks().setVisible(true);

        /* click event */
        series.addSeriesMouseListener( new SeriesMouseAdapter() {
            public void seriesClicked(SeriesMouseEvent e) {
                DecimalFormat df = new DecimalFormat("#.##");
                JOptionPane.showMessageDialog(null,
                        "Shape: "
                        + series.getLabels().getString(e.getValueIndex())
                        + " Value: "
                        + df.format(series.getZValues().getValue(e.getValueIndex()))
                );
                /* stop zooming */
                chart1.getChart().setCancelMouse(true);
            };
        });
    };    
    
    private void addSampleShapes() {
        series.fillSampleValues();
    }    

    private Button editButton;
    private Button axesButton, marksButton, paletteButton;
    private Label shape;	
}
