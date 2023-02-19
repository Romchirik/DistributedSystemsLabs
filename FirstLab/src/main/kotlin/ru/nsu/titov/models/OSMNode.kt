package ru.nsu.titov.models


data class OSMNode(
    val user: String, val tags: List<OSMTag>
) {

    class OSMNodeBuilder {
        private var user: String? = null
        private val tags: MutableList<OSMTag> = mutableListOf()
        fun applyAttribute(name: String, value: String): OSMNodeBuilder = this.apply {
            when (name) {
                Constants.USER_ATTRIBUTE -> user = value
            }
        }

        fun applyTag(tag: OSMTag): OSMNodeBuilder = this.apply {
            tags.add(tag)
        }

        fun build(): OSMNode = user?.let {
            OSMNode(
                user = it,
                tags = tags,
            )
        } ?: run {
            throw IllegalStateException("Failed to build OSMNode: missed name param")
        }

    }
}