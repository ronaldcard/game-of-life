package com.foolishpuma.kata.gameoflife

typealias Neighbors = Set<Neighbor>

data class Neighbor(
        val position: NeighborPosition,
        val cell: Cell)

enum class NeighborPosition {
    TOP_LEFT,
    TOP,
    TOP_RIGHT,
    LEFT,
    RIGHT,
    BOTTOM_LEFT,
    BOTTOM,
    BOTTOM_RIGHT
}

fun Neighbors.liveCount() = this.filter { it.cell.status == CellStatus.ALIVE }.count()