package ru.geracimov.otus.architecture_design_patterns.tanks.ioc.impl

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ru.geracimov.otus.architecture_design_patterns.tanks.ioc.IocStorage
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

private const val SCOPE_ID0 = "scopeId0"
private const val SCOPE_ID1 = "scopeId1"
private const val SCOPE_ID2 = "scopeId2"
private const val SCOPE_ID3 = "scopeId3"

private const val DEP_ID0 = "dependencyId0"
private const val DEP_ID1 = "dependencyId1"
private const val DEP_ID2 = "dependencyId2"
private const val DEP_ID3 = "dependencyId3"
private const val DEP_ID4 = "dependencyId4"

private const val DEP_0 = "DEPENDENCY0"
private const val DEP_1 = "DEPENDENCY1"
private const val DEP_2 = "DEPENDENCY2"
private const val DEP_3 = "DEPENDENCY3"
private const val DEP_4 = "DEPENDENCY4"

internal class IocStorageImplTest {
    private lateinit var storage: IocStorage

    @BeforeEach
    fun setUp() {
        storage = IocStorageImpl()
        storage.addScope(SCOPE_ID1)
        storage.addDependency(DEP_ID1, DEP_1)
        storage.addDependency(DEP_ID2, DEP_2)
        storage.addScope(SCOPE_ID2)
        storage.addDependency(DEP_ID3, DEP_3)
        storage.addDependency(DEP_ID4, DEP_4)
    }

    @Test
    fun isScopeExists() {
        assertTrue(storage.isScopeExists(SCOPE_ID1))
        assertTrue(storage.isScopeExists(SCOPE_ID2))
        assertFalse(storage.isScopeExists(SCOPE_ID3))
        assertFalse(storage.isScopeExists(""))
    }

    @Test
    fun isDependencyExists() {
        assertTrue(storage.isDependencyExists(DEP_ID1))
        assertTrue(storage.isDependencyExists(DEP_ID2))
        assertTrue(storage.isDependencyExists(DEP_ID3))
        assertTrue(storage.isDependencyExists(DEP_ID4))
        assertFalse(storage.isDependencyExists(DEP_ID0))
        assertFalse(storage.isDependencyExists(""))
    }

    @Test
    fun addScope() {
        assertFalse(storage.isScopeExists(SCOPE_ID0))
        storage.addScope(SCOPE_ID0)
        assertTrue(storage.isScopeExists(SCOPE_ID0))
        assertThrows<IllegalArgumentException> { storage.addScope(SCOPE_ID0) }
    }

    @Test
    fun addDependency() {
        assertFalse(storage.isDependencyExists(DEP_ID0))
        storage.addDependency(DEP_ID0, DEP_0)
        assertTrue(storage.isDependencyExists(DEP_ID0))
        assertEquals(DEP_0, storage.getDependency(DEP_ID0))
        assertThrows<IllegalArgumentException> { storage.addDependency(DEP_ID0, DEP_0) }
    }

    @Test
    fun removeDependency() {
        assertTrue(storage.isDependencyExists(DEP_ID1))
        storage.removeDependency(DEP_ID1)
        assertFalse(storage.isDependencyExists(DEP_ID1))
        assertThrows<IllegalArgumentException> { storage.removeDependency(DEP_ID1) }
    }

    @Test
    fun getDependency() {
        assertThrows<IllegalArgumentException> {
            storage.getDependency(DEP_ID0)
        }
    }
}