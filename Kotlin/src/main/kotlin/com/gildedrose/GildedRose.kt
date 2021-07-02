package com.gildedrose

class GildedRose(var items: Array<Item>) {
    val itemList: MutableList<Items> = mutableListOf()

    fun updateQuality() {
        for (item in items) {
            item.decay()

            item.updateQuality()
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

fun Item.updateQuality() {
    if (name.contains("Conjured")) {
        val conjured = Items.Conjured(this)
        conjured.updateQuality()
        return
    }

    if (name != "Aged Brie" && name != "Backstage passes to a TAFKAL80ETC concert" && name != "Sulfuras, Hand of Ragnaros") {
        decreaseQuality()

    } else {
        increaseQuality()

        if (name == "Backstage passes to a TAFKAL80ETC concert") {
            if (sellIn < 11) {
                increaseQuality()
            }

            if (sellIn < 6) {
                increaseQuality()
            }
        }
    }

    if (sellIn < 0) {
        if (name == "Sulfuras, Hand of Ragnaros") return

        if (name == "Backstage passes to a TAFKAL80ETC concert") {
            quality = 0
        }
        if (name != "Aged Brie") {

            decreaseQuality()
        } else {
            increaseQuality()
        }
    }
}
