package com.foolishpuma.kata.gameoflife

data class Neighbors(
        val topLeft: Cell? = null,
        val top: Cell? = null,
        val topRight: Cell? = null,
        val left: Cell? = null,
        val right: Cell? = null,
        val bottomLeft: Cell? = null,
        val bottom: Cell? = null,
        val bottomRight: Cell? = null
) {
    private val list = listOf(topLeft, top, topRight, left, right, bottomLeft, bottom, bottomRight)

    fun liveCount() = list.count{ it?.status == CellStatus.ALIVE }
}