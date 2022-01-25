package ru.geracimov.otus.architecture_design_patterns.tanks.exception

class CommandException(override val message: String) : RuntimeException(message)