/*
 * XmlSourceDemo.java
 *
 * <p>Copyright: Copyright (c) 2006-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.imports;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import features.ChartSamplePanel;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 *
 * @author tom
 */
public class XmlSourceDemo extends ChartSamplePanel
    implements ActionListener {

    /**
     * Creates a new instance of XmlSourceDemo
     */
    public XmlSourceDemo() {
        super();
        reloadButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();

        if (source == reloadButton) {
            InputStream stream = null;
            try {
                stream = new ByteArrayInputStream(xmlArea.getText().getBytes("UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(XmlSourceDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(stream != null) {
                try {
                    chart1.getImport().getXML().open(stream);
                } catch (IOException ex) {
                    Logger.getLogger(XmlSourceDemo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(XmlSourceDemo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

           reloadButton.setEnabled(false);

        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setText("XML Import");
    }

    protected void initComponents() {
        super.initComponents();
        reloadButton = new JButton("Reload!");
        reloadButton.setEnabled(false);
        xmlArea = new JTextArea();
        xmlArea.setFont(new Font(xmlArea.getFont().getFontName(), Font.PLAIN, 10));
        xmlArea.setEditable(true);
        xmlArea.getDocument().addDocumentListener( new DocumentListener() {
            public void insertUpdate(DocumentEvent de) {
                reloadButton.setEnabled(true);
            }
            public void removeUpdate(DocumentEvent de) {
                reloadButton.setEnabled(true);
            }
            public void changedUpdate(DocumentEvent de) {
                reloadButton.setEnabled(true);
            }
        });
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(reloadButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
        JSplitPane tmpSplitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(xmlArea),
                chart1
                
                );
        tmpSplitPane.setDividerLocation(150);
        tmpSplitPane.setBorder(null);
        fillXmlArea();
        setSamplePane(tmpSplitPane);
    }

    private void fillXmlArea() {
        xmlArea.setText(
                "<?xml version=\"1.0\" ?>\n"+
                "<chart>\n"+
                "<series title=\"Series1\" type=\"Point\">\n"+
                "<points count=\"6\">\n"+
                "<point text=\"a\" color=\"#FF8040\" Y=\"1086\"/>\n"+
                "<point text=\"b\" color=\"#008080\" Y=\"1197\"/>\n"+
                "<point text=\"c\" color=\"#FF00FF\" Y=\"1126\"/>\n"+
                "<point text=\"d\" color=\"#FFFF00\" Y=\"1135\"/>\n"+
                "<point text=\"e\" color=\"#000080\" Y=\"1182\"/>\n"+
                "<point text=\"f\" color=\"#FF0000\" Y=\"1089\"/>\n"+
                "<point text=\"g\" color=\"#FF0000\" Y=\"1089\"/>\n"+
                "</points>\n"+
                "</series>\n"+
                "</chart>"
                );
    }

    private JButton reloadButton;
    private JTextArea xmlArea;
}
