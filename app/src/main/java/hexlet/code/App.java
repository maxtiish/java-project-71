package hexlet.code;

import picocli.CommandLine;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff",
        description = "Compares two configuration files and shows a difference.")

public final class App implements Callable<Integer> {
    @CommandLine.Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = " output format [default: ${DEFAULT-VALUE}]",  paramLabel = "format")
    private String format;
    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true,
            description = "Show this @|fg(blue) help|@ message and exit.")
    private boolean usageHelpRequested;
    @CommandLine.Option(names = {"-V", "--version"}, versionHelp = true,
            description = "Print version information and exit.")
    private boolean versionInfoRequested;
    @CommandLine.Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    private static String filepath1;
    @CommandLine.Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    private static String filepath2;

    @Override
    public Integer call() throws Exception {
        System.out.println(Differ.generate(filepath1, filepath2, format));
        return null;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
