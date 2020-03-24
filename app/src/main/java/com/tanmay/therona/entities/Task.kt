package com.tanmay.therona.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) @NotNull val taskId: Long,
    val body: String,
    val taskListEntityId: Long
)