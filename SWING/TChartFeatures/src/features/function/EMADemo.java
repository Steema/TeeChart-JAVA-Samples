/*
 * EMADemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.ExpMovAverage;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.Line;
import com.steema.teechart.languages.Language;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.Timer;


import features.ChartSamplePanel;

/**
 *
 * @author Marjan
 */
public class EMADemo extends ChartSamplePanel
    implements ActionListener, ChangeListener, ItemListener {

    private Candle candleS;
    private  ExpMovAverage sFunction;
    private int delta = 1;
    
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == RSIComboBox) {
            String valuesrc = RSIComboBox.getSelectedItem().toString();
            switch (RSIComboBox.getSelectedIndex()) {
                case 0: valuesrc = Language.getString("ValuesOpen"); break;
                case 1: valuesrc = Language.getString("ValuesHigh"); break;
                case 2: valuesrc = Language.getString("ValuesLow"); break;
                case 3: valuesrc = Language.getString("ValuesClose"); break;
            }
            sFunction.getSeries().getYValues().setDataMember(valuesrc);
        }
    }
    
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == animateButton) {
            if (isSelected) {
                timer.start();
            } else {
                timer.stop();
            }
        };
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
    public EMADemo() {
        super();
        animateButton.addItemListener(this);
        RSIComboBox.addActionListener(this);
        periodSpinner.addChangeListener(this);
    }

    
    protected void initComponents() {
        super.initComponents();

        candleS = new com.steema.teechart.styles.Candle(chart1.getChart());
        candleS.setTitle("Candle");
        candleS.getVertAxis().getTitle().setCaption("Candle");
        candleS.fillSampleValues(40);
        
        sFunction = new ExpMovAverage(chart1.getChart());
        sFunction.setPeriod(10);

        Line functionSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        functionSeries.getPointer().setVisible(true);
        functionSeries.getPointer().setHorizSize(2);
        functionSeries.getPointer().setVertSize(2);
        functionSeries.getPointer().getBrush().setColor(Color.WHITE);
        functionSeries.getPointer().getBrush().setVisible(true);
        functionSeries.getPointer().getPen().setColor(Color.RED);
        functionSeries.setTitle("Exponential MA");
        functionSeries.setColor(Color.GREEN);
        functionSeries.setDataSource(candleS);
        functionSeries.getYValues().setDataMember(Language.getString("ValuesClose"));
        functionSeries.setFunction(sFunction);
        
        RSIComboBox = new JComboBox(RSIstyleStrings);
        RSIComboBox.setSelectedIndex(3);
        
        SpinnerModel tmpModel;
        tmpModel = new SpinnerNumberModel(
                10,
                1,
                100,
                1);
        periodSpinner = new JSpinner(tmpModel);
        
        animateButton = new JCheckBox("Animate");
        animateButton.setSelected(false);

        
        //Create a timer.
        timer = new Timer(ONE_MILLISECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                /* stop timer */
                timer.stop();
                SpinnerModel tmpModel = periodSpinner.getModel();
                int pos = ((SpinnerNumberModel)tmpModel).getNumber().intValue();

                pos += delta;
                periodSpinner.setValue(new Double(pos));
                if ((pos < 2) || (pos > 19)) {
                    delta = -delta;
                }
               
                /* re-enable timer again */
                timer.start();
            }
        });
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("Exponential Moving Average Example");
        chart1.getAspect().setView3D(false);

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(animateButton);
            tmpPane.add(Box.createHorizontalStrut(10));
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
    private JCheckBox animateButton;
    private Timer timer;

    private static final String[] RSIstyleStrings = { "Open", "High", "Low", "Close" };
    private final static int ONE_MILLISECOND = 1;

}
