/*
 * DrawCanvasDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.pointer;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Points;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class DrawCanvasDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private Points pointSeries;

    /** Creates a new instance of DrawCanvasDemo */
    public DrawCanvasDemo() {
        super();
        editButton.addActionListener(this);
        view3DButton.addItemListener(this);

        chart1.addMouseMotionListener( new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                int tmp = pointSeries.clicked(e.getX(),e.getY());
                if (tmp != -1) {          //TODO correct place to draw
                    pointSeries.getPointer().draw(
                            chart1.getGraphics3D(),
                            chart1.getAspect().getView3D(),
                            5,5,
                            10,10,
                            pointSeries.getValueColor(tmp),
                            pointSeries.getPointer().getStyle()
                    );
                }
            }
        });

    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editChart(chart1.getChart());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = e.getStateChange() == ItemEvent.SELECTED;
        if (source == view3DButton) {
            chart1.getAspect().setView3D(isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();

        pointSeries = new com.steema.teechart.styles.Points(chart1.getChart());
        pointSeries.setColorEach(true);
        pointSeries.fillSampleValues(10);

        pointerLabel = new JLabel();

        editButton = new JButton("Edit...");
        view3DButton = new JCheckBox("3D");
        view3DButton.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setChart3DPercent(10);

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(new JLabel("Pointer displayed here:"));
            tmpPane.add(pointerLabel);
            tmpPane.add(Box.createHorizontalStrut(100));
            tmpPane.add(view3DButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JCheckBox view3DButton;
    private JLabel pointerLabel;
}
