package ru.geracimov.otus.architecture_design_patterns.tanks.command

import ru.geracimov.otus.architecture_design_patterns.tanks.ioc.IocStorage

class IocAddDependencyCommand(
    private val dependencyId: String,
    private val supplier: (Array<Any>) -> Command,
    private val iocStorage: IocStorage,
) : AbstractCommand() {

    override fun execute() {
        iocStorage.addDependency(dependencyId, supplier)
    }

}