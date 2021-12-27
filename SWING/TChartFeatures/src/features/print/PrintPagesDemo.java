/*
 * PrintPagesDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.print;

import com.steema.teechart.Page;
import com.steema.teechart.editors.ButtonPageNavigator;
import com.steema.teechart.editors.AbstractPageNavigator;
import com.steema.teechart.events.ChangeEvent;
import com.steema.teechart.events.ChangeListener;
import com.steema.teechart.styles.Bar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class PrintPagesDemo extends ChartSamplePanel
    implements ActionListener, ChangeListener {

    private AbstractPageNavigator navigator;

    /** Creates a new instance of PrintPagesDemo */
    public PrintPagesDemo() {
        super();
        printPagesButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == printPagesButton) {
            try {
                chart1.getPrinter().print();
            } catch (PrinterException ex) {
                System.out.println(ex);
            }
        }
    }

    public void stateChanged(ChangeEvent ce) {
        Object source = ce.getSource();
        if (source == chart1.getPage()) {
            updatePageLabel();
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getPage().setMaxPointsPerPage(5);
        chart1.getPage().addChangeListener(this);
    }

    protected void initComponents() {
        super.initComponents();
        navigator = new ButtonPageNavigator(chart1.getChart());

        Bar series = new Bar(chart1.getChart());
        series.getMarks().setArrowLength(20);
        series.setVisible(true);
        series.fillSampleValues(20);

        printPagesButton = new JButton("Print Pages...");
        pageLabel = new JLabel();
        updatePageLabel();
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(printPagesButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(navigator);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(pageLabel);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    protected void updatePageLabel() {
        Page p = chart1.getPage();
        pageLabel.setText(String.valueOf(p.getCurrent())+ " of "+String.valueOf(p.getCount()));
    }

    private JButton printPagesButton;
    private JLabel pageLabel;
}
