package ru.geracimov.otus.architecture_design_patterns.tanks.ioc.impl

import ru.geracimov.otus.architecture_design_patterns.tanks.ioc.IocScope
import ru.geracimov.otus.architecture_design_patterns.tanks.ioc.IocStorage

class IocStorageImpl(
    private val iocScopeSupplier: (String, IocScope?) -> IocScope =
        { scopeId: String, parent: IocScope? -> IocScopeImpl(scopeId, parent) },
    private val storage: MutableMap<String, IocScope> = mutableMapOf(),
) : IocStorage {
    private var currentScope: IocScope = iocScopeSupplier.invoke("ROOT", null)

    override fun isScopeExists(scopeId: String): Boolean {
        return storage.containsKey(scopeId)
    }

    override fun isDependencyExists(dependencyId: String): Boolean {
        return scopeWithDependencyIdExists(currentScope, dependencyId)
    }

    override fun addScope(scopeId: String) {
        if (isScopeExists(scopeId)) {
            throw IllegalArgumentException("Scope '$scopeId' already exists")
        }

        storage[scopeId] = iocScopeSupplier.invoke(scopeId, currentScope)
    }

    override fun checkoutScope(scopeId: String) {
        currentScope = storage.computeIfAbsent(scopeId) { iocScopeSupplier.invoke(scopeId, currentScope) }
    }

    override fun addDependency(dependencyId: String, dependency: Any) {
        currentScope.addDependency(dependencyId, dependency)
    }

    /*  override fun removeDependency(scopeId: String, dependencyId: String) {
          if (!isDependencyExists(scopeId, dependencyId)) {
              throw IllegalArgumentException("Dependency '$dependencyId' not exists in scope '$scopeId'")
          }
          storage[scopeId]?.removeDependency(dependencyId)
      } */
    override fun removeDependency(dependencyId: String) {
        scopeWithDependencyId(currentScope, dependencyId).removeDependency(dependencyId)
    }

    override fun getDependency(dependencyId: String): Any {
        return scopeWithDependencyId(currentScope, dependencyId).getDependency(dependencyId)
    }

    private fun scopeWithDependencyId(currentScope: IocScope?, dependencyId: String): IocScope {
        if (currentScope == null) {
            throw IllegalArgumentException("Dependency '$dependencyId' not exists in scopes")
        }
        return if (currentScope.isDependencyExists(dependencyId)) currentScope
        else scopeWithDependencyId(currentScope.getParentScope(), dependencyId)
    }

    private fun scopeWithDependencyIdExists(currentScope: IocScope?, dependencyId: String): Boolean {
        if (currentScope == null) {
            return false
        }
        return if (currentScope.isDependencyExists(dependencyId)) true
        else scopeWithDependencyIdExists(currentScope.getParentScope(), dependencyId)
    }

}