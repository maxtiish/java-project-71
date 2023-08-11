package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static String fileStylish;
    private static String filePlain;
    private static JsonNode fileJson;

    @BeforeAll
    public static void beforeAll() throws Exception {
        fileStylish = readFile(getPath("stylishResult.txt"));

        filePlain = readFile(getPath("plainResult.txt"));

        ObjectMapper objectMapper = new ObjectMapper();
        fileJson = objectMapper.readTree(readFile(getPath("jsonResult.txt")));
    }

    @Test
    public void testJsonFiles() throws Exception {
        var filepath1 = getPath("file1.json");
        var filepath2 = getPath("file2.json");

        var actual1 = Differ.generate(filepath1, filepath2);
        assertEquals(fileStylish, actual1);

        var actual2 = Differ.generate(filepath1, filepath2, "stylish");
        assertEquals(fileStylish, actual2);

        var actual3 = Differ.generate(filepath1, filepath2, "plain");
        assertEquals(filePlain, actual3);

        ObjectMapper objectMapper = new ObjectMapper();
        var actual4 = objectMapper.readTree(Differ.generate(filepath1, filepath2, "json"));
        assertEquals(fileJson, actual4);
    }

    @Test
    public void testIfExists() {
        var filepath1 = getPath("file.json");
        var filepath2 = getPath("file2.json");

        var thrown = catchThrowable(
                () -> Differ.generate(filepath1, filepath2)
        );
        assertThat(thrown).isInstanceOf(Exception.class);
    }

    @Test
    public void testEmptyFile() {
        var filepath1 = getPath("file1.json");
        var filepath2 = getPath("emptyFile.json");
        var format = "stylish";

        var thrown = catchThrowable(
                () -> Differ.generate(filepath1, filepath2, format)
        );
        assertThat(thrown).isInstanceOf(Exception.class);
    }

    @Test
    public void testYmlFiles() throws Exception {
        var filepath1 = getPath("file1.yml");
        var filepath2 = getPath("file2.yml");

        var actual1 = Differ.generate(filepath1, filepath2);
        assertEquals(fileStylish, actual1);

        var actual2 = Differ.generate(filepath1, filepath2, "stylish");
        assertEquals(fileStylish, actual2);

        var actual3 = Differ.generate(filepath1, filepath2, "plain");
        assertEquals(filePlain, actual3);

        ObjectMapper objectMapper = new ObjectMapper();
        var actual4 = objectMapper.readTree(Differ.generate(filepath1, filepath2, "json"));
        assertEquals(fileJson, actual4);
    }

    public static String getPath(String file) {
        return "src/test/resources/" + file;
    }

    public static String readFile(String filepath) throws Exception {
        Path path = Path.of(filepath);
        return Files.readString(path);
    }
}
