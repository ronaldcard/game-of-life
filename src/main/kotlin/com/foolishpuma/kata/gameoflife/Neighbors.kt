package com.foolishpuma.kata.gameoflife

data class Neighbors(
        val topLeft: Cell = Cell(),
        val top: Cell = Cell(),
        val topRight: Cell = Cell(),
        val left: Cell = Cell(),
        val right: Cell = Cell(),
        val bottomLeft: Cell = Cell(),
        val bottom: Cell = Cell(),
        val bottomRight: Cell = Cell()
) {
    private val list = listOf(topLeft, top, topRight, left, right, bottomLeft, bottom, bottomRight)

    fun liveCount() = list.count{ it.status == CellStatus.ALIVE }
}