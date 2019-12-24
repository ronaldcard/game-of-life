package com.foolishpuma.kata.gameoflife

data class Neighbors(
        var topLeft: Cell? = null,
        var top: Cell? = null,
        var topRight: Cell? = null,
        var left: Cell? = null,
        var right: Cell? = null,
        var bottomLeft: Cell? = null,
        var bottom: Cell? = null,
        var bottomRight: Cell? = null
) {
    private val list = listOf(topLeft, top, topRight, left, right, bottomLeft, bottom, bottomRight)

    fun liveCount() = list.count{ it?.status == CellStatus.ALIVE }
}