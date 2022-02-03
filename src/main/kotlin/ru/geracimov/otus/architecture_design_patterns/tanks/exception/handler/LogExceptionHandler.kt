package ru.geracimov.otus.architecture_design_patterns.tanks.exception.handler

import ru.geracimov.otus.architecture_design_patterns.tanks.command.Command
import ru.geracimov.otus.architecture_design_patterns.tanks.command.LogExceptionCommand

class LogExceptionHandler(private val queue: ArrayDeque<Command>) : ExceptionHandler {
    override fun handle(command: Command, exception: Exception) {
        val logExceptionCommand = LogExceptionCommand(command, exception)
        queue.add(logExceptionCommand)
    }
}