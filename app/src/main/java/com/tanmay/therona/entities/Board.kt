package com.tanmay.therona.entities

data class Board(
    val boardId : Long,
    val name: String,
    val tasklists : MutableList<TaskList>
)