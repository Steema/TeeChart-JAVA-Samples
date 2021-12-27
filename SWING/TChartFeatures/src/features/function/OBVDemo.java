/*
 * ONVDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.OBV;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.Volume;
import com.steema.teechart.styles.Line;
import com.steema.teechart.axis.Axis;

import features.ChartSamplePanel;

/**
 *
 * @author Marjan
 */
public class OBVDemo extends ChartSamplePanel {

    private Candle candleS;
    private Volume volumeS;
    private OBV sFunction;

    /**
     * Creates a new instance of CCIDemo
     */
    public OBVDemo() {
        super();
    }

    
    protected void initComponents() {
        super.initComponents();

        candleS = new com.steema.teechart.styles.Candle(chart1.getChart());
        candleS.getMarks().setVisible(false);
        candleS.getPointer().setVisible(true);
        candleS.setTitle("Candle");
        candleS.getVertAxis().setStartPosition(0);
        candleS.getVertAxis().setEndPosition(40);
        candleS.getVertAxis().getTitle().setCaption("Candle");
        candleS.fillSampleValues(50);
        
        Axis tmpAxis;
        tmpAxis = chart1.getAxes().getCustom().getNew();
        tmpAxis.setHorizontal(false);
        tmpAxis.setOtherSide(false);
        tmpAxis.setStartPosition(50.0);
        tmpAxis.setEndPosition(70);
        tmpAxis.getTitle().setText("Volume");
        tmpAxis.getTitle().setAngle(90);
        volumeS = new com.steema.teechart.styles.Volume(chart1.getChart());
        volumeS.getPointer().setVisible(true);
        volumeS.setTitle("Volume");
        volumeS.setDataSource(candleS);
        volumeS.setCustomVertAxis(tmpAxis);

        sFunction = new com.steema.teechart.functions.OBV(chart1.getChart());
        sFunction.setVolume(volumeS);

        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setDataSource(candleS);
        functionSeries.setFunction(sFunction);
        functionSeries.setTitle("OBV");
        functionSeries.setColor(Color.GREEN);
        tmpAxis = chart1.getAxes().getCustom().getNew();
        tmpAxis.getTitle().setText("OBV");
        tmpAxis.getTitle().setAngle(90);
        tmpAxis.setHorizontal(false);
        tmpAxis.setOtherSide(false);
        tmpAxis.setStartPosition(75.0);
        tmpAxis.setEndPosition(100);
        functionSeries.setCustomVertAxis(tmpAxis);

    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("OBV Function Example");
        chart1.getAspect().setView3D(false);

    }

}