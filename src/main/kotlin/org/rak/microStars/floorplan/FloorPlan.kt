package org.rak.microStars.floorplan

import org.rak.microStars.tile.*

class FloorPlan(val size: Int = 5) {
    private val tiles = (0 until size).associate { x ->
        (x to (0 until size).associateWith { y ->
            SPACE.copy(position = Position(x, y))
        }.toMutableMap())
    }

    fun getTileMap() :Map<Int, Map<Int, Tile>> {
        return tiles.toMap()
    }

    fun getAllTiles(): List<Tile> {
        return tiles.values.flatMap { it.values }
    }

    fun getTile(pos: Position): Tile {
        return getTile(pos.x, pos.y)
    }

    fun getTile(x: Int, y: Int): Tile {
        return if (x in 0 until size && y in 0 until size) {
            tiles[x]!![y] ?: DEFAULT_TILE
        } else {
            DEFAULT_TILE
        }
    }

    fun setTile(tile: Tile, pos: Position) {
        setTile(tile, pos.x, pos.y)
    }

    fun setTile(tile: Tile, x: Int, y: Int) {
        if (x in 0 until size && y in 0 until size) {
            val newTile = tile.copy(position = Position(x, y))
            tiles[x]!![y] = newTile
            orient(newTile, this)
            getNeighbors(newTile).forEach { orient(it, this) }
        }
    }

    fun getNeighbors(tile: Tile) : List<Tile> {
        return tile.position.neighbors().map { getTile(it) }.filter { it != DEFAULT_TILE }
    }
}

fun fromSimpleFloorPlan(simpleFloorPlan: SimpleFloorPlan) : FloorPlan{
    val size = simpleFloorPlan.tiles.flatten().maxBy { it.x }?.x ?: 10
    val floorPlan = FloorPlan(size)
    simpleFloorPlan.tiles.flatten().map {
        floorPlan.setTile(fromSimpleTile(it), it.x, it.y)
    }

    return floorPlan
}