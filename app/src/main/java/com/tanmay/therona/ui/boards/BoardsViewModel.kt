package com.tanmay.therona.ui.boards

import androidx.lifecycle.ViewModel
import com.tanmay.therona.entities.Board

class BoardsViewModel : ViewModel() {

    private var dbServe: DataAccessInterface? = null

    fun setDataAccessInterface(dai: DataAccessInterface?) = run { dbServe = dai }


    fun saveNewBoard(board: Board) = dbServe?.saveNewBoard(board)
    fun getBoardCount() = dbServe?.getAllBoards()?.size

    interface DataAccessInterface {

        fun getAllBoards(): List<Board>
        fun getBoardById(id: Long): Board
        fun saveNewBoard(b: Board)
    }

}
