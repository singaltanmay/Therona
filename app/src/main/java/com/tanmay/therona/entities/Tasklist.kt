package com.tanmay.therona.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class Tasklist(
    @PrimaryKey(autoGenerate = true) @NotNull var taskListId: Long? = null,
    var name: String? = null,
    @Ignore
    var tasks: List<Task>? = mutableListOf(),
    var boardEntityId: Long? = null
)