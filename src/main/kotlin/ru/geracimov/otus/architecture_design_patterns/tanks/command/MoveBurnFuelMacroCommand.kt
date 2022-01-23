package ru.geracimov.otus.architecture_design_patterns.tanks.command

import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.FuelBurnable
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.FuelCheckable
import ru.geracimov.otus.architecture_design_patterns.tanks.adapter.Movable

class MoveBurnFuelMacroCommand(
    f: FuelCheckable,
    m: Movable,
    b: FuelBurnable
) : MacroCommand(arrayOf(CheckFuelCommand(f), MoveCommand(m), BurnFuelCommand(b)))