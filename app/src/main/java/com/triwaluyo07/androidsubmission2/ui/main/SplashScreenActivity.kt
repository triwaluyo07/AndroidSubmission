package com.triwaluyo07.androidsubmission2.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.triwaluyo07.androidsubmission2.R

class SplashScreenActivity : AppCompatActivity() {
    lateinit var handler: Handler

    lateinit var topAnimation: Animation
    lateinit var bottomAnimation: Animation
    lateinit var logo: ImageView
    lateinit var txtLogo: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        //Animation
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        logo = findViewById(R.id.logo)
        txtLogo = findViewById(R.id.txtGithubUser)

        logo.setAnimation(topAnimation)
        txtLogo.setAnimation(bottomAnimation)

        val delay: Int = 2000

        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                finish()
            },
            2000
        )
    }
}