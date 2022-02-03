package ru.geracimov.otus.architecture_design_patterns.tanks.ioc

import ru.geracimov.otus.architecture_design_patterns.tanks.command.*
import ru.geracimov.otus.architecture_design_patterns.tanks.ioc.impl.IocStorageImpl

@Suppress("UNCHECKED_CAST")
class IoC {
    companion object {
        const val IOC_REGISTER: String = "IoC.Register"
        const val IOC_UNREGISTER: String = "IoC.Unregister"
        const val IOC_SCOPE_ADD: String = "IoC.Scope.Add"
        const val IOC_SCOPE_CHECKOUT: String = "IoC.Scope.Checkout"

        private val storageThreadLocal: ThreadLocal<IocStorage> = ThreadLocal.withInitial { IocStorageImpl() }

        fun <T> resolve(key: String, vararg args: Any): T {
            val storage = storageThreadLocal.get()

            return when (key) {
                IOC_REGISTER -> IocAddDependencyCommand(args[0] as String, args[1] as (Array<Any>) -> Command, storage)
                IOC_UNREGISTER -> IocRemoveDependencyCommand(args[0] as String, storage)
                IOC_SCOPE_ADD -> IocScopeAddCommand(args[0] as String, storage)
                IOC_SCOPE_CHECKOUT -> IocScopeCheckoutCommand(args[0] as String, storage)
                else -> (storage.getDependency(key) as (Array<Any>) -> Command)
                    .invoke(args as Array<Any>)
            } as T
        }
    }
}