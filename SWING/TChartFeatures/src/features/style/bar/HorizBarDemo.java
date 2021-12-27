/*
 * BarSeries.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bar;
import com.steema.teechart.Aspect;
import com.steema.teechart.axis.Axis;
import com.steema.teechart.BevelStyle;
import com.steema.teechart.Header;
import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.drawing.StringAlignment;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.BarStyle;
import com.steema.teechart.styles.HorizBar;
import com.steema.teechart.styles.MultiBars;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import features.utils.EnumStrings;

/**
 *
 * @author tom
 */
public class HorizBarDemo extends ChartSamplePanel
        implements ActionListener {

    private JButton editButton;
    private JComboBox barStyleList;
    private JComboBox layoutList;

    /** Creates a new instance of BarSeries */
    public HorizBarDemo() {
        super();
        editButton.addActionListener(this);
        layoutList.addActionListener(this);
        barStyleList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        HorizBar tmpSeries = (HorizBar)chart1.getSeries(2);

        if (source == layoutList) {
            MultiBars layout = MultiBars.NONE;
            switch (layoutList.getSelectedIndex()) {
                case 0: layout = MultiBars.NONE; break;
                case 1: layout = MultiBars.SIDE; break;
                case 2: layout = MultiBars.STACKED; break;
                case 3: layout = MultiBars.STACKED100; break;
                case 4: layout = MultiBars.SIDEALL; break;
                case 5: layout = MultiBars.SELFSTACK; break;
            }

            boolean tmpVisible = (layout != MultiBars.STACKED)
            && (layout != MultiBars.STACKED100)
            && (layout != MultiBars.SELFSTACK);

            tmpSeries.setMultiBar(layout);
            for (int i=0; i < chart1.getSeriesCount(); i++) {
                chart1.getSeries(i).getMarks().setVisible(tmpVisible);
            }
        } else if (source == barStyleList) {
            BarStyle barStyle = BarStyle.RECTANGLE;
            switch (barStyleList.getSelectedIndex()) {
                case 0: barStyle = BarStyle.RECTANGLE; break;
                case 1: barStyle = BarStyle.PYRAMID; break;
                case 2: barStyle = BarStyle.INVPYRAMID; break;
                case 3: barStyle = BarStyle.CYLINDER; break;
                case 4: barStyle = BarStyle.ELLIPSE; break;
                case 5: barStyle = BarStyle.ARROW; break;
                case 6: barStyle = BarStyle.RECTGRADIENT; break;
                case 7: barStyle = BarStyle.CONE; break;
            }

            tmpSeries.setBarStyle(barStyle);
        } else if (source == editButton) {
            ChartEditor.editChart(chart1.getChart());
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText( "Horizontal Bar" );

        Aspect tmpAspect = chart1.getAspect();
        tmpAspect.setElevation(347);
        tmpAspect.setOrthogonal(false);
        tmpAspect.setPerspective(68);
        tmpAspect.setRotation(351);
        tmpAspect.setChart3DPercent(65);
        tmpAspect.setZoom(76);
        tmpAspect.setZoomText(false);

        Wall tmpWall;
        tmpWall = chart1.getWalls().getBack();
        tmpWall.getBrush().setColor(Color.WHITE);
        tmpWall.setColor(Color.SILVER);
        tmpWall.setSize(11);
        tmpWall = chart1.getWalls().getBottom();
        tmpWall.setColor(Color.MAROON);
        tmpWall.setSize(10);
        tmpWall = chart1.getWalls().getLeft();
        tmpWall.setColor(Color.BLUE);
        tmpWall.setSize(10);

        chart1.getLegend().getFont().setSize(12);

        Header tmpHeader = chart1.getHeader();
        tmpHeader.setAlignment(StringAlignment.NEAR);
        tmpHeader.getFont().setSize(13);
        tmpHeader.getFont().setBold(true);
        tmpHeader.getGradient().setDirection(GradientDirection.VERTICAL);
        tmpHeader.getGradient().setEndColor(Color.SILVER);
        tmpHeader.getGradient().setVisible(true);
        tmpHeader.setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);

        //BottomAxis.DateTimeFormat = 'M/d/yy'

        Axis tmpAxis = chart1.getAxes().getLeft();
        tmpAxis.setMinorTickCount(1);
        tmpAxis.getMinorTicks().setLength(5);
        tmpAxis.getMinorTicks().setColor(Color.BLUE);
        tmpAxis.getTicks().setLength(8);
        tmpAxis.getTicks().setColor(Color.YELLOW);

        chart1.getPanel().setBevelInner(BevelStyle.LOWERED);
        chart1.getPanel().setBevelOuter(BevelStyle.NONE);
    }


    protected void initComponents() {
        super.initComponents();

        HorizBar barSeries = null;
        for (int i=0; i < 3; i++) {
            barSeries = new HorizBar(chart1.getChart());
            barSeries.setBarStyle(BarStyle.RECTANGLE);
            barSeries.setMultiBar(MultiBars.NONE);
            barSeries.fillSampleValues(3);
        }

        editButton = new JButton("Edit...");

        layoutList = new JComboBox(EnumStrings.SERIES_LAYOUTS);
        layoutList.setSelectedIndex(0);

        barStyleList = new JComboBox(EnumStrings.BAR_STYLES);
        barStyleList.setSelectedIndex(0);

    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            JLabel tmpLabel;
            tmpLabel = new JLabel("Layout:");
            tmpLabel.setDisplayedMnemonic('L');
            tmpLabel.setLabelFor(layoutList);
            tmpPane.add(tmpLabel);
            tmpPane.add(layoutList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Style Bar 1:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(barStyleList);
            tmpPane.add(tmpLabel);
            tmpPane.add(barStyleList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
}
