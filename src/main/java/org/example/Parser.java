package org.example;

import org.apache.commons.cli.*;

public class Parser {

    private String outputDir;
    private String prefix;
    private boolean addMode;
    private boolean shortStats;
    private boolean fullStats;
    private String[] fileNames;

    public Parser(String[] args) {
        parseArgs(args);
    }

    public String getOutputDir() {
        return outputDir;
    }
    public String getPrefix() {
        return prefix;
    }
    public boolean isAddMode() {
        return addMode;
    }
    public boolean isShortStats() {
        return shortStats;
    }
    public boolean isFullStats() {
        return fullStats;
    }
    public String[] getFileNames() {
        return fileNames;
    }

    private void parseArgs(String[] args) {
        Options options = new Options()
                .addOption("o", "output", true, "путь результата")
                .addOption("p", "prefix", true, "имя файла с отфильтрованным содержимым")
                .addOption("s", "short", false, "короткая статистика")
                .addOption("f", "full", false, "полная статистика")
                .addOption("a", "add", false, "режим добавления в файлы");
        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine commandLine = parser.parse(options, args);
            this.outputDir = commandLine.getOptionValue("o", "output");
            this.prefix = commandLine.getOptionValue("p", "");
            this.addMode = commandLine.hasOption("a");
            this.shortStats = commandLine.hasOption("s");
            this.fullStats = commandLine.hasOption("f");
            this.fileNames = commandLine.getArgs();
        } catch (ParseException e) {
            throw new IllegalArgumentException("Ошибка при разборе аргументов командной строки", e);
        }
    }
}
