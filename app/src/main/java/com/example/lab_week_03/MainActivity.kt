package com.example.lab_week_03

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentContainerView

interface CoffeeListener {
    fun onSelected(id: Int)
}

class MainActivity : AppCompatActivity(), CoffeeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Atur padding sesuai system bars (status bar/navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Tambahkan ListFragment pertama kali (saat activity baru dibuat)
        if (savedInstanceState == null) {
            val listFragment = ListFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, listFragment)
                .commit()
        }
    }

    override fun onSelected(id: Int) {
        // Ganti ListFragment dengan DetailFragment secara dinamis
        val detailFragment = DetailFragment.newInstance(id)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailFragment)
            .addToBackStack(null) // supaya bisa back ke ListFragment
            .commit()
    }
}
