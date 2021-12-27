/*
 * ImageDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.misc.ImageUtils;
import com.steema.teechart.styles.Points;
import com.steema.teechart.tools.ChartImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class ImageDemo extends ChartSamplePanel
    implements ActionListener, ChangeListener, ItemListener {

    private ChartImage tool;

    /** Creates a new instance of ImageDemo */
    public ImageDemo() {
        super();
        oldZoom = -1;
        editButton.addActionListener(this);
        showImageButton.addItemListener(this);
        zoomSlider.addChangeListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editTool(tool);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == showImageButton) {
            tool.setActive(isSelected);
        }
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            int zoom = (int)source.getValue();
            if (zoom > oldZoom) {
                chart1.getZoom().zoomPercent(105.0);
            } else {
                chart1.getZoom().zoomPercent(95.0);
            }
            oldZoom = zoom;
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(false);
        chart1.getAspect().setView3D(false);
    }

    protected void initComponents() {
        super.initComponents();

        //Contour series1 = new com.steema.teechart.styles.Contour(chart1.getChart());
        //series1.getMarks().setVisible(false);
        //series1.fillSampleValues(20);

        Points series2 = new com.steema.teechart.styles.Points(chart1.getChart());
        series2.setColorEach(true);
        series2.getPointer().setHorizSize(2);
        series2.getPointer().setVertSize(2);
        series2.getPointer().setInflateMargins(false);
        series2.getPointer().setVisible(true);
        series2.clear();
        Random generator = new Random();
        for (int t=0; t < 50; t++) {
            series2.add(1+generator.nextInt(20), 1+generator.nextInt(20));
        }

        tool = new com.steema.teechart.tools.ChartImage(chart1.getChart());
        tool.setImage(ImageUtils.toImage(ImageUtils.getImage(ChartSamplePanel.class.getResource(URL_BACKIMAGE),chart1)));

        editButton = new JButton("Edit...");
        showImageButton = new JCheckBox("Image");
        showImageButton.setSelected(true);
        zoomSlider = new JSlider(JSlider.HORIZONTAL, 50, 200, 100);
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            JLabel tmpLabel = new JLabel("Zoom:");
            tmpLabel.setDisplayedMnemonic('Z');
            tmpLabel.setLabelFor(zoomSlider);
            tmpPane.add(tmpLabel);
            tmpPane.add(zoomSlider);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(showImageButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JCheckBox showImageButton;
    private JSlider zoomSlider;

    private int oldZoom;
    private final static String URL_BACKIMAGE = "images/toolimage.jpg";
}
