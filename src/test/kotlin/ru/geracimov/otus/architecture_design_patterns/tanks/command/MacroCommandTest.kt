package ru.geracimov.otus.architecture_design_patterns.tanks.command

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import ru.geracimov.otus.architecture_design_patterns.tanks.exception.CommandException
import kotlin.test.assertFailsWith

internal class MacroCommandTest {
    private lateinit var someCommand1: Command
    private lateinit var someCommand2: Command
    private lateinit var someCommand3: Command


    @BeforeEach
    internal fun setUp() {
        someCommand1 = mock {}
        someCommand2 = mock {
            on { execute() } doThrow CommandException("Command thrown some exception")
        }
        someCommand3 = mock {}
    }

    @Test
    fun execute() {
        val macroCommand = MacroCommand(arrayOf(someCommand1, someCommand2, someCommand3))
        assertFailsWith<CommandException>(block = { macroCommand.execute() })
        verify(someCommand1).execute()
        verify(someCommand2).execute()
        verify(someCommand3, times(0)).execute()
    }
}