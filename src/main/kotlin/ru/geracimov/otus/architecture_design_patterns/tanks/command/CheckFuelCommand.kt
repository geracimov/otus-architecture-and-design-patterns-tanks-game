package ru.geracimov.otus.architecture_design_patterns.tanks.command

import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.FuelCheckable
import ru.geracimov.otus.architecture_design_patterns.tanks.exception.CommandException

open class CheckFuelCommand(
    private val fuelCheckable: FuelCheckable,
    private val fuelQuantity: Int
) : AbstractCommand() {

    override fun execute() {
        if (!fuelCheckable.isFuelEnough(fuelQuantity))
            throw CommandException("Required fuel quantity is not enough")
    }
}