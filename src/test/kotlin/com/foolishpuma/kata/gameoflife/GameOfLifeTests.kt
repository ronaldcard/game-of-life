package com.foolishpuma.kata.gameoflife

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GameOfLifeTests {

    class RulesTests {
        @Test
        fun `live cell with less than 2 live neighbors, will die`() {
            val cell = liveCell()
            val neighbors = setOf<Neighbor>()

            assertThat(cell.cellLives(neighbors)).isFalse()
        }

        @Test
        fun `live cell with 2 or 3 live neighbors, will live`() {
            val cell = liveCell()

            val twoLiveNeighbors = setOf(
                    Neighbor(NeighborPosition.LEFT, liveCell()),
                    Neighbor(NeighborPosition.RIGHT, liveCell())
            )

            assertThat(cell.cellLives(twoLiveNeighbors)).isTrue()

            val threeLiveNeighbors = setOf(
                    Neighbor(NeighborPosition.TOP, liveCell()),
                    Neighbor(NeighborPosition.LEFT, liveCell()),
                    Neighbor(NeighborPosition.BOTTOM_RIGHT, liveCell())
            )

            assertThat(cell.cellLives(threeLiveNeighbors)).isTrue()
        }

        @Test
        fun `live cell with more than 3 live neighbors, will die`() {
            val cell = Cell(CellStatus.ALIVE)

            val neighbors = setOf(
                    Neighbor(NeighborPosition.TOP, liveCell()),
                    Neighbor(NeighborPosition.TOP_RIGHT, liveCell()),
                    Neighbor(NeighborPosition.LEFT, liveCell()),
                    Neighbor(NeighborPosition.BOTTOM_RIGHT, liveCell())
            )

            assertThat(cell.cellLives(neighbors)).isFalse()
        }

        @Test
        fun `dead cell with exactly 3 live neighbors, will resurrect`() {
            val cell = Cell(CellStatus.DEAD)

            val threeLiveNeighbors = setOf(
                    Neighbor(NeighborPosition.TOP, liveCell()),
                    Neighbor(NeighborPosition.LEFT, liveCell()),
                    Neighbor(NeighborPosition.BOTTOM_RIGHT, liveCell())
            )

            assertThat(cell.cellLives(threeLiveNeighbors)).isTrue();
        }
    }

    class SimulationTests {

        @Test
        fun `2x3 seed world returns expected world`() {

            val seedWorld =
                    arrayOf(arrayOf(liveCell(), deadCell(), liveCell()),
                            arrayOf(deadCell(), liveCell(), deadCell()))

            val warden = GameWarden(seedWorld)

            val newWorld = warden.simulate()

            val expectedWorld =
                    arrayOf(arrayOf(deadCell(), liveCell(), deadCell()),
                            arrayOf(deadCell(), liveCell(), deadCell()))

            assertThat(newWorld).isEqualTo(expectedWorld)
        }
    }
}