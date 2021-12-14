package com.example.food_ordering_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity2 : AppCompatActivity() {
    private lateinit var session: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val string=intent.getStringExtra("string")
        val apiclient=application as ApiClient
        session=SessionManager(this)
        var intent= Intent(this,User_Login::class.java)

        var token=session.fetchAuthToken()
        val items:MutableList<FooditemData> = mutableListOf<FooditemData>()
        if(session.fetchAuthToken()!=null){
            CoroutineScope(Dispatchers.IO).launch{
                val result=apiclient.apiService.GetDishes("Bearer "+token)
                var i=0
                if(result.isSuccessful){
                    while(i<result.body()?.dishes!!.size){
                        items.add(result.body()?.dishes!![i])
                        i+=1
                    }
                }
                else
                {
                    startActivity(intent)
                }
                withContext(Dispatchers.Main){
                    val recycle=findViewById<RecyclerView>(R.id.recycle)
                    recycle.adapter=AdapterClass(items)
                    recycle.layoutManager= LinearLayoutManager(this@MainActivity2)
                }

            }
        }
      else
            startActivity(intent)
    }
}