package hexlet.code.formatters;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Stylish {
    public static String makeStylish(Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder result = new StringBuilder("\n{");

        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        for (String key : keys) {
            var value1 = convertToString(map1.get(key));
            var value2 = convertToString(map2.get(key));

            if (!map1.containsKey(key)) {
                result.append("\n  + " + key + ": " + value2);
            } else if (!map2.containsKey(key)) {
                result.append("\n  - " + key + ": " + value1);
            } else if (value1.equals(value2)) {
                result.append("\n    " + key + ": " + value1);
            } else {
                result.append("\n  - " + key + ": " + value1 + "\n" + "  + " + key + ": " + value2);
            }
        }
        result.append("\n}");
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
