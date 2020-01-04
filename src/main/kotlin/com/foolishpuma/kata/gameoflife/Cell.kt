package com.foolishpuma.kata.gameoflife

private const val NEIGHBOR_LIVE_CELL_MIN_COUNT = 2
private const val NEIGHBOR_LIVE_CELL_MAX_COUNT = 3
private const val NEIGHBOR_DEAD_CELL_COUNT = 3

data class Cell(val status: CellStatus = CellStatus.DEAD) {

    fun isAlive() = status == CellStatus.ALIVE
    fun isDead() = status == CellStatus.DEAD
    fun cellLives(neighbors: Neighbors) = liveCellSurvives(neighbors) || deadCellResurrects(neighbors)

    private fun liveCellSurvives(neighbors: Neighbors) =
            isAlive() && neighbors.liveCount() in NEIGHBOR_LIVE_CELL_MIN_COUNT..NEIGHBOR_LIVE_CELL_MAX_COUNT

    private fun deadCellResurrects(neighbors: Neighbors) =
            isDead() && neighbors.liveCount() == NEIGHBOR_DEAD_CELL_COUNT
}

enum class CellStatus {
    ALIVE,
    DEAD
}

fun liveCell() = Cell(CellStatus.ALIVE)
fun deadCell() = Cell(CellStatus.DEAD)
