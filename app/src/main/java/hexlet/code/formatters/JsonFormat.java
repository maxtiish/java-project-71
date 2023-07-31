package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFormat {
    public static String makeJson(Map<String, Object> map1, Map<String, Object> map2) throws Exception {
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);

        ObjectMapper result = new ObjectMapper();
        return result.writerWithDefaultPrettyPrinter().writeValueAsString(list);
    }
}
