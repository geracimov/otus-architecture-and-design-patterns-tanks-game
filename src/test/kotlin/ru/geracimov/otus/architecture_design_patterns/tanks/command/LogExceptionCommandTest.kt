package ru.geracimov.otus.architecture_design_patterns.tanks.command

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertTrue

internal class LogExceptionCommandTest {
    private lateinit var logExceptionCommand: Command
    private lateinit var mockCommand: Command

    @BeforeEach
    internal fun setUp() {
        mockCommand = mock {
            on { toString() } doReturn "MockCommand"
        }
        logExceptionCommand = LogExceptionCommand(mockCommand, IllegalStateException("SomeException"))
    }

    @Test
    fun execute() {
        val systemErr = System.err
        val byteArrayOutputStream = ByteArrayOutputStream(1024)
        val byteArraySystemErr = PrintStream(byteArrayOutputStream)

        System.setErr(byteArraySystemErr)
        logExceptionCommand.execute()
        System.setErr(systemErr)

        val printedMessage = byteArrayOutputStream.toString().trimEnd()
        println(printedMessage)
        assertTrue(printedMessage.endsWith("INFO: Command MockCommand threw exception: SomeException"))
    }
}