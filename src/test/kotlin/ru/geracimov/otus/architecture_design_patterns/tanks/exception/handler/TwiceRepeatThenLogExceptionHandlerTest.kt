package ru.geracimov.otus.architecture_design_patterns.tanks.exception.handler

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import ru.geracimov.otus.architecture_design_patterns.tanks.command.*

internal class TwiceRepeatThenLogExceptionHandlerTest {
    private val queue: ArrayDeque<Command> = ArrayDeque()
    private lateinit var mainExceptionHandler: ExceptionHandler
    private lateinit var mockRotateCommand: RotateCommand

    @BeforeEach
    internal fun setUp() {
        val handlers = HashMap<Int, ExceptionHandler>()
        mainExceptionHandler = MainExceptionHandler(handlers)
        mockRotateCommand = mock {
            on { execute() } doThrow IllegalStateException("SomeException")
            on { toString() } doReturn "MockRotateCommand"
        }

        val moveIse = mainExceptionHandler.hashCodeOf(mockRotateCommand::class, IllegalStateException::class)
        handlers[moveIse] = RepeatExceptionHandler(queue)

        val repeatIse = mainExceptionHandler.hashCodeOf(RepeatCommand::class, IllegalStateException::class)
        handlers[repeatIse] = TwiceRepeatExceptionHandler(queue)

        val twiceRepeatIse = mainExceptionHandler.hashCodeOf(TwiceRepeatCommand::class, IllegalStateException::class)
        handlers[twiceRepeatIse] = LogExceptionHandler(queue)
    }

    @Test
    fun moveCommandWillRepeatedThenLoggedToConsoleTest() {
        queue.addLast(mockRotateCommand)

        while (queue.isNotEmpty()) {
            val command = queue.removeFirst()
            try {
                command.execute()
            } catch (e: Exception) {
                mainExceptionHandler.handle(command, e)
            }
        }

        verify(mockRotateCommand, times(3)).execute()
    }
}