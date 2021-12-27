/*
 * JScriptExportDemo.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.exports;

import com.steema.teechart.styles.Bar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class JScriptExportDemo extends ChartSamplePanel implements ActionListener {

    /** Creates a new instance of JScriptExportDemo */
    public JScriptExportDemo() {
        super();
        exportButton.addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source == exportButton) {
            try {
                chart1.getExport().getImage().getJScript().save("./Java2JavascriptSwing.html");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(JScriptExportDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    protected void initChart() {
        super.initChart();
        
        chart1.getAspect().setView3D(false);
        chart1.getHeader().setText("TeeChart Javascript on HTML5 Canvas");
        Bar bar1 = new Bar(chart1.getChart());
        bar1.fillSampleValues();
    }
    
    protected void initComponents() {
        super.initComponents();
        
        exportButton = new JButton("Export to Javascript...");
        fc = new JFileChooser();
        fc.setAcceptAllFileFilterUsed(false);
    }
    
    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(exportButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(Box.createHorizontalGlue());
        }
    }
    private JButton exportButton;
    private JFileChooser fc;
}
