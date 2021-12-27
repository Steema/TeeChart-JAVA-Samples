/*
 * BitmapDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.colorgrid;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.HatchStyle;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.styles.ColorGrid;
import com.steema.teechart.styles.PaletteStyle;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class BitmapDemo extends ChartSamplePanel
    implements ActionListener {

    private ColorGrid series;

    /** Creates a new instance of BitmapDemo */
    public BitmapDemo() {
        super();
        zoomInButton.addActionListener(this);
        zoomOutButton.addActionListener(this);
        colorList.addActionListener(this);
    }

     public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == zoomInButton) {
            chart1.getZoom().zoomPercent(105);
        } else if (source == zoomOutButton) {
            chart1.getZoom().zoomPercent(95);
        } else if (source == colorList) {
            switch (colorList.getSelectedIndex()) {
                case 0: {
                    setFlower();
                    break;
                }
                case 1: {
                    for (int t=0; t < series.getCount(); t++) {
                        //series.setValueColor(t) = Color.EMPTY;
                    }
                    series.setUseColorRange(true);
                    series.setUsePalette(false);
                    break;
                }
                case 2: {
                    for (int t=0; t < series.getCount(); t++) {
                        //series.setValueColor(t) = Color.EMPTY; //TODO clTeeColor
                    }
                    series.setUseColorRange(false);
                    series.setUsePalette(true);
                    break;
                }
            }
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getLegend().setVisible(false);
        chart1.getPanel().setMarginLeft(10);
        chart1.getAspect().setView3D(false);
        chart1.getPanel().setMarginLeft(10);
        chart1.getPanel().setMarginRight(10);
        chart1.getAspect().setPerspective(53);
        chart1.getZoom().getBrush().setColor(Color.YELLOW);
        chart1.getZoom().getBrush().setStyle(HatchStyle.SOLIDDIAMOND);
        chart1.getZoom().getPen().setColor(Color.BLACK);
        chart1.getZoom().getPen().setWidth(3);
    }

    protected void initComponents() {
        super.initComponents();

        series = new ColorGrid(chart1.getChart());
        series.getPen().setVisible(false);
        series.setUseColorRange(false);
        series.setUsePalette(true);
        series.setPaletteStyle(PaletteStyle.STRONG);
        series.setPaletteSteps(10);

        zoomInButton = new JButton("+ Zoom");
        zoomOutButton = new JButton("- Zoom");
        gridButton = new ButtonPen(series.getPen(), "Grid...");
        imageLabel = new JLabel();
        colorList = new JComboBox( new String[] {"Original colors","Color range","Color palette"});
        colorList.setMaximumSize(new Dimension(0, 25));
        setFlower();
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(zoomInButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(zoomOutButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(colorList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(gridButton);
            tmpPane.add(Box.createHorizontalStrut(30));
            tmpPane.add(imageLabel);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void setFlower() {
        ImageIcon tmpIcon = new ImageIcon(ChartSamplePanel.class.getResource(URL_BITMAP));
        imageLabel.setIcon(tmpIcon);
//TODO        series.setBitmap(tmpIcon.getImage());
    }

    private ButtonPen gridButton;
    private JButton zoomInButton, zoomOutButton;
    private JComboBox colorList;
    private JLabel imageLabel;
    private final static String URL_BITMAP = "images/flower.jpg";
}
