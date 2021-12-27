/*
 * ErrorPointDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2011 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.errorpoint;


import com.steema.teechart.editors.series.ErrorPointEditor;
import com.steema.teechart.styles.ErrorPoint;


import com.steema.teechart.themes.Theme;
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
 * @author chris
 *
 */
public class ErrorPointDemo extends ChartSamplePanel implements WindowListener {

    private ErrorPoint series;
    private JButton dialog;
    private Action openAction;
    
	public ErrorPointDemo() {
		super();

	}
   
    
    protected void initChart() {
    	super.initChart();
        chart1.getAspect().setView3D(false);
        series = new ErrorPoint(chart1.getChart());
        series.fillSampleValues(10);
        series.setColorEach(true);
        Theme theme = new com.steema.teechart.themes.BlackIsBackTheme(chart1.getChart());
        theme.apply();

        openAction = new OpenAction("ErrorPoint Editor");
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
                    ErrorPointEditor dialog = new ErrorPointEditor();
                    dialog.setSeries(series, null);
                    window.add(dialog);
                    window.setVisible(true);
                    window.setSize(347, 217);
                    window.setBounds(500, 400, 280, 250);
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
