package hexlet.code.formatters;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFormat {
    public static String makeJson(List<Map<String, Object>> list) throws Exception {
        ObjectMapper result = new ObjectMapper();
        return result.writerWithDefaultPrettyPrinter().writeValueAsString(list);
    }
}
