package ru.geracimov.otus.architecture_design_patterns.tanks

class Vector(private val body: IntArray) {

    fun dimensionByIndex(index: Int): Int {
        return body[index]
    }


    fun plus(vector: Vector): Vector {
        val newBody = Array(this.body.size) { i -> this.body[i] + vector.body[i] }.toIntArray()
        return Vector(newBody)
    }

}