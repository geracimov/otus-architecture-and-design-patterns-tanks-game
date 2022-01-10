package ru.geracimov.otus.architecture_design_patterns.tanks.ru.geracimov.otus.architecture_design_patterns.tanks

class Vector(private val body: IntArray) {

    fun dimensionByIndex(index: Int): Int {
        return body[index]
    }

    companion object {
        fun plus(v1: Vector, v2: Vector): Vector {
            val newBody = Array(v1.body.size) { i -> v1.body[i] + v2.body[i] }.toIntArray()
            return Vector(newBody)
        }
    }
}