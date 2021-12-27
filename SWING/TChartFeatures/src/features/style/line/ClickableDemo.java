/*
 * ClickableDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.line;

import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.SeriesPointer;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class ClickableDemo extends ChartSamplePanel
    implements ItemListener {

    private Line lineSeries;

    private JCheckBox clickableLineButton;
    private JLabel coordinateLabel;

    /**
     * Creates a new instance of ClickableDemo
     */
    public ClickableDemo() {
        super();
        clickableLineButton.addItemListener(this);
        chart1.addMouseMotionListener( new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                int isClickable = lineSeries.clicked(e.getX(),e.getY());
                if (isClickable == -1) {
                    coordinateLabel.setText("");
                } else {
                    coordinateLabel.setText("Point: "+String.valueOf(isClickable));
                }
            }
        });
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = e.getStateChange() == ItemEvent.SELECTED;
        if (source == clickableLineButton) {
            lineSeries.setClickableLine(isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();

        lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries.setClickableLine(false);

        SeriesPointer tmpPointer = lineSeries.getPointer();
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.RECTANGLE);
        tmpPointer.setVisible(true);
        lineSeries.fillSampleValues(8);
        clickableLineButton = new JCheckBox("Clickable line");
        coordinateLabel = new JLabel("");
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(clickableLineButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(coordinateLabel);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
}
