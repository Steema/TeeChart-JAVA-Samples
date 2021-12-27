/*
 * CalendarDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.calendar;

import com.steema.teechart.BevelStyle;
import com.steema.teechart.TChart;
import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Calendar;
import com.steema.teechart.styles.Calendar.CalendarCell;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import features.BasicSamplePanel;


/**
 *
 * @author tom
 */
public class CalendarDemo extends BasicSamplePanel
    implements ActionListener, ItemListener {

    private TChart[] chart;
    private Calendar[] calendar;

    /** Creates a new instance of CalendarDemo */
    public CalendarDemo() {
        super();
        JPanel tmpPanel = new JPanel();
        tmpPanel.setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 2;
        tmpPanel.add(chart[0], c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.4;
        c.weighty = 0.4;
        c.gridheight = 1;
        tmpPanel.add(chart[1], c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.3;
        c.weighty = 0.6;
        c.gridheight = 1;
        tmpPanel.add(chart[2], c);

        setSamplePane(tmpPanel);

        weekDaysButton.addItemListener(this);
        monthNameButton.addItemListener(this);
        editButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editSeries(calendar[0]);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == weekDaysButton) {
            calendar[0].getWeekDays().setVisible(isSelected);
        } else if (source == monthNameButton) {
            calendar[0].getMonths().setVisible(isSelected);
        }
    }

    protected void initChart() {
        chart = new TChart[3];
        for (int t=0; t <3; t++)  {
            chart[t] = new TChart();
            chart[t].getAspect().setView3D(false);
            chart[t].getHeader().setVisible(false);
        }
        chart[0].getHeader().setVisible(true);
        chart[0].setText("Calendar Series");

        chart[0].getPanel().getGradient().setDirection(GradientDirection.HORIZONTAL);
        chart[0].getPanel().getGradient().setStartColor(Color.SILVER);
        chart[0].getPanel().getGradient().setEndColor(Color.WHITE);
        chart[0].getPanel().getGradient().setVisible(true);

        chart[1].getPanel().getGradient().setDirection(GradientDirection.VERTICAL);
        //chart[1].getPanel().getGradient().setStartColor(Color.GRAY);
        //chart[1].getPanel().getGradient().setMiddleColor(Color.LIME);
        //chart[1].getPanel().getGradient().setUseMiddleColor(true);
        chart[1].getPanel().getGradient().setStartColor(Color.LIME);
        chart[1].getPanel().getGradient().setEndColor(Color.WHITE);
        chart[1].getPanel().getGradient().setVisible(true);


    }

    protected void initCalendars() {
        calendar = new Calendar[3];
        for (int t=0; t < 3; t++) {
            calendar[t] = new Calendar(chart[t].getChart());
            calendar[t].getMarks().setVisible(false);
            calendar[t].setShowInLegend(false);
            calendar[t].getDays().setTransparent(true);
            calendar[t].getDays().setVisible(true);
            calendar[t].getMonths().setTransparent(true);
            calendar[t].getMonths().setVisible(true);
            calendar[t].getWeekDays().setFormat("E");
        }
        Calendar tmpCalendar;
        CalendarCell tmpCell;

        /* calendar 1 */
        tmpCalendar = calendar[0];
        tmpCalendar.getMonths().setFormat("MMMM, yyyy");
        tmpCalendar.getWeekDays().setUpperCase(true);
        tmpCalendar.getNextMonthButton().setVisible(true);
        tmpCalendar.getPreviousMonthButton().setVisible(true);

        tmpCell = tmpCalendar.getSunday();
        tmpCell.setColor(Color.RED);
        tmpCell.getFont().setColor(Color.WHITE);
        tmpCell.getShadow().setVisible(false);
        tmpCell.setTransparent(false);
        tmpCell.setVisible(true);

        tmpCell = tmpCalendar.getToday();
        tmpCell.setColor(Color.BLUE);
        tmpCell.getFont().setColor(Color.WHITE);
        tmpCell.getShadow().setVisible(false);
        tmpCell.setTransparent(false);
        tmpCell.setVisible(true);

        tmpCell = tmpCalendar.getTrailing();
        tmpCell.getFont().setColor(Color.GRAY);
        tmpCell.setTransparent(true);
        tmpCell.setVisible(true);

        tmpCell = tmpCalendar.getWeekDays();
        tmpCell.setColor(Color.YELLOW);
        tmpCell.getFont().setColor(Color.BLUE);
        tmpCell.getFont().setSize(9);
        tmpCell.getFont().setItalic(true);
        tmpCell.getPen().setVisible(false);
        tmpCell.getShadow().setColor(Color.GRAY);
        tmpCell.getShadow().setHorizSize(3);
        tmpCell.getShadow().setVertSize(3);
        tmpCell.getShadow().setVisible(true);
        tmpCell.setTransparent(false);
        tmpCell.setVisible(true);

        tmpCell = tmpCalendar.getDays();
        tmpCell.setColor(Color.GREEN);


        /* calendar 2 */
        tmpCalendar = calendar[1];
        tmpCalendar.getMonths().setVisible(false);
        tmpCalendar.getMonths().setUpperCase(true);
        tmpCalendar.getPen().setVisible(false);

        tmpCell = tmpCalendar.getSunday();
        tmpCell.setColor(Color.RED);
        tmpCell.getFont().setColor(Color.WHITE);
        tmpCell.getShadow().setVisible(false);
        tmpCell.setTransparent(false);
        tmpCell.setVisible(true);

        tmpCell = tmpCalendar.getToday();
        tmpCell.setColor(Color.BLUE);
        tmpCell.getFont().setColor(Color.WHITE);
        tmpCell.getShadow().setVisible(false);
        tmpCell.setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        tmpCell.setTransparent(false);
        tmpCell.setVisible(true);

        tmpCell = tmpCalendar.getTrailing();
        tmpCell.getFont().setColor(Color.GRAY);
        tmpCell.setTransparent(true);
        tmpCell.setVisible(true);

        tmpCell = tmpCalendar.getWeekDays();
        tmpCell.setBevelInner(BevelStyle.RAISED);
        tmpCell.setBevelWidth(1);
        tmpCell.setColor(Color.SILVER);
        tmpCell.getFont().setColor(Color.NAVY);
        tmpCell.getFont().getShadow().setColor(Color.WHITE);
        tmpCell.getFont().getShadow().setHorizSize(1);
        tmpCell.getFont().getShadow().setVertSize(1);
        tmpCell.getFont().getShadow().setVisible(true);
        tmpCell.getShadow().setVisible(false);
        tmpCell.getPen().setVisible(false);
        tmpCell.setTransparent(false);
        tmpCell.setVisible(true);

        /* calendar 3 */
        tmpCalendar = calendar[2];
        tmpCalendar.getMonths().setFormat("MMMM, yyyy");
        tmpCalendar.getPen().setColor(Color.GRAY);

        tmpCell = tmpCalendar.getDays();
        tmpCell.setColor(Color.YELLOW);
        tmpCell.getFont().setColor(Color.NAVY);
        tmpCell.getFont().setBold(true);
        tmpCell.getShadow().setVisible(false);
        tmpCell.setTransparent(true);
        tmpCell.setVisible(true);

        tmpCell = tmpCalendar.getMonths();
        tmpCell.getFont().setColor(Color.TEAL);
        tmpCell.getFont().setBold(true);
        tmpCell.setTransparent(true);
        tmpCell.setVisible(true);

        tmpCell = tmpCalendar.getSunday();
        tmpCell.setColor(Color.BLUE);
        tmpCell.getFont().setColor(Color.YELLOW);
        tmpCell.getFont().setBold(true);
        tmpCell.getShadow().setVisible(false);
        tmpCell.setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        tmpCell.setTransparent(false);
        tmpCell.setVisible(true);

        tmpCell = tmpCalendar.getToday();
        tmpCell.setColor(Color.RED);
        tmpCell.getFont().setColor(Color.WHITE);
        tmpCell.getShadow().setVisible(false);
        tmpCell.setTransparent(false);
        tmpCell.setVisible(true);

        tmpCell = tmpCalendar.getTrailing();
        tmpCell.getFont().setColor(Color.GRAY);
        tmpCell.getFont().setSize(9);
        tmpCell.getShadow().setVisible(false);
        tmpCell.setTransparent(true);
        tmpCell.setVisible(true);

        tmpCell = tmpCalendar.getWeekDays();
        tmpCell.getPen().setVisible(false);
        tmpCell.setTransparent(false);
        tmpCell.setVisible(false);

    }

    protected void initComponents() {
        super.initComponents();
        initChart();
        initCalendars();

        editButton = new JButton("Edit...");
        monthNameButton = new JCheckBox("Month names");
        monthNameButton.setSelected(calendar[0].getMonths().getVisible());
        weekDaysButton = new JCheckBox("Weekdays");
        weekDaysButton.setSelected(calendar[0].getWeekDays().getVisible());
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(weekDaysButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(monthNameButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JCheckBox weekDaysButton, monthNameButton;
}
