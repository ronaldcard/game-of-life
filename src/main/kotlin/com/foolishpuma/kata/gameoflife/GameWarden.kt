package com.foolishpuma.kata.gameoflife

typealias World = Array<Array<Cell>>

data class WorldPosition(val row: Int, val column: Int) {
    val top = row - 1
    val bottom = row + 1
    val left = column - 1
    val right = column + 1
}

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
}

private fun getNeighbors(world: World, worldPosition: WorldPosition): Neighbors {

    val rowCount = world.size
    val columnCount = world[1].size
    val isNotFirstRow = worldPosition.row > 0
    val isNotLastRow = worldPosition.row < rowCount - 1
    val isNotFirstColumn = worldPosition.column > 0
    val isNotLastColumn = worldPosition.column < columnCount - 1

    val neighbors = mutableSetOf<Neighbor>()

    if (isNotFirstRow && isNotFirstColumn)
        addTopLeftNeighbor(world, worldPosition, neighbors)

    if (isNotFirstRow)
        addTopNeighbor(world, worldPosition, neighbors)

    if (isNotFirstRow && isNotLastColumn)
        addTopRightNeighbor(world, worldPosition, neighbors)

    if (isNotFirstColumn)
        addLeftNeighbor(world, worldPosition, neighbors)

    if (isNotLastColumn)
        addRightNeighbor(world, worldPosition, neighbors)

    if (isNotLastRow && isNotFirstColumn)
        addBottomLeftNeighbor(world, worldPosition, neighbors)

    if (isNotLastRow)
        addBottomNeighbor(world, worldPosition, neighbors)

    if (isNotLastRow && isNotLastColumn)
        addBottomRightNeighbor(world, worldPosition, neighbors)

    return neighbors
}

private fun addTopLeftNeighbor(world: World, worldPosition: WorldPosition, neighbors: MutableSet<Neighbor>) {
    neighbors.add(Neighbor(NeighborPosition.TOP_LEFT, world[worldPosition.top][worldPosition.left]))
}

private fun addTopNeighbor(world: World, worldPosition: WorldPosition, neighbors: MutableSet<Neighbor>) {
    neighbors.add(Neighbor(NeighborPosition.TOP, world[worldPosition.top][worldPosition.column]))
}

private fun addTopRightNeighbor(world: World, worldPosition: WorldPosition, neighbors: MutableSet<Neighbor>) {
    neighbors.add(Neighbor(NeighborPosition.TOP_RIGHT, world[worldPosition.top][worldPosition.right]))
}

private fun addLeftNeighbor(world: World, worldPosition: WorldPosition, neighbors: MutableSet<Neighbor>) {
    neighbors.add(Neighbor(NeighborPosition.LEFT, world[worldPosition.row][worldPosition.left]))
}

private fun addRightNeighbor(world: World, worldPosition: WorldPosition, neighbors: MutableSet<Neighbor>) {
    neighbors.add(Neighbor(NeighborPosition.RIGHT, world[worldPosition.row][worldPosition.right]))
}

private fun addBottomLeftNeighbor(world: World, worldPosition: WorldPosition, neighbors: MutableSet<Neighbor>) {
    neighbors.add(Neighbor(NeighborPosition.BOTTOM_LEFT, world[worldPosition.bottom][worldPosition.left]))
}

private fun addBottomNeighbor(world: World, worldPosition: WorldPosition, neighbors: MutableSet<Neighbor>) {
    neighbors.add(Neighbor(NeighborPosition.BOTTOM, world[worldPosition.bottom][worldPosition.column]))
}

private fun addBottomRightNeighbor(world: World, worldPosition: WorldPosition, neighbors: MutableSet<Neighbor>) {
    neighbors.add(Neighbor(NeighborPosition.BOTTOM_RIGHT, world[worldPosition.bottom][worldPosition.right]))
}
