/*
 * AnnotationClickDemo.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.tool.annotation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.steema.teechart.Cursor;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.Point;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.tools.Annotation;
import com.steema.teechart.tools.AnnotationPosition;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class AnnotationClickDemo extends ChartSample {

    private Annotation tool1, tool2, tool3;
    
	public AnnotationClickDemo(Composite c) {
		super(c);

		
        chart1.addMouseMoveListener( new MouseMoveListener() {
            public void mouseMove(MouseEvent e) {
            	Point point = new Point(e.x, e.y);
                if (tool1.clicked(point)) {
                    toolLabel.setText(tool1.getText());
                } else if (tool2.clicked(point)) {
                    toolLabel.setText(tool2.getText());
                } else if (tool3.clicked(point)) {
                    toolLabel.setText(tool3.getText());
                } else {
                    toolLabel.setText("    ");
                }
                toolLabel.getParent().layout();
            }
        });
	}

	protected void createContent() {
		super.createContent();	
        toolLabel = addLabel(SWT.LEFT, "  ");        
	}

	protected void initContent() {
		super.initContent();
	}

	protected void initChart() {
		super.initChart();
        chart1.getHeader().setVisible(true);
        chart1.setText("Annotation Tools\nClick and Cursor Example");
        chart1.getLegend().setVisible(false);

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
       
        /*TODO
        ToolMouseListener tmpListener = new ToolMouseListener() {
          public void toolClicked(MouseEvent e) {
              JOptionPane.showMessageDialog(null, ((Annotation)e.getSource()).getText());

              // disable chart zoom as we've handled this mouse click ourselves:
              chart1.getChart().setCancelMouse(true);
          }
        };        

        tool1.addToolMouseListener(tmpListener);
        tool2.addToolMouseListener(tmpListener);
        tool3.addToolMouseListener(tmpListener);
        */
	}   	
	
    private Label toolLabel;
}
