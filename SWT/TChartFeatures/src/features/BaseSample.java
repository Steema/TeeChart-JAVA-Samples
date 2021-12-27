/*
 * BaseSample.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

/**
*
* @author tom
*/
public class BaseSample extends BaseComposite {

	/** Creates a new instance of BaseSample */
	public BaseSample(Composite c) {
		super(c);		
	}

	public Combo addCombo(int style) {
		return WidgetFactory.createCombo(buttonPane, style, null);
	}
	
	public Combo addCombo(int style, SelectionListener listener) {
		return WidgetFactory.createCombo(buttonPane, style, listener);
	}	
	
	public Button addCheckButton(String text, String toolTip, SelectionListener listener) {
		return WidgetFactory.createCheckButton(buttonPane, text, toolTip, listener);
	}

	public Button addPushButton(String text, String toolTip, SelectionListener listener) {
		return WidgetFactory.createPushButton(buttonPane, text, toolTip, listener);
	}
	
	public Button addRadioButton(String text, String toolTip, SelectionListener listener) {
		return WidgetFactory.createRadioButton(buttonPane, text, toolTip, listener);
	}
	
	public Label addLabel(int style, String text) {		
		return WidgetFactory.createLabel(buttonPane, style, text);
	}
	
	public Slider addSlider(int style, int minimum, int maximum, int selection) {
		return WidgetFactory.createSlider(buttonPane, style, minimum, maximum, selection);
	}

	public Slider addSlider(int style, int minimum, int maximum, int selection, SelectionListener listener) {
		return WidgetFactory.createSlider(buttonPane, style, minimum, maximum, selection, listener);
	}
	
	public Spinner addSpinner(int style, int minimum, int maximum, int increment) {
		return WidgetFactory.createSpinner(buttonPane, style, minimum, maximum, increment);
	}
	
	public Spinner addSpinner(int style, int minimum, int maximum, int increment, ModifyListener listener) {
		return WidgetFactory.createSpinner(buttonPane, style, minimum, maximum, increment, listener);
	}

	public Text addText(int style, String text) {
		return WidgetFactory.createText(buttonPane, style, text);
	}		
	
	public Text addText(int style, String text, ModifyListener listener) {
		return WidgetFactory.createText(buttonPane, style, text, listener);
	}		
	
    public Composite getButtonPane() {
        return buttonPane;
    }
    
    public Composite getExampleTabPage() {
    	return exampleTabPage;
    }

    public Composite getSamplePane() {
        return samplePane;
    }    
    
    public void hideButtonPane() {
        buttonPane.setVisible(false);
    }

    public void setSamplePane(Composite samplePane) {
        if (samplePane != null) {
           //TODO basePane.add(samplePane, BorderLayout.CENTER);
        }
    }

    public void setSampleText(String sampleText) {
        if (sampleText != null) {
          //  sampleDescription.setText(sampleText);
        }
    }	
    
	private Composite addTabPage(CTabFolder folder, String label) {
		CTabItem tab = new CTabItem(folder, SWT.NONE);
		tab.setText(label);
		Composite page = new Composite(folder, SWT.NONE);
		page.setLayout(new FillLayout());		
		tab.setControl(page);
		return page;
	}
	
    /**
     * Creates, initializes and configures the UI components.
     */
    protected void createContent() {
    	final CTabFolder folder = new CTabFolder(this, SWT.BOTTOM);
    	folder.setLayout(new FillLayout());
    	exampleTabPage = addTabPage(folder, "Example");
    	sourceTabPage = addTabPage(folder, "Source Code");
    	Color c = new Color(sourceTabPage.getDisplay(),255,255,155);
    	sourceTabPage.setBackground(c);

        createExampleTab(exampleTabPage);
        createSourceTab(sourceTabPage);
    	
        folder.setSelection(0);    	
        folder.addSelectionListener( new SelectionAdapter() {
        	public void widgetSelected(SelectionEvent se) {
        		if (se.widget == sourceTabPage.getParent()) {
        			Cursor cursor = getShell().getCursor();
        			getShell().setCursor(getDisplay().getSystemCursor(SWT.CURSOR_WAIT));
                    try {
                        loadSourceFile();
                    } finally {
                    	getShell().setCursor(cursor);
                    }        			
        		}        				
        	}
        });
        //containerPane = folder;        
    }

