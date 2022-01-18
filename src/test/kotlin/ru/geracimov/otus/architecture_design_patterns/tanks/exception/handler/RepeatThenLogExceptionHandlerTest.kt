package ru.geracimov.otus.architecture_design_patterns.tanks.exception.handler

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.Movable
import ru.geracimov.otus.architecture_design_patterns.tanks.command.Command
import ru.geracimov.otus.architecture_design_patterns.tanks.command.MoveCommand
import ru.geracimov.otus.architecture_design_patterns.tanks.command.RepeatCommand
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.*
import kotlin.test.assertTrue

internal class RepeatThenLogExceptionHandlerTest {
    private val queue: Queue<Command> = LinkedList()
    private lateinit var mainExceptionHandler: ExceptionHandler

    @BeforeEach
    internal fun setUp() {
        val handlers = HashMap<Int, ExceptionHandler>()
        mainExceptionHandler = MainExceptionHandler(handlers)

        val moveIse = mainExceptionHandler.hashCodeOf(MoveCommand::class, IllegalStateException::class)
        handlers[moveIse] = RepeatExceptionHandler(queue)

        val repeatIse = mainExceptionHandler.hashCodeOf(RepeatCommand::class, IllegalStateException::class)
        handlers[repeatIse] = LogExceptionHandler(queue)
    }

    @Test
    fun moveCommandWillRepeatedThenLoggedToConsoleTest() {
        val mockMovable = mock<Movable> {
            on { getPosition() } doThrow IllegalStateException("SomeException")
        }

        queue.offer(MoveCommand(mockMovable))
        val systemErr = System.err
        val byteArrayOutputStream = ByteArrayOutputStream(1024)
        val byteArraySystemErr = PrintStream(byteArrayOutputStream)

        System.setErr(byteArraySystemErr)
        while (queue.isNotEmpty()) {
            val command = queue.poll()
            try {
                command.execute()
            } catch (e: Exception) {
                mainExceptionHandler.handle(command, e)
            }
        }
        System.setErr(systemErr)
        val loggedMessage = byteArrayOutputStream.toString().trimEnd()
        println(loggedMessage)

        verify(mockMovable, times(2)).getPosition()
        assertTrue(loggedMessage.endsWith("threw exception: SomeException"))
    }
}