package hexlet.code;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.ArrayList;

public class DifferDefiner {
    public static List<Map<String, Object>> findDifference(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());
        List<Map<String, Object>> result = new ArrayList<>();

        for (String key : keys) {
            Map<String, Object> map = new HashMap<>();
            map.put("key", key);
            if (!map1.containsKey(key)) {
                map.put("type", "added");
                map.put("value", map2.get(key));
            } else if (!map2.containsKey(key)) {
                map.put("type", "deleted");
                map.put("value", map1.get(key));
            } else if (compareValues(map1.get(key), map2.get(key))) {
                map.put("type", "unchanged");
                map.put("value", map1.get(key));
            } else {
                map.put("type", "changed");
                map.put("value1", map1.get(key));
                map.put("value2", map2.get(key));
            }
            result.add(map);

        }
        return result;
    }

    public static boolean compareValues(Object value1, Object value2) {
        if (value1 == null && value2 == null) {
            return true;
        } else if (value1 == null || value2 == null) {
            return false;
        } else {
            return value1.equals(value2);
        }
    }
}
