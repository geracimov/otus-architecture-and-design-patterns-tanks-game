package ru.geracimov.otus.architecture_design_patterns.tanks.adapter

import ru.geracimov.otus.architecture_design_patterns.tanks.UObject
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.Rotable.Companion.ANGULAR_VELOCITY
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.Rotable.Companion.DIRECTION
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.Rotable.Companion.MAX_DIRECTIONS

class RotableAdapter(private val obj: UObject) : Rotable {

    override fun getDirection(): Int {
        return obj.getProperty(DIRECTION)
    }

    override fun setDirection(direction: Int) {
        obj.setProperty(DIRECTION, direction)
    }

    override fun getAngularVelocity(): Int {
        return obj.getProperty(ANGULAR_VELOCITY)
    }

    override fun getMaxDirections(): Int {
        return obj.getProperty(MAX_DIRECTIONS)
    }
}