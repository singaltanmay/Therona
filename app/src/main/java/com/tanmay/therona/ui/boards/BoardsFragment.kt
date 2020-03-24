package com.tanmay.therona.ui.boards

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tanmay.therona.MainActivity
import com.tanmay.therona.R
import com.tanmay.therona.entities.Board
import com.tanmay.therona.ui.tasklists.TasklistsFragment
import kotlinx.android.synthetic.main.boards_fragment.*

class BoardsFragment : Fragment() {

    private lateinit var viewModel: BoardsViewModel
    private lateinit var adapter: BoardRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.boards_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(BoardsViewModel(context as BoardsViewModel.DataAccessInterface)::class.java)
        addEmptyBoard()
        setViewModelObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = BoardRecyclerAdapter(context!!, onBoardClickLambda)
        fragment_board_recycler_view.adapter = adapter
    }

    val onBoardClickLambda: (Long) -> Unit = { id ->
        (activity as MainActivity).navigateTo(TasklistsFragment(id))
    }

    fun setViewModelObservers() {
        viewModel.allBoards.observe(viewLifecycleOwner, Observer {
            it.let {
                refreshAdapterData(it)
                Log.v("epmn", it.size.toString())
            }
        })
    }


    fun refreshAdapterData(data: List<Board>) {
        adapter.data = data
        adapter.notifyDataSetChanged()
    }

    fun addEmptyBoard() {
        Thread {
            viewModel.saveNewBoard(Board())
//            Log.v(
//                BoardsFragment::class.java.simpleName,
//                "There are ${viewModel.getBoardCount()} boards."
//            )
        }.start()
    }

}
