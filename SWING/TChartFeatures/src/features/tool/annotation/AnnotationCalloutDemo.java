/*
 * AnnotationCalloutDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.annotation;

import com.steema.teechart.Cursor;
import com.steema.teechart.TextShapePosition;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.DashStyle;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.PointerStyle;
import com.steema.teechart.styles.Points;
import com.steema.teechart.styles.Series;
import com.steema.teechart.tools.Annotation;
import com.steema.teechart.tools.AnnotationCallout;
import com.steema.teechart.misc.Utils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class AnnotationCalloutDemo extends ChartSamplePanel
    implements ActionListener, ItemListener {

    private Annotation tool1;
    private Points series1;
    private ButtonPen borderButton;

    /**
     * Creates a new instance of AnnotationCalloutDemo
     */
    public AnnotationCalloutDemo() {
        super();
        followMouseButton.addItemListener(this);
        editButton.addActionListener(this);
        borderButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == editButton) {
            ChartEditor.editTool(tool1);
        } else if (source instanceof ButtonPen) {
            ((ButtonPen)source).editPen();
        }
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);

        if (source == followMouseButton) {
            chart1.getHeader().setText("");
            if (isSelected) {
                chart1.setText("Move the mouse over points !");
                series1.setCursor(Cursor.DEFAULT);
            } else {
                chart1.setText("Click a point !");
                series1.setCursor(Cursor.HAND);
            }
        }
    }

    protected void initChart(){
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Move the mouse over points !");
        chart1.getLegend().setVisible(true);
        chart1.getAspect().setChart3DPercent(15);
        chart1.addMouseListener( new MouseAdapter() {
             public void mouseClicked(MouseEvent e) {
                 if(!followMouseButton.isSelected()) {
                     int index = series1.clicked(e.getX(), e.getY());
                     if(index != -1) {
                         setCallout(series1, index);
                     }
                 }
             }
        });

        chart1.addMouseMotionListener( new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                if(followMouseButton.isSelected()) {
                    int tmp = getNearestPoint(series1, e.getX(), e.getY());
                    if(tmp != -1) {
                        setCallout(series1, tmp);
                    }
                }
            }
        });
    }

    protected void initComponents() {
        super.initComponents();

        series1 = new com.steema.teechart.styles.Points(chart1.getChart());
        series1.fillSampleValues();
        series1.setColorEach(true);
        series1.getMarks().setVisible(false);
        series1.getPointer().setVisible(true);

        // force a first-time chart redrawing, to obtain valid
        // coordinates (Series1.CalcYPos, etc).
        chart1.refreshControl();

        tool1 = new Annotation(chart1.getChart());
        tool1.setText(" Move the mouse ! ");
        tool1.setLeft(160);
        tool1.setTop(35);
        tool1.getShape().setCustomPosition(true);
        AnnotationCallout tmpCallout = tool1.getCallout();
        tmpCallout.getBrush().setColor(Color.RED);
        tmpCallout.setHorizSize(6);
        tmpCallout.getPen().setColor(Color.LIME);
        tmpCallout.getPen().setWidth(2);
        tmpCallout.setStyle(PointerStyle.CIRCLE);
        tmpCallout.setVertSize(6);
        tmpCallout.setVisible(true);
        tmpCallout.getArrow().setColor(Color.BLUE);
        tmpCallout.getArrow().setStyle(DashStyle.DOT);
        tmpCallout.getArrow().setWidth(2);
        tmpCallout.getArrow().setVisible(true);
        tmpCallout.setXPosition(50);
        tmpCallout.setXPosition(90);

        TextShapePosition tmpShape = tool1.getShape();
        tmpShape.getGradient().setDirection(GradientDirection.HORIZONTAL);
        tmpShape.getGradient().setEndColor(Color.WHITE);
        tmpShape.getGradient().setStartColor(Color.SILVER);
        tmpShape.getShadow().setHorizSize(4);
        tmpShape.getShadow().setTransparency(75);
        tmpShape.getShadow().setVertSize(4);
        tmpShape.setLeft(160);
        tmpShape.setTop(35);

        borderButton = new ButtonPen(tool1.getCallout().getArrow(), "Border...");

        editButton = new JButton("Edit...");
        followMouseButton = new JCheckBox("Follow mouse");
        followMouseButton.setSelected(true);

        followMouseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (followMouseButton.isSelected()) {
                    chart1.setText("Move the mouse over points !");
                    series1.setCursor(Cursor.DEFAULT);
                } else {
                    chart1.setText("Click a point !");
                    series1.setCursor(Cursor.HAND);
                }
            }
        });
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(borderButton);
            tmpPane.add(Box.createHorizontalStrut(20));
            tmpPane.add(followMouseButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    /**
     * Returns Series point index that is nearest to xy position.
     */
    private int getNearestPoint(Series aSeries, int x, int y) {
        int tmpResult = -1;
        int difference = -1;
        int tmpDif, tmpX, tmpY;

        for (int t=0; t < aSeries.getCount(); t++) {

            tmpX = aSeries.calcXPos(t) - x;
            tmpY = aSeries.calcYPos(t) - y;
            tmpDif = Utils.round(Math.sqrt(Utils.sqr(tmpX) + Utils.sqr(tmpY)));

            if ((difference==-1) || (tmpDif<difference)) {
                difference = tmpDif;
                tmpResult = t;
            }
        }

        return tmpResult;
    }

    private void setCallout(Series aSeries, int aIndex) {
        // Change annotation text
        tool1.setText("Point: "+ aIndex +"\n"+
                      " Value: "+ aSeries.getValueMarkText(aIndex));

        // Re-position annotation callout
        AnnotationCallout tmpCallout = tool1.getCallout();
        tmpCallout.setVisible(true);
        tmpCallout.setXPosition(series1.calcXPos(aIndex));
        tmpCallout.setYPosition(series1.calcYPos(aIndex));
        tmpCallout.setZPosition(0);
    }

    private JButton editButton;
    private JCheckBox followMouseButton;
}
