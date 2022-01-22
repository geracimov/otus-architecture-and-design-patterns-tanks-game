package ru.geracimov.otus.architecture_design_patterns.tanks.adapter

interface Rotable {
    fun getDirection(): Int
    fun setDirection(direction: Int)
    fun getAngularVelocity(): Int
    fun getMaxDirections(): Int

    companion object {
        const val DIRECTION = "Direction"
        const val ANGULAR_VELOCITY = "AngularVelocity"
        const val MAX_DIRECTIONS = "MaxDirections"
    }
}