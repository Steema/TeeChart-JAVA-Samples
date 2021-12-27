
package features.tool.drawline;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.drawing.Color;
import com.steema.teechart.events.ChangeEvent;
import com.steema.teechart.styles.FastLine;
import com.steema.teechart.tools.DrawLine;

import features.ChartSample;
import features.WidgetFactory;
import features.utils.EnumStrings;

/**
 * @author tom
 *
 */
public class DrawLineDemo extends ChartSample implements SelectionListener {

    private DrawLine tool;
    //TODO private ButtonPen penButton;    
    
	public DrawLineDemo(Composite c) {
		super(c);
	}
	
	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;				
        if (source == mouseButtonList) {
            int tmpValue = 1;
            switch (mouseButtonList.getSelectionIndex()) {
                case 0: tmpValue = 1; break;
                case 1: tmpValue = 3; break;
                case 2: tmpValue = 2; break;
            }
            tool.setButton(tmpValue);
        } else if (source == deleteButton) {
            tool.deleteSelected();
            deleteButton.setEnabled(false);
            countLinesLabel.setText(Integer.toString(tool.getLines().size()));
/*        } else if (source == penButton) {
            chart1.repaint();*/
        } else {
        	boolean isSelected = ((Button)source).getSelection();
            if (source == activeButton) {
                tool.setActive(isSelected);
            } else if (source == enableDrawButton) {
                tool.setEnableDraw(isSelected);
            } else if (source == enableSelectButton) {
                tool.setEnableSelect(isSelected);
            }        	
        }
	}
	
	protected void createContent() {
		super.createContent();	

		Composite vComposite1 = new Composite(getButtonPane(), SWT.NONE);
		vComposite1.setLayout(new FillLayout(SWT.VERTICAL));
		activeButton = WidgetFactory.createCheckButton(vComposite1, "Active", "", this);
		enableDrawButton = WidgetFactory.createCheckButton(vComposite1, "Enable Draw", "", this);
		enableSelectButton = WidgetFactory.createCheckButton(vComposite1, "Enable Selection", "", this);
		
		Composite vComposite2 = new Composite(getButtonPane(), SWT.NONE);
		vComposite2.setLayout(new FillLayout(SWT.VERTICAL));
        //TODO penButton = new ButtonPen( tool.getPen() );		
		deleteButton = WidgetFactory.createPushButton(vComposite2, "Delete", "", this);
		new Label(vComposite2, SWT.LEFT).setText("Lines: ");		
        countLinesLabel = WidgetFactory.createLabel(vComposite2, SWT.LEFT, "0");

		Composite vComposite3 = new Composite(getButtonPane(), SWT.NONE);
		vComposite3.setLayout(new FillLayout(SWT.VERTICAL));
		new Label(vComposite3, SWT.LEFT).setText("Mouse button: ");
		mouseButtonList = new Combo(vComposite3, SWT.READ_ONLY | SWT.SINGLE | SWT.BORDER);
	}

	protected void initContent() {
		super.initContent();
		deleteButton.setEnabled(false); 
		activeButton.setSelection(true);
		enableDrawButton.setSelection(true);
		enableSelectButton.setSelection(true);
        mouseButtonList.setItems(EnumStrings.MOUSE_BUTTONS);		
        mouseButtonList.select(0);		
	}

	protected void initChart() {
		super.initChart();
        chart1.getAspect().setView3D(false);
        
        FastLine tmpLine = new com.steema.teechart.styles.FastLine(chart1.getChart());
        tmpLine.fillSampleValues(20);

        tool = new com.steema.teechart.tools.DrawLine(chart1.getChart());
        tool.getPen().setColor(Color.BLUE);
        tool.addDrawLineListener( tool.new DrawLineAdapter() {
             public void lineNew(ChangeEvent e) {
                 countLinesLabel.setText(Integer.toString(tool.getLines().size()));
             };
             public void lineSelected(ChangeEvent e) {
                 deleteButton.setEnabled(true);
             };
        });        
	} 	

    private Button deleteButton;
    private Button activeButton, enableDrawButton, enableSelectButton;
    private Label countLinesLabel;
    private Combo mouseButtonList;
}
