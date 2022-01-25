package ru.geracimov.otus.architecture_design_patterns.tanks.adapter

interface FuelBurnable {
    fun burnFuel()

    companion object {
        const val FUEL_BURN_RATE = "FuelBurnRate"
    }
}