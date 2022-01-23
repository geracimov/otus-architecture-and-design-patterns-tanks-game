package ru.geracimov.otus.architecture_design_patterns.tanks.command

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.eq
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
            //так и не нашел как можно сделать условие не на равенство, а на условие <=
            on(it.isFuelEnough(eq(5))) doReturn true
        }
    }

    @Test
    fun executeSuccessTest() {
        checkFuelCommand = CheckFuelCommand(mockFuelCheckable, 5)
        assertDoesNotThrow { checkFuelCommand.execute() }
    }

    @Test
    fun executeFailTest() {
        checkFuelCommand = CheckFuelCommand(mockFuelCheckable, 8)
        assertFailsWith<CommandException>(block = { checkFuelCommand.execute() })
    }

}