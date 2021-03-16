package com.example.coroutineapp.dp

import androidx.room.*


@Dao
interface UserDao {
    @Query("select*from User")
    suspend fun getAllUsers():List<User>
    @Insert
    suspend fun insertNewUser( vararg user:User)
    @Delete
    suspend fun deleteUser( vararg user:User)
    @Query("delete from User")
    suspend fun deleteAll()
    @Update
    suspend fun updateUser(user:User)
    @Query("select*from User where uId= :userId ")
    suspend fun getAllUsersById(userId:Int):User
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertorUpdate(user:User):Long
}