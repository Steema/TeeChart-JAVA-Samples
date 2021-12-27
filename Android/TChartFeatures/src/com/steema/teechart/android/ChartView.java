package com.steema.teechart.android;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.steema.teechart.Chart;
import com.steema.teechart.DateTime;
import com.steema.teechart.DateTimeStep;
import com.steema.teechart.TChart;
import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.android.SeriesDataDialog.OnImportData;
import com.steema.teechart.android.editors.ChartEditor;
import com.steema.teechart.android.editors.ThemesEditor;
import com.steema.teechart.android.editors.ToolsEditor;
import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.ChartBrush;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.drawing.Image;
import com.steema.teechart.drawing.LineCap;
import com.steema.teechart.events.ChartDrawEvent;
import com.steema.teechart.events.ChartPaintAdapter;
import com.steema.teechart.events.FrameworkMouseEvent;
import com.steema.teechart.events.SeriesMouseAdapter;
import com.steema.teechart.events.SeriesMouseEvent;
import com.steema.teechart.events.ToolMouseListener;
import com.steema.teechart.languages.Language;
import com.steema.teechart.legend.Legend;
import com.steema.teechart.legend.LegendAlignment;
import com.steema.teechart.legend.LegendStyle;
import com.steema.teechart.misc.Utils;
import com.steema.teechart.styles.Area;
import com.steema.teechart.styles.Bar;
import com.steema.teechart.styles.Bubble;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.CircularGauge;
import com.steema.teechart.styles.ColorGrid;
import com.steema.teechart.styles.Contour;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.styles.Gantt;
import com.steema.teechart.styles.HighLow;
import com.steema.teechart.styles.HorizBar;
import com.steema.teechart.styles.IsoSurface;
import com.steema.teechart.styles.Line;
import com.steema.teechart.styles.LinearGauge;
import com.steema.teechart.styles.Map;
import com.steema.teechart.styles.PaletteStyle;
import com.steema.teechart.styles.Pie;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Points;
import com.steema.teechart.styles.Polar;
import com.steema.teechart.styles.Radar;
import com.steema.teechart.styles.Series;
import com.steema.teechart.styles.Series.MarkTextResolver;
import com.steema.teechart.styles.SeriesMarks;
import com.steema.teechart.styles.SeriesPointer;
import com.steema.teechart.styles.Smith;
import com.steema.teechart.styles.Surface;
import com.steema.teechart.styles.Tower;
import com.steema.teechart.styles.TowerStyle;
import com.steema.teechart.styles.ValueListOrder;
import com.steema.teechart.themes.ColorPalettes;
import com.steema.teechart.themes.ThemesList;
import com.steema.teechart.tools.Annotation;
import com.steema.teechart.tools.AxisArrow;
import com.steema.teechart.tools.AxisArrowPosition;
import com.steema.teechart.tools.CursorTool;
import com.steema.teechart.tools.DragMarks;
import com.steema.teechart.tools.DragPoint;
import com.steema.teechart.tools.DragPointStyle;
import com.steema.teechart.tools.DrawLine;
import com.steema.teechart.tools.DrawLineItem;
import com.steema.teechart.tools.ExtraLegend;
import com.steema.teechart.tools.GanttTool;
import com.steema.teechart.tools.MarksTip;
import com.steema.teechart.tools.ScrollPager;
import com.steema.teechart.tools.SeriesBand;

public class ChartView extends ListItem {

	private TChart chart;
	private static int serieSelected;
	private int themeSelected, toolSelected, descriptionSelected = 0;
	private Series series;
	private Bundle extras;
	private MarksTip marksTip;
	private Bar barseries1, barseries2, barseries3;
	private GanttTool ganttTool;
	private Gantt gantt;
	Dialog dialog;
	final static int MENU_SETTINGS = 0, MENU_THEMES = 1, MENU_TOOLS = 2, MENU_SAVE = 3;
	final static int MENU_DESCRIPTION = 4, MENU_EXIT = 5;
	private static final int SELECT_TEXTDATA = 1111;
	private static final int SELECT_IMAGE = 1112;
	private boolean mExternalStorageAvailable = false;
	private boolean mExternalStorageWriteable = false;
	final static int PIE = 0, LINE = 1, BAR = 2, AREA = 3, POINT = 4, HORIZBAR = 5, CANDLE = 6, SURFACE = 7, TOWER = 8, CONTOUR = 9, COLORGRID = 10,
			POLAR = 11, SMITH = 12, RADAR = 13, FASTLINE = 14, ISOSURFACE = 15, BUBBLE = 16, CIRCULARGAUGE = 17, LINEARGAUGE = 18, MAP = 19,
			HIGHLOW = 20;

	TextView description;
	Button okButton;

