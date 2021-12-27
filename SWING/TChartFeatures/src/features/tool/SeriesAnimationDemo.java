/*
 * SeriesAnimationDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.tools.SeriesAnimation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
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
public class SeriesAnimationDemo extends ChartSamplePanel
    implements ActionListener, ChangeListener, ItemListener {

    private SeriesAnimation tool;

    /** Creates a new instance of SeriesAnimationDemo */
    public SeriesAnimationDemo() {
        super();
        animateButton.addActionListener(this);
        editButton.addActionListener(this);
        drawEveryButton.addItemListener(this);
        stepsSlider.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == animateButton) {
            animateButton.setEnabled(false);
            try {
                tool.execute();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            animateButton.setEnabled(true);
        } else if (source == editButton) {
            ChartEditor.editTool(tool);
            // cosmetic... just in case "steps" has changed
            stepsSlider.setValue(tool.getSteps());
            stepsLabel.setText(Integer.toString(stepsSlider.getValue()));
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == drawEveryButton) {
            tool.setDrawEvery(isSelected?1:0);
        }
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int step = (int)source.getValue();
            tool.setSteps(step);
            stepsLabel.setText(Integer.toString(stepsSlider.getValue()));
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.setText("Series Animation tool");
        chart1.getHeader().setVisible(true);
    }

    protected void initComponents() {
        super.initComponents();

        Bar barSeries = new com.steema.teechart.styles.Bar(chart1.getChart());
        barSeries.setColorEach(true);
        barSeries.getMarks().setArrowLength(20);
        barSeries.getMarks().getCallout().getBrush().setColor(Color.BLACK);
        barSeries.getMarks().getCallout().setLength(20);
        barSeries.getMarks().setVisible(false);
        barSeries.setValueFormat("000");
        barSeries.fillSampleValues(6);
        tool = new com.steema.teechart.tools.SeriesAnimation(chart1.getChart());
        tool.setStartAtMin(false);
        tool.setSeries(barSeries);

        animateButton = new JButton("Animate !");
        editButton = new JButton("Edit...");
        drawEveryButton = new JCheckBox("One by one");
        stepsLabel = new JLabel("100");
        stepsSlider = new JSlider(JSlider.HORIZONTAL, 1, 1000, 100);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {   tmpPane.add(animateButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Steps:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(stepsSlider);
            tmpPane.add(tmpLabel);
            tmpPane.add(stepsSlider);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(stepsLabel);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(drawEveryButton);
            tmpPane.add(Box.createHorizontalStrut(40));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton animateButton, editButton;
    private JCheckBox drawEveryButton;
    private JLabel stepsLabel;
    private JSlider stepsSlider;
}
