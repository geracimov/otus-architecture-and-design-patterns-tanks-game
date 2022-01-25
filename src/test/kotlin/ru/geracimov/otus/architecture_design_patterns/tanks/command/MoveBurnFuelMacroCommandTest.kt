package ru.geracimov.otus.architecture_design_patterns.tanks.command

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import ru.geracimov.otus.architecture_design_patterns.tanks.Vector
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.FuelBurnable
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.FuelCheckable
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.Movable

internal class MoveBurnFuelMacroCommandTest {
    private lateinit var command: MoveBurnFuelMacroCommand
    private lateinit var fuelCheckable: FuelCheckable
    private lateinit var movable: Movable
    private lateinit var fuelBurnable: FuelBurnable

    @BeforeEach
    internal fun setUp() {
        fuelCheckable = mock {
            on(it.isFuelEnough()) doReturn true
        }
        movable = mock {
            on(it.getPosition()) doReturn Vector(intArrayOf(1, 2))
            on(it.getVelocity()) doReturn Vector(intArrayOf(3, 4))
        }
        fuelBurnable = mock {
            doNothing().`when`(mock).burnFuel()
        }
    }

    @Test
    internal fun commandTest() {
        command = MoveBurnFuelMacroCommand(fuelCheckable, movable, fuelBurnable)

        assertDoesNotThrow { command.execute() }
        verify(fuelCheckable).isFuelEnough()
        verify(movable).getPosition()
        verify(movable).getVelocity()
        verify(movable).setPosition(Vector(intArrayOf(4, 6)))
        verify(fuelBurnable).burnFuel()
    }
}