package edu.itesm.tiendaperroingles.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.itesm.tiendaperroingles.R
import edu.itesm.tiendaperroingles.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}