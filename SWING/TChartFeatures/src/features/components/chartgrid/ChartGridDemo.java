/*
 * ChartGridDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.components.chartgrid;
import com.steema.teechart.editors.ButtonColor;
import com.steema.teechart.editors.ChartGrid;
import com.steema.teechart.styles.Bar;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import features.SamplePanel;


/**
 *
 * @author tom
 */
public class ChartGridDemo extends SamplePanel
        implements ItemListener {

    private Bar barSeries;
    private ChartGrid chartGrid;

    /**
     * Creates a new instance of ChartGridDemo
     */
    public ChartGridDemo() {
        super();

        showLabelsButton.addItemListener(this);
        editableButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == showLabelsButton) {
            chartGrid.setShowLabels(isSelected);
        } else if (source == editableButton) {
            chartGrid.setEditable(isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();

        barSeries = new Bar(chart1.getChart());
        {
            // fill series
            barSeries.add( 32,"John");
            barSeries.add(417,"Anne");
            barSeries.add( 65,"Louise");
            barSeries.add( 87,"Jeff");
        }
        chartGrid = new ChartGrid(chart1.getChart());
        chartGrid.setVisible(true);

        colorButton = new ButtonColor("Series Color...", barSeries.getColor());
        showLabelsButton = new JCheckBox("Show Labels");
        showLabelsButton.setSelected(chartGrid.getShowLabels());

        editableButton = new JCheckBox("Editable");
        editableButton.setSelected(chartGrid.getEditable());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(showLabelsButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editableButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(colorButton);
        }

        JSplitPane tmpSplitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(chartGrid),
                chart1
                
                );
        tmpSplitPane.setDividerLocation(100);
        tmpSplitPane.setBorder(null);
        setSamplePane(tmpSplitPane);
    }

    private ButtonColor colorButton;
    private JCheckBox showLabelsButton, editableButton;
}
