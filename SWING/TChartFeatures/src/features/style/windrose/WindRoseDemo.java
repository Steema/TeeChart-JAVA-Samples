/*
 * WindRoseDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.windrose;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.WindRose;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class WindRoseDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private WindRose series1, series2;

    /** Creates a new instance of WindRoseDemo */
    public WindRoseDemo() {
        super();
        visibleButton.addItemListener(this);
        circledButton.addItemListener(this);
        editButton.addActionListener(this);
        anglesList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == anglesList) {
            switch (anglesList.getSelectedIndex()) {
                case 0: series1.setAngleIncrement(15); break;
                case 1: series1.setAngleIncrement(30); break;
                case 2: series1.setAngleIncrement(45); break;
                case 3: series1.setAngleIncrement(90); break;
            }
        } else if (source == editButton) {
            ChartEditor.editSeries(series1);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == visibleButton) {
            chart1.getAxes().setVisible(isSelected);
        } else if (source == circledButton) {
            series1.setCircled(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Wind-Rose series");
        chart1.getAxes().getBottom().setIncrement(10.0);
        chart1.getLegend().setVisible(false);
        chart1.getPanel().setColor(Color.GRAY);
    }

    protected void initComponents() {
        super.initComponents();
        series1 = new WindRose(chart1.getChart());
        series1.clear();
        series1.add( 30, 100, "", Color.EMPTY );
        series1.add( 60, 200, "", Color.EMPTY );
        series1.add( 90,  50, "", Color.EMPTY );
        series1.add( 120, 150, "", Color.EMPTY );
        series1.setCircled(true);
        series1.setAngleIncrement(15);
        series1.getBrush().setVisible(false);
        series1.getMarks().setVisible(false);
        series1.setCircleBackColor(Color.SILVER);
        series1.getCircleLabelsFont().setColor(Color.YELLOW);
        series1.getCircleLabelsFont().setBold(true);
        series1.getPointer().getBrush().setColor(Color.BLUE);
        series1.getPointer().setHorizSize(2);
        series1.getPointer().setVertSize(2);
        series1.getPointer().setVisible(true);

        series2 = new WindRose(chart1.getChart());
        series2.clear();
        series2.add( 130, 100, "", Color.EMPTY );
        series2.add( 160, 200, "", Color.EMPTY );
        series2.add( 190,  50, "", Color.EMPTY );
        series2.add( 220, 150, "", Color.EMPTY );
        series2.setAngleIncrement(10);
        series2.getBrush().setVisible(false);
        series2.getMarks().setVisible(false);
        series2.getPointer().setStyle(PointerStyle.RECTANGLE);
        series2.getPointer().setHorizSize(3);
        series2.getPointer().setVertSize(3);
        series2.getPointer().setVisible(true);

        editButton = new JButton("Edit...");
        anglesList = new JComboBox(new String[] {"15", "30", "45", "90"});
        anglesList.setSelectedIndex(0);
        visibleButton = new JCheckBox("Axes");
        visibleButton.setSelected(chart1.getAxes().getVisible());
        circledButton = new JCheckBox("Circled");
        circledButton.setSelected(series1.getCircled());
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(visibleButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Labels:");
            tmpLabel.setDisplayedMnemonic('L');
            tmpLabel.setLabelFor(anglesList);
            tmpPane.add(tmpLabel);
            tmpPane.add(anglesList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(circledButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JComboBox anglesList;
    private JCheckBox visibleButton;
    private JCheckBox circledButton;
}