    private void createExampleTab(Composite parent) {
        Composite composite;

        if (description.hasContent()) {
        	SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
        	sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        	createSampleDescription(sashForm);
        	composite = new Composite(sashForm, SWT.NONE);
        	composite.setLayout(new FillLayout());
        	sashForm.setWeights(new int[] {60,200});
        } else {
        	composite = parent;
        }
    	GridLayout gridLayout = new GridLayout(1, true);       	
    	composite.setLayout(gridLayout);
        
        buttonPane = new Composite(composite, SWT.NONE);    	               
        buttonPane.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
    	buttonPane.setLayout(new RowLayout());    	
        
        samplePane = new Composite(composite, SWT.NONE);
        samplePane.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        samplePane.setLayout(new FillLayout());
    }

    private void createSourceTab(Composite parent) {
    	GridLayout gridLayout = new GridLayout(1, true);
    	gridLayout.marginWidth = 0;
        parent.setLayout(gridLayout);

    	pathPane = new Composite(parent, SWT.NONE);
    	pathPane.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
        {
            pathPane.setLayout(new RowLayout());

            Button configButton = new Button(pathPane, SWT.PUSH);
            configButton.setText("Configure...");
            configButton.addSelectionListener(new SelectionAdapter() {
        		public void widgetSelected(SelectionEvent se) {
                    String startDirectory = System.getProperty("user.dir");
                    if (Parameters.getSourcePath() != "") {
                        startDirectory = Parameters.getSourcePath();
                    }
                    DirectoryDialog dlg = new DirectoryDialog(getShell());
                    dlg.setFilterPath(startDirectory);
                    dlg.setText("Folder with TeeChart demo sources");
                    String dir = dlg.open();
                    if (dir != null) {
                    	Parameters.setSourcePath(dir);
                    	loadSourceFile();
                    }
        		};
            });
        }
        
        sourceStyledText = new StyledText(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
        sourceStyledText.setEditable(false);
        sourceStyledText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    }
    
    
    protected void loadSourceFile() {

/*  TODO  	
        // Prepare syntax highlighter
        EditorKit editorKit = new StyledEditorKit() {
            public Document createDefaultDocument() {
                return new JavaSyntaxDocument();
            }
        };

        // Set syntax highlighter
        sourceTextArea.setEditorKit(editorKit);
*/
        // Load resource to text editor pane
        File sourceFile = Parameters.getFilePath(getClass());
        File f = new File(System.getProperty("user.dir")+sourceFile.toString());
          if (f.isFile()) {
            try {
                loadSources(new FileReader(f));
                //sourceStyledText.setVisible(true);
                pathPane.setVisible(false);                
            } catch (IOException ioe) {
                System.out.println(
                        "importData: Unable to read from file " +
                        sourceFile.toString());
            }
        } 
        sourceStyledText.setVisible(!sourceStyledText.getText().equals(""));
    }

    private void loadSources(Reader input) throws IOException {

        BufferedReader in = new BufferedReader(input);

        String tmp = "";

        String str;
        while ((str = in.readLine()) != null) {
            tmp += (str + "\n");
        }

        in.close();

/*TODO        
        // Force syntax highlighting here:
        try {
            sourceTextArea.getDocument().insertString(0, tmp, null);
        } catch (BadLocationException ex) {
        }
*/
        // Without syntax highlighting:
        sourceStyledText.setText(tmp);

        sourceStyledText.setCaretOffset(0);
    }    

    private Composite buttonPane;
    private Composite samplePane;

    //private Composite containerPane;
    private Composite exampleTabPage;
    private StyledText sourceStyledText;
    //private Composite sourcePane;
    //private Composite basePane;
    private Composite pathPane;
    private Composite sourceTabPage;    
}
