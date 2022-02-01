package ru.geracimov.otus.architecture_design_patterns.tanks.exception.handler

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import ru.geracimov.otus.architecture_design_patterns.tanks.command.Command
import ru.geracimov.otus.architecture_design_patterns.tanks.command.RepeatCommand
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class RepeatExceptionHandlerTest {
    private lateinit var queue: ArrayDeque<Command>
    private lateinit var repeatExceptionHandler: ExceptionHandler
    private lateinit var repeatCommand: Command
    private lateinit var mockCommand: Command

    @BeforeEach
    internal fun setUp() {
        queue = ArrayDeque()
        repeatExceptionHandler = RepeatExceptionHandler(queue)
        mockCommand = mock {
            on { toString() } doReturn "MockCommand"
        }
        repeatCommand = RepeatCommand(mockCommand)
    }

    @Test
    fun handle() {
        repeatExceptionHandler.handle(repeatCommand, IllegalStateException())
        assertEquals(1, queue.size)
        val commandInQueue = queue.removeFirst()
        assertTrue(commandInQueue is RepeatCommand)
    }
}