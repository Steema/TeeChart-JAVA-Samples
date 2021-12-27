/*
 * ErrorPoint3DDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2011 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.errorpoint3d;


import com.steema.teechart.editors.series.ErrorPoint3DEditor;


import com.steema.teechart.styles.ErrorPoint3D;
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
public class ErrorPoint3DDemo extends ChartSamplePanel implements WindowListener {

    private ErrorPoint3D series;
    private JButton dialog;
    private Action openAction;
    
	public ErrorPoint3DDemo() {
		super();

	}
   
    
    protected void initChart() {
    	super.initChart();
        
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setChart3DPercent(70);
        chart1.getAspect().setZoom(80);
        chart1.getLegend().setVisible(false);
        chart1.getAxes().getDepth().setVisible(true);
        chart1.getAxes().getDepth().setIncrement(2);
        
        series = new ErrorPoint3D(chart1.getChart());
        series.fillSampleValues(10);
        series.setColorEach(true);
        Theme theme = new com.steema.teechart.themes.BlackIsBackTheme(chart1.getChart());
        theme.apply();

        openAction = new OpenAction("ErrorPoint3D Editor");
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
                    ErrorPoint3DEditor dialog = new ErrorPoint3DEditor();
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
