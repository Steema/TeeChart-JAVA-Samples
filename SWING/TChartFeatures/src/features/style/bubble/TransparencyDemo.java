/*
 * TransparencyDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bubble;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.styles.Bubble;
import javax.swing.Box;
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
public class TransparencyDemo extends ChartSamplePanel
        implements ChangeListener {

    private Bubble bubbleSeries;

    /**
     * Creates a new instance of TransparencyDemo
     */
    public TransparencyDemo() {
        super();
        transparencySlider.addChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (transparencySlider == source) {
            transparencyLabel.setText(String.valueOf(transparencySlider.getValue()+"%"));
            bubbleSeries.getPointer().setTransparency(transparencySlider.getValue());
            bubbleSeries.repaint();
        }
    }

    protected void initComponents() {
        super.initComponents();

        bubbleSeries = new com.steema.teechart.styles.Bubble(chart1.getChart());
        // transparency:
        //bubbleSeries.getPointer().getGradient().setTransparency(50);
        bubbleSeries.getPointer().setTransparency(50);

        // cosmetic gradient:
        bubbleSeries.getPointer().getGradient().setVisible(true);
        bubbleSeries.getPointer().getGradient().setDirection(GradientDirection.FORWARDDIAGONAL);


        bubbleSeries.fillSampleValues();

        // Now, let's adjust horizontal axis to fit all bubbles...
        int tmp;
        tmp = bubbleSeries.getVertAxis().calcSizeValue(bubbleSeries.getRadiusValues().getFirst());
        bubbleSeries.getHorizAxis().setMinimumOffset(tmp);
        tmp = bubbleSeries.getVertAxis().calcSizeValue(bubbleSeries.getRadiusValues().getLast());
        bubbleSeries.getHorizAxis().setMaximumOffset(tmp);

        transparencySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        transparencyLabel = new JLabel(String.valueOf(transparencySlider.getValue()+"%"));
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(transparencySlider);
            tmpPane.add(transparencyLabel);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JLabel transparencyLabel;
    private JSlider transparencySlider;
}
