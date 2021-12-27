/*
 * BasicComposite.java
 *
 * <p>Copyright: (c) 2005-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

/**
*
* @author tom
*/
public class BaseComposite extends Composite {
    private final static String SAMPLE_DESCRIPTION_EXTENSION = ".html";
    protected Browser sampleDescription;
    protected Description description;

    /** Creates a new instance of BaseComposite */
    public BaseComposite(Composite c) {
        super(c, SWT.BORDER);
        description = new Description(getClass());
    	setLayout(new FillLayout());
        createContent();
        initContent();
    }    
    
    public Browser getSampleDescription() {
        return sampleDescription;
    }
    
    public Composite createSampleDescription(Composite parent) {    	      
        try {
            final Color bgColor = new Color(parent.getDisplay(), 215, 215, 215);
        	sampleDescription = new Browser(parent, SWT.NONE);
        	sampleDescription.setBackground(bgColor);      
        
        	if (description.hasContent()) { 
        		//sampleDescription.setUrl(description.url.toString());
        		sampleDescription.setText(description.toString()); 
        	};   
        
	        addDisposeListener(new DisposeListener() {
				public void widgetDisposed(DisposeEvent e) {
					bgColor.dispose();
				}
	    	});
        }
	    catch (SWTException e) {
	    	System.out.println(e.getMessage());
	    	return null;
	    }
        return sampleDescription;
    }
    
    /**
     * Creates the UI components.
     */    
    protected void createContent() {
        createSampleDescription(this);
    }    
    
    /**
     * Initializes and configures the UI components.
     */    
    protected void initContent() {
    	    	
    }
    
    final class Description {
    	InputStream stream;
    	URL url;
    	
    	private String value;
   	
    	Description(Class cls) {
    		String s = "/" + cls.getName().replace('.', '/') +	SAMPLE_DESCRIPTION_EXTENSION;
    		
    		url = getClass().getResource(s);
    		
    		//workaround for setURL problem in jar
    		stream = getClass().getResourceAsStream(s);
    		if (stream != null) {
    			try {
    				StringBuffer content = new StringBuffer();
    				String line;

    				BufferedReader bf = new BufferedReader( new InputStreamReader(stream) );

	    			if( bf.ready()){ 
	    				while ((line = bf.readLine()) != null) { 
	    					content.append(line);
	    				} 
	    			}
	    			
	    			value = content.toString();
	
	    		} catch (Exception e) {
	    			value = "Error loading description "+ e.getMessage();	
	    		}
    		}
    	}
    	
    	public String toString() {
    		return value;
    	}
    	
    	public boolean hasContent() {
    		return stream != null;
    	}
    }    
}
