package org.stroganov.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator {

    public static boolean isStringFilePath(String directoryPath) {
        String regexp = "[A-Z]:\\\\[^&<>|#:;*?^\\/]*.(csv|json)";
        return isStringValidPattern(directoryPath, regexp);
    }

    public static boolean isStringYear(String year) {
        String regexp = "^(19|20)\\d{2}$";
        return isStringValidPattern(year, regexp);
    }

    public static boolean isStringNumberPage(String year) {
        String regexp = "^\\d{1,4}";
        return isStringValidPattern(year, regexp);
    }

    private static boolean isStringValidPattern(String string, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

}
