package ru.nsu.titov.app

import ru.nsu.titov.models.OSMStatistic

class CliOSMStatisticWriter : OSMStatisticWriter {
    override fun write(statistic: OSMStatistic) {
        val userStats = statistic.userAfford
        val tagStats = statistic.tagAfford

        println("User contribution statistic:")
        userStats
            .entries
            .sortedByDescending { it.value }
            .forEach { (name, occurrences) ->
                println("    User: $name, contributed $occurrences ${getTimes(occurrences)}")
            }
        println("\n")
        println("Tag occurrences statistic:")
        tagStats
            .entries
            .sortedByDescending { it.value }
            .forEach { (name, occurrences) ->
                println("    Tag: $name, encountered $occurrences ${getTimes(occurrences)}")
            }
        println("\n")
    }

    private fun getTimes(value: Int) = if (value == 1) {
        "time"
    } else {
        "times"
    }
}