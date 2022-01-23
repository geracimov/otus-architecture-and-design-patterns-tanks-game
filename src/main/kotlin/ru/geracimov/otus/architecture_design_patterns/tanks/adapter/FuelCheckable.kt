package ru.geracimov.otus.architecture_design_patterns.tanks.adapter

interface FuelCheckable {
    fun isFuelEnough(fuelQuantity: Int): Boolean

    companion object {
        const val FUEL_LEVEL = "FuelLevel"
    }
}