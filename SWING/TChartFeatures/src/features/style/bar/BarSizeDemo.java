/*
 * BarSizeDemo.java
 *
 * <p>Copyright: (c) 2004-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bar;

import com.steema.teechart.TextShapePosition;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.tools.Annotation;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class BarSizeDemo extends ChartSamplePanel
        implements ChangeListener {

    private BarSize barSeries;
    private Annotation annotationTool;

    /**
     * Creates a new instance of BarSizeDemo
     */
    public BarSizeDemo() {
        super();
        barSpinner.addChangeListener(this);
        positionSlider.addChangeListener(this);
        sizeSlider.addChangeListener(this);
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();

        if (source == barSpinner) {
            refreshButtonPane();
        } else if (source instanceof JSlider) {
            JSlider tmpSlider = (JSlider)e.getSource();

            double tmpValue = (int)tmpSlider.getValue() / 100.0;
            if (tmpSlider == positionSlider) {
                positionLabel.setText(String.valueOf(tmpValue));
            } else  if (tmpSlider == sizeSlider) {
                sizeLabel.setText(String.valueOf(tmpValue));
            }

            if (!tmpSlider.getValueIsAdjusting()) {
                int tmpBar = barSpinnerModel.getNumber().intValue();
                if (tmpSlider == positionSlider) {
                    barSeries.getXValues().setValue(tmpBar , tmpValue);
                    refreshAnnotation();
                } else  if (tmpSlider == sizeSlider) {

                    barSeries.getSizeValues().setValue(tmpBar , tmpValue);
                    sizeLabel.setText(String.valueOf(tmpValue));
                }
            }
            barSeries.repaint();
        }
    }

    protected void initComponents() {
        super.initComponents();
        chart1.getHeader().setVisible(true);
        chart1.setText("Bar Size Series example.");

        barSeries = new BarSize(chart1.getChart());
        barSeries.getMarks().setVisible(false);
        barSeries.fillSampleValues(6);

        barSeries.getXValues().setValue(3,2.8);
        barSeries.getColors().setColor(1, Color.BLUE);
        barSeries.getColors().setColor(4, Color.YELLOW);

        barSeries.getSizeValues().setValue(0, 0.4);
        barSeries.getSizeValues().setValue(2, 0.1);
        barSeries.getSizeValues().setValue(4, 1.1);

        annotationTool = new Annotation(chart1.getChart());
        annotationTool.getCallout().setColor(Color.BLACK);
        annotationTool.getCallout().getArrow().setVisible(false);

        TextShapePosition tmpShape = annotationTool.getShape();
        tmpShape.setCustomPosition(true);
        tmpShape.getGradient().setDirection(GradientDirection.VERTICAL);
        tmpShape.getGradient().setEndColor(Color.SILVER);
        tmpShape.getGradient().setVisible(true);
        tmpShape.setLeft(6);
        tmpShape.getShadow().setColor(Color.GRAY);
        tmpShape.getShadow().setHorizSize(1);
        tmpShape.getShadow().setVertSize(1);
        tmpShape.setTop(8);

        positionSlider = new JSlider(JSlider.HORIZONTAL, -barSeries.getCount()*100, barSeries.getCount()*100, 0);
        positionLabel = new JLabel("");
        sizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 300, 40);
        sizeLabel = new JLabel("");
        barSpinnerModel = new SpinnerNumberModel(3,
                0,
                barSeries.getXValues().count-1,
                1);
        barSpinner = new JSpinner(barSpinnerModel);

        refreshButtonPane();
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Bar:");
            tmpLabel.setDisplayedMnemonic('B');
            tmpLabel.setLabelFor(barSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(barSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Position:");
            tmpLabel.setDisplayedMnemonic('P');
            tmpLabel.setLabelFor(positionSlider);
            tmpPane.add(tmpLabel);
            tmpPane.add(positionSlider);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(positionLabel);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Size:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(sizeSlider);
            tmpPane.add(tmpLabel);
            tmpPane.add(sizeSlider);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(sizeLabel);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void refreshAnnotation() {
        int selBar = barSpinnerModel.getNumber().intValue();

        annotationTool.setText(String.valueOf(selBar));
        annotationTool.getShape().setTop( chart1.getHeight() - 28);  //TODO Not visible under X-Axis!
        annotationTool.getShape().setLeft( barSeries.calcXPosValue( barSeries.getXValues().value[selBar])-8);
    }

    private void refreshButtonPane() {
        double tmpValue;
        int selBar = barSpinnerModel.getNumber().intValue();
        tmpValue = barSeries.getXValues().getValue(selBar);
        positionSlider.setValue( (int)Math.round( tmpValue * 100));
        positionLabel.setText(String.valueOf(tmpValue));

        tmpValue = barSeries.getSizeValues().getValue(selBar);
        sizeSlider.setValue( (int)Math.round( tmpValue * 100));
        sizeLabel.setText(String.valueOf(tmpValue));

        refreshAnnotation();
    }

    private JLabel positionLabel, sizeLabel;
    private JSlider positionSlider, sizeSlider;
    private JSpinner barSpinner;
    private SpinnerNumberModel barSpinnerModel;
}
