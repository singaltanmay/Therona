package com.tanmay.therona.db.repository

import com.tanmay.therona.db.dao.BoardDao
import com.tanmay.therona.entities.Board

class BoardRepository(private val dao: BoardDao) {

    fun getAllBoards() = dao.getAllBoards()

    fun getBoardById(boardId: Long) = dao.getBoard(boardId)

    fun insertBoard(board: Board) = dao.insertBoard(board)

}