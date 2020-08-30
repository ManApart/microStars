package org.rak.microStars

import org.junit.Assert.assertEquals
import org.junit.Test
import org.rak.microStars.airflow.pushAir
import org.rak.microStars.floorplan.FloorPlan

class AirSimulatorTest {

    @Test
    fun setupTest() {
        val floorPlan = createFloorPlanWithAir(
            listOf(
                listOf(0, 0, 0),
                listOf(0, 100, 0),
                listOf(0, 0, 0)
            )
        )

        val expected = listOf(
            listOf(0, 0, 0),
            listOf(0, 100, 0),
            listOf(0, 0, 0)
        )

        val airMap = floorPlan.getTileMap().map { row -> row.value.map { column -> column.value.air } }
        assertEquals(expected, airMap)
    }

    @Test
    fun simpleEvenDistribution() {
        val floorPlan = createFloorPlanWithAir(
            listOf(
                listOf(0, 0, 0),
                listOf(0, 100, 0),
                listOf(0, 0, 0)
            )
        )

        val expected = listOf(
            listOf(0, 20, 0),
            listOf(20, 20, 20),
            listOf(0, 20, 0)
        )

        val tile = floorPlan.getTile(1, 1)
        val neighbors = floorPlan.getNeighbors(tile).filter { !it.isSolid() && it.air != 100 }
        pushAir(tile, neighbors)

        val airMap = floorPlan.getTileMap().map { row -> row.value.map { column -> column.value.air } }
        assertEquals(expected, airMap)
    }

    @Test
    fun simpleUnevenDistribution() {
        val floorPlan = createFloorPlanWithAir(
            listOf(
                listOf(0, 0, 0),
                listOf(0, 100, 100),
                listOf(0, 0, 0)
            )
        )

        val expected = listOf(
            listOf(0, 25, 0),
            listOf(25, 25, 100),
            listOf(0, 25, 0)
        )

        val tile = floorPlan.getTile(1, 1)
        val neighbors = floorPlan.getNeighbors(tile).filter { !it.isSolid() && it.air != 100 }
        pushAir(tile, neighbors)

        val airMap = floorPlan.getTileMap().map { row -> row.value.map { column -> column.value.air } }
        assertEquals(expected, airMap)
    }


    private fun createFloorPlanWithAir(plan: List<List<Int>>): FloorPlan {
        val floorPlan = FloorPlan(plan.size)
        plan.toMap().entries.forEach { (y, entry) ->
            entry.entries.forEach { (x, air) ->
                floorPlan.getTile(x, y).air = air
            }
        }
        return floorPlan
    }


}