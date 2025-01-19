package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileContentFiltering {
    private final List<String> integers = new ArrayList<>();
    private final List<String> floats = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

        public void readAndFilter(String[] fileNames) throws IOException {
        for (String fileName : fileNames) {
            Path path = Path.of(fileName);
            if (!Files.exists(path)) {
                System.err.println("Файл не найден: " + fileName);
                continue;
            }
            try (Stream<String> lines = Files.lines(path)) {
                for (String line : (Iterable<String>) lines::iterator) {
                    filterData(line);
                }
            }
        }
    }

    private void filterData(String line) {
        if (line.matches("-?\\d+")) {
            integers.add(line);
        } else if (line.matches("-?\\d*\\.\\d+([eE][-+]?\\d+)?")) {
            floats.add(line);
        } else {
            strings.add(line);
        }
    }

    public void writeToFile(String outputDir, String prefix, boolean addMode) throws IOException {
        writeListToFile(integers, outputDir, prefix + "integers.txt", addMode);
        writeListToFile(floats, outputDir, prefix + "floats.txt", addMode);
        writeListToFile(strings, outputDir, prefix + "strings.txt", addMode);
    }

    private void writeListToFile(List<String> list, String outputDir, String fileName, boolean addMode) throws IOException {
        if (list.isEmpty()) {
            return;
        }
        Path outputPath = Path.of(outputDir, fileName);
        Files.createDirectories(outputPath.getParent());
        try (BufferedWriter writer = Files.newBufferedWriter(outputPath, addMode ? StandardOpenOption.APPEND : StandardOpenOption.CREATE)) {
            for (String line : list) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public Stats createStats() {
        return new Stats(integers, floats, strings);
    }
}
