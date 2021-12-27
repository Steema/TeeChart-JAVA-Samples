/*
 * PageDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.components.charteditor;

import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Bar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class PageDemo extends ChartSamplePanel
    implements ActionListener {

    /** Creates a new instance of PageDemo */
    public PageDemo() {
        super();
        editButton.addActionListener(this);
    }

   public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == editButton) {
            ChartEditor.editChart(chart1.getChart());
        }
    }

    protected void initComponents() {
        super.initComponents();

        Bar tmpSeries;
        tmpSeries = new Bar(chart1.getChart());
        {
            tmpSeries.setColorEach(true);
            tmpSeries.fillSampleValues(20);
        }
        chart1.getPage().setMaxPointsPerPage(5);

        editButton = new JButton("Edit Paging...");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            //tmpPane.add(Box.createHorizontalStrut(10));
            //tmpPane.add();
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
}
