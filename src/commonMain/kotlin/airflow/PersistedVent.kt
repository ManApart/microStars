package airflow

import systems.ShipSystem
import persistence.PersistedSystem

class PersistedVent(
    val health: Int,
    val airProduced: Int,
    val totalPowerCapacity: Int,
    val powerConsumedPerTick: Int,
    val power: Int
) : PersistedSystem {

    constructor(vent: Vent) : this(
        vent.health,
        vent.airProduced,
        vent.totalPowerCapacity,
        vent.powerConsumedPerTick,
        vent.power
    )

    override fun toSystem(): ShipSystem {
        return Vent(health, airProduced, totalPowerCapacity, powerConsumedPerTick).also { it.power = power }
    }
}