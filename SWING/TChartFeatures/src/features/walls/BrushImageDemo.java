/*
 * BrushImageDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.walls;

import com.steema.teechart.ImageMode;
import com.steema.teechart.styles.ImageBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import com.steema.teechart.editors.BrushEditor;

/**
 *
 * @author tom
 */
public class BrushImageDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private ImageBar series;

    /**
     * Creates a new instance of BrushImageDemo
     */
    public BrushImageDemo() {
        super();
        editButton.addActionListener(this);
        backImageButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            BrushEditor.edit(chart1.getWalls().getBack().getBrush());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == backImageButton) {
            chart1.getWalls().getBack().setTransparent(!isSelected);
            chart1.refreshControl();
            chart1.invalidate();
            chart1.repaint();
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Back wall with image");
        chart1.getWalls().getBack().setTransparent(false);
        chart1.getWalls().getBack().getBrush().loadImage(ChartSamplePanel.class.getResource(URL_BACKIMAGE));
        chart1.getWalls().getBack().getBrush().setImageMode(ImageMode.TILE);
    }

    protected void initComponents() {
        super.initComponents();
        editButton = new JButton("Edit...");
        backImageButton = new JCheckBox("Back wall image");
        backImageButton.setSelected(!chart1.getWalls().getBack().getTransparent());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(backImageButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JCheckBox backImageButton;
    private JButton editButton;
    private final static String URL_BACKIMAGE = "images/backwall.jpg";
}
