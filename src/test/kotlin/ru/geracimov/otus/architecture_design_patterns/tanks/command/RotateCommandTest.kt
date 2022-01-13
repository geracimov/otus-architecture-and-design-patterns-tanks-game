package ru.geracimov.otus.architecture_design_patterns.tanks.command

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.geracimov.otus.architecture_design_patterns.tanks.UObject
import ru.geracimov.otus.architecture_design_patterns.tanks.UObjectImpl
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.Rotable.Companion.ANGULAR_VELOCITY
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.Rotable.Companion.DIRECTION
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.Rotable.Companion.MAX_DIRECTIONS
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.RotableAdapter
import kotlin.test.assertFailsWith

internal class RotateCommandTest {
    private lateinit var uObject: UObject

    @BeforeEach
    internal fun setUp() {
        uObject = UObjectImpl(HashMap())
    }

    @Test
    fun rotateCommandExecutedSuccessTest() {
        uObject.setProperty(DIRECTION, 4)
        uObject.setProperty(ANGULAR_VELOCITY, 6)
        uObject.setProperty(MAX_DIRECTIONS, 8)
        RotateCommand(RotableAdapter(uObject)).execute()
        val property = uObject.getProperty(DIRECTION) as Int
        assertEquals(2, property)
    }

    @Test
    fun rotateCommandWithoutDirectionTest() {
        uObject.setProperty(ANGULAR_VELOCITY, 6)
        uObject.setProperty(MAX_DIRECTIONS, 8)
        assertFailsWith<IllegalStateException>(block = { RotateCommand(RotableAdapter(uObject)).execute() })
    }

    @Test
    fun rotateCommandWithoutAngularVelocityTest() {
        uObject.setProperty(DIRECTION, 4)
        uObject.setProperty(MAX_DIRECTIONS, 8)
        assertFailsWith<IllegalStateException>(block = { RotateCommand(RotableAdapter(uObject)).execute() })
    }

    @Test
    fun rotateCommandWithoutMaxDirectionsTest() {
        uObject.setProperty(DIRECTION, 4)
        uObject.setProperty(ANGULAR_VELOCITY, 6)
        assertFailsWith<IllegalStateException>(block = { RotateCommand(RotableAdapter(uObject)).execute() })
    }

}