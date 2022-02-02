package ru.geracimov.otus.architecture_design_patterns.tanks

@Suppress("UNCHECKED_CAST")
class UObjectImpl(private val properties: MutableMap<String, Any> = mutableMapOf()) : UObject {

    override fun <T> getProperty(key: String): T {
        return properties[key] as T
            ?: throw IllegalStateException("Object does not have the requested property: $key")
    }

    override fun <T> setProperty(key: String, value: T) {
        if (value == null) {
            throw IllegalArgumentException("Value cannot be null")
        }
        properties[key] = value
    }
}

