/*
 * CompressionOHLCDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.function;

import com.steema.teechart.functions.CompressOHLC;
import com.steema.teechart.functions.CompressionPeriod;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.CandleStyle;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import features.ChartSamplePanel;

/**
 *
 * @author Marjan
 */
public class CompressionOHLCDemo extends ChartSamplePanel
    implements ActionListener {

    private Candle candle1,candle2;
    private CompressOHLC compress;
    
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == periodComboBox) {
            candle1.setVisible(false);
            candle2.setVisible(true);
            CompressionPeriod period = CompressionPeriod.DAY; 
            switch (periodComboBox.getSelectedIndex())
            {
                case 0 : {
                    period = CompressionPeriod.DAY; 
                    candle1.setVisible(true);
                    candle2.setVisible(false);
                }; break;
                case 1 : period = CompressionPeriod.WEEK; break;
                case 2 : period = CompressionPeriod.MONTH; break;
                case 3 : period = CompressionPeriod.BIMONTH; break;
                case 4 : period = CompressionPeriod.QUARTER; break;
                case 5 : period = CompressionPeriod.YEAR; break;
            }
            compress.setCompress(period);     
        }
        else if (source==candleComboBox)
        {
            CandleStyle style;
            switch (candleComboBox.getSelectedIndex())
            {
                case 1 : style = CandleStyle.CANDLEBAR; break;
                case 2 : style = CandleStyle.OPENCLOSE; break;
                default : style = CandleStyle.CANDLESTICK; break;
            }
            
            candle1.setStyle(style);
            candle2.setStyle(style);
        }
    }
    
    /**
     * Creates a new instance of CCIDemo
     */
    public CompressionOHLCDemo() {
        super();
        periodComboBox.addActionListener(this);
        candleComboBox.addActionListener(this);
    }

    
    protected void initComponents() {
        super.initComponents();

        candle1 = new Candle(chart1.getChart());
        candle1.fillSampleValues(100);
        
        compress = new CompressOHLC(chart1.getChart());
        compress.setCompress(CompressionPeriod.DAY);

        candle2 = new Candle(chart1.getChart());
        candle2.setActive(false);
        candle2.setFunction(compress);
        candle2.setDataSource(candle1);
        
        periodComboBox = new JComboBox(CompressionPeriodStrings);
        periodComboBox.setSelectedIndex(0);
        candleComboBox = new JComboBox(CandleStyleStrings);
        candleComboBox.setSelectedIndex(0);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("Candle Compression Example");
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Compression Period:");
            tmpLabel.setDisplayedMnemonic('C');
            tmpLabel.setLabelFor(periodComboBox);
            tmpPane.add(tmpLabel);
            tmpPane.add(periodComboBox);
            tmpPane.add(Box.createHorizontalStrut(10));

            tmpLabel = new JLabel("Candle Style:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(candleComboBox);
            tmpPane.add(tmpLabel);
            tmpPane.add(candleComboBox);
            
            tmpPane.add(Box.createHorizontalGlue());
        }        
    }
    
    private JComboBox periodComboBox,candleComboBox;
    private static final String[] CompressionPeriodStrings = {"Daily","Weekly","Monthly","Bi-Monthly","Quarterly","Yearly"};
    private static final String[] CandleStyleStrings = {"Stick","Bar","Open/Close"};

}
