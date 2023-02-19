package ru.nsu.titov.app;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.nsu.titov.app.Tags.NODE_TAG;

public class StatisticsReader {

    public StatisticsReader(

    ) {

    }

    private final StatisticsData stats = new StatisticsData();

    void readFromFile() throws IOException, XMLStreamException {
        try (StaxStreamProcessor processor = new StaxStreamProcessor(Files.newInputStream(Paths.get("payload.xml")))) {
            XMLStreamReader reader = processor.getReader();
            while (reader.hasNext()) {
                int event = reader.next();
                if (event == XMLEvent.START_ELEMENT && NODE_TAG.equals(reader.getLocalName())) {

                }
            }
        }
    }
}
