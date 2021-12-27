/*
 * ButtonsDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.calendar;

import com.steema.teechart.Panel;
import com.steema.teechart.TextShapeStyle;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Calendar;
import com.steema.teechart.styles.Calendar.CalendarCell;
import java.awt.PopupMenu;
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

/**
 *
 * @author tom
 */
public class ButtonsDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private Calendar calendar;

    /** Creates a new instance of ButtonsDemo */
    public ButtonsDemo() {
        super();
        editButton.addActionListener(this);
        popupButton.addActionListener(this);
        showButtonsButton.addItemListener(this);
        transparencyButton.addItemListener(this);
        alignList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == alignList) {
            // re-position buttons to left, right or middle
            switch (alignList.getSelectedIndex()) {
                case 0: {
                    calendar.getPreviousMonthButton().setAlignmentX(JButton.LEFT_ALIGNMENT);
                    calendar.getNextMonthButton().setAlignmentX(JButton.LEFT_ALIGNMENT);
                    break;
                }
                case 1: {
                    calendar.getPreviousMonthButton().setAlignmentX(JButton.CENTER_ALIGNMENT);
                    calendar.getNextMonthButton().setAlignmentX(JButton.CENTER_ALIGNMENT);
                    break;
                }
                case 2: {
                    calendar.getPreviousMonthButton().setAlignmentX(JButton.RIGHT_ALIGNMENT);
                    calendar.getNextMonthButton().setAlignmentX(JButton.RIGHT_ALIGNMENT);
                    break;
                }
            }
        } else if (source == editButton) {
            ChartEditor.editSeries(calendar);
        } else if (source == popupButton) {
            ((PopupMenu)calendar.getPopupMenu()).show(this, chart1.getWidth() / 2, 10);
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == showButtonsButton) {
            calendar.getNextMonthButton().setVisible(isSelected);
            calendar.getPreviousMonthButton().setVisible(isSelected);
        } else if (source == transparencyButton) {
            // switch on / off transparency for some cells...
            int tmp = (isSelected) ? 50 : 0;
            calendar.getDays().setTransparency(tmp);
            calendar.getSunday().setTransparency(tmp);
            calendar.getToday().setTransparency(tmp);
        }
    }

    protected void initChart() {
        super.initChart();
        Panel tmpPanel = chart1.getPanel();
        tmpPanel.getGradient().setEndColor(Color.NAVY);
        tmpPanel.getGradient().setMiddleColor(Color.SILVER);
        tmpPanel.getGradient().setStartColor(Color.GRAY);
        tmpPanel.getGradient().setDirection(GradientDirection.VERTICAL);
        tmpPanel.getGradient().setUseMiddle(true);
        tmpPanel.getGradient().setVisible(true);
        tmpPanel.setMarginTop(5.0);
        chart1.getHeader().setVisible(false);
    }

    protected void initCalendar() {
        CalendarCell tmpCell;

        calendar = new Calendar(chart1.getChart());
        calendar.getPen().setStyle(DashStyle.DOT);
        calendar.getPen().setWidth(3);
        calendar.setShowInLegend(false);

        tmpCell = calendar.getDays();
        tmpCell.setColor(Color.LIGHT_YELLOW);
        tmpCell.getFont().setColor(Color.BLACK);
        tmpCell.setTransparent(false);
        tmpCell.setVisible(true);
        tmpCell.getPen().setVisible(false);

        tmpCell = calendar.getMonths();
        tmpCell.getFont().setColor(Color.NAVY);
        tmpCell.getFont().setSize(16);
        tmpCell.getFont().setBold(true);
        tmpCell.getFont().setItalic(true);
        tmpCell.setTransparent(true);
        tmpCell.setVisible(true);

        tmpCell = calendar.getSunday();
        tmpCell.setColor(Color.RED);
        tmpCell.getFont().setColor(Color.WHITE);
        tmpCell.setTransparent(false);
        tmpCell.setVisible(true);

        tmpCell = calendar.getToday();
        tmpCell.setColor(Color.BLUE);
        tmpCell.getFont().setColor(Color.NAVY);
        tmpCell.setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        tmpCell.getFont().setSize(19);
        tmpCell.getFont().setBold(true);
        tmpCell.getFont().getShadow().setHorizSize(1);
        tmpCell.getFont().getShadow().setVertSize(1);
        tmpCell.getFont().getShadow().setVisible(true);
        tmpCell.setTransparent(false);
        tmpCell.getGradient().setStartColor(Color.BLUE);
        tmpCell.getGradient().setVisible(true);
        tmpCell.getGradient().setDirection(GradientDirection.HORIZONTAL);
        tmpCell.setVisible(true);

        tmpCell = calendar.getTrailing();
        tmpCell.getFont().setColor(Color.GRAY);
        tmpCell.setTransparent(true);
        tmpCell.setVisible(true);

        tmpCell = calendar.getWeekDays();
        tmpCell.setBevelWidth(1);
        tmpCell.setColor(Color.SILVER);
        tmpCell.getFont().setColor(Color.NAVY);
        tmpCell.getGradient().setStartColor(Color.GRAY);
        tmpCell.getGradient().setEndColor(Color.SILVER);
        tmpCell.getGradient().setStartColor(Color.WHITE);
        tmpCell.getGradient().setDirection(GradientDirection.VERTICAL);
        tmpCell.getGradient().setUseMiddle(true);
        tmpCell.getGradient().setVisible(true);

        tmpCell.getShadow().setVisible(false);
        tmpCell.getPen().setVisible(false);
        tmpCell.setTransparent(false);
        tmpCell.setShapeStyle(TextShapeStyle.ROUNDRECTANGLE);
        tmpCell.setVisible(true);

        calendar.getPreviousMonthButton().setToolTipText("Previous Month");
        calendar.getNextMonthButton().setToolTipText("Next Month");
    }

    protected void initComponents() {
        super.initComponents();
        initCalendar();
        editButton = new JButton("Edit...");
        popupButton = new JButton("Popup!");
        showButtonsButton = new JCheckBox("Show buttons");
        showButtonsButton.setSelected(true);
        transparencyButton = new JCheckBox("Transparency");
        alignList = new JComboBox( new String[] {"Left", "Right", "Center"} );
    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(showButtonsButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpLabel = new JLabel("Align:");
            tmpLabel.setDisplayedMnemonic('A');
            tmpLabel.setLabelFor(alignList);
            tmpPane.add(tmpLabel);
            tmpPane.add(alignList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(popupButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(transparencyButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton, popupButton;
    private JCheckBox showButtonsButton, transparencyButton;
    private JComboBox alignList;
}
