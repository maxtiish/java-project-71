package hexlet.code;

import hexlet.code.formatters.JsonFormat;
import hexlet.code.formatters.PlainFormat;
import hexlet.code.formatters.StylishFormat;

import java.util.Map;

public class Formatter {
    public static String chooseFormat(Map<String, Object> map1, Map<String, Object> map2, String format)
            throws Exception {
        switch (format) {
            case "plain":
                return PlainFormat.makePlain(map1, map2);
            case "json":
                return JsonFormat.makeJson(map1, map2);
            default:
                return StylishFormat.makeStylish(map1, map2);
        }
    }
}
