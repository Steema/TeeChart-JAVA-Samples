package features;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;


public class WidgetFactory {

	public static Button createButton(Composite parent, int arg, String text, String toolTip) {	
		Button button = new Button(parent, arg);
		button.setText(text);
		button.setToolTipText(toolTip);
		return button;
	}
	
	public static Button createButton(Composite parent, int arg, String text, String toolTip, SelectionListener listener) {		
		Button button = createButton(parent, arg, text, toolTip);
		if (listener != null) {
			button.addSelectionListener(listener);
		}
		return button;
	}	
	
	public static Combo createCombo(Composite parent, int style, SelectionListener listener) {
		Combo combo = new Combo(parent, style);
		if (listener != null) {
			combo.addSelectionListener(listener);
		}
		return combo;		
	}
	
	public static Label createLabel(Composite parent, int style, String text) {
		Label label = new Label(parent, style);
		label.setText(text);
		return label;
	}
	
	public static Button createCheckButton(Composite parent, String text, String toolTip, SelectionListener listener) {
		return createButton(parent, SWT.CHECK, text, toolTip, listener);
	}		
	
	public static Button createPushButton(Composite parent, String text, String toolTip, SelectionListener listener) {
		return createButton(parent, SWT.PUSH, text, toolTip, listener);
	}	

	public static Button createRadioButton(Composite parent, String text, String toolTip, SelectionListener listener) {
		return createButton(parent, SWT.RADIO, text, toolTip, listener);
	}		
	
	public static Slider createSlider(Composite parent, int style, int minimum, int maximum, int selection) {
		Slider slider = new Slider(parent, style);
		slider.setMinimum(minimum);
		slider.setMaximum(maximum);
		slider.setSelection(selection);
		return slider;
	}
	
	public static Slider createSlider(Composite parent, int style, int minimum, int maximum, int selection, SelectionListener listener) {
		Slider slider = createSlider(parent, style, minimum, maximum, selection);
		if (listener != null) {
			slider.addSelectionListener(listener);
		}
		return slider;
	}
	
	public static Spinner createSpinner(Composite parent, int style, int minimum, int maximum, int increment) {
		Spinner spinner = new Spinner(parent, style);
		spinner.setMinimum(minimum);
		spinner.setMaximum(maximum);
		spinner.setIncrement(increment);
		return spinner;
	}	
	
	public static Spinner createSpinner(Composite parent, int style, int minimum, int maximum, int increment, ModifyListener listener) {
		Spinner spinner = createSpinner(parent, style, minimum, maximum, increment);
		if (listener != null) {
			spinner.addModifyListener(listener);
		}
		return spinner;
	}	
	
	public static Text createText(Composite parent, int style, String text) {
		Text textField = new Text(parent, style);
		textField.setText(text);
		return textField;
	}	
	
	public static Text createText(Composite parent, int style, String text, ModifyListener listener) {
		Text textField = createText(parent, style, text);
		if (listener != null) {
			textField.addModifyListener(listener);
		}
		return textField;
	}
	 
		  
}
