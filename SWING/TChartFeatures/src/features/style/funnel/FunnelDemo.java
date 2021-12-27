/*
 * FunnelDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.funnel;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Funnel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;
import com.steema.teechart.events.SeriesMouseAdapter;
import java.text.NumberFormat;
import javax.swing.JOptionPane;
import com.steema.teechart.events.SeriesMouseEvent;

/**
 *
 * @author tom
 */
public class FunnelDemo extends ChartSamplePanel
        implements ActionListener, ChangeListener {

    private Funnel series;
    private com.steema.teechart.legend.Legend d;


    /** Creates a new instance of FunnelDemo */
    public FunnelDemo() {
        super();
        editButton.addActionListener(this);
        toleranceSpinner.addChangeListener(this);

        // ClickSeries Event
        class FunnelMouseListener extends SeriesMouseAdapter {
            NumberFormat nf = NumberFormat.getInstance();

            public void seriesClicked(SeriesMouseEvent e) {
                Funnel fseries = (Funnel)e.getSeries();

                double quote = fseries.getQuoteValues().getValue(e.getValueIndex());
                double opportunity = fseries.getOpportunityValues().getValue(e.getValueIndex());
                double percentage = fseries.getOpportunityValues().getValue(e.getValueIndex())/fseries.getQuoteValues().getValue(e.getValueIndex())*100;
                StringBuffer sb = new StringBuffer("Quote = ");
                sb.append(nf.format(quote)).append("\n");
                sb.append("Opportunity = ").append(nf.format(opportunity)).append("\n");
                sb.append("Percentage = ").append(nf.format(percentage));
                JOptionPane.showMessageDialog(null, sb.toString() );
            };
        }
    series.addSeriesMouseListener( new FunnelMouseListener() );
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeries(series);
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == toleranceSpinner) {
            int difference = ((SpinnerNumberModel)toleranceSpinner.getModel()).getNumber().intValue();
            series.setDifferenceLimit(difference);
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Funnel (Pipeline) series");
        chart1.getAxes().getLeft().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();

        series = new Funnel(chart1.getChart());
        series.getMarks().setVisible(true);
        series.getMarks().setClip(true);
        series.setDifferenceLimit(30);
        /* add some points to Funnnel/Pipeline */
        series.addSegment(50,110,"1st",Color.EMPTY);
        series.addSegment(200,50,"2nd",Color.EMPTY);
        series.addSegment(100,50,"3rd",Color.EMPTY);
        series.addSegment(60,55,"4th",Color.EMPTY);
        series.reCalc();

        editButton = new JButton("Edit...");
        toleranceSpinner = new JSpinner(
                new SpinnerNumberModel(
                series.getDifferenceLimit(),
                0,
                100,
                1)
                );
    }

    protected void initGUI() {
        super.initGUI();

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            JLabel tmpLabel = new JLabel("Desired tolerance for acceptance:");
            tmpLabel.setDisplayedMnemonic('D');
            tmpLabel.setLabelFor(toleranceSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(toleranceSpinner);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JSpinner toleranceSpinner;
}
