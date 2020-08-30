package org.rak.microStars.tile

import org.rak.microStars.floorplan.Position

val SPACE = Tile("Space", airProduced = -100)
val FLOOR = Tile("Floor")
val WALL = Tile("Wall", solid = true)
val VENT = Tile("Vent", airProduced = 10)
val DEFAULT_TILE = Tile("Void")

val tileTypes = listOf(
    SPACE,
    FLOOR,
    WALL,
    VENT
)

data class Tile(
    val name: String,
    val position: Position = Position(),
    private val totalHealth: Int = 100,
    private val solid: Boolean = false,
    val airProduced: Int = 0
) {

    var health = totalHealth
    var air = if (solid) {
        0
    } else {
        100
    }
    var adjacency = Adjacency.NONE
    var rotation = 0
    var distanceMap = DistanceMap(this)

    fun isSolid(): Boolean {
        return solid && health > 0
    }

    fun isType(other: Tile): Boolean {
        return name == other.name
    }

    fun isEdgeTile(floorPlanSize: Int): Boolean {
        return position.x == 0 || position.y == 0 || position.x == floorPlanSize - 1 || position.y == floorPlanSize - 1
    }
}

fun fromSimpleTile(simpleTile: SimpleTile): Tile {
    val tile = tileTypes
        .first { it.name.toLowerCase() == simpleTile.name.toLowerCase() }
        .copy(position = Position(simpleTile.x, simpleTile.y), solid = simpleTile.solid)

    tile.health = simpleTile.health
    tile.air = simpleTile.air
    tile.adjacency = simpleTile.adjacency
    tile.rotation = simpleTile.rotation

    return tile
}