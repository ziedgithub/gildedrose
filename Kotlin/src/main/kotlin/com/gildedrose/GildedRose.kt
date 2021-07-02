package com.gildedrose

class GildedRose(var items: Array<Item>) {
    val itemList: MutableList<Items> = mutableListOf()

    fun updateQuality() {
        for (item in items) {

            if (item.name.contains("Conjured")) {
                item.decay()
                val conjured = Items.Conjured(item)
                conjured.updateQuality()
                continue
            }

            if (item.name != "Aged Brie" && item.name != "Backstage passes to a TAFKAL80ETC concert") {
                if (item.quality > 0) {
                    if (item.name != "Sulfuras, Hand of Ragnaros") {
                        item.decreaseQuality()
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.increaseQuality()

                    if (item.name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.increaseQuality()
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.increaseQuality()
                            }
                        }
                    }
                }
            }

            if (item.name != "Sulfuras, Hand of Ragnaros") {
                item.decay()
            }

            if (item.sellIn < 0) {
                if (item.name != "Aged Brie") {
                    if (item.name != "Backstage passes to a TAFKAL80ETC concert") {
                        if (item.quality > 0) {
                            if (item.name != "Sulfuras, Hand of Ragnaros") {
                                item.decreaseQuality()
                            }
                        }
                    } else {
                        item.quality = 0
                    }
                } else {
                    if (item.quality < 50) {
                        item.increaseQuality()
                    }
                }
            }
        }
    }



}

fun Item.increaseQuality() {
    quality++
}
fun Item.decreaseQuality() {
    quality--
}

fun Item.decay(){
    sellIn--
}
