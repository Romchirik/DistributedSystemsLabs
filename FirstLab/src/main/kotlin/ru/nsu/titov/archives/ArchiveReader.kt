package ru.nsu.titov.archives

import org.apache.commons.compress.compressors.CompressorStreamFactory
import java.io.BufferedInputStream
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path


object ArchiveReader {
    fun of(path: Path): InputStream {
        val inputStream: InputStream = BufferedInputStream(Files.newInputStream(path))
        return CompressorStreamFactory().createCompressorInputStream(inputStream)
    }
}