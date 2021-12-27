/*
 * DirectionDemo.java
 *
 * <p>Copyright: (c) 2004-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.zoom;
import com.steema.teechart.ZoomDirections;
import com.steema.teechart.styles.Line;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import features.utils.EnumStrings;

/**
 *
 * @author tom
 */
public class DirectionDemo extends ChartSamplePanel
        implements ActionListener {

    /** Creates a new instance of MinPixelsDemo */
    public DirectionDemo() {
        super();
        directionList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == directionList) {
            switch (directionList.getSelectedIndex()) {
                case 0: chart1.getZoom().setDirection(ZoomDirections.HORIZONTAL); break;
                case 1: chart1.getZoom().setDirection(ZoomDirections.VERTICAL); break;
                case 2: chart1.getZoom().setDirection(ZoomDirections.BOTH); break;
            }
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setChart3DPercent(15);
    }

    protected void initComponents() {
        super.initComponents();
        Line series = new Line(chart1.getChart());
        series.fillSampleValues(40);

        directionList = new JComboBox(EnumStrings.DIRECTIONS);
        directionList.setSelectedIndex(chart1.getZoom().getDirection().getValue());
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Zoom Direction:");
            tmpLabel.setDisplayedMnemonic('D');
            tmpLabel.setLabelFor(directionList);
            tmpPane.add(tmpLabel);
            tmpPane.add(directionList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(new JLabel("<html><b>Drag to zoom...</b></html>"));
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JComboBox directionList;
}
