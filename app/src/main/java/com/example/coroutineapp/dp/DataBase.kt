package com.example.coroutineapp.dp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [User::class], version = 1)
 abstract class DataBase : RoomDatabase() {
    abstract fun UserDao(): UserDao?
    //single tone

}