/*
 * XmlImportDemo.java
 *
 * <p>Copyright: Copyright (c) 2006-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.imports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import features.ChartSamplePanel;
import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author tom
 */
public class XmlImportDemo extends ChartSamplePanel
    implements ActionListener {

    /**
     * Creates a new instance of XmlImportDemo
     */
    public XmlImportDemo() {
        super();
        loadButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();

        if (source == loadButton) {
            {
               URL xmlUrl = null;
                try {
                    xmlUrl = new URL(urlTextField.getText());
                } catch (MalformedURLException ex) {
                    Logger.getLogger(XmlImportDemo.class.getName()).log(Level.SEVERE, null, ex);
                }
                InputStream in = null;
                if(xmlUrl != null) { 
                    try {
                        in = xmlUrl.openStream();
                    } catch (IOException ex) {
                        Logger.getLogger(XmlImportDemo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(in != null) {
                    try {
                        chart1.getImport().getXML().open(in);
                    } catch (IOException ex) {
                        Logger.getLogger(XmlImportDemo.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(XmlImportDemo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setText("XML Data from Web");
    }

    protected void initComponents() {
        super.initComponents();
        loadButton = new JButton("Load!");
        urlTextField = new JTextField("http://www.steema.com/SampleSurfaceData.xml");
        //urlTextField = new JTextField("d://SampleData2.xml");
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("URL:");
            tmpLabel.setDisplayedMnemonic('U');
            tmpLabel.setLabelFor(urlTextField);
            tmpPane.add(tmpLabel);
            tmpPane.add(urlTextField);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(loadButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton loadButton;
    private JTextField urlTextField;
}
