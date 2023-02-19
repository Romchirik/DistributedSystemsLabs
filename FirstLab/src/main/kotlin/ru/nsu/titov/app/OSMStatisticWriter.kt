package ru.nsu.titov.app

import ru.nsu.titov.models.OSMStatistic

interface OSMStatisticWriter {

    fun write(statistic: OSMStatistic)
}