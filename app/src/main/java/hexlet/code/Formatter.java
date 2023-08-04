package hexlet.code;

import hexlet.code.formatters.JsonFormat;
import hexlet.code.formatters.PlainFormat;
import hexlet.code.formatters.StylishFormat;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String chooseFormat(List<Map<String, Object>> list, String format)
            throws Exception {
        switch (format) {
            case "plain":
                return PlainFormat.makePlain(list);
            case "json":
                return JsonFormat.makeJson(list);
            default:
                return StylishFormat.makeStylish(list);
        }
    }
}
