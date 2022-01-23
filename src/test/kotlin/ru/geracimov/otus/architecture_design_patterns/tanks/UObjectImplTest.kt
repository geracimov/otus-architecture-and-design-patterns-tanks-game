package ru.geracimov.otus.architecture_design_patterns.tanks

import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class UObjectImplTest {
    private val uObject = UObjectImpl(HashMap())

    @Test
    fun setProperty() {
        assertFailsWith<IllegalArgumentException>(block = { uObject.setProperty("SomeProp", null) })
    }
}