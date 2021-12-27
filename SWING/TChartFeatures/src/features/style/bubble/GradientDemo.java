/*
 * GradientDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bubble;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.editors.DialogFactory;
import com.steema.teechart.styles.Bubble;
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
public class GradientDemo extends ChartSamplePanel
        implements ItemListener, ActionListener {

    private Bubble bubbleSeries;

    /** Creates a new instance of GradientDemo */
    public GradientDemo() {
        super();
        useGradientButton.addItemListener(this);
        editGradientButton.addActionListener(this);
        adjustMarginsButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == adjustMarginsButton) {
            int tmp;
            tmp = bubbleSeries.getVertAxis().calcSizeValue(bubbleSeries.getRadiusValues().getFirst());
            bubbleSeries.getHorizAxis().setMinimumOffset(tmp);
            tmp = bubbleSeries.getVertAxis().calcSizeValue(bubbleSeries.getRadiusValues().getLast());
            bubbleSeries.getHorizAxis().setMaximumOffset(tmp);
        } else if (source == editGradientButton) {
            DialogFactory.showModal(bubbleSeries.getPointer().getGradient());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == useGradientButton) {
            bubbleSeries.getPointer().getGradient().setVisible(isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();

        bubbleSeries = new com.steema.teechart.styles.Bubble(chart1.getChart());
        bubbleSeries.fillSampleValues();
        bubbleSeries.setColorEach(true);
        bubbleSeries.getPointer().getGradient().setVisible(true);
        bubbleSeries.getPointer().getGradient().setDirection(GradientDirection.HORIZONTAL);
        bubbleSeries.getColors().setColor(4, Color.AQUA);
        editGradientButton = new JButton("Edit Gradient...");
        adjustMarginsButton = new JButton("Adjust margins");

        useGradientButton = new JCheckBox("Gradient");
        useGradientButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(useGradientButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editGradientButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(adjustMarginsButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editGradientButton;
    private JButton adjustMarginsButton;
    private JCheckBox useGradientButton;
}
