/*
 * FastLineTreatNullslDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.fastline;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.TreatNullsStyle;
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
 * @author narcis
 */
public class FastLineTreatNullslDemo extends ChartSamplePanel
    implements ActionListener {

    private FastLine fastLine1;

    /**
     * Creates a new instance of FastLineTreatNullslDemo
     */
    public FastLineTreatNullslDemo() {
        super();
        dontPaint.addActionListener(this);
        skip.addActionListener(this);
        ignore.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == dontPaint) {
            fastLine1.setTreatNulls(TreatNullsStyle.DONOTPAINT);
        } else if (source == skip) {
            fastLine1.setTreatNulls(TreatNullsStyle.SKIP);
        } else if (source == ignore) {
            fastLine1.setTreatNulls(TreatNullsStyle.IGNORE);
        }
    }

    protected void initComponents() {
        super.initComponents();
        
        fastLine1 = new com.steema.teechart.styles.FastLine(chart1.getChart());
        fastLine1.setColor(Color.fromArgb(224,77,44));
        fastLine1.setTreatNulls(TreatNullsStyle.DONOTPAINT);
        FillSeries();
        fastLine1.getVertAxis().setMinMax(-10, 30);

        dontPaint = new JRadioButton("Don't Paint");
        skip = new JRadioButton("Skip");
        ignore = new JRadioButton("Ignore");        

        ButtonGroup group = new ButtonGroup();
        group.add(dontPaint);
        group.add(skip);
        group.add(ignore);
        
        dontPaint.setSelected(true);
    }
    
    private void FillSeries() {
        boolean  done = false;
        double y;
        for (int x = 0; x <= 100; x++)
        {
            y = (x % 2 == 0) ? 10 : 20;
            if ((x >= 30) && (x <= 70))
            {
                if (!done) {
                    fastLine1.add(x, 0, Color.TRANSPARENT);
                }
                done = true;
            }
            else { 
                fastLine1.add(x, y);
            }
        }        
    }

    protected void initGUI() {
        super.initGUI();

        chart1.getAspect().setView3D(false);
        chart1.getAspect().setSmoothingMode(true);
        chart1.getHeader().setVisible(true);
        chart1.getHeader().getFont().setColor(Color.NAVY);
        chart1.setText("FastLine: null points support");
        
        chart1.getPanel().getGradient().setEndColor(Color.fromArgb(254,21,60,89));
        //chart1.getPanel().getGradient().setMiddleColor(Color.fromArgb(254,255,255,255));
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
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JRadioButton dontPaint, skip, ignore;
}
