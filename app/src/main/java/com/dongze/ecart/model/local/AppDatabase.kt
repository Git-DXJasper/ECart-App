package com.dongze.ecart.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dongze.ecart.model.local.DBConstants.DATABASE_NAME
import com.dongze.ecart.model.local.DBConstants.DATABASE_VERSION

@Database(entities = [InCartItem::class], version = DATABASE_VERSION, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getInCartItemDao(): InCartItemDao

    companion object{
        private var INSTANCE: AppDatabase?= null

        fun getInstance(context: Context): AppDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as AppDatabase
        }
    }
}