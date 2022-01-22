package ru.geracimov.otus.architecture_design_patterns.tanks.command

open class RepeatCommand(private val repeatableCommand: Command) : AbstractCommand() {

    override fun execute() {
        repeatableCommand.execute()
    }
}