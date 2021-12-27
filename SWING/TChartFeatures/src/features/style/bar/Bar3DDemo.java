/*
 * Bar3DDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bar;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Bar3D;
import com.steema.teechart.styles.BarStyle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class Bar3DDemo extends ChartSamplePanel
    implements ActionListener {

    private Bar3D series1;

    /**
     * Creates a new instance of Bar3DDemo
     */
    public Bar3DDemo() {
        super();
        styleList.addActionListener(this);
        editButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == styleList) {
            switch (styleList.getSelectedIndex()) {
                case 0: series1.setBarStyle(BarStyle.RECTANGLE); break;
                case 1: series1.setBarStyle(BarStyle.PYRAMID); break;
                case 2: series1.setBarStyle(BarStyle.INVPYRAMID); break;
                case 3: series1.setBarStyle(BarStyle.CYLINDER); break;
                case 4: series1.setBarStyle(BarStyle.ELLIPSE); break;
                case 5: series1.setBarStyle(BarStyle.ARROW); break;
                case 6: series1.setBarStyle(BarStyle.RECTGRADIENT); break;
                case 7: series1.setBarStyle(BarStyle.CONE); break;
            }
        } else if (source == editButton) {
            ChartEditor.editSeries(series1);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Bar 3D series");
    }

    protected void initComponents() {
        super.initComponents();

        series1 = new Bar3D(chart1.getChart());
        series1.add( 0, 250, 200, "A", Color.RED );
        series1.add( 1,  10, 200, "B", Color.GREEN );
        series1.add( 2,  90, 100, "C", Color.YELLOW );
        series1.add( 3,  30,  50, "D", Color.BLUE );
        series1.add( 4,  70, 150, "E", Color.WHITE );
        series1.add( 5, 120, 150, "F", Color.SILVER );
        series1.setColorEach(true);
        series1.getMarks().setArrowLength(20);
        series1.getMarks().setVisible(true);
        series1.setBarStyle(BarStyle.RECTGRADIENT);
        series1.setBarWidthPercent(90);
        series1.getGradient().setDirection(GradientDirection.HORIZONTAL);
        series1.getGradient().setStartColor(Color.YELLOW);

        editButton = new JButton("Edit...");
        styleList = new JComboBox(
                new String[] {
                    "Rectangle",
                    "Pyramid",
                    "Inverted Pyramid",
                    "Cylinder",
                    "Ellipse",
                    "Arrow",
                    "Rect. Gradient",
                    "Cone"});
        styleList.setSelectedIndex(6);
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Styles:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(styleList);
            tmpPane.add(tmpLabel);
            tmpPane.add(styleList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JComboBox styleList;
}
