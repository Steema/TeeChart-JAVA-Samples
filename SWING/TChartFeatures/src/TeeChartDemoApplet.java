import java.applet.*;
import java.awt.BorderLayout;

/**
 * <p>Title: TeeChart for Java</p>
 *
 * <p>Description: TeeChart charting component library</p>
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 *
 * @author not attributable
 * @version 1.0
 */
public class TeeChartDemoApplet extends Applet {

    public TeeChartDemoApplet() {
        super();
        setLayout(new BorderLayout());

        TeeChartDemo d = new TeeChartDemo();
        d.closeButton.setVisible(false);
        add(d, BorderLayout.CENTER);
    }
}
