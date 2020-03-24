package com.tanmay.therona.entities

data class TaskList(
    val taskListId : Long,
    val name: String,
    val tasks : List<Task>
)