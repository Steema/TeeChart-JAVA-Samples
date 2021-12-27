/*
 * AreaTreatNullsDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.area;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.TreatNullsStyle;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;

/**
 *
 * @author narcis
 */
public class AreaTreatNullsDemo extends ChartSamplePanel
        implements ActionListener {

    private Area area1;
    private JComboBox treatNullsList;

    /**
     * Creates a new instance of AreaTreatNulls Demo
     */
    public AreaTreatNullsDemo() {
        super();
        treatNullsList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == treatNullsList) {            
            int index = treatNullsList.getSelectedIndex();
            
            if (index == 0) {
                area1.setTreatNulls(TreatNullsStyle.DONOTPAINT);
            } else {
                if (index == 1) {
                    area1.setTreatNulls(TreatNullsStyle.SKIP);
                } else {
                    area1.setTreatNulls(TreatNullsStyle.IGNORE);
                }
            }
        }    
    }

    protected void initComponents() {
        super.initComponents();

        area1 = new Area(chart1.getChart());
        area1.setColor(Color.fromArgb(224,77,44));
        area1.getAreaLines().setVisible(true);
        area1.setColorEachLine(true);
        area1.getVertAxis().setMinMax(-10, 30);
        FillSeries();
        area1.setTreatNulls(TreatNullsStyle.DONOTPAINT);
        
        treatNullsList = new JComboBox();
        treatNullsList.addItem("Don't paint");
        treatNullsList.addItem("Skip");
        treatNullsList.addItem("As value");
        treatNullsList.setSelectedIndex(0);
    }

    protected void initGUI() {
        super.initGUI();
        
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setView3D(false);
        chart1.getAspect().setSmoothingMode(true);
        
        chart1.getPanel().getGradient().setEndColor(Color.fromArgb(254,21,60,89));
        chart1.getPanel().getGradient().setStartColor(Color.fromArgb(254,255,255,255));
        chart1.getPanel().getGradient().setUseMiddle(false);
        chart1.getPanel().getGradient().setVisible(true);
            
        chart1.getWalls().getBack().setTransparent(false);
        chart1.getWalls().getBack().getGradient().setVisible(true);
        chart1.getWalls().getBack().getGradient().setUseMiddle(false);
        chart1.getWalls().getBack().getGradient().setStartColor(Color.fromArgb(234, 234, 234));
        chart1.getWalls().getBack().getGradient().setEndColor(Color.WHITE);
        
        JPanel tmpPane = getButtonPane();
        {            
            JLabel tmpLabel1;
            tmpLabel1 = new JLabel("Treat nulls:");
            tmpLabel1.setDisplayedMnemonic('N');
            tmpLabel1.setLabelFor(treatNullsList);
            tmpPane.add(tmpLabel1);
            tmpPane.add(treatNullsList);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void FillSeries() {
        boolean done = false;
        double y;
        for (int x = 0; x <= 10; x++) {
            y = (x % 2 == 0) ? 10 : 20;
            if ((x >= 3) && (x <= 7)) {
                if (!done) {
                    area1.add(x, 0, Color.TRANSPARENT);
                }
                done = true;
            }
            else {
                area1.add(x, y);
            }
        }
    }

}
