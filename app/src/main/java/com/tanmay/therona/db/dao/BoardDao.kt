package com.tanmay.therona.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tanmay.therona.entities.Board

@Dao
interface BoardDao {

    @Query("SELECT * FROM board")
    fun getAllBoards(): List<Board>

    @Query("SELECT * FROM board WHERE boardId = :boardId")
    fun getBoard(boardId: Long): Board

    @Insert
    suspend fun insertBoard(board: Board)

}