package ru.geracimov.otus.architecture_design_patterns.tanks.adapter

interface FuelCheckable {
    fun isFuelEnough(): Boolean

    companion object {
        const val FUEL_LEVEL = "FuelLevel"
    }
}