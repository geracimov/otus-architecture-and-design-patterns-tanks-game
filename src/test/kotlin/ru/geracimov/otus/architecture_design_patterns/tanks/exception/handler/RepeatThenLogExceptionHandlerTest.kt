package ru.geracimov.otus.architecture_design_patterns.tanks.exception.handler

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import ru.geracimov.otus.architecture_design_patterns.tanks.command.Command
import ru.geracimov.otus.architecture_design_patterns.tanks.command.MoveCommand
import ru.geracimov.otus.architecture_design_patterns.tanks.command.RepeatCommand

internal class RepeatThenLogExceptionHandlerTest {
    private val queue: ArrayDeque<Command> = ArrayDeque()
    private lateinit var mainExceptionHandler: ExceptionHandler
    private lateinit var mockMoveCommand: MoveCommand

    @BeforeEach
    internal fun setUp() {
        val handlers = HashMap<Int, ExceptionHandler>()
        mainExceptionHandler = MainExceptionHandler(handlers)
        mockMoveCommand = mock {
            on { execute() } doThrow IllegalStateException("SomeException")
            on { toString() } doReturn "MockMoveCommand"
        }
        val moveIse = mainExceptionHandler.hashCodeOf(mockMoveCommand::class, IllegalStateException::class)
        handlers[moveIse] = RepeatExceptionHandler(queue)

        val repeatIse = mainExceptionHandler.hashCodeOf(RepeatCommand::class, IllegalStateException::class)
        handlers[repeatIse] = LogExceptionHandler(queue)
    }

    @Test
    fun moveCommandWillRepeatedThenLoggedToConsoleTest() {
        queue.addLast(mockMoveCommand)

        while (queue.isNotEmpty()) {
            val command = queue.removeFirst()
            try {
                command.execute()
            } catch (e: Exception) {
                mainExceptionHandler.handle(command, e)
            }
        }

        verify(mockMoveCommand, times(2)).execute()
    }
}