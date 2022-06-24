package com.sportdafa.dafahome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private val mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getFootballNews()
        getCricketNews()
        getHockeyNews()
        getFormulaNews()


        val navView:NavigationView = findViewById(R.id.nv_view)

        toggle = ActionBarDrawerToggle(this,drawerActivity, R.string.open, R.string.close)
        drawerActivity.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.page_2 ->{
                    Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.page_1 -> {
                    Toast.makeText(this, "Coming soon", Toast.LENGTH_SHORT).show()
                    true

                }
                R.id.log_out->{
                    mAuth.signOut()
                    startActivity(Intent(this, ActivityInUp::class.java))
                    true
                }

                else -> false

            }
        }

    }
    private fun getFootballNews() {
        val news: Call<News> = Service.newsInst.getArticle(  "sports", "football")

        news.enqueue(object: Callback<News>
        {
            override fun onResponse(call: Call<News>, response: Response<News>) {

                val news: News? = response.body()
                Log.d("onResponse", "Ok")

                if(news!=null) {
                    footballRv.adapter = Adapter(this@MainActivity, news.articles)
                    footballRv.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL, false)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("onFailure", "Bug")
            }
        })
    }

   private fun getCricketNews() {
        val news: Call<News> = Service.newsInst.getArticle("sports", "cricket")

        news.enqueue(object: Callback<News>
        {
            override fun onResponse(call: Call<News>, response: Response<News>) {

                val news: News? = response.body()
                Log.d("onResponse", "Ok")

                if(news!=null) {
                    cricketRv.adapter = Adapter(this@MainActivity, news.articles)
                    cricketRv.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL, false)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("onFailure", "Bug")
            }
        })
    }

    private fun getHockeyNews() {
        val news: Call<News> = Service.newsInst.getArticle("sports", "hockey")

        news.enqueue(object: Callback<News>
        {
            override fun onResponse(call: Call<News>, response: Response<News>) {

                val news: News? = response.body()
                Log.d("onResponse", "Ok")

                if(news!=null) {
                    hockeyRv.adapter = Adapter(this@MainActivity, news.articles)
                    hockeyRv.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL, false)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("onFailure", "Bug")
            }
        })
    }

    private fun getFormulaNews() {
        val news: Call<News> = Service.newsInst.getArticle("sports", "nba")

        news.enqueue(object: Callback<News>
        {
            override fun onResponse(call: Call<News>, response: Response<News>) {

                val news: News? = response.body()
                Log.d("onResponse", "Ok")

                if(news!=null) {
                    iceSkatingRv.adapter = Adapter(this@MainActivity, news.articles)
                    iceSkatingRv.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL, false)
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("onFailure", "Bug")
            }
        })
    }
}