/**
 * 
 */
package features;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.steema.teechart.Commander;

/**
 * A sample panel with a TChart 'chart1' & 'commander' instance
 * @author tom
 *
 */
public class CommandSample extends ChartSample {
	protected Commander commander;

	public CommandSample(Composite c) {
		super(c);
	}
	
    public void setCommanderOrientation(boolean vertical) {
        if (vertical) {
            //myCommander.setOrientation(SWT.VERTICAL);
            //samplePane.add(myCommander, BorderLayout.LINE_START);
        } else {
            //myCommander.setOrientation(SWT.HORIZONTAL);
            //samplePane.add(myCommander, BorderLayout.PAGE_START);
        }
        samplePane.redraw();
    }

    protected void createCommander() {
        commander = new Commander(chart1.getChart(), samplePane, SWT.BORDER);
        commander.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
    }
    
    protected void initContent() {
        super.initContent();
        commander.setVisible(true);
    }

    protected void createContent() {
        super.createContent();
        samplePane = new Composite(getSamplePane().getParent(), SWT.NONE);        
    	GridLayout gridLayout = new GridLayout(1, true);       	
    	samplePane.setLayout(gridLayout);
    	samplePane.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    	createCommander();       
    	getSamplePane().setParent(samplePane);   
    	getSamplePane().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
    }

    private Composite samplePane;

    public void setLabelText(boolean show) {
        commander.setLabelValues(show);
    }	

}
