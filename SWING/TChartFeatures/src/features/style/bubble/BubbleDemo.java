/*
 * BubbleDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bubble;
import com.steema.teechart.DateTime;
import com.steema.teechart.drawing.ChartFont;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bubble;
import com.steema.teechart.styles.HorizontalAxis;
import com.steema.teechart.styles.ISeries;
import com.steema.teechart.styles.PointerStyleResolver;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.ValueListOrder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Random;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import features.utils.EnumStrings;

/**
 *
 * @author tom
 */
public class BubbleDemo extends ChartSamplePanel
        implements ItemListener, ActionListener {

    private Bubble bubbleSeries;

    /** Creates a new instance of BubbleDemo */
    public BubbleDemo() {
        super();
        randomButton.addItemListener(this);
        viewMarksButton.addItemListener(this);
        bubbleStyleList.addActionListener(this);
        zoomInButton.addActionListener(this);
        zoomOutButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == bubbleStyleList) {
            PointerStyle pointerStyle = PointerStyle.NOTHING;
            switch (bubbleStyleList.getSelectedIndex()) {
                case 0: pointerStyle = PointerStyle.RECTANGLE; break;
                case 1: pointerStyle = PointerStyle.CIRCLE; break;
                case 2: pointerStyle = PointerStyle.TRIANGLE; break;
                case 3: pointerStyle = PointerStyle.DOWNTRIANGLE; break;
                case 4: pointerStyle = PointerStyle.CROSS; break;
                case 5: pointerStyle = PointerStyle.DIAGCROSS; break;
                case 6: pointerStyle = PointerStyle.STAR; break;
                case 7: pointerStyle = PointerStyle.DIAMOND; break;
            }
            setBubbleSeriesStyle(pointerStyle);
        } else if (source == zoomInButton) {
            chart1.getZoom().zoomPercent(90);
        } else if (source == zoomOutButton) {
            chart1.getZoom().zoomPercent(110);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == randomButton) {
            chart1.repaint();
        } else if (source == viewMarksButton) {
            bubbleSeries.getMarks().setVisible(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        ChartFont tmpFont;
        chart1.getWalls().getBack().getBrush().setColor(Color.WHITE);
        chart1.getWalls().getBack().setColor(Color.WHITE);
        chart1.getLegend().setVisible(false);
        chart1.getAxes().getBottom().getLabels().setAngle(90);
        chart1.getAxes().getRight().getTitle().setAngle(180);
        chart1.getAxes().getLeft().getLabels().getFont().setSize(13);
        chart1.getAxes().getLeft().getMinorTicks().setLength(4);
        chart1.getAxes().getLeft().getMinorTicks().setColor(Color.RED);
        chart1.getAxes().getLeft().getTicks().setLength(8);
        chart1.getAxes().getLeft().getTitle().setText("Price");
        tmpFont = chart1.getAxes().getLeft().getTitle().getFont();
        tmpFont.setColor(Color.NAVY);
        tmpFont.setSize(15);
        tmpFont.setBold(true);
        chart1.getHeader().setText("Bubble");
        chart1.getHeader().setVisible(true);
        tmpFont = chart1.getHeader().getFont();
        tmpFont.setSize(15);
        tmpFont.setItalic(true);
        tmpFont = chart1.getAxes().getBottom().getLabels().getFont();
        tmpFont.setColor(Color.TEAL);
        tmpFont.setSize(12);
        tmpFont = chart1.getAxes().getBottom().getTitle().getFont();
        tmpFont.setColor(Color.GREEN);
        tmpFont.setSize(16);
        tmpFont.setItalic(true);
        chart1.getAxes().getTop().getLabels().setDateTimeFormat("dd/MM/yy");
        chart1.getAxes().getTop().getLabels().setAngle(90);
        chart1.getAxes().getTop().getTitle().setText("Date");
        chart1.getAspect().setView3D(false);
        chart1.getZoom().setAnimated(true);
        chart1.getZoom().setAnimatedSteps(3);
    }

    protected void initComponents() {
        super.initComponents();
        generator = new Random();

        DateTime tmpDate = DateTime.getToday();
        bubbleSeries = new com.steema.teechart.styles.Bubble(chart1.getChart());
        for (int t=0; t < 100; t++) {
            tmpDate.add(Calendar.DATE, t);
            bubbleSeries.add(
                    tmpDate.getTimeInMillis(),                              /* <-- x value */
                    generator.nextInt(CHART_SAMPLES_MAX),                   /* <-- y value */
                    CHART_SAMPLES_MAX / (20+generator.nextInt(25)),         /* radius value */
                    "",                                                     /* label string */
                    chart1.getGraphics3D().getDefaultColor(t)
                    );
        }

        bubbleSeries.getXValues().setDateTime(true);
        bubbleSeries.getRadiusValues().setDateTime(false);
        bubbleSeries.getRadiusValues().setName("Radius");
        bubbleSeries.getRadiusValues().setOrder(ValueListOrder.NONE);
        bubbleSeries.setHorizontalAxis(HorizontalAxis.TOP);
        bubbleSeries.getMarks().setArrowLength(0);
        bubbleSeries.getMarks().setClip(true);
        bubbleSeries.getMarks().getFont().setColor(Color.WHITE);
        bubbleSeries.getMarks().getFont().setSize(16);
        bubbleSeries.getMarks().getFont().setItalic(true);
        bubbleSeries.getMarks().getFrame().setVisible(false);
        bubbleSeries.getMarks().setTransparent(true);
        bubbleSeries.getMarks().setVisible(false);
        bubbleSeries.getPointer().setHorizSize(14);
        bubbleSeries.getPointer().setVertSize(14);
        bubbleSeries.getPointer().setInflateMargins(false);
        bubbleSeries.setVisible(true);

        bubbleSeries.setPointerStyleResolver(new PointerStyleResolver() {
            public PointerStyle getStyle(ISeries series, int valueIndex, PointerStyle style) {
                  if (randomButton.isSelected()) {
                      return (PointerStyle.fromInt(generator.nextInt(10)));
                  } else {
                      return (bubbleSeries.getPointer().getStyle());
                  }
            }
        });

        zoomInButton = new JButton("Zoom In");
        zoomOutButton = new JButton("Zoom Out");

        randomButton = new JCheckBox("Random");
        randomButton.setSelected(false);
        viewMarksButton = new JCheckBox("View Marks");
        viewMarksButton.setSelected( bubbleSeries.getMarks().getVisible());

        bubbleStyleList = new JComboBox(EnumStrings.BUBBLE_STYLES);
        bubbleStyleList.setSelectedIndex(1);
        setBubbleSeriesStyle(PointerStyle.CIRCLE);
    }

    protected void initGUI() {
        super.initGUI();

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(randomButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(viewMarksButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Style:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(bubbleStyleList);
            tmpPane.add(tmpLabel);
            tmpPane.add(bubbleStyleList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(zoomInButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(zoomOutButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private static final int CHART_SAMPLES_MAX = 1000;

    private void setBubbleSeriesStyle(PointerStyle pointerStyle) {
        bubbleSeries.getPointer().setStyle(pointerStyle);
    }

    private Random generator;

    private JButton zoomInButton, zoomOutButton;
    private JComboBox bubbleStyleList;
    private JCheckBox randomButton;
    private JCheckBox viewMarksButton;
}
