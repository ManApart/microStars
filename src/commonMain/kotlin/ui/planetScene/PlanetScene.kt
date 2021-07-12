package ui.planetScene

import com.soywiz.korev.Key
import com.soywiz.korge.input.keys
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korma.geom.vector.line
import planet.Planet
import ui.Resources
import ui.WINDOW_SIZE
import ui.planetScene.planetView.paint

class PlanetScene : Scene() {
    override suspend fun Container.sceneInit() {
        Resources.init()
        val planet = PlanetManager.getPlanet(0)

        fixedSizeContainer(WINDOW_SIZE, WINDOW_SIZE, clip = false) {
            scaleView(WINDOW_SIZE, WINDOW_SIZE, 2.5, filtering = false) {
                paint(planet, PlanetManager.viewType)
            }
        }

        keys {
            up(Key.SPACE) {
                println("Space pressed")
            }
        }

    }
}