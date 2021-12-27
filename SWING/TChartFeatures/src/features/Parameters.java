/*
 * Parameters.java
 *
 * <p>Copyright: Copyright (c) 2005-2007 by Steema Software SL. All Rights
 * Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * @author tom
 */
public class Parameters {

    private static boolean needsToSave = false;

    protected static Properties properties = null;

    /** Creates a new instance of Parameters */
    private Parameters() {}

    public static String getClassPath(Class cls, String separator) {
        String s = cls.getName();
        StringBuffer n = new StringBuffer();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                n.append(separator);
            } else {
                n.append(s.charAt(i));
            }
        }
        n.append(SOURCE_CODE_EXTENSION);

        return n.toString();
    }

    public static File getFilePath(Class cls) {
        String name = getSourcePath() + getFileSeparator() +
                      getClassPath(cls, getFileSeparator());
        return new File(name);
    }

    public static boolean getShowAllFeatures() {
        if (properties != null) {
            return properties.getProperty("demo.showall", "").equals("1");
        } else {
            return false;
        }
    }

    public static String getSourcePath() {
        String result = "";

        if (properties != null) {
            result = properties.getProperty("demo.sourcepath", "");
        }

        if (result.length() == 0) {
            String source = getFileSeparator() + "TeeChartDemo.java";

            String s = System.getProperty("user.dir");
            File tmp = new File(s + source);

            //System.out.println(s);

            if (tmp.exists()) {
                result = s;
            } else {
                s ="." + getFileSeparator() + "src";

                //System.out.println("trying result="+ s);

                if ((new File(s + source)).exists()) {
                    result = s;
                }
            }
        }

        System.out.println("result="+ result);

        return result;
    }

    public static void setSourcePath(String path) {
        needsToSave = !getSourcePath().equals(path);

        if (properties != null) {
            properties.setProperty("demo.sourcepath", path);
        }
    }

    private static File getPropsFile() {
        return new File(
                System.getProperty("user.dir")
                + getFileSeparator()
                + PARAMETERS_FILE);
    }

    public static void loadProperties() {
        properties = new Properties();
        try {
            File tmp = getPropsFile();
            if (tmp.exists()) {
                properties.load(new FileInputStream(tmp));
            }
        } catch (Exception e) {
            e.printStackTrace();
            //pass
        }
    }

    public static void saveProperties() {

        if (needsToSave) {
            try {
                File propsFile = getPropsFile();
                propsFile.createNewFile();
                properties.store(new FileOutputStream(propsFile),
                                 PARAMETERS_FILE);
            } catch (Exception e) {
                e.printStackTrace();
            }

            needsToSave = false;
        }
    }

    private static String getFileSeparator() {
        return System.getProperty("file.separator");
    }

    public static final String featuresName = "features.";

    private static final String PARAMETERS_FILE = featuresName + "properties";
    private static final String SOURCE_CODE_EXTENSION = ".java";
}
