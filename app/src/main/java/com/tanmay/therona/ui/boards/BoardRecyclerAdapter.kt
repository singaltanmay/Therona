package com.tanmay.therona.ui.boards

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tanmay.therona.R
import com.tanmay.therona.entities.Board

class BoardRecyclerAdapter(
    val context: Context,
    val onClickNavigationLambda: ((Long) -> (Unit))?,
    var data: List<Board> = listOf()
) :
    RecyclerView.Adapter<BoardRecyclerAdapter.BoardHolder>() {

    inner class BoardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val parent = itemView
        val boardTitleTextView = itemView.findViewById<TextView>(R.id.item_board_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_board_personal, parent, false)
        return BoardHolder(view)
    }

    override fun onBindViewHolder(holder: BoardHolder, position: Int) {
        val currentBoard = data[position]

        holder.boardTitleTextView.text = currentBoard.name
        holder.parent.setOnClickListener {
            onClickNavigationLambda?.invoke(
                currentBoard.boardId!!
            )
        }
    }

    override fun getItemCount() = data.size

}
