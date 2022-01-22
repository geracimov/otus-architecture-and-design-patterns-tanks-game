package ru.geracimov.otus.architecture_design_patterns.tanks

data class Vector(private val body: IntArray) {

    fun dimensionByIndex(index: Int): Int {
        return body[index]
    }

    fun plus(vector: Vector): Vector {
        for (i in 0 until this.body.size) this.body[i] += vector.body[i]
        return this
    }

    override fun toString(): String {
        return body.contentToString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vector

        if (!body.contentEquals(other.body)) return false

        return true
    }

    override fun hashCode(): Int {
        return body.contentHashCode()
    }

}