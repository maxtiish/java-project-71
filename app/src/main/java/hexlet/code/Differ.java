package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        StringBuilder result = new StringBuilder("\n{");

        var map1 = Parser.readFile(filepath1);
        var map2 = Parser.readFile(filepath2);

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
