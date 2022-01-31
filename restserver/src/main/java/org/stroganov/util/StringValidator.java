package org.stroganov.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator {

    public static boolean isStringFilePath(String directoryPath) {
        String regexp = "[A-Z]:\\\\[^&<>|#:;*?^\\/]*.(csv|json)";
        return isStringValidPattern(directoryPath, regexp);
    }

    private static boolean isStringValidPattern(String string, String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

}
