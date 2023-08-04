package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;


public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        var content1 = Paths.get(filepath1).toAbsolutePath().normalize();
        String file1 = Files.readString(content1);
        var format1 = findFormat(filepath1);
        var map1 = Parser.readFile(file1, format1);

        var content2 = Paths.get(filepath2).toAbsolutePath().normalize();
        String file2 = Files.readString(content2);
        var format2 = findFormat(filepath2);
        var map2 = Parser.readFile(file2, format2);

        var result = DifferDefiner.findDifference(map1, map2);

        return Formatter.chooseFormat(result, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String findFormat(String filepath) {
        return filepath.substring(filepath.lastIndexOf('.') + 1);
    }
}