	// private String

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.chartview);
		LinearLayout group = (LinearLayout) findViewById(R.id.linearLayoutTchart);
		chart = new TChart(this);
		group.addView(chart);
		chart.getPanel().setBorderRound(7);
		chart.getAspect().setView3D(false);
		// We recover data from previous screen, this data is an integer
		// which allow us to differentiate previous selection in 3 differents
		// choices :
		// 0 = Serie
		// 1 = Theme
		// 2 = Tool

		extras = getIntent().getExtras();
		int valueListener = extras.getInt("valueListener");

		// We hide the Tabs and enable Home button
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// the following may be used and set to change default zoom and
		// scroll behaviour. See the tutorials for more information.
		// chart.getZoom().setZoomStyle( ZoomStyle.FULLCHART);
		// //if inchart ...>
		// chart.getPanning().setMouseButton(FrameworkMouseEvent.BUTTON1);
		// chart.getZoom().setMouseButton(FrameworkMouseEvent.BUTTON3);

		if (valueListener == 1) // Theme Selected
		{
			if (extras.getBoolean("serieSelected", true)) {
				descriptionSelected = extras.getInt("SeriesPosition");
				selectSerie(extras.getInt("SeriesPosition"));
			}
			themeSelected = extras.getInt("numThemeSelected");
			ThemesList.applyTheme(chart.getChart(), themeSelected);

		} else if (valueListener == 0) // Serie Selected
		{
			serieSelected = extras.getInt("SeriesPosition");

			if (extras.getBoolean("ThemeSelected", true)) {
				ThemesList.applyTheme(chart.getChart(), extras.getInt("numThemeSelected"));
			} else {
				ThemesList.applyTheme(chart.getChart(), 1);
			}
			descriptionSelected = serieSelected;
			selectSerie(serieSelected);
		} else if (valueListener == 2) // Tool Selected
		{
			if (extras.getBoolean("ThemeSelected", true)) {
				ThemesList.applyTheme(chart.getChart(), extras.getInt("numThemeSelected"));
			} else {
				ThemesList.applyTheme(chart.getChart(), 1);
			}

			toolSelected = extras.getInt("toolsList");
			selectTool(toolSelected);
		}
	}

	// With one of the recovered integers we can select series
	// with properties for a correct display
	public void selectSerie(int serieSelected) {
		chart.removeAllSeries();
		try {
			switch (serieSelected) {

			case PIE: // Pie series selected
				chart.getAspect().setView3D(false);
				chart.getLegend().setVisible(false);
				chart.getHeader().setText("Pie Series");
				chart.getHeader().getFont().setSize(14);

				series = Series.createNewSeries(chart.getChart(), Pie.class, null);
				series.fillSampleValues(4);
				series.getMarks().setTransparent(true);
				series.getMarks().getFont().setColor(Color.WHITE);
				series.getMarks().getArrow().setVisible(false);
				((Pie) series).setCircled(true);
				((Pie) series).getPen().setWidth(4);
				((Pie) series).getPen().setColor(Color.fromArgb(30, 30, 30));
				((Pie) series).getPen().setEndCap(LineCap.ROUND);
				((Pie) series).setExplodeBiggest(15);

				series.setColorEach(false);
				int themeS = 1;
				if (extras.getBoolean("ThemeSelected", true))
					themeS = extras.getInt("numThemeSelected");
				
				for (int i = 0; i < series.getCount(); i++)
					series.getColors().setColor(i, ColorPalettes.Palettes[themeS][i]);
				
				break;

			case LINE: // Line series selected
				for (int i = 0; i < 2; i++) {
					series = Series.createNewSeries(chart.getChart(), Line.class, null);
					series.fillSampleValues();
					chart.addSeries(series);
					((Line) series).getLinePen().setWidth(3);
				}

				chart.getLegend().setVisible(false);
				chart.getHeader().setText("Line Series");
				chart.getHeader().getFont().setSize(14);
				break;

			case BAR: // Bar series selected
				for (int i = 0; i < 2; i++) {
					series = Series.createNewSeries(chart.getChart(), Bar.class, null);
					series.fillSampleValues(4);
					series.getMarks().setVisible(false);
					chart.addSeries(series);
				}

				chart.getLegend().setVisible(false);
				chart.getHeader().setText("Bar Series");
				chart.getHeader().getFont().setSize(14);
				break;

			case AREA: // Area series selected
				series = Series.createNewSeries(chart.getChart(), Area.class, null);
				series.fillSampleValues();
				chart.addSeries(series);
				chart.getLegend().setVisible(false);
				chart.getHeader().setText("Area Series");
				chart.getHeader().getFont().setSize(14);
				((Area) series).setTransparency(50);
				((Area) series).getAreaLinesPen().setColor(Utils.darkenColor(series.getColor(), 60));
				break;

			case POINT: // Point series selected
				series = Series.createNewSeries(chart.getChart(), Points.class, null);
				series.fillSampleValues();
				chart.addSeries(series);
				chart.getLegend().setVisible(false);
				chart.getHeader().setText("Point Series");
				chart.getHeader().getFont().setSize(14);
				((Points) series).getPointer().setStyle(PointerStyle.CIRCLE);
				((Points) series).getPointer().setHorizSize(9);
				((Points) series).getPointer().setVertSize(9);
				break;

			case HORIZBAR: // Horizontal bar series selected
				for (int i = 0; i < 2; i++) {
					series = Series.createNewSeries(chart.getChart(), HorizBar.class, null);
					series.fillSampleValues();
					series.getMarks().setVisible(false);
					chart.addSeries(series);
				}

				chart.getLegend().setVisible(false);
				chart.getHeader().setText("HorizBar Series");
				chart.getHeader().getFont().setSize(14);
				break;

			case CANDLE: // Candle series selected
				series = Series.createNewSeries(chart.getChart(), Candle.class, null);
				series.fillSampleValues();
				chart.addSeries(series);
				chart.getLegend().setVisible(false);
				chart.getHeader().setText("Candle Series");
				chart.getHeader().getFont().setSize(14);
				break;

			case SURFACE: // Surface series selected
				chart.getAspect().setView3D(true);
				series = Series.createNewSeries(chart.getChart(), Surface.class, null);
				series.fillSampleValues();
				chart.addSeries(series);
				chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
				chart.getAspect().setOrthogonal(false);
				chart.getAspect().setZoom(70);
				chart.getAspect().setRotation(320);
				chart.getAspect().setElevation(340);
				chart.getAspect().setPerspective(37);
				chart.getAspect().setChart3DPercent(90);
				break;

			case TOWER: // Tower series selected
				chart.getAspect().setView3D(true);
				series = Series.createNewSeries(chart.getChart(), Tower.class, null);
				series.fillSampleValues();
				chart.addSeries(series);
				chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
				chart.getHeader().setText("Tower Series");
				chart.getHeader().getFont().setSize(14);
				chart.getAspect().setOrthogonal(false);
				chart.getAspect().setZoom(70);
				chart.getAspect().setChart3DPercent(90);
				chart.getAspect().setRotation(320);
				chart.getAspect().setElevation(345);
				((Tower) series).setTowerStyle(TowerStyle.CUBE);
				break;

			case CONTOUR: // Contour series selected
				series = Series.createNewSeries(chart.getChart(), Contour.class, null);
				series.fillSampleValues();
				chart.addSeries(series);

				chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
				chart.getHeader().setText("Contour Series");
				chart.getHeader().getFont().setSize(14);
				break;

			case COLORGRID: // ColorGrid series selected
				series = Series.createNewSeries(chart.getChart(), ColorGrid.class, null);
				series.fillSampleValues();
				chart.addSeries(series);
				chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
				chart.getHeader().setText("ColorGrid Series");
				chart.getHeader().getFont().setSize(14);
				break;

			case POLAR: // Polar series selected
				series = Series.createNewSeries(chart.getChart(), Polar.class, null);
				series.fillSampleValues();
				chart.addSeries(series);
				chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
				chart.getHeader().setText("Polar Series");
				chart.getHeader().getFont().setSize(14);
				((Polar) series).setTransparency(20);
				break;

			case SMITH: // Smith series selected
				series = Series.createNewSeries(chart.getChart(), Smith.class, null);
				series.fillSampleValues();
				chart.addSeries(series);
				chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
				chart.getHeader().setText("Smith Series");
				chart.getHeader().getFont().setSize(14);
				((Smith) series).getCirclePen().setColor(Color.WHITE);
				break;

			case RADAR: // Radar series selected
				series = Series.createNewSeries(chart.getChart(), Radar.class, null);
				series.fillSampleValues();
				chart.addSeries(series);
				chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
				chart.getHeader().setText("Radar Series");
				chart.getHeader().getFont().setSize(14);
				((Radar) series).setTransparency(20);
				break;

			case FASTLINE: // Fastline series selected
				for (int i = 0; i < 2; i++) {
					series = Series.createNewSeries(chart.getChart(), FastLine.class, null);
					series.fillSampleValues(1000);
					chart.addSeries(series);
				}

				chart.getLegend().setVisible(false);
				chart.getHeader().setText("FastLine Series");
				chart.getHeader().getFont().setSize(14);
				chart.getAspect().setOrthogonal(false);
				break;

			case ISOSURFACE: // IsoSurface series selected
				chart.getAspect().setView3D(true);
				series = Series.createNewSeries(chart.getChart(), IsoSurface.class, null);
				series.fillSampleValues();
				chart.addSeries(series);
				chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
				chart.getHeader().setText("ISOSurface Series");
				chart.getHeader().getFont().setSize(14);
				chart.getAspect().setOrthogonal(false);
				chart.getAspect().setZoom(70);
				chart.getAspect().setRotation(320);
				chart.getAspect().setElevation(340);
				chart.getAspect().setPerspective(37);
				chart.getAspect().setChart3DPercent(90);
				((IsoSurface) series).setPaletteStyle(PaletteStyle.RAINBOW);
				break;

			case BUBBLE: // Bubble series selected
				series = Series.createNewSeries(chart.getChart(), Bubble.class, null);
				series.fillSampleValues();
				chart.addSeries(series);
				chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
				chart.getHeader().setText("Bubble Series");
				chart.getHeader().getFont().setSize(14);
				break;

			case CIRCULARGAUGE: // CircularGauge series selected
				series = Series.createNewSeries(chart.getChart(), CircularGauge.class, null);
				((CircularGauge) series).setValue(20.0);
				chart.addSeries(series);
				chart.getHeader().setText("CircularGauge Series");
				chart.getHeader().getFont().setSize(14);
				break;

			case LINEARGAUGE: // LinearGauge series selected
				series = Series.createNewSeries(chart.getChart(), LinearGauge.class, null);
				series.fillSampleValues();
				chart.addSeries(series);
				chart.getHeader().setText("LinearGauge Series");
				chart.getHeader().getFont().setSize(14);
				((LinearGauge) series).getHand().getGradient().setVisible(true);
				((LinearGauge) series).getHand().getGradient().setStartColor(Color.GREEN);
				((LinearGauge) series).getHand().getGradient().setEndColor(Color.YELLOW);
				((LinearGauge) series).getHand().getGradient().setDirection(GradientDirection.HORIZONTAL);
				((LinearGauge) series).getHand().getColor().transparentColor(20);
				break;

			case MAP: // Map series selected
				series = Series.createNewSeries(chart.getChart(), Map.class, null);
				series.fillSampleValues();
				chart.addSeries(series);
				chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
				chart.getHeader().setText("Map Series");
				chart.getHeader().getFont().setSize(14);
				break;

			case HIGHLOW: // HighLow series selected
				series = Series.createNewSeries(chart.getChart(), HighLow.class, null);
				series.fillSampleValues();
				chart.addSeries(series);
				chart.getLegend().setAlignment(LegendAlignment.BOTTOM);
				chart.getHeader().setText("HighLow Series");
				chart.getHeader().getFont().setSize(14);
				break;
			}

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	// With one of the recovered integers we can select tool
	private void selectTool(int toolSelected) {

		switch (toolSelected) {
		case 0: // Tool : Annotation Click
			final Annotation tool1,
			tool2,
			tool3;

			// Init Chart
			chart.removeAllSeries();
			chart.getHeader().setVisible(true);
			chart.setText("Annotation Tools\nClick and Cursor Example");
			chart.getLegend().setVisible(false);
			chart.removeAllSeries();
			FastLine tmpSeries = new com.steema.teechart.styles.FastLine(chart.getChart());
			tmpSeries.fillSampleValues(1000);

			chart.getAspect().setOrthogonal(false);
			// Start tool
			tool1 = new Annotation(chart.getChart());
			tool1.setText("Annotation 1");
			tool1.getShape().setCustomPosition(true);
			tool1.setLeft(65);
			tool1.setTop(125);
			ToolMouseListener tool1Listener = new ToolMouseListener() {
				public void toolClicked(FrameworkMouseEvent e) {
					AlertDialog dialog = new AlertDialog.Builder(ChartView.this).create();
					dialog.setTitle(Language.getString("Message"));
					dialog.setMessage(tool1.getText());

					dialog.setButton(DialogInterface.BUTTON_POSITIVE, Language.getString("OK"), new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							dialog.cancel();
						}
					});
					dialog.show();
				}
			};
			tool1.addToolMouseListener(tool1Listener);

			tool2 = new Annotation(chart.getChart());
			tool2.setText("Annotation 2");
			tool2.getShape().setCustomPosition(true);
			tool2.setLeft(130);
			tool2.setTop(70);
			tool2.getShape().setColor(Color.RED);
			tool2.getShape().getFont().setColor(Color.BLUE);

			ToolMouseListener tool2Listener = new ToolMouseListener() {
				public void toolClicked(FrameworkMouseEvent e) {
					AlertDialog dialog = new AlertDialog.Builder(ChartView.this).create();
					dialog.setTitle(Language.getString("Message"));
					dialog.setMessage(tool2.getText());

					dialog.setButton(DialogInterface.BUTTON_POSITIVE, Language.getString("OK"), new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							dialog.cancel();
						}
					});
					dialog.show();
				}
			};
			tool2.addToolMouseListener(tool2Listener);

			tool3 = new Annotation(chart.getChart());
			tool3.setText("Another one\nwith multiple\nlines of text.");
			tool3.getShape().setCustomPosition(true);
			tool3.setLeft(130);
			tool3.setTop(170);
			tool3.getShape().setColor(Color.YELLOW);
			ToolMouseListener tool3Listener = new ToolMouseListener() {
				public void toolClicked(FrameworkMouseEvent e) {
					AlertDialog dialog = new AlertDialog.Builder(ChartView.this).create();
					dialog.setTitle(Language.getString("Message"));
					dialog.setMessage(tool3.getText());

					dialog.setButton(DialogInterface.BUTTON_POSITIVE, Language.getString("OK"), new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							dialog.cancel();
						}
					});
					dialog.show();

				}
			};
			tool3.addToolMouseListener(tool3Listener);
			serieSelected = FASTLINE;
			break;

		case 1: // Tool : Axis Arrow

			// Init Chart
			chart.removeAllSeries();
			chart.getLegend().setVisible(false);
			chart.getAxes().setDrawBehind(true);
			// Start tool
			AxisArrow arrowtool1,
			arrowtool2,
			arrowtool3;
			Line arrowSeries = new com.steema.teechart.styles.Line(chart.getChart());
			arrowSeries.fillSampleValues(10);
			arrowSeries.getPointer().setVisible(true);
			arrowSeries.getMarks().setVisible(false);

			arrowtool1 = new AxisArrow(chart.getAxes().getLeft());
			arrowtool1.setPosition(AxisArrowPosition.START);
			arrowtool1.setLength(70);

			arrowtool2 = new AxisArrow(chart.getAxes().getBottom());
			arrowtool2.getBrush().setColor(Color.GREEN_YELLOW);
			arrowtool2.getPen().setColor(Color.WHITE);
			arrowtool2.setPosition(AxisArrowPosition.BOTH);
			arrowtool2.setLength(70);

			arrowtool3 = new AxisArrow(chart.getAxes().getLeft());
			arrowtool3.setPosition(AxisArrowPosition.END);
			arrowtool3.getBrush().setColor(Color.WHITE);
			arrowtool3.getPen().setColor(Color.BLUE);
			arrowtool3.setLength(70);
			serieSelected = LINE;
			break;

		case 2: // Tool: Drag Marks
			// Init Chart
			chart.getLegend().setVisible(false);
			chart.removeAllSeries();
			// Start tool
			new DragMarks(chart.getChart());
			Points series1 = new Points(chart.getChart());
			series1.fillSampleValues(10);
			series1.getMarks().setVisible(true);
			series1.getMarks().getFont().setSize(15);
			series1.getMarks().getCallout().setLength(10);
			((Points) series1).getPointer().setStyle(PointerStyle.CIRCLE);
			((Points) series1).getPointer().setHorizSize(9);
			((Points) series1).getPointer().setVertSize(9);
			Line series2 = new Line(chart.getChart());
			series2.fillSampleValues(6);
			series2.getPointer().setVisible(false);
			series2.getMarks().getFont().setSize(13);

			SeriesMarks tmpMarks = series2.getMarks();

			tmpMarks.setBackColor(Color.SILVER);
			tmpMarks.setColor(Color.SILVER);
			tmpMarks.getFont().setColor(Color.BLUE);
			tmpMarks.getFont().getShadow().setColor(Color.AQUA);
			tmpMarks.getFont().getShadow().setHorizSize(1);
			tmpMarks.getFont().getShadow().setVertSize(1);
			tmpMarks.setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
			tmpMarks.setVisible(true);
			serieSelected = POINT;
			break;

		case 3: // Tool: Drag Point

			// Init Chart
			chart.getLegend().setVisible(false);
			chart.getHeader().setVisible(true);
			chart.setText("Drag Point Tool");
			chart.removeAllSeries();
			// Start tool
			Line seriesLine;
			seriesLine = new Line(chart.getChart());
			seriesLine.fillSampleValues(20);
			SeriesPointer tmpPointer = seriesLine.getPointer();
			tmpPointer.setInflateMargins(true);
			tmpPointer.setVisible(true);
			tmpPointer.setVertSize(7);
			tmpPointer.setHorizSize(7);
			DragPoint toolDragPoint;
			toolDragPoint = new DragPoint(seriesLine);
			toolDragPoint.setStyle(DragPointStyle.BOTH);
			serieSelected = LINE;
			break;
		case 4: // Tool : Draw Line
			// Init Chart
			chart.getLegend().setVisible(false);
			chart.removeAllSeries();
			// Start tool
			FastLine fastLineTool = new com.steema.teechart.styles.FastLine(chart.getChart());
			fastLineTool.fillSampleValues(100);
			DrawLine drawLine;
			drawLine = new com.steema.teechart.tools.DrawLine(chart.getChart());
			drawLine.getPen().setColor(Color.RED);

			// create a new DrawLine
			DrawLineItem tmpLine = new DrawLineItem(drawLine);

			// set the "X" line positions (start and end position)
			tmpLine.getStartPos().x = 5.0;
			tmpLine.getEndPos().x = 15.0;

			// set the "Y" line positions (start and end position)
			double tmp = (fastLineTool.getYValues().getMaximum() - fastLineTool.getYValues().getMinimum()) / 5;
			tmpLine.getStartPos().y = fastLineTool.getYValues().getMaximum() - tmp;
			tmpLine.getEndPos().y = fastLineTool.getYValues().getMinimum() + tmp;
			serieSelected = FASTLINE;
			break;
		case 5: // Tool : Extra Legend

			// Init Chart
			chart.removeAllSeries();
			chart.getLegend().setVisible(true);
			chart.getLegend().setLegendStyle(LegendStyle.VALUES);

			// Start Tool
			new Line(chart.getChart());
			new Line(chart.getChart());
			for (int i = 0; i < chart.getSeriesCount(); i++) {
				Series seriesLengend = chart.getSeries(i);
				seriesLengend.fillSampleValues(5);
				seriesLengend.getMarks().setVisible(false);
				((Line) seriesLengend).getPointer().setVisible(false);
			}
			ExtraLegend toolExtraLegend;
			toolExtraLegend = new ExtraLegend(chart.getChart());
			toolExtraLegend.setSeries(chart.getSeries(1));

			// Cosmetic
			Legend tmpLegend = toolExtraLegend.getLegend();
			tmpLegend.setLegendStyle(LegendStyle.VALUES);
			tmpLegend.getShadow().setTransparency(70);
			tmpLegend.setCustomPosition(true);
			tmpLegend.setLeft(50);
			tmpLegend.setTop(50);
			break;
		case 6: // Tool : Gantt Drag Mouse

			/** INIT CHART **/
			chart.setText("Gantt and mouse drag");
			chart.getLegend().setVisible(false);
			// Set 2D

			Axis tmpAxis;

			// Set horizontal bottom axis minimum and maximum
			tmpAxis = chart.getAxes().getBottom();
			tmpAxis.setMinMax(new DateTime(2003, 4, 1), new DateTime(2003, 5, 1));
			// Set horizontal daily Increment for labels and grids:
			tmpAxis.setIncrement(DateTimeStep.ONEWEEK);
			tmpAxis.getLabels().setAngle(90);
			tmpAxis.getLabels().setDateTimeFormat("dd-MMM");

			// Set vertical left axis minimum and maximum
			tmpAxis = chart.getAxes().getLeft();
			tmpAxis.setMinMax(-2, 5);
			tmpAxis.getGrid().setCentered(false);

			// Disable zoom
			chart.getZoom().setAllow(false);

			/** START TOOL **/

			gantt = new com.steema.teechart.styles.Gantt(chart.getChart());
			// Disable automatic sorting by date
			gantt.getXValues().setOrder(ValueListOrder.NONE);

			// Fill Gantt with sample date-time values:

			gantt.add(new DateTime(2003, 4, 1), new DateTime(2003, 4, 10), 0, "A");
			gantt.add(new DateTime(2003, 4, 5), new DateTime(2003, 4, 15), 1, "B");
			gantt.add(new DateTime(2003, 4, 2), new DateTime(2003, 4, 8), 2, "C");
			gantt.add(new DateTime(2003, 4, 9), new DateTime(2003, 4, 21), 3, "D");

			// Another gantt bar on the same line....
			gantt.add(new DateTime(2003, 4, 12), new DateTime(2003, 4, 18), 2, "C");

			// Make marks visible
			gantt.getMarks().setVisible(true);
			gantt.getMarks().getShadow().setSize(0);
			gantt.getMarks().getGradient().setVisible(true);

			gantt.getPointer().setVisible(true);
			gantt.getPointer().setVertSize(6);

			// Example, customize Series marks...
			gantt.setMarkTextResolver(new MarkTextResolver() {
				public String getMarkText(int valueIndex, String markText) {
					String tmpText = "";
					switch (valueIndex) {
					case 0:
						tmpText = "John";
						break;
					case 1:
						tmpText = "Ann";
						break;
					case 2:
						tmpText = "David";
						break;
					case 3:
						tmpText = "Carol";
						break;
					case 4:
						tmpText = "David 2";
						break;
					}
					return tmpText;
				}
			});

			// Initialize Gantt Tool programatically

			ganttTool = new GanttTool(chart.getChart());
			ganttTool.setSeries(gantt);
			serieSelected = LINE;
			break;
		case 7: // Tool: Marks Tip
			// Init Chart
			chart.getHeader().setVisible(false);

			// Start tool
			marksTip = new MarksTip(chart.getChart());
			barseries1 = new Bar(chart.getChart());
			barseries1.getMarks().setVisible(false);
			barseries1.fillSampleValues(4);

			barseries2 = new Bar(chart.getChart());
			barseries2.getMarks().setVisible(false);
			barseries2.fillSampleValues(4);

			barseries3 = new Bar(chart.getChart());
			barseries3.getMarks().setVisible(false);
			barseries3.fillSampleValues(4);

			for (int i = 0; i < chart.getSeriesCount(); i++) {
				final Series s = chart.getSeries(i);
				s.addSeriesMouseListener(new SeriesMouseAdapter() {
					public void seriesClicked(SeriesMouseEvent e) {
						marksTip.setMouseDelay(0);
						marksTip.setSeries(s);
						Toast t;
						t = Toast.makeText(ChartView.this, marksTip.getSeries().getValueMarkText(e.getValueIndex()), Toast.LENGTH_SHORT);
						t.setGravity(Gravity.TOP | Gravity.LEFT, e.getPoint().x - 50, e.getPoint().y + ((View) chart.getParent()).getTop());
						t.show();
					};
				});
			}
			serieSelected = BAR;
			break;
		case 8: // Tool : Moving cursor
			// Init Chart
			chart.getLegend().setVisible(false);

			// Start tool
			Points pointSeries1 = new Points(chart.getChart());
			pointSeries1.fillSampleValues(20);
			((Points) pointSeries1).getPointer().setStyle(PointerStyle.CIRCLE);
			((Points) pointSeries1).getPointer().setHorizSize(9);
			((Points) pointSeries1).getPointer().setVertSize(9);
			SeriesPointer tmpPointer1 = pointSeries1.getPointer();
			tmpPointer1.setInflateMargins(true);
			tmpPointer1.setStyle(PointerStyle.CIRCLE);
			CursorTool cursorTool1;
			cursorTool1 = new CursorTool(chart.getChart());
			cursorTool1.setSeries(pointSeries1);
			cursorTool1.getPen().setColor(Color.gray);
			cursorTool1.getPen().setWidth(5);
			cursorTool1.getPen().setStyle(DashStyle.DASHDOTDOT);
			serieSelected = POINT;
			break;
		case 9: // Tool : Series Band
			// Init chart

			chart.getHeader().setVisible(true);
			chart.setText("SeriesBand Tool Example");
			chart.getLegend().setVisible(false);

			// Start tool
			Line line1 = new Line(chart.getChart());
			Line line2 = new Line(chart.getChart());
			line1.fillSampleValues();

			for (int i = 0; i < line1.getCount(); i++) {
				line2.add(line1.getXValues().getValue(i), line1.getYValues().getValue(i) / 2.0);
			}
			SeriesBand seriesBandTool1;
			seriesBandTool1 = new SeriesBand(chart.getChart());
			seriesBandTool1.setSeries(line1);
			seriesBandTool1.setSeries2(line2);

			line1.getLinePen().setWidth(3);
			line2.getLinePen().setWidth(3);

			seriesBandTool1.getGradient().setVisible(true);
			seriesBandTool1.getGradient().setStartColor(Color.SILVER);
			serieSelected = LINE;
			break;
		case 10: // Scroll Pager
			chart.getHeader().setVisible(true);
			chart.setText("ScrollPager Tool Example");
			chart.getLegend().setVisible(false);

			// Start tool
			Line lineSeries = new Line(chart.getChart());
			lineSeries.getLinePen().setWidth(2);
			lineSeries.fillSampleValues(100);

			new ScrollPager(chart.getChart(), this);

			// The ScrollPager tool needs the chart to be drawn to calculate the
			// SubChart size
			// that's why we call setSeries at the chartPainted event.
			chart.addChartPaintListener(new ChartPaintAdapter() {

				@Override
				public void chartPainted(ChartDrawEvent e) {
					if (chart.getTools().getTool(0).getClass() == ScrollPager.class) {
						ScrollPager s = (ScrollPager) chart.getTools().getTool(0);
						if (s.getSeries() == null) {
							s.setSeries(chart.getSeries(0));

							TChart scrollPagerChart = s.getSubChartTChart();
							if (extras.getBoolean("ThemeSelected", true)) {
								ThemesList.applyTheme(scrollPagerChart.getChart(), extras.getInt("numThemeSelected"));
							} else {
								ThemesList.applyTheme(scrollPagerChart.getChart(), 1);
							}
						}
					}
				}
			});
			break;
		}
	}

	// We associate a thumbnail for all menu options
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, 0, Menu.NONE, Language.getString("Settings")).setIcon(android.R.drawable.ic_menu_manage);
		menu.add(Menu.NONE, 1, Menu.NONE, Language.getString("Theme")).setIcon(android.R.drawable.ic_menu_gallery);
		menu.add(Menu.NONE, 2, Menu.NONE, Language.getString("Tools")).setIcon(android.R.drawable.ic_menu_preferences);
		menu.add(Menu.NONE, 3, Menu.NONE, Language.getString("Save")).setIcon(android.R.drawable.ic_menu_save);
		menu.add(Menu.NONE, 4, Menu.NONE, Language.getString("Description")).setIcon(android.R.drawable.ic_menu_info_details);
		menu.add(Menu.NONE, 5, Menu.NONE, Language.getString("Share")).setIcon(android.R.drawable.ic_menu_share);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_SETTINGS:
			ChartEditor edit = new ChartEditor(this, chart.getChart(), new OnImportData() {
				public void execute() {
					Intent intent = new Intent();
					intent.setType("text/*");
					intent.setAction(Intent.ACTION_GET_CONTENT);

					startActivityForResult(Intent.createChooser(intent, Language.getString("Select Data")), SELECT_TEXTDATA);
				}
			});
			edit.show();
			return true;

		case MENU_THEMES:
			ThemesEditor themes = new ThemesEditor(chart.getChart(), 0);
			themes.choose(ChartView.this);
			return true;

		case MENU_TOOLS:
			ToolsEditor tools = new ToolsEditor(chart.getChart(), 0);
			tools.choose(ChartView.this);
			return true;

		case MENU_SAVE:
			doSave();
			return true;
		case MENU_DESCRIPTION:
			getDescription(descriptionSelected);
			return true;
		case MENU_EXIT:
			try {
				doExport();
			} catch (IOException e) {
				e.printStackTrace();
				Toast.makeText(this, "Cannot export Chart: " + e.getMessage(), Toast.LENGTH_SHORT).show();
			}
			return true;
		case android.R.id.home:
			Intent parentActivityIntent = new Intent(this, ListItem.class);
			parentActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(parentActivityIntent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void doExport() throws IOException {
		final Intent sendIntent = new Intent(Intent.ACTION_SEND);

		final Image image = chart.getExport().getImage().image(chart.getWidth(), chart.getHeight());

		File file;
		final FileOutputStream stream;
		String fileName = Environment.getExternalStorageDirectory().getPath() + "/TeeChart/teechart.png";

		file = new File(fileName);
		stream = new FileOutputStream(file);

		image.save(stream);
		stream.flush();
		stream.close();

		sendIntent.setType("image/png");
		sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + fileName));

		startActivity(Intent.createChooser(sendIntent, "Export"));
	}

	private void getDescription(int descriptionSelected) {

		dialog = new Dialog(ChartView.this);
		dialog.setContentView(R.layout.description);
		dialog.show();

		switch (descriptionSelected) {
		case PIE:
			dialog.setTitle("Pie Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.pie_description);
			break;

		case LINE:
			dialog.setTitle("Line Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.line_description);
			break;

		case BAR:
			dialog.setTitle("Bar Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.bar_description);
			break;

		case AREA:
			dialog.setTitle("Area Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.area_description);
			break;

		case POINT:
			dialog.setTitle("Points Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.points_description);
			break;

		case HORIZBAR:
			dialog.setTitle("Horiz Bar Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.bar_description);
			break;

		case CANDLE:
			dialog.setTitle("Candle Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.candle_description);
			break;

		case SURFACE:
			dialog.setTitle("Surface Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.surface_description);
			break;

		case TOWER:
			dialog.setTitle("Tower Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.tower_description);
			break;

		case CONTOUR:
			dialog.setTitle("Contour Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.contour_description);
			break;

		case COLORGRID:
			dialog.setTitle("ColorGrid Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.colorgrid_description);
			break;

		case POLAR:
			dialog.setTitle("Polar Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.polar_description);
			break;

		case SMITH:
			dialog.setTitle("Smith Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.smith_description);
			break;

		case RADAR:
			dialog.setTitle("Radar Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.polar_description);
			break;

		case FASTLINE:
			dialog.setTitle("Fastline Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.fastline_description);
			break;

		case ISOSURFACE:
			dialog.setTitle("ISOSurface Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.isosurface_description);
			break;

		case BUBBLE:
			dialog.setTitle("Bubble Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.bubble_description);
			break;

		case CIRCULARGAUGE:
			dialog.setTitle("Circular Gauge Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.circulargauge_description);
			break;

		case LINEARGAUGE:
			dialog.setTitle("Linear Gauge Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.lineargauge_description);
			break;

		case MAP:
			dialog.setTitle("Map Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.map_description);
			break;

		case HIGHLOW:
			dialog.setTitle("HighLow Series Description");
			description = (TextView) dialog.findViewById(R.id.descriptionText);
			description.setText(R.string.highlow_description);
			break;
		}

		description.setTextColor(0xFFFFFFFF);
		okButton = (Button) dialog.findViewById(R.id.okButton);
		okButton.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				dialog.cancel();
			}

		});
	}

	public interface InputClickListener {
		void onClick(EditText input);
	}

	private static ChartView mainInstance;
	@SuppressWarnings("unused")
	private static ChartBrush mainBrush;

	public static void browseImage(ChartBrush brush) {
		mainBrush = brush;

		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);

		mainInstance.startActivityForResult(Intent.createChooser(intent, Language.getString("Select_picture_file")), SELECT_IMAGE);
	}

	private void doSave() {
		checkExternalStorage();

		if (mExternalStorageAvailable) {
			if (mExternalStorageWriteable) {
				final File ExtPath = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "TeeChart");
				ExtPath.mkdirs();
				final int numFiles = ExtPath.list().length;
				String text = "TeeChart " + String.valueOf(numFiles + 1);
				inputText("Chart file name", text, new InputClickListener() {

					public void onClick(EditText input) {
						String text = input.getText().toString();

						if (text != "") {
							OutputStream stream = null;
							try {
								text = checkFileExtension(text, Language.getString("TeeFilesExtension"));
								stream = new FileOutputStream(ExtPath.getAbsolutePath() + File.separator + text);

								try {
									chart.getExport().getTemplate().toStream(stream);
									stream.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
						}
					}

				});
			} else
				Toast.makeText(this, "Storage read-only. Cannot save Chart.", Toast.LENGTH_SHORT).show();
		} else
			Toast.makeText(this, "Storage not available.", Toast.LENGTH_SHORT).show();
	}

	private void checkExternalStorage() {
		final String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_SHARED.equals(state)) {
			// We can read and write the media
			mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			// We can only read the media
			mExternalStorageAvailable = true;
			mExternalStorageWriteable = false;
		} else {
			// Something else is wrong. It may be one of many other states, but
			// all we need
			// to know is we can neither read nor write
			mExternalStorageAvailable = mExternalStorageWriteable = false;
		}
	}

	protected void inputText(final String title, final String text, final InputClickListener onOk) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle(title);
		alert.setMessage("");

		final EditText input = new EditText(this);
		input.setText(text);
		alert.setView(input);

		alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				onOk.onClick(input);
			}
		});

		alert.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		});

		alert.show();
	}

	private String checkFileExtension(final String text, final String ext) {
		return hasExtension(text, ext) ? text : text + "." + ext;
	}

	private boolean hasExtension(final String file, final String ext) {
		return file.trim().toUpperCase().endsWith("." + ext.toUpperCase());
	}

	public static String getToolDescription(int index) {
		return ToolsList.getToolDescription(serieSelected, index);
	}

	public void applyTool(Chart chart, int index, Context context) {
		ToolsList.applyTool(chart, index, serieSelected, context);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		chart.getAspect().setView3D(chart.getAspect().getView3D());
	}

}
