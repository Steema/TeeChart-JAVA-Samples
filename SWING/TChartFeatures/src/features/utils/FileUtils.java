/*
 * FileUtils.java
 *
 * <p>Copyright: (c) 2004-2007 by Steema Software SL. All Rights Reserved.</p>
 *
 * <p>Company: Steema Software SL</p>
 */

package features.utils;

import java.io.File;

/**
 *
 * @author tom
 */
public class FileUtils implements FileExtensions {

    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = "";
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return "";
    }

    public static boolean isExtension(File f, String ext) {
        return FileUtils.getExtension(f).compareToIgnoreCase(ext) == 0;
    }

    public static String replaceExtension(File f, String ext) {
        String name = "";
        String s = f.getAbsolutePath();
        int i = s.lastIndexOf('.');
        if (i == -1) { i = s.length(); };
        name = s.substring(0,i);
        name = name.concat(".").concat(ext);
        return name;
    }

}
