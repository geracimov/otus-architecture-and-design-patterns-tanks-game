package ru.geracimov.otus.architecture_design_patterns.tanks.ioc.impl

import ru.geracimov.otus.architecture_design_patterns.tanks.ioc.IocScope

class IocScopeImpl(
    private val id: String,
    private val parent: IocScope? = null,
    private val storage: MutableMap<String, Any> = mutableMapOf(),
) : IocScope {

    override fun getParentScope(): IocScope? {
        return parent
    }

    override fun isDependencyExists(dependencyId: String): Boolean {
        return storage.containsKey(dependencyId)
    }

    override fun addDependency(dependencyId: String, dependency: Any) {
        if (isDependencyExists(dependencyId)) {
            throw IllegalArgumentException("Dependency '$dependencyId' already exists in scope '$id'")
        }
        storage[dependencyId] = dependency
    }

    override fun removeDependency(dependencyId: String) {
        if (!isDependencyExists(dependencyId)) {
            throw IllegalArgumentException("Dependency '$dependencyId' not exists in scope '$id'")
        }
        storage.remove(dependencyId)
    }

    override fun getDependency(dependencyId: String): Any {
        if (!isDependencyExists(dependencyId)) {
            throw IllegalArgumentException("Dependency '$dependencyId' not exists in scope '$id'")
        }
        return storage[dependencyId]!!
    }

}