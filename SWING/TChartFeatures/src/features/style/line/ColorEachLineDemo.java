/*
 * Line_ColorEachLine.java
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
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.*;

/**
 *
 * @author tom
 */
public class ColorEachLineDemo extends ChartSamplePanel
        implements ItemListener {

    private JCheckBox colorEachLineButton, colorEachPointButton;
    private Line lineSeries;

    /** Creates a new instance of Line_ColorEachLine */
    public ColorEachLineDemo() {
        super();
        colorEachLineButton.addItemListener(this);
        colorEachPointButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = e.getStateChange() == ItemEvent.SELECTED;
        if (source == colorEachLineButton) {
            lineSeries.setColorEachLine(isSelected);
        } else if (source == colorEachPointButton) {
            lineSeries.setColorEach(isSelected);
        }
        lineSeries.repaint();
        colorEachLineButton.setEnabled(colorEachPointButton.isSelected());
    }

    protected void initComponents() {
        super.initComponents();

        lineSeries = new com.steema.teechart.styles.Line(chart1.getChart());
        lineSeries.setColorEach(true);
        lineSeries.setColorEachLine(false);
        SeriesPointer tmpPointer = lineSeries.getPointer();
        tmpPointer.setInflateMargins(true);
        tmpPointer.setStyle(PointerStyle.RECTANGLE);
        tmpPointer.setVisible(true);
        lineSeries.fillSampleValues(20);
        colorEachLineButton = new JCheckBox("Color each line");
        colorEachPointButton = new JCheckBox("Color each point");
        colorEachPointButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(colorEachLineButton);
            tmpPane.add(colorEachPointButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
}
