package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.util.Map;

public class Parser {
    public static Map<String, Object> readFile(String file, String format) throws Exception {
        switch (format) {
            case "yml", "yaml":
                ObjectMapper mapperYML = new YAMLMapper();
                return mapperYML.readValue(file, new TypeReference<Map<String, Object>>() {
                });
            case "json":
                ObjectMapper mapperJSON = new ObjectMapper();
                return mapperJSON.readValue(file, new TypeReference<Map<String, Object>>() {
                });
            default:
                throw new Exception("Invalid format");
        }
    }
}
