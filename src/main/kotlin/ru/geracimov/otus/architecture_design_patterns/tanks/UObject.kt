package ru.geracimov.otus.architecture_design_patterns.tanks

interface UObject {
    fun <T> getProperty(key: String): T
    fun <T> setProperty(key: String, value: T)
}