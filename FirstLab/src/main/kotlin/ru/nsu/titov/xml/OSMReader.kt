package ru.nsu.titov.xml

import ru.nsu.titov.models.OSMNode

interface OSMReader {
    fun readNextNode(): OSMNode?
    fun hasNext(): Boolean
}