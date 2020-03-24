package com.tanmay.therona.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tanmay.therona.db.dao.BoardDao
import com.tanmay.therona.db.dao.TaskDao
import com.tanmay.therona.db.dao.TasklistDao
import com.tanmay.therona.entities.Board
import com.tanmay.therona.entities.Task
import com.tanmay.therona.entities.Tasklist

@Database(
    entities = arrayOf(
        Board::class,
        Tasklist::class,
        Task::class
    ), version = 1, exportSchema = false
)
abstract class AppDb : RoomDatabase() {

    abstract fun boardDao(): BoardDao
    abstract fun tasklistDao(): TasklistDao
    abstract fun taskDao(): TaskDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDb? = null

        fun getDatabase(context: Context): AppDb {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java,
                    "app_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }

}