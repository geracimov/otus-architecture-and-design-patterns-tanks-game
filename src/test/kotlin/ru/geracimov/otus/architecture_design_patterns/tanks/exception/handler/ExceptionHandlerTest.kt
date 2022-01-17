package ru.geracimov.otus.architecture_design_patterns.tanks.exception.handler

import org.junit.jupiter.api.Test
import ru.geracimov.otus.architecture_design_patterns.tanks.UObjectImpl
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.MovableAdapter
import ru.geracimov.otus.architecture_design_patterns.tanks.command.Command
import ru.geracimov.otus.architecture_design_patterns.tanks.command.MoveCommand
import ru.geracimov.otus.architecture_design_patterns.tanks.command.RotateCommand
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

internal class ExceptionHandlerTest {
    private val exceptionHandler = object : ExceptionHandler {
        override fun handle(command: Command, exception: Exception) {}
    }

    @Test
    fun sameCommandExceptionReturnSameResultTest() {
        val hashCode1 = exceptionHandler.hashCodeOf(MoveCommand::class, IllegalStateException::class)
        val hashCode2 = exceptionHandler.hashCodeOf(MoveCommand::class, IllegalStateException::class)
        assertEquals(hashCode1, hashCode2)
    }

    @Test
    fun differentCommandExceptionReturnDifferentResultTest() {
        val hashCode1 = exceptionHandler.hashCodeOf(MoveCommand::class, IllegalStateException::class)
        val hashCode2 = exceptionHandler.hashCodeOf(RotateCommand::class, IndexOutOfBoundsException::class)
        assertNotEquals(hashCode1, hashCode2)
    }

    @Test
    fun sameCommandDifferentExceptionReturnDifferentResultTest() {
        val hashCode1 = exceptionHandler.hashCodeOf(MoveCommand::class, IllegalStateException::class)
        val hashCode2 = exceptionHandler.hashCodeOf(MoveCommand::class, IndexOutOfBoundsException::class)
        assertNotEquals(hashCode1, hashCode2)
    }

    @Test
    fun differentCommandSameExceptionReturnDifferentResultTest() {
        val hashCode1 = exceptionHandler.hashCodeOf(MoveCommand::class, IllegalStateException::class)
        val hashCode2 = exceptionHandler.hashCodeOf(RotateCommand::class, IllegalStateException::class)
        assertNotEquals(hashCode1, hashCode2)
    }

    @Test
    fun newInstancesCommandExceptionReturnSameResultTest() {
        val command1 = MoveCommand(MovableAdapter(UObjectImpl(HashMap())))
        val command2 = MoveCommand(MovableAdapter(UObjectImpl(HashMap())))
        val exception1 = IllegalStateException()
        val exception2 = IllegalStateException()
        assertNotEquals(command1, command2)
        assertNotEquals(exception1, exception2)

        val hashCode1 = exceptionHandler.hashCodeOf(command1, exception1)
        val hashCode2 = exceptionHandler.hashCodeOf(command2, exception2)
        assertEquals(hashCode1, hashCode2)
    }

}