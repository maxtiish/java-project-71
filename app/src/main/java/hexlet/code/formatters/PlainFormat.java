package hexlet.code.formatters;

import java.util.List;
import java.util.Map;


public class PlainFormat {
    public static String makePlain(List<Map<String, Object>> list)  throws Exception {
        StringBuilder result = new StringBuilder();

        for (Map<String, Object> map : list) {
            switch (map.get("type").toString()) {
                case "added":
                    String value = convertToString(map.get("value"));
                    result.append("Property '" + map.get("key") + "' was added with value: " + value);
                    result.append("\n");
                    break;
                case "deleted":
                    result.append("Property '" + map.get("key") + "' was removed" + "\n");
                    break;
                case "changed":
                    String value1 = convertToString(map.get("value1"));
                    String value2 = convertToString(map.get("value2"));
                    result.append("Property '" + map.get("key") + "' was updated. ");
                    result.append("From " + value1 + " to " + value2 + "\n");
                    break;
                case "unchanged":
                    break;
                default:
                    throw new Exception("invalid category");
            }
        }
        return result.toString().trim();
    }

    public static String convertToString(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof Map<?, ?> || value instanceof Iterable<?>) {
            return "[complex value]";
        } else {
            return value.toString();
        }
    }
}
