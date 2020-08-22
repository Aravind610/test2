package com.aravind.aravind_systemtest2

import androidx.room.*
import com.aravind.aravind_systemtest2.model.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User where email= :mail and password= :password")
    fun getUser(mail: String?, password: String?): User?

    @Insert
    fun insert(user: User?)

    @Update
    fun update(user: User?)

    @Delete
    fun delete(user: User?)
}