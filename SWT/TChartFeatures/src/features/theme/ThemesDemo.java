package features.theme;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;

import com.steema.teechart.styles.Bar;
import com.steema.teechart.themes.DefaultTheme;
import com.steema.teechart.themes.ExcelTheme;
import com.steema.teechart.themes.ThemesList;

import features.ChartSample;
import features.WidgetFactory;

/**
*
* @author tom
*/
public class ThemesDemo extends ChartSample implements SelectionListener {  
    
	public ThemesDemo(Composite c) {
		super(c);
		
        themeList.addListener (SWT.Selection, new Listener () {
    		public void handleEvent (Event e) {
    			int index = themeList.getSelectionIndex();
    			if (index == -1) {
    	          ThemesList.applyTheme(chart1.getChart(), new DefaultTheme(chart1.getChart()));
    	        } else {
    	          ThemesList.applyTheme(chart1.getChart(), index);
    	        }
    		}
    	});		
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {
		widgetSelected(se);
	}

	public void widgetSelected(SelectionEvent se) {
		if (se.widget==codeButton) {
			ThemesList.applyTheme(chart1.getChart(), new ExcelTheme(chart1.getChart()));		    
		} else  if (se.widget==editButton) {
			//TODO
		}
	}	
		
    protected void createContent() {
    	super.createContent();
    	
    	GridLayout buttonLayout = new GridLayout(2, false);
    	getButtonPane().setLayout(buttonLayout);
    	
    	codeButton = WidgetFactory.createPushButton(
    			getButtonPane(), 
    			"Apply by code",
    			"Change the theme by code",
    			this
    	);
    	codeButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.TOP, true, false));
    	
    	editButton = WidgetFactory.createPushButton(
    			getButtonPane(), 
    			"Theme editor...",
    			"Open the theme editor dialog",
    			null
    	); 
    	editButton.setLayoutData(new GridData(SWT.END, SWT.TOP, false, false));    	
    	
    	exampleForm = new SashForm(getSamplePane().getParent(), SWT.HORIZONTAL);
    	exampleForm.setLayout(new FillLayout());
    	exampleForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    	
    	themeList = new List (exampleForm, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
    	    	
    	getSamplePane().setParent(exampleForm);    	    
    }
    
    protected void initContent() {
    	super.initContent();
    	
    	editButton.setEnabled(false);
    	
    	for (int t=0; t < ThemesList.size(); t++) {
            themeList.add(ThemesList.getThemeDescription(t));
        }    	
        
        exampleForm.setWeights(new int[] {150,200});                
    }
    
    protected void initChart() {
    	super.initChart();
        Bar series = new Bar(chart1.getChart());
        series.fillSampleValues();
        series.setColorEach(true);    	    
    }   
    
	private Button codeButton;
    private Button editButton;
	private List themeList;
	
    private SashForm exampleForm;    
}
