package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class GildedRoseTest {

    @Test
    fun foo() {
        val items = arrayOf<Item>(Item("foo", 0, 0));
        updateQualityByOneDay(items)
        assertEquals("foo", items[0].name)

    }

    @Test
    fun `allCases_sellInDecrease`() {
        val items = arrayOf<Item>(
            Item("foo", 5, 10),
            Item("Conjured Manu's mighty hammer", 5, 10),
            Item("Aged Brie", 5,10),
            Item("Backstage passes to a TAFKAL80ETC concert", 5, 10)
        )


        updateQualityByOneDay(items)

        items.forEach{
            assertEquals(it.sellIn, 4, "${it.name}: sellIn is not right")
        }
    }

    @Test
    fun `sulfuras items never decay`() {
        val items = arrayOf(Item("Sulfuras, Hand of Ragnaros", 5, 10))
        updateQualityByOneDay(items)

        assertEquals(items[0].sellIn,5)
    }



    @Test
    fun `regularCase_qualityDecaysAfterEachDay`() {
        val items = arrayOf<Item>(Item("foo", 5, 10))

        updateQualityByOneDay(items)

        assertTrue { items[0].quality == 9 }
    }

    @Test
    fun `regularCase_qualityDecaysTwiceAfterSell`() {
        val items = arrayOf(Item("soldItem", -1, 10))
        val store = GildedRose(items)
        store.updateQuality()

        assertTrue { items[0].quality == 8 }

    }

    @Test
    fun `regularCase_qualityCannotBeNegative`() {
        val items = arrayOf<Item>(Item("foo", 0, 0))
        updateQualityByOneDay(items)
        assertTrue { items[0].quality == 0 }
    }

    @Test
    fun `regularCase_qualityCannotBeMoreThan50`() {
        val items = arrayOf<Item>(Item("foo", 0, 50))
        updateQualityByOneDay(items)
        assertTrue { items[0].quality <= 50 }
    }

    @Test
    fun `conjuredCase_qualityCannotDecreasesTwiceAsFast`() {
        val items = arrayOf<Item>(Item("Conjured Manu's mighty hammer", 8, 50))
        val app = GildedRose(items)
        app.updateQuality()
        assertTrue { items[0].quality == 48 }
    }

    @Test
    fun `conjuredCase_qualityCannotDecreasesTwiceAsFastAfterSellIn`() {
        val items = arrayOf<Item>(Item("Conjured Manu's mighty hammer", -1, 50))
        val app = GildedRose(items)
        app.updateQuality()
        assertTrue { items[0].quality == 46 }
    }

    @Test
    fun `Sulfuras_qualityAlwaysEqualsTo80`() {
        val items = arrayOf<Item>(Item("Sulfuras, Hand of Ragnaros", 0, 80))
        val app = GildedRose(items)
        app.updateQuality()
        assertTrue { items[0].quality == 80 }
    }

    @Test
    fun `AgedBrie_qualityIncreases`() {
        val items = arrayOf<Item>(Item("Aged Brie", 8, 45))
        val app = GildedRose(items)
        app.updateQuality()
        assertTrue { items[0].quality == 46 }
    }

    @Test
    fun `AgedBrie_qualityIncreasesTwiceAsFast`() {
        val items = arrayOf<Item>(Item("Aged Brie", -1, 45))
        val app = GildedRose(items)
        app.updateQuality()
        assertTrue { items[0].quality == 47 }
    }

    @Test
    fun `AgedBrie_qualityIncreasesAndNotMoreThan50`() {
        val items = arrayOf<Item>(Item("Aged Brie", 0, 50))
        val app = GildedRose(items)
        app.updateQuality()
        assertTrue { items[0].quality == 50 }
    }

    @Test
    fun `BackstagePasses_qualityIncreasesAndNotMoreThan50`() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 8, 49))
        val app = GildedRose(items)
        app.updateQuality()
        assertTrue { items[0].quality == 50 }
    }

    @Test
    fun `BackstagePasses_QualityIncreasesBy2When10OrLess`() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 8, 2))
        val app = GildedRose(items)
        app.updateQuality()
        assertTrue { items[0].quality == 4 }

    }

    @Test
    fun `BackstagePasses_QualityIncreasesBy3When5OrLess`() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", 4, 2))
        val app = GildedRose(items)
        app.updateQuality()
        assertTrue { items[0].quality == 5 }
    }

    @Test
    fun `BackstagePasses_QualityDropTo0`() {
        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", -1, 50))
        val app = GildedRose(items)
        app.updateQuality()
        assertTrue { items[0].quality == 0 }
    }


}


fun updateQualityByOneDay(items: Array<Item>) {
    val app = GildedRose(items)
    app.updateQuality()
}
