package ru.geracimov.otus.architecture_design_patterns.tanks.command

class RepeatCommand(private val repeatableCommand: Command) : Command {

    override fun execute() {
        repeatableCommand.execute()
    }
}