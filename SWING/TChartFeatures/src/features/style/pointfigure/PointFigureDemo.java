/*
 * PointFigure.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.pointfigure;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.PointFigure;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.SeriesPointer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class PointFigureDemo extends ChartSamplePanel
    implements ActionListener {

    private PointFigure series1;

    /** Creates a new instance of PointFigure */
    public PointFigureDemo() {
        super();
        editButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeries(series1);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setText("Point & Figure");
        chart1.getHeader().setVisible(true);
    }

    protected void initComponents() {
        super.initComponents();

        series1 = new com.steema.teechart.styles.PointFigure(chart1.getChart());
        series1.fillSampleValues();
        series1.setBoxSize(5);
        series1.getMarks().setVisible(false);

        SeriesPointer tmpPointer;
        tmpPointer = series1.getDownSymbol();
        tmpPointer.getBrush().setColor(Color.RED);
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.CIRCLE);
        tmpPointer.setVisible(true);
        tmpPointer = series1.getUpSymbol();
        tmpPointer.getBrush().setColor(Color.GREEN);
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.DIAGCROSS);
        tmpPointer.setVisible(true);

        editButton = new JButton("Edit...");
    }

    protected void initGUI() {
        super.initGUI();

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
}
