package ru.geracimov.otus.architecture_design_patterns.tanks.command

import ru.geracimov.otus.architecture_design_patterns.tanks.exception.CommandException

open class MacroCommand(private val commands: List<Command>) : AbstractCommand() {

    override fun execute() {
        try {
            commands.forEach(Command::execute)
        } catch (e: Exception) {
            throw CommandException(e.message ?: "Command thrown exception: $e")
        }
    }

}