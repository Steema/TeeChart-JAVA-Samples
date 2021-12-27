/*
 * CustomColorsDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.candle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.CandleStyle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import features.utils.EnumStrings;

/**
 *
 * @author tom
 */
public class CustomColorsDemo extends ChartSamplePanel
        implements ItemListener, ActionListener {

    private Candle candleSeries;

    /** Creates a new instance of AreaSeries */
    public CustomColorsDemo() {
        super();
        customColorsButton.addItemListener(this);
        candleStyleList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == candleStyleList) {
            CandleStyle candleStyle = CandleStyle.CANDLESTICK;
            switch (candleStyleList.getSelectedIndex()) {
                case 0: candleStyle = CandleStyle.CANDLESTICK; break;
                case 1: candleStyle = CandleStyle.CANDLEBAR; break;
                case 2: candleStyle = CandleStyle.OPENCLOSE; break;
                case 3: candleStyle = CandleStyle.LINE; break;
            }
            setCandleSeriesStyle(candleStyle);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == customColorsButton) {
            if (isSelected) {
                setCustomColors();
            } else {
                for(int i=0; i < candleSeries.getCount(); i++) {
                    candleSeries.getColors().setColor(i, Color.EMPTY);
                };
                candleSeries.repaint();
            }
        }
    }

    protected void initComponents() {
        super.initComponents();

        candleSeries = new com.steema.teechart.styles.Candle(chart1.getChart());
        candleSeries.fillSampleValues(30);

        customColorsButton = new JCheckBox("Custom colors");
        customColorsButton.setSelected(true);

        candleStyleList = new JComboBox(EnumStrings.CANDLE_STYLES);
        candleStyleList.setSelectedIndex(0);
        setCandleSeriesStyle(CandleStyle.CANDLESTICK);

        setCustomColors();
    }

    protected void initGUI() {
        super.initGUI();

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(customColorsButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Style:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(candleStyleList);
            tmpPane.add(tmpLabel);
            tmpPane.add(candleStyleList);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void setCustomColors() {
        /* some custom colors... */
        candleSeries.getColors().setColor(11, Color.YELLOW);
        candleSeries.getColors().setColor(15, Color.LIME);
        candleSeries.getColors().setColor(16, Color.BLUE);
        candleSeries.repaint();
    }

    private void setCandleSeriesStyle(CandleStyle candleStyle) {
        candleSeries.setStyle(candleStyle);
    }

    private JComboBox candleStyleList;
    private JCheckBox customColorsButton;
}
