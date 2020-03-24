package com.tanmay.therona.ui.boards

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tanmay.therona.entities.Board
import kotlinx.coroutines.launch

class BoardsViewModel() : ViewModel() {

    var dbServe: DataAccessInterface? = null

    constructor(dbServe: DataAccessInterface) : this() {
        this.dbServe = dbServe
        init()
        initialized = true
    }

    var initialized: Boolean = false
        get() {
            return !(this.dbServe == null || this.allBoards == null)
        }

//    fun getBoardCount() = dbServe?.getAllBoards()?.size
//
//fun refreshAllBoards() {
//    CoroutineScope(Dispatchers.IO).launch {
//        val allBoards1 = dbServe?.getAllBoards()
//        setAllBoardsValue(allBoards1)
//    }
//}
//
//    private fun setAllBoardsValue(x: List<Board>?) {
//        _allBoards.value = x
//    }

    private var _allBoards: LiveData<List<Board>>? = null

    var allBoards: LiveData<List<Board>>? = null

    private fun init() {
        _allBoards = dbServe?.getAllBoards()
        allBoards = _allBoards
//        Thread {
//            _allBoards.postValue(dbServe.getAllBoards().value)
//        }.start()
    }

    fun saveNewBoard(board: Board) {
        viewModelScope.launch {
            dbServe?.saveNewBoard(board)
        }
    }

    interface DataAccessInterface {
        fun getAllBoards(): LiveData<List<Board>>
        fun getBoardById(id: Long): MutableLiveData<Board>
        suspend fun saveNewBoard(b: Board)

    }

}
