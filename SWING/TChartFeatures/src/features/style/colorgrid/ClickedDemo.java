/*
 * ClickedDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.colorgrid;

import com.steema.teechart.styles.ColorGrid;
import com.steema.teechart.styles.PaletteStyle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class ClickedDemo extends ChartSamplePanel {

    private ColorGrid series;

    /** Creates a new instance of ClickedDemo */
    public ClickedDemo() {
        super();
        chart1.addMouseMotionListener( new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                int tmp = series.clicked(e.getX(),e.getY());
                if (tmp == -1) {
                    clickedLabel.setText("");
                } else {
                    clickedLabel.setText(
                            "x: "+String.valueOf(series.getXValues().getValue(tmp))
                            + " z: "+String.valueOf(series.getZValues().getValue(tmp))
                            + " Value is: "+String.valueOf(series.getYValues().getValue(tmp))
                     );
                }
            }
        });
    }

    protected void initComponents() {
        super.initComponents();

        series = new ColorGrid(chart1.getChart());
        series.getPen().setVisible(false);
        series.setPaletteSteps(100);
        series.setPaletteStyle(PaletteStyle.STRONG);
        series.setUseColorRange(false);
        series.setUsePalette(true);
        series.fillSampleValues(100);

        clickedLabel = new JLabel();
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);
        chart1.getLegend().setVisible(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("ColorGrid series\nExample of mouse hit detection.");

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(new JLabel("Selected cell is: "));
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(clickedLabel);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JLabel clickedLabel;
}
