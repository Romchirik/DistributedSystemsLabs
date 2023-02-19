package ru.nsu.titov.xml

import ru.nsu.titov.models.Constants
import ru.nsu.titov.models.OSMNode
import ru.nsu.titov.models.OSMTag
import java.io.InputStream
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamConstants
import javax.xml.stream.XMLStreamReader

class FileOSMReader(
    private val stream: InputStream
) : OSMReader, AutoCloseable {

    private val readerFactory = XMLInputFactory.newInstance()

    private val _reader: XMLStreamReader? = readerFactory.createXMLStreamReader(stream, Constants.ARCHIVE_ENCODING)
    private val reader: XMLStreamReader = checkNotNull(_reader) { "Reader is not initialized" }
    override fun readNextNode(): OSMNode? {
        val osmNodeBuilder = OSMNode.OSMNodeBuilder()

        var event = reader.next()
        while (reader.hasNext()) {
            when {
                (event == XMLStreamConstants.START_ELEMENT && Constants.NODE_TAG == reader.localName) -> {
                    break
                }

                (event == XMLStreamConstants.START_ELEMENT && Constants.WAY_TAG == reader.localName) -> {
                    return null
                }
            }
            event = reader.next()
        }
        for (i in 0 until reader.attributeCount) {
            val attrName = reader.getAttributeName(i).localPart
            val attrValue = reader.getAttributeValue(i)


            if (attrValue != null && attrName != null) {
                osmNodeBuilder.applyAttribute(name = attrName, value = attrValue)
            }
        }

        event = reader.next()
        while (!reader.isEndElement) {
            if (event == XMLStreamConstants.START_ELEMENT && reader.localName == Constants.TAG_TAG) {
                val key = reader.getAttributeValue(0)
                val value = reader.getAttributeValue(1)

                if (key != null && value != null) {
                    osmNodeBuilder.applyTag(OSMTag(key, value))
                }
            }
            event = reader.next()
        }

        return osmNodeBuilder.build()
    }

    override fun hasNext() = reader.hasNext()

    override fun close() = stream.close()
}