/*
 * AnnotationDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.annotation;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.tools.Annotation;
import com.steema.teechart.tools.AnnotationPosition;
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
public class AnnotationDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private Annotation tool1, tool2, tool3;

    /**
     * Creates a new instance of AnnotationDemo
     */
    public AnnotationDemo() {
        super();
        visibleButton.addItemListener(this);
        editButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editTool(tool1);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == visibleButton) {
            tool1.setActive(isSelected);
            tool2.setActive(isSelected);
            tool3.setActive(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Annotation Tool Example");
        chart1.getLegend().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();

        FastLine tmpSeries = new com.steema.teechart.styles.FastLine(chart1.getChart());
        tmpSeries.fillSampleValues(30);


        tool1 = new Annotation(chart1.getChart());
        tool1.setText("Annotation 1");
        tool1.setLeft(65);
        tool1.setTop(125);
        tool1.getShape().setCustomPosition(true);

        tool2 = new Annotation(chart1.getChart());
        tool2.setText("Annotation 2");
        tool2.setLeft(130);
        tool2.setTop(70);
        tool2.getShape().setColor(Color.PURPLE);
        tool2.getShape().getFont().setColor(Color.BLUE);
        tool2.getShape().setCustomPosition(true);

        tool3 = new Annotation(chart1.getChart());
        tool3.setText("Another one\nwith multiple\nlines of text.");
        tool3.setPosition(AnnotationPosition.RIGHTTOP);
        tool3.getShape().setColor(Color.YELLOW);

        editButton = new JButton("Edit...");
        visibleButton = new JCheckBox("Visible");
        visibleButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(visibleButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JCheckBox visibleButton;
}
