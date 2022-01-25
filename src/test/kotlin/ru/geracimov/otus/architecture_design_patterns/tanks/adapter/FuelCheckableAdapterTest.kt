package ru.geracimov.otus.architecture_design_patterns.tanks.adapter

import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import ru.geracimov.otus.architecture_design_patterns.tanks.UObject
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.FuelBurnable.Companion.FUEL_BURN_RATE
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.FuelCheckable.Companion.FUEL_LEVEL
import kotlin.test.assertTrue

class FuelCheckableAdapterTest {
    private lateinit var fuelCheckableAdapter: FuelCheckableAdapter

    @Test
    fun isFuelEnoughTest() {
        fuelCheckableAdapter = FuelCheckableAdapter(mockUObject(5, 4))
        assertTrue { fuelCheckableAdapter.isFuelEnough() }
    }

    @Test
    fun isFuelNotEnoughTest() {
        fuelCheckableAdapter = FuelCheckableAdapter(mockUObject(10, 12))
    }

    private fun mockUObject(fuelLevel: Int, burnRate: Int): UObject = mock {
        doReturn(fuelLevel).`when`(it).getProperty<Int>(FUEL_LEVEL)
        doReturn(burnRate).`when`(it).getProperty<Int>(FUEL_BURN_RATE)
    }

}