/*
 * ToolbarDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.toolbar;
import com.steema.teechart.styles.Bar3D;
import com.steema.teechart.styles.BarStyle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.SamplePanel;

/**
 *
 * @author tom
 */
public class ToolbarDemo extends SamplePanel
        implements ItemListener, PropertyChangeListener {

    /**
     * Creates a new instance of ToolbarDemo
     */
    public ToolbarDemo() {
        super();
        verticalButton.addItemListener(this);
        hintsButton.addItemListener(this);
        textButton.addItemListener(this);
        enableButton.addItemListener(this);
        myCommander.addPropertyChangeListener("orientation", this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == verticalButton) {
            setCommanderOrientation(isSelected);
        } else if (source == hintsButton) {
            myCommander.setHints(isSelected);
        } else if (source == textButton) {
            myCommander.setLabelValues(isSelected);
        } else if (source == enableButton) {
            myCommander.setEnabled(isSelected);
        }
    }

    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        if (source == myCommander) {
            Integer isVertical = (Integer)e.getNewValue();
            verticalButton.setSelected(isVertical.intValue()==1);
        }
    }

    protected void initComponents() {
        super.initComponents();

        Bar3D tmpSeries = new Bar3D(chart1.getChart());
        tmpSeries.setBarStyle(BarStyle.PYRAMID);
        tmpSeries.setColorEach(true);
        tmpSeries.fillSampleValues(5);

        verticalButton = new JCheckBox("Vertical");
        hintsButton = new JCheckBox("Hints");
        textButton = new JCheckBox("Text");
        enableButton = new JCheckBox("Enable");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(verticalButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(hintsButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(textButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(enableButton);
            tmpPane.add(Box.createHorizontalGlue());

            hintsButton.setSelected(true);
            enableButton.setSelected(true);
            textButton.setSelected(false);
            verticalButton.setSelected(false);
        }
    }

    private JCheckBox verticalButton, hintsButton, textButton, enableButton;
}
