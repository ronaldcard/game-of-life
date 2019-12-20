package com.foolishpuma.kata.gameoflife

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GameOfLifeTests {

    @Test
    fun `live cell with less than 2 live neighbors, will die`() {
        val cell = Cell(status = CellStatus.ALIVE)
        val neighbors = Neighbors()

        assertThat(cell.isAlive(neighbors)).isFalse()
    }

    @Test
    fun `live cell with 2 or 3 live neighbors, will live`() {
        val cell = Cell(status = CellStatus.ALIVE)

        val twoLiveNeighbors = Neighbors(
                top = Cell(status = CellStatus.ALIVE),
                left = Cell(status = CellStatus.ALIVE)
        )

        assertThat(cell.isAlive(twoLiveNeighbors)).isTrue()

        val threeLiveNeighbors = Neighbors(
                top = Cell(status = CellStatus.ALIVE),
                left = Cell(status = CellStatus.ALIVE),
                bottomRight = Cell(status = CellStatus.ALIVE)
        )

        assertThat(cell.isAlive(threeLiveNeighbors)).isTrue()
    }

    @Test
    fun `live cell with more than 3 live neighbors, will die`() {
        val cell = Cell(status = CellStatus.ALIVE)

        val neighbors = Neighbors(
                top = Cell(status = CellStatus.ALIVE),
                topRight = Cell(status = CellStatus.ALIVE),
                left = Cell(status = CellStatus.ALIVE),
                bottomRight = Cell(status = CellStatus.ALIVE)
        )

        assertThat(cell.isAlive(neighbors)).isFalse()
    }


}