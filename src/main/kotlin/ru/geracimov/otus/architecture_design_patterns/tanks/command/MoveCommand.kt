package ru.geracimov.otus.architecture_design_patterns.tanks.command

import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.Movable

class MoveCommand(private val movable: Movable) : Command {

    override fun execute() {
        val newPosition = movable.getPosition().plus(movable.getVelocity())
        movable.setPosition(newPosition)
    }
}