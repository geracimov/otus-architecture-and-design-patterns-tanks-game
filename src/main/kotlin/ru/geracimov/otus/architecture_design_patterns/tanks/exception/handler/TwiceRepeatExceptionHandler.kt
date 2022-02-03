package ru.geracimov.otus.architecture_design_patterns.tanks.exception.handler

import ru.geracimov.otus.architecture_design_patterns.tanks.command.Command
import ru.geracimov.otus.architecture_design_patterns.tanks.command.TwiceRepeatCommand

class TwiceRepeatExceptionHandler(private val queue: ArrayDeque<Command>) : ExceptionHandler {
    override fun handle(command: Command, exception: Exception) {
        val repeatCommand = TwiceRepeatCommand(command)
        queue.add(repeatCommand)
    }
}