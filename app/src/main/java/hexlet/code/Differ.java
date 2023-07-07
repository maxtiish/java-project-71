package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filepath1, String filepath2) throws Exception {
        StringBuilder result = new StringBuilder();
        result.append("\n{");

        Path path1 = Paths.get(filepath1).toAbsolutePath().normalize();
        String content1 = Files.readString(path1);

        Path path2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String content2 = Files.readString(path2);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map1 = mapper.readValue(content1, new TypeReference<Map<String, Object>>() { });
        Map<String, Object> map2 = mapper.readValue(content2, new TypeReference<Map<String, Object>>() { });

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
