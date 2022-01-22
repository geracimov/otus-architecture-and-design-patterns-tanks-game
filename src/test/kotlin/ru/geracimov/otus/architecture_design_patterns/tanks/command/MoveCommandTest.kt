package ru.geracimov.otus.architecture_design_patterns.tanks.command

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.geracimov.otus.architecture_design_patterns.tanks.UObject
import ru.geracimov.otus.architecture_design_patterns.tanks.UObjectImpl
import ru.geracimov.otus.architecture_design_patterns.tanks.Vector
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.Movable.Companion.POSITION
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.Movable.Companion.VELOCITY
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.MovableAdapter
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class MoveCommandTest {
    private lateinit var uObject: UObject

    @BeforeEach
    internal fun setUp() {
        uObject = UObjectImpl(HashMap())
    }

    @Test
    fun moveCommandExecutedSuccessTest() {
        uObject.setProperty(POSITION, Vector(intArrayOf(12, 5)))
        uObject.setProperty(VELOCITY, Vector(intArrayOf(-7, 3)))

        MoveCommand(MovableAdapter(uObject)).execute()

        val expected = uObject.getProperty<Vector>(POSITION)
        assertEquals(expected, Vector(intArrayOf(5, 8)))
    }

    @Test
    fun cannotMoveObjectWithoutPositionTest() {
        uObject.setProperty(VELOCITY, Vector(intArrayOf(-7, 3)))

        assertFailsWith<IllegalStateException>(block = { MoveCommand(MovableAdapter(uObject)).execute() })
    }

    @Test
    fun cannotMoveObjectWithoutVelocityTest() {
        uObject.setProperty(POSITION, Vector(intArrayOf(12, 5)))

        assertFailsWith<IllegalStateException>(block = { MoveCommand(MovableAdapter(uObject)).execute() })
    }

}