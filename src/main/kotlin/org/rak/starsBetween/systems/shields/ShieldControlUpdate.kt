package org.rak.starsBetween.systems.shields

import org.rak.starsBetween.systems.shields.Shield

class ShieldControlUpdate(
    val id: Int,
    val frequency: Int,
    val currentDesiredPower: Int
) {
    constructor(shield: Shield, id: Int) : this(id, shield.frequency, shield.currentDesiredPower)
}
