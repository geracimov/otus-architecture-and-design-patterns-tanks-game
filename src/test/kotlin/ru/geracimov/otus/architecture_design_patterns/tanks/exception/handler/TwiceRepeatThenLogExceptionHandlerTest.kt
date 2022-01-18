package ru.geracimov.otus.architecture_design_patterns.tanks.exception.handler

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.Movable
import ru.geracimov.otus.architecture_design_patterns.tanks.command.Command
import ru.geracimov.otus.architecture_design_patterns.tanks.command.MoveCommand
import ru.geracimov.otus.architecture_design_patterns.tanks.command.RepeatCommand
import ru.geracimov.otus.architecture_design_patterns.tanks.command.TwiceRepeatCommand
import java.util.*

internal class TwiceRepeatThenLogExceptionHandlerTest {
    private val queue: Queue<Command> = LinkedList()
    private lateinit var mainExceptionHandler: ExceptionHandler

    @BeforeEach
    internal fun setUp() {
        val handlers = HashMap<Int, ExceptionHandler>()
        mainExceptionHandler = MainExceptionHandler(handlers)

        val moveIse = mainExceptionHandler.hashCodeOf(MoveCommand::class, IllegalStateException::class)
        handlers[moveIse] = RepeatExceptionHandler(queue)

        val repeatIse = mainExceptionHandler.hashCodeOf(RepeatCommand::class, IllegalStateException::class)
        handlers[repeatIse] = TwiceRepeatExceptionHandler(queue)

        val twiceRepeatIse = mainExceptionHandler.hashCodeOf(TwiceRepeatCommand::class, IllegalStateException::class)
        handlers[twiceRepeatIse] = LogExceptionHandler(queue)
    }

    @Test
    fun moveCommandWillRepeatedThenLoggedToConsoleTest() {
        val mockMovable = mock<Movable> {
            on { getPosition() } doThrow IllegalStateException("SomeException")
        }

        queue.offer(MoveCommand(mockMovable))

        while (queue.isNotEmpty()) {
            val command = queue.poll()
            try {
                command.execute()
            } catch (e: Exception) {
                mainExceptionHandler.handle(command, e)
            }
        }

        verify(mockMovable, times(3)).getPosition()
    }
}