/*
 * DownSamplingDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.function;

import com.steema.teechart.ZoomDirections;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.functions.DownSampling;
import com.steema.teechart.functions.DownSamplingMethod;
import com.steema.teechart.styles.Points;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.TreatNullsStyle;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import features.ChartSamplePanel;

public class DownSamplingDemo extends ChartSamplePanel
        implements ActionListener, ChangeListener
{

    private Points points;
    private FastLine fastLine;
    private DownSampling downSampling;
    private double[] xValues, yValues;

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();
        if (source == DownSampleComboBox)
        {
            DownSamplingMethod dMethod;

            switch (DownSampleComboBox.getSelectedIndex())
            {
                case 0:
                   downSampling.setMethod(DownSamplingMethod.MAX);
                    break;
                case 1:
                   downSampling.setMethod(DownSamplingMethod.MIN);
                    break;
                case 2:
                     downSampling.setMethod(DownSamplingMethod.MINMAX);
                    break;
                case 3:
                    downSampling.setMethod(DownSamplingMethod.MINMAXFIRSTLAST);
                    break;
                case 4:
                    downSampling.setMethod(DownSamplingMethod.MINMAXFIRSTLASTNULL);
                    break;
                case 5:
                   downSampling.setMethod(DownSamplingMethod.AVERAGE);
                    break;
                default:
                   downSampling.setMethod(DownSamplingMethod.MAX);
                    break;
            }

            chart1.getSeries(1).clear();
            chart1.getSeries(1).checkDataSource();
            chart1.getSeries(1).invalidate();
        }
    }

    public void stateChanged(ChangeEvent e)
    {
        Object source = e.getSource();
        if (source instanceof JSpinner)
        {
            SpinnerModel tmpModel = ((JSpinner) source).getModel();
            if (tmpModel instanceof SpinnerNumberModel)
            {
                int dCount = ((SpinnerNumberModel) tmpModel).getNumber().intValue();
                if ((dCount > -1) && (dCount < 2001))
                {
                    if (source == displaySpinner)
                    {
                        downSampling.setDisplayedPointCount(dCount);
                        chart1.getSeries(1).checkDataSource();
                    }
                }
            }
        }
    }

    /**
     * Creates a new instance of CCIDemo
     */
    public DownSamplingDemo()
    {
        super();
        DownSampleComboBox.addActionListener(this);
        displaySpinner.addChangeListener(this);
    }

    public void ChartInit()
    {
        createArrays();

        points = new com.steema.teechart.styles.Points(chart1.getChart());
        fastLine = new com.steema.teechart.styles.FastLine(chart1.getChart());
        points.getPointer().setHorizSize(0);
        points.getPointer().setVertSize(0);
        points.getPointer().getPen().setVisible(false);

        //chart1.getSeries(0).setActive(false);

        chart1.getSeries(0).add(xValues, yValues);

        downSampling = new com.steema.teechart.functions.DownSampling(chart1.getChart());

        fastLine.setTitle("DownSample");
        fastLine.setColor(Color.GREEN);

        chart1.getSeries(1).setDataSource(points);
        chart1.getSeries(1).setFunction(downSampling);
//        chart1.getSeries(1).setActive(false);

    }

    protected void initComponents()
    {
        super.initComponents();

        createArrays();
        chart1.getAspect().setView3D(false);
        chart1.getZoom().setDirection(ZoomDirections.HORIZONTAL);
        points = new Points(chart1.getChart());
        fastLine = new FastLine(chart1.getChart());

        DownSampleComboBox = new JComboBox(DownSampleMethodStrings);
        downSampling = new DownSampling(chart1.getChart());
        points.add(xValues, yValues);
        points.getPointer().setHorizSize(0);
        points.getPointer().setVertSize(0);
        points.setColor(Color.TRANSPARENT);
        downSampling.setDisplayedPointCount(1000);
        displaySpinner = new JSpinner(new SpinnerNumberModel(downSampling.getDisplayedPointCount(),0,2000,100));
        downSampling.setMethod(DownSamplingMethod.MINMAXFIRSTLASTNULL);
        fastLine.setTreatNulls(TreatNullsStyle.DONOTPAINT);
        fastLine.setDataSource(points);
        fastLine.setFunction(downSampling);
       DownSampleComboBox.setSelectedIndex(4);

    }

    protected void initGUI()
    {
        super.initGUI();
        chart1.getHeader().setVisible(true);
        chart1.setText("DownSample Function Example");
        chart1.getAspect().setView3D(false);

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Style:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(DownSampleComboBox);
            tmpPane.add(tmpLabel);
            tmpPane.add(DownSampleComboBox);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Display Count:");
            tmpLabel.setDisplayedMnemonic('D');
            tmpLabel.setLabelFor(displaySpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(displaySpinner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void createArrays()
    {
        int length = 2000;

        xValues = new double[length * 2];
        yValues = new double[length * 2];

        for (int i = 0; i < length; i++)
        {
            xValues[i] = i;
            if (i % 20 == 0)
            {
                yValues[i] = 0.0;
            }
            else
            {
                yValues[i] = Math.round(Math.random() * 100);
            }
        }

        for (int i = 0; i < length; i++)
        {
            xValues[i + length] = i;
            if (i % 20 == 0)
            {
                yValues[i + length] = 0.0;
            }
            else
            {
                yValues[i + length] = Math.round(Math.random() * 100);
            }
        }
    }

    private void unZoomed(com.steema.teechart.events.ChartEvent evt)
    {
        //series 0 is the original series, although you could use any value to set the maximum
        chart1.getAxes().getBottom().setMinMax(0, chart1.getSeries(0).getXValues().getMaximum());
        chart1.getSeries(1).checkDataSource();
    }

    private void onZoom(com.steema.teechart.events.ChartEvent evt)
    {
        chart1.getSeries(1).checkDataSource();
    }
    private JComboBox DownSampleComboBox;
    private JSpinner displaySpinner;
    private static final String[] DownSampleMethodStrings =
    {
        "MAX", "MIN",
        "MINMAX", "MINMAXFIRSTLAST", "MINMAXFIRSTLASTNULL", "AVERAGE"
    };
}
