package ru.geracimov.otus.architecture_design_patterns.tanks.ioc

interface IocStorage {

    fun isScopeExists(scopeId: String): Boolean

    fun addScope(scopeId: String)

    fun checkoutScope(scopeId: String)

    fun isDependencyExists(dependencyId: String): Boolean

    fun addDependency(dependencyId: String, dependency: Any)

    fun removeDependency(dependencyId: String)

    fun getDependency(dependencyId: String): Any

}