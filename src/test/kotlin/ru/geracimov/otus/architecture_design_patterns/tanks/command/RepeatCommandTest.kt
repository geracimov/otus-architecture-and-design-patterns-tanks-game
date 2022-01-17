package ru.geracimov.otus.architecture_design_patterns.tanks.command

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

internal class RepeatCommandTest {
    private lateinit var repeatCommand: Command
    private lateinit var mockCommand: Command

    @BeforeEach
    internal fun setUp() {
        mockCommand = mock {
            on { toString() } doReturn "MockCommand"
        }
        repeatCommand = RepeatCommand(mockCommand)
    }

    @Test
    fun execute() {
        repeatCommand.execute()
        verify(mockCommand, times(1)).execute()
    }
}