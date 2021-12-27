/*
 * MarkTipDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.marktips;
import com.steema.teechart.BevelStyle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.BarStyle;
import com.steema.teechart.tools.MarksTip;
import com.steema.teechart.tools.MarksTipMouseAction;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class MarkTipDemo extends ChartSamplePanel
        implements ActionListener, ChangeListener, ItemListener {

    private MarksTip tool;

    /** Creates a new instance of MarkTipDemo */
    public MarkTipDemo() {
        super();
        editButton.addActionListener(this);
        showTipsButton.addItemListener(this);
        mouseActionList.addActionListener(this);
        hintPauseSpinner.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == mouseActionList) {
            switch (mouseActionList.getSelectedIndex()) {
                case 0: tool.setMouseAction(MarksTipMouseAction.MOVE); break;
                case 1: tool.setMouseAction(MarksTipMouseAction.CLICK); break;
            }
        } else if (source == editButton) {
            ChartEditor.editTool(tool);
            hintPauseSpinnerModel.setValue(new Integer(tool.getMouseDelay()));
            mouseActionList.setSelectedItem(tool.getMouseAction());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == showTipsButton) {
            tool.setActive(isSelected);
        };
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();

        if (source == hintPauseSpinner) {
            int pause = hintPauseSpinnerModel.getNumber().intValue();
            tool.setMouseDelay(pause);
        };
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(false);
        chart1.getWalls().getBottom().setTransparent(true);
        chart1.getWalls().getLeft().setTransparent(true);
        chart1.getLegend().setBevelOuter(BevelStyle.RAISED);
        chart1.getLegend().setColor(Color.SILVER);
        chart1.getLegend().getGradient().setDirection(GradientDirection.VERTICAL);
        chart1.getLegend().getGradient().setStartColor(Color.GRAY);
        chart1.getLegend().getShadow().setSize(0);
        chart1.getAxes().getBottom().getGrid().setVisible(false);
        chart1.getAxes().getLeft().getGrid().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();

        Bar barSeries = new com.steema.teechart.styles.Bar(chart1.getChart());
        barSeries.getPen().setVisible(false);
        barSeries.getMarks().setVisible(false);
        barSeries.setColor(Color.RED);
        barSeries.setBarStyle(BarStyle.RECTGRADIENT);
        barSeries.getGradient().setDirection(GradientDirection.VERTICAL);
        barSeries.getGradient().setMiddleColor(new Color(16744576));
        barSeries.getGradient().setStartColor(new Color(4227327));
        barSeries.fillSampleValues(8);

        tool = new com.steema.teechart.tools.MarksTip(chart1.getChart());
        tool.setSeries(barSeries);

        editButton = new JButton("Edit...");
        showTipsButton = new JCheckBox("Show tips on:");
        showTipsButton.setSelected(tool.getActive());
        mouseActionList = new JComboBox(new String[] {"Move", "Click"});
        mouseActionList.setSelectedIndex(tool.getMouseAction().getValue());
        hintPauseSpinnerModel = new SpinnerNumberModel(
                tool.getMouseDelay(),
                0,
                20000,
                1);
        hintPauseSpinner = new JSpinner(hintPauseSpinnerModel);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(showTipsButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(mouseActionList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Delay (msec):");
            tmpLabel.setDisplayedMnemonic('D');
            tmpLabel.setLabelFor(hintPauseSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(hintPauseSpinner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JCheckBox showTipsButton;
    private JComboBox mouseActionList;
    private JSpinner hintPauseSpinner;
    private SpinnerNumberModel hintPauseSpinnerModel;
}
