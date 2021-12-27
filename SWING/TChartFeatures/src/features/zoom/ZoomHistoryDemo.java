

package features.zoom;

import com.steema.teechart.styles.Line;
import com.steema.teechart.tools.GridBand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;


public class ZoomHistoryDemo extends ChartSamplePanel implements ActionListener
{
 
   public ZoomHistoryDemo()
   {
     super();
     CBHistory.addActionListener(this);
     }
	
   private JButton button1;
   private JLabel label1;
   private GridBand gridBand1;


   protected void initChart() 
   {
      super.initChart();
      chart1.getLegend().setVisible(false);
         }


   protected void initComponents() 
   {
       
        super.initComponents();
        Line series = new Line(chart1.getChart());
        series.fillSampleValues(50);
        CBHistory = new JCheckBox();
        CBHistory.setSelected(false);
        chart1.getZoom().setHistory(false);
        CBHistory.setName("CBHistory");
        CBHistory.setSize(137, 17);
        CBHistory.setText("Historical unzoom steps");
      

    }
   


    protected void initGUI() {
        super.initGUI();
        JPanel tmpPane = getButtonPane();
        {
            tmpPane.add(Box.createHorizontalStrut(275));
            tmpPane.add(CBHistory);
                      
      
        }
    }
        JCheckBox CBHistory;

    public void actionPerformed(ActionEvent e) 
    {
        if(CBHistory.isSelected())
            chart1.getZoom().setHistory(true);
        else
            chart1.getZoom().setHistory(false);
    }


}


