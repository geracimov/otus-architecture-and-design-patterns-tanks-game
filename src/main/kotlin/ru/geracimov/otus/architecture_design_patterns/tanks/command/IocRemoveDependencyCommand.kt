package ru.geracimov.otus.architecture_design_patterns.tanks.command

import ru.geracimov.otus.architecture_design_patterns.tanks.ioc.IocStorage

class IocRemoveDependencyCommand(
    private val dependencyId: String,
    private val iocStorage: IocStorage
) : AbstractCommand() {

    override fun execute() {
        iocStorage.removeDependency( dependencyId)
    }

}