package com.gildedrose

class GildedRose(var items: Array<Item>) {
    val itemList: MutableList<Items> = mutableListOf()

    fun updateQuality() {
        for (item in items) {
            item.decay()

            updateQuality(item)
        }
    }

    private fun updateQuality(item: Item) {
        if (item.name.contains("Conjured")) {
            val conjured = Items.Conjured(item)
            conjured.updateQuality()
            return
        }

        if (item.name != "Aged Brie" && item.name != "Backstage passes to a TAFKAL80ETC concert") {
            if (item.name != "Sulfuras, Hand of Ragnaros") {
                item.decreaseQuality()
            }

        } else {
            item.increaseQuality()

            if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                if (item.sellIn < 11) {
                    item.increaseQuality()
                }

                if (item.sellIn < 6) {
                    item.increaseQuality()
                }
            }
        }

        if (item.sellIn < 0) {
            if (item.name != "Aged Brie") {
                if (item.name != "Backstage passes to a TAFKAL80ETC concert") {
                    if (item.name != "Sulfuras, Hand of Ragnaros") {
                        item.decreaseQuality()
                    }
                } else {
                    item.quality = 0
                }
            } else {
                item.increaseQuality()
            }
        }
    }


}

fun Item.increaseQuality() {
    if (quality < 50) {
        quality++
    }
}

fun Item.decreaseQuality() {
    if (quality > 0) {
        quality--
    }
}

fun Item.decay() {
    if (name != "Sulfuras, Hand of Ragnaros") {
        sellIn--
    }
}
