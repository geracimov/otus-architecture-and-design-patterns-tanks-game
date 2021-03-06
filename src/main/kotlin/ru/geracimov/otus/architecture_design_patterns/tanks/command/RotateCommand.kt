package ru.geracimov.otus.architecture_design_patterns.tanks.command

import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.Rotable
import kotlin.math.abs

open class RotateCommand(private val rotable: Rotable) : AbstractCommand() {

    override fun execute() {
        val newDirection =  abs(rotable.getDirection() + rotable.getAngularVelocity()) % rotable.getMaxDirections()
        rotable.setDirection(newDirection)
    }
}