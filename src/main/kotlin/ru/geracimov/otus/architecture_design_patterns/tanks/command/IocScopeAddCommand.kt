package ru.geracimov.otus.architecture_design_patterns.tanks.command

import ru.geracimov.otus.architecture_design_patterns.tanks.ioc.IocStorage

class IocScopeAddCommand(
    private val scopeId: String,
    private val iocStorage: IocStorage
) : AbstractCommand() {

    override fun execute() {
        iocStorage.addScope(scopeId)
    }

}