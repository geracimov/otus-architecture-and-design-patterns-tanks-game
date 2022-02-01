package ru.geracimov.otus.architecture_design_patterns.tanks.exception.handler

import ru.geracimov.otus.architecture_design_patterns.tanks.command.Command
import ru.geracimov.otus.architecture_design_patterns.tanks.command.RepeatCommand

class RepeatExceptionHandler(private val queue: ArrayDeque<Command>) : ExceptionHandler {
    override fun handle(command: Command, exception: Exception) {
        val repeatCommand = RepeatCommand(command)
        queue.add(repeatCommand)
    }
}