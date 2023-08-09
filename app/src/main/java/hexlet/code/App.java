package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

@Command(name = "gendiff", version = "1.0", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")

public final class App implements Callable<Integer> {
    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = " output format [default: ${DEFAULT-VALUE}]",  paramLabel = "format")
    private String format;
    @Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    private static String filepath1;
    @Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    private static String filepath2;

    @Override
    public Integer call() {
        try {
            System.out.println(Differ.generate(filepath1, filepath2, format));
        } catch (Exception error) {
            System.out.println(error);
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
