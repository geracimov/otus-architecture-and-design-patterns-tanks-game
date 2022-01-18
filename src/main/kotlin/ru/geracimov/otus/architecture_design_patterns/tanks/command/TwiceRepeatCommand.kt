package ru.geracimov.otus.architecture_design_patterns.tanks.command

class TwiceRepeatCommand(private val repeatableCommand: Command) : Command {

    override fun execute() {
        repeatableCommand.execute()
    }
}