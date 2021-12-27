/*
 * AnnotationClickDemo.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.tool.annotation;

import com.steema.teechart.Cursor;
import com.steema.teechart.events.FrameworkMouseEvent;
import com.steema.teechart.events.ToolMouseListener;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.Point;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.tools.Annotation;
import com.steema.teechart.tools.AnnotationPosition;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class AnnotationClickDemo extends ChartSamplePanel {

    private Annotation tool1, tool2, tool3;

    /**
     * Creates a new instance of AnnotationClickDemo
     */
    public AnnotationClickDemo() {
        super();

        chart1.addMouseMotionListener( new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
            	
            	java.awt.Point tmp=e.getPoint();
            	Point p=new Point(tmp.x, tmp.y);
            	
                if (tool1.clicked(p)) {
                    toolLabel.setText(tool1.getText());
                } else if (tool2.clicked(p)) {
                    toolLabel.setText(tool2.getText());
                } else if (tool3.clicked(p)) {
                    toolLabel.setText(tool3.getText());
                } else {
                    toolLabel.setText("    ");
                }
            }
        });

    }

    protected void initChart() {
        super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Annotation Tools\nClick and Cursor Example");
        chart1.getLegend().setVisible(false);
    }

    protected void initComponents() {
        super.initComponents();

        FastLine tmpSeries = new com.steema.teechart.styles.FastLine(chart1.getChart());
        tmpSeries.fillSampleValues(30);

        tool1 = new Annotation(chart1.getChart(), "ABCD");
        tool1.getShape().setColor(Color.WHITE);
        tool1.setPosition(AnnotationPosition.LEFTTOP);
        tool1.setCursor(Cursor.HAND);

        tool2 = new Annotation(chart1.getChart());
        tool2.setText("Hello\nWorld");
        tool2.setLeft(60);
        tool2.setTop(80);
        tool2.getShape().setCustomPosition(true);
        tool2.getShape().setColor(Color.YELLOW);
        tool2.getShape().getShadow().setHorizSize(1);
        tool2.getShape().getShadow().setVertSize(1);
        tool2.setCursor(Cursor.CROSS);

        tool3 = new Annotation(chart1.getChart());
        tool3.setText("1234567");
        tool3.setPosition(AnnotationPosition.RIGHTTOP);
        tool3.getShape().setColor(Color.ORANGE);
        tool3.getShape().getFont().setSize(16);
        tool3.getShape().getFont().setBold(true);
        tool3.setCursor(Cursor.HAND);

        ToolMouseListener tmpListener = new ToolMouseListener() {
          public void toolClicked(FrameworkMouseEvent e) {
              JOptionPane.showMessageDialog(null, ((Annotation)e.getSource()).getText());

              // disable chart zoom as we've handled this mouse click ourselves:
              chart1.getChart().setCancelMouse(true);
          }
        };

        tool1.addToolMouseListener(tmpListener);
        tool2.addToolMouseListener(tmpListener);
        tool3.addToolMouseListener(tmpListener);

        toolLabel = new JLabel("   ");
    }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(toolLabel);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JLabel toolLabel;
}
