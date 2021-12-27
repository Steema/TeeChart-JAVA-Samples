/*
 * CustomPolarTreatNullsDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.polar;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.CustomPolar;
import com.steema.teechart.styles.Polar;
import com.steema.teechart.styles.Radar;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.TreatNullsStyle;
import com.steema.teechart.styles.WindRose;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

/**
 *
 * @author narcis
 */
public class CustomPolarTreatNullsDemo extends ChartSamplePanel
        implements ActionListener, ItemListener {

    private JComboBox treatNullsList, styleList;
    private JCheckBox nullCB, filledCB;
    private CustomPolar series;

    /**
     * Creates a new instance of CustomPolarTreatNulls Demo
     */
    public CustomPolarTreatNullsDemo() {
        super();
        treatNullsList.addActionListener(this);
        styleList.addActionListener(this);
        nullCB.addItemListener(this);
        filledCB.addItemListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == treatNullsList) {            
            int index = treatNullsList.getSelectedIndex();            
            
            if (index == 0) {
                series.setTreatNulls(TreatNullsStyle.DONOTPAINT);
            } else {
                if (index == 1) {
                    series.setTreatNulls(TreatNullsStyle.SKIP);
                } else {
                    series.setTreatNulls(TreatNullsStyle.IGNORE);
                }
            }
        }    
        else  if (source == styleList) {            
            int index = styleList.getSelectedIndex();            
            Series s = chart1.getSeries(0);
            
            if (index == 1) {
                try {
                    try {
                        Series.changeType(s, Radar.class);
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (InstantiationException ex) {
                    Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                if (index == 2) {
                    try {
                        try {
                            Series.changeType(s, WindRose.class);
                        } catch (NoSuchMethodException ex) {
                            Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalArgumentException ex) {
                            Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvocationTargetException ex) {
                            Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        try {
                            Series.changeType(s, Polar.class);
                        } catch (NoSuchMethodException ex) {
                            Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IllegalArgumentException ex) {
                            Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InvocationTargetException ex) {
                            Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (InstantiationException ex) {
                        Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(CustomPolarTreatNullsDemo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            chart1.getAspect().setView3D(false);
        } 
        
    }
    
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == nullCB) {
        series.setNull(5,nullCB.isSelected());
           series.repaint();
        } else if (source == filledCB) {
            series.getBrush().setVisible(isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();

        chart1.getSeries().add(new Polar());
        series = (CustomPolar)chart1.getSeries(0);
        series.setColor(Color.fromArgb(224,77,44));
        
        treatNullsList = new JComboBox();
        treatNullsList.addItem("Don't paint");
        treatNullsList.addItem("Skip");
        treatNullsList.addItem("Ignore");
        
        styleList = new JComboBox();
        styleList.addItem("Polar");
        styleList.addItem("Radar");
        styleList.addItem("Windrose");
        
        nullCB = new JCheckBox("Point 5 is null");
        filledCB = new JCheckBox("Filled");
        
        treatNullsList.setSelectedIndex(2);
        styleList.setSelectedIndex(0);
        nullCB.setSelected(true);
        filledCB.setSelected(true);
        
        series.fillSampleValues(10);
        series.setTreatNulls(TreatNullsStyle.IGNORE);        
        series.setNull(5,nullCB.isSelected());               
    }

    protected void initGUI() {
        super.initGUI();
        
        chart1.getAspect().setView3D(false);
        chart1.getAspect().setSmoothingMode(true);
                
        JPanel tmpPane = getButtonPane();
        {            
            JLabel tmpLabel1, tmpLabel2;
            tmpLabel2 = new JLabel("Style:");
            tmpLabel2.setDisplayedMnemonic('S');
            tmpLabel2.setLabelFor(styleList);
            tmpPane.add(tmpLabel2);
            tmpPane.add(styleList);
            tmpPane.add(Box.createHorizontalStrut(40));
            
            tmpLabel1 = new JLabel("Treat nulls");
            tmpLabel1.setDisplayedMnemonic('N');
            tmpLabel1.setLabelFor(treatNullsList);
            tmpPane.add(tmpLabel1);
            tmpPane.add(treatNullsList);
            tmpPane.add(Box.createHorizontalStrut(40));
            
            tmpPane.add(nullCB);
            tmpPane.add(Box.createHorizontalStrut(20));
            
            tmpPane.add(filledCB);            
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

}
