package com.sportdafa.dafahome

import android.content.Context
import com.orhanobut.hawk.Hawk
import com.sportdafa.dafahome.Const.C1
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class Async(val context: Context) {

    private var jsoup: String? = "null"
    private val hawk : String? = Hawk.get(C1,"null")
    private var forJsoupSet: String = Const.lru + Const.odone + hawk

    suspend fun getDocSecretKey(): String?{
        withContext(Dispatchers.IO){
            val doc = Jsoup.connect(forJsoupSet).get()
            jsoup = doc.text().toString()
        }
        return jsoup
    }
}