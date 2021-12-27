/*
 * BasicFeatures.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.styles.Series;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import java.awt.Font;


/**
 *
 * @author tom
 */
public class BasicFeatures extends ChartSamplePanel implements ActionListener {

    private JButton runButton;
    private JButton showEditorButton;
    private JTextArea codeArea;

    /** Creates a new instance of BasicFeatures */
    public BasicFeatures() {
        super();
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == runButton) {
            chart1.removeAllSeries();

            Series bar = new com.steema.teechart.styles.Bar(chart1.getChart());

            bar.clear();

            bar.add(123, "ABC", Color.RED);
            bar.add(456, "DEF", Color.BLUE);
            bar.add(321, "GHI", Color.GREEN);

            bar.getMarks().setStyle(MarksStyle.VALUE);
        } else
        if (source == showEditorButton) {
            ChartEditor.editChart(chart1.getChart(), "myChart...");
        }
    }

    protected void initComponents() {
        super.initComponents();
        runButton = new JButton("Run the code");
        showEditorButton = new JButton("Show the chart editor");

        codeArea = new JTextArea(
                "Run-time code to create charts:\n\n" +
                "myChart.removeAllSeries();\n\n" +
                "Series bar = new steema.teechart.styles.Bar(myChart.getChart());\n\n" +
                "bar.clear();\n" +
                "bar.add(123, \"ABC\", Color.RED);\n" +
                "bar.add(456, \"DEF\", Color.BLUE);\n" +
                "bar.add(321, \"GHI\", Color.GREEN);\n\n" +
                "Change the bar Marks :\n\n" +
                "bar.getMarks().setStyle(MarksStyle.VALUE);\n\n"
                   );

        codeArea.setEditable(false);
        codeArea.setFont(new Font("Courier",Font.PLAIN,11));

        chart1.getAxes().getBottom().setLabelsOnAxis(true);
    }

    protected void initGUI() {
        super.initGUI();

        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(runButton);
            tmpPane.add(Box.createHorizontalGlue());
            tmpPane.add(showEditorButton);
            runButton.addActionListener(this);
            showEditorButton.addActionListener(this);
        }

        JSplitPane tmpSplitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(codeArea),
                chart1
                                  
                                  );

        tmpSplitPane.setBorder(null);
        tmpSplitPane.setDividerLocation(250);

        setSamplePane(tmpSplitPane);
    }
}
