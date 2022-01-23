package ru.geracimov.otus.architecture_design_patterns.tanks.adapter

import ru.geracimov.otus.architecture_design_patterns.tanks.UObject
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.FuelCheckable.Companion.FUEL_LEVEL

class FuelCheckableAdapter(private val obj: UObject) : FuelCheckable {

    override fun isFuelEnough(fuelQuantity: Int): Boolean {
        return getFuelLevel() >= fuelQuantity
    }

    private fun getFuelLevel(): Int {
        return obj.getProperty(FUEL_LEVEL)
    }

}