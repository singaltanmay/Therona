package com.tanmay.therona.ui.boards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tanmay.therona.entities.Board
import kotlinx.coroutines.launch

class BoardsViewModel : ViewModel() {

    private var dbServe: DataAccessInterface? = null

    fun setDataAccessInterface(dai: DataAccessInterface?) = run { dbServe = dai }

    fun saveNewBoard(board: Board) {
        viewModelScope.launch {
            dbServe?.saveNewBoard(board)
        }
    }

    fun getBoardCount() = dbServe?.getAllBoards()?.size

    interface DataAccessInterface {

        fun getAllBoards(): List<Board>
        fun getBoardById(id: Long): Board
        suspend fun saveNewBoard(b: Board)
    }

}
