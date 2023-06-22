package hexlet.code;

import picocli.CommandLine;

@CommandLine.Command(name="gendiff",
        description="Compares two configuration files and shows a difference.")

class App implements Runnable {

    @CommandLine.Option(names = {"-f", "--format"}, defaultValue = "stylish",
            description = " output format [default: ${DEFAULT-VALUE}]",  paramLabel = "format")
    private String format;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true,
            description = "Show this @|fg(blue) help|@ message and exit.")
    boolean usageHelpRequested;

    @CommandLine.Option(names = {"-V", "--version"}, versionHelp = true,
            description = "Print version information and exit.")
    boolean versionInfoRequested;

    @CommandLine.Parameters(index = "0", description = "path to first file", paramLabel = "filepath1")
    String filepath1;

    @CommandLine.Parameters(index = "1", description = "path to second file", paramLabel = "filepath2")
    String filepath2;

    public void run() {
    }

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new App());
        commandLine.parseArgs(args);
        if (commandLine.isUsageHelpRequested()) {
            commandLine.usage(System.out);
            return;
        } else if (commandLine.isVersionHelpRequested()) {
            commandLine.printVersionHelp(System.out);
            return;
        }

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
