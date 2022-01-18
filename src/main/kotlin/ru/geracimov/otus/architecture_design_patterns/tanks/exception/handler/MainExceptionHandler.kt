package ru.geracimov.otus.architecture_design_patterns.tanks.exception.handler

import ru.geracimov.otus.architecture_design_patterns.tanks.command.Command

class MainExceptionHandler(private val handlers: Map<Int, ExceptionHandler>) : ExceptionHandler {

    override fun handle(command: Command, exception: Exception) {
        val key = hashCodeOf(command, exception)
        handlers[key]?.handle(command, exception)
    }
}