/*
 * LineInterpolateDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.line;

import com.steema.teechart.Rectangle;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.drawing.IGraphics3D;
import com.steema.teechart.events.ChartDrawEvent;
import com.steema.teechart.events.ChartPaintAdapter;
import com.steema.teechart.styles.Custom;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.themes.ColorPalettes;
import com.steema.teechart.themes.Theme;
import com.steema.teechart.tools.CursorTool;
import com.steema.teechart.tools.CursorTool.CursorEvent;
import com.steema.teechart.tools.CursorTool.CursorListener;
import com.steema.teechart.tools.CursorToolStyle;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author narcis
 */
public class LineInterpolateDemo extends ChartSamplePanel{

    private Line lineSeries1;
    private Line lineSeries2;
    private Line lineSeries3;

    private JCheckBox markersButton;
    private CursorTool cursorTool1;
    
    private double xval;

    /**
     * Creates a new instance of RealtimeDemo
     */
    public LineInterpolateDemo() {
        super();
    }    

    private final static NumberFormat formatter = new DecimalFormat("0.00");
    
    protected void initComponents() {
        super.initComponents();

        ColorPalettes.applyPalette(chart1.getChart(), Theme.DefaultPalette);
        
        lineSeries1 = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries2 = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries3 = new com.steema.teechart.styles.Line(chart1.getChart());        
                
        lineSeries1.fillSampleValues(20);
        lineSeries2.fillSampleValues(20);
        lineSeries3.fillSampleValues(20);
        
        lineSeries1.getPointer().setVisible(true);
        lineSeries2.getPointer().setVisible(true);
        lineSeries3.getPointer().setVisible(true);
        
        lineSeries1.getPointer().setStyle(PointerStyle.RECTANGLE);
        lineSeries2.getPointer().setStyle(PointerStyle.CIRCLE);
        lineSeries3.getPointer().setStyle(PointerStyle.TRIANGLE);
        
        cursorTool1 = new CursorTool(chart1.getChart());
        cursorTool1.setFollowMouse(true);        
        cursorTool1.setStyle(CursorToolStyle.VERTICAL);
        cursorTool1.getPen().setStyle(DashStyle.DASH);
        
        cursorTool1.addCursorListener( new CursorListener() {
            public void cursorMoved(CursorEvent e) {
                xval = e.getXValue();
                
                chart1.getHeader().setText("");
                
                for (int i=0; i< chart1.getSeriesCount(); i++){
                    if (chart1.getSeries(i) instanceof Custom) {                    
                        String tmpStr = chart1.getHeader().getText();
                        tmpStr = tmpStr + chart1.getSeries(i).toString() + ": Y("+formatter.format(e.getXValue()) + ") = ";
                        tmpStr = tmpStr + formatter.format(InterpolateLineSeries(((Custom)chart1.getSeries(i)),e.getXValue())) + "   \r\n";

                        chart1.getHeader().setText(tmpStr);
                    }
                }
            }
        });
        
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(true);
        
        chart1.addChartPaintListener( new ChartPaintAdapter() {
            public void chartPainted(ChartDrawEvent pce) {
                IGraphics3D g = chart1.getGraphics3D();
                
                if(markersButton.isSelected()) {
                    int xs = chart1.getAxes().getBottom().calcXPosValue(xval);
                    int ys;
                    
                    g.getBrush().setVisible(true);
                    g.getBrush().setSolid(true);
                    
                    for (int i=0; i<chart1.getSeriesCount(); i++)
                        if (chart1.getSeries(i) instanceof Custom){
                            ys = chart1.getSeries(i).getVertAxis().calcYPosValue(InterpolateLineSeries((Custom)chart1.getSeries(i), xval));
                            
                            g.getBrush().setColor(chart1.getSeries(i).getColor());
                            g.ellipse(new Rectangle(xs-4,ys-4,8,8));                                              
                        }
                }
            };
        });
        
        markersButton = new JCheckBox("Show markers");
        markersButton.setSelected(true);        
    }
    
    private double InterpolateLineSeries(com.steema.teechart.styles.Custom series,double xvalue)
    { 
      return InterpolateLineSeries(series,series.getFirstVisible(),series.getLastVisible(),xvalue);
    }
    
    // Calculate y=y(x) for arbitrary x. Works fine only for line series with ordered x values.
    // <returns>y=y(xvalue) where xvalue is arbitrary x value.</returns>
    private double InterpolateLineSeries(com.steema.teechart.styles.Custom series, int firstindex, int lastindex, double xvalue)
    {
        int index;
        
        for (index=firstindex; index<=lastindex; index++) {
            if ((index == -1) || (series.getXValues().getValue(index)>xvalue)) break;
        }

        // safeguard
        if (index<1) index = 1;
        else if (index>=series.getCount()) index = series.getCount() -1;
        
        // y=(y2-y1)/(x2-x1)*(x-x1)+y1        
        double dx = series.getXValues().getValue(index) - series.getXValues().getValue(index-1);
        double dy = series.getYValues().getValue(index) - series.getYValues().getValue(index-1);
        if (dx!=0.0) return dy*(xvalue - series.getXValues().getValue(index-1))/dx + series.getYValues().getValue(index-1);
        else return 0.0;
    }    

    protected void initGUI() {
        super.initGUI();

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(markersButton);            
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
}
