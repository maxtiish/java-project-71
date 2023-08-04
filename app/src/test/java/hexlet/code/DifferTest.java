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

        var expected1 = "{\n"
                + "  - chars1: [a, b, c]\n"
                + "  + chars1: [a, b, c]\n"
                + "    chars2: [d, e, f]\n"
                + "    checked: false\n"
                + "    default: null\n"
                + "    id: 45\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "  - numbers1: [1, 2, 3, 4]\n"
                + "  + numbers1: [1, 2, 3, 4]\n"
                + "    numbers2: [2, 3, 4, 5]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "    setting1: Some value\n"
                + "    setting2: 200\n"
                + "    setting3: true\n"
                + "}";
        var actual1 = Differ.generate(filepath1, filepath2);
        assertEquals(expected1, actual1);

        var expected2 = "Property 'chars1' was updated. From [complex value] to [complex value]\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers1' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n";
        var format2 = "plain";
        var actual2 = Differ.generate(filepath1, filepath2, format2);
        assertEquals(expected2, actual2);

        var expected3 = "[ {\n"
                + "  \"value2\" : [ \"a\", \"b\", \"c\" ],\n"
                + "  \"value1\" : [ \"a\", \"b\", \"c\" ],\n"
                + "  \"type\" : \"changed\",\n"
                + "  \"key\" : \"chars1\"\n"
                + "}, {\n"
                + "  \"type\" : \"unchanged\",\n"
                + "  \"value\" : [ \"d\", \"e\", \"f\" ],\n"
                + "  \"key\" : \"chars2\"\n"
                + "}, {\n"
                + "  \"type\" : \"unchanged\",\n"
                + "  \"value\" : false,\n"
                + "  \"key\" : \"checked\"\n"
                + "}, {\n"
                + "  \"type\" : \"unchanged\",\n"
                + "  \"value\" : null,\n"
                + "  \"key\" : \"default\"\n"
                + "}, {\n"
                + "  \"type\" : \"unchanged\",\n"
                + "  \"value\" : 45,\n"
                + "  \"key\" : \"id\"\n"
                + "}, {\n"
                + "  \"type\" : \"deleted\",\n"
                + "  \"value\" : \"value1\",\n"
                + "  \"key\" : \"key1\"\n"
                + "}, {\n"
                + "  \"type\" : \"added\",\n"
                + "  \"value\" : \"value2\",\n"
                + "  \"key\" : \"key2\"\n"
                + "}, {\n"
                + "  \"value2\" : [ 1, 2, 3, 4 ],\n"
                + "  \"value1\" : [ 1, 2, 3, 4 ],\n"
                + "  \"type\" : \"changed\",\n"
                + "  \"key\" : \"numbers1\"\n"
                + "}, {\n"
                + "  \"type\" : \"unchanged\",\n"
                + "  \"value\" : [ 2, 3, 4, 5 ],\n"
                + "  \"key\" : \"numbers2\"\n"
                + "}, {\n"
                + "  \"type\" : \"deleted\",\n"
                + "  \"value\" : [ 3, 4, 5 ],\n"
                + "  \"key\" : \"numbers3\"\n"
                + "}, {\n"
                + "  \"type\" : \"added\",\n"
                + "  \"value\" : [ 4, 5, 6 ],\n"
                + "  \"key\" : \"numbers4\"\n"
                + "}, {\n"
                + "  \"type\" : \"added\",\n"
                + "  \"value\" : {\n"
                + "    \"nestedKey\" : \"value\",\n"
                + "    \"isNested\" : true\n"
                + "  },\n"
                + "  \"key\" : \"obj1\"\n"
                + "}, {\n"
                + "  \"type\" : \"unchanged\",\n"
                + "  \"value\" : \"Some value\",\n"
                + "  \"key\" : \"setting1\"\n"
                + "}, {\n"
                + "  \"type\" : \"unchanged\",\n"
                + "  \"value\" : 200,\n"
                + "  \"key\" : \"setting2\"\n"
                + "}, {\n"
                + "  \"type\" : \"unchanged\",\n"
                + "  \"value\" : true,\n"
                + "  \"key\" : \"setting3\"\n"
                + "} ]";
        var format3 = "json";
        var actual3 = Differ.generate(filepath1, filepath2, format3);
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

        var expected1 = "{\n"
                + "  - buy: true\n"
                + "  + characteristics: [funny to play, difficult, interesting]\n"
                + "  - developer: The Indie Stone\n"
                + "  + developer: The Indie Stone\n"
                + "  + frightening: sometimes\n"
                + "    name: Project Zomboid\n"
                + "  - version: [41.78.16, 145.15, 40.70.13]\n"
                + "}";
        var actual1 = Differ.generate(filepath1, filepath2);
        assertEquals(expected1, actual1);

        var expected2 = "Property 'buy' was removed\n"
                + "Property 'characteristics' was added with value: [complex value]\n"
                + "Property 'developer' was updated. From 'The Indie Stone' to 'The Indie Stone'\n"
                + "Property 'frightening' was added with value: 'sometimes'\n"
                + "Property 'version' was removed\n";
        var actual2 = Differ.generate(filepath1, filepath2, format);
        assertEquals(expected2, actual2);

        var expected3 = "[ {\n"
                + "  \"type\" : \"deleted\",\n"
                + "  \"value\" : true,\n"
                + "  \"key\" : \"buy\"\n"
                + "}, {\n"
                + "  \"type\" : \"added\",\n"
                + "  \"value\" : [ \"funny to play\", \"difficult\", \"interesting\" ],\n"
                + "  \"key\" : \"characteristics\"\n"
                + "}, {\n"
                + "  \"value2\" : \"The Indie Stone\",\n"
                + "  \"value1\" : \"The Indie Stone\",\n"
                + "  \"type\" : \"changed\",\n"
                + "  \"key\" : \"developer\"\n"
                + "}, {\n"
                + "  \"type\" : \"added\",\n"
                + "  \"value\" : \"sometimes\",\n"
                + "  \"key\" : \"frightening\"\n"
                + "}, {\n"
                + "  \"type\" : \"unchanged\",\n"
                + "  \"value\" : \"Project Zomboid\",\n"
                + "  \"key\" : \"name\"\n"
                + "}, {\n"
                + "  \"type\" : \"deleted\",\n"
                + "  \"value\" : [ \"41.78.16\", 145.15, \"40.70.13\" ],\n"
                + "  \"key\" : \"version\"\n"
                + "} ]";
        var format3 = "json";
        var actual3 = Differ.generate(filepath1, filepath2, format3);
        assertEquals(expected3, actual3);
    }
}
