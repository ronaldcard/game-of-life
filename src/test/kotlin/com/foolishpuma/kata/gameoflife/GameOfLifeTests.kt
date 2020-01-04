package com.foolishpuma.kata.gameoflife

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GameOfLifeTests {

    class RulesTests {
        @Test
        fun `live cell with less than 2 live neighbors, will die`() {
            val cell = Cell(CellStatus.ALIVE)
            val neighbors = setOf<Neighbor>()

            assertThat(cell.cellLives(neighbors)).isFalse()
        }

        @Test
        fun `live cell with 2 or 3 live neighbors, will live`() {
            val cell = Cell(CellStatus.ALIVE)

            val twoLiveNeighbors = setOf(
                    Neighbor(NeighborPosition.LEFT, Cell(CellStatus.ALIVE)),
                    Neighbor(NeighborPosition.RIGHT,Cell(CellStatus.ALIVE))
            )

            assertThat(cell.cellLives(twoLiveNeighbors)).isTrue()

            val threeLiveNeighbors = setOf(
                    Neighbor(NeighborPosition.TOP, Cell(CellStatus.ALIVE)),
                    Neighbor(NeighborPosition.LEFT, Cell(CellStatus.ALIVE)),
                    Neighbor(NeighborPosition.BOTTOM_RIGHT, Cell(CellStatus.ALIVE))
            )

            assertThat(cell.cellLives(threeLiveNeighbors)).isTrue()
        }

        @Test
        fun `live cell with more than 3 live neighbors, will die`() {
            val cell = Cell(CellStatus.ALIVE)

            val neighbors = setOf(
                    Neighbor(NeighborPosition.TOP, Cell(CellStatus.ALIVE)),
                    Neighbor(NeighborPosition.TOP_RIGHT, Cell(CellStatus.ALIVE)),
                    Neighbor(NeighborPosition.LEFT, Cell(CellStatus.ALIVE)),
                    Neighbor(NeighborPosition.BOTTOM_RIGHT, Cell(CellStatus.ALIVE))
            )

            assertThat(cell.cellLives(neighbors)).isFalse()
        }

        @Test
        fun `dead cell with exactly 3 live neighbors, will resurrect`() {
            val cell = Cell(CellStatus.DEAD)

            val threeLiveNeighbors = setOf(
                    Neighbor(NeighborPosition.TOP, Cell(CellStatus.ALIVE)),
                    Neighbor(NeighborPosition.LEFT, Cell(CellStatus.ALIVE)),
                    Neighbor(NeighborPosition.BOTTOM_RIGHT, Cell(CellStatus.ALIVE))
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