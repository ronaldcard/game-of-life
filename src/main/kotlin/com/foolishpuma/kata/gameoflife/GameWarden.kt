package com.foolishpuma.kata.gameoflife

typealias World = Array<Array<Cell>>

class GameWarden(private val world: World) {

    fun simulate(): World {
        val newWorld: World = Array(world.size) { Array(world[1].size) { liveCell() } }

        for (rowIndex in world.indices) {
            for (columnIndex in world[rowIndex].indices) {
                val currentCell = world[rowIndex][columnIndex]
                val neighbors = getNeighbors(world, WorldPosition(rowIndex, columnIndex))
                val cellLives = currentCell.cellLives(neighbors)

                if (cellLives) {
                    newWorld[rowIndex][columnIndex] = liveCell()
                } else {
                    newWorld[rowIndex][columnIndex] = deadCell()
                }
            }
        }

        return newWorld
    }

    private fun getNeighbors(world: World, position: WorldPosition): Neighbors {

        val rowCount = world.size
        val columnCount = world[1].size
        val isNotFirstRow = position.row > 0
        val isNotLastRow = position.row < rowCount - 1
        val isNotFirstColumn = position.column > 0
        val isNotLastColumn = position.column < columnCount - 1

        val neighbors = mutableSetOf<Neighbor>()

        if (isNotFirstRow && isNotFirstColumn) addTopLeftNeighbor(world, position, neighbors)
        if (isNotFirstRow) addTopNeighbor(world, position, neighbors)
        if (isNotFirstRow && isNotLastColumn) addTopRightNeighbor(world, position, neighbors)
        if (isNotFirstColumn) addLeftNeighbor(world, position, neighbors)
        if (isNotLastColumn) addRightNeighbor(world, position, neighbors)
        if (isNotLastRow && isNotFirstColumn) addBottomLeftNeighbor(world, position, neighbors)
        if (isNotLastRow) addBottomNeighbor(world, position, neighbors)
        if (isNotLastRow && isNotLastColumn) addBottomRightNeighbor(world, position, neighbors)

        return neighbors
    }

    private fun addTopLeftNeighbor(world: World, position: WorldPosition, neighbors: MutableSet<Neighbor>) {
        neighbors.add(Neighbor(NeighborPosition.TOP_LEFT, world[position.top][position.left]))
    }

    private fun addTopNeighbor(world: World, position: WorldPosition, neighbors: MutableSet<Neighbor>) {
        neighbors.add(Neighbor(NeighborPosition.TOP, world[position.top][position.column]))
    }

    private fun addTopRightNeighbor(world: World, position: WorldPosition, neighbors: MutableSet<Neighbor>) {
        neighbors.add(Neighbor(NeighborPosition.TOP_RIGHT, world[position.top][position.right]))
    }

    private fun addLeftNeighbor(world: World, position: WorldPosition, neighbors: MutableSet<Neighbor>) {
        neighbors.add(Neighbor(NeighborPosition.LEFT, world[position.row][position.left]))
    }

    private fun addRightNeighbor(world: World, position: WorldPosition, neighbors: MutableSet<Neighbor>) {
        neighbors.add(Neighbor(NeighborPosition.RIGHT, world[position.row][position.right]))
    }

    private fun addBottomLeftNeighbor(world: World, position: WorldPosition, neighbors: MutableSet<Neighbor>) {
        neighbors.add(Neighbor(NeighborPosition.BOTTOM_LEFT, world[position.bottom][position.left]))
    }

    private fun addBottomNeighbor(world: World, position: WorldPosition, neighbors: MutableSet<Neighbor>) {
        neighbors.add(Neighbor(NeighborPosition.BOTTOM, world[position.bottom][position.column]))
    }

    private fun addBottomRightNeighbor(world: World, position: WorldPosition, neighbors: MutableSet<Neighbor>) {
        neighbors.add(Neighbor(NeighborPosition.BOTTOM_RIGHT, world[position.bottom][position.right]))
    }
}

data class WorldPosition(val row: Int, val column: Int) {
    val top = row - 1
    val bottom = row + 1
    val left = column - 1
    val right = column + 1
}