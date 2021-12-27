/*
 * Main.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

import com.steema.teechart.editors.AboutDialog;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.io.File;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import features.Parameters;

import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.awt.event.ActionListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.DefaultTreeModel;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * 
 * @author tom
 */
@SuppressWarnings("serial")
final public class TeeChartDemo extends JPanel implements ActionListener {

    JMenuItem menuTest;
    JMenuItem menuShowAll;
    JMenuItem menuLookDefault;
    JMenuItem menuLookMetal;
    JMenuItem menuLookMotif;
    JMenuItem menuLookGTK;
    public JTree tmpTree, newTree;
    private JTree activeTree;
    private MouseListener l;

    /** Creates a new instance of Main */
    public TeeChartDemo() {
        super(new BorderLayout());

        initComponents();

        tmpTree = new JTree(buildFeaturesTree());
        newTree = new JTree(buildnewFeaturesTree());
        JComponent featuresTab = buildFeaturesTab(tmpTree, newTree);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, featuresTab,
                samplePane);
        newTree.setBorder(BorderFactory.createEtchedBorder(new Color(0, 0, 0),
                new Color(255, 255, 255)));
        tmpTree.setBorder(BorderFactory.createEtchedBorder(new Color(0, 0, 0),
                new Color(255, 255, 255)));
        splitPane.setBorder(BorderFactory.createEmptyBorder());
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(255);
        splitPane.setDividerSize(7);

        // Provide minimum sizes for the two components in the split pane.
        Dimension minimumSize = new Dimension(100, 50);
        featuresTab.setMinimumSize(minimumSize);
        samplePane.setMinimumSize(minimumSize);

        // Provide a preferred size for the split pane.
        splitPane.setPreferredSize(new Dimension(400, 200));

        add(buildHeaderPane(), BorderLayout.PAGE_START);
        add(splitPane, BorderLayout.CENTER);

