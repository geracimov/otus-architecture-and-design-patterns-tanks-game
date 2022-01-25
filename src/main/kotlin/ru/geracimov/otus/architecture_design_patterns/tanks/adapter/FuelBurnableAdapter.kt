package ru.geracimov.otus.architecture_design_patterns.tanks.adapter

import ru.geracimov.otus.architecture_design_patterns.tanks.UObject
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.FuelBurnable.Companion.FUEL_BURN_RATE
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.FuelCheckable.Companion.FUEL_LEVEL

class FuelBurnableAdapter(private val obj: UObject) : FuelBurnable {

    override fun burnFuel() {
        val newFuelLevel = getFuelLevel() - getFuelBurnRate()
        obj.setProperty(FUEL_LEVEL, newFuelLevel)
    }

    private fun getFuelLevel(): Int {
        return obj.getProperty(FUEL_LEVEL)
    }

    private fun getFuelBurnRate(): Int {
        return obj.getProperty(FUEL_BURN_RATE)
    }

}