package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> readFile(String filepath) throws Exception {
        Map<String, Object> map = new HashMap<>();

        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        String content = Files.readString(path);

        if(filepath.endsWith("json")) {
            ObjectMapper mapper = new ObjectMapper();
            map = mapper.readValue(content, new TypeReference<Map<String, Object>>() {
            });
        } else if (filepath.endsWith("yml")) {
            ObjectMapper mapper = new YAMLMapper();
            map  = mapper.readValue(content, new TypeReference<Map<String, Object>>() { });
        }
        return map;
    }
}
