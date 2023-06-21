package hexlet.code;

import picocli.CommandLine;

@CommandLine.Command(name="gendiff",
        description="Compares two configuration files and shows a difference.")
class App implements Runnable {

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true,
            description = "Show this help message and exit.")
    boolean usageHelpRequested;

    @CommandLine.Option(names = {"-V", "--version"}, versionHelp = true,
            description = "Print version information and exit.")
    boolean versionInfoRequested;

    public void run() {
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);

        CommandLine commandLine = new CommandLine(new App());
        commandLine.parseArgs(args);
        if (commandLine.isUsageHelpRequested()) {
            commandLine.usage(System.out);
            return;
        } else if (commandLine.isVersionHelpRequested()) {
            commandLine.printVersionHelp(System.out);
            return;
        }
    }
}
