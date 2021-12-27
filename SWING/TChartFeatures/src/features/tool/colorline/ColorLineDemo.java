/*
 * ColorLineDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.colorline;
import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Points;
import com.steema.teechart.tools.ColorLine;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class ColorLineDemo extends ChartSamplePanel
    implements ItemListener {

    private ColorLine tool1, tool2;

    /**
     * Creates a new instance of ColorLineDemo
     */
    public ColorLineDemo() {
        super();
        showLinesButton.addItemListener(this);
        view3DButton.addItemListener(this);
        view3DLinesButton.addItemListener(this);
        drawBehindButton.addItemListener(this);
        allowDragButton.addItemListener(this);
        dragRepaintButton.addItemListener(this);
        noLimitDragButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == showLinesButton) {
            tool1.setActive(isSelected);
            tool2.setActive(isSelected);
        } else if (source == view3DButton) {
            chart1.getAspect().setView3D(isSelected);
            view3DLinesButton.setEnabled(isSelected);
        } else if (source == view3DLinesButton) {
            tool1.setDraw3D(isSelected);
            tool2.setDraw3D(isSelected);
        } else if (source == drawBehindButton) {
            tool1.setDrawBehind(isSelected);
            tool2.setDrawBehind(isSelected);
        } else if (source == allowDragButton) {
            tool1.setAllowDrag(isSelected);
            tool2.setAllowDrag(isSelected);
        } else if (source == dragRepaintButton) {
            tool1.setDragRepaint(isSelected);
            tool2.setDragRepaint(isSelected);
        } else if (source == noLimitDragButton) {
            tool1.setNoLimitDrag(isSelected);
            tool2.setNoLimitDrag(isSelected);
        }
    }

    //@ TODO Events

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(false);
        chart1.getAxes().getLeft().setAutomatic(false);
        chart1.getAxes().getLeft().setAutomaticMinimum(false);
        chart1.getAxes().getLeft().setEndPosition(80);
    }

    protected void initComponents() {
        super.initComponents();

        tool1 = new com.steema.teechart.tools.ColorLine(chart1.getChart());
        tool1.getPen().setColor(Color.BLUE);
        tool1.getPen().setWidth(2);
        tool1.setAxis(chart1.getAxes().getLeft());

        tool2 = new com.steema.teechart.tools.ColorLine(chart1.getChart());
        tool2.getPen().setColor(Color.LIME);
        tool2.getPen().setWidth(2);
        tool2.setValue(10.0);
        tool2.setAxis(chart1.getAxes().getBottom());

        tool1ButtonPen = new ButtonPen( tool1.getPen() );
        tool2ButtonPen = new ButtonPen( tool2.getPen() );

        Axis tmpAxis = chart1.getAxes().getCustom().getNew();
        tmpAxis.setHorizontal(false);
        tmpAxis.setOtherSide(false);
        tmpAxis.setStartPosition(80.0);

        Line tmpLineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        tmpLineSeries.fillSampleValues(20);
        tmpLineSeries.getMarks().setVisible(false);
        tmpLineSeries.setCustomVertAxis(tmpAxis);

        Points tmpPointSeries = new com.steema.teechart.styles.Points(chart1.getChart());
        tmpPointSeries.fillSampleValues(20);
        tmpPointSeries.getMarks().setVisible(false);

        showLinesButton = new JCheckBox("Show Lines");
        showLinesButton.setSelected(true);
        view3DButton = new JCheckBox("3D");
        view3DButton.setSelected(true);
        view3DLinesButton = new JCheckBox("3D Lines");
        view3DLinesButton.setSelected(true);
        drawBehindButton = new JCheckBox("Draw Behind");
        allowDragButton = new JCheckBox("Allow Drag");
        allowDragButton.setSelected(true);
        dragRepaintButton = new JCheckBox("Drag Repaint");
        noLimitDragButton = new JCheckBox("No Limit Drag");
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
                hPane.add(showLinesButton);
                hPane.add(view3DButton);
                hPane.add(view3DLinesButton);
                hPane.add(drawBehindButton);
                hPane.add(Box.createHorizontalGlue());
            }
            tmpPane.add(hPane);
            hPane = new JPanel();
            {
                hPane.setLayout( new BoxLayout(hPane, BoxLayout.X_AXIS) );
                hPane.add(allowDragButton);
                hPane.add(dragRepaintButton);
                hPane.add(noLimitDragButton);
                hPane.add(Box.createHorizontalStrut(10));
                hPane.add(tool1ButtonPen);
                hPane.add(tool2ButtonPen);
                hPane.add(Box.createHorizontalGlue());
            }
            tmpPane.add(hPane);
        }
    }

    private ButtonPen tool1ButtonPen, tool2ButtonPen;
    private JCheckBox showLinesButton, view3DButton, view3DLinesButton, drawBehindButton;
    private JCheckBox allowDragButton, dragRepaintButton, noLimitDragButton;
}
