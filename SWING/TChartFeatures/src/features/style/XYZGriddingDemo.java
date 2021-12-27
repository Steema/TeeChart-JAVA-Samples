/*
 * XYZGriddingDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style;

import com.steema.teechart.misc.Utils;
import com.steema.teechart.styles.Custom3D;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Points3D;
import com.steema.teechart.styles.Surface;
import com.steema.teechart.tools.Rotate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class XYZGriddingDemo extends ChartSamplePanel
        implements ActionListener {

    private Points3D series1;
    private Surface series2;

    /** Creates a new instance of XYZGriddingDemo */
    public XYZGriddingDemo() {
        super();
        griddingButton.addActionListener(this);
        surfaceButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if ((source == griddingButton) || (source == surfaceButton)) {
            series1.setVisible(griddingButton.isSelected());
            series2.setVisible(surfaceButton.isSelected());
        }
    }

    protected void initComponents() {
        super.initComponents();
        new Rotate(chart1.getChart());

        series1 = new Points3D(chart1.getChart());
        series1.getLinePen().setVisible(false);
        series1.getPointer().setHorizSize(1);
        series1.getPointer().setVertSize(1);
        series1.getPointer().setInflateMargins(true);
        series1.getPointer().setStyle(PointerStyle.RECTANGLE);
        series1.getPointer().setVisible(true);

        series2 = new Surface(chart1.getChart());

        griddingButton = new JRadioButton("Original XYZ");
        surfaceButton = new JRadioButton("Surface");
        ButtonGroup group = new ButtonGroup();
        group.add(griddingButton);
        group.add(surfaceButton);
        surfaceButton.setSelected(true);
        series1.setVisible(griddingButton.isSelected());
        series2.setVisible(surfaceButton.isSelected());

        fill();
        gridding(series1, series2, 15);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getWalls().getBack().setTransparent(false);
        chart1.getLegend().setVisible(false);

        //@TODO: chart1.getAxes().getBottom().getGrid().setSmallDots(false);
        //@TODO: chart1.getAxes().getDepth().getGrid().setSmallDots(false);
        //@TODO: chart1.getAxes().getLeft().getGrid().setSmallDots(false);

//        try {
            chart1.getAxes().getDepth().getLabels().setSeparation(60);
//        }  catch (ChartException e) {
//            System.out.print(e.getMessage());
//            e.printStackTrace();
//        }

        chart1.getAxes().getDepth().setVisible(true);

        chart1.getAspect().setChart3DPercent(90);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(56);
        chart1.getAspect().setZoom(68);
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add( new JLabel("View: "));
            tmpPane.add(griddingButton);
            tmpPane.add(surfaceButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    /**
     * Add many random XYZ points to Series1
     */
    private void fill() {
        series1.clear();
        int m = 50;
        double tmpX, tmpZ;

        for (double x=-m; x <= +m; x++) {
            tmpX = Utils.sqr(x/30);
            for (double z=-m; z <= +m; z++) {
                tmpZ = Utils.sqr(z/30);
                tmpZ = Math.sqrt(tmpX+tmpZ);
                series1.add(x, 4*Math.cos(3*tmpZ)*Math.exp(-0.5*tmpZ), z);
            }
        }
    }

    private static double getClosestValue(Custom3D source, double x, double z) {
        double tmpResult = 0;
        double maxDist = MAX_DISTANCE;
        int closer = -1;

        double tmpX, tmpZ, dist;
        for (int t=0; t < source.getCount(); t++) {
            tmpX = source.getXValues().getValue(t)-x;
            tmpZ = source.getZValues().getValue(t)-z;
            dist = Math.sqrt(Utils.sqr(tmpX)+Utils.sqr(tmpZ));
            if (dist < maxDist) {
                maxDist = dist;
                closer = t;
            }
        }
        if (closer != -1) {
            tmpResult = source.getYValues().getValue(closer);
        }

        return tmpResult;
    }

    private static void gridding(Custom3D source, Custom3D dest, int gridSize) {
        double tmpMinX = source.getXValues().getMinimum();
        double tmpMinZ = source.getXValues().getMinimum();

        double tmpXFactor = source.getXValues().getRange() / gridSize;
        double tmpZFactor = source.getXValues().getRange() / gridSize;

        dest.clear();

        /* loop all grid cells */
        double tmpX, tmpZ;
        for (int x=1; x <= gridSize; x++) {
            tmpX = tmpMinX+((x-1)*tmpXFactor);
            for (int z=1; z <= gridSize; z++) {
                tmpZ = tmpMinZ+ ((z-1)*tmpZFactor);
                dest.add(x, getClosestValue(source, tmpX, tmpZ) , z);
            }
        }
    }

    private JRadioButton griddingButton, surfaceButton;
    private final static double MAX_DISTANCE=1E+300;
}
