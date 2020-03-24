package com.tanmay.therona.ui.boards

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tanmay.therona.R
import com.tanmay.therona.entities.Board

class BoardsFragment : Fragment() {

    private lateinit var viewModel: BoardsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.boards_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BoardsViewModel::class.java)
        if (context is BoardsViewModel.DataAccessInterface) {
            viewModel.setDataAccessInterface(context as BoardsViewModel.DataAccessInterface)
        } else {
            throw RuntimeException("$context must implement DataAccessInterface")
        }

        addEmptyBoard()
    }

    fun addEmptyBoard() {
        Thread {
            viewModel.saveNewBoard(Board())
            Log.v(
                BoardsFragment::class.java.simpleName,
                "There are ${viewModel.getBoardCount()} boards."
            )
        }.start()
    }

}
