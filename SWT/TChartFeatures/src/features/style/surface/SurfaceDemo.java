/*
 * SurfaceDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.style.surface;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.BevelStyle;
import com.steema.teechart.Wall;
import com.steema.teechart.drawing.Color;
import com.steema.teechart.drawing.Gradient;
import com.steema.teechart.drawing.GradientDirection;
import com.steema.teechart.misc.MathUtils;
import com.steema.teechart.styles.Custom3DGrid;
import com.steema.teechart.styles.ISeries;
import com.steema.teechart.styles.PaletteStyle;
import com.steema.teechart.styles.Surface;

import features.ChartSample;

/**
*
* @author tom
*/
public class SurfaceDemo extends ChartSample implements ModifyListener, SelectionListener {

    private Surface surfaceSeries;
    
	public SurfaceDemo(Composite c) {
		super(c);
		sampleSpinner.addModifyListener(this);
		colorModeList.addSelectionListener(this);
		frameList.addSelectionListener(this);
	}	
	
	public void modifyText(ModifyEvent me) {
		Widget source = me.widget;
		if (source == sampleSpinner) {
			 surfaceSeries.reCreateValues();    		
		}
	}	
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;		
        if (source == colorModeList) {
            switch (colorModeList.getSelectionIndex()) {
                case 0: {
                    surfaceSeries.setUseColorRange(false);
                    surfaceSeries.setUsePalette(false);
                    break;
                }
                case 1: {
                    surfaceSeries.setUseColorRange(true);
                    surfaceSeries.setUsePalette(false);
                    break;
                }
                case 2: {
                    surfaceSeries.setUseColorRange(false);
                    surfaceSeries.setUsePalette(true);
                    surfaceSeries.setPaletteStyle(PaletteStyle.PALE);
                    break;
                }
                case 3: {
                    surfaceSeries.setUseColorRange(false);
                    surfaceSeries.setUsePalette(true);
                    surfaceSeries.setPaletteStyle(PaletteStyle.STRONG);
                    break;
                }
                case 4: {
                    surfaceSeries.setUseColorRange(false);
                    surfaceSeries.setUsePalette(true);
                    surfaceSeries.setPaletteStyle(PaletteStyle.GRAYSCALE);
                    break;
                }
                case 5: {
                    surfaceSeries.setUseColorRange(false);
                    surfaceSeries.setUsePalette(true);
                    surfaceSeries.setPaletteStyle(PaletteStyle.INVERTED_GRAYSCALE);
                    break;
                }
                case 6: {
                    surfaceSeries.setUseColorRange(false);
                    surfaceSeries.setUsePalette(true);
                    surfaceSeries.setPaletteStyle(PaletteStyle.RAINBOW);
                    break;
                }
            }
        } else if (source == frameList) {
            switch (frameList.getSelectionIndex()) {
                case 0: {
                    surfaceSeries.setWireFrame(false);
                    surfaceSeries.setDotFrame(false);
                    surfaceSeries.getPen().setVisible(true);
                    break;
                }
                case 1: {
                    surfaceSeries.setWireFrame(false);
                    surfaceSeries.setDotFrame(false);
                    surfaceSeries.getPen().setVisible(false);
                    break;
                }
                case 2: {
                    surfaceSeries.setWireFrame(true);
                    surfaceSeries.getPen().setVisible(true);
                    break;
                }
                case 3: {
                    surfaceSeries.setDotFrame(true);
                    break;
                }
            }
        } else if (source instanceof Button) {
        	boolean isSelected = ((Button)source).getSelection();
        	if (source == animateButton) {       	
	            if (isSelected) {
	            	getDisplay().timerExec(ONE_MILLISECOND, timer);
	            } else {
	            	getDisplay().timerExec(-1, timer);
	            }
        	}
        };
	}
	
	protected void createContent() {
		super.createContent();	

        animateButton = addCheckButton("Animate", "", this);
        addLabel(SWT.LEFT, "Style: ");
        colorModeList = addCombo(SWT.DROP_DOWN | SWT.READ_ONLY);
        frameList = addCombo(SWT.DROP_DOWN | SWT.READ_ONLY);
        
        addLabel(SWT.LEFT, "Sample: ");
        sampleSpinner = new Spinner(getButtonPane(), SWT.READ_ONLY);
        
        //Create a timer.
        timer = new Runnable() {
            public void run() {
                Random generator = new Random();
                /* Invert Left axis randomly */
                if (generator.nextInt(100)<2) {
                    chart1.getAxes().getLeft().setInverted(chart1.getAxes().getLeft().getInverted());
                }

                /* Invert Bottom axis randomly */
                if (generator.nextInt(100)<2) {
                    chart1.getAxes().getBottom().setInverted(chart1.getAxes().getBottom().getInverted());
                }

                /* Invert Depth axis randomly */
                if (generator.nextInt(100)<2) {
                    chart1.getAxes().getDepth().setInverted(chart1.getAxes().getDepth().getInverted());
                }

                /* Change Color Mode(Single, Range, Palette or Grayscale) randomly */
                if (generator.nextInt(100)<2) {
                    if (colorModeList.getSelectionIndex() < colorModeList.getItemCount()-1) {
                        colorModeList.select(colorModeList.getSelectionIndex() + 1);
                    } else {
                        colorModeList.select(0);
                    }
                }

                /* Change Chart Gradient Colors randomly(only at 16k colors or greater) */
                if (generator.nextInt(100)<10) {
                    chart1.getPanel().getGradient().setStartColor(Color.random());
                } else {
                    if (generator.nextInt(100)<10) {
                        chart1.getPanel().getGradient().setEndColor(Color.random());
                    }
                }

                /* Change Chart Gradient direction randomly */
                if (generator.nextInt(100)<5) {
                    int tmpValue = generator.nextInt(GradientDirection.size());
                    chart1.getPanel().getGradient().setDirection(GradientDirection.atIndex(tmpValue));
                }

                /* Random change Surface and Chart colors */
                switch (colorModeList.getSelectionIndex()) {
                    case 0: { /* single color */
                        if (generator.nextInt(100)<15) {
                            surfaceSeries.setColor(Color.random());
                        }
                        break;
                    }
                    case 1: { /* color range */
                        if (generator.nextInt(100)<15) {
                            surfaceSeries.setStartColor(Color.random());
                        } else {
                            if (generator.nextInt(100)<15) {
                                surfaceSeries.setEndColor(Color.random());
                            }
                        }
                    }
                }
                /* random change pen color */
                if (generator.nextInt(100)<15) {
                    surfaceSeries.getPen().setColor(Color.random());
                }
                
                /* Change Surface Example: */
                if (sampleSpinner.getSelection() != sampleSpinner.getMaximum()) {
                    sampleSpinner.setSelection(sampleSpinner.getSelection()+sampleSpinner.getIncrement());
                } else {
                    sampleSpinner.setSelection(sampleSpinner.getMinimum());
                }

              getDisplay().timerExec(ONE_MILLISECOND, this);
            }
          };
	}

	protected void initContent() {
		super.initContent();
        animateButton.setSelection(false);
        colorModeList.setItems(new String[] {
        		"Single Color",
                "Color Range",
                "Color Palette",
                "Strong Palette",
                "Grayscale",
                "Inverted Gray",
                "Rainbow"});
        colorModeList.select(0);    	
    	frameList.setItems(new String[] {"Solid and Grid", "Solid", "Wireframe", "Dots"});
    	frameList.select(0);
    	sampleSpinner.setMaximum(8);
    	sampleSpinner.setMinimum(1);
    	sampleSpinner.setIncrement(1);
    	sampleSpinner.setSelection(1); 
    	
        surfaceSeries.reCreateValues();    	
	}

	protected void initChart() {
		super.initChart();
        Wall tmpWall;
        tmpWall = chart1.getWalls().getBack();
        tmpWall.getBrush().setColor(Color.WHITE);
        tmpWall.setColor(Color.LIME); //BackWall.Color = 8453888
        tmpWall.setSize(10);
        tmpWall.setTransparent(false);

        tmpWall = chart1.getWalls().getBottom();
        tmpWall.getBrush().setColor(Color.WHITE);
        tmpWall.getBrush().setVisible(false);
        tmpWall.setSize(10);

        chart1.getWalls().getLeft().setSize(10);

        Gradient tmpGradient = chart1.getPanel().getGradient();
        tmpGradient.setEndColor(Color.WHITE);
        tmpGradient.setMiddleColor(Color.YELLOW);
        tmpGradient.setStartColor(Color.GRAY);
        tmpGradient.setVisible(true);

        chart1.getLegend().setVisible(false);

        chart1.getPanel().setMarginBottom(5);
        chart1.getPanel().setMarginLeft(5);
        chart1.getPanel().setMarginRight(5);
        chart1.getPanel().setMarginTop(5);
        chart1.getPanel().setBevelInner(BevelStyle.LOWERED);
        chart1.getPanel().setBevelWidth(5);

        chart1.getHeader().setVisible(true);
        chart1.setText("Surface");

        chart1.getAxes().getBottom().getGrid().setColor(Color.SILVER);
        chart1.getAxes().getLeft().getGrid().setColor(Color.SILVER);
        chart1.getAxes().getRight().getGrid().setColor(Color.SILVER);
        chart1.getAxes().getTop().getGrid().setColor(Color.SILVER);
        chart1.getAxes().getDepth().setVisible(true);

        //TODO chart1.setClipPoints(false);
        chart1.getAspect().setChart3DPercent(90);
        chart1.getAspect().setElevation(348);
        chart1.getAspect().setOrthogonal(false);
        chart1.getAspect().setPerspective(100);
        chart1.getAspect().setRotation(329);
        chart1.getAspect().setVertOffset(-20);
        chart1.getAspect().setZoom(60);
        
        initSeries();
        surfaceSeries.reCreateValues();    
	}   	
    
    protected void initSeries() {
        surfaceSeries = new com.steema.teechart.styles.Surface(chart1.getChart());
        surfaceSeries.setPaletteStyle(PaletteStyle.STRONG);
        surfaceSeries.getSideBrush().setColor(Color.WHITE);
        surfaceSeries.getSideBrush().setStyle(null);
        surfaceSeries.setUseColorRange(false);
        surfaceSeries.setUsePalette(true);

        class GetYValue implements Custom3DGrid.YCalculator {
            public double calculate(ISeries sender, int x, int z) {
                double tmpY = 0;
                Surface s = (Surface)sender;
                int n = s.getNumXValues();
                double portianPI = Math.PI / s.getNumXValues();
                double halfPI = Math.PI * 0.5;
                double tmpX = x*portianPI;
                double tmpZ = z*portianPI;
                int sample = sampleSpinner.getSelection();
                //sample surfaces
                switch (sample) {
                    case 1: {
                        tmpY = 0.5*MathUtils.sqr(Math.cos(x/(n*0.2)))
                        + MathUtils.sqr(Math.cos(z/(n*0.2)))
                        - Math.cos(z/(n*0.5));
                        break;
                    }
                    case 2: {
                        tmpY = MathUtils.sqr(Math.cos(tmpX)) * MathUtils.sqr(Math.sin(tmpZ));
                        break;
                    }
                    case 3: {
                        tmpY = Math.cos(tmpX*tmpX)+Math.sin(tmpZ*tmpZ);
                        break;
                    }
                    case 4: {
                        tmpY = MathUtils.sqr(Math.cos(tmpX))+MathUtils.sqr(Math.sin(tmpZ));
                        break;
                    }
                    case 5: {
                        tmpY = -tmpX+MathUtils.sqr(tmpZ)*Math.sin(tmpX*tmpZ);
                        break;
                    }
                    case 6 : {
                        tmpY = Math.sqrt(tmpX*tmpX+tmpZ*tmpZ);
                        break;
                    }
                    case 7 : {
                        tmpY = Math.cos(Math.abs(tmpX-halfPI))*Math.sin(tmpZ);
                        break;
                    }
                    case 8 : {
                        tmpY = Math.cos(Math.abs(tmpX-halfPI)*Math.abs(tmpZ-halfPI));
                        break;
                    }
                }
                return tmpY;
            }
        }
        surfaceSeries.setYCalculator(new GetYValue());
    }
    
    private Button animateButton;
    private Combo colorModeList, frameList;
    private Spinner sampleSpinner;
    
    private Runnable timer;
    private final static int ONE_MILLISECOND = 1;	
}
