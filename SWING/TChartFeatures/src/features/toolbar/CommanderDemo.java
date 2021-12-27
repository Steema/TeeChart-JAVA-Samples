/*
 * CommanderDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by
 Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.toolbar;
import com.steema.teechart.styles.Pie;
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
public class CommanderDemo extends SamplePanel
    implements ItemListener, PropertyChangeListener{

    /** Creates a new instance of CommanderDemo */
    public CommanderDemo() {
        super();
        verticalButton.addItemListener(this);
        myCommander.addPropertyChangeListener("orientation", this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == verticalButton) {
            setCommanderOrientation(isSelected);
        }
    }

    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        if (source == myCommander) {
            Integer isVertical = (Integer)e.getNewValue();
            verticalButton.setSelected(isVertical.intValue()==1);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setElevation(315);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(0);
        chart1.getAspect().setRotation(360);
    }

    protected void initComponents() {
        super.initComponents();
        Pie tmpSeries = new Pie(chart1.getChart());
        tmpSeries.fillSampleValues(8);

        verticalButton = new JCheckBox("Vertical");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(verticalButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox verticalButton;
}
