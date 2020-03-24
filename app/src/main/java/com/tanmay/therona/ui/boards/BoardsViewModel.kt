package com.tanmay.therona.ui.boards

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tanmay.therona.entities.Board
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BoardsViewModel() : ViewModel() {

    var dbServe: DataAccessInterface? = null

    constructor(dbServe: DataAccessInterface) : this() {
        this.dbServe = dbServe
        init()
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

//    private var _allBoards: MutableLiveData<List<Board>> = MutableLiveData()

    val allBoards: MutableLiveData<List<Board>> = MutableLiveData()

    private fun init() {

        CoroutineScope(Dispatchers.IO).launch {
            Log.v("egmkp", "Launching coroutine")

            dbServe?.saveNewBoard(Board())
            dbServe?.saveNewBoard(Board())
            dbServe?.saveNewBoard(Board())
            dbServe?.saveNewBoard(Board())
            dbServe?.saveNewBoard(Board())
            dbServe?.saveNewBoard(Board())
            dbServe?.saveNewBoard(Board())
            dbServe?.saveNewBoard(Board())
            dbServe?.saveNewBoard(Board())

//            val allBoards1 = dbServe?.getAllBoards()
//            val value = allBoards1?.value
//            Log.v("egmkp", value.toString())
//            value?.forEach {
//                Log.v("egmkp", it.boardId.toString())
//            }
//            _allBoards = allBoards1!!

            val allBoards1 = dbServe?.getAllBoards()
            allBoards.postValue(allBoards1)

        }

//        Thread {
//
//        }.start()
    }

    fun saveNewBoard(board: Board) {
        viewModelScope.launch {
            dbServe?.saveNewBoard(board)
        }
    }

    interface DataAccessInterface {
        fun getAllBoards(): List<Board>
        fun getBoardById(id: Long): MutableLiveData<Board>
        suspend fun saveNewBoard(b: Board)

    }

}
