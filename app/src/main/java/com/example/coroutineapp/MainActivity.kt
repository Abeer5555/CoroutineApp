
package com.example.coroutineapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import android.support.v4.os.IResultReceiver
import android.support.v4.os.IResultReceiver.Default
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.example.coroutineapp.dp.DataBase
import com.example.coroutineapp.dp.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var db:DataBase
   lateinit var textView1:TextView
    lateinit var button :Button
    lateinit var button2:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView1=findViewById(R.id.displyText1)
        button=findViewById(R.id.button1)
        button2=findViewById(R.id.button2)
        db=Room.databaseBuilder(applicationContext,DataBase::class.java,"DataBaseOfUser").build()
        val user1=User(1,"Ahmed","Mohamed",40,true)
        val user2=User(2,"Amr","Mohamed",28,true)
        val user3=User(3,"Eman","Mohamed",35,true)

             GlobalScope.launch{
                 db.UserDao()!!.deleteAll()
                 db.UserDao()!!.insertNewUser(user1,user2,user3)
                 delay(1000)// to make delay 1000 sec in thread is Thread.sleep(1000)
                 ReturnAllDataBase()
               }
              //Main use to interact with main thread and UI,IO Input OutPut request from local database,//
             // Default to do any heavy comppettion work like heavy list ,filters
            // launch is meaning coroutine building
          CoroutineScope(Main).launch{
          }
        GlobalScope.launch(Dispatchers.IO){}

         button.setOnClickListener {

          Toast.makeText(applicationContext, "hellosssssssss", Toast.LENGTH_LONG).show()
             GlobalScope.launch {
                 db.UserDao()!!.deleteUser(user2)
                 ReturnAllDataBase()


                 }

         }
        button2.setOnClickListener {
            Toast.makeText(applicationContext, "user updated", Toast.LENGTH_LONG).show()
            GlobalScope.launch {
                user1.name="Abeer"
                db.UserDao()!!.updateUser(user1)
                ReturnAllDataBase()

            }
        }



    }
     suspend fun ReturnAllDataBase()
    {
         val ss:List<User>
         ss= db.UserDao()!!.getAllUsers()
         var displayTexts=""
      for(x in ss)
        {
            displayTexts +="${x.name} ${x.lastName} ${x.age} ${x.emp}\n"
        }
           withContext(Main){ //or use CoroutineScope(Main).launch{}   // withContext() mean switch context of coroutine what were you mention
               textView1.setText(displayTexts)
           }

    }
}