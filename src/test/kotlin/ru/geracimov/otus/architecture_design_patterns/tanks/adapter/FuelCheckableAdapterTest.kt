package ru.geracimov.otus.architecture_design_patterns.tanks.adapter

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import ru.geracimov.otus.architecture_design_patterns.tanks.UObject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FuelCheckableAdapterTest {

    private lateinit var mockUObject: UObject
    private lateinit var fuelCheckableAdapter: FuelCheckableAdapter

    @BeforeEach
    internal fun setUp() {
        mockUObject = mock {
            doReturn(5).`when`(it).getProperty(FuelCheckable.FUEL_LEVEL)
        }
        fuelCheckableAdapter = FuelCheckableAdapter(mockUObject)
    }

    @Test
    fun isFuelEnoughTest() {
        assertTrue { fuelCheckableAdapter.isFuelEnough(0) }
        assertTrue { fuelCheckableAdapter.isFuelEnough(4) }
        assertTrue { fuelCheckableAdapter.isFuelEnough(5) }
    }

    @Test
    fun isFuelNotEnoughTest() {
        assertFalse { fuelCheckableAdapter.isFuelEnough(6) }
        assertFalse { fuelCheckableAdapter.isFuelEnough(4545) }
    }

}