package ru.geracimov.otus.architecture_design_patterns.tanks.command

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.FuelBurnable

internal class BurnFuelCommandTest {
    private lateinit var mockFuelBurnable: FuelBurnable
    private lateinit var burnFuelCommand: BurnFuelCommand

    @BeforeEach
    internal fun setUp() {
        mockFuelBurnable = mock { }
        burnFuelCommand = BurnFuelCommand(mockFuelBurnable)
    }

    @Test
    fun execute() {
        burnFuelCommand.execute()
        verify(mockFuelBurnable).burnFuel()
    }
}