/*
 * PointsTreatNullsDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.pointer;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Points;
import com.steema.teechart.styles.TreatNullsStyle;
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
import javax.swing.JCheckBox;

/**
 *
 * @author narcis
 */
public class PointsTreatNullsDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private Points points1;

    /**
     * Creates a new instance of PointsTreatNulls Demo
     */
    public PointsTreatNullsDemo() {
        super();
        dontPaint.addActionListener(this);
        skip.addActionListener(this);
        ignore.addActionListener(this);
        autoVertAxis.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == dontPaint) {
            points1.setTreatNulls(TreatNullsStyle.DONOTPAINT);
        } else if (source == skip) {
            points1.setTreatNulls(TreatNullsStyle.SKIP);
        } else if (source == ignore) {
            points1.setTreatNulls(TreatNullsStyle.IGNORE);
        }
    }
    
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == autoVertAxis) {
            points1.getVertAxis().setAutomatic(isSelected);
            if (!points1.getVertAxis().getAutomatic()) {
                points1.getVertAxis().setMinMax(-10,30);
            }
            chart1.refreshControl();
        } 
    }

    protected void initComponents() {
        super.initComponents();
        
        points1 = new com.steema.teechart.styles.Points(chart1.getChart());
        points1.setColor(Color.fromArgb(224,77,44));
        points1.setTreatNulls(TreatNullsStyle.DONOTPAINT);
        FillSeries();
        points1.getVertAxis().setMinMax(-10, 30);

        dontPaint = new JRadioButton("Don't Paint");
        skip = new JRadioButton("Skip");
        ignore = new JRadioButton("Ignore");        

        ButtonGroup group = new ButtonGroup();
        group.add(dontPaint);
        group.add(skip);
        group.add(ignore);
        
        dontPaint.setSelected(true);
        
        autoVertAxis = new JCheckBox("Automatic vertical axis");
        autoVertAxis.setSelected(false);
    }
    
    private void FillSeries() {
        boolean  done = false;
        double y;
        for (int x = 0; x <= 5; x++) {
            y = (x % 2 == 0) ? 10 : 20;
            if (x == 3) {
                if (!done) {
                    points1.add(x, 0, Color.TRANSPARENT);
                }
                done = true;
            }
            else { 
                points1.add(x, y);
            }
        }        
    }

    protected void initGUI() {
        super.initGUI();

        chart1.getAspect().setView3D(false);
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
            tmpPane.add(autoVertAxis);
            tmpPane.add(Box.createHorizontalGlue());
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JRadioButton dontPaint, skip, ignore;
    private JCheckBox autoVertAxis;
}
