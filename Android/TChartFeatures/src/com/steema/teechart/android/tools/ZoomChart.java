package com.steema.teechart.android.tools;

import com.steema.teechart.Aspect;
import com.steema.teechart.Chart;
import com.steema.teechart.Cursor;
import com.steema.teechart.events.FrameworkMouseEvent;
import com.steema.teechart.misc.MathUtils;
import com.steema.teechart.tools.Tool;

@SuppressWarnings("serial")
public class ZoomChart extends Tool {
	
	public boolean inverted=false;
	public int button=FrameworkMouseEvent.BUTTON1;

	transient private int iOldX;
	transient private int iOldY;
	transient private boolean dragging;

	public ZoomChart(Chart chart) {
		super(chart);
	}

	private void mouseMove(FrameworkMouseEvent e) {

        int tmpX = MathUtils.round(90.0 * (e.getX() - iOldX) / chart.getWidth());
        if (inverted) {
            tmpX = -tmpX;
        }

        int tmpY = MathUtils.round(90.0 * (iOldY - e.getY()) / chart.getHeight());
        if (inverted) {
            tmpY = -tmpY;
        }

        doAction(tmpX,tmpY);
        
        iOldX = e.getX();
        iOldY = e.getY();
        chart.setCancelMouse(true);
    }

    protected void doAction(int x, int y) {
        Aspect a = chart.getAspect();

        a.setView3D(true);
        a.setZoom(a.getZoom()+(x+y));
	}

	public Cursor mouseEvent(FrameworkMouseEvent e, Cursor c) {
        if (e.getID() == FrameworkMouseEvent.MOUSE_RELEASED) {
            if (dragging) {
                dragging = false;
            }
        } else
        if (e.getID() == FrameworkMouseEvent.MOUSE_MOVED) {
            if (dragging) {
                mouseMove(e);
            }
        } else
        if (e.getID() == FrameworkMouseEvent.MOUSE_PRESSED) {
            if (e.getButton() == button) {
                dragging = true;
                iOldX = e.getX();
                iOldY = e.getY();
                chart.setCancelMouse(true);
            }
        }
        return c;
    }
}
