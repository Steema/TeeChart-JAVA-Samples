/*
 * OpenCloseDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.candle;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.CandleStyle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class OpenCloseDemo extends ChartSamplePanel
        implements ActionListener, ItemListener {

    private Candle candleSeries;

    /** Creates a new instance of SideAll */
    public OpenCloseDemo() {
        super();
        stickButton.addActionListener(this);
        barButton.addActionListener(this);
        openCloseButton.addActionListener(this);
        lineButton.addActionListener(this);
        view3DButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == stickButton) {
            candleSeries.setStyle(CandleStyle.CANDLESTICK);
        } else if (source == barButton) {
            candleSeries.setStyle(CandleStyle.CANDLEBAR);
        } else if (source == openCloseButton) {
            candleSeries.setStyle(CandleStyle.OPENCLOSE);
        } else if (source == lineButton) {
            candleSeries.setStyle(CandleStyle.LINE);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == view3DButton) {
            candleSeries.getPointer().setDraw3D(isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();
        candleSeries = new com.steema.teechart.styles.Candle(chart1.getChart());

        candleSeries.fillSampleValues(20);
        candleSeries.setStyle(CandleStyle.CANDLESTICK);
        candleSeries.getPointer().setDraw3D(true);

        view3DButton = new JCheckBox("3D");
        view3DButton.setSelected(chart1.getAspect().getView3D());

        stickButton = new JRadioButton("Stick");
        stickButton.setSelected(true);
        barButton = new JRadioButton("Bar");
        openCloseButton = new JRadioButton("Open Close");
        lineButton = new JRadioButton("Line");
        ButtonGroup group = new ButtonGroup();
        group.add(stickButton);
        group.add(barButton);
        group.add(openCloseButton);
        group.add(lineButton);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel groupPane = new JPanel();
        {
            groupPane.setBorder(BorderFactory.createTitledBorder("Candle style:"));
            groupPane.add(stickButton);
            groupPane.add(barButton);
            groupPane.add(openCloseButton);
            groupPane.add(lineButton);
        }

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(groupPane);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(view3DButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JRadioButton stickButton, barButton, openCloseButton, lineButton;
    private JCheckBox view3DButton;
}
