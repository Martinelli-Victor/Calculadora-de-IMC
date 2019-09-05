package com.example.calculadoraimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Launch : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        Handler().postDelayed({

            val intentMainActivity = Intent(this, MainActivity::class.java)

            //Iniciando a tela de
            startActivity(intentMainActivity)

        },3000)


    }
}
