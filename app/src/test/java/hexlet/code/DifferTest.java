package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DifferTest {
    @Test
    public void testJsonFiles() throws Exception {
        var filepath1 = "src/test/resources/file1.json";
        var filepath2 = "src/test/resources/file2.json";
        var format = "plain";

        var expected1 = "\n"
                + "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";
        var actual1 = Differ.generate(filepath1, filepath2);
        assertEquals(expected1, actual1);

        var expected2 = "\n"
                + "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'";
        var actual2 = Differ.generate(filepath1, filepath2, format);
        assertEquals(expected2, actual2);
    }

    @Test
    public void testIfExists() {
        var filepath1 = "src/test/resources/file.json";
        var filepath2 = "src/test/resources/file2.json";

        var thrown = catchThrowable(
                () -> Differ.generate(filepath1, filepath2)
        );
        assertThat(thrown).isInstanceOf(Exception.class);
    }

    @Test
    public void testEmptyFile() {
        var filepath1 = "src/test/resources/file1.json";
        var filepath2 = "src/test/resources/emptyFile.json";
        var format = "stylish";

        var thrown = catchThrowable(
                () -> Differ.generate(filepath1, filepath2, format)
        );
        assertThat(thrown).isInstanceOf(Exception.class);
    }

    @Test
    public void testYmlFiles() throws Exception {
        var filepath1 = "src/test/resources/file1.yml";
        var filepath2 = "src/test/resources/file2.yml";
        var format = "plain";

        var expected1 = "\n"
                + "{\n"
                + "  - buy: true\n"
                + "  + characteristics: [funny to play, difficult, interesting, u will never be alone again]\n"
                + "    developer: The Indie Stone\n"
                + "  + frightening: sometimes\n"
                + "  - name: Project Zomboid\n"
                + "  + name: project zomboid\n"
                + "  - version: [41.78.16, 145.15, 40.70.13]\n"
                + "}";
        var actual1 = Differ.generate(filepath1, filepath2);
        assertEquals(expected1, actual1);

        var expected2 = "\n"
                + "Property 'buy' was removed\n"
                + "Property 'characteristics' was added with value: [complex value]\n"
                + "Property 'frightening' was added with value: 'sometimes'\n"
                + "Property 'name' was updated. From 'Project Zomboid' to 'project zomboid'\n"
                + "Property 'version' was removed";
        var actual2 = Differ.generate(filepath1, filepath2, format);
        assertEquals(expected2, actual2);
    }
}
