/*
 * BezierDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bezier;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Bezier;
import com.steema.teechart.styles.CustomStack;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.SeriesPointer;
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
import features.utils.EnumStrings;


/**
 *
 * @author tom
 */
public class BezierDemo extends ChartSamplePanel
    implements ActionListener, ChangeListener, ItemListener {

    private Bezier series;

    /** Creates a new instance of BezierDemo */
    public BezierDemo() {
        super();
        editButton.addActionListener(this);
        view3DButton.addItemListener(this);
        styleList.addActionListener(this);
        pointsSpinner.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeries(series);
        };
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == view3DButton) {
            chart1.getAspect().setView3D(isSelected);
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == pointsSpinner) {
            int points = ((SpinnerNumberModel)pointsSpinner.getModel()).getNumber().intValue();

        }
    }

    protected void initComponents() {
        super.initComponents();

        series = new Bezier(chart1.getChart());
        series.setStacked(CustomStack.NONE);
        series.getLinePen().setColor(Color.RED);
        series.fillSampleValues(30);

        SeriesPointer tmpPointer = series.getPointer();
        tmpPointer.setHorizSize(2);
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.CROSS);
        tmpPointer.setVertSize(2);
        tmpPointer.setVisible(true);

        editButton = new JButton("Edit...");
        view3DButton = new JCheckBox("3D");
        view3DButton.setSelected(chart1.getAspect().getView3D());
        styleList = new JComboBox(EnumStrings.BEZIER_STYLES);
        styleList.setSelectedIndex(0);
        pointsSpinner = new JSpinner(
            new SpinnerNumberModel(
                32,
                3,
                200,
                5
            )
        );
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("Bezier curves");
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Style :");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(styleList);
            tmpPane.add(tmpLabel);
            tmpPane.add(styleList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Middle points :");
            tmpLabel.setDisplayedMnemonic('M');
            tmpLabel.setLabelFor(pointsSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(pointsSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(view3DButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JCheckBox view3DButton;
    private JComboBox styleList;
    private JSpinner pointsSpinner;
}
