/*
 * BasicFeatures.java
 *
 * <p>Copyright: (c) 2005-2008 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.styles.Series;

/**
*
* @author tom
*/
public class BasicFeatures extends ChartSample implements SelectionListener {
   
	/** Creates a new instance of BasicFeatures */
	public BasicFeatures(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) { }

	public void widgetSelected(SelectionEvent se) {
		if (se.widget==runButton) {
			chart1.removeAllSeries();
			
		    Series bar = new com.steema.teechart.styles.Bar(chart1.getChart());

		    bar.clear();

		    bar.add(123, "ABC", Color.RED);
		    bar.add(456, "DEF", Color.BLUE);
		    bar.add(321, "GHI", Color.GREEN);
		    
		} else  if (se.widget==showEditorButton) {
			//TODO
		}
	}	
		
    protected void createContent() {
    	super.createContent();
    	
    	GridLayout buttonLayout = new GridLayout(2, false);
    	getButtonPane().setLayout(buttonLayout);
    	
    	runButton = WidgetFactory.createPushButton(
    			getButtonPane(), 
    			"Run the code",
    			"Create the chart by code",
    			this
    	);
    	runButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.TOP, true, false));
    	
    	showEditorButton = WidgetFactory.createPushButton(
    			getButtonPane(), 
    			"Show the chart editor",
    			"Show the chart editor",
    			null
    	); 
    	showEditorButton.setLayoutData(new GridData(SWT.END, SWT.TOP, false, false));    	
    	
    	exampleForm = new SashForm(getSamplePane().getParent(), SWT.HORIZONTAL);
    	exampleForm.setLayout(new FillLayout());
    	exampleForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    	
    	codeArea = new Text(exampleForm, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.VERTICAL);    	
    	getSamplePane().setParent(exampleForm);    	    
    }
    
    protected void initContent() {
    	super.initContent();
    	
    	showEditorButton.setEnabled(false);
    	
    	codeArea.setText(
                "Run-time code to create charts:\n\n" +
                "chart1.removeAllSeries();\n\n" +
                "Series bar = new steema.teechart.styles.Bar(chart1.getChart());\n\n" +
                "bar.clear();\n" +
                "bar.add(123, \"ABC\", Color.RED);\n" +
                "bar.add(456, \"DEF\", Color.BLUE);\n" +
                "bar.add(321, \"GHI\", Color.GREEN);\n\n" +
                "Change the bar Marks :\n\n" +
                "bar.getMarks().setStyle(MarksStyle.VALUE);\n\n"
                   );

        codeArea.setEditable(false);

        Display device = exampleForm.getDisplay();
        final org.eclipse.swt.graphics.Color bgColor = new org.eclipse.swt.graphics.Color(device, 215, 215, 215);                   
        codeArea.setBackground(bgColor);
        final Font font = new Font(device, "Courier", 11, SWT.NONE);
        codeArea.setFont(font);
        
        exampleForm.setWeights(new int[] {150,200});
        
        addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				bgColor.dispose();
				font.dispose();
			}
    	}); 
    }
    
    protected void initChart() {
    	super.initChart();
    	chart1.getAxes().getBottom().setLabelsOnAxis(true);    	
    }
    
	private Button runButton;
    private Button showEditorButton;
    private Text codeArea;
    
    private SashForm exampleForm;        
}

