/*
 * Resolver.java
 *
 * <p>Copyright: Copyright (c) 2006-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.legend;

import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Bar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import com.steema.teechart.legend.LegendResolver;
import com.steema.teechart.legend.LegendItemCoordinates;
import com.steema.teechart.Rectangle;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.legend.Legend;

/**
 *
 * @author pep
 */

public class Resolver extends ChartSamplePanel implements ActionListener {

    /** Creates a new instance of Resolver */
    public Resolver() {
        super();
        editButton.addActionListener(this);

        // Customizing the Legend
        chart1.setLegendResolver(new LegendResolver() {

            public Rectangle getBounds(Legend legend, Rectangle rectangle) {
                return rectangle;
            }

            public LegendItemCoordinates getItemCoordinates(Legend legend, LegendItemCoordinates coordinates) {
                return coordinates;
            }

            public String getItemText(Legend legend, LegendStyle legendStyle, int index, String text) {
                if (index==0)
                    text += " Custom";

                return text;
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == editButton) {
            ChartEditor.editChart(chart1.getChart());
        }
    }

    protected void initChart() {
        super.initChart();
    }

    protected void initComponents() {
        super.initComponents();
        Bar series = new Bar(chart1.getChart());

        chart1.getAspect().setView3D(false);
        series.fillSampleValues();

        editButton = new JButton("Edit Chart...");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
}
