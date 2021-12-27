package com.steema.teechart.android.editors;

import com.steema.teechart.android.editors.styles.*;

public class Utils {

    //full path to avoid classname clashes (eg ShapeEditor)
    @SuppressWarnings("rawtypes")
	final static public Class[] seriesEditorsOf = {
            LineEditor.class,
            null, //PointsEditor.class,
            null, //AreaEditor.class,
            null, //FastLineEditor.class,
            LineEditor.class,
            BarEditor.class,
            BarEditor.class,
            PieEditor.class,
            null, //com.steema.teechart.editors.series.ShapeEditor.class,
            null, //ArrowEditor.class,
            null, //PointsEditor.class, //Bubble
            null, //GanttEditor.class,
            null, //CandleEditor.class,
            null, //DonutEditor.class,
            null, //VolumeEditor.class,
            BarEditor.class, //Bar3D
            null, //Point3DEditor.class, //Points3D
            null, //PolarEditor.class,
            null, //PolarEditor.class, //Radar
            null, //ClockEditor.class,
            null, //PolarEditor.class, //WindRose
            null, //PyramidEditor.class,
            null, //SurfaceEditor.class,
            null, //PointsEditor.class, //LinePoint
            null, //BarEditor.class, //BarJoin
            null, //ColorGridEditor.class,
            null, //WaterFallEditor.class,
            null, //HistoEditor.class,
            null, //ErrBarEditor.class, //Error
            null, //ErrBarEditor.class,
            null, //ContourEditor.class,
            null, //SmithEditor.class,
            null, //CalendarEditor.class,
            null, //HighLowEditor.class,
            null, //TriSurfaceEditor.class,
            null, //FunnelEditor.class,
            null, //BoxPlotEditor.class,
            null, //BoxPlotEditor.class, //HorizBox
            null, //AreaEditor.class,
            null, //TowerEditor.class,
            null, //PointFigureEditor.class,
            null, //GaugesEditor.class,
            null, //Vector3DEditor.class,
            null, //MapEditor.class,
            null, //BezierEditor.class,
            null, //ImageEditor.class,
            null, //IsoSurfaceEditor.class,
            null, //CircularGaugeEditor.class,
            null, //LinearGaugeEditor.class, //VerticalLinearGauge
            null //HistoEditor.class //HorizHistogram
    };
}
