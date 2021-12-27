/*
 * GanttMouseDragDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.steema.teechart.axis.Axis;
import com.steema.teechart.DateTime;
import com.steema.teechart.DateTimeStep;
import com.steema.teechart.events.DragAdapter;
import com.steema.teechart.styles.Gantt;
import com.steema.teechart.styles.Series.MarkTextResolver;
import com.steema.teechart.styles.ValueListOrder;
import com.steema.teechart.tools.GanttTool;

import features.ChartSamplePanel;
import features.utils.EnumStrings;


/**
 *
 * @author tom
 */
public class GanttMouseDragDemo extends ChartSamplePanel
    implements ActionListener, ChangeListener {

    private Gantt series;
    private GanttTool tool;

    /** Creates a new instance of GanttMouseDragDemo */
    public GanttMouseDragDemo() {
        super();
        scrollLeftButton.addActionListener(this);
        scrollRightButton.addActionListener(this);
        zoomInButton.addActionListener(this);
        zoomOutButton.addActionListener(this);
        periodStepList.addActionListener(this);
        vertSizeSlider.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Axis bottomAxis = chart1.getAxes().getBottom();
        if (source == periodStepList) {
            switch (periodStepList.getSelectedIndex()) {
                case 0: bottomAxis.setIncrement(DateTimeStep.ONEDAY); break;
                case 1: bottomAxis.setIncrement(DateTimeStep.ONEWEEK); break;
                case 2: bottomAxis.setIncrement(DateTimeStep.HALFMONTH); break;
                case 3: bottomAxis.setIncrement(DateTimeStep.ONEMONTH); break;
            }
        } else if (source == scrollLeftButton) {
            bottomAxis.setMinMax(bottomAxis.getMinimum()-1,bottomAxis.getMaximum()-1);
        } else if (source == scrollRightButton) {
            bottomAxis.setMinMax(bottomAxis.getMinimum()+1,bottomAxis.getMaximum()+1);
        } else if (source == zoomInButton) {
            bottomAxis.setMinMax(bottomAxis.getMinimum()+1,bottomAxis.getMaximum()-1);
        } else if (source == zoomOutButton) {
            bottomAxis.setMinMax(bottomAxis.getMinimum()-1,bottomAxis.getMaximum()+1);
        }
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int size = (int)source.getValue();
            series.getPointer().setVertSize(size);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.setText("Gantt and mouse drag");
        chart1.getLegend().setVisible(false);
        // Set 2D
        chart1.getAspect().setView3D(false);

        Axis tmpAxis;

        // Set horizontal bottom axis minimum and maximum
        tmpAxis = chart1.getAxes().getBottom();
        tmpAxis.setMinMax(new DateTime(2003,4,1), new DateTime(2003,5,1));
        // Set horizontal daily Increment for labels and grids:
        tmpAxis.setIncrement(DateTimeStep.ONEWEEK);
        tmpAxis.getLabels().setAngle(90);
        tmpAxis.getLabels().setDateTimeFormat("dd-MMM");

        // Set vertical left axis minimum and maximum
        tmpAxis = chart1.getAxes().getLeft();
        tmpAxis.setMinMax( -2, 5 );
        tmpAxis.getGrid().setCentered(false);

        // Disable zoom
        chart1.getZoom().setAllow(false);

        chart1.addMouseListener( new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
               positionLabel.setText("");
            }
        });


    }

    protected void initSeries() {
        series = new com.steema.teechart.styles.Gantt(chart1.getChart());
        // Disable automatic sorting by date
        series.getXValues().setOrder(ValueListOrder.NONE);

        // Fill Gantt with sample date-time values:

        series.add(new DateTime(2003,4,1), new DateTime(2003,4,10), 0,"A");
        series.add(new DateTime(2003,4,5), new DateTime(2003,4,15), 1,"B");
        series.add(new DateTime(2003,4,2), new DateTime(2003,4,8),  2,"C");
        series.add(new DateTime(2003,4,9), new DateTime(2003,4,21), 3,"D");

        // Another gantt bar on the same line....
        series.add(new DateTime(2003,4,12), new DateTime(2003,4,18),  2,"C");

        // Make marks visible
        series.getMarks().setVisible(true);
        series.getMarks().getShadow().setSize(0);
        series.getMarks().getGradient().setVisible(true);

        series.getPointer().setVisible(true);
        series.getPointer().setVertSize(6);

        // Example, customize Series marks...
        series.setMarkTextResolver( new MarkTextResolver() {
            public String getMarkText(int valueIndex, String markText) {
                String tmpText = "";
                switch (valueIndex) {
                    case 0: { tmpText = "John"; break; }
                    case 1: { tmpText = "Ann"; break; }
                    case 2: { tmpText = "David"; break; }
                    case 3: { tmpText = "Carol"; break; }
                    case 4: { tmpText = "David 2"; break; }
                }
                return tmpText;
            }
        });
    }

    protected void initTools() {
        // Initialize Gantt Tool programatically
        tool = new com.steema.teechart.tools.GanttTool(chart1.getChart());
        tool.setSeries(series);
        tool.addDragListener( new DragAdapter() {
            public void dragMoving(com.steema.teechart.events.ChangeEvent e) {
                positionLabel.setText(
                        series.getStartValues().asDateTime(tool.getDragItem().getBar()) +
                        " "+
                        series.getEndValues().asDateTime(tool.getDragItem().getBar())
                );
            }
        });
    }

    protected void initComponents() {
        super.initComponents();
        initSeries();
        initTools();

        scrollLeftButton = new JButton("<<");
        scrollRightButton = new JButton(">>");
        zoomInButton = new JButton("> <");
        zoomOutButton = new JButton("< >");
        periodStepList = new JComboBox(EnumStrings.PERIOD_STEPS);
        periodStepList.setSelectedIndex(0);
        positionLabel = new JLabel("");
        vertSizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 6);
    }

    protected void initGUI() {
        super.initGUI();

        JPanel hPane;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.setLayout( new BoxLayout(tmpPane, BoxLayout.Y_AXIS) );
            hPane = new JPanel();
            {
                hPane.setLayout( new BoxLayout(hPane, BoxLayout.X_AXIS) );
                hPane.add(scrollLeftButton);
                hPane.add(Box.createHorizontalStrut(10));
                hPane.add(scrollRightButton);
                hPane.add(Box.createHorizontalStrut(10));
                hPane.add(periodStepList);
                hPane.add(Box.createHorizontalStrut(10));
                hPane.add(zoomInButton);
                hPane.add(Box.createHorizontalStrut(10));
                hPane.add(zoomOutButton);
                tmpPane.add(Box.createHorizontalStrut(10));
                hPane.add(vertSizeSlider);
                hPane.add(Box.createHorizontalGlue());
            }
            tmpPane.add(hPane);
            hPane = new JPanel();
            {
                hPane.setLayout( new BoxLayout(hPane, BoxLayout.X_AXIS) );
                hPane.add(positionLabel);
                hPane.add(Box.createHorizontalGlue());
            }
            tmpPane.add(hPane);
        }
    }

    JButton scrollLeftButton, scrollRightButton, zoomInButton, zoomOutButton;
    JComboBox periodStepList;
    JLabel positionLabel;
    JSlider vertSizeSlider;
}
