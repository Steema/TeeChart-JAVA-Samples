/*
 * CLVDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.CLV;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.Volume;
import com.steema.teechart.styles.Line;
import com.steema.teechart.axis.Axis;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;



import features.ChartSamplePanel;

/**
 *
 * @author Marjan
 */
public class CLVDemo extends ChartSamplePanel 
    implements ItemListener {

    private Candle candleS;
    private Volume volumeS;
    private CLV sFunction;

    /**
     * Creates a new instance of CCIDemo
     */
    public CLVDemo() {
        super();
        accCheckBox.addItemListener(this);
        multiplyCheckBox.addItemListener(this);
    }
    
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == accCheckBox) {
            sFunction.setAccumulate(isSelected);
        }
        else if (source == multiplyCheckBox)
        {
            if (isSelected) sFunction.setVolume(volumeS);
            else sFunction.setVolume(null);
        }
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

        sFunction = new com.steema.teechart.functions.CLV(chart1.getChart());
        sFunction.setAccumulate(true);
        sFunction.setVolume(volumeS);

        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setDataSource(candleS);
        functionSeries.setFunction(sFunction);
        functionSeries.setTitle("CLV");
        functionSeries.setColor(Color.GREEN);
        tmpAxis = chart1.getAxes().getCustom().getNew();
        tmpAxis.getTitle().setText("CLV");
        tmpAxis.getTitle().setAngle(90);
        tmpAxis.setHorizontal(false);
        tmpAxis.setOtherSide(false);
        tmpAxis.setStartPosition(75.0);
        tmpAxis.setEndPosition(100);
        functionSeries.setCustomVertAxis(tmpAxis);
        
        accCheckBox = new JCheckBox("Accumulate");
        accCheckBox.setSelected(true);

        multiplyCheckBox = new JCheckBox("Multiply");
        multiplyCheckBox.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("CLV Function Example");
        chart1.getAspect().setView3D(false);
        
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(accCheckBox);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(multiplyCheckBox);
            tmpPane.add(Box.createHorizontalGlue());
        }

    }
    
    private JCheckBox accCheckBox, multiplyCheckBox;
    
}