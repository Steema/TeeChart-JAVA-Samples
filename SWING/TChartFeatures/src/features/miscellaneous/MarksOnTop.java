/*
 * MarksOnTop.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.miscellaneous;

import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.MultiBars;

import features.ChartSamplePanel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

/**
 * @author yeray
 *
 */
public class MarksOnTop extends ChartSamplePanel
    implements ItemListener {

    private Bar bar1, bar2, bar3;

    /** Creates a new instance of BorderShadowDemo */
    public MarksOnTop() {
        super();
        onTopButton.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == onTopButton) {
            for(int i = 0; i < chart1.getSeriesCount(); i++)
                chart1.getSeries(i).getMarks().setOnTop(isSelected);
        }
    }

    protected void initComponents() {
        super.initComponents();

        chart1.getAspect().setView3D(false);
        
        bar1 = new Bar(chart1.getChart());
        bar1.fillSampleValues();
		
	bar2 = new Bar(chart1.getChart());
	bar2.fillSampleValues();
		
	bar3 = new Bar(chart1.getChart());
	bar3.fillSampleValues();
		
	bar1.setMultiBar(MultiBars.STACKED);
	bar2.setMultiBar(MultiBars.STACKED);
	bar3.setMultiBar(MultiBars.STACKED);
		
	for (int i = 0; i < chart1.getSeriesCount(); i++)
            chart1.getSeries(i).getMarks().setOnTop(true);

        onTopButton = new JCheckBox("Marks on Top");
        onTopButton.setSelected(bar1.getMarks().getOnTop());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(onTopButton);
            tmpPane.add(Box.createHorizontalStrut(10));
        }
    }

    private JCheckBox onTopButton;
}
