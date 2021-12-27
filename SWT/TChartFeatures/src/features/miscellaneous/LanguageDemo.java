/*
 * LanguageDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2008 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */
package features.miscellaneous;

import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

import com.steema.teechart.styles.Pie;

import features.ChartSample;

/**
 * @author tom
 *
 */
public class LanguageDemo extends ChartSample implements SelectionListener {

	public LanguageDemo(Composite c) {
		super(c);
		languageList.addSelectionListener(this);
	}

	public void widgetDefaultSelected(SelectionEvent se) {}

	public void widgetSelected(SelectionEvent se) {	
		Widget source = se.widget;
		if (source == languageList) {

			switch (languageList.getSelectionIndex()) {
			case 0:
				com.steema.teechart.languages.Language.activate();
				break;
			case 1:
				com.steema.teechart.languages.Language.activate(new Locale("ca"));
				break;
			case 2:
				com.steema.teechart.languages.Language.activate(new Locale("zh", "CN"));
				break;
			case 3:
				com.steema.teechart.languages.Language.activate(new Locale("zh", "HK"));
				break;
			case 4:
				com.steema.teechart.languages.Language.activate(new Locale("nl"));
				break;
			case 5:
				com.steema.teechart.languages.Language.activate(new Locale("en"));
				break;
			case 6:
				com.steema.teechart.languages.Language.activate(new Locale("fr"));
				break;
			case 7:
				com.steema.teechart.languages.Language.activate(new Locale("de"));
				break;
			case 8:
				com.steema.teechart.languages.Language.activate(new Locale("id"));
				break;
			case 9:
				com.steema.teechart.languages.Language.activate(new Locale("it"));
				break;
			case 10:
				com.steema.teechart.languages.Language.activate(new Locale("ja", "JP"));
				break;
			case 11:
				com.steema.teechart.languages.Language.activate(new Locale("no"));
				break;
			case 12:
				com.steema.teechart.languages.Language.activate(new Locale("pt"));
				break;
			case 13:
				com.steema.teechart.languages.Language.activate(new Locale("ru"));
				break;
			case 14:
				com.steema.teechart.languages.Language.activate(new Locale("sk"));
				break;
			case 15:
				com.steema.teechart.languages.Language.activate(new Locale("sl"));
				break;
			case 16:
				com.steema.teechart.languages.Language.activate(new Locale("es"));
				break;
			case 17:
				com.steema.teechart.languages.Language.activate(new Locale("sv"));
				break;
			case 18:
				com.steema.teechart.languages.Language.activate(new Locale("tr"));
				break;
			case 19:
				com.steema.teechart.languages.Language.activate(new Locale("uk"));
				break;

			default:
				com.steema.teechart.languages.Language.activate();
			break;
			}

		} else if (source == editButton) {
			//TODO ChartEditor.editChart(chart1.getChart());
		}
	}	

	protected void createContent() {
		super.createContent();    	       	
		addLabel(SWT.LEFT, "Choose a language: ");    	
		languageList = addCombo(SWT.READ_ONLY | SWT.SINGLE);
		editButton = addPushButton("Edit Chart...", "", this);
	}

	protected void initContent() {
		super.initContent();   
		editButton.setEnabled(false);

		languageList.add("Default");
		languageList.add("Catalan");
		languageList.add("Chinese (PRC)");
		languageList.add("Chinese (Hong Kong SAR)");
		languageList.add("Dutch");
		languageList.add("English");
		languageList.add("French");
		languageList.add("German");
		languageList.add("Indonesian");
		languageList.add("Italian");
		languageList.add("Japanese");
		languageList.add("Norwegian");
		languageList.add("Portuguese");
		languageList.add("Russian");
		languageList.add("Slovak");
		languageList.add("Slovenian");
		languageList.add("Spanish");
		languageList.add("Swedish");
		languageList.add("Turkish");
		languageList.add("Ukranian");
	}

	protected void initChart() {
		super.initChart();
		Pie series = new Pie(chart1.getChart());    	
		chart1.getAspect().setView3D(false);

		series.fillSampleValues();
	}   				

	private Button editButton;
	private Combo languageList;	
}
