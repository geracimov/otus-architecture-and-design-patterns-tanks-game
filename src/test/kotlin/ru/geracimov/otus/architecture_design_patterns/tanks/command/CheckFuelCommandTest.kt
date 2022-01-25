package ru.geracimov.otus.architecture_design_patterns.tanks.command

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.FuelCheckable
import ru.geracimov.otus.architecture_design_patterns.tanks.exception.CommandException
import kotlin.test.assertFailsWith


internal class CheckFuelCommandTest {
    private lateinit var mockFuelCheckable: FuelCheckable
    private lateinit var checkFuelCommand: CheckFuelCommand

    @BeforeEach
    internal fun setUp() {
        mockFuelCheckable = mock {
            on(it.isFuelEnough()) doReturn true
        }
    }

    @Test
    fun executeSuccessTest() {
        checkFuelCommand = CheckFuelCommand(mockFuelCheckable)
        assertDoesNotThrow { checkFuelCommand.execute() }
    }

    @Test
    fun executeFailTest() {
        mockFuelCheckable = mock {
            on(it.isFuelEnough()) doReturn false
        }
        checkFuelCommand = CheckFuelCommand(mockFuelCheckable)
        assertFailsWith<CommandException>(block = { checkFuelCommand.execute() })
    }

}