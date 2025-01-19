package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Parser parser = new Parser(args);
        FileContentFiltering filtering = new FileContentFiltering();

        filtering.readAndFilter(parser.getFileNames());
        filtering.writeToFile(parser.getOutputDir(), parser.getPrefix(), parser.isAddMode());

        Stats stats = filtering.createStats();
        if (parser.isShortStats()) {
            stats.printAllShortStats();
        }
        if (parser.isFullStats()) {
            stats.printAllFullStats();
        }
    }
}