        statusPanel.setPreferredSize(new Dimension(0, 24));
        // statusPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        statusPanel.setBorder(BorderFactory.createEtchedBorder(new Color(0, 0,
                0), new Color(255, 255, 255)));
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.SOUTH);

        addPopupMenu();
        activeTree = newTree;
        showBasicDemo(activeTree);
    }

    private JMenuItem addMenu(JPopupMenu popupMenu, String text) {
        JMenuItem result = new JMenuItem(text);
        result.addActionListener(this);
        popupMenu.add(result);
        return result;
    }

    private void addPopupMenu() {
        final JPopupMenu popupMenu = new JPopupMenu("Menu");

        menuTest = addMenu(popupMenu,
                "Test all demos (" + Integer.toString(treeNodeCount) + ")");

        menuShowAll = new JCheckBoxMenuItem("Show all tree nodes");
        menuShowAll.addActionListener(this);
        popupMenu.add(menuShowAll);
        popupMenu.add(new JSeparator());

        menuLookDefault = addMenu(popupMenu, "Default Look");
        menuLookMetal = addMenu(popupMenu, "Metal Look");
        menuLookMotif = addMenu(popupMenu, "Motif Look");
        menuLookGTK = addMenu(popupMenu, "GTK Look");

        statusPanel.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent evt) {
                if (evt.isPopupTrigger()) {
                    popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }

            public void mouseReleased(MouseEvent evt) {
                if (evt.isPopupTrigger()) {
                    popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String tmp = e.getActionCommand();

        if (tmp.equals(menuTest.getText())) {
            walk(activeTree.getModel(), activeTree.getModel().getRoot());
        } else if (tmp.equals(menuShowAll.getText())) {
            fullTree = menuShowAll.isSelected();
            activeTree.setModel(new DefaultTreeModel(buildFeaturesTree(), false));
            activeTree.setCellRenderer(new TeeFeatureNodeRenderer(fullTree));
            activeTree.expandRow(0);

        } else if (tmp.equals(menuLookDefault.getText())) {
            setLook(UIManager.getSystemLookAndFeelClassName());
        } else if (tmp.equals(menuLookMetal.getText())) {
            setLook("javax.swing.plaf.metal.MetalLookAndFeel");
        } else if (tmp.equals(menuLookMotif.getText())) {
            setLook("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        } else if (tmp.equals(menuLookGTK.getText())) {
            setLook("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        }
    }

    private void setLook(String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(window);
            window.pack();

        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    protected void walk(TreeModel model, Object o) {
        int count = model.getChildCount(o);

        for (int t = 0; t < count; t++) {
            Object child = model.getChild(o, t);

            if (model.isLeaf(child)) {
                final TeeFeatureNode feature = (TeeFeatureNode) ((DefaultMutableTreeNode) child).getUserObject();

                showSamplePane(feature);
                paintAll(getGraphics());

            } else {
                walk(model, child);
            }
        }
    }

    final public class AboutAction extends AbstractAction {

        public AboutAction(String text, ImageIcon icon, String desc,
                Integer mnemonic) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            AboutDialog dialog = new AboutDialog(window);
            dialog.setVisible(true);
        }
    }

    final public class CloseAction extends AbstractAction {

        public CloseAction(String text, ImageIcon icon, String desc,
                Integer mnemonic) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            Parameters.saveProperties();
            window.dispose();
            System.exit(0);
        }
    }

    public class LeftAction extends AbstractAction {

        public LeftAction(String text, ImageIcon icon, String desc,
                Integer mnemonic) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            int tmpRowSelection = activeTree.getLeadSelectionRow() - 1;

            if (tmpRowSelection >= 0) {
                activeTree.setSelectionRow(tmpRowSelection);
            } else {
                activeTree.setSelectionRow(0);
            }
            activeTree.expandRow(tmpRowSelection);
        }
    }

    public class searchAction extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            list1.removeAll();
            a_t.removeAllElements();
            javaFiles.removeAllElements();
            pathCounter.removeAllElements();
            String SearchValue = jComboBox1.getSelectedItem().toString();
            String[] auxSearch = new String[300];

            for (int j = 0; j < 300; j++) {
                auxSearch[j] = "";
                auxSearch[j].toCharArray();
            }

            int counterWord = 0, forInt = 0;


            getFiles(SearchValue, javaFiles, pathCounter);

            for (int i = 0; i < javaFiles.size(); i++) {
                tee = new TeeFeatureNode(javaFiles.get(i).toString(),
                        pathCounter.get(i).toString(), "", true, false);
                a_t.add(tee);
                list1.add(javaFiles.get(i).toString().replace(".java", ""));
            }
            jLabel1.setText(javaFiles.size() + " items found.");


        }
    }

    public class cleanAction extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            list1.removeAll();
            a_t.removeAllElements();
            javaFiles.removeAllElements();
            pathCounter.removeAllElements();
            jLabel1.setText("");
        }
    }

    public class showAction extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            int i = 0;
            boolean found = false;

            while (i < a_t.size() && !found) {

                found = a_t.get(i).name.equals(list1.getSelectedItem());
                i++;
            }
            if (found) {
                showSamplePane(a_t.get(i - 1));
            }

        }
    }

    public class RightAction extends AbstractAction {

        public RightAction(String text, ImageIcon icon, String desc,
                Integer mnemonic) {
            super(text, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
        }

        public void actionPerformed(ActionEvent e) {
            int tmpRowSelection = activeTree.getLeadSelectionRow() + 1;
            if (activeTree.getPathForRow(tmpRowSelection) != null) {
                activeTree.setSelectionRow(tmpRowSelection);
                activeTree.expandRow(tmpRowSelection);
            } else {
                tmpRowSelection -= 1;
            }
        }
    }
    private static JFrame window;
    private JSplitPane splitPane;
    JButton closeButton, aboutButton, leftButton, rightButton, jButton1,
            jButton2, jButton3;
    JComboBox jComboBox1, jComboBox2;
    private java.awt.List list1 = new java.awt.List();
    private Action aboutAction, closeAction, leftAction, rightAction, searchAction, cleanAction, showAction;
    private JPanel samplePane;
    private JCheckBox jCheckBox1;
    private JPanel statusPanel = new JPanel();
    private JLabel statusLabel = new JLabel();
    private TeeFeatureNode tee;
    private JLabel jLabel1 = new JLabel();
    private Vector<TeeFeatureNode> a_t = new Vector<TeeFeatureNode>();
    private boolean showWait = false;
    private boolean fullTree = false;
    Vector<String> lineCounter = new Vector<String>(),
            pathCounter = new Vector<String>();
    String path, aux;
    Vector<String> javaFiles = new Vector<String>();

    /**
     * Creates, initializes and configures the UI components.
     */
    private void initComponents() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension(dim.width - (dim.width / 4), dim.height - (dim.height / 4)));

        // Create actions
        aboutAction = new AboutAction("", null, "About TeeChart for Java", null);
        closeAction = new CloseAction("", null,
                "Close TeeChart Demo Application", null);
        leftAction = new LeftAction("", null, "Previous", null);
        rightAction = new RightAction("", null, "Next", null);
        searchAction = new searchAction();
        cleanAction = new cleanAction();
        showAction = new showAction();
        // create buttons
        closeButton = new JButton(closeAction);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        ImageIcon closeIcon = new ImageIcon(
                TeeChartDemo.class.getResource("features/images/Button-Close-icon.png"));

        closeButton.setIcon(closeIcon);
        closeButton.setMnemonic('q');

        aboutButton = new JButton(aboutAction);
        aboutButton.setBorderPainted(false);
        aboutButton.setFocusPainted(false);
        ImageIcon aboutIcon = new ImageIcon(
                TeeChartDemo.class.getResource("features/images/about-icon.png"));

        aboutButton.setIcon(aboutIcon);
        aboutButton.setMnemonic('i');

        // navigate buttons
        leftButton = new JButton(leftAction);
        ImageIcon leftIcon = new ImageIcon(
                TeeChartDemo.class.getResource("features/images/Button-Previous-icon.png"));

        leftButton.setIcon(leftIcon);
        leftButton.setBorderPainted(false);
        leftButton.setFocusPainted(false);
        rightButton = new JButton(rightAction);
        rightButton.setBorderPainted(false);
        rightButton.setFocusPainted(false);
        ImageIcon rightIcon = new ImageIcon(
                TeeChartDemo.class.getResource("features/images/Button-Next-icon.png"));

        rightButton.setIcon(rightIcon);

        samplePane = buildSamplePane();
    }

    protected final class TeeFeatureNode {

        private String name;
        private String classpath;
        private String issues;
        private boolean visible;
        private boolean leaf;

        public TeeFeatureNode(String name, String classpath, String issues,
                boolean visible, boolean leaf) {
            this.name = name;
            this.classpath = classpath;
            this.issues = issues;
            this.visible = visible;
        }

        public TeeFeatureNode(String name, String classpath) {
            this(name, classpath, "", true, true);
        }

        public String getIssues() {
            return issues;
        }

        public boolean isFinished() {
            return ((issues.length() == 0) || issues.equals("0"));
        }

        public boolean isLeaf() {
            return leaf;
        }

        public boolean isSample() {
            return !this.classpath.equalsIgnoreCase("");
        }

        public boolean isVisible() {
            return visible;
        }

        public String toString() {
            return name;
        }
    }

    final protected static class TeeFeatureNodeRenderer extends DefaultTreeCellRenderer {

        private boolean fullTree = true;

        public TeeFeatureNodeRenderer(boolean fullTree) {
            super();
            this.fullTree = fullTree;
            setBackgroundNonSelectionColor(FEATURES_TREECOLOR);

            ImageIcon tmp = new ImageIcon(
                    TeeFeatureNodeRenderer.class.getResource("features/images/treeleaf.png"));
            ImageIcon tmp2 = new ImageIcon(Utils.makeTransparent(
                    tmp.getImage(), new Color(234, 238, 255)));

            setLeafIcon(tmp2);
        }

        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean selected, boolean expanded, boolean leaf, int row,
                boolean hasFocus) {

            Component c = super.getTreeCellRendererComponent(tree, value,
                    selected, expanded, leaf, row, hasFocus);

            if (fullTree && (value != null)) {
                Object tmpObject = ((DefaultMutableTreeNode) value).getUserObject();
                if (tmpObject != null) {
                    TeeFeatureNode tmpNode = (TeeFeatureNode) tmpObject;
                    if (!tmpNode.isVisible()) {
                        c.setForeground(Color.GRAY);
                        setToolTipText(null);
                    } else if (!tmpNode.isFinished()) {
                        c.setForeground(Color.MAGENTA);
                        setToolTipText(tmpNode.getIssues());
                    } else {
                        setToolTipText(null);
                    }
                }
            }

            return c;
        }
    }

    protected JComponent buildHeaderPane() {

        JPanel headerPanel = new JPanel();
        {

            headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
            // headerPanel.setBackground(Color.fromArgb(247, 189, 0));
            ImageIcon image = new ImageIcon(
                    TeeChartDemo.class.getResource("features/images/header.jpg"));
            JLabel chartHeader = new JLabel();
            chartHeader.setIcon(image);
            headerPanel.add(chartHeader);
            headerPanel.setPreferredSize(new Dimension(1300, 140));
        }

        JPanel buttonPane1 = new JPanel();
        {

            buttonPane1.add(leftButton);
            buttonPane1.add(rightButton);
            buttonPane1.setLayout(new FlowLayout(FlowLayout.LEFT));
            ImageIcon image = new ImageIcon(
                    TeeChartDemo.class.getResource("features/images/label.gif"));
            JLabel chartHeader = new JLabel(image);
            buttonPane1.add(chartHeader);
            buttonPane1.add(aboutButton);
            buttonPane1.add(closeButton);
        }

        JPanel headerPane = new JPanel();
        {
            headerPane.setLayout(new BorderLayout());
            headerPane.add(headerPanel, BorderLayout.LINE_START);
            headerPane.add(buttonPane1, BorderLayout.SOUTH);

            headerPane.setPreferredSize(new Dimension(100, 140));
        }

        return headerPane;
    }

    /*
     * // Create an Annotation tool private Annotation getBanner() { Annotation
     * a = new Annotation("TeeChart Pro for Java"); a.setLeft(60); a.setTop(-5);
     * a.getShape().getFont().setName("Arial Black");
     * 
     * Gradient ag = a.getShape().getGradient(); ag.setVisible(true);
     * ag.setDirection(GradientDirection.HORIZONTAL);
     * ag.setStartColor(Color.darkGray);
     * 
     * ChartFont f = a.getShape().getFont(); f.setSize(26); f.setItalic(true);
     * 
     * f.getOutline().setColor(Color.WHITE); f.getOutline().setVisible(true);
     * 
     * f.getShadow().setVisible(true); f.getShadow().setHorizSize(2);
     * f.getShadow().setVertSize(2);
     * 
     * Gradient g = f.getGradient(); g.setVisible(true);
     * g.setStartColor(Color.GREEN); g.setMiddleColor(Color.NAVY);
     * g.setEndColor(Color.red); g.setDirection(GradientDirection.HORIZONTAL);
     * 
     * a.getShape().setTransparent(true);
     * 
     * return a; }
     */
    public TeeFeatureNode getFeatureNode(TreePath path) {

        if (path != null) {
            Object comp = path.getLastPathComponent();
            DefaultMutableTreeNode tmpNode = (DefaultMutableTreeNode) comp;

            if (tmpNode != null) {
                Object tmpObject = tmpNode.getUserObject();

                if (tmpObject != null) {
                    return (TeeFeatureNode) tmpObject;
                }
            }
        }

        return null;
    }

    public TeeFeatureNode createFeatureNodeOf(Node childElement) {
        NamedNodeMap elementAttributes = childElement.getAttributes();

        // String treeNodeLabel = ""; // = childElement.getNodeName();

        TeeFeatureNode tmpNode = null;

        if (elementAttributes != null && elementAttributes.getLength() > 0) {

            boolean hasChilds = (childElement.getChildNodes().getLength() > 0);

            Node attribute = elementAttributes.getNamedItem("caption");
            String tmpCaption = (attribute != null) ? attribute.getNodeValue()
                    : "";
            attribute = elementAttributes.getNamedItem("classpath");
            String tmpClassPath = (attribute != null) ? attribute.getNodeValue() : "";
            attribute = elementAttributes.getNamedItem("visible");
            boolean tmpVisible = (attribute != null) ? attribute.getNodeValue().equals("1") : true;
            attribute = elementAttributes.getNamedItem("issues");
            String tmpIssues = (attribute != null) ? attribute.getNodeValue()
                    : "";

            if (!fullTree) {
                if (tmpIssues.equals("") || tmpIssues.equals("0")) {
                } else {
                    tmpVisible = hasChilds;
                    if (hasChilds) {
                        tmpClassPath = "";
                    }
                    ;
                    tmpIssues = "";
                }
            } else {
                tmpVisible = true;
            }

            tmpNode = new TeeFeatureNode(tmpCaption, tmpClassPath, tmpIssues,
                    tmpVisible, !hasChilds);
        }
        return tmpNode;
    }

    private void addChildren(DefaultMutableTreeNode parentTreeNode,
            Node parentXMLElement) {

        NodeList childElements = parentXMLElement.getChildNodes();

        for (int i = 0; i < childElements.getLength(); i++) {
            Node childElement = childElements.item(i);

            if (!(childElement instanceof Text || childElement instanceof Comment)) {

                boolean hasChilds = (childElement.getChildNodes().getLength() > 0);

                TeeFeatureNode tmpInfo = createFeatureNodeOf(childElement);

                if (tmpInfo.isVisible() || Parameters.getShowAllFeatures() || hasChilds) {

                    if (tmpInfo.isSample()) {
                        treeNodeCount++;
                    }
                    ;

                    DefaultMutableTreeNode childTreeNode = new DefaultMutableTreeNode(
                            tmpInfo);
                    parentTreeNode.add(childTreeNode);
                    addChildren(childTreeNode, childElement);
                }
            }
        }
    }
    private int treeNodeCount = 0;

    protected DefaultMutableTreeNode buildFeaturesTree() {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            Document document = builder.parse(TeeChartDemo.class.getResource(
                    FEATURES_URL) // ClassLoader.getSystemResource(FEATURES_URL)
                    .toString());

            document.getDocumentElement().normalize();
            Element rootElement = document.getDocumentElement();
            DefaultMutableTreeNode rootTreeNode = null;
            NodeList tmpList = document.getElementsByTagName("all");

            treeNodeCount = 0;

            if (tmpList != null) {
                rootElement = (Element) tmpList.item(0);
                rootTreeNode = new DefaultMutableTreeNode(
                        createFeatureNodeOf(rootElement));

                addChildren(rootTreeNode, rootElement);
            }

            return rootTreeNode;

        } catch (Exception e) {
            String errorMessage = "Error creating feature tree: " + e;
            System.err.println(errorMessage);
            e.printStackTrace();
            return (new DefaultMutableTreeNode(errorMessage));
        }
    }

    protected DefaultMutableTreeNode buildnewFeaturesTree() {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            Document document = builder.parse(TeeChartDemo.class.getResource(
                    NEW_FEATURES_URL) // ClassLoader.getSystemResource(FEATURES_URL)
                    .toString());

            document.getDocumentElement().normalize();
            Element rootElement = document.getDocumentElement();
            DefaultMutableTreeNode rootTreeNode = null;
            NodeList tmpList = document.getElementsByTagName("all");

            treeNodeCount = 0;

            if (tmpList != null) {
                rootElement = (Element) tmpList.item(0);
                rootTreeNode = new DefaultMutableTreeNode(
                        createFeatureNodeOf(rootElement));

                addChildren(rootTreeNode, rootElement);
            }

            return rootTreeNode;

        } catch (Exception e) {
            String errorMessage = "Error creating feature tree: " + e;
            System.err.println(errorMessage);
            e.printStackTrace();
            return (new DefaultMutableTreeNode(errorMessage));
        }
    }

    public JComponent buildFeaturesTab(final JTree tmpTree, final JTree newTree) {
        JTabbedPane tmpPane = new JTabbedPane();
        JTabbedPane newPane = new JTabbedPane();
        tmpPane.setBorder(BorderFactory.createEmptyBorder());
        newPane.setBorder(BorderFactory.createEmptyBorder());

        tmpTree.setBackground(FEATURES_TREECOLOR);

        tmpTree.setCellRenderer(new TeeFeatureNodeRenderer(fullTree));

        tmpTree.setEditable(false);
        tmpTree.setRootVisible(false);
        tmpTree.expandRow(0);
        tmpTree.setBackground(FEATURES_TREECOLOR);

        newTree.setBackground(FEATURES_TREECOLOR);

        newTree.setCellRenderer(new TeeFeatureNodeRenderer(fullTree));

        newTree.setEditable(false);
        newTree.setRootVisible(false);
        newTree.expandRow(0);
        newTree.setBackground(FEATURES_TREECOLOR);

        if (showWait) {

            tmpTree.addMouseListener(new MouseAdapter() {

                public TeeFeatureNode getNode(MouseEvent evt) {

                    if (evt.getSource() instanceof JTree) {
                        JTree t = (JTree) evt.getSource();
                        TreePath path = t.getPathForLocation(evt.getX(),
                                evt.getY());

                        return getFeatureNode(path);

                    } else {
                        return null;
                    }
                }

                ;

                public void mousePressed(MouseEvent evt) {
                    TeeFeatureNode sampleNode = getNode(evt);

                    if (sampleNode != null) {
                        if (sampleNode.isSample()) {                            // REMOVED. To avoid big flicker.
                            // splitPane.setRightComponent(defaultPanel);
                            // Slow!
                            // WaitToolkit.startWait(window.getRootPane());
                        }
                    }
                }

                ;

                public void mouseReleased(MouseEvent evt) {
                    try {
                        TeeFeatureNode sampleNode = getNode(evt);

                        if (sampleNode != null) {
                            showSamplePane(sampleNode);
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }
                }
            });
            newTree.addMouseListener(new MouseAdapter() {

                public TeeFeatureNode getNode(MouseEvent evt) {

                    if (evt.getSource() instanceof JTree) {
                        JTree t = (JTree) evt.getSource();
                        TreePath path = t.getPathForLocation(evt.getX(),
                                evt.getY());

                        return getFeatureNode(path);

                    } else {
                        return null;
                    }
                }

                ;

                public void mousePressed(MouseEvent evt) {
                    activeTree = newTree;
                    TeeFeatureNode sampleNode = getNode(evt);

                    if (sampleNode != null) {
                        if (sampleNode.isSample()) {                            // REMOVED. To avoid big flicker.
                            // splitPane.setRightComponent(defaultPanel);
                            // Slow!
                            // WaitToolkit.startWait(window.getRootPane());
                        }
                    }
                }

                ;

                public void mouseReleased(MouseEvent evt) {
                    try {
                        TeeFeatureNode sampleNode = getNode(evt);

                        if (sampleNode != null) {
                            showSamplePane(sampleNode);
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();

                    }
                }
            });

        } else {
            tmpTree.addTreeSelectionListener(new TreeSelectionAdapter() {

                public void valueChanged(TreeSelectionEvent e) {
                    TeeFeatureNode sampleNode = getFeatureNode(e.getPath());

                    if (sampleNode != null) {
                        showSamplePane(sampleNode);
                        activeTree = tmpTree;
                    }
                }
            });

            newTree.addTreeSelectionListener(new TreeSelectionAdapter() {

                public void valueChanged(TreeSelectionEvent e) {
                    TeeFeatureNode sampleNode = getFeatureNode(e.getPath());

                    if (sampleNode != null) {
                        showSamplePane(sampleNode);
                        activeTree = newTree;
                    }
                }
            });
        }

        ToolTipManager.sharedInstance().registerComponent(tmpTree);
        ToolTipManager.sharedInstance().registerComponent(newTree);

        /*
         * this is the first version, so everything is new :-) tmpPane.addTab(
         * "What's New?", new JScrollPane(tmpTree) );
         */
        ImageIcon tmp1 = new ImageIcon(
                TeeFeatureNodeRenderer.class.getResource("features/images/Retro-Block-Exclamation-icon.png"));
        ImageIcon tmp2 = new ImageIcon(Utils.makeTransparent(tmp1.getImage(),
                new Color(234, 238, 255)));
        ImageIcon tmp3 = new ImageIcon(
                TeeFeatureNodeRenderer.class.getResource("features/images/features.jpg"));
        ImageIcon tmp4 = new ImageIcon(Utils.makeTransparent(tmp3.getImage(),
                new Color(234, 238, 255)));
        ImageIcon tmp5 = new ImageIcon(
                TeeFeatureNodeRenderer.class.getResource("features/images/Search-icon2.png"));
        ImageIcon tmp6 = new ImageIcon(Utils.makeTransparent(tmp5.getImage(),
                new Color(234, 238, 255)));

        tmpPane.addTab("New features", tmp2, new JScrollPane(newTree));
        tmpPane.addTab("All features", tmp4, new JScrollPane(tmpTree));
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        jPanel1 = createPanelSearch();
        tmpPane.addTab("Search", tmp6, jPanel1);

        return tmpPane;
    }

    private javax.swing.JPanel createPanelSearch() {
        JPanel jPanel1 = new JPanel();
        jPanel1.setBorder(BorderFactory.createEtchedBorder(new Color(0, 0, 0),
                new Color(255, 255, 255)));
        ImageIcon tmp3 = new ImageIcon(
                TeeFeatureNodeRenderer.class.getResource("features/images/Symbols-Delete-icon.png"));
        ImageIcon tmp4 = new ImageIcon(Utils.makeTransparent(tmp3.getImage(),
                new Color(234, 238, 255)));
        ImageIcon tmp5 = new ImageIcon(
                TeeFeatureNodeRenderer.class.getResource("features/images/Search-icon2.png"));
        ImageIcon tmp6 = new ImageIcon(Utils.makeTransparent(tmp5.getImage(),
                new Color(234, 238, 255)));
        ImageIcon tmp7 = new ImageIcon(
                TeeFeatureNodeRenderer.class.getResource("features/images/Slide-Show-icon.png"));
        ImageIcon tmp8 = new ImageIcon(Utils.makeTransparent(tmp7.getImage(),
                new Color(234, 238, 255)));
        jComboBox1 = new JComboBox();
        jComboBox1.setEditable(true);
        jButton1 = new JButton(searchAction);
        jComboBox1.removeAllItems();

        jButton1.setText("Search");
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        list1 = new java.awt.List();
        list1.removeAll();
        jCheckBox1 = new JCheckBox();
        jCheckBox1.setText("Find all words (AND)");
        jButton1.setMnemonic(KeyEvent.VK_ENTER);
        jButton2 = new JButton(cleanAction);
        jButton2.setText("Clean Results");
        jButton2.setIcon(tmp4);
        jButton2.setFocusPainted(false);
        jButton2.setBorderPainted(false);
        jButton3 = new JButton(showAction);
        jButton3.setBorderPainted(false);
        jButton3.setFocusPainted(false);
        jButton3.setText("Show");
        jButton1.setIcon(tmp6);
        jButton3.setIcon(tmp8);

        l = new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                String j = list1.getSelectedItem();
                int i = 0;
                boolean found = false;
                while (i < a_t.size() && !found) {
                    found = a_t.get(i).name.equals(j);
                    i++;
                }
                showSamplePane(a_t.get(i - 1));
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        };
        list1.addMouseListener(l);
        list1.setVisible(true);
        return jPanel1;
    }

    private void showBasicDemo(JTree Tree) {
        Tree.setSelectionRow(1);
    }

    protected static JPanel buildSamplePane() {
        JPanel tmpPane = new JPanel();
        tmpPane.setBorder(BorderFactory.createEmptyBorder());
        return tmpPane;
    }

    private void showSamplePane(TeeFeatureNode sampleInfo) {
        if (sampleInfo.classpath.equals("")) {
            return;
        }

        try {
            String className = Parameters.featuresName + sampleInfo.classpath;

            Class cls = Class.forName(className);
            JPanel tmpPanel = (JPanel) cls.newInstance();

            if (showWait) {
                tmpPanel.addComponentListener(new ComponentAdapter() {

                    public void componentResized(ComponentEvent e) {
                    }
                });
            }

            int oldPos = splitPane.getDividerLocation();
            splitPane.setRightComponent(tmpPanel);
            splitPane.setDividerLocation(oldPos);

            statusLabel.setText(sampleInfo.name + "  (" + className + ".java)");

        } catch (InstantiationException e) {
            System.out.println(e);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            System.out.println(e);
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        window = new JFrame("TeeChart for Java");
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        window.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent we) {
                Parameters.saveProperties();
                System.exit(0);
            }
        });

        window.setContentPane(new TeeChartDemo());
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void getFiles(String searchValue, Vector<String> javaFiles, Vector<String> pathCounter) {
        //Element elemento;

        try {

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(TeeChartDemo.class.getResource(
                    FEATURES_URL).toString());
            doc.getDocumentElement().normalize();

            NodeList featuresList = doc.getElementsByTagName("feature");
            int totalPersonas = featuresList.getLength();

            for (int i = 0; i < totalPersonas; i++) {
                if (featuresList.item(i).getAttributes().item(0).toString().toLowerCase().contains(searchValue.toLowerCase())) {

                    String featureClassPath = featuresList.item(i).getAttributes().item(1).toString().replace("classpath=", "");
                    featureClassPath = featureClassPath.replace("\"", "");
                    pathCounter.add(featureClassPath);
                    int j = 0, counter = 0;
                    while (j < featureClassPath.length()) {
                        if (featureClassPath.charAt(j) == '.') {
                            counter = j;
                        }
                        j++;
                    }
                    String featureName = featureClassPath.substring(counter + 1, featureClassPath.length());
                    javaFiles.add(featureName);
                }

            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }




    }

    public String getTagValue(String tag, Element elemento) {

        NodeList lista = elemento.getElementsByTagName(tag).item(0).getChildNodes();

        Node valor = (Node) lista.item(0);

        return valor.getNodeValue();

    }
    public static final Color FEATURES_TREECOLOR = new Color(234, 238, 255);
    public static final String FEATURES_URL = "features/features.xml";
    public static final String NEW_FEATURES_URL = "features/new.xml";

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        Parameters.loadProperties();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    createAndShowGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}

abstract class TreeSelectionAdapter implements TreeSelectionListener {

    public abstract void valueChanged(TreeSelectionEvent evt);
}
