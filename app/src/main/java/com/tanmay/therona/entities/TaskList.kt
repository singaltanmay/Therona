package com.tanmay.therona.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class TaskList(
    @PrimaryKey(autoGenerate = true) @NotNull val taskListId: Long,
    val name: String,
    @Ignore
    val tasks: List<Task>,
    val boardEntityId: Long
)