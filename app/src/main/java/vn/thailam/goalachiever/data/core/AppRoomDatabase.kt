package vn.thailam.goalachiever.data.core

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import vn.thailam.goalachiever.data.goal.dao.GoalDao
import vn.thailam.goalachiever.data.goal.entity.Goal

@Database(entities = [(Goal::class)], version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun goalDao(): GoalDao

    companion object {
        private const val NAME = "app_main_db"

        fun build(context: Context): AppRoomDatabase {
            return Room.databaseBuilder(
                context,
                AppRoomDatabase::class.java,
                NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}