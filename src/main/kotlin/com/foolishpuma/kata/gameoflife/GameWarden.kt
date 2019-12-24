package com.foolishpuma.kata.gameoflife

private const val NEIGHBOR_LIVE_CELL_MIN_COUNT = 2
private const val NEIGHBOR_LIVE_CELL_MAX_COUNT = 3

class GameWarden(
        private val simulationIterations: Int,
        private val world: Array<Array<Cell>>
) {

    fun simulate(): Array<Array<Cell>> {
        val newWorld: Array<Array<Cell>> = world.clone()

        for (rowIndex in world.indices) {
            for (columnIndex in world[rowIndex].indices) {
                val currentCell = world[rowIndex][columnIndex]
                val neighbors = getNeighbors(world, WorldPosition(rowIndex, columnIndex))
                val cellLives = currentCell.cellLives(neighbors)
                println("cellLives [$cellLives]")

                if (cellLives) {
                    newWorld[rowIndex][columnIndex] = liveCell()
                } else {
                    newWorld[rowIndex][columnIndex] = deadCell()
                }
            }
        }

        return newWorld
    }

    private fun getNeighbors(world: Array<Array<Cell>>, position: WorldPosition): Neighbors {

        val isNotFirstRow = position.row > 0
        val isNotLastRow = position.row < world.size - 1
        val isNotFirstColumn = position.column > 0
        val isNotLastColumn = position.column < world[1].size - 1
        val neighbors = Neighbors()

        // topLeft
        if (isNotFirstRow && isNotFirstColumn) {
            neighbors.topLeft = world[position.row - 1][position.column - 1]
        }

        // top
        if (isNotFirstRow) {
            neighbors.top = world[position.row - 1][position.column]
        }

        // topRight
        if (isNotFirstRow && isNotLastColumn) {
            neighbors.topRight = world[position.row - 1][position.column + 1]
        }

        // left
        if (isNotFirstColumn) {
            neighbors.left = world[position.row][position.column - 1]
        }

        // right
        if (isNotLastColumn) {
            neighbors.right = world[position.row][position.column + 1]
        }

        // bottomLeft
        if (isNotLastRow && isNotFirstColumn) {
            neighbors.bottomLeft = world[position.row + 1][position.column - 1]
        }

        // bottom
        if (isNotLastRow) {
            neighbors.bottom = world[position.row + 1][position.column]
        }

        // bottomRight
        if (isNotLastRow && isNotLastColumn) {
            neighbors.bottomRight = world[position.row + 1][position.column + 1]
        }

        println("[${position.row}][${position.column}] $neighbors")
        return neighbors
    }
}

data class WorldPosition(val row: Int, val column: Int)