package ru.nsu.titov.app;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class StatisticsData {

    @Getter
    @Setter
    private Map<String, Integer> userData = new HashMap<>();
}
