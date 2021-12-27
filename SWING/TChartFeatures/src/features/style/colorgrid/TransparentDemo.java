/*
 * TransparentDemo.java
 *
 * <p>Copyright: Copyright (c)

 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.colorgrid;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.ColorGrid;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import features.ChartSamplePanel;

/**
 *
 * @author tom
 */
public class TransparentDemo extends ChartSamplePanel
    implements ActionListener, ChangeListener, ItemListener {

    private ColorGrid series;

    /** Creates a new instance of TransparentDemo */
    public TransparentDemo() {
        super();
        randomButton.addActionListener(this);
        transparentButton.addItemListener(this);
        levelSpinner.addChangeListener(this);
    }

     public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == randomButton) {
            /* make 10 cells transparent... */
            int x, z;
            Random generator = new Random();
            for (int t=1; t <=10; t++) {
                x = 1 + generator.nextInt(series.getNumXValues());
                z = 1 + generator.nextInt(series.getNumZValues());
                series.setNull(series.getIndex(x,z));
            }
            series.repaint();
        };
    }

    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        boolean isSelected = (e.getStateChange() == ItemEvent.SELECTED);
        if (source == transparentButton) {
            levelSpinner.setEnabled(isSelected);

            //Series1.Bitmap.Transparent:=CheckBox1.Checked;

            //Series1.CreateRangePalette;

            if (isSelected) {
                setTransparentColors();
            }
            series.repaint();
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if (source == levelSpinner) {
            setTransparentColors();
            series.repaint();
        }
    }

    protected void initComponents() {
        super.initComponents();

        series = new ColorGrid(chart1.getChart());
        series.getPen().setVisible(false);
        series.setPaletteSteps(MAX_LEVEL);
        series.setCenteredPoints(true);
        series.fillSampleValues(100);

        randomButton = new JButton("Make some random cells transparent");
        transparentButton = new JCheckBox("Transparent");
        transparentButton.setSelected(true);
        levelSpinner = new JSpinner(
                new SpinnerNumberModel(
                1,
                1,
                MAX_LEVEL,
                1)
                );
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getWalls().getBack().setTransparent(false);
        chart1.getLegend().setVisible(false);
        chart1.getAspect().setView3D(false);

        JPanel tmpPane = getButtonPane();

        {
            tmpPane.add(transparentButton);
            tmpPane.add(Box.createHorizontalStrut(10));
            JLabel tmpLabel = new JLabel("Levels: ");
            tmpLabel.setDisplayedMnemonic('L');
            tmpLabel.setLabelFor(levelSpinner);
            tmpPane.add(tmpLabel);
            tmpPane.add(levelSpinner);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(randomButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private void setTransparentColors() {
        /* Set colorgrid bitmap to transparent */

        /*
         series.getImage().  Transparent:=False;
        Series1.Bitmap.Transparent:=True;
        */
        /* re-create the palette */
        //series.cr  CreateRangePalette;

        /* set levels to transparent color (white in this example)  */
        int level = ((SpinnerNumberModel)levelSpinner.getModel()).getNumber().intValue();
        for (int t=0; t < level; t++) {
            series.getPalette().getPalette(t).color = Color.TRANSPARENT;
        }
    }

    private JButton randomButton;
    private JCheckBox transparentButton;
    private JSpinner levelSpinner;
    private static final int MAX_LEVEL = 30;
}
