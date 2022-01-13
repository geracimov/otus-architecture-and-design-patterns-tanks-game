package ru.geracimov.otus.architecture_design_patterns.tanks.adapter

import ru.geracimov.otus.architecture_design_patterns.tanks.Vector

interface Movable {
    fun getPosition(): Vector
    fun setPosition(position: Vector)
    fun getVelocity(): Vector

    companion object {
        const val POSITION = "Position"
        const val VELOCITY = "Velocity"
    }
}