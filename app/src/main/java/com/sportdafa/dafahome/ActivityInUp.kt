package com.sportdafa.dafahome

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_in_up.*

class ActivityInUp : AppCompatActivity() {
    private val mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_up)

        sign_up_btn.setOnClickListener{
            createAcc()
        }
        login_btn.setOnClickListener{
            loginAcc()
        }
    }
    private fun loginAcc() {
        val mail = et_mail.text.toString()
        val password = et_password.text.toString()

        when {
            TextUtils.isEmpty(mail) -> {
                et_mail.error = "Email is empty. Enter your email!"
                et_mail.requestFocus()
            }
            TextUtils.isEmpty(password) -> {
                et_password.error = "Password is empty. Create your password!"
                et_password.requestFocus()
            }
            else -> {
                mAuth.signInWithEmailAndPassword(mail, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Log.d(ContentValues.TAG, "signInWithEmail:success")
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)

                        } else {
                            Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }

            }            }

    }
    private fun createAcc() {
        val mail = et_mail.text.toString()
        val password = et_password.text.toString()

        when {
            TextUtils.isEmpty(mail) -> {
                et_mail.error = "Email is empty. Enter your email!"
                et_mail.requestFocus()
            }
            TextUtils.isEmpty(password) -> {
                et_password.error = "Password is empty. Create your password!"
                et_password.requestFocus()
            }

            else -> {
                mAuth.createUserWithEmailAndPassword(mail, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val userId: String? = mAuth.currentUser?.uid
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)

                        } else {
                            Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}