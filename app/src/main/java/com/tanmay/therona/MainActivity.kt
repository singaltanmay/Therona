package com.tanmay.therona

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tanmay.therona.db.AppDb
import com.tanmay.therona.db.repository.BoardRepository
import com.tanmay.therona.entities.Board
import com.tanmay.therona.ui.boards.BoardsFragment
import com.tanmay.therona.ui.boards.BoardsViewModel

class MainActivity : AppCompatActivity(), BoardsViewModel.DataAccessInterface {

    private lateinit var database: AppDb
    private lateinit var boardRepository: BoardRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        getPersistence()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BoardsFragment())
                .commitNow()
        }
    }

    fun getPersistence() {
        database = AppDb.getDatabase(this)
        boardRepository = BoardRepository(database.boardDao())
    }

    override fun getAllBoards(): List<Board> {
        return boardRepository.getAllBoards()
    }

    override fun getBoardById(id: Long): Board {
        return boardRepository.getBoardById(id)
    }

    override fun saveNewBoard(b: Board) {
        return boardRepository.insertBoard(b)
    }
}
