package ru.geracimov.otus.architecture_design_patterns.tanks.command

import java.lang.System.getLogger

open class LogExceptionCommand(
    private val command: Command,
    private val exception: Exception
) : AbstractCommand() {

    override fun execute() {
        loggerWithExplicitClass.log(System.Logger.Level.INFO, "Command $command threw exception: ${exception.message}")
    }

    companion object {
        private val loggerWithExplicitClass = getLogger(LogExceptionCommand::class.qualifiedName)
    }
}