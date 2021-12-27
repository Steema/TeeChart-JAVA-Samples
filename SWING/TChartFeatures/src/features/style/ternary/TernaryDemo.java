/*
 * TernaryDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.ternary;


import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.series.TernaryEditor;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Ternary.TernaryMarkStyle;

import com.steema.teechart.styles.Ternary;
import com.steema.teechart.styles.Ternary.TernaryLegendStyle;
import com.steema.teechart.styles.Ternary.TernaryStyle;

import features.ChartSamplePanel;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author tom
 *
 */
public class TernaryDemo extends ChartSamplePanel implements WindowListener {

    private Ternary series;
    private JButton dialog;
    private Action openAction;
    
	public TernaryDemo() {
		super();

	}
   
    
    protected void initChart() {
    	super.initChart();
        series = new Ternary(chart1.getChart());
        series.fillSampleValues(10);
        chart1.getHeader().setText("Ternary Series Example");
        series.setMarkStyle(TernaryMarkStyle.LongLabelled);
        series.setTernaryStyle(TernaryStyle.Bubble);
        series.getPointer().setTransparency(20);
        series.setTernaryLegendStyle(TernaryLegendStyle.BubbleWeight);
        chart1.getLegend().getTitle().setText("Radius" + ", " + "Weights");
        series.getMarks().setMultiLine(true);
        series.getPointer().setStyle(PointerStyle.CIRCLE);
        series.setEndColor(Color.GREEN); //for use when range palette active (ColorEach false)
        chart1.getLegend().getTitle().setVisible(true);
        openAction = new OpenAction("Ternary Editor");
        dialog = new JButton(openAction);
        window.addWindowListener(this);
    }

     final public class OpenAction extends AbstractAction {

        public OpenAction(String text) {
            super(text);
        }

        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if (obj == dialog) {
                if (!b) {
                    TernaryEditor dialog = new TernaryEditor(series);
                    window.add(dialog);
                    window.setVisible(true);
                    window.setSize(347, 217);
                    window.setBounds(500, 400, 480, 250);
                    b = true;
                }
            }
        }
    }
    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {

            tmpPane.add(dialog);
            tmpPane.add(Box.createHorizontalGlue());

        }
    }
      public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        Object obj = e.getSource();
        if (obj == window) {
            b = false;
        }
    }

    public void windowClosed(WindowEvent e) {
        Object obj = e.getSource();
        if (obj == window) {
            b = false;
        }
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    private static JFrame window = new JFrame();
    private Boolean b = false;
}
