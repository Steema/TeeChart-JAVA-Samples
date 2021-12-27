/*
 * BasicPanel.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.text.BadLocationException;
import javax.swing.JEditorPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.io.Reader;
import java.awt.Cursor;

import java.awt.Color;

/**
 *
 * @author tom
 */
public class BasicSamplePanel extends BasicPanel {
    /** Creates a new instance of BasicPanel */
    public BasicSamplePanel() {
        super();
    }

    public JComponent getContainerComponent() {
        return containerPane;
    }

    public JPanel getBasePane() {
        return basePane;
    }

    public JPanel getButtonPane() {
        return buttonPane;
    }

    public JPanel getSamplePane() {
        return samplePane;
    }

    public void hideButtonPane() {
        buttonPane.setVisible(false);
    }

    public void setSamplePane(JComponent samplePane) {
        if (samplePane != null) {
            basePane.add(samplePane, BorderLayout.CENTER);
        }
    }

    public void setSampleText(String sampleText) {
        if (sampleText != null) {
            sampleDescription.setText(sampleText);
        }
    }

    /**
     * Creates, intializes and configures the UI components.
     */
    protected void initComponents() {
        super.initComponents();

        buildExampleTab();
        buildSourceTab();

        final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
        tabbedPane.addTab("Example", exampleTab);
        tabbedPane.addTab("Source Code", sourceTab);

        tabbedPane.addChangeListener(new ChangeAdapter() {
            public void stateChanged(ChangeEvent e) {
                if (tabbedPane.getSelectedComponent() == sourceTab) {
                    if (sourceTextArea.getText().length() == 0) {

                        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        try {
                            loadSourceFile();
                        } finally {
                            setCursor(Cursor.getDefaultCursor());
                        }
                    }
                }
            }
        });

        containerPane = tabbedPane;

        containerPane.setBorder(null);
    }

    private void buildExampleTab() {
        buttonPane = new JPanel();
        samplePane = new JPanel();

        basePane = new JPanel();
        {
            basePane.setLayout(new BorderLayout());
            buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
            buttonPane.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createBevelBorder(BevelBorder.RAISED),
                    BorderFactory.createEmptyBorder(6, 6, 6, 6)
                                 ));
            basePane.add(buttonPane, BorderLayout.PAGE_START);
            basePane.add(samplePane, BorderLayout.CENTER);
        }

        if (description.url!=null) {
            exampleTab = new JSplitPane(
                    JSplitPane.VERTICAL_SPLIT,
                    new JScrollPane(sampleDescription),
                    basePane
                         );
            ((JSplitPane) exampleTab).setDividerLocation(150);
        } else {
            exampleTab = basePane;
        }
    }

    private void buildSourceTab() {
        pathPane = new JPanel();
        {
            pathPane.setLayout(new BoxLayout(pathPane, BoxLayout.X_AXIS));
            pathPane.setBorder(
                    BorderFactory.createEmptyBorder(6, 6, 6, 6)
                    );

            JButton configButton = new JButton("Configure...");
            configButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String startDirectory = System.getProperty("user.dir");
                    if (Parameters.getSourcePath() != "") {
                        startDirectory = Parameters.getSourcePath();
                    }
                    JFileChooser chooser = new JFileChooser(startDirectory);
                    chooser.setDialogTitle("Folder with TeeChart demo sources");
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    int returnVal = chooser.showOpenDialog(sourceTab);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        Parameters.setSourcePath(chooser.getSelectedFile().
                                                 getAbsolutePath());
                        loadSourceFile();
                    }
                }
            });
            pathPane.add(configButton);
            pathPane.add(Box.createHorizontalGlue());
        }

        sourceTextArea = new JEditorPane();
        sourceTextArea.setEditable(false);
        sourceTextArea.setBackground(new Color(255,255,155));
        sourcePane = new JScrollPane(sourceTextArea);

        sourceTab = new JPanel();
        {
            sourceTab.setLayout(new BorderLayout());
            sourceTab.add(sourcePane, BorderLayout.CENTER);
            sourceTab.add(pathPane, BorderLayout.PAGE_START);
        }
    }

    protected void loadSourceFile() {

       // Load resource to text editor pane
        File sourceFile = Parameters.getFilePath(getClass());
        File f = new File(System.getProperty("user.dir")+"\\build\\classes"+sourceFile.toString());
              try {
                loadSources(new FileReader(f));
                //sourceStyledText.setVisible(true);
                pathPane.setVisible(false);                
            } catch (IOException ioe) 
            {
                      System.out.println(
                        "importData: Unable to read from file " +
                        f.toString());
             }
      
       
    }

    private void loadSources(Reader input) throws IOException {

        BufferedReader in = new BufferedReader(input);

        String tmp = "";

        String str;
        while ((str = in.readLine()) != null) {
            tmp += (str + "\n");
        }

        in.close();

        // Force syntax highlighting here:
        try {
            sourceTextArea.getDocument().insertString(0, tmp, null);
        } catch (BadLocationException ex) {
        }

        // Without syntax highlighting:
        // sourceTextArea.setText(tmp);

        sourceTextArea.setCaretPosition(0);
    }

    private JPanel buttonPane;
    private JPanel samplePane;

    private JComponent containerPane;
    private JComponent exampleTab;
    private JEditorPane sourceTextArea;
    private JScrollPane sourcePane;
    private JPanel basePane;
    private JPanel pathPane;
    private JPanel sourceTab;
}


abstract class ChangeAdapter implements ChangeListener {
    public abstract void stateChanged(ChangeEvent e);
}
