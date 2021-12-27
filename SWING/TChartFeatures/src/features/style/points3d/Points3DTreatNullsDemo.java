/*
 * Points3DTreatNullsDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.points3d;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Points3D;
import com.steema.teechart.styles.TreatNullsStyle;
import com.steema.teechart.misc.Utils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import features.ChartSamplePanel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JCheckBox;

/**
 *
 * @author narcis
 */
public class Points3DTreatNullsDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private Points3D points3D1;

    /**
     * Creates a new instance of Points3DTreatNulls Demo
     */
    public Points3DTreatNullsDemo() {
        super();
        dontPaint.addActionListener(this);
        skip.addActionListener(this);
        ignore.addActionListener(this);
        view3D.addItemListener(this);
        refreshButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == dontPaint) {
            points3D1.setTreatNulls(TreatNullsStyle.DONOTPAINT);
        } else if (source == skip) {
            points3D1.setTreatNulls(TreatNullsStyle.SKIP);
        } else if (source == ignore) {
            points3D1.setTreatNulls(TreatNullsStyle.IGNORE);
        } else if (source == refreshButton) {
            points3D1.clear();
            addPoints();            
        }
    }
    
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == view3D) {
            chart1.getAspect().setView3D(isSelected);
        } 
    }

    protected void initComponents() {
        super.initComponents();
        
        points3D1 = new com.steema.teechart.styles.Points3D(chart1.getChart());
        points3D1.setColor(Color.fromArgb(224,77,44));
//        points3D1.setTreatNulls(TreatNullsStyle.DONOTPAINT);
        addPoints();

        dontPaint = new JRadioButton("Don't Paint");
        skip = new JRadioButton("Skip");
        ignore = new JRadioButton("Ignore");        

        ButtonGroup group = new ButtonGroup();
        group.add(dontPaint);
        group.add(skip);
        group.add(ignore);
        
        dontPaint.setSelected(true);
        
        view3D = new JCheckBox("View3D");
        view3D.setSelected(true);
        
        refreshButton = new JButton("Refresh...");
    }
    
    private void addPoints() {
            int length = 55;

            double[] xValues = new double[length], yValues = new double[length],
                    zValues = new double[length];
            Color[] colorValues = new Color[length];

            Random rnd = new Random();
            double tmp, oldTemp = 0;

            points3D1.setDefaultNullValue(0);

            for (int i = 0; i < length; i++) {
                    tmp = rnd.nextDouble();
                    while (tmp == oldTemp) {
                            tmp = rnd.nextDouble();
                    }

                    if ((i > 0) && (i % 5 == 0)) {
                            xValues[i] = i;
                            yValues[i] = 0;
                            zValues[i] = tmp;
                            colorValues[i] = Color.TRANSPARENT;
                    }
                    else {
                            xValues[i] = i;
                            yValues[i] = tmp;
                            zValues[i] = tmp;
                            colorValues[i] = Color.fromArgb(255 - 
                                    Utils.round(255 * tmp),
                                    Utils.round(255 * tmp), 
                                    255 - Utils.round(255 * tmp));
                    }

                    oldTemp = tmp;
            }

            points3D1.add(xValues, yValues, zValues, colorValues);
    }

    protected void initGUI() {
        super.initGUI();

        chart1.getAspect().setView3D(true);
        chart1.getAspect().setSmoothingMode(true);
        chart1.getHeader().setVisible(true);
        chart1.getHeader().getFont().setColor(Color.NAVY);
        chart1.setText("Null points support");
        
        chart1.getPanel().getGradient().setEndColor(Color.fromArgb(254,21,60,89));
        chart1.getPanel().getGradient().setStartColor(Color.fromArgb(254,255,255,255));
        chart1.getPanel().getGradient().setUseMiddle(false);
        chart1.getPanel().getGradient().setVisible(true);
            
        chart1.getWalls().getBack().setTransparent(false);
        chart1.getWalls().getBack().getGradient().setVisible(true);
        chart1.getWalls().getBack().getGradient().setUseMiddle(false);
        chart1.getWalls().getBack().getGradient().setStartColor(Color.fromArgb(234, 234, 234));
        chart1.getWalls().getBack().getGradient().setEndColor(Color.WHITE);

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Treat Nulls:");
            tmpLabel.setDisplayedMnemonic('N');
            tmpPane.add(tmpLabel);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(dontPaint);
            tmpPane.add(skip);
            tmpPane.add(ignore);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(view3D);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(refreshButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JRadioButton dontPaint, skip, ignore;
    private JCheckBox view3D;
    private JButton refreshButton;
}
