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
}