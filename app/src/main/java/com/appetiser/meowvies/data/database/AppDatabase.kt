package com.appetiser.meowvies.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.appetiser.meowvies.data.dao.RecentVisitedMovieDao
import com.appetiser.meowvies.data.model.Movie
import com.appetiser.meowvies.helper.Constants.DATABASE_NAME


/**
 * Database creation
 */
@Database(
    entities = [Movie.MovieList::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase(){
    abstract fun RecentVisitedMovieDao(): RecentVisitedMovieDao
    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         *  @param context to get the main context of the activity
         * @return a single instance of the database
         */
        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}