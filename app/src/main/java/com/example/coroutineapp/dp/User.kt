package com.example.coroutineapp.dp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (@PrimaryKey(autoGenerate = true)
                 @ColumnInfo(name ="uId") var uId:Int?=null,
                 @ColumnInfo(name="First_Name") var name:String,
                 @ColumnInfo(name="Last_Name") val lastName:String,
                 @ColumnInfo(name="age") val age:Int,
                 @ColumnInfo(name="employee") val emp:Boolean)

{
  // @PrimaryKey(autoGenerate = true) var uId:Int?=null
}