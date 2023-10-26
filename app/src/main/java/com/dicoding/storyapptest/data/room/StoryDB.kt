package com.dicoding.storyapptest.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dicoding.storyapptest.data.response.ListStoryItem


@Database(entities = [ListStoryItem::class], version = 3, exportSchema = false)
abstract class StoryDB : RoomDatabase() {
    abstract fun storyDao(): StoryDao
    companion object {
        @Volatile
        private var INSTANCE: StoryDB? = null
        @JvmStatic
        fun getDatabase(context: Context): StoryDB {
            if (INSTANCE == null) {
                synchronized(StoryDB::class.java) {
                    val MIGRATION_2_3: Migration = object : Migration(2, 3) {
                        override fun migrate(database: SupportSQLiteDatabase) {
                            database.execSQL("ALTER TABLE stories ADD COLUMN description TEXT")
                        }
                    }
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        StoryDB::class.java, "story.db")
                        .fallbackToDestructiveMigration()
                        .addMigrations(MIGRATION_2_3)
                        .build()
                }
            }
            return INSTANCE as StoryDB
        }
    }
}