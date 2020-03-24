package com.tanmay.therona.db.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tanmay.therona.db.dao.BoardDao
import com.tanmay.therona.entities.Board

class BoardRepository(private val dao: BoardDao) {

    fun getAllBoards(): List<Board> {
        return dao.getAllBoards()
    }

    fun getBoardById(boardId: Long): MutableLiveData<Board> {
        return MutableLiveData<Board>().apply {
            value = dao.getBoard(boardId)
        }
    }

    suspend fun insertBoard(board: Board) = dao.insertBoard(board)

}