package hexlet.code;

import hexlet.code.formatters.Stylish;

public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws Exception {
        var map1 = Parser.readFile(filepath1);
        var map2 = Parser.readFile(filepath2);

        return Formatter.chooseFormat(map1, map2, format);
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        var map1 = Parser.readFile(filepath1);
        var map2 = Parser.readFile(filepath2);

        return Stylish.makeStylish(map1, map2);
    }
}
