/*
 * RSIDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.RSI;
import com.steema.teechart.functions.RSIStyle;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.Line;
import com.steema.teechart.axis.Axis;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import features.ChartSamplePanel;

/**
 *
 * @author Marjan
 */
public class RSIDemo extends ChartSamplePanel
    implements ActionListener, ChangeListener {

    private Candle candleS;
    private RSI sFunction;
    
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == RSIComboBox) {
            RSIStyle rsistyle;
            if (RSIComboBox.getSelectedIndex()==0) rsistyle = RSIStyle.OPENCLOSE;
            else rsistyle = RSIStyle.CLOSE;
            sFunction.SetRSIStyle(rsistyle);
        }
    }
    
    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source instanceof JSpinner) {
            SpinnerModel tmpModel = ((JSpinner)source).getModel();
            if (tmpModel instanceof SpinnerNumberModel) {
                int period = ((SpinnerNumberModel)tmpModel).getNumber().intValue();
                if ((period > 0) && (period < 101)) {
                    if (source == periodSpinner) {
                        sFunction.setPeriod(period);
                    }
                }
            }
        }
    }

    
    /**
     * Creates a new instance of CCIDemo
     */
    public RSIDemo() {
        super();
        RSIComboBox.addActionListener(this);
        periodSpinner.addChangeListener(this);
    }

    
    protected void initComponents() {
        super.initComponents();

        candleS = new com.steema.teechart.styles.Candle(chart1.getChart());
        candleS.getMarks().setVisible(false);
        candleS.getPointer().setVisible(true);
        candleS.setTitle("Candle");
        candleS.getVertAxis().setStartPosition(0);
        candleS.getVertAxis().setEndPosition(50);
        candleS.getVertAxis().getTitle().setCaption("Candle");
        candleS.fillSampleValues(50);
        
        sFunction = new com.steema.teechart.functions.RSI(chart1.getChart());
        sFunction.setPeriod(10);

        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.setTitle("RSI");
        functionSeries.setColor(Color.GREEN);
        Axis tmpAxis = chart1.getAxes().getCustom().getNew();
        tmpAxis.getTitle().setText("RSI");
        tmpAxis.getTitle().setAngle(90);
        tmpAxis.setHorizontal(false);
        tmpAxis.setOtherSide(false);
        tmpAxis.setStartPosition(60);
        tmpAxis.setEndPosition(100);
        functionSeries.setCustomVertAxis(tmpAxis);
        functionSeries.setDataSource(candleS);
        functionSeries.setFunction(sFunction);
        
        RSIComboBox = new JComboBox(RSIstyleStrings);
        RSIComboBox.setSelectedIndex(0);
        
        SpinnerModel tmpModel;
        tmpModel = new SpinnerNumberModel(
                10,
                1,
                100,
                1);
        periodSpinner = new JSpinner(tmpModel);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("RSI Function Example");
        chart1.getAspect().setView3D(false);

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Style:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(RSIComboBox);
            tmpPane.add(tmpLabel);
            tmpPane.add(RSIComboBox);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Period:");
            tmpLabel.setDisplayedMnemonic('P');
            tmpLabel.setLabelFor(periodSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(periodSpinner);
            tmpPane.add(Box.createHorizontalGlue());
        }        
    }
    
    private JComboBox RSIComboBox;
    private JSpinner periodSpinner;
    private static final String[] RSIstyleStrings = { "OpenClose", "Close" };

}
