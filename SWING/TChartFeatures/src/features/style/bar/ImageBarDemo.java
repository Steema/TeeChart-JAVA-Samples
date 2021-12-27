/*
 * ImageBarDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.bar;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ButtonColor;
import com.steema.teechart.misc.ImageUtils;
import com.steema.teechart.styles.ImageBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class ImageBarDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private ImageBar series;

    /** Creates a new instance of ImageBarDemo */
    public ImageBarDemo() {
        super();
        imageButton.addActionListener(this);
        tiledButton.addItemListener(this);
        colorEachButton.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == imageButton) {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Open");
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                setSeriesImage(new ImageIcon(chooser.getSelectedFile().getAbsolutePath()));
            }
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == tiledButton) {
            series.setImageTiled(isSelected);
        } else if (source == colorEachButton) {
            series.setColorEach(isSelected);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Image Bar series");
    }

    protected void initComponents() {
        super.initComponents();
        series = new ImageBar(chart1.getChart());
        series.setColor(Color.RED);
        series.fillSampleValues(6);
        series.getMarks().setArrowLength(20);
        series.getMarks().getCallout().getBrush().setColor(Color.BLACK);
        series.getMarks().getCallout().setLength(20);
        series.getMarks().setVisible(true);
        series.setImageTiled(true);

        imageButton = new JButton();
        colorButton = new ButtonColor("Color...", series.getColor());
        tiledButton = new JCheckBox("Tiled");
        tiledButton.setSelected(series.getImageTiled());
        colorEachButton = new JCheckBox("Color each");
        colorEachButton.setSelected(series.getColorEach());

        setSeriesImage(new ImageIcon(ChartSamplePanel.class.getResource(URL_IMAGEBAR)));
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(new JLabel("Image:"));
            tmpPane.add(imageButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(tiledButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(colorEachButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(colorButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    protected void setSeriesImage(ImageIcon icon) {
        imageButton.setIcon(icon);
        series.setImage(ImageUtils.toImage(icon.getImage()));
    }

    private ButtonColor colorButton;
    private JCheckBox tiledButton, colorEachButton;
    private JButton imageButton;
    private final static String URL_IMAGEBAR = "images/imagebar.png";
}
