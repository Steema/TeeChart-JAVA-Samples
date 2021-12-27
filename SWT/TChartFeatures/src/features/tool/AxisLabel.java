/*
 * AxisLabel.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;

import com.steema.teechart.IBaseChart;
import com.steema.teechart.axis.Axis;
import com.steema.teechart.tools.ToolAxis;

/**
 *
 * @author tom
 */
@SuppressWarnings("serial")
public class AxisLabel extends ToolAxis {

    protected String millionPrefix = "M";
    protected String thousandPrefix = "T";

    /** Creates a new instance of AxisLabel */
    public AxisLabel() {
        this((IBaseChart)null);
    }

    public AxisLabel(IBaseChart c) {
        super(c);
    }

    public AxisLabel(Axis a) {
        this(a.getChart());
        iAxis = a;
    }

    public void setChart(IBaseChart value) {
        super.setChart(value);
        //set get AxisLabel;
    }

    public void setMillion(String value) {
        if (millionPrefix != value) {
            millionPrefix = value;
            invalidate();
        }
    }

    public void setThousand(String value) {
        if (thousandPrefix != value) {
            thousandPrefix = value;
            invalidate();
        }
    }
}
