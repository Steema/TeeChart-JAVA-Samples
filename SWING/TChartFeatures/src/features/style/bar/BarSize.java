/*
 * BarSize.java
 *
 * <p>Copyright: (c) 2004-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 *
 * EXAMPLE:
 * Implementing a new Bar series that can display individual bar points
 * with different sizes (different widths).
 *
 * The example form (BarSizeDemo) also shows how to position
 * each bar at a custom "X".
 *
 *
 */

package features.style.bar;

import com.steema.teechart.IBaseChart;
import com.steema.teechart.drawing.Point;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.ValueList;

/**
 *
 * @author tom
 * @version 1.0
 */
public class BarSize extends Bar {

    private ValueList sizeValues;
    private int iSize;

    /**
     * Creates a new instance of BarSize without associating it to any Chart.
     */
    public BarSize() {
        this((IBaseChart)null);
    }

    /**
     * Creates a new instance of BarSize series associated to Chart parameter.
     *
     * @param chart IBaseChart
     */
    public BarSize(final IBaseChart chart) {
        super(chart);

        // Create storage for each Bar "size" value.
        sizeValues = new ValueList(this, "Size");
    }

    private void internalCalcBarSize(int valueIndex) {
        if ((sizeValues == null) || (sizeValues.getValue(valueIndex)==0)) {
            iBarSize = iSize;
        } else {
            iBarSize = getHorizAxis().calcSizeValue(sizeValues.getValue(valueIndex));
        }
    }

    protected boolean internalClicked(int valueIndex, Point point) {
        internalCalcBarSize(valueIndex);
        return super.internalClicked(valueIndex,point);
    }

    public void drawValue(int valueIndex) {
        if (valueIndex == getFirstVisible()) {
            iSize = iBarSize;
        }

        internalCalcBarSize(valueIndex);

        // Call default Bar drawing method.
        super.drawValue(valueIndex);
    }

    public void setSizeValues(final ValueList valueList) {
        sizeValues = valueList;
    }

    public ValueList getSizeValues() {
        return sizeValues;
    }
}
