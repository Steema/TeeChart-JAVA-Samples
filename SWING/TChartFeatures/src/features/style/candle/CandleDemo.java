/*
 * CandleDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.candle;
import com.steema.teechart.axis.Axis;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.editors.ButtonColor;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Candle;
import com.steema.teechart.styles.CandleStyle;
import com.steema.teechart.styles.MarksStyle;
import com.steema.teechart.tools.MarksTip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import features.utils.EnumStrings;

/**
 *
 * @author tom
 */
public class CandleDemo extends ChartSamplePanel
        implements ItemListener, ActionListener {

    private Candle candleSeries;
    private ButtonColor upColorButton, downColorButton;

    /** Creates a new instance of AreaSeries */
    public CandleDemo() {
        super();
        borderButton.addItemListener(this);
        view3DButton.addItemListener(this);
        editButton.addActionListener(this);
        candleStyleList.addActionListener(this);
        upColorButton.addActionListener(this);
        downColorButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == candleStyleList) {
            CandleStyle candleStyle = CandleStyle.CANDLESTICK;
            switch (candleStyleList.getSelectedIndex()) {
                case 0: candleStyle = CandleStyle.CANDLESTICK; break;
                case 1: candleStyle = CandleStyle.CANDLEBAR; break;
                case 2: candleStyle = CandleStyle.OPENCLOSE; break;
            }
            setCandleSeriesStyle(candleStyle);
        } else if (source == editButton) {
            ChartEditor.editSeries(candleSeries);
        } else if (source == upColorButton) {
            candleSeries.setUpCloseColor(new Color(upColorButton.getColor())); //TODO: change getColor in ButtonColor?
        } else if (source == downColorButton) {
            candleSeries.setDownCloseColor(new Color(downColorButton.getColor()));
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == borderButton) {
            candleSeries.getPen().setVisible(isSelected);
        } else if (source == view3DButton) {
            candleSeries.getPointer().setDraw3D(true);
            chart1.getAspect().setView3D(isSelected);
        } else if (source == upColorButton) {
            candleSeries.setUpCloseColor(new Color(upColorButton.getColor()));
        } else if (source == downColorButton) {
            candleSeries.setDownCloseColor(new Color(downColorButton.getColor()));
        }
    }

    protected void initChart() {
        super.initChart();
        chart1.getAspect().setView3D(false);
        chart1.getAspect().setChart3DPercent(10);
        chart1.getLegend().setVisible(false);
        chart1.getHeader().setVisible(true);
        chart1.setText("Candle OHCL");
        chart1.getHeader().getFont().setColor(Color.WHITE);
        chart1.getPanel().setColor(Color.GRAY);

        Axis tmpAxis;
        tmpAxis = chart1.getAxes().getBottom();
        tmpAxis.getGrid().setColor(Color.BLACK);
        tmpAxis.getGrid().setStyle(DashStyle.SOLID);
        tmpAxis = chart1.getAxes().getLeft();
        tmpAxis.getGrid().setColor(Color.SILVER);
    }

    protected void initComponents() {
        super.initComponents();

        candleSeries = new Candle(chart1.getChart());
        candleSeries.fillSampleValues(20);

        MarksTip tool = new MarksTip();
        tool.setSeries(candleSeries);
        tool.setStyle(MarksStyle.XVALUE);

        upColorButton = new ButtonColor(candleSeries.getUpCloseColor());
        upColorButton.setText("UpCloseColor");

        downColorButton = new ButtonColor(candleSeries.getDownCloseColor());
        downColorButton.setText("DownCloseColor");


        editButton = new JButton("Edit...");

        borderButton = new JCheckBox("Border");
        borderButton.setSelected(candleSeries.getPen().getVisible());
        view3DButton = new JCheckBox("Draw 3D");
        view3DButton.setSelected(chart1.getAspect().getView3D());

        candleStyleList = new JComboBox(EnumStrings.CANDLE_STYLES);
        candleStyleList.setSelectedIndex(0);
        setCandleSeriesStyle(CandleStyle.CANDLESTICK);
    }

    protected void initGUI() {
        super.initGUI();

        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Style:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpLabel.setLabelFor(candleStyleList);
            tmpPane.add(tmpLabel);
            tmpPane.add(candleStyleList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(borderButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(view3DButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(upColorButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(downColorButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void setCandleSeriesStyle(CandleStyle candleStyle) {
        candleSeries.setStyle(candleStyle);
    }

    private JButton editButton;
    private JComboBox candleStyleList;
    private JCheckBox borderButton;
    private JCheckBox view3DButton;
}
