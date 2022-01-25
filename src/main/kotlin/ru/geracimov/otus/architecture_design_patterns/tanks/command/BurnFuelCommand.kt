package ru.geracimov.otus.architecture_design_patterns.tanks.command

import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.FuelBurnable

open class BurnFuelCommand(private val fuelBurnable: FuelBurnable) : AbstractCommand() {

    override fun execute() {
        fuelBurnable.burnFuel()
    }
}