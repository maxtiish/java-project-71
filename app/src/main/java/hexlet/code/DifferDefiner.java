package hexlet.code;

import java.util.*;

public class FindDiffer {
    public static List<Map<String, Object>> findDifference(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> maps = new ArrayList<>();
        maps.add(map1);
        maps.add(map2);

        for (Map<String, Object> map : maps) {
            for (Map.Entry<String, Object> data : map.entrySet()) {
                if (!map1.containsKey(data.getKey())) {
                    data.setValue("added");
                } else if (!map2.containsKey(data.getKey())) {
                    data.setValue("deleted");
                } else if (!map1.get(data.getKey()).equals(map2.get(data.getKey()))) {
                    data.setValue("changed");
                } else {
                    data.setValue("unchanged");
                }
            }
        }
        return maps;
    }
}
