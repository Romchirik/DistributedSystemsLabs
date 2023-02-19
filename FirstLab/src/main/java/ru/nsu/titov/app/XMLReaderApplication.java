package ru.nsu.titov.app;

import picocli.CommandLine;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.concurrent.Callable;

@Command(name = "bzstat", mixinStandardHelpOptions = true, description = "get statistic about .osm.bz2")
public class XMLReaderApplication implements Callable<Integer> {

    @Parameters(index = "0", description = "The file whose checksum to calculate.")
    private File file;

    @Override
    public Integer call() throws Exception {

        try (InputStream inputStream = new FileInputStream(file)) {
            var reader = new StaxStreamProcessor(inputStream).getReader();

            while (reader.hasNext()) {

            }

        }

        return 0;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new XMLReaderApplication()).execute(args);
        System.exit(exitCode);
    }
}
