/*
 * DragMarksDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;
import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.DialogFactory;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Points;
import com.steema.teechart.styles.SeriesMarks;
import com.steema.teechart.tools.DragMarks;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;


/**
 *
 * @author tom
 */
public class DragMarksDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private DragMarks tool1;

    /**
     * Creates a new instance of DragMarksDemo
     */
    public DragMarksDemo() {
        super();
        editButton.addActionListener(this);
        resetButton.addActionListener(this);
        activeButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == resetButton) {
            for (int i=0; i < chart1.getSeriesCount(); i++) {
                chart1.getSeries(i).getMarks().resetPositions();
            }
        } else if (source == editButton) {
            DialogFactory.showModal(tool1);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == activeButton) {
            tool1.setActive(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();

        tool1 = new DragMarks(chart1.getChart());

        Points series1 = new Points(chart1.getChart());
        series1.fillSampleValues(10);
        series1.getMarks().setVisible(true);
        series1.getMarks().getCallout().setLength(10);

        Line series2 = new Line(chart1.getChart());
        series2.fillSampleValues(6);
        series2.getPointer().setVisible(false);
        SeriesMarks tmpMarks = series2.getMarks();

        tmpMarks.setBackColor(Color.SILVER);
        tmpMarks.setColor(Color.SILVER);
        tmpMarks.getFont().setColor(Color.BLUE);
        tmpMarks.getFont().getShadow().setColor(Color.AQUA);
        tmpMarks.getFont().getShadow().setHorizSize(1);
        tmpMarks.getFont().getShadow().setVertSize(1);
        tmpMarks.setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        tmpMarks.setVisible(true);

        editButton = new JButton("Edit...");
        resetButton = new JButton("Reset");
        activeButton = new JCheckBox("Active");
        activeButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(activeButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(resetButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton, resetButton;
    private JCheckBox activeButton;
}
