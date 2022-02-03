package ru.geracimov.otus.architecture_design_patterns.tanks.exception.handler

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.Movable
import ru.geracimov.otus.architecture_design_patterns.tanks.command.Command
import ru.geracimov.otus.architecture_design_patterns.tanks.command.MoveCommand
import ru.geracimov.otus.architecture_design_patterns.tanks.command.RepeatCommand

internal class MainExceptionHandlerTest {
    private lateinit var mainExceptionHandler: ExceptionHandler
    private lateinit var mockHandler: ExceptionHandler

    @BeforeEach
    internal fun setUp() {
        val handlers = HashMap<Int, ExceptionHandler>()
        mainExceptionHandler = MainExceptionHandler(handlers)

        mockHandler = mock {
            doNothing().`when`(mock).handle(any(), any())
        }
        val moveIse = mainExceptionHandler.hashCodeOf(MoveCommand::class, IllegalStateException::class)
        handlers[moveIse] = mockHandler
    }

    @Test
    fun configuredHandlerWillCalledTest() {
        val mockMovable = mock<Movable> {
            on { getPosition() } doThrow IllegalStateException("SomeException")
        }

        val command: Command = MoveCommand(mockMovable)

        try {
            command.execute()
        } catch (e: Exception) {
            mainExceptionHandler.handle(command, e)
        }

        verify(mockMovable).getPosition()
        verify(mockHandler).handle(any(), any())
    }

    @Test
    fun notConfiguredHandlerDoesNotCalledTest() {
        val mockCommand = mock<Command> {
            on { execute() } doThrow IllegalStateException("SomeException")
        }

        val command: Command = RepeatCommand(mockCommand)

        try {
            command.execute()
        } catch (e: Exception) {
            mainExceptionHandler.handle(command, e)
        }

        verify(mockCommand).execute()
        verify(mockHandler, times(0)).handle(any(), any())
    }
}