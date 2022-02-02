package ru.geracimov.otus.architecture_design_patterns.tanks.ioc

interface IocScope {

    fun getParentScope(): IocScope?

    fun isDependencyExists(dependencyId: String): Boolean

    fun addDependency(dependencyId: String, dependency: Any)

    fun removeDependency(dependencyId: String)

    fun getDependency(dependencyId: String): Any

}