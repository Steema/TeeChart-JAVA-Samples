/*
 * PieFocusDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.tool;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.CustomBar;
import com.steema.teechart.styles.CustomBar.MarksLocation;
import com.steema.teechart.styles.HorizBar;
import com.steema.teechart.styles.Series;

import com.steema.teechart.themes.ThemesList;
import features.ChartSamplePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.event.ChangeListener;

/**
 * @author tom
 *
 */
public class MarksOnBarDemo extends ChartSamplePanel implements ActionListener, ItemListener, ChangeListener {

    private javax.swing.JCheckBox jMarksOnBar;
    private javax.swing.JComboBox jOrientation;
    private javax.swing.JComboBox jLocation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSpinner jAngle;
    private javax.swing.JSpinner jFontSize;
    private HorizBar bar1;

    public MarksOnBarDemo() {
        super();
        jAngle.addChangeListener(this);
        jFontSize.addChangeListener(this);

    }

    protected void initChart() {
        super.initChart();
        bar1 = new HorizBar(chart1.getChart());
        bar1.fillSampleValues(6);
        bar1.getPen().setVisible(false);
        chart1.getAspect().setView3D(false);
        bar1.setMarksOnBar(true);
        ThemesList.applyTheme(chart1.getChart(), 1);
        bar1.getGradient().setStartColor(Color.fromArgb(230, 200, 90));
        bar1.getGradient().setMiddleColor(Color.fromArgb(226, 242, 170));
        bar1.getGradient().setEndColor(Color.fromArgb(230, 200, 90));
        bar1.getGradient().setVisible(true);
        bar1.getMarks().getFont().setBold(true);
        jLabel1 = new javax.swing.JLabel();
        jOrientation = new javax.swing.JComboBox();
        jMarksOnBar = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLocation = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jAngle = new javax.swing.JSpinner();
        jFontSize = new javax.swing.JSpinner();
        jMarksOnBar.setSelected(true);
        jOrientation.addActionListener(this);
        jLabel1.setText("Bar Style: ");
        jOrientation.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"horizontal", "vertical"}));

        jMarksOnBar.addItemListener(this);
        jMarksOnBar.setText("Marks on Bar");

        jLabel2.setText("Location: ");

        jLocation.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"end", "center", "start"}));
        jLocation.addActionListener(this);

        jLabel3.setText("Angle");

        jLabel4.setText("Font Size:");
        bar1.getMarks().getFont().setSize(17);
        bar1.getMarks().getFont().setColor(Color.BLACK);
        jAngle.setModel(new javax.swing.SpinnerNumberModel(bar1.getMarks().getAngle(), 0, 360, 90));
        jFontSize.setModel(new javax.swing.SpinnerNumberModel(bar1.getMarks().getFont().getSize(), 0, 20, 1));


    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(jLabel1);
            tmpPane.add(jOrientation);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(jMarksOnBar);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(jLabel2);
            tmpPane.add(jLocation);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(jLabel3);
            tmpPane.add(jAngle);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(jLabel4);
            tmpPane.add(jFontSize);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        double auxAngle = (Double) jAngle.getValue();
        int auxFontSize = (Integer) jFontSize.getValue();

        if (source == jOrientation) {
            switch (jOrientation.getSelectedIndex()) {
                case 0:
                    Series s = chart1.getSeries(0);
                    try {
                        Series.changeType(s, HorizBar.class);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(MarksOnBarDemo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(MarksOnBarDemo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(MarksOnBarDemo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(MarksOnBarDemo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(MarksOnBarDemo.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                case 1:
                    s = chart1.getSeries(0);
                    try {
                        Series.changeType(s, Bar.class);
                    } catch (InstantiationException ex) {
                        Logger.getLogger(MarksOnBarDemo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException ex) {
                        Logger.getLogger(MarksOnBarDemo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NoSuchMethodException ex) {
                        Logger.getLogger(MarksOnBarDemo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalArgumentException ex) {
                        Logger.getLogger(MarksOnBarDemo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvocationTargetException ex) {
                        Logger.getLogger(MarksOnBarDemo.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
            }

            if (jMarksOnBar.isSelected()) {
                ((CustomBar) chart1.getSeries(0)).setMarksOnBar(true);
                ((CustomBar) chart1.getSeries(0)).getMarks().getFrame().setVisible(false);
                ((CustomBar) chart1.getSeries(0)).getMarks().getBrush().setVisible(false);
                ((CustomBar) chart1.getSeries(0)).getMarks().getFont().setColor(Color.white);
                jAngle.setValue(auxAngle);
                jFontSize.setValue(auxFontSize);
                ((CustomBar) chart1.getSeries(0)).getMarks().setAngle((Double) jAngle.getValue());
                ((CustomBar) chart1.getSeries(0)).getMarks().getFont().setSize((Integer) jFontSize.getValue());
                ((CustomBar) chart1.getSeries(0)).getGradient().setStartColor(Color.fromArgb(230, 200, 90));
                ((CustomBar) chart1.getSeries(0)).getGradient().setMiddleColor(Color.fromArgb(226, 242, 170));
                ((CustomBar) chart1.getSeries(0)).getGradient().setEndColor(Color.fromArgb(230, 200, 90));
                ((CustomBar) chart1.getSeries(0)).getGradient().setVisible(true);
                ((CustomBar) chart1.getSeries(0)).getMarks().getFont().setSize(17);
                ((CustomBar) chart1.getSeries(0)).getMarks().getFont().setColor(Color.BLACK);
                ((CustomBar) chart1.getSeries(0)).getMarks().getFont().setBold(true);
                switch (jLocation.getSelectedIndex()) {
                    case 0:
                        ((CustomBar) chart1.getSeries(0)).setMarksLocation(MarksLocation.End);
                        break;
                    case 1:
                        ((CustomBar) chart1.getSeries(0)).setMarksLocation(MarksLocation.Center);
                        break;
                    case 2:
                        ((CustomBar) chart1.getSeries(0)).setMarksLocation(MarksLocation.Start);
                        break;

                }

            } else {
                ((CustomBar) chart1.getSeries(0)).setMarksOnBar(false);
                ((CustomBar) chart1.getSeries(0)).getMarks().getFrame().setVisible(false);
                ((CustomBar) chart1.getSeries(0)).getMarks().getBrush().setVisible(false);
                ((CustomBar) chart1.getSeries(0)).getMarks().getFont().setColor(Color.white);
                jAngle.setValue(auxAngle);
                jFontSize.setValue(auxFontSize);
                ((CustomBar) chart1.getSeries(0)).getMarks().setAngle((Double) jAngle.getValue());
                ((CustomBar) chart1.getSeries(0)).getMarks().getFont().setSize((Integer) jFontSize.getValue());
                ((CustomBar) chart1.getSeries(0)).getGradient().setStartColor(Color.fromArgb(230, 200, 90));
                ((CustomBar) chart1.getSeries(0)).getGradient().setMiddleColor(Color.fromArgb(226, 242, 170));
                ((CustomBar) chart1.getSeries(0)).getGradient().setEndColor(Color.fromArgb(230, 200, 90));
                ((CustomBar) chart1.getSeries(0)).getGradient().setVisible(true);
                ((CustomBar) chart1.getSeries(0)).getMarks().getFont().setSize(17);
                ((CustomBar) chart1.getSeries(0)).getMarks().getFont().setColor(Color.BLACK);
                ((CustomBar) chart1.getSeries(0)).getMarks().getFont().setBold(true);
            }




        } else if (source == jLocation) {
            switch (jLocation.getSelectedIndex()) {
                case 0:
                    ((CustomBar) chart1.getSeries(0)).setMarksLocation(MarksLocation.End);
                    break;
                case 1:
                    ((CustomBar) chart1.getSeries(0)).setMarksLocation(MarksLocation.Center);
                    break;
                case 2:
                    ((CustomBar) chart1.getSeries(0)).setMarksLocation(MarksLocation.Start);
                    break;
            }
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object aux = e.getSource();
        if (aux == jMarksOnBar) {
            if (jMarksOnBar.isSelected()) {
                ((CustomBar) chart1.getSeries(0)).setMarksOnBar(true);
                jLocation.setEnabled(true);


            } else {
                ((CustomBar) chart1.getSeries(0)).setMarksOnBar(false);
                jLocation.setEnabled(false);
            }

        }
    }

    public void stateChanged(javax.swing.event.ChangeEvent e) {
        Object aux = e.getSource();
        if (aux == jAngle) {
            ((CustomBar) chart1.getSeries(0)).getMarks().setAngle((Double) jAngle.getValue());
        } else if (aux == jFontSize) {
            ((CustomBar) chart1.getSeries(0)).getMarks().getFont().setSize((Integer) jFontSize.getValue());
        }
    }
}
