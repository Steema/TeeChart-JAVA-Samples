/*
 * LineTreatNullsDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.line;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.CustomPoint;
import com.steema.teechart.styles.HorizLine;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.TreatNullsStyle;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

/**
 *
 * @author narcis
 */
public class LineTreatNullsDemo extends ChartSamplePanel
        implements ActionListener {

    private JComboBox treatNullsList;
    private JRadioButton lineCB, horizLineCB;

    /**
     * Creates a new instance of LineTreatNulls Demo
     */
    public LineTreatNullsDemo() {
        super();
        treatNullsList.addActionListener(this);
        lineCB.addActionListener(this);
        horizLineCB.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        Series s = chart1.getSeries(0);

        if (source == treatNullsList) {            
            int index = treatNullsList.getSelectedIndex();            
            
            if (index == 0) {
                ((CustomPoint)s).setTreatNulls(TreatNullsStyle.DONOTPAINT);
            } else {
                if (index == 1) {
                    ((CustomPoint)s).setTreatNulls(TreatNullsStyle.SKIP);
                } else {
                    ((CustomPoint)s).setTreatNulls(TreatNullsStyle.IGNORE);
                }
            }
        }    
        else {
            if (source == lineCB) {
                try {
                    try {
                        Series.changeType(s, Line.class);
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(LineTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(LineTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(LineTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (InstantiationException ex) {
                    Logger.getLogger(LineTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(LineTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (source == horizLineCB) {
                try {
                    try {
                        Series.changeType(s, HorizLine.class);
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(LineTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(LineTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(LineTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (InstantiationException ex) {
                    Logger.getLogger(LineTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(LineTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
    }

    protected void initComponents() {
        super.initComponents();

        chart1.getSeries().add(new Line());
        CustomPoint series = (CustomPoint)chart1.getSeries(0);
        series.setColor(Color.fromArgb(224,77,44));
        series.getPointer().setVisible(true);
        
        series.fillSampleValues(25);
        series.setNull(12);
        series.getYValues().setValue(12, 0);
        series.setNull(13);
        series.getYValues().setValue(13, 0);
        
        treatNullsList = new JComboBox();
        treatNullsList.addItem("Don't paint");
        treatNullsList.addItem("Skip");
        treatNullsList.addItem("As value");
        
        lineCB = new JRadioButton("Line");
        horizLineCB = new JRadioButton("Horizontal Line");

        ButtonGroup group = new ButtonGroup();
        group.add(lineCB);
        group.add(horizLineCB);
        
        series.setTreatNulls(TreatNullsStyle.SKIP);
        treatNullsList.setSelectedIndex(1);
        lineCB.setSelected(true);
    }

    protected void initGUI() {
        super.initGUI();
        
        chart1.getLegend().setVisible(false);
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
            JLabel tmpLabel1, tmpLabel2;
            tmpLabel1 = new JLabel("Treat nulls:");
            tmpLabel1.setDisplayedMnemonic('N');
            tmpLabel1.setLabelFor(treatNullsList);
            tmpPane.add(tmpLabel1);
            tmpPane.add(treatNullsList);
            tmpPane.add(Box.createHorizontalStrut(50));
            
            tmpLabel2 = new JLabel("Style:");
            tmpLabel2.setDisplayedMnemonic('S');
            tmpPane.add(tmpLabel2);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(lineCB);
            tmpPane.add(horizLineCB);
            
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

}
