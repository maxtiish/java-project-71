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
        Path pathStylish = Path.of("src/test/resources/stylishResult.txt");
        fileStylish = Files.readString(pathStylish);

        Path pathPlain = Path.of("src/test/resources/plainResult.txt");
        filePlain = Files.readString(pathPlain);

        ObjectMapper objectMapper = new ObjectMapper();
        Path pathJson = Path.of("src/test/resources/jsonResult.txt");
        fileJson = objectMapper.readTree(Files.readString(pathJson));
    }

    @Test
    public void testJsonFiles() throws Exception {
        var filepath1 = "src/test/resources/file1.json";
        var filepath2 = "src/test/resources/file2.json";
        var expected1 = fileStylish;
        var actual1 = Differ.generate(filepath1, filepath2);
        assertEquals(expected1, actual1);

        var expected2 = filePlain;
        var format2 = "plain";
        var actual2 = Differ.generate(filepath1, filepath2, format2);
        assertEquals(expected2, actual2);

        var expected3 = fileJson;
        var format3 = "json";
        ObjectMapper objectMapper = new ObjectMapper();
        var actual3 = objectMapper.readTree(Differ.generate(filepath1, filepath2, format3));
        assertEquals(expected3, actual3);
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

        var expected1 = fileStylish;
        var actual1 = Differ.generate(filepath1, filepath2);
        assertEquals(expected1, actual1);

        var expected2 = filePlain;
        var actual2 = Differ.generate(filepath1, filepath2, format);
        assertEquals(expected2, actual2);

        var expected3 = fileJson;
        var format3 = "json";
        ObjectMapper objectMapper = new ObjectMapper();
        var actual3 = objectMapper.readTree(Differ.generate(filepath1, filepath2, format3));
        assertEquals(expected3, actual3);
    }
}
