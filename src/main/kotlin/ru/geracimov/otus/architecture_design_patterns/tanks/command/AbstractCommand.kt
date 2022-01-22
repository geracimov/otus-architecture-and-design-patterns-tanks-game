package ru.geracimov.otus.architecture_design_patterns.tanks.command

abstract class AbstractCommand : Command {

    override fun toString(): String {
        return this::class.simpleName.toString()
    }
}