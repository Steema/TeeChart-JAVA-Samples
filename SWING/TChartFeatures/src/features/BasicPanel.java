/*
 * BasicPanel.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author tom
 */
public class BasicPanel extends JPanel {

    private final static String SAMPLE_DESCRIPTION_EXTENSION = ".html";
    protected JEditorPane sampleDescription;
    protected DescriptionURL description;

    /** Creates a new instance of BasicPanel */
    public BasicPanel() {
        super();
        description = new DescriptionURL(getClass());

        initComponents();

        if (description.url != null) {
            try {
                sampleDescription.setPage(description.url);
                //sampleDescription.setFont(new Font("Verdana", Font.BOLD, 22));

            } catch (IOException e) {
                //System.err.println("Attempted to read a bad URL: " + s);
            }
        }

        initGUI();
    }

    public JEditorPane getSampleDescription() {
        return sampleDescription;
    }

    public JComponent getContainerComponent() {
        return new JScrollPane(sampleDescription);
    }

    /**
     * Creates, intializes and configures the UI components.
     */
    protected void initComponents() {
        sampleDescription = new JEditorPane();
        sampleDescription.setBackground(new java.awt.Color(255, 255, 240));
        sampleDescription.setFont(new Font("Arial", Font.BOLD, 32));
        sampleDescription.setEditable(false);
    }

    protected void initGUI() {
        setLayout(new BorderLayout());
        add(getContainerComponent(), BorderLayout.CENTER);
    }

    final class DescriptionURL {
        URL url;

        DescriptionURL(Class cls) {
            String s = "/" + cls.getName().replace('.', '/') +
                       SAMPLE_DESCRIPTION_EXTENSION;
            url = BasicPanel.class.getResource(s);
        }
    }
}
