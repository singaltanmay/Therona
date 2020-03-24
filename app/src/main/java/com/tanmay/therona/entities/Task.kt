package com.tanmay.therona.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) @NotNull var taskId: Long? = null,
    var body: String? = null,
    var taskListEntityId: Long? = null
)