package ru.geracimov.otus.architecture_design_patterns.tanks.adapter

import ru.geracimov.otus.architecture_design_patterns.tanks.UObject
import ru.geracimov.otus.architecture_design_patterns.tanks.Vector
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.Movable.Companion.POSITION
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.Movable.Companion.VELOCITY

class MovableAdapter(private val obj: UObject) : Movable {

    override fun getPosition(): Vector {
        return obj.getProperty(POSITION)
    }

    override fun setPosition(position: Vector) {
        obj.setProperty(POSITION, position)
    }

    override fun getVelocity(): Vector {
        return obj.getProperty(VELOCITY)
    }
}