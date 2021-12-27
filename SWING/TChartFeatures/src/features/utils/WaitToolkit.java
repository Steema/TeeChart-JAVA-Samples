/*
 * WaitToolkit.java
 *
 * <p>Copyright: Copyright (c) 2004-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.utils;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.Calendar;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;

//import thirdparty.waitwithstyle.PerformanceInfiniteProgressPanel;

/**
 *
 * @author tom
 */
public class WaitToolkit {
    public static final Cursor WAIT_CURSOR = new Cursor( Cursor.WAIT_CURSOR);
    private final static MouseAdapter mouseAdapter = new MouseAdapter() {};

    //private final static PerformanceInfiniteProgressPanel waitPanel = new PerformanceInfiniteProgressPanel();

    private final static JPanel defaultPanel = new JPanel();

    static {
        defaultPanel.setEnabled(false);
        defaultPanel.setOpaque(false);

        //waitPanel.setCursor(WAIT_CURSOR);
        //waitPanel.addMouseListener(mouseAdapter);
    }

    private WaitToolkit() {};

    public static void startWait(JComponent component) {
        RootPaneContainer root =
                ((RootPaneContainer) component.getTopLevelAncestor());

        //root.setGlassPane(waitPanel);
        //waitPanel.repaint();
        //waitPanel.start();
    }

    /**
     * Sets cursor for specified component to normal cursor
     */
    public static void stopWait(JComponent component) {
        RootPaneContainer root =
                ((RootPaneContainer) component.getTopLevelAncestor());
        root.setGlassPane(defaultPanel);

        //waitPanel.stop();
    }

    public static ActionListener createListener(final JComponent component, final ActionListener mainActionListener) {
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(final ActionEvent ae) {
                startWait(component);
                try {
                    mainActionListener.actionPerformed(ae);
                } finally {
                    stopWait(component);
                }
            }
        };
        int t = Calendar.MILLISECOND;
        return actionListener;
    }

    /*
    private class SimpleWaitPane extends JComponent {
        protected void paintComponent(Graphics g) {
            Rectangle oClip = g.getClipBounds();
            g.setColor(new Color(255, 255, 255, 180));
            g.fillRect(oClip.x, oClip.y, oClip.width, oClip.height);
        }
    }
    */
}
