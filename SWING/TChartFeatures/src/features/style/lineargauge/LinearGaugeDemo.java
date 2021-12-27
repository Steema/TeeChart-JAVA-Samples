/*
 * CircularGaugeDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.lineargauge;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.editors.ButtonPen;
import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.editors.series.LinearGaugeEditor;
import com.steema.teechart.styles.LinearGauge;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import java.awt.event.WindowListener;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author tom
 */
public class LinearGaugeDemo extends ChartSamplePanel
    implements ActionListener,WindowListener {

    private LinearGauge series,series2;
    private JButton dialog,dialog2;
    private Action openAction,openAction2;
    

    /** Creates a new instance of WaterfallDemo */
    public LinearGaugeDemo() {
        super();
    }


    protected void initComponents() {
        super.initComponents();
        t= new Timer(100, this);
        t.addActionListener(this);
        t.start();
        series = new LinearGauge(chart1.getChart());
        series2 = new LinearGauge(chart1.getChart());
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Animate Linear Gauge 1");
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox2.setSelected(true);
        jCheckBox2.setText("Animate Linear Gauge 2");
        jCheckBox2.addActionListener(this);
        openAction = new OpenAction("Top Linear Gauge Editor");
        openAction2 = new OpenAction("Bottom Linear Gauge Editor");
        dialog = new JButton(openAction);
        dialog2 = new JButton(openAction2);
        window.addWindowListener(this);
        window2.addWindowListener(this);
      
      }

    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
               tmpPane.add(jCheckBox1);
               tmpPane.add(dialog);
               tmpPane.add(jCheckBox2);
               tmpPane.add(dialog2);
               
        }
    }
    
public void actionPerformed(ActionEvent e) {
          Object aux = e.getSource();
          if(aux == jCheckBox1 || aux == jCheckBox2)
          {
            if(jCheckBox1.isSelected() || jCheckBox2.isSelected() )
            {
             t.start();
            }
              else if(!jCheckBox1.isSelected() && !jCheckBox2.isSelected())
            {
                  t.stop();
            }
          }
          else if(aux==t)
            {
              if(jCheckBox1.isSelected() && jCheckBox2.isSelected())
              {
              Random rnd = new Random();
              double value2 = rnd.nextDouble()*100;
                 
                  series.setValue(value2);
                     
                  while(value2==series.getValue())
                  {
                      value2 = rnd.nextDouble()*100;
                  }
                    series2.setValue(value2);
                
              }
              else if(jCheckBox1.isSelected() && !jCheckBox2.isSelected())
              {
              Random rnd = new Random();
              double value2 = rnd.nextDouble()*100;
                        series.setValue(value2);
            
        
              }
              else if(!jCheckBox1.isSelected() && jCheckBox2.isSelected())
              {
                     Random rnd = new Random();
                     double value2 = rnd.nextDouble()*100;
                    series2.setValue(value2);
              
             }
          }
          
    }
    
    	final public class OpenAction extends AbstractAction {

		public OpenAction(String text) {
                    super(text);
		}

		public void actionPerformed(ActionEvent e) {
                    Object obj = e.getSource();
                    if(obj == dialog)
                   {
                     if(!b)
                      {
                        LinearGaugeEditor dialog = new LinearGaugeEditor(series);
                        window.add(dialog);
                        window.setVisible(true);
                        window.setSize(347,217);
                        b= true;
                     }
                   }
                   else if(obj == dialog2)
                   {
                     if(!b2)
                      {
                        LinearGaugeEditor dialog2 = new LinearGaugeEditor(series2);
                        window2.add(dialog2);
                        window2.setVisible(true);
                        window2.setSize(347,217);
                        b2= true;
                     }
                   }
                }
	}
    private JButton editButton;
    private javax.swing.JCheckBox jCheckBox1,jCheckBox2;
    private static JFrame window=new JFrame(),window2=new JFrame();
    private Boolean b=false,b2=false;
    private   Timer t;

    public void windowOpened(WindowEvent e) {
        
    }

    public void windowClosing(WindowEvent e) {
        Object obj = e.getSource();
        if(obj == window)
        b=false;
        else if(obj==window2) b2=false;
    }

    public void windowClosed(WindowEvent e) {
         Object obj = e.getSource();
        if(obj == window)
        b=false;
        else if(obj==window2) b2=false;
    }

    public void windowIconified(WindowEvent e) {
        
    }

    public void windowDeiconified(WindowEvent e) {
       
    }

    public void windowActivated(WindowEvent e) {
        
    }

    public void windowDeactivated(WindowEvent e) {
         
    }
}
