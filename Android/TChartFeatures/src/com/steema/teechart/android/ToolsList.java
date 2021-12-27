package com.steema.teechart.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.steema.teechart.Chart;
import com.steema.teechart.TChart;
import com.steema.teechart.android.tools.PanChart;
import com.steema.teechart.android.tools.PanData;
import com.steema.teechart.android.tools.ZoomChart;
import com.steema.teechart.android.tools.ZoomData;
import com.steema.teechart.android.tools.ZoomText;
import com.steema.teechart.drawing.ChartFont;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.drawing.HatchStyle;
import com.steema.teechart.events.FrameworkMouseEvent;
import com.steema.teechart.events.SeriesMouseAdapter;
import com.steema.teechart.events.SeriesMouseEvent;
import com.steema.teechart.events.ToolMouseListener;
import com.steema.teechart.languages.Language;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.SeriesPointer;
import com.steema.teechart.themes.ThemesList;
import com.steema.teechart.tools.Annotation;
import com.steema.teechart.tools.AxisArrow;
import com.steema.teechart.tools.AxisArrowPosition;
import com.steema.teechart.tools.ColorBand;
import com.steema.teechart.tools.DragPoint;
import com.steema.teechart.tools.DragPointStyle;
import com.steema.teechart.tools.DrawLine;
import com.steema.teechart.tools.DrawLineItem;
import com.steema.teechart.tools.ExtraLegend;
import com.steema.teechart.tools.GridBand;
import com.steema.teechart.tools.GridTranspose;
import com.steema.teechart.tools.MarksTip;
import com.steema.teechart.tools.Rotate;
import com.steema.teechart.tools.ScrollPager;
import com.steema.teechart.tools.SeriesBand;

/**
 * 
 * <p>
 * Title:
 * </p>
 * 
 * <p>
 * Description: ThemesList is a collection of Theme objects.
 * </p>
 * 
 * <p>
 * Copyright (c) 2005-2008 by Steema Software SL. All Rights Reserved.
 * </p>
 * 
 * <p>
 * Company: Steema Software SL
 * </p>
 * 
 * <p>
 * Example:
 * 
 * <pre>
 * <font face="Courier" size="4">
 * ThemesList.applyTheme(myChart.getChart(), new
 * ExcelTheme(myChart.getChart()));
 * </font>
 * </pre>
 * 
 * </p>
 * 
 * @see com.steema.teechart.themes.Theme
 */
public final class ToolsList {

	final static int PIE = 0, LINE = 1, BAR = 2, AREA = 3, POINT = 4, HORIZBAR = 5, CANDLE = 6, SURFACE = 7, TOWER = 8, CONTOUR = 9, COLORGRID = 10,
			POLAR = 11, SMITH = 12, RADAR = 13, FASTLINE = 14, ISOSURFACE = 15, BUBBLE = 16, CIRCULARGAUGE = 17, LINEARGAUGE = 18, MAP = 19;
	private static MarksTip marksTip;
	private static Annotation annotationClick1, annotationClick2, annotationClick3, annotation1, annotation2, annotation3;
	private static AxisArrow axisArrow1, axisArrow2, axisArrow3;
	private static SeriesBand seriesBand1;
	private static DrawLine drawLine;
	private static ExtraLegend extraLegend1;
	private static Legend legend1;
	private static ColorBand colorBand1;
	private static SeriesPointer seriesPointer1, seriesPointer2;
	private static DragPoint dragPoint1, dragPoint2;
	private static GridTranspose gridTranspose1;
	private static GridBand gridBand1;
	private static ZoomText zoomText;
	private static PanData panData;
	private static ZoomData zoomData;
	private static ZoomChart zoomChart;
	private static PanChart panChart;
	private static ScrollPager scrollPager1;
	private static int tooltoDisable = 33;

	public static int size() {
		return 11;
	}

