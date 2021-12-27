/*
 * SurfaceDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.surface;

import com.steema.teechart.BevelStyle;
import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.Gradient;
import com.steema.teechart.styles.PaletteStyle;
import com.steema.teechart.styles.Surface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;

import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.misc.Utils;
import com.steema.teechart.styles.Custom3DGrid;

import com.steema.teechart.styles.ISeries;

/**
 *
 * @author tom
 */
public class SurfaceDemo extends ChartSamplePanel
        implements ActionListener, ChangeListener, ItemListener {

    private Surface surfaceSeries;

    /** Creates a new instance of SurfaceDemo */
    public SurfaceDemo() {
        super();
        animateButton.addItemListener(this);
        colorModeList.addActionListener(this);
        frameList.addActionListener(this);
        sampleSpinner.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == colorModeList) {
            switch (colorModeList.getSelectedIndex()) {
                case 0: {
                    surfaceSeries.setUseColorRange(false);
                    surfaceSeries.setUsePalette(false);
                    break;
                }
                case 1: {
                    surfaceSeries.setUseColorRange(true);
                    surfaceSeries.setUsePalette(false);
                    break;
                }
                case 2: {
                    surfaceSeries.setUseColorRange(false);
                    surfaceSeries.setUsePalette(true);
                    surfaceSeries.setPaletteStyle(PaletteStyle.PALE);
                    break;
                }
                case 3: {
                    surfaceSeries.setUseColorRange(false);
                    surfaceSeries.setUsePalette(true);
                    surfaceSeries.setPaletteStyle(PaletteStyle.STRONG);
                    break;
                }
                case 4: {
                    surfaceSeries.setUseColorRange(false);
                    surfaceSeries.setUsePalette(true);
                    surfaceSeries.setPaletteStyle(PaletteStyle.GRAYSCALE);
                    break;
                }
                case 5: {
                    surfaceSeries.setUseColorRange(false);
                    surfaceSeries.setUsePalette(true);
                    surfaceSeries.setPaletteStyle(PaletteStyle.INVERTED_GRAYSCALE);
                    break;
                }
                case 6: {
                    surfaceSeries.setUseColorRange(false);
                    surfaceSeries.setUsePalette(true);
                    surfaceSeries.setPaletteStyle(PaletteStyle.RAINBOW);
                    break;
                }
            }
        } else if (source == frameList) {
            switch (frameList.getSelectedIndex()) {
                case 0: {
                    surfaceSeries.setWireFrame(false);
                    surfaceSeries.setDotFrame(false);
                    surfaceSeries.getPen().setVisible(true);
                    break;
                }
                case 1: {
                    surfaceSeries.setWireFrame(false);
                    surfaceSeries.setDotFrame(false);
                    surfaceSeries.getPen().setVisible(false);
                    break;
                }
                case 2: {
                    surfaceSeries.setWireFrame(true);
                    surfaceSeries.getPen().setVisible(true);
                    break;
                }
                case 3: {
                    surfaceSeries.setDotFrame(true);
                    break;
                }
            }
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == animateButton) {
            if (isSelected) {
                timer.start();
            } else {
                timer.stop();
            }
        };
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == sampleSpinner) {
            surfaceSeries.reCreateValues();
        }
    }

    protected void initChart() {
        super.initChart();
        Wall tmpWall;
        tmpWall = chart1.getWalls().getBack();
        tmpWall.getBrush().setColor(Color.WHITE);
        tmpWall.setColor(Color.LIME); //BackWall.Color = 8453888
        tmpWall.setSize(10);
        tmpWall.setTransparent(false);

        tmpWall = chart1.getWalls().getBottom();
        tmpWall.getBrush().setColor(Color.WHITE);
        tmpWall.getBrush().setVisible(false);
        tmpWall.setSize(10);

        chart1.getWalls().getLeft().setSize(10);

        Gradient tmpGradient = chart1.getPanel().getGradient();
        tmpGradient.setEndColor(Color.WHITE);
        tmpGradient.setMiddleColor(Color.YELLOW);
        tmpGradient.setStartColor(Color.GRAY);
        tmpGradient.setVisible(true);

        chart1.getLegend().setVisible(false);

        chart1.getPanel().setMarginBottom(5);
        chart1.getPanel().setMarginLeft(5);
        chart1.getPanel().setMarginRight(5);
        chart1.getPanel().setMarginTop(5);
        chart1.getPanel().setBevelInner(BevelStyle.LOWERED);
        chart1.getPanel().setBevelWidth(5);

        chart1.getHeader().setVisible(true);
        chart1.setText("Surface");

        chart1.getAxes().getBottom().getGrid().setColor(Color.SILVER);
        chart1.getAxes().getLeft().getGrid().setColor(Color.SILVER);
        chart1.getAxes().getRight().getGrid().setColor(Color.SILVER);
        chart1.getAxes().getTop().getGrid().setColor(Color.SILVER);
        chart1.getAxes().getDepth().setVisible(true);

        chart1.setClipPoints(false);
        chart1.getAspect().setChart3DPercent(90);
        chart1.getAspect().setElevation(348);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(100);
        chart1.getAspect().setRotation(329);
        chart1.getAspect().setVertOffset(-20);
        chart1.getAspect().setZoom(60);
    }

    protected void initSeries() {
        surfaceSeries = new com.steema.teechart.styles.Surface(chart1.getChart());
        surfaceSeries.setPaletteStyle(PaletteStyle.STRONG);
        surfaceSeries.getSideBrush().setColor(Color.WHITE);
        surfaceSeries.getSideBrush().setStyle(null);
        surfaceSeries.setUseColorRange(false);
        surfaceSeries.setUsePalette(true);

        class GetYValue implements Custom3DGrid.YCalculator {
            public double calculate(ISeries sender, int x, int z) {
                double tmpY = 0;
                Surface s = (Surface)sender;
                int n = s.getNumXValues();
                double portianPI = Math.PI / s.getNumXValues();
                double halfPI = Math.PI * 0.5;
                double tmpX = x*portianPI;
                double tmpZ = z*portianPI;
                int sample = ((Integer)sampleSpinnerModel.getValue()).intValue();
                //sample surfaces
                switch (sample) {
                    case 1: {
                        tmpY = 0.5*Utils.sqr(Math.cos(x/(n*0.2)))
                        + Utils.sqr(Math.cos(z/(n*0.2)))
                        - Math.cos(z/(n*0.5));
                        break;
                    }
                    case 2: {
                        tmpY = Utils.sqr(Math.cos(tmpX)) * Utils.sqr(Math.sin(tmpZ));
                        break;
                    }
                    case 3: {
                        tmpY = Math.cos(tmpX*tmpX)+Math.sin(tmpZ*tmpZ);
                        break;
                    }
                    case 4: {
                        tmpY = Utils.sqr(Math.cos(tmpX))+Utils.sqr(Math.sin(tmpZ));
                        break;
                    }
                    case 5: {
                        tmpY = -tmpX+Utils.sqr(tmpZ)*Math.sin(tmpX*tmpZ);
                        break;
                    }
                    case 6 : {
                        tmpY = Math.sqrt(tmpX*tmpX+tmpZ*tmpZ);
                        break;
                    }
                    case 7 : {
                        tmpY = Math.cos(Math.abs(tmpX-halfPI))*Math.sin(tmpZ);
                        break;
                    }
                    case 8 : {
                        tmpY = Math.cos(Math.abs(tmpX-halfPI)*Math.abs(tmpZ-halfPI));
                        break;
                    }
                }
                return tmpY;
            }
        }
        surfaceSeries.setYCalculator(new GetYValue());
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();

        animateButton = new JCheckBox("Animate");
        colorModeList = new JComboBox(new String[] {
            "Single Color",
            "Color Range",
            "Color Palette",
            "Strong Palette",
            "Grayscale",
            "Inverted Gray",
            "Rainbow"
        });
        colorModeList.setSelectedIndex(3);
        frameList = new JComboBox(new String[] {"Solid and Grid", "Solid", "Wireframe", "Dots"});
        sampleSpinnerModel = new SpinnerNumberModel(
                1,
                1,
                8,
                1);
        sampleSpinner = new JSpinner(sampleSpinnerModel);

        //Create a timer.
        timer = new Timer(ONE_MILLISECOND, new OnTime());

        surfaceSeries.reCreateValues();
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(animateButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Style:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(colorModeList);
            tmpPane.add(tmpLabel);
            tmpPane.add(colorModeList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(frameList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Sample:");
            tmpLabel.setDisplayedMnemonic('m');
            tmpLabel.setLabelFor(sampleSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(sampleSpinner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private class OnTime implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            /* stop timer */
            timer.stop();

            Random generator = new Random();
            /* Invert Left axis randomly */
            if (generator.nextInt(100)<2) {
                chart1.getAxes().getLeft().setInverted(chart1.getAxes().getLeft().getInverted());
            }

            /* Invert Bottom axis randomly */
            if (generator.nextInt(100)<2) {
                chart1.getAxes().getBottom().setInverted(chart1.getAxes().getBottom().getInverted());
            }

            /* Invert Depth axis randomly */
            if (generator.nextInt(100)<2) {
                chart1.getAxes().getDepth().setInverted(chart1.getAxes().getDepth().getInverted());
            }

            /* Change Color Mode(Single, Range, Palette or Grayscale) randomly */
            if (generator.nextInt(100)<2) {
                if (colorModeList.getSelectedIndex() < colorModeList.getItemCount()-1) {
                    colorModeList.setSelectedIndex(colorModeList.getSelectedIndex() + 1);
                } else {
                    colorModeList.setSelectedIndex(0);
                }
            }

            /* Change Chart Gradient Colors randomly(only at 16k colors or greater) */
            if (generator.nextInt(100)<10) {
                chart1.getPanel().getGradient().setStartColor(Color.random());
            } else {
                if (generator.nextInt(100)<10) {
                    chart1.getPanel().getGradient().setEndColor(Color.random());
                }
            }

            /* Change Chart Gradient direction randomly */
            if (generator.nextInt(100)<5) {
                int tmpValue = generator.nextInt(GradientDirection.size());
                chart1.getPanel().getGradient().setDirection(GradientDirection.atIndex(tmpValue));
            }

            /* Random change Surface and Chart colors */
            switch (colorModeList.getSelectedIndex()) {
                case 0: { /* single color */
                    if (generator.nextInt(100)<15) {
                        surfaceSeries.setColor(Color.random());
                    }
                    break;
                }
                case 1: { /* color range */
                    if (generator.nextInt(100)<15) {
                        surfaceSeries.setStartColor(Color.random());
                    } else {
                        if (generator.nextInt(100)<15) {
                            surfaceSeries.setEndColor(Color.random());
                        }
                    }
                }
            }
            /* random change pen color */
            if (generator.nextInt(100)<15) {
                surfaceSeries.getPen().setColor(Color.random());
            }

            /* Change Surface Example: */
            if (sampleSpinnerModel.getNextValue() != null) {
                sampleSpinnerModel.setValue(sampleSpinnerModel.getNextValue());
            } else {
                sampleSpinnerModel.setValue(sampleSpinnerModel.getMinimum());
            }

            /* re-enable timer again */
            timer.start();
        }
    }

    private JCheckBox animateButton;
    private JComboBox colorModeList, frameList;
    private JSpinner sampleSpinner;
    private SpinnerNumberModel sampleSpinnerModel;

    private Timer timer;
    private final static int ONE_MILLISECOND = 1;
}
