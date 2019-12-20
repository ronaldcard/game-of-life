package com.foolishpuma.kata.gameoflife

private const val LIVE_CELL_MIN_COUNT = 2
private const val LIVE_CELL_MAX_COUNT = 3

data class Cell(
        val status: CellStatus = CellStatus.DEAD
) {
    fun isAlive(neighbors: Neighbors) =
            neighbors.liveCount() in LIVE_CELL_MIN_COUNT..LIVE_CELL_MAX_COUNT
}