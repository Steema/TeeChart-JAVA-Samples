/*
 * PreviewNavigateDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.print;

import com.steema.teechart.editors.ButtonPageNavigator;
import com.steema.teechart.editors.AbstractPageNavigator;
import com.steema.teechart.editors.PreviewPanelEditor;
import com.steema.teechart.events.ChangeEvent;
import com.steema.teechart.events.ChangeListener;
import com.steema.teechart.printer.PreviewPanel;
import com.steema.teechart.styles.Histogram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class PreviewNavigateDemo extends ChartSamplePanel
    implements ActionListener {

    private AbstractPageNavigator navigator;
    private PreviewPanel preview;

    /** Creates a new instance of PreviewNavigateDemo */
    public PreviewNavigateDemo() {
        super();
        editButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == editButton) {
            PreviewPanelEditor.showModal(chart1.getChart(),preview);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getPage().setMaxPointsPerPage(6);
        chart1.getPrinter().getMargins().setLeft(13);
        chart1.getPrinter().getMargins().setTop(15);
        chart1.getPrinter().getMargins().setRight(13);
        chart1.getPrinter().getMargins().setBottom(15);
        chart1.getPage().addChangeListener( new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                /* after changing the current page, refresh the Print Preview */
                preview.repaint();
            }
        });
    }

    protected void initComponents() {
        super.initComponents();
        navigator = new ButtonPageNavigator(chart1.getChart());

        preview = new PreviewPanel(chart1.getChart(), chart1.getChart().getPrinter());

        Histogram series = new Histogram(chart1.getChart());
        series.getMarks().setVisible(false);
        series.fillSampleValues(20);

        editButton = new JButton("Edit...");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(navigator);
            tmpPane.add(Box.createHorizontalGlue());
        }
        JSplitPane tmpSplitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                chart1,
                preview
                
                );
        tmpSplitPane.setDividerLocation(200);
        setSamplePane(tmpSplitPane);
    }

    private JButton editButton;
}
