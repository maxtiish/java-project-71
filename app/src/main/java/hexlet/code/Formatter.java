package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;

public class Formatter {
    public static String chooseFormat(Map<String, Object> map1, Map<String, Object> map2, String format) {
        switch (format) {
            case "plain":
                return Plain.makePlain(map1, map2);
            default:
                return Stylish.makeStylish(map1, map2);
        }
    }
}
