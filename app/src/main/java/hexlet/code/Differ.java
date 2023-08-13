package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        List<Map<String, Object>> result = DifferDefiner.findDifference(getMap(filepath1), getMap(filepath2));
        return Formatter.chooseFormat(result, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String findFormat(String filepath) {
        return filepath.substring(filepath.lastIndexOf('.') + 1);
    }

    public static String readFile(String filepath) throws Exception {
        Path path = Path.of(filepath).toAbsolutePath().normalize();
        return Files.readString(path);
    }

    public static Map<String, Object> getMap(String filepath) throws Exception {
        String file = readFile(filepath);
        return Parser.readFile(file, findFormat(filepath));
    }
}
