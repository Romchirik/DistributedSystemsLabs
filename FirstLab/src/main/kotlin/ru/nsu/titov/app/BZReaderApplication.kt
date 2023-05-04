package ru.nsu.titov.app

import picocli.CommandLine
import ru.nsu.titov.archives.ArchiveReader
import ru.nsu.titov.models.OSMStatistic
import ru.nsu.titov.xml.FileOSMReader
import java.nio.file.Path
import java.util.concurrent.Callable

@CommandLine.Command(name = "bzstat", mixinStandardHelpOptions = true, description = ["get statistic about .osm.bz2"])
class BZReaderApplication : Callable<Int> {
    @CommandLine.Parameters(index = "0", description = ["Target file"])
    lateinit var path: Path

    @Throws(Exception::class)
    override fun call(): Int {
        println(path)
        val stream = ArchiveReader.of(path)
        val statistic = FileOSMReader(stream).use { reader ->
            val statisticBuilder = OSMStatistic.OSMStatisticBuilder()

            while (reader.hasNext()) {
                val node = reader.readNextNode()
                node?.let { statisticBuilder.applyNode(it) } ?: break
            }

            statisticBuilder.build()
        }

        CliOSMStatisticWriter().write(statistic)

        return 0
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            CommandLine(BZReaderApplication()).execute(*args)
        }
    }
}