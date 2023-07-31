package hexlet.code.formatters;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Plain {
    public static String makePlain(Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder result = new StringBuilder();

        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        for (String key : keys) {
            var value1 = convertToString(map1.get(key));
            var value2 = convertToString(map2.get(key));

            if (!map1.containsKey(key)) {
                result.append("\nProperty '" + key + "' was added with value: " + value2);
            } else if (!map2.containsKey(key)) {
                result.append("\nProperty '" + key + "' was removed");
            } else if (!value1.equals(value2)) {
                result.append("\nProperty '" + key + "' was updated. From " + value1 + " to " + value2);
            }
        }
        return result.toString();
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
