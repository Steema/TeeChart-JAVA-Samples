/*
 * ErrorDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.error;

import com.steema.teechart.legend.LegendSymbolSize;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.StringAlignment;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Error;
import com.steema.teechart.styles.ErrorStyle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;
import features.utils.EnumStrings;

/**
 *
 * @author tom
 */
public class ErrorDemo extends ChartSamplePanel
    implements ActionListener, ChangeListener {

    private Error series;

    /** Creates a new instance of ErrorDemo */
    public ErrorDemo() {
        super();
        editButton.addActionListener(this);
        styleList.addActionListener(this);
        sizeSlider.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeries(series);
        } else if (source == styleList) {
            switch (styleList.getSelectedIndex()) {
                case 0: series.setErrorStyle(ErrorStyle.LEFT); break;
                case 1: series.setErrorStyle(ErrorStyle.TOP); break;
                case 2: series.setErrorStyle(ErrorStyle.RIGHT); break;
                case 3: series.setErrorStyle(ErrorStyle.BOTTOM); break;
                case 4: series.setErrorStyle(ErrorStyle.LEFTRIGHT); break;
                case 5: series.setErrorStyle(ErrorStyle.TOPBOTTOM); break;
            }
        }
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int size = (int)source.getValue();
            series.setErrorWidth(size);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.getHeader().setAlignment(StringAlignment.NEAR);
        chart1.setText("Error Series");
        chart1.getLegend().getSymbol().setWidth(15);
        chart1.getLegend().getSymbol().setWidthUnits(LegendSymbolSize.PIXELS);
    }

    protected void initComponents() {
        super.initComponents();
        series = new Error(chart1.getChart());
        initSeries();
        editButton = new JButton("Edit...");
        styleList = new JComboBox(EnumStrings.ERROR_STYLES);
        styleList.setSelectedIndex(5);
        sizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 200, 100);
        sizeSlider.setValue(series.getErrorWidth());
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Style:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(styleList);
            tmpPane.add(tmpLabel);
            tmpPane.add(styleList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Size:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(sizeSlider);
            tmpPane.add(tmpLabel);
            tmpPane.add(sizeSlider);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void initSeries() {
        series.clear();
        /* Index Value Error Label Color */
        series.add(0, 50, 10, "A", Color.RED);
        series.add(1, 80, 20, "B", Color.YELLOW);
        series.add(2, 20,  8, "C", Color.GREEN);
        series.add(3, 60, 30, "D", Color.BLUE);
        series.add(4, 40,  5, "E", Color.WHITE);
    }

    private JButton editButton;
    private JComboBox styleList;
    private JSlider sizeSlider;
}
