/*
 * GradientDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.area;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.editors.DialogFactory;
import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.CustomStack;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class GradientDemo extends ChartSamplePanel
        implements ActionListener, ItemListener, ChangeListener {

    private Area areaSeries;
    private JButton showGradientEditorButton;
    private JCheckBox gradientButton;
    private JSlider transparencySlider;

    /**
     * Creates a new instance of GradientDemo
     */
    public GradientDemo() {
        super();
        gradientButton.addItemListener(this);
        showGradientEditorButton.addActionListener(this);
        transparencySlider.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source  == showGradientEditorButton) {
            DialogFactory.showModal(areaSeries.getGradient());
            areaSeries.repaint();
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == gradientButton) {
            areaSeries.getGradient().setVisible(isSelected);
        }
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (transparencySlider == source) {
            areaSeries.setTransparency(transparencySlider.getValue());
            areaSeries.getGradient().setTransparency(transparencySlider.getValue());
            areaSeries.repaint();
        }
    };

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
    }

    protected void initSeries() {
        areaSeries = new Area(chart1.getChart());
        areaSeries.getMarks().setVisible(false);
        areaSeries.setColor(Color.RED);
        areaSeries.fillSampleValues(10);
        areaSeries.setTransparency(0);
        areaSeries.setStacked(CustomStack.NONE);

        com.steema.teechart.drawing.Gradient tmpGradient = areaSeries.getGradient();
        tmpGradient.setVisible(true);
        tmpGradient.setUseMiddle(true);
        tmpGradient.setDirection(GradientDirection.HORIZONTAL);
        tmpGradient.setStartColor(Color.RED);
        tmpGradient.setMiddleColor(Color.BLUE);
        tmpGradient.setEndColor(Color.GREEN);
        tmpGradient.setTransparency(0);
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();
        showGradientEditorButton = new JButton("Edit gradient...");
        gradientButton = new JCheckBox("Gradient Visible");
        gradientButton.setSelected(areaSeries.getGradient().getVisible());
        transparencySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
    }

    protected void initGUI() {
        super.initGUI();

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(showGradientEditorButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(gradientButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(transparencySlider);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
}
