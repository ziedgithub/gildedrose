package com.gildedrose

interface ItemBehaviour {
    fun updateQuality(): Item
}

sealed class Items(val item: Item) : ItemBehaviour {
    class Conjured(item: Item): Items(item) {
        override fun updateQuality(): Item {
            if (item.sellIn >= 0) item.quality -= 2
            else item.quality -= 4
            return item
        }
    }
}

