package ru.nsu.titov.app;

import java.util.HashMap;
import java.util.Map;

public class StatisticReader {

    private final Map<String, Integer> changesPerUser = new HashMap<>();
    private final Map<String, Integer> tagCountPerKey = new HashMap<>();


    void apply(Node node) {

        changesPerUser.compute(node.getUser(), (name, occurrences) -> {
            if (occurrences == null) {
                return 1;
            } else {
                return occurrences + 1;
            }
        });
    }
}
