package com.foolishpuma.kata.gameoflife

data class Cell(
        val status: CellStatus = CellStatus.DEAD
) {
    fun isAlive(neighbors: Neighbors) = neighbors.liveCount() > 2
}