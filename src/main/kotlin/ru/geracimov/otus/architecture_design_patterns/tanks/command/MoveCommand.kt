package ru.geracimov.otus.architecture_design_patterns.tanks.command

import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.Movable

open class MoveCommand(private val movable: Movable) : AbstractCommand() {

    override fun execute() {
        val newPosition = movable.getPosition().plus(movable.getVelocity())
        movable.setPosition(newPosition)
    }
}