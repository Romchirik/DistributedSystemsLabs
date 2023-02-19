package ru.nsu.titov.models

data class OSMStatistic(
    val userAfford: Map<String, Int>,
    val tagAfford: Map<String, Int>,
) {

    class OSMStatisticBuilder {
        private val userAfford: MutableMap<String, Int> = mutableMapOf()
        private val tagAfford: MutableMap<String, Int> = mutableMapOf()
        fun applyNode(node: OSMNode): OSMStatisticBuilder = this.apply {
            userAfford.compute(node.user) { _, value ->
                value?.inc() ?: 1
            }
            node.tags.forEach { tag ->
                tagAfford.compute(tag.key) { _, value ->
                    value?.inc() ?: 1
                }
            }
        }

        fun build(): OSMStatistic = OSMStatistic(
            userAfford = userAfford,
            tagAfford = tagAfford,
        )
    }
}
