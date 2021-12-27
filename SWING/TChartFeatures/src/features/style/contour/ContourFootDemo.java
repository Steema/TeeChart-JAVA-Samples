/*
 * ContourFootDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.style.contour;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.ColorGrid;
import com.steema.teechart.styles.Contour;
import com.steema.teechart.styles.ContourConstruction;
import com.steema.teechart.styles.Custom3DGrid;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 *
 * @author narcis
 */
public class ContourFootDemo extends ChartSamplePanel
        implements ActionListener {

    private Custom3DGrid series;
    private JRadioButton contourCheck, colorGridCheck;
    private Color[] ColorPalette;

    // Parameters for binary data
    private int BIGPADWIDTH;
    private int BIGPADLENGTH;
    private byte[][] FootScanArray;
    
    /** Creates a new instance of ContourFootDemo */
    public ContourFootDemo() {
        super();
        contourCheck.addActionListener(this);
        colorGridCheck.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        chart1.removeAllSeries();
        
        if (contourCheck.isSelected()) {
            series = new Contour(chart1.getChart());
            ((Contour)series).setDrawingalgorithm(ContourConstruction.FAST);
        } else if (colorGridCheck.isSelected()) {
            series = new ColorGrid(chart1.getChart());
        }
        
        AddDataToSeries(series);
        InitSeries(series);
    }

    protected void initComponents() {
        super.initComponents();

        contourCheck = new JRadioButton("Contour Series");
        contourCheck.setSelected(true);
        colorGridCheck = new JRadioButton("ColorGrid Series");        
        
        ButtonGroup group = new ButtonGroup();
        group.add(contourCheck);
        group.add(colorGridCheck);
    }

    protected void initGUI() {
        super.initGUI();
        chart1.getAspect().setView3D(false);
        chart1.getAspect().setSmoothingMode(true);
        chart1.getWalls().getBottom().setTransparent(true);
        chart1.getAxes().getDepth().setVisible(true);
        chart1.getAxes().getLeft().setVisible(false);
        
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Show as:");
            tmpLabel.setDisplayedMnemonic('S');
            tmpPane.add(tmpLabel);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(contourCheck);
            tmpPane.add(colorGridCheck);
            tmpPane.add(Box.createHorizontalGlue());
        }
        
        InitArrays();
        LoadData("FootData.bin");        
        actionPerformed(null);
    }

    private void InitArrays() {
        ColorPalette = new Color[] {Color.fromArgb(0x00,0x00,0x00),
            Color.fromArgb(0xFF,0x00,0x00),
            Color.fromArgb(0xFF,0x80,0x00),
            Color.fromArgb(0xFF,0xC0,0x00),
            Color.fromArgb(0xFF,0xFF,0x00),
            Color.fromArgb(0xC0,0xFF,0x00),
            Color.fromArgb(0x80,0xFF,0x00),
            Color.fromArgb(0x00,0xFF,0x00),
            Color.fromArgb(0x00,0xFF,0x80),
            Color.fromArgb(0x00,0xFF,0xFF),
            Color.fromArgb(0x00,0xDF,0xFF),
            Color.fromArgb(0x00,0xC0,0xFF),
            Color.fromArgb(0x00,0xA0,0xFF),
            Color.fromArgb(0x00,0x80,0xFF),
            Color.fromArgb(0x00,0x60,0xFF),
            Color.fromArgb(0x00,0x00,0xFF)};
        
        BIGPADWIDTH = 64;
        BIGPADLENGTH = 96;
        FootScanArray = new byte[BIGPADLENGTH][BIGPADWIDTH];
    }
    
    private void LoadData(String FileName) {
        File f = new File(FileName);
        
        if(f.exists()) {
            FileInputStream fs = null;
            DataInputStream bin = null;
            try {
                fs = new FileInputStream(f);
                bin = new DataInputStream(fs);

                for (int len = 0; len < BIGPADLENGTH; ++len) {
                    for (int wid = 0; wid < BIGPADWIDTH; ++wid) {
                        FootScanArray[len][wid] = bin.readByte();
                    }
                }                
                
                fs.close();
                bin.close();
            } catch (IOException ex) {
                Logger.getLogger(ContourFootDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }			
    }    

    private void AddDataToSeries(Custom3DGrid ASeries) {	
        ASeries.clear();
        for(int len=0; len<BIGPADLENGTH; ++len) {
            for(int wid=0; wid<BIGPADWIDTH; ++wid) {                                        
                double tmp = (double)(FootScanArray[len][wid]);
                ASeries.add(wid+1, tmp, len+1);                
            }
        }
    }

    private void InitSeries(Custom3DGrid ASeries) {
        if (ASeries instanceof Contour)
        {
            ((Contour)ASeries).setNumLevels(ColorPalette.length);
            ((Contour)ASeries).createAutoLevels();
            ((Contour)ASeries).setAutomaticLevels(false);
            for (int i = 0; i < ColorPalette.length; i++) {
                ((Contour)ASeries).getLevels().getLevel(i).setUpToValue(i + 0.5);
                ((Contour)ASeries).getLevels().getLevel(i).setColor(ColorPalette[i]);
            }
        }
        ASeries.setColorEach(false);
        ASeries.setUsePalette(true);
        ASeries.setUseColorRange(false);
        ASeries.clearPalette();
        for (int i = 0; i < ColorPalette.length; i++)
            ASeries.addPalette(i + 0.5, ColorPalette[i]);

        ASeries.repaint();
    }
}
