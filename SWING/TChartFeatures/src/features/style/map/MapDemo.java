/*
 * MapDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.map;

import com.steema.teechart.BevelStyle;
import com.steema.teechart.axis.AxisLabelStyle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.events.SeriesMouseAdapter;
import com.steema.teechart.events.SeriesMouseEvent;
import com.steema.teechart.styles.Map;
import com.steema.teechart.styles.PaletteStyle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author tom
 */
public class MapDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private Map series;

    /** Creates a new instance of MapDemo */
    public MapDemo() {
        super();
        editButton.addActionListener(this);
        axesButton.addItemListener(this);
        marksButton.addItemListener(this);
        paletteButton.addItemListener(this);
    }

     public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editChart(chart1.getChart());
        };
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == axesButton) {
            chart1.getAxes().setVisible(isSelected);
        } else if (source == marksButton) {
            series.getMarks().setVisible(isSelected);
        } else if (source == paletteButton) {
            /* change the color palette style... */
            if (isSelected) {
                series.setUsePalette(true);
                series.setUseColorRange(false);
            } else {
                series.setUseColorRange(true);
            }
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.setText("Map Series");
        chart1.getHeader().setVisible(true);
        chart1.getFrame().setVisible(false);
        chart1.getAxes().getLeft().getLabels().setStyle(AxisLabelStyle.VALUE);
        chart1.getAspect().setView3D(false);
        chart1.addMouseMotionListener( new MouseMotionAdapter() {
             public void mouseMoved(MouseEvent e) {
                // which map polygon is under the mouse ?
                int tmp=-1;
                tmp = series.clicked(e.getX(), e.getY());
                if (tmp==-1) {
                    // none ?
                    shape.setVisible(false); // hide color shape
                } else {
                    // show color shape
                    shape.setBackground(series.getValueColor(tmp));
                    shape.setVisible(true);
                }
             }
        });
    }

    protected void initSeries() {
        series = new Map(chart1.getChart());
        addSampleShapes();

        /* Set the color palette "strong" */
        series.setPaletteStyle(PaletteStyle.STRONG);
        series.setUsePalette(true);
        series.setUseColorRange(false);

        /* Marks... */
        series.getMarks().setBevelInner(BevelStyle.RAISED);
        series.getMarks().setColor(Color.SILVER);
        series.getMarks().setVisible(true);

        /* click event */
        series.addSeriesMouseListener( new SeriesMouseAdapter() {
            public void seriesClicked(SeriesMouseEvent e) {
                DecimalFormat df = new DecimalFormat("#.##");
                JOptionPane.showMessageDialog(null,
                        "Shape: "
                        + series.getLabels().getString(e.getValueIndex())
                        + " Value: "
                        + df.format(series.getZValues().getValue(e.getValueIndex()))
                );
                /* stop zooming */
                chart1.getChart().setCancelMouse(true);
            };
        });
}

    protected void initComponents() {
        super.initComponents();

        initSeries();

        shape = new JLabel("     ");
        shape.setVisible(false);
        shape.setOpaque(true);
        shape.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        editButton = new JButton("Edit...");
        axesButton = new JCheckBox("Axes");
        axesButton.setSelected(chart1.getAxes().getVisible());
        marksButton = new JCheckBox("Marks");
        marksButton.setSelected(series.getMarks().getVisible());
        paletteButton = new JCheckBox("Palette");
        paletteButton.setSelected(series.getUsePalette());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(axesButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(marksButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(paletteButton);
            tmpPane.add(Box.createHorizontalStrut(30));
            tmpPane.add(shape);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void addSampleShapes() {
        series.fillSampleValues();
    }

    private JButton editButton;
    private JCheckBox axesButton, marksButton, paletteButton;
    private JLabel shape;
}
