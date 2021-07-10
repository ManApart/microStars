package power

import systems.ShipSystem
import persistence.PersistedSystem

class PersistedEngine(
    val health: Int,
    val powerProduced: Int,
    val totalPowerCapacity: Int,
    val power: Int
) : PersistedSystem {
    constructor(engine: Engine) : this(
        engine.health,
        engine.powerProduced,
        engine.totalPowerCapacity,
        engine.power
    )

    override fun toSystem(): ShipSystem {
        return Engine(health, powerProduced, totalPowerCapacity).also { it.power = power }
    }
}