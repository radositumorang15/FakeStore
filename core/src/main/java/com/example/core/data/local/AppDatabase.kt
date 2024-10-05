package com.example.core.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.core.data.local.dao.ProductDao
import com.example.core.data.local.entity.LocalProduct

@Database(entities = [LocalProduct::class], version = 2,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addMigrations(MIGRATION_1_2) // Add migration
                    .fallbackToDestructiveMigrationFrom(1) // Fallback to destructive migration from version 1
                    .build()
                INSTANCE = instance
                instance
            }
        }

        // Migration from version 1 to version 2
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Implement the migration logic here if necessary
                // For example, add new columns, create tables, etc.
                // database.execSQL("ALTER TABLE product ADD COLUMN newColumn TEXT DEFAULT ''")
            }
        }
    }
}
