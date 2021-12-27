/*
 * AxisLabelsDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.candle;

import com.steema.teechart.DateTime;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.StringList;
import java.util.Random;
import features.ChartSamplePanel;
import java.util.Calendar;

/**
 *
 * @author tom
 */
public class AxisLabelsDemo extends ChartSamplePanel {

    private Candle candleSeries;

    /**
     * Creates a new instance of AxisLabelsDemo
     */
    public AxisLabelsDemo() {
        super();
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getAspect().setSmoothingMode(true);
        chart1.getLegend().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();

        candleSeries = new com.steema.teechart.styles.Candle(chart1.getChart());

        StringList labels = new StringList(10);
        
         
        labels.add("08/05/2008");       
        labels.add("09/05/2008");
        labels.add("12/05/2008");
        labels.add("13/05/2008");
        labels.add("14/05/2008");
        labels.add("15/05/2008");
        labels.add("16/05/2008");
        labels.add("19/05/2008");
        labels.add("20/05/2008");
        labels.add("21/05/2008");        
        
        /* no dates */
        candleSeries.getXValues().setDateTime(false);
        chart1.getAxes().getBottom().getLabels().setAngle(90);

        /* fill the candle with random points */
        Random generator = new Random();

        double tmpOpen, tmpClose;
        tmpOpen = generator.nextDouble()*1000;

        DateTime dt = new DateTime(2008, 5, 8);
        int count = 0;
        
        for (int t=0; t < 13; t++) {
            tmpOpen  = tmpOpen + generator.nextDouble()*100 - 50;
            tmpClose = tmpOpen - generator.nextDouble()*100 + 50;
                        
            if ((dt.getDayOfWeek() != Calendar.SATURDAY) && 
                    (dt.getDayOfWeek() != Calendar.SUNDAY)) {
                ++count;

                /* add the point */
                candleSeries.add(
                        count,
                        tmpOpen,
                        tmpOpen + generator.nextDouble()*10,
                        tmpClose - generator.nextDouble()*10,
                        tmpClose
                        );
            }
            
            dt.add(7, 1); 
            
            candleSeries.setLabels(labels);
        }
    }
}
