package com.sportdafa.dafahome

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.orhanobut.hawk.Hawk
import kotlinx.coroutines.*

class Invite : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite)
        val txtEr: TextView = findViewById(R.id.txtMainMain)

        runBlocking {

            val job: Job = GlobalScope.launch(Dispatchers.IO) {
                getAsync(applicationContext)
            }
            job.join()
            val jsoup: String? = Hawk.get(Const.asyncResult, "")
            Log.d("cora", "cora $jsoup")

            txtEr.text = jsoup

            if (jsoup == "B5c6") {
                Intent(applicationContext, ActivityInUp::class.java).also { startActivity(it) }
            } else {
                Intent(applicationContext, BrAct::class.java).also { startActivity(it) }
            }
            finish()
        }
    }

    private suspend fun getAsync(context: Context) {
        val asyncKey = Async(context)
        val asyncResult = asyncKey.getDocSecretKey()
        Hawk.put(Const.asyncResult, asyncResult)
    }
}