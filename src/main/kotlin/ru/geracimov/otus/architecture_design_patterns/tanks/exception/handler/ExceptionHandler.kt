package ru.geracimov.otus.architecture_design_patterns.tanks.exception.handler

import ru.geracimov.otus.architecture_design_patterns.tanks.command.Command
import kotlin.reflect.KClass

interface ExceptionHandler {

    fun handle(command: Command, exception: Exception)

    fun hashCodeOf(command: Command, exception: Exception): Int {
        return hashCodeOf(command::class, exception::class)
    }

    fun hashCodeOf(command: KClass<out Command>, exception: KClass<out Exception>): Int {
        val commandClassName = command.qualifiedName
        val exceptionClassName = exception.qualifiedName
        return commandClassName.hashCode().times(31).plus(exceptionClassName.hashCode())
    }

}