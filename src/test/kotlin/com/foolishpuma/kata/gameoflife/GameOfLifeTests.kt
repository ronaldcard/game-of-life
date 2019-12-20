package com.foolishpuma.kata.gameoflife

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class GameOfLifeTests {

    @Test
    fun `live cell with less than 2 live neighbors, will die`() {
        val cell = Cell(status = CellStatus.ALIVE)
        val neighbors = Neighbors()

        assertThat(GameWarden.cellLives(cell, neighbors)).isFalse()
    }

    @Test
    fun `live cell with 2 or 3 live neighbors, will live`() {
        val cell = Cell(status = CellStatus.ALIVE)

        val twoLiveNeighbors = Neighbors(
                top = Cell(status = CellStatus.ALIVE),
                left = Cell(status = CellStatus.ALIVE)
        )

        assertThat(GameWarden.cellLives(cell, twoLiveNeighbors)).isTrue()

        val threeLiveNeighbors = Neighbors(
                top = Cell(status = CellStatus.ALIVE),
                left = Cell(status = CellStatus.ALIVE),
                bottomRight = Cell(status = CellStatus.ALIVE)
        )

        assertThat(GameWarden.cellLives(cell, threeLiveNeighbors)).isTrue()
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

        assertThat(GameWarden.cellLives(cell, neighbors)).isFalse()
    }

    @Test
    fun `dead cell with exactly 3 live neighbors, will resurrect`() {
        val cell = Cell(status = CellStatus.DEAD)

        val neighbors = Neighbors(
                top = Cell(status = CellStatus.ALIVE),
                left = Cell(status = CellStatus.ALIVE),
                bottomRight = Cell(status = CellStatus.ALIVE)
        )

        assertThat(GameWarden.cellLives(cell, neighbors)).isTrue();
    }
}