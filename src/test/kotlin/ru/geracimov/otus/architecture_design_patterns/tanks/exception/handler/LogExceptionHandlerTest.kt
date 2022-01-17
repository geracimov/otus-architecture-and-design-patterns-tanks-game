package ru.geracimov.otus.architecture_design_patterns.tanks.exception.handler

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import ru.geracimov.otus.architecture_design_patterns.tanks.command.Command
import ru.geracimov.otus.architecture_design_patterns.tanks.command.LogExceptionCommand
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class LogExceptionHandlerTest {
    private lateinit var queue: Queue<Command>
    private lateinit var logExceptionHandler: ExceptionHandler
    private lateinit var logExceptionCommand: Command

    @BeforeEach
    internal fun setUp() {
        queue = LinkedList()
        logExceptionHandler = LogExceptionHandler(queue)
        val mockCommand = mock<Command> {
            on { toString() } doReturn "MockCommand"
        }
        val mockException = mock<Exception> { }
        logExceptionCommand = LogExceptionCommand(mockCommand, mockException)
    }

    @Test
    fun logExceptionHandlerHandleTest() {
        logExceptionHandler.handle(logExceptionCommand, IllegalStateException())
        assertEquals(1, queue.size)
        val commandInQueue = queue.poll()
        assertTrue(commandInQueue is LogExceptionCommand)
    }
}