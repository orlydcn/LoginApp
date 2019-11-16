package io.orly.loginapp.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import io.orly.loginapp.data.local.room.dao.UserDao
import io.orly.loginapp.data.local.room.entity.UserEntity
import io.orly.loginapp.util.AppConstant
import io.orly.loginapp.util.AppConstant.DATABASE_VERSION
import io.orly.loginapp.util.AppExecutors

@Database(
    entities = [
        UserEntity::class
    ], version = DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao


    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context, appExecutors: AppExecutors): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context, appExecutors).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context, appExecutors: AppExecutors): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                AppConstant.DATABASE_NAME
            ).addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    appExecutors.networkIO().execute {
                        getInstance(context, appExecutors).userDao().insert(
                            UserEntity(
                                id = 1,
                                email = "test@value.io",
                                name = "Orlando",
                                password = "pas#w0rd"
                            )
                        )
                    }
                }
            }).fallbackToDestructiveMigration().build()
        }
    }
}