package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> readJsonFile(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        String content = Files.readString(path);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map  = mapper.readValue(content, new TypeReference<Map<String, Object>>() { });

        return map;
    }

    public static Map<String, Object> readYamlFile(String filepath) throws Exception {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        String content = Files.readString(path);

        ObjectMapper mapper = new YAMLMapper();
        Map<String, Object> map  = mapper.readValue(content, new TypeReference<Map<String, Object>>() { });

        return map;
    }
}
