package com.foolishpuma.kata.gameoflife

private const val NEIGHBOR_LIVE_CELL_MIN_COUNT = 2
private const val NEIGHBOR_LIVE_CELL_MAX_COUNT = 3

class GameWarden {
    companion object {
        @JvmStatic
        fun cellLives(cell: Cell, neighbors: Neighbors) = liveCellSurvives(cell, neighbors) || deadCellResurrects(cell, neighbors)

        private fun liveCellSurvives(cell: Cell, neighbors: Neighbors) =
                cell.status == CellStatus.ALIVE && neighbors.liveCount() in NEIGHBOR_LIVE_CELL_MIN_COUNT..NEIGHBOR_LIVE_CELL_MAX_COUNT

        private fun deadCellResurrects(cell: Cell, neighbors: Neighbors) = cell.status == CellStatus.DEAD && neighbors.liveCount() == 3
    }
}