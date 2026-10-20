
package com.example.project

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project.Home.HomeFragment
import com.example.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val green = Color.parseColor("#207A33")
    private val gray = Color.parseColor("#7B857C")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        binding.itemHome.setOnClickListener {
            navigateToTab(R.id.itemHome)
        }

        binding.itemPlants.setOnClickListener {
            navigateToTab(R.id.itemPlants)
        }

        binding.itemEducation.setOnClickListener {
            navigateToTab(R.id.itemEducation)
        }

        binding.itemProfile.setOnClickListener {
            navigateToTab(R.id.itemProfile)
        }

        if (savedInstanceState == null) {
            navigateToTab(R.id.itemHome)
        }
    }

    fun openPlantWebsite() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://id.wikipedia.org/wiki/Tumbuhan")
        )

        startActivity(intent)
    }

    fun navigateToTab(itemId: Int) {

        val fragment = when (itemId) {
            R.id.itemHome -> HomeFragment()

            R.id.itemPlants -> PlantsFragment()

            R.id.itemEducation -> SimplePageFragment.newInstance(
                title = "Edukasi Tanaman",
                description = "Pelajari tanaman dan lingkungan di sekitarmu.",
                showLogout = false,
                showList = true
            )

            R.id.itemProfile -> SimplePageFragment.newInstance(
                title = "Profil Pengguna",
                description = "Selamat datang di profil EcoPlant.",
                showLogout = true
            )

            else -> return
        }

        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.fragmentContainer, fragment)
            .commit()

        updateNavigation(itemId)
    }

    private fun updateNavigation(selectedId: Int) {

        updateItem(
            R.id.itemHome,
            binding.ivNavHome,
            binding.tvNavHome,
            selectedId
        )

        updateItem(
            R.id.itemPlants,
            binding.ivNavPlants,
            binding.tvNavPlants,
            selectedId
        )

        updateItem(
            R.id.itemEducation,
            binding.ivNavEducation,
            binding.tvNavEducation,
            selectedId
        )

        updateItem(
            R.id.itemProfile,
            binding.ivNavProfile,
            binding.tvNavProfile,
            selectedId
        )
    }

    private fun updateItem(
        itemId: Int,
        icon: ImageView,
        label: TextView,
        selectedId: Int
    ) {

        val isSelected = itemId == selectedId
        val color = if (isSelected) green else gray

        icon.setColorFilter(color)

        label.setTextColor(color)

        label.setTypeface(
            null,
            if (isSelected) {
                android.graphics.Typeface.BOLD
            } else {
                android.graphics.Typeface.NORMAL
            }
        )

    }

}
