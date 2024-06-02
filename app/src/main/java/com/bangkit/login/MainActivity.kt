package com.bangkit.login

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bangkit.login.auth.LoginActivity
import com.bangkit.login.classification.ScannerActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var user: FirebaseUser
    private lateinit var auth: FirebaseAuth
    private lateinit var textView: TextView
    private lateinit var txtLogout: TextView
    private lateinit var cvScanner: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        txtLogout = findViewById(R.id.tv_logout)
        textView = findViewById(R.id.tv_user)
        cvScanner = findViewById(R.id.cv_scanner)

        user = auth.currentUser!!
        if (user == null) {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            textView.text = user.email
        }

        txtLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        cvScanner.setOnClickListener {
            val intent = Intent(applicationContext, ScannerActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