	public static void applyTool(final Chart chart, int index, int serieSelected, final Context context) {
		switch (serieSelected) {
		case PIE: // Pie series selected
			switch (index) {
			case 0: // Annotation Click

				// Init Chart
				chart.getHeader().setVisible(true);
				chart.getLegend().setVisible(false);
				// Start tool
				annotationClick1 = new Annotation(chart.getChart());
				annotationClick1.getShape().setCustomPosition(true);
				annotationClick1.setText("Annotation 1");
				annotationClick1.getShape().setCustomPosition(true);
				annotationClick1.setLeft(65);
				annotationClick1.setTop(125);
				ToolMouseListener annotationClick1Listener = new ToolMouseListener() {
					public void toolClicked(FrameworkMouseEvent e) {
						AlertDialog dialog = new AlertDialog.Builder(context).create();
						dialog.setTitle(Language.getString("Message"));
						dialog.setMessage(annotationClick1.getText());

						dialog.setButton(DialogInterface.BUTTON_POSITIVE, Language.getString("OK"), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								dialog.cancel();
							}
						});
						dialog.show();
					}
				};
				annotationClick1.addToolMouseListener(annotationClick1Listener);
				annotationClick2 = new Annotation(chart.getChart());
				annotationClick2.getShape().setCustomPosition(true);
				annotationClick2.setText("Annotation 2");
				annotationClick2.getShape().setCustomPosition(true);
				annotationClick2.setLeft(130);
				annotationClick2.setTop(70);

				annotationClick2.getShape().setColor(Color.RED);
				annotationClick2.getShape().getFont().setColor(Color.BLUE);
				ToolMouseListener annotationClick2Listener = new ToolMouseListener() {
					public void toolClicked(FrameworkMouseEvent e) {
						AlertDialog dialog = new AlertDialog.Builder(context).create();
						dialog.setTitle(Language.getString("Message"));
						dialog.setMessage(annotationClick2.getText());

						dialog.setButton(DialogInterface.BUTTON_POSITIVE, Language.getString("OK"), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								dialog.cancel();
							}
						});
						dialog.show();
					}
				};
				annotationClick2.addToolMouseListener(annotationClick2Listener);

				annotationClick3 = new Annotation(chart.getChart());
				annotationClick3.getShape().setCustomPosition(true);
				annotationClick3.setText("Another one\nwith multiple\nlines of text.");
				annotationClick3.setLeft(130);
				annotationClick3.setTop(170);
				annotationClick3.getShape().setColor(Color.YELLOW);
				ToolMouseListener annotationClick3Listener = new ToolMouseListener() {
					public void toolClicked(FrameworkMouseEvent e) {
						AlertDialog dialog = new AlertDialog.Builder(context).create();
						dialog.setTitle(Language.getString("Message"));
						dialog.setMessage(annotationClick3.getText());

						dialog.setButton(DialogInterface.BUTTON_POSITIVE, Language.getString("OK"), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								dialog.cancel();
							}
						});
						dialog.show();

					}
				};

				annotationClick3.addToolMouseListener(annotationClick3Listener);
				break;

			case 1: // Marks Tip
				chart.getHeader().setVisible(false);
				chart.getLegend().setVisible(false);
				// Start tool
				marksTip = new com.steema.teechart.tools.MarksTip(chart.getChart());

				for (int i = 0; i < chart.getSeriesCount(); i++) {
					final Series s = chart.getSeries(i);
					s.addSeriesMouseListener(new SeriesMouseAdapter() {
						public void seriesClicked(SeriesMouseEvent e) {

							marksTip.setMouseDelay(0);
							marksTip.setSeries(s);
							Toast t;
							t = Toast.makeText(context, marksTip.getSeries().getValueMarkText(e.getValueIndex()), Toast.LENGTH_SHORT);
							t.setGravity(Gravity.TOP | Gravity.LEFT, e.getPoint().x - 50, e.getPoint().y + ((View) chart.getParent()).getTop());
							t.show();
						};
					});
				}
				break;
			case 2: // Axis Label
				// Init Chart
				chart.getLegend().setVisible(false);
				ChartFont tmpFont = chart.getAxes().getLeft().getLabels().getFont();
				tmpFont.setColor(Color.NAVY);
				tmpFont.setSize(13);
				tmpFont.setBold(true);
				tmpFont.getShadow().setColor(Color.AQUA);
				tmpFont.getShadow().setHorizSize(1);
				tmpFont.getShadow().setVertSize(-1);

				chart.getSeries(0).getMarks().setVisible(true);
				chart.getSeries(0).getMarks().setColor(Color.RED);
				break;
			case 3: // Rotate
				new Rotate(chart.getChart());
				break;
			case 4: // Drag Rectangle
				break;
			default:
				selectDefaultTool(chart, index);
				break;

			}
			break;
		case LINE: // Line series selected
			switch (index) {
			case 0:
				// Drag Point
				chart.getLegend().setVisible(false);
				chart.getHeader().setVisible(true);
				seriesPointer1 = ((com.steema.teechart.styles.Line) chart.getSeries(0)).getPointer();
				seriesPointer1.getBrush().setColor(Color.RED);
				seriesPointer1.setVertSize(7);
				seriesPointer1.setHorizSize(7);
				seriesPointer1.setInflateMargins(true);
				seriesPointer1.getPen().setColor(Color.LIME);
				seriesPointer1.setVisible(true);

				dragPoint1 = new DragPoint(chart.getSeries(0));
				dragPoint1.setStyle(DragPointStyle.BOTH);

				seriesPointer2 = ((com.steema.teechart.styles.Line) chart.getSeries(1)).getPointer();
				seriesPointer2.getBrush().setColor(Color.RED);
				seriesPointer2.setVertSize(7);
				seriesPointer2.setHorizSize(7);
				seriesPointer2.setInflateMargins(true);
				seriesPointer2.getPen().setColor(Color.MAROON);
				seriesPointer2.setVisible(true);

				dragPoint2 = new DragPoint(chart.getSeries(1));
				dragPoint2.setStyle(DragPointStyle.BOTH);
				break;

			case 1: // series band
				// init chart
				chart.getAspect().setView3D(false);
				chart.getHeader().setVisible(true);
				chart.getHeader().setText("SeriesBand Tool Example");
				chart.getLegend().setVisible(false);

				seriesBand1 = new SeriesBand(chart.getChart());
				seriesBand1.setSeries(chart.getSeries(0));
				seriesBand1.setSeries2(chart.getSeries(1));

				seriesBand1.getGradient().setVisible(true);
				seriesBand1.getGradient().setStartColor(Color.SILVER);

				break;
			case 2:// Rotate
				new Rotate(chart.getChart());
				break;
			case 3: // Marks tip
				chart.getHeader().setVisible(false);
				chart.getLegend().setVisible(false);
				// Start tool
				marksTip = new com.steema.teechart.tools.MarksTip(chart.getChart());

				for (int i = 0; i < chart.getSeriesCount(); i++) {
					final Series s = chart.getSeries(i);
					s.addSeriesMouseListener(new SeriesMouseAdapter() {
						public void seriesClicked(SeriesMouseEvent e) {

							marksTip.setMouseDelay(0);
							marksTip.setSeries(s);
							Toast t;
							t = Toast.makeText(context, marksTip.getSeries().getValueMarkText(e.getValueIndex()), Toast.LENGTH_SHORT);
							t.setGravity(Gravity.TOP | Gravity.LEFT, e.getPoint().x - 50, e.getPoint().y + ((View) chart.getParent()).getTop());
							t.show();
						};
					});
				}
				break;
			case 4: // Extra Legend

				chart.getLegend().setVisible(true);
				chart.getLegend().setLegendStyle(LegendStyle.VALUES);

				// Start Tool
				for (int i = 0; i < chart.getSeriesCount(); i++) {
					Series seriesLengend = chart.getSeries(i);
					seriesLengend.fillSampleValues(5);
					seriesLengend.getMarks().setVisible(false);
				}
				extraLegend1 = new ExtraLegend(chart.getChart());
				extraLegend1.setSeries(chart.getSeries(1));

				// Cosmetic
				legend1 = extraLegend1.getLegend();
				legend1.setLegendStyle(LegendStyle.VALUES);
				legend1.getShadow().setTransparency(70);
				legend1.setCustomPosition(true);
				legend1.setLeft(50);
				legend1.setTop(50);
				break;
			case 10: // Scroll Pager

				chart.getAspect().setView3D(false);
				chart.getHeader().setVisible(true);
				chart.getHeader().setText("Scroll Pager Tool Example");
				chart.getLegend().setVisible(false);

				scrollPager1 = new ScrollPager(chart.getChart(), context);
				scrollPager1.setSeries(chart.getSeries(0));

				TChart scrollPagerChart = scrollPager1.getSubChartTChart();
				Series s = chart.getSeries(1).cloneSeries();
				s.setChart(null);
				scrollPagerChart.getSeries().add(s);

				Bundle extras = ((ChartView) context).getIntent().getExtras();
				if (extras.getBoolean("ThemeSelected", true)) {
					ThemesList.applyTheme(scrollPagerChart.getChart(), extras.getInt("numThemeSelected"));
				} else {
					ThemesList.applyTheme(scrollPagerChart.getChart(), 1);
				}
				break;
			default:
				selectDefaultTool(chart, index);
				break;
			}

			break;
		case BAR: // Bar series selected
			switch (index) {
			case 0: // Draw Line
				chart.getLegend().setVisible(false);
				// Start tool

				drawLine = new com.steema.teechart.tools.DrawLine(chart.getChart());
				drawLine.getPen().setColor(Color.RED);

				// create a new DrawLine
				DrawLineItem tmpLine = new DrawLineItem(drawLine);

				// set the "X" line positions (start and end position)
				tmpLine.getStartPos().x = 5.0;
				tmpLine.getEndPos().x = 15.0;

				// set the "Y" line positions (start and end position)
				double tmp = (chart.getSeries(0).getYValues().getMaximum() - chart.getSeries(0).getYValues().getMinimum()) / 5;
				tmpLine.getStartPos().y = chart.getSeries(0).getYValues().getMaximum() - tmp;
				tmpLine.getEndPos().y = chart.getSeries(0).getYValues().getMinimum() + tmp;
				break;
			case 1: // Extra Legend

				chart.getLegend().setVisible(true);
				chart.getLegend().setLegendStyle(LegendStyle.VALUES);

				// Start Tool
				for (int i = 0; i < chart.getSeriesCount(); i++) {
					Series seriesLengend = chart.getSeries(i);
					seriesLengend.fillSampleValues(5);
					seriesLengend.getMarks().setVisible(false);
				}
				extraLegend1 = new ExtraLegend(chart.getChart());
				extraLegend1.setSeries(chart.getSeries(1));

				// Cosmetic
				legend1 = extraLegend1.getLegend();
				legend1.setLegendStyle(LegendStyle.VALUES);
				legend1.getShadow().setTransparency(70);
				legend1.setCustomPosition(true);
				legend1.setLeft(50);
				legend1.setTop(50);
				break;
			case 2: // ColorBand
				chart.getLegend().setVisible(false);
				// tool1
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setStyle(HatchStyle.BACKWARDDIAGONAL);
				colorBand1.setEnd(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool2
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.BLUE);
				colorBand1.setEnd(200.0);
				colorBand1.setStart(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool3
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.YELLOW);
				colorBand1.getGradient().setDirection(GradientDirection.HORIZONTAL);
				colorBand1.getGradient().setEndColor(Color.GRAY);
				colorBand1.getGradient().setMiddleColor(Color.RED);
				colorBand1.setEnd(300.0);
				colorBand1.setStart(200.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool4
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.LIME);
				colorBand1.setEnd(400.0);
				colorBand1.setStart(300.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool5
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.RED);
				colorBand1.setEnd(700.0);
				colorBand1.setStart(500.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());
				break;

			case 3: // Annotation
				// Init Chart
				chart.getHeader().setVisible(true);
				chart.getLegend().setVisible(false);
				// Start tool
				annotation1 = new Annotation(chart.getChart());
				annotation1.getShape().setCustomPosition(true);
				annotation1.setText("Annotation 1");
				annotation1.setLeft(65);
				annotation1.setTop(125);

				annotation2 = new Annotation(chart.getChart());
				annotation2.getShape().setCustomPosition(true);
				annotation2.setText("Annotation 2");
				annotation2.setLeft(130);
				annotation2.setTop(70);

				annotation2.getShape().setColor(Color.RED);
				annotation2.getShape().getFont().setColor(Color.BLUE);

				annotation3 = new Annotation(chart.getChart());
				annotation3.getShape().setCustomPosition(true);
				annotation3.setText("Another one\nwith multiple\nlines of text.");
				annotation3.setLeft(130);
				annotation3.setTop(170);
				annotation3.getShape().setColor(Color.YELLOW);

				break;
			case 4: // Rotate
				new Rotate(chart.getChart());
				break;
			default:
				selectDefaultTool(chart, index);
				break;
			}
			break;

		case AREA: // Area series selected
			switch (index) {
			case 0:// Annotation

				// Init Chart
				chart.getHeader().setVisible(true);
				chart.getLegend().setVisible(false);
				// Start tool
				annotation1 = new Annotation(chart.getChart());
				annotation1.getShape().setCustomPosition(true);
				annotation1.setText("Annotation 1");
				annotation1.setLeft(65);
				annotation1.setTop(125);

				annotation2 = new Annotation(chart.getChart());
				annotation2.getShape().setCustomPosition(true);
				annotation2.setText("Annotation 2");
				annotation2.setLeft(130);
				annotation2.setTop(70);

				annotation2.getShape().setColor(Color.RED);
				annotation2.getShape().getFont().setColor(Color.BLUE);

				annotation3 = new Annotation(chart.getChart());
				annotation3.getShape().setCustomPosition(true);
				annotation3.setText("Another one\nwith multiple\nlines of text.");
				annotation3.setLeft(130);
				annotation3.setTop(170);
				annotation3.getShape().setColor(Color.YELLOW);

				break;
			case 1: // ColorBand
				chart.getLegend().setVisible(false);
				// tool1
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setStyle(HatchStyle.BACKWARDDIAGONAL);
				colorBand1.setEnd(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool2
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.BLUE);
				colorBand1.setEnd(200.0);
				colorBand1.setStart(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool3
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.YELLOW);
				colorBand1.getGradient().setDirection(GradientDirection.HORIZONTAL);
				colorBand1.getGradient().setEndColor(Color.GRAY);
				colorBand1.getGradient().setMiddleColor(Color.RED);
				colorBand1.setEnd(300.0);
				colorBand1.setStart(200.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool4
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.LIME);
				colorBand1.setEnd(400.0);
				colorBand1.setStart(300.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool5
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.RED);
				colorBand1.setEnd(700.0);
				colorBand1.setStart(500.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());
				break;

			case 2: // Rotate
				new Rotate(chart.getChart());
				break;
			case 3: // Series Band
				// init chart
				chart.getAspect().setView3D(false);
				chart.getHeader().setVisible(true);
				chart.getHeader().setText("SeriesBand Tool Example");
				chart.getLegend().setVisible(false);
				for (int i = 0; i < chart.getSeries(0).getCount(); i++) {
					chart.getSeries(1).add(chart.getSeries(0).getXValues().getValue(i), chart.getSeries(0).getYValues().getValue(i) / 2.0);
				}

				seriesBand1 = new SeriesBand(chart.getChart());
				seriesBand1.setSeries(chart.getSeries(0));
				seriesBand1.setSeries2(chart.getSeries(1));

				seriesBand1.getGradient().setVisible(true);
				seriesBand1.getGradient().setStartColor(Color.SILVER);

				break;
			case 4: // Axis Arrow
				// Init Chart
				chart.getLegend().setVisible(false);

				// Start tool
				axisArrow1 = new AxisArrow(chart.getAxes().getLeft());
				axisArrow1.setPosition(AxisArrowPosition.START);
				axisArrow1.setLength(70);

				axisArrow2 = new AxisArrow(chart.getAxes().getBottom());
				axisArrow2.getBrush().setColor(Color.GREEN_YELLOW);
				axisArrow2.getPen().setColor(Color.WHITE);
				axisArrow2.setPosition(AxisArrowPosition.BOTH);
				axisArrow2.setLength(70);

				axisArrow3 = new AxisArrow(chart.getAxes().getLeft());
				axisArrow3.setPosition(AxisArrowPosition.END);
				axisArrow3.getBrush().setColor(Color.WHITE);
				axisArrow3.getPen().setColor(Color.BLUE);
				axisArrow3.setLength(70);
				break;
			default:
				selectDefaultTool(chart, index);
				break;
			}
			break;
		case POINT: // Point series selected

			switch (index) {
			case 0:// Rotate
				new Rotate(chart.getChart());
				break;
			case 1: // Drag Point
				chart.getLegend().setVisible(false);
				chart.getHeader().setVisible(true);
				seriesPointer1 = ((com.steema.teechart.styles.Points) chart.getSeries(0)).getPointer();
				seriesPointer1.getBrush().setColor(Color.RED);
				seriesPointer1.setVertSize(7);
				seriesPointer1.setHorizSize(7);
				seriesPointer1.setInflateMargins(true);
				seriesPointer1.getPen().setColor(Color.LIME);
				seriesPointer1.setVisible(true);

				dragPoint1 = new DragPoint(chart.getSeries(0));
				dragPoint1.setStyle(DragPointStyle.BOTH);
				break;
			case 2: // GridBand
				chart.getAspect().setView3D(false);
				chart.getSeries(0).fillSampleValues();
				chart.getSeries(0).getMarks().setVisible(false);
				chart.getSeries(0).setColor(Color.GREEN);

				// Create GridBandTool...
				gridBand1 = new com.steema.teechart.tools.GridBand(chart.getChart());
				gridBand1.setAxis(chart.getAxes().getLeft());

				// cosmetic examples:
				// GetTeeBrush(0,Band1.Image.Bitmap);
				gridBand1.getBand1().setColor(Color.RED);

				gridBand1.getBand2().setStyle(HatchStyle.CROSS);
				gridBand1.getBand2().setColor(Color.YELLOW);

				// Change Left axis grid
				chart.getAxes().getLeft().getGrid().setColor(Color.RED);
				chart.getAxes().getLeft().getGrid().setStyle(DashStyle.SOLID);
				break;
			case 3: // ColorBand
				chart.getLegend().setVisible(false);
				// tool1
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setStyle(HatchStyle.BACKWARDDIAGONAL);
				colorBand1.setEnd(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool2
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.BLUE);
				colorBand1.setEnd(200.0);
				colorBand1.setStart(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool3
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.YELLOW);
				colorBand1.getGradient().setDirection(GradientDirection.HORIZONTAL);
				colorBand1.getGradient().setEndColor(Color.GRAY);
				colorBand1.getGradient().setMiddleColor(Color.RED);
				colorBand1.setEnd(300.0);
				colorBand1.setStart(200.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool4
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.LIME);
				colorBand1.setEnd(400.0);
				colorBand1.setStart(300.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool5
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.RED);
				colorBand1.setEnd(700.0);
				colorBand1.setStart(500.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());
				break;

			case 4: // Annotation click

				// Init Chart
				chart.getHeader().setVisible(true);
				chart.getLegend().setVisible(false);

				// Start tool
				annotationClick1 = new Annotation(chart.getChart());
				annotationClick1.getShape().setCustomPosition(true);
				annotationClick1.setText("Annotation 1");
				annotationClick1.setLeft(65);
				annotationClick1.setTop(125);

				ToolMouseListener annotationClick1Listener = new ToolMouseListener() {
					public void toolClicked(FrameworkMouseEvent e) {
						AlertDialog dialog = new AlertDialog.Builder(context).create();
						dialog.setTitle(Language.getString("Message"));
						dialog.setMessage(annotationClick1.getText());

						dialog.setButton(DialogInterface.BUTTON_POSITIVE, Language.getString("OK"), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								dialog.cancel();
							}
						});
						dialog.show();
					}
				};
				annotationClick1.addToolMouseListener(annotationClick1Listener);

				annotationClick2 = new Annotation(chart.getChart());
				annotationClick2.getShape().setCustomPosition(true);
				annotationClick2.setText("Annotation 2");
				annotationClick2.setLeft(130);
				annotationClick2.setTop(70);

				annotationClick2.getShape().setColor(Color.RED);
				annotationClick2.getShape().getFont().setColor(Color.BLUE);
				ToolMouseListener annotationClick2Listener = new ToolMouseListener() {
					public void toolClicked(FrameworkMouseEvent e) {
						AlertDialog dialog = new AlertDialog.Builder(context).create();
						dialog.setTitle(Language.getString("Message"));
						dialog.setMessage(annotationClick2.getText());

						dialog.setButton(DialogInterface.BUTTON_POSITIVE, Language.getString("OK"), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								dialog.cancel();
							}
						});
						dialog.show();
					}
				};
				annotationClick2.addToolMouseListener(annotationClick2Listener);

				annotationClick3 = new Annotation(chart.getChart());
				annotationClick3.getShape().setCustomPosition(true);
				annotationClick3.setText("Another one\nwith multiple\nlines of text.");
				annotationClick3.setLeft(130);
				annotationClick3.setTop(170);
				annotationClick3.getShape().setColor(Color.YELLOW);
				ToolMouseListener annotationClick3Listener = new ToolMouseListener() {
					public void toolClicked(FrameworkMouseEvent e) {
						AlertDialog dialog = new AlertDialog.Builder(context).create();
						dialog.setTitle(Language.getString("Message"));
						dialog.setMessage(annotationClick3.getText());

						dialog.setButton(DialogInterface.BUTTON_POSITIVE, Language.getString("OK"), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								dialog.cancel();
							}
						});
						dialog.show();

					}
				};

				annotationClick3.addToolMouseListener(annotationClick3Listener);
				break;
			default:
				selectDefaultTool(chart, index);
				break;
			}
			break;
		case HORIZBAR: // Horizontal bar series selected
			switch (index) {
			case 0:// Draw Line
				chart.getLegend().setVisible(false);
				// Start tool

				drawLine = new com.steema.teechart.tools.DrawLine(chart.getChart());
				drawLine.getPen().setColor(Color.RED);

				// create a new DrawLine
				DrawLineItem tmpLine = new DrawLineItem(drawLine);

				// set the "X" line positions (start and end position)
				tmpLine.getStartPos().x = 5.0;
				tmpLine.getEndPos().x = 15.0;

				// set the "Y" line positions (start and end position)
				double tmp = (chart.getSeries(0).getYValues().getMaximum() - chart.getSeries(0).getYValues().getMinimum()) / 5;
				tmpLine.getStartPos().y = chart.getSeries(0).getYValues().getMaximum() - tmp;
				tmpLine.getEndPos().y = chart.getSeries(0).getYValues().getMinimum() + tmp;
				break;
			case 1: // Extra Legend

				chart.getLegend().setVisible(true);
				chart.getLegend().setLegendStyle(LegendStyle.VALUES);

				// Start Tool
				for (int i = 0; i < chart.getSeriesCount(); i++) {
					Series seriesLengend = chart.getSeries(i);
					seriesLengend.fillSampleValues(5);
					seriesLengend.getMarks().setVisible(false);
				}
				extraLegend1 = new ExtraLegend(chart.getChart());
				extraLegend1.setSeries(chart.getSeries(1));

				// Cosmetic
				legend1 = extraLegend1.getLegend();
				legend1.setLegendStyle(LegendStyle.VALUES);
				legend1.getShadow().setTransparency(70);
				legend1.setCustomPosition(true);
				legend1.setLeft(50);
				legend1.setTop(50);
				break;
			case 2: // ColorBand
				chart.getLegend().setVisible(false);
				// tool1
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setStyle(HatchStyle.BACKWARDDIAGONAL);
				colorBand1.setEnd(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool2
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.BLUE);
				colorBand1.setEnd(200.0);
				colorBand1.setStart(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool3
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.YELLOW);
				colorBand1.getGradient().setDirection(GradientDirection.HORIZONTAL);
				colorBand1.getGradient().setEndColor(Color.GRAY);
				colorBand1.getGradient().setMiddleColor(Color.RED);
				colorBand1.setEnd(300.0);
				colorBand1.setStart(200.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool4
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.LIME);
				colorBand1.setEnd(400.0);
				colorBand1.setStart(300.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool5
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.RED);
				colorBand1.setEnd(700.0);
				colorBand1.setStart(500.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());
				break;

			case 3:// Annotation
					// Init Chart
				chart.getHeader().setVisible(true);
				chart.getLegend().setVisible(false);
				// Start tool
				annotation1 = new Annotation(chart.getChart());
				annotation1.getShape().setCustomPosition(true);
				annotation1.setText("Annotation 1");
				annotation1.setLeft(65);
				annotation1.setTop(125);

				annotation2 = new Annotation(chart.getChart());
				annotation2.getShape().setCustomPosition(true);
				annotation2.setText("Annotation 2");
				annotation2.setLeft(130);
				annotation2.setTop(70);

				annotation2.getShape().setColor(Color.RED);
				annotation2.getShape().getFont().setColor(Color.BLUE);

				annotation3 = new Annotation(chart.getChart());
				annotation3.getShape().setCustomPosition(true);
				annotation3.setText("Another one\nwith multiple\nlines of text.");
				annotation3.setLeft(130);
				annotation3.setTop(170);
				annotation3.getShape().setColor(Color.YELLOW);

				break;
			case 4:// Rotate
				new Rotate(chart.getChart());
				break;
			default:
				selectDefaultTool(chart, index);
				break;
			}
			break;
		case CANDLE: // Candle series selected
			switch (index) {
			case 0: // ColorBand
				chart.getLegend().setVisible(false);
				// tool1
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setStyle(HatchStyle.BACKWARDDIAGONAL);
				colorBand1.setEnd(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool2
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.BLUE);
				colorBand1.setEnd(200.0);
				colorBand1.setStart(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool3
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.YELLOW);
				colorBand1.getGradient().setDirection(GradientDirection.HORIZONTAL);
				colorBand1.getGradient().setEndColor(Color.GRAY);
				colorBand1.getGradient().setMiddleColor(Color.RED);
				colorBand1.setEnd(300.0);
				colorBand1.setStart(200.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool4
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.LIME);
				colorBand1.setEnd(400.0);
				colorBand1.setStart(300.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool5
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.RED);
				colorBand1.setEnd(700.0);
				colorBand1.setStart(500.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());
				break;

			case 1: // Axis Arrow
					// Init Chart
				chart.getLegend().setVisible(false);

				// Start tool
				axisArrow1 = new AxisArrow(chart.getAxes().getLeft());
				axisArrow1.setPosition(AxisArrowPosition.START);
				axisArrow1.setLength(70);

				axisArrow2 = new AxisArrow(chart.getAxes().getBottom());
				axisArrow2.getBrush().setColor(Color.GREEN_YELLOW);
				axisArrow2.getPen().setColor(Color.WHITE);
				axisArrow2.setPosition(AxisArrowPosition.BOTH);
				axisArrow2.setLength(70);

				axisArrow3 = new AxisArrow(chart.getAxes().getLeft());
				axisArrow3.setPosition(AxisArrowPosition.END);
				axisArrow3.getBrush().setColor(Color.WHITE);
				axisArrow3.getPen().setColor(Color.BLUE);
				axisArrow3.setLength(70);
				break;
			case 2:// Annotation
					// Init Chart
				chart.getHeader().setVisible(true);
				chart.getLegend().setVisible(false);
				// Start tool
				annotation1 = new Annotation(chart.getChart());
				annotation1.getShape().setCustomPosition(true);
				annotation1.setText("Annotation 1");
				annotation1.setLeft(65);
				annotation1.setTop(125);

				annotation2 = new Annotation(chart.getChart());
				annotation2.getShape().setCustomPosition(true);
				annotation2.setText("Annotation 2");
				annotation2.setLeft(130);
				annotation2.setTop(70);

				annotation2.getShape().setColor(Color.RED);
				annotation2.getShape().getFont().setColor(Color.BLUE);

				annotation3 = new Annotation(chart.getChart());
				annotation3.getShape().setCustomPosition(true);
				annotation3.setText("Another one\nwith multiple\nlines of text.");
				annotation3.setLeft(130);
				annotation3.setTop(170);
				annotation3.getShape().setColor(Color.YELLOW);

				break;
			case 3:
				// Init Chart
				chart.getLegend().setVisible(false);
				chart.getHeader().setVisible(true);

				// Start tool

				seriesPointer1 = ((com.steema.teechart.styles.Candle) chart.getSeries(0)).getPointer();
				seriesPointer1.getBrush().setColor(Color.RED);
				seriesPointer1.setVertSize(7);
				seriesPointer1.setHorizSize(7);
				seriesPointer1.setInflateMargins(true);
				seriesPointer1.getPen().setColor(Color.LIME);
				seriesPointer1.setVisible(true);

				dragPoint1 = new DragPoint(((com.steema.teechart.styles.Candle) chart.getSeries(0)));
				dragPoint1.setStyle(DragPointStyle.BOTH);
				break;
			case 4: // GridBand
				chart.getAspect().setView3D(false);
				chart.getSeries(0).fillSampleValues();
				chart.getSeries(0).getMarks().setVisible(false);
				chart.getSeries(0).setColor(Color.GREEN);

				// Create GridBandTool...
				gridBand1 = new com.steema.teechart.tools.GridBand(chart.getChart());
				gridBand1.setAxis(chart.getAxes().getLeft());

				// cosmetic examples:
				// GetTeeBrush(0,Band1.Image.Bitmap);
				gridBand1.getBand1().setColor(Color.RED);

				gridBand1.getBand2().setStyle(HatchStyle.CROSS);
				gridBand1.getBand2().setColor(Color.YELLOW);

				// Change Left axis grid
				chart.getAxes().getLeft().getGrid().setColor(Color.RED);
				chart.getAxes().getLeft().getGrid().setStyle(DashStyle.SOLID);
				break;
			default:
				selectDefaultTool(chart, index);
				break;
			}
			break;
		case SURFACE: // Surface series selected
			switch (index) {
			case 0: // ColorBand
				chart.getLegend().setVisible(false);
				// tool1
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setStyle(HatchStyle.BACKWARDDIAGONAL);
				colorBand1.setEnd(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool2
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.BLUE);
				colorBand1.setEnd(200.0);
				colorBand1.setStart(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool3
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.YELLOW);
				colorBand1.getGradient().setDirection(GradientDirection.HORIZONTAL);
				colorBand1.getGradient().setEndColor(Color.GRAY);
				colorBand1.getGradient().setMiddleColor(Color.RED);
				colorBand1.setEnd(300.0);
				colorBand1.setStart(200.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool4
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.LIME);
				colorBand1.setEnd(400.0);
				colorBand1.setStart(300.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool5
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.RED);
				colorBand1.setEnd(700.0);
				colorBand1.setStart(500.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());
				break;

			case 1: // Axis Arrow
				// Init Chart
				chart.getLegend().setVisible(false);

				// Start tool
				axisArrow1 = new AxisArrow(chart.getAxes().getLeft());
				axisArrow1.setPosition(AxisArrowPosition.START);
				axisArrow1.setLength(70);

				axisArrow2 = new AxisArrow(chart.getAxes().getBottom());
				axisArrow2.getBrush().setColor(Color.GREEN_YELLOW);
				axisArrow2.getPen().setColor(Color.WHITE);
				axisArrow2.setPosition(AxisArrowPosition.BOTH);
				axisArrow2.setLength(70);

				axisArrow3 = new AxisArrow(chart.getAxes().getLeft());
				axisArrow3.setPosition(AxisArrowPosition.END);
				axisArrow3.getBrush().setColor(Color.WHITE);
				axisArrow3.getPen().setColor(Color.BLUE);
				axisArrow3.setLength(70);
				break;
			case 2: // GridBand
				chart.getAspect().setView3D(false);
				chart.getSeries(0).fillSampleValues();
				chart.getSeries(0).getMarks().setVisible(false);
				chart.getSeries(0).setColor(Color.GREEN);

				// Create GridBandTool...
				gridBand1 = new com.steema.teechart.tools.GridBand(chart.getChart());
				gridBand1.setAxis(chart.getAxes().getLeft());

				// cosmetic examples:
				// GetTeeBrush(0,Band1.Image.Bitmap);
				gridBand1.getBand1().setColor(Color.RED);

				gridBand1.getBand2().setStyle(HatchStyle.CROSS);
				gridBand1.getBand2().setColor(Color.YELLOW);

				// Change Left axis grid
				chart.getAxes().getLeft().getGrid().setColor(Color.RED);
				chart.getAxes().getLeft().getGrid().setStyle(DashStyle.SOLID);
				break;

			case 3: // Tanspose

				chart.getHeader().setVisible(true);
				chart.getLegend().setVisible(false);
				chart.getAspect().setChart3DPercent(65);
				chart.getAxes().getDepth().setVisible(false);
				chart.getAspect().setOrthoAngle(60);
				gridTranspose1 = new GridTranspose(chart.getChart());
				gridTranspose1.setSeries((com.steema.teechart.styles.Surface) chart.getSeries(0));
				gridTranspose1.transpose();
				break;
			case 4: // Rotate
				new Rotate(chart.getChart());
				break;
			default:
				selectDefaultTool(chart, index);
				break;
			}
			break;
		case TOWER: // Tower series selected
			switch (index) {
			case 0: // ColorBand
				chart.getLegend().setVisible(false);
				// tool1
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setStyle(HatchStyle.BACKWARDDIAGONAL);
				colorBand1.setEnd(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool2
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.BLUE);
				colorBand1.setEnd(200.0);
				colorBand1.setStart(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool3
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.YELLOW);
				colorBand1.getGradient().setDirection(GradientDirection.HORIZONTAL);
				colorBand1.getGradient().setEndColor(Color.GRAY);
				colorBand1.getGradient().setMiddleColor(Color.RED);
				colorBand1.setEnd(300.0);
				colorBand1.setStart(200.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool4
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.LIME);
				colorBand1.setEnd(400.0);
				colorBand1.setStart(300.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool5
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.RED);
				colorBand1.setEnd(700.0);
				colorBand1.setStart(500.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());
				break;

			case 1: // Axis Arrow
					// Init Chart
				chart.getLegend().setVisible(false);

				// Start tool
				axisArrow1 = new AxisArrow(chart.getAxes().getLeft());
				axisArrow1.setPosition(AxisArrowPosition.START);
				axisArrow1.setLength(70);

				axisArrow2 = new AxisArrow(chart.getAxes().getBottom());
				axisArrow2.getBrush().setColor(Color.GREEN_YELLOW);
				axisArrow2.getPen().setColor(Color.WHITE);
				axisArrow2.setPosition(AxisArrowPosition.BOTH);
				axisArrow2.setLength(70);

				axisArrow3 = new AxisArrow(chart.getAxes().getLeft());
				axisArrow3.setPosition(AxisArrowPosition.END);
				axisArrow3.getBrush().setColor(Color.WHITE);
				axisArrow3.getPen().setColor(Color.BLUE);
				axisArrow3.setLength(70);
				break;
			case 2:// Annotation
					// Init Chart
				chart.getHeader().setVisible(true);
				chart.getLegend().setVisible(false);
				// Start tool
				annotation1 = new Annotation(chart.getChart());
				annotation1.getShape().setCustomPosition(true);
				annotation1.setText("Annotation 1");
				annotation1.setLeft(65);
				annotation1.setTop(125);

				annotation2 = new Annotation(chart.getChart());
				annotation2.getShape().setCustomPosition(true);
				annotation2.setText("Annotation 2");
				annotation2.setLeft(130);
				annotation2.setTop(70);

				annotation2.getShape().setColor(Color.RED);
				annotation2.getShape().getFont().setColor(Color.BLUE);

				annotation3 = new Annotation(chart.getChart());
				annotation3.getShape().setCustomPosition(true);
				annotation3.setText("Another one\nwith multiple\nlines of text.");
				annotation3.setLeft(130);
				annotation3.setTop(170);
				annotation3.getShape().setColor(Color.YELLOW);

				break;
			case 3: // Rotate
				new Rotate(chart.getChart());
				break;
			case 4:
				// GridBand
				chart.getAspect().setView3D(false);
				chart.getSeries(0).fillSampleValues();
				chart.getSeries(0).getMarks().setVisible(false);
				chart.getSeries(0).setColor(Color.GREEN);

				// Create GridBandTool...
				gridBand1 = new com.steema.teechart.tools.GridBand(chart.getChart());
				gridBand1.setAxis(chart.getAxes().getLeft());

				// cosmetic examples:
				// GetTeeBrush(0,Band1.Image.Bitmap);
				gridBand1.getBand1().setColor(Color.RED);

				gridBand1.getBand2().setStyle(HatchStyle.CROSS);
				gridBand1.getBand2().setColor(Color.YELLOW);

				// Change Left axis grid
				chart.getAxes().getLeft().getGrid().setColor(Color.RED);
				chart.getAxes().getLeft().getGrid().setStyle(DashStyle.SOLID);
				break;
			default:
				selectDefaultTool(chart, index);
				break;
			}
			break;
		case CONTOUR: // Contour series selected
			switch (index) {
			case 0: // Axis Arrow

				// Init Chart
				chart.getLegend().setVisible(false);

				// Start tool
				axisArrow1 = new AxisArrow(chart.getAxes().getLeft());
				axisArrow1.setPosition(AxisArrowPosition.START);
				axisArrow1.setLength(70);

				axisArrow2 = new AxisArrow(chart.getAxes().getBottom());
				axisArrow2.getBrush().setColor(Color.GREEN_YELLOW);
				axisArrow2.getPen().setColor(Color.WHITE);
				axisArrow2.setPosition(AxisArrowPosition.BOTH);
				axisArrow2.setLength(70);

				axisArrow3 = new AxisArrow(chart.getAxes().getLeft());
				axisArrow3.setPosition(AxisArrowPosition.END);
				axisArrow3.getBrush().setColor(Color.WHITE);
				axisArrow3.getPen().setColor(Color.BLUE);
				axisArrow3.setLength(70);
				break;
			case 1: // ColorBand
				chart.getLegend().setVisible(false);
				// tool1
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setStyle(HatchStyle.BACKWARDDIAGONAL);
				colorBand1.setEnd(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool2
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.BLUE);
				colorBand1.setEnd(200.0);
				colorBand1.setStart(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool3
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.YELLOW);
				colorBand1.getGradient().setDirection(GradientDirection.HORIZONTAL);
				colorBand1.getGradient().setEndColor(Color.GRAY);
				colorBand1.getGradient().setMiddleColor(Color.RED);
				colorBand1.setEnd(300.0);
				colorBand1.setStart(200.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool4
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.LIME);
				colorBand1.setEnd(400.0);
				colorBand1.setStart(300.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool5
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.RED);
				colorBand1.setEnd(700.0);
				colorBand1.setStart(500.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());
				break;

			case 2: // Annotation click

				// Init Chart
				chart.getHeader().setVisible(true);
				chart.getLegend().setVisible(false);

				// Start tool
				annotationClick1 = new Annotation(chart.getChart());
				annotationClick1.getShape().setCustomPosition(true);
				annotationClick1.setText("Annotation 1");
				annotationClick1.setLeft(65);
				annotationClick1.setTop(125);

				ToolMouseListener annotationClick1Listener = new ToolMouseListener() {
					public void toolClicked(FrameworkMouseEvent e) {
						AlertDialog dialog = new AlertDialog.Builder(context).create();
						dialog.setTitle(Language.getString("Message"));
						dialog.setMessage(annotationClick1.getText());

						dialog.setButton(DialogInterface.BUTTON_POSITIVE, Language.getString("OK"), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								dialog.cancel();
							}
						});
						dialog.show();
					}
				};
				annotationClick1.addToolMouseListener(annotationClick1Listener);

				annotationClick2 = new Annotation(chart.getChart());
				annotationClick2.getShape().setCustomPosition(true);
				annotationClick2.setText("Annotation 2");
				annotationClick2.setLeft(130);
				annotationClick2.setTop(70);

				annotationClick2.getShape().setColor(Color.RED);
				annotationClick2.getShape().getFont().setColor(Color.BLUE);
				ToolMouseListener annotationClick2Listener = new ToolMouseListener() {
					public void toolClicked(FrameworkMouseEvent e) {
						AlertDialog dialog = new AlertDialog.Builder(context).create();
						dialog.setTitle(Language.getString("Message"));
						dialog.setMessage(annotationClick2.getText());

						dialog.setButton(DialogInterface.BUTTON_POSITIVE, Language.getString("OK"), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								dialog.cancel();
							}
						});
						dialog.show();
					}
				};
				annotationClick2.addToolMouseListener(annotationClick2Listener);

				annotationClick3 = new Annotation(chart.getChart());
				annotationClick3.getShape().setCustomPosition(true);
				annotationClick3.setText("Another one\nwith multiple\nlines of text.");
				annotationClick3.setLeft(130);
				annotationClick3.setTop(170);
				annotationClick3.getShape().setColor(Color.YELLOW);
				ToolMouseListener annotationClick3Listener = new ToolMouseListener() {
					public void toolClicked(FrameworkMouseEvent e) {
						AlertDialog dialog = new AlertDialog.Builder(context).create();
						dialog.setTitle(Language.getString("Message"));
						dialog.setMessage(annotationClick3.getText());

						dialog.setButton(DialogInterface.BUTTON_POSITIVE, Language.getString("OK"), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								dialog.cancel();
							}
						});
						dialog.show();

					}
				};

				annotationClick3.addToolMouseListener(annotationClick3Listener);
				break;
			case 3: // Drag Points
				chart.getLegend().setVisible(false);
				chart.getHeader().setVisible(true);

				// Start tool

				seriesPointer1 = ((com.steema.teechart.styles.Contour) chart.getSeries(0)).getPointer();
				seriesPointer1.getBrush().setColor(Color.RED);
				seriesPointer1.setVertSize(7);
				seriesPointer1.setHorizSize(7);
				seriesPointer1.setInflateMargins(true);
				seriesPointer1.getPen().setColor(Color.LIME);
				seriesPointer1.setVisible(true);

				dragPoint1 = new DragPoint(((com.steema.teechart.styles.Contour) chart.getSeries(0)));
				dragPoint1.setStyle(DragPointStyle.BOTH);
				break;

			case 4:// Draw Line
				chart.getLegend().setVisible(false);
				// Start tool

				drawLine = new com.steema.teechart.tools.DrawLine(chart.getChart());
				drawLine.getPen().setColor(Color.RED);

				// create a new DrawLine
				DrawLineItem tmpLine = new DrawLineItem(drawLine);

				// set the "X" line positions (start and end position)
				tmpLine.getStartPos().x = 5.0;
				tmpLine.getEndPos().x = 15.0;

				// set the "Y" line positions (start and end position)
				double tmp = (chart.getSeries(0).getYValues().getMaximum() - chart.getSeries(0).getYValues().getMinimum()) / 5;
				tmpLine.getStartPos().y = chart.getSeries(0).getYValues().getMaximum() - tmp;
				tmpLine.getEndPos().y = chart.getSeries(0).getYValues().getMinimum() + tmp;
				break;
			default:
				selectDefaultTool(chart, index);
				break;
			}

			break;
		case COLORGRID: // ColorGrid series selected
			switch (index) {
			case 0: // ColorBand
				chart.getLegend().setVisible(false);
				// tool1
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setStyle(HatchStyle.BACKWARDDIAGONAL);
				colorBand1.setEnd(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool2
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.BLUE);
				colorBand1.setEnd(200.0);
				colorBand1.setStart(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool3
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.YELLOW);
				colorBand1.getGradient().setDirection(GradientDirection.HORIZONTAL);
				colorBand1.getGradient().setEndColor(Color.GRAY);
				colorBand1.getGradient().setMiddleColor(Color.RED);
				colorBand1.setEnd(300.0);
				colorBand1.setStart(200.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool4
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.LIME);
				colorBand1.setEnd(400.0);
				colorBand1.setStart(300.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool5
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.RED);
				colorBand1.setEnd(700.0);
				colorBand1.setStart(500.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());
				break;

			case 1: // Axis Arrow
					// Init Chart
				chart.getLegend().setVisible(false);

				// Start tool
				axisArrow1 = new AxisArrow(chart.getAxes().getLeft());
				axisArrow1.setPosition(AxisArrowPosition.START);
				axisArrow1.setLength(70);

				axisArrow2 = new AxisArrow(chart.getAxes().getBottom());
				axisArrow2.getBrush().setColor(Color.GREEN_YELLOW);
				axisArrow2.getPen().setColor(Color.WHITE);
				axisArrow2.setPosition(AxisArrowPosition.BOTH);
				axisArrow2.setLength(70);

				axisArrow3 = new AxisArrow(chart.getAxes().getLeft());
				axisArrow3.setPosition(AxisArrowPosition.END);
				axisArrow3.getBrush().setColor(Color.WHITE);
				axisArrow3.getPen().setColor(Color.BLUE);
				axisArrow3.setLength(70);
				break;
			case 2: // Rotate
				new Rotate(chart.getChart());
				break;
			case 3: // Draw Line
				chart.getLegend().setVisible(false);
				// Start tool

				drawLine = new com.steema.teechart.tools.DrawLine(chart.getChart());
				drawLine.getPen().setColor(Color.RED);

				// create a new DrawLine
				DrawLineItem tmpLine = new DrawLineItem(drawLine);

				// set the "X" line positions (start and end position)
				tmpLine.getStartPos().x = 5.0;
				tmpLine.getEndPos().x = 15.0;

				// set the "Y" line positions (start and end position)
				double tmp = (chart.getSeries(0).getYValues().getMaximum() - chart.getSeries(0).getYValues().getMinimum()) / 5;
				tmpLine.getStartPos().y = chart.getSeries(0).getYValues().getMaximum() - tmp;
				tmpLine.getEndPos().y = chart.getSeries(0).getYValues().getMinimum() + tmp;
				break;
			case 4: // Marks tip
				chart.getHeader().setVisible(false);
				chart.getLegend().setVisible(false);
				// Start tool
				marksTip = new com.steema.teechart.tools.MarksTip(chart.getChart());

				for (int i = 0; i < chart.getSeriesCount(); i++) {
					final Series s = chart.getSeries(i);
					s.addSeriesMouseListener(new SeriesMouseAdapter() {
						public void seriesClicked(SeriesMouseEvent e) {

							marksTip.setMouseDelay(0);
							marksTip.setSeries(s);
							Toast t;
							t = Toast.makeText(context, marksTip.getSeries().getValueMarkText(e.getValueIndex()), Toast.LENGTH_SHORT);
							t.setGravity(Gravity.TOP | Gravity.LEFT, e.getPoint().x - 50, e.getPoint().y + ((View) chart.getParent()).getTop());
							t.show();
						};
					});
				}
				break;
			default:
				selectDefaultTool(chart, index);
				break;
			}
			break;
		case POLAR: // Polar series selected
			switch (index) {
			case 0: // Annotation
				// Init Chart
				chart.getHeader().setVisible(true);
				chart.getLegend().setVisible(false);
				// Start tool
				annotation1 = new Annotation(chart.getChart());
				annotation1.getShape().setCustomPosition(true);
				annotation1.setText("Annotation 1");
				annotation1.setLeft(65);
				annotation1.setTop(125);

				annotation2 = new Annotation(chart.getChart());
				annotation2.getShape().setCustomPosition(true);
				annotation2.setText("Annotation 2");
				annotation2.setLeft(130);
				annotation2.setTop(70);

				annotation2.getShape().setColor(Color.RED);
				annotation2.getShape().getFont().setColor(Color.BLUE);

				annotation3 = new Annotation(chart.getChart());
				annotation3.getShape().setCustomPosition(true);
				annotation3.setText("Another one\nwith multiple\nlines of text.");
				annotation3.setLeft(130);
				annotation3.setTop(170);
				annotation3.getShape().setColor(Color.YELLOW);

				break;
			case 1: // Rotate
				new Rotate(chart.getChart());
				break;
			case 2: // Drag points
				chart.getLegend().setVisible(false);
				chart.getHeader().setVisible(true);

				// Start tool

				seriesPointer1 = ((com.steema.teechart.styles.Polar) chart.getSeries(0)).getPointer();
				seriesPointer1.getBrush().setColor(Color.RED);
				seriesPointer1.setVertSize(7);
				seriesPointer1.setHorizSize(7);
				seriesPointer1.setInflateMargins(true);
				seriesPointer1.getPen().setColor(Color.LIME);
				seriesPointer1.setVisible(true);

				dragPoint1 = new DragPoint(((com.steema.teechart.styles.Polar) chart.getSeries(0)));
				dragPoint1.setStyle(DragPointStyle.BOTH);
				break;
			case 3: // Marks tip
				chart.getHeader().setVisible(false);
				chart.getLegend().setVisible(false);
				// Start tool
				marksTip = new com.steema.teechart.tools.MarksTip(chart.getChart());

				for (int i = 0; i < chart.getSeriesCount(); i++) {
					final Series s = chart.getSeries(i);
					s.addSeriesMouseListener(new SeriesMouseAdapter() {
						public void seriesClicked(SeriesMouseEvent e) {

							marksTip.setMouseDelay(0);
							marksTip.setSeries(s);
							Toast t;
							t = Toast.makeText(context, marksTip.getSeries().getValueMarkText(e.getValueIndex()), Toast.LENGTH_SHORT);
							t.setGravity(Gravity.TOP | Gravity.LEFT, e.getPoint().x - 50, e.getPoint().y + ((View) chart.getParent()).getTop());
							t.show();
						};
					});
				}
				break;
			case 4: // Axis Label
				// Init Chart
				chart.getLegend().setVisible(false);
				ChartFont tmpFont = chart.getAxes().getLeft().getLabels().getFont();
				tmpFont.setColor(Color.NAVY);
				tmpFont.setSize(13);
				tmpFont.setBold(true);
				tmpFont.getShadow().setColor(Color.AQUA);
				tmpFont.getShadow().setHorizSize(1);
				tmpFont.getShadow().setVertSize(-1);

				chart.getSeries(0).getMarks().setVisible(true);
				chart.getSeries(0).getMarks().setColor(Color.RED);
				break;
			default:
				selectDefaultTool(chart, index);
				break;
			}
			break;
		case SMITH: // Smith series selected
			switch (index) {
			case 0: // Annotation
				// Init Chart
				chart.getHeader().setVisible(true);
				chart.getLegend().setVisible(false);
				// Start tool
				annotation1 = new Annotation(chart.getChart());
				annotation1.getShape().setCustomPosition(true);
				annotation1.setText("Annotation 1");
				annotation1.setLeft(65);
				annotation1.setTop(125);

				annotation2 = new Annotation(chart.getChart());
				annotation2.getShape().setCustomPosition(true);
				annotation2.setText("Annotation 2");
				annotation2.setLeft(130);
				annotation2.setTop(70);

				annotation2.getShape().setColor(Color.RED);
				annotation2.getShape().getFont().setColor(Color.BLUE);

				annotation3 = new Annotation(chart.getChart());
				annotation3.getShape().setCustomPosition(true);
				annotation3.setText("Another one\nwith multiple\nlines of text.");
				annotation3.setLeft(130);
				annotation3.setTop(170);
				annotation3.getShape().setColor(Color.YELLOW);

				break;

			case 1: // Rotate
				new Rotate(chart.getChart());
				break;
			case 2: // Drag points
				chart.getLegend().setVisible(false);
				chart.getHeader().setVisible(true);

				// Start tool

				seriesPointer1 = ((com.steema.teechart.styles.Smith) chart.getSeries(0)).getPointer();
				seriesPointer1.getBrush().setColor(Color.RED);
				seriesPointer1.setVertSize(7);
				seriesPointer1.setHorizSize(7);
				seriesPointer1.setInflateMargins(true);
				seriesPointer1.getPen().setColor(Color.LIME);
				seriesPointer1.setVisible(true);

				dragPoint1 = new DragPoint(((com.steema.teechart.styles.Smith) chart.getSeries(0)));
				dragPoint1.setStyle(DragPointStyle.BOTH);
				break;
			case 3: // Marks tip
				chart.getHeader().setVisible(false);
				chart.getLegend().setVisible(false);
				// Start tool
				marksTip = new com.steema.teechart.tools.MarksTip(chart.getChart());

				for (int i = 0; i < chart.getSeriesCount(); i++) {
					final Series s = chart.getSeries(i);
					s.addSeriesMouseListener(new SeriesMouseAdapter() {
						public void seriesClicked(SeriesMouseEvent e) {

							marksTip.setMouseDelay(0);
							marksTip.setSeries(s);
							Toast t;
							t = Toast.makeText(context, marksTip.getSeries().getValueMarkText(e.getValueIndex()), Toast.LENGTH_SHORT);
							t.setGravity(Gravity.TOP | Gravity.LEFT, e.getPoint().x - 50, e.getPoint().y + ((View) chart.getParent()).getTop());
							t.show();
						};
					});
				}
				break;
			case 4: // Axis Label
				// Init Chart
				chart.getLegend().setVisible(false);
				ChartFont tmpFont = chart.getAxes().getLeft().getLabels().getFont();
				tmpFont.setColor(Color.NAVY);
				tmpFont.setSize(13);
				tmpFont.setBold(true);
				tmpFont.getShadow().setColor(Color.AQUA);
				tmpFont.getShadow().setHorizSize(1);
				tmpFont.getShadow().setVertSize(-1);

				chart.getSeries(0).getMarks().setVisible(true);
				chart.getSeries(0).getMarks().setColor(Color.RED);
				break;
			default:
				selectDefaultTool(chart, index);
				break;
			}
			break;
		case RADAR: // Radar series selected
			switch (index) {
			case 0:// Annotation
					// Init Chart
				chart.getHeader().setVisible(true);
				chart.getLegend().setVisible(false);
				// Start tool
				annotation1 = new Annotation(chart.getChart());
				annotation1.getShape().setCustomPosition(true);
				annotation1.setText("Annotation 1");
				annotation1.setLeft(65);
				annotation1.setTop(125);

				annotation2 = new Annotation(chart.getChart());
				annotation2.getShape().setCustomPosition(true);
				annotation2.setText("Annotation 2");
				annotation2.setLeft(130);
				annotation2.setTop(70);

				annotation2.getShape().setColor(Color.RED);
				annotation2.getShape().getFont().setColor(Color.BLUE);

				annotation3 = new Annotation(chart.getChart());
				annotation3.getShape().setCustomPosition(true);
				annotation3.setText("Another one\nwith multiple\nlines of text.");
				annotation3.setLeft(130);
				annotation3.setTop(170);
				annotation3.getShape().setColor(Color.YELLOW);

				break;

			case 1:// Rotate
				new Rotate(chart.getChart());
				break;
			case 2: // Drag points
				chart.getLegend().setVisible(false);
				chart.getHeader().setVisible(true);

				// Start tool

				seriesPointer1 = ((com.steema.teechart.styles.Radar) chart.getSeries(0)).getPointer();
				seriesPointer1.getBrush().setColor(Color.RED);
				seriesPointer1.setVertSize(7);
				seriesPointer1.setHorizSize(7);
				seriesPointer1.setInflateMargins(true);
				seriesPointer1.getPen().setColor(Color.LIME);
				seriesPointer1.setVisible(true);

				dragPoint1 = new DragPoint(((com.steema.teechart.styles.Radar) chart.getSeries(0)));
				dragPoint1.setStyle(DragPointStyle.BOTH);
				break;
			case 3: // Marks tip
				chart.getHeader().setVisible(false);
				chart.getLegend().setVisible(false);
				// Start tool
				marksTip = new com.steema.teechart.tools.MarksTip(chart.getChart());

				for (int i = 0; i < chart.getSeriesCount(); i++) {
					final Series s = chart.getSeries(i);
					s.addSeriesMouseListener(new SeriesMouseAdapter() {
						public void seriesClicked(SeriesMouseEvent e) {

							marksTip.setMouseDelay(0);
							marksTip.setSeries(s);
							Toast t;
							t = Toast.makeText(context, marksTip.getSeries().getValueMarkText(e.getValueIndex()), Toast.LENGTH_SHORT);
							t.setGravity(Gravity.TOP | Gravity.LEFT, e.getPoint().x - 50, e.getPoint().y + ((View) chart.getParent()).getTop());
							t.show();
						};
					});
				}
				break;
			case 4:// Axis Label
					// Init Chart
				chart.getLegend().setVisible(false);
				ChartFont tmpFont = chart.getAxes().getLeft().getLabels().getFont();
				tmpFont.setColor(Color.NAVY);
				tmpFont.setSize(13);
				tmpFont.setBold(true);
				tmpFont.getShadow().setColor(Color.AQUA);
				tmpFont.getShadow().setHorizSize(1);
				tmpFont.getShadow().setVertSize(-1);

				chart.getSeries(0).getMarks().setVisible(true);
				chart.getSeries(0).getMarks().setColor(Color.RED);
				break;
			default:
				selectDefaultTool(chart, index);
				break;
			}
			break;
		case FASTLINE: // Fastline series selected
			switch (index) {
			case 0: // Axis Label
				// Init Chart
				chart.getLegend().setVisible(false);
				ChartFont tmpFont = chart.getAxes().getLeft().getLabels().getFont();
				tmpFont.setColor(Color.NAVY);
				tmpFont.setSize(13);
				tmpFont.setBold(true);
				tmpFont.getShadow().setColor(Color.AQUA);
				tmpFont.getShadow().setHorizSize(1);
				tmpFont.getShadow().setVertSize(-1);

				chart.getSeries(0).getMarks().setVisible(true);
				chart.getSeries(0).getMarks().setColor(Color.RED);
				break;
			case 1: // series band
					// init chart
				chart.getAspect().setView3D(false);
				chart.getHeader().setVisible(true);
				chart.getHeader().setText("SeriesBand Tool Example");
				chart.getLegend().setVisible(false);
				for (int i = 0; i < chart.getSeries(0).getCount(); i++) {
					chart.getSeries(1).add(chart.getSeries(0).getXValues().getValue(i), chart.getSeries(0).getYValues().getValue(i) / 2.0);
				}

				seriesBand1 = new SeriesBand(chart.getChart());
				seriesBand1.setSeries(chart.getSeries(0));
				seriesBand1.setSeries2(chart.getSeries(1));

				seriesBand1.getGradient().setVisible(true);
				seriesBand1.getGradient().setStartColor(Color.SILVER);

				break;
			case 2:// Extra Legend

				chart.getLegend().setVisible(true);
				chart.getLegend().setLegendStyle(LegendStyle.VALUES);

				// Start Tool
				for (int i = 0; i < chart.getSeriesCount(); i++) {
					Series seriesLengend = chart.getSeries(i);
					seriesLengend.fillSampleValues(5);
					seriesLengend.getMarks().setVisible(false);
				}
				extraLegend1 = new ExtraLegend(chart.getChart());
				extraLegend1.setSeries(chart.getSeries(1));

				// Cosmetic
				legend1 = extraLegend1.getLegend();
				legend1.setLegendStyle(LegendStyle.VALUES);
				legend1.getShadow().setTransparency(70);
				legend1.setCustomPosition(true);
				legend1.setLeft(50);
				legend1.setTop(50);
				break;
			case 3:// Draw Line
				chart.getLegend().setVisible(false);
				// Start tool

				drawLine = new com.steema.teechart.tools.DrawLine(chart.getChart());
				drawLine.getPen().setColor(Color.RED);

				// create a new DrawLine
				DrawLineItem tmpLine = new DrawLineItem(drawLine);

				// set the "X" line positions (start and end position)
				tmpLine.getStartPos().x = 5.0;
				tmpLine.getEndPos().x = 15.0;

				// set the "Y" line positions (start and end position)
				double tmp = (chart.getSeries(0).getYValues().getMaximum() - chart.getSeries(0).getYValues().getMinimum()) / 5;
				tmpLine.getStartPos().y = chart.getSeries(0).getYValues().getMaximum() - tmp;
				tmpLine.getEndPos().y = chart.getSeries(0).getYValues().getMinimum() + tmp;
				break;
			case 4: // Drag Point
				chart.getLegend().setVisible(false);
				chart.getHeader().setVisible(true);
				seriesPointer1 = ((com.steema.teechart.styles.Line) chart.getSeries(0)).getPointer();
				seriesPointer1.getBrush().setColor(Color.RED);
				seriesPointer1.setVertSize(7);
				seriesPointer1.setHorizSize(7);
				seriesPointer1.setInflateMargins(true);
				seriesPointer1.getPen().setColor(Color.LIME);
				seriesPointer1.setVisible(true);

				dragPoint1 = new DragPoint(chart.getSeries(0));
				dragPoint1.setStyle(DragPointStyle.BOTH);

				seriesPointer2 = ((com.steema.teechart.styles.Line) chart.getSeries(1)).getPointer();
				seriesPointer2.getBrush().setColor(Color.RED);
				seriesPointer2.setVertSize(7);
				seriesPointer2.setHorizSize(7);
				seriesPointer2.setInflateMargins(true);
				seriesPointer2.getPen().setColor(Color.MAROON);
				seriesPointer2.setVisible(true);

				dragPoint2 = new DragPoint(chart.getSeries(1));
				dragPoint2.setStyle(DragPointStyle.BOTH);
				break;
			default:
				selectDefaultTool(chart, index);
				break;
			}
			break;
		case ISOSURFACE: // ISOSURFACE series selected
			switch (index) {
			case 0: // ColorBand
				chart.getLegend().setVisible(false);
				// tool1
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setStyle(HatchStyle.BACKWARDDIAGONAL);
				colorBand1.setEnd(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool2
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.BLUE);
				colorBand1.setEnd(200.0);
				colorBand1.setStart(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool3
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.YELLOW);
				colorBand1.getGradient().setDirection(GradientDirection.HORIZONTAL);
				colorBand1.getGradient().setEndColor(Color.GRAY);
				colorBand1.getGradient().setMiddleColor(Color.RED);
				colorBand1.setEnd(300.0);
				colorBand1.setStart(200.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool4
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.LIME);
				colorBand1.setEnd(400.0);
				colorBand1.setStart(300.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool5
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.RED);
				colorBand1.setEnd(700.0);
				colorBand1.setStart(500.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());
				break;

			case 1: // Axis Arrow
				// Init Chart
				chart.getLegend().setVisible(false);

				// Start tool
				axisArrow1 = new AxisArrow(chart.getAxes().getLeft());
				axisArrow1.setPosition(AxisArrowPosition.START);
				axisArrow1.setLength(70);

				axisArrow2 = new AxisArrow(chart.getAxes().getBottom());
				axisArrow2.getBrush().setColor(Color.GREEN_YELLOW);
				axisArrow2.getPen().setColor(Color.WHITE);
				axisArrow2.setPosition(AxisArrowPosition.BOTH);
				axisArrow2.setLength(70);

				axisArrow3 = new AxisArrow(chart.getAxes().getLeft());
				axisArrow3.setPosition(AxisArrowPosition.END);
				axisArrow3.getBrush().setColor(Color.WHITE);
				axisArrow3.getPen().setColor(Color.BLUE);
				axisArrow3.setLength(70);
				break;
			case 2: // GridBand
				chart.getAspect().setView3D(false);
				chart.getSeries(0).fillSampleValues();
				chart.getSeries(0).getMarks().setVisible(false);
				chart.getSeries(0).setColor(Color.GREEN);

				// Create GridBandTool...
				gridBand1 = new com.steema.teechart.tools.GridBand(chart.getChart());
				gridBand1.setAxis(chart.getAxes().getLeft());

				// cosmetic examples:
				// GetTeeBrush(0,Band1.Image.Bitmap);
				gridBand1.getBand1().setColor(Color.RED);

				gridBand1.getBand2().setStyle(HatchStyle.CROSS);
				gridBand1.getBand2().setColor(Color.YELLOW);

				// Change Left axis grid
				chart.getAxes().getLeft().getGrid().setColor(Color.RED);
				chart.getAxes().getLeft().getGrid().setStyle(DashStyle.SOLID);
				break;

			case 3: // Tanspose

				chart.getHeader().setVisible(true);
				chart.getLegend().setVisible(false);
				chart.getAspect().setChart3DPercent(65);
				chart.getAxes().getDepth().setVisible(false);
				chart.getAspect().setOrthoAngle(60);
				gridTranspose1 = new GridTranspose(chart.getChart());
				gridTranspose1.setSeries((com.steema.teechart.styles.IsoSurface) chart.getSeries(0));
				gridTranspose1.transpose();
				break;
			case 4: // Rotate
				new Rotate(chart.getChart());
				break;
			default:
				selectDefaultTool(chart, index);
				break;
			}
			break;
		case BUBBLE: // Bubble series selected
			switch (index) {
			case 0: // Axis Label
				// Init Chart
				chart.getLegend().setVisible(false);
				ChartFont tmpFont = chart.getAxes().getLeft().getLabels().getFont();
				tmpFont.setColor(Color.NAVY);
				tmpFont.setSize(13);
				tmpFont.setBold(true);
				tmpFont.getShadow().setColor(Color.AQUA);
				tmpFont.getShadow().setHorizSize(1);
				tmpFont.getShadow().setVertSize(-1);

				chart.getSeries(0).getMarks().setVisible(true);
				chart.getSeries(0).getMarks().setColor(Color.RED);
				break;

			case 1: // Axis Arrow
					// Init Chart
				chart.getLegend().setVisible(false);

				// Start tool
				axisArrow1 = new AxisArrow(chart.getAxes().getLeft());
				axisArrow1.setPosition(AxisArrowPosition.START);
				axisArrow1.setLength(70);

				axisArrow2 = new AxisArrow(chart.getAxes().getBottom());
				axisArrow2.getBrush().setColor(Color.GREEN_YELLOW);
				axisArrow2.getPen().setColor(Color.WHITE);
				axisArrow2.setPosition(AxisArrowPosition.BOTH);
				axisArrow2.setLength(70);

				axisArrow3 = new AxisArrow(chart.getAxes().getLeft());
				axisArrow3.setPosition(AxisArrowPosition.END);
				axisArrow3.getBrush().setColor(Color.WHITE);
				axisArrow3.getPen().setColor(Color.BLUE);
				axisArrow3.setLength(70);
				break;
			case 2: // Annotation Click;

				// Init Chart
				chart.getHeader().setVisible(true);
				chart.getLegend().setVisible(false);

				// Start tool
				annotationClick1 = new Annotation(chart.getChart());
				annotationClick1.getShape().setCustomPosition(true);
				annotationClick1.setText("Annotation 1");
				annotationClick1.setLeft(65);
				annotationClick1.setTop(125);

				ToolMouseListener annotationClick1Listener = new ToolMouseListener() {
					public void toolClicked(FrameworkMouseEvent e) {
						AlertDialog dialog = new AlertDialog.Builder(context).create();
						dialog.setTitle(Language.getString("Message"));
						dialog.setMessage(annotationClick1.getText());

						dialog.setButton(DialogInterface.BUTTON_POSITIVE, Language.getString("OK"), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								dialog.cancel();
							}
						});
						dialog.show();
					}
				};
				annotationClick1.addToolMouseListener(annotationClick1Listener);

				annotationClick2 = new Annotation(chart.getChart());
				annotationClick2.getShape().setCustomPosition(true);
				annotationClick2.setText("Annotation 2");
				annotationClick2.setLeft(130);
				annotationClick2.setTop(70);

				annotationClick2.getShape().setColor(Color.RED);
				annotationClick2.getShape().getFont().setColor(Color.BLUE);
				ToolMouseListener annotationClick2Listener = new ToolMouseListener() {
					public void toolClicked(FrameworkMouseEvent e) {
						AlertDialog dialog = new AlertDialog.Builder(context).create();
						dialog.setTitle(Language.getString("Message"));
						dialog.setMessage(annotationClick2.getText());

						dialog.setButton(DialogInterface.BUTTON_POSITIVE, Language.getString("OK"), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								dialog.cancel();
							}
						});
						dialog.show();
					}
				};
				annotationClick2.addToolMouseListener(annotationClick2Listener);

				annotationClick3 = new Annotation(chart.getChart());
				annotationClick3.getShape().setCustomPosition(true);
				annotationClick3.setText("Another one\nwith multiple\nlines of text.");
				annotationClick3.setLeft(130);
				annotationClick3.setTop(170);
				annotationClick3.getShape().setColor(Color.YELLOW);
				ToolMouseListener annotationClick3Listener = new ToolMouseListener() {
					public void toolClicked(FrameworkMouseEvent e) {
						AlertDialog dialog = new AlertDialog.Builder(context).create();
						dialog.setTitle(Language.getString("Message"));
						dialog.setMessage(annotationClick3.getText());

						dialog.setButton(DialogInterface.BUTTON_POSITIVE, Language.getString("OK"), new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int whichButton) {
								dialog.cancel();
							}
						});
						dialog.show();

					}
				};

				annotationClick3.addToolMouseListener(annotationClick3Listener);
				break;
			case 3:// Draw Line
				chart.getLegend().setVisible(false);
				
				// Start tool
				drawLine = new com.steema.teechart.tools.DrawLine(chart.getChart());
				drawLine.getPen().setColor(Color.RED);

				// create a new DrawLine
				DrawLineItem tmpLine = new DrawLineItem(drawLine);

				// set the "X" line positions (start and end position)
				tmpLine.getStartPos().x = 5.0;
				tmpLine.getEndPos().x = 15.0;

				// set the "Y" line positions (start and end position)
				double tmp = (chart.getSeries(0).getYValues().getMaximum() - chart.getSeries(0).getYValues().getMinimum()) / 5;
				tmpLine.getStartPos().y = chart.getSeries(0).getYValues().getMaximum() - tmp;
				tmpLine.getEndPos().y = chart.getSeries(0).getYValues().getMinimum() + tmp;
				break;
			case 4: // Drag points
				chart.getLegend().setVisible(false);
				chart.getHeader().setVisible(true);

				// Start tool
				seriesPointer1 = ((com.steema.teechart.styles.Bubble) chart.getSeries(0)).getPointer();
				seriesPointer1.getBrush().setColor(Color.RED);
				seriesPointer1.setVertSize(7);
				seriesPointer1.setHorizSize(7);
				seriesPointer1.setInflateMargins(true);
				seriesPointer1.getPen().setColor(Color.LIME);
				seriesPointer1.setVisible(true);

				dragPoint1 = new DragPoint(((com.steema.teechart.styles.Bubble) chart.getSeries(0)));
				dragPoint1.setStyle(DragPointStyle.BOTH);
				break;
			default:
				selectDefaultTool(chart, index);
				break;
			}
			break;

		case MAP: // Map series selected
			switch (index) {
			case 0: // GridBand
				chart.getAspect().setView3D(false);
				chart.getSeries(0).fillSampleValues();
				chart.getSeries(0).getMarks().setVisible(false);
				chart.getSeries(0).setColor(Color.GREEN);

				// Create GridBandTool...
				gridBand1 = new com.steema.teechart.tools.GridBand(chart.getChart());
				gridBand1.setAxis(chart.getAxes().getLeft());

				// cosmetic examples:
				// GetTeeBrush(0,Band1.Image.Bitmap);
				gridBand1.getBand1().setColor(Color.RED);

				gridBand1.getBand2().setStyle(HatchStyle.CROSS);
				gridBand1.getBand2().setColor(Color.YELLOW);

				// Change Left axis grid
				chart.getAxes().getLeft().getGrid().setColor(Color.RED);
				chart.getAxes().getLeft().getGrid().setStyle(DashStyle.SOLID);
				break;
			case 1: // Axis Arrow
				// Init Chart
				chart.getLegend().setVisible(false);

				// Start tool
				axisArrow1 = new AxisArrow(chart.getAxes().getLeft());
				axisArrow1.setPosition(AxisArrowPosition.START);
				axisArrow1.setLength(70);

				axisArrow2 = new AxisArrow(chart.getAxes().getBottom());
				axisArrow2.getBrush().setColor(Color.GREEN_YELLOW);
				axisArrow2.getPen().setColor(Color.WHITE);
				axisArrow2.setPosition(AxisArrowPosition.BOTH);
				axisArrow2.setLength(70);

				axisArrow3 = new AxisArrow(chart.getAxes().getLeft());
				axisArrow3.setPosition(AxisArrowPosition.END);
				axisArrow3.getBrush().setColor(Color.WHITE);
				axisArrow3.getPen().setColor(Color.BLUE);
				axisArrow3.setLength(70);
				break;
			case 2: // ColorBand
				chart.getLegend().setVisible(false);
				// tool1
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setStyle(HatchStyle.BACKWARDDIAGONAL);
				colorBand1.setEnd(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool2
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.BLUE);
				colorBand1.setEnd(200.0);
				colorBand1.setStart(100.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool3
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.YELLOW);
				colorBand1.getGradient().setDirection(GradientDirection.HORIZONTAL);
				colorBand1.getGradient().setEndColor(Color.GRAY);
				colorBand1.getGradient().setMiddleColor(Color.RED);
				colorBand1.setEnd(300.0);
				colorBand1.setStart(200.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool4
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.LIME);
				colorBand1.setEnd(400.0);
				colorBand1.setStart(300.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());

				// tool5
				colorBand1 = new com.steema.teechart.tools.ColorBand(chart.getChart());
				colorBand1.getBrush().setColor(Color.RED);
				colorBand1.setEnd(700.0);
				colorBand1.setStart(500.0);
				colorBand1.getPen().setVisible(false);
				colorBand1.setAxis(chart.getAxes().getLeft());
				break;

			case 3: // Annotation
				// Init Chart
				chart.getHeader().setVisible(true);
				chart.getLegend().setVisible(false);
				// Start tool
				annotation1 = new Annotation(chart.getChart());
				annotation1.getShape().setCustomPosition(true);
				annotation1.setText("Annotation 1");
				annotation1.setLeft(65);
				annotation1.setTop(125);

				annotation2 = new Annotation(chart.getChart());
				annotation2.getShape().setCustomPosition(true);
				annotation2.setText("Annotation 2");
				annotation2.setLeft(130);
				annotation2.setTop(70);

				annotation2.getShape().setColor(Color.RED);
				annotation2.getShape().getFont().setColor(Color.BLUE);

				annotation3 = new Annotation(chart.getChart());
				annotation3.getShape().setCustomPosition(true);
				annotation3.setText("Another one\nwith multiple\nlines of text.");
				annotation3.setLeft(130);
				annotation3.setTop(170);
				annotation3.getShape().setColor(Color.YELLOW);

				break;

			case 4:// Axis Label

				// Init Chart
				chart.getLegend().setVisible(false);
				ChartFont tmpFont = chart.getAxes().getLeft().getLabels().getFont();
				tmpFont.setColor(Color.NAVY);
				tmpFont.setSize(13);
				tmpFont.setBold(true);
				tmpFont.getShadow().setColor(Color.AQUA);
				tmpFont.getShadow().setHorizSize(1);
				tmpFont.getShadow().setVertSize(-1);

				chart.getSeries(0).getMarks().setVisible(true);
				chart.getSeries(0).getMarks().setColor(Color.RED);
				break;
			default:
				selectDefaultTool(chart, index);
				break;
			}
			break;
		}

	}

	private static void selectDefaultTool(Chart chart, int index) {
		switch (index) {
		case 5: // Pan
			disableTools(tooltoDisable);
			chart.getAspect().setView3D(true);
			panChart = new PanChart(chart);
			tooltoDisable = 5;
			break;
		case 6: // Pan Data
			disableTools(tooltoDisable);
			panData = new PanData(chart);
			tooltoDisable = 6;
			break;
		case 7: // Zoom
			disableTools(tooltoDisable);
			zoomChart = new ZoomChart(chart);
			tooltoDisable = 7;
			break;
		case 8: // Zoom Data
			disableTools(tooltoDisable);
			zoomData = new ZoomData(chart);
			tooltoDisable = 8;
			break;
		case 9: // Zoom Text
			disableTools(tooltoDisable);
			zoomText = new ZoomText(chart);
			tooltoDisable = 9;
			break;
		}

	}

	public static String getToolDescription(int serieSelected, int index) {

		switch (serieSelected) {
		case PIE: // Pie series selected
			switch (index) {
			case 0:
				return "Annotation Click";
			case 1:
				return "Marks Tip";
			case 2:
				return "Axis Label";
			case 3:
				return "Rotate";
			case 4:
				return "";
			case 5:
				return "Pan";
			case 6:
				return "Pan Data";
			case 7:
				return "Zoom";
			case 8:
				return "Zoom Data";
			case 9:
				return "Zoom Text";
			default:
				return "";

			}

		case LINE: // Line series selected
			switch (index) {
			case 0:
				return "Drag Point";
			case 1:
				return "Series Band";
			case 2:
				return "Rotate";
			case 3:
				return "Marks Tip";
			case 4:
				return "Extra Legend";
			case 5:
				return "Pan";
			case 6:
				return "Pan Data";
			case 7:
				return "Zoom";
			case 8:
				return "Zoom Data";
			case 9:
				return "Zoom Text";
			case 10:
				return "Scroll Pager";
			default:
				return "";
			}

		case BAR: // Bar series selected
			switch (index) {
			case 0:
				return "Draw Line";
			case 1:
				return "Extra Legend";
			case 2:
				return "Color Band";
			case 3:
				return "Annotation";
			case 4:
				return "Rotate";
			case 5:
				return "Pan";
			case 6:
				return "Pan Data";
			case 7:
				return "Zoom";
			case 8:
				return "Zoom Data";
			case 9:
				return "Zoom Text";
			default:
				return "";
			}

		case AREA: // Area series selected
			switch (index) {
			case 0:
				return "Annotation";
			case 1:
				return "Color Band";
			case 2:
				return "Rotate";
			case 3:
				return "Series Region";
			case 4:
				return "Axis Arrow";
			case 5:
				return "Pan";
			case 6:
				return "Pan Data";
			case 7:
				return "Zoom";
			case 8:
				return "Zoom Data";
			case 9:
				return "Zoom Text";
			default:
				return "";
			}

		case POINT: // Point series selected

			switch (index) {
			case 0:
				return "Rotate";
			case 1:
				return "Drag Point";
			case 2:
				return "Grid Band";
			case 3:
				return "ColorBand";
			case 4:
				return "Annotation Click";
			case 5:
				return "Pan";
			case 6:
				return "Pan Data";
			case 7:
				return "Zoom";
			case 8:
				return "Zoom Data";
			case 9:
				return "Zoom Text";
			default:
				return "";
			}
		case HORIZBAR: // Horizontal bar series selected
			switch (index) {
			case 0:
				return "Draw Line";
			case 1:
				return "Extra Legend";
			case 2:
				return "Color Band";
			case 3:
				return "Annotation";
			case 4:
				return "Rotate";
			case 5:
				return "Pan";
			case 6:
				return "Pan Data";
			case 7:
				return "Zoom";
			case 8:
				return "Zoom Data";
			case 9:
				return "Zoom Text";
			default:
				return "";
			}

		case CANDLE: // Candle series selected
			switch (index) {
			case 0:
				return "Color Band";
			case 1:
				return "Axis Arrow";
			case 2:
				return "Annotation";
			case 3:
				return "Drag Point";
			case 4:
				return "Grid Band";
			case 5:
				return "Pan";
			case 6:
				return "Pan Data";
			case 7:
				return "Zoom";
			case 8:
				return "Zoom Data";
			case 9:
				return "Zoom Text";
			default:
				return "";
			}
		case SURFACE: // Surface series selected
			switch (index) {
			case 0:
				return "Color Band";
			case 1:
				return "Axis Arrow";
			case 2:
				return "GridBand";
			case 3:
				return "GridTranspose";
			case 4:
				return "Rotate";
			case 5:
				return "Pan";
			case 6:
				return "Pan Data";
			case 7:
				return "Zoom";
			case 8:
				return "Zoom Data";
			case 9:
				return "Zoom Text";
			default:
				return "";
			}
		case TOWER: // Tower series selected
			switch (index) {
			case 0:
				return "Color Band";
			case 1:
				return "Axis Arrow";
			case 2:
				return "Annotation";
			case 3:
				return "Rotate";
			case 4:
				return "GridBand";
			case 5:
				return "Pan";
			case 6:
				return "Pan Data";
			case 7:
				return "Zoom";
			case 8:
				return "Zoom Data";
			case 9:
				return "Zoom Text";
			default:
				return "";
			}
		case CONTOUR: // Contour series selected
			switch (index) {
			case 0:
				return "Axis Arrow";
			case 1:
				return "Color Band";
			case 2:
				return "Annotation Click";
			case 3:
				return "Drag Points";
			case 4:
				return "Draw Line";
			case 5:
				return "Pan";
			case 6:
				return "Pan Data";
			case 7:
				return "Zoom";
			case 8:
				return "Zoom Data";
			case 9:
				return "Zoom Text";
			default:
				return "";
			}
		case COLORGRID: // ColorGrid series selected
			switch (index) {
			case 0:
				return "Color Band";
			case 1:
				return "Axis Arrow";
			case 2:
				return "Rotate";
			case 3:
				return "Draw Line";
			case 4:
				return "Marks Tip";
			case 5:
				return "Pan";
			case 6:
				return "Pan Data";
			case 7:
				return "Zoom";
			case 8:
				return "Zoom Data";
			case 9:
				return "Zoom Text";
			default:
				return "";
			}
		case POLAR: // Polar series selected
			switch (index) {
			case 0:
				return "Annotation";
			case 1:
				return "Rotate";
			case 2:
				return "Drag Points";
			case 3:
				return "Marks Tip";
			case 4:
				return "Axis Labels";
			case 5:
				return "Pan";
			case 6:
				return "Pan Data";
			case 7:
				return "Zoom";
			case 8:
				return "Zoom Data";
			case 9:
				return "Zoom Text";
			default:
				return "";
			}
		case SMITH: // Smith series selected
			switch (index) {
			case 0:
				return "Annotation";
			case 1:
				return "Rotate";
			case 2:
				return "Drag Point";
			case 3:
				return "Marks Tip";
			case 4:
				return "Axis Labels";
			case 5:
				return "Pan";
			case 6:
				return "Pan Data";
			case 7:
				return "Zoom";
			case 8:
				return "Zoom Data";
			case 9:
				return "Zoom Text";
			default:
				return "";
			}
		case RADAR: // Radar series selected
			switch (index) {
			case 0:
				return "Annotation";
			case 1:
				return "Rotate";
			case 2:
				return "Drag Point";
			case 3:
				return "Marks Tip";
			case 4:
				return "Axis Labels";
			case 5:
				return "Pan";
			case 6:
				return "Pan Data";
			case 7:
				return "Zoom";
			case 8:
				return "Zoom Data";
			case 9:
				return "Zoom Text";
			default:
				return "";
			}
		case FASTLINE: // Fastline series selected
			switch (index) {
			case 0:
				return "Axis Labels";
			case 1:
				return "Series Band";
			case 2:
				return "Extra Legend";
			case 3:
				return "Draw Line";
			case 4:
				return "Drag Point";
			case 5:
				return "Pan";
			case 6:
				return "Pan Data";
			case 7:
				return "Zoom";
			case 8:
				return "Zoom Data";
			case 9:
				return "Zoom Text";
			default:
				return "";
			}
		case ISOSURFACE: // IsoSurface series selected
			switch (index) {
			case 0:
				return "Color Band";
			case 1:
				return "Axis Scroll";
			case 2:
				return "GridBand";
			case 3:
				return "GridTranspose";
			case 4:
				return "Rotate";
			case 5:
				return "Pan";
			case 6:
				return "Pan Data";
			case 7:
				return "Zoom";
			case 8:
				return "Zoom Data";
			case 9:
				return "Zoom Text";
			default:
				return "";
			}
		case BUBBLE: // Bubble series selected
			switch (index) {
			case 0:
				return "Axis Label";
			case 1:
				return "Axis Arrow";
			case 2:
				return "Annotation Click";
			case 3:
				return "DrawLine";
			case 4:
				return "Drag Point";
			case 5:
				return "Pan";
			case 6:
				return "Pan Data";
			case 7:
				return "Zoom";
			case 8:
				return "Zoom Data";
			case 9:
				return "Zoom Text";
			default:
				return "";
			}
		case CIRCULARGAUGE: // CircularGauge series selected
			switch (index) {
			case 0:
				return "Item 1";
			case 1:
				return "Item 2";
			case 2:
				return "Item 3";
			case 3:
				return "Item 4";
			case 4:
				return "Item 5";
			default:
				return "";
			}
		case LINEARGAUGE: // LinearGauge series selected
			switch (index) {
			case 0:
				return "Item 1";
			case 1:
				return "Item 2";
			case 2:
				return "Item 3";
			case 3:
				return "Item 4";
			case 4:
				return "Item 5";
			default:
				return "";
			}
		case MAP: // Map series selected
			switch (index) {
			case 0:
				return "Grid Band";
			case 1:
				return "Axis Arrow";
			case 2:
				return "ColorBand";
			case 3:
				return "Annotation";
			case 4:
				return "Axis Label";
			case 5:
				return "Pan";
			case 6:
				return "Pan Data";
			case 7:
				return "Zoom";
			case 8:
				return "Zoom Data";
			case 9:
				return "Zoom Text";
			default:
				return "";
			}
		default:
			return "";
		}
	}

	private static void disableTools(int toolToDisable) {
		switch (toolToDisable) {
		case 5:
			panChart.setChart(null);
			break;
		case 6:
			panData.setChart(null);
			break;
		case 7:
			zoomChart.setChart(null);
			break;
		case 8:
			zoomData.setChart(null);
			break;
		case 9:
			zoomText.setChart(null);
			break;
		}
	}
}
