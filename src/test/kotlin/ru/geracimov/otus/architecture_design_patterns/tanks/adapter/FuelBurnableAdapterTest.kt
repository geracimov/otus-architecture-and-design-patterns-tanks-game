package ru.geracimov.otus.architecture_design_patterns.tanks.adapter

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import ru.geracimov.otus.architecture_design_patterns.tanks.UObject

class FuelBurnableAdapterTest {

    private lateinit var mockUObject: UObject
    private lateinit var fuelBurnableAdapter: FuelBurnableAdapter

    @BeforeEach
    internal fun setUp() {
        mockUObject = mock {
            doReturn(70).`when`(it).getProperty<Int>(FuelBurnable.FUEL_BURN_RATE)
            doReturn(101).`when`(it).getProperty<Int>(FuelCheckable.FUEL_LEVEL)
        }
        fuelBurnableAdapter = FuelBurnableAdapter(mockUObject)
    }

    @Test
    fun isFuelEnoughTest() {
        fuelBurnableAdapter.burnFuel()
        verify(mockUObject).getProperty<Int>(FuelCheckable.FUEL_LEVEL)
        verify(mockUObject).getProperty<Int>(FuelBurnable.FUEL_BURN_RATE)
        verify(mockUObject).setProperty(FuelCheckable.FUEL_LEVEL, 31)
    }

}