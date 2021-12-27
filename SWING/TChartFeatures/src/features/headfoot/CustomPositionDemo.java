/*
 * CustomPositionDemo.java
 *
 * <p>Copyright: (c) 2004-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.headfoot;

import com.steema.teechart.Dimension;
import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.drawing.ChartFont;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Pie;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class CustomPositionDemo extends ChartSamplePanel
        implements ChangeListener, ItemListener {

    /** Creates a new instance of CustomPositionDemo */
    public CustomPositionDemo() {
        super();
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent evt) {
                leftSlider.setMaximum(chart1.getWidth());
                topSlider.setMaximum(chart1.getHeight());
                leftSlider.setValue(chart1.getHeader().getLeft());
                topSlider.setValue(chart1.getHeader().getTop());
            }
        });

        customPosButton.addItemListener(this);
        leftSlider.addChangeListener(this);
        topSlider.addChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int pos = (int)source.getValue();
            if (source == leftSlider) {
                chart1.getHeader().setLeft(pos);
            } else if (source == topSlider) {
                chart1.getHeader().setTop(pos);
            }
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == customPosButton) {
            chart1.getHeader().setCustomPosition(isSelected);
            chart1.getHeader().setLeft(leftSlider.getValue());
            chart1.getHeader().setTop(topSlider.getValue());
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(true);
        chart1.getHeader().setText("This is the Title");
        chart1.getHeader().setTransparent(false);
        chart1.getHeader().setCustomPosition(true);
        chart1.getHeader().setLeft(123);
        chart1.getHeader().setTop(9);
        chart1.getHeader().setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        ChartFont font = chart1.getHeader().getFont();
        font.setBold(true);
        font.setColor(Color.BLACK);
        font.getShadow().setSize(new Dimension(4,2));
        font.getShadow().setColor(Color.DARK_GRAY);
    }

    protected void initComponents() {
        super.initComponents();
        Pie tmpSeries = new Pie(chart1.getChart());
        tmpSeries.fillSampleValues(7);

        customPosButton = new JCheckBox("Custom position");
        customPosButton.setSelected(chart1.getHeader().getCustomPosition());
        leftSlider = new JSlider(JSlider.HORIZONTAL, 0, 0, 0);
        topSlider = new JSlider(JSlider.HORIZONTAL, 0, 0, 0);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(customPosButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Left:");
            tmpLabel.setDisplayedMnemonic('L');
            tmpLabel.setLabelFor(leftSlider);
            tmpPane.add(tmpLabel);
            tmpPane.add(leftSlider);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Top:");
            tmpLabel.setDisplayedMnemonic('T');
            tmpLabel.setLabelFor(topSlider);
            tmpPane.add(tmpLabel);
            tmpPane.add(topSlider);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox customPosButton;
    private JSlider leftSlider, topSlider;
}
