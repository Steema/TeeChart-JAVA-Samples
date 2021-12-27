/*
 * LanguageDemo.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.miscellaneous;

import com.steema.teechart.editors.ChartEditor;
import com.steema.teechart.styles.Pie;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import features.ChartSamplePanel;
import java.util.Locale;

/**
 *
 * @author tom
 */
public class LanguageDemo extends ChartSamplePanel implements ActionListener {

    /** Creates a new instance of LanguageDemo */
    public LanguageDemo() {
        super();
        editButton.addActionListener(this);
        languageList.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == languageList) {

            switch (languageList.getSelectedIndex()) {
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
            case 20:
                com.steema.teechart.languages.Language.activate(new Locale("ko", "KR"));
                break;

            default:
                com.steema.teechart.languages.Language.activate();
                break;
            }

        } else if (source == editButton) {
            ChartEditor.editChart(chart1.getChart());
        }
    }

    protected void initChart() {
        super.initChart();
    }

    protected void initComponents() {
        super.initComponents();
        Pie series = new Pie(chart1.getChart());
        chart1.getAspect().setView3D(false);

        series.fillSampleValues();

        editButton = new JButton("Edit Chart...");
        languageList = new JComboBox();

       languageList.addItem("Default");
       languageList.addItem("Catalan");
       languageList.addItem("Chinese (PRC)");
       languageList.addItem("Chinese (Hong Kong SAR)");
       languageList.addItem("Dutch");
       languageList.addItem("English");
       languageList.addItem("French");
       languageList.addItem("German");
       languageList.addItem("Indonesian");
       languageList.addItem("Italian");
       languageList.addItem("Japanese");
       languageList.addItem("Norwegian");
       languageList.addItem("Portuguese");
       languageList.addItem("Russian");
       languageList.addItem("Slovak");
       languageList.addItem("Slovenian");
       languageList.addItem("Spanish");
       languageList.addItem("Swedish");
       languageList.addItem("Turkish");
       languageList.addItem("Ukranian");
       languageList.addItem("Korean");

    }

    protected void initGUI() {
        super.initGUI();
        JLabel tmpLabel;
        JPanel tmpPane = getButtonPane();
        {
            tmpLabel = new JLabel("Choose a language:");
            tmpLabel.setDisplayedMnemonic('C');
            tmpLabel.setLabelFor(languageList);
            tmpPane.add(tmpLabel);
            tmpPane.add(languageList);
            tmpPane.add(Box.createHorizontalStrut(10));
            tmpPane.add(editButton);
            tmpPane.add(Box.createHorizontalGlue());
        }
    }

    private JButton editButton;
    private JComboBox languageList;
}
