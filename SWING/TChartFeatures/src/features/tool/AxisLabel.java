/*
 * AxisLabel.java
 *
 * <p>Copyright: (c) 2005-2011 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool;

import com.steema.teechart.IBaseChart;
import com.steema.teechart.axis.Axis;
import com.steema.teechart.axis.AxisLabelResolver;
import com.steema.teechart.axis.NextAxisLabelValue;
import com.steema.teechart.events.ScrollModEventArgs;
import com.steema.teechart.styles.ISeries;
import com.steema.teechart.tools.ToolAxis;
import java.awt.event.ItemEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

/**
 *
 * @author tom
 */
public class AxisLabel extends ToolAxis {

    protected String millionSuffix = "M";
    protected String thousandSuffix = "K";

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
        setResolver(true);
    }
    
    public void setResolver(boolean value)
    {
        Axis a = iAxis;
       
        if ((value==true) && (a !=null))
        {
            a.getChart().getParent().setAxisLabelResolver(new AxisLabelResolver() {

                public String getLabel(Axis axis, ISeries s, int valueIndex, String labelText) {

                    if ((iAxis!=null) && (axis !=null))
                    {
                       String tmpSt=labelText;
                       NumberFormat nf = NumberFormat.getNumberInstance();
                       DecimalFormat df = (DecimalFormat)nf;
                       DecimalFormatSymbols dffs = df.getDecimalFormatSymbols();
                       dffs.getGroupingSeparator();

                       //ready the number format for the machine
                       tmpSt = tmpSt.replace(Character.toString(dffs.getGroupingSeparator()), "");
                       tmpSt = tmpSt.replace(Character.toString(dffs.getDecimalSeparator()), ".");
                       double tmp = Double.parseDouble(tmpSt);

                       //put the number format in displayable format
                       if (Math.abs(tmp)>=100000)
                       {
                           df.applyLocalizedPattern("0"+dffs.getDecimalSeparator()+"##");
                           labelText = df.format(tmp/1000000)+millionSuffix;
                       }
                       else if (Math.abs(tmp)>=1000)
                       {
                           labelText = "K";
                           df.applyLocalizedPattern("0"+dffs.getDecimalSeparator()+"##");
                           labelText = df.format(tmp/1000)+thousandSuffix;
                       }
                    }

                    return labelText;
               }

                public NextAxisLabelValue getNextLabel(Axis axis, int labelIndex, NextAxisLabelValue nextLabel) {
                    return nextLabel;
                }

                public void scrollModHandler(Axis a, ScrollModEventArgs e) {
                    // n/a
                }
            });
        }
        else
            a.getChart().getParent().removeAxisLabelResolver();
    }

    public void setActive(boolean value)
    {
       setResolver(value);
       super.setActive(value);
    }

    public void setChart(IBaseChart value) {
        super.setChart(value);
    }

    public void setMillion(String value) {
        if (millionSuffix != value) {
            millionSuffix = value;
            invalidate();
        }
    }

    public void setThousand(String value) {
        if (thousandSuffix != value) {
            thousandSuffix = value;
            invalidate();
        }
    }

    public IBaseChart getChart() {
        return super.getChart();
    }
}
