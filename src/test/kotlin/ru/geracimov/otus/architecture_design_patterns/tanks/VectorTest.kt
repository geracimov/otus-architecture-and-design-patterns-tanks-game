package ru.geracimov.otus.architecture_design_patterns.tanks

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import ru.geracimov.otus.architecture_design_patterns.tanks.ru.geracimov.otus.architecture_design_patterns.tanks.Vector

internal class VectorTest {

    @Test
    fun vectorsPlusOperatorTest() {
        val v1 = Vector(intArrayOf(1, 2))
        val v2 = Vector(intArrayOf(3, -1))
        val result = Vector.plus(v1, v2)
        assertEquals(4, result.dimensionByIndex(0))
        assertEquals(1, result.dimensionByIndex(1))
    }

}