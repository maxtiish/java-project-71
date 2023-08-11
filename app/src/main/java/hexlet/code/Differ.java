package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        String file1 = readFile(filepath1);
        String format1 = findFormat(filepath1);
        var map1 = Parser.readFile(file1, format1);

        String file2 = readFile(filepath2);
        String format2 = findFormat(filepath2);
        var map2 = Parser.readFile(file2, format2);

        List<Map<String, Object>> result = DifferDefiner.findDifference(map1, map2);

        return Formatter.chooseFormat(result, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String findFormat(String filepath) {
        return filepath.substring(filepath.lastIndexOf('.') + 1);
    }

    public static String readFile(String filepath) throws Exception {
        Path path = Path.of(filepath);
        return Files.readString(path);
    }
}
