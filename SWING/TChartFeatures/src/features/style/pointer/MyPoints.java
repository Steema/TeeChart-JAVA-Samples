/*
 * MyPoints.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.pointer;

import com.steema.teechart.IBaseChart;
import com.steema.teechart.drawing.ChartPen;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.IGraphics3D;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Points;

/**
 *
 * @author tom
 */
public class MyPoints extends Points {

    private ChartPen linesPen;

    /** Creates a new instance of MyPoints */
    public MyPoints() {
        this((IBaseChart)null);
    }

    public MyPoints(IBaseChart c) {
        super(c);

        getPointer().setDraw3D(false);
        getPointer().setStyle(PointerStyle.DIAMOND);
    }

    /**
     * Determines the pen to draw the lines between each point and
     * vertical/horizontal axes.
     *
     * @return ChartPen
     */
    public ChartPen getLinesPen() {
        if (linesPen == null) {
            linesPen = new ChartPen(chart, Color.RED);
        }
        return linesPen;
    }

    /**
     * Called internally. Draws the "ValueIndex" point of the Series.
     *
     * @param valueIndex int
     */
    public void drawValue(int valueIndex) {
        //calculate the point position
        int tmpX = calcXPos(valueIndex);
        int tmpY = calcYPos(valueIndex);

        IGraphics3D g = chart.getGraphics3D();
        g.getBrush().setVisible(false);
        g.setPen(getLinesPen());

        g.moveTo(getVertAxis().getPosAxis(), tmpY,  getStartZ());
        g.lineTo(tmpX, tmpY, getStartZ());
        g.lineTo(tmpX, getHorizAxis().getPosAxis(), getStartZ());

        super.drawValue(valueIndex);
    }
}
