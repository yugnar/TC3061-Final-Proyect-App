package edu.itesm.tiendaperroingles.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import edu.itesm.tiendaperroingles.R
import edu.itesm.tiendaperroingles.databinding.ActivitySplashBinding
import edu.itesm.tiendaperroingles.databinding.FragmentRegisterBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler().postDelayed({

            startActivity(Intent(this, MainActivity::class.java))

            // close this activity
            finish()
        }, 3000)
    }
}