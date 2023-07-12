package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();

        StringBuilder result = new StringBuilder("\n{");

        if (filepath1.endsWith("json") && filepath2.endsWith("json")) {
            map1 = Parser.readJsonFile(filepath1);
            map2 = Parser.readJsonFile(filepath2);
        } else if (filepath1.endsWith("yml") && filepath2.endsWith("yml")) {
            map1 = Parser.readYamlFile(filepath1);
            map2 = Parser.readYamlFile(filepath2);
        }

        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        for (String key : keys) {
            if (!map1.containsKey(key)) {
                result.append("\n  + " + key + ": " + map2.get(key));
            } else if (!map2.containsKey(key)) {
                result.append("\n  - " + key + ": " + map1.get(key));
            } else if (map1.get(key).equals(map2.get(key))) {
                result.append("\n    " + key + ": " + map1.get(key));
            } else {
                result.append("\n  - " + key + ": " + map1.get(key) + "\n" + "  + " + key + ": " + map2.get(key));
            }
        }
        result.append("\n}");
        return result.toString();
    }
}
