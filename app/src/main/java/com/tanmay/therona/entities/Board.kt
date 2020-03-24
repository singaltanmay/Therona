package com.tanmay.therona.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class Board(
    @PrimaryKey(autoGenerate = true) @NotNull var boardId: Long? = null,
    var name: String? = null,
    @Ignore
    var tasklists: MutableList<Tasklist>? = mutableListOf()
)