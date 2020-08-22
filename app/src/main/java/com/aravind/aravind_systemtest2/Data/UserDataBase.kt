package com.aravind.aravind_systemtest2.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aravind.aravind_systemtest2.UserDao
import com.aravind.aravind_systemtest2.model.User

@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {
    abstract val userDao: UserDao?
}