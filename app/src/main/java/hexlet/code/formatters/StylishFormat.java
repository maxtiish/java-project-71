package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormat {
    public static String makeStylish(List<Map<String, Object>> list) throws Exception {
        StringBuilder result = new StringBuilder("{\n");

        for (Map<String, Object> map : list) {
            switch (map.get("type").toString()) {
                case "added":
                    result.append("  + " + map.get("key") + ": " + map.get("value") + "\n");
                    break;
                case "deleted":
                    result.append("  - " + map.get("key") + ": " + map.get("value") + "\n");
                    break;
                case "changed":
                    String value1 = convertToString(map.get("value1"));
                    String value2 = convertToString(map.get("value2"));
                    result.append("  - " + map.get("key") + ": " + value1 + "\n");
                    result.append("  + " + map.get("key") + ": " + value2 + "\n");
                    break;
                case "unchanged":
                    result.append("    " + map.get("key") + ": " + map.get("value") + "\n");
                    break;
                default:
                    throw new Exception("invalid category");
            }
        }
        result.append("}");
        return result.toString();
    }

    public static String convertToString(Object value) {
        if (value == null) {
            return "null";
        } else {
            return value.toString();
        }
    }
}
