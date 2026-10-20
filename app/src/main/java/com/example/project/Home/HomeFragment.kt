
package com.example.project.Home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project.MainActivity
import com.example.project.R
import com.example.project.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext()
            .getSharedPreferences(
                "user_pref",
                Context.MODE_PRIVATE
            )

        val username = sharedPref.getString("username", null)

        binding.tvGreeting.text = if (username.isNullOrBlank()) {
            "Halo, Sobat EcoPlant!"
        } else {
            "Halo, $username!"
        }

        binding.btnExplore.setOnClickListener {
            openWebsite()
        }

        binding.cardCategoryHias.setOnClickListener {
            openPlants()
        }

        binding.cardCategoryPangan.setOnClickListener {
            openPlants()
        }

        binding.cardCategoryObat.setOnClickListener {
            openPlants()
        }

        binding.cardCategoryPohon.setOnClickListener {
            openPlants()
        }

        // Kartu tanaman populer menuju halaman Tanaman
        binding.cardPopularPlant.setOnClickListener {
            openPlants()
        }
    }

    private fun openWebsite() {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://id.wikipedia.org/wiki/Tumbuhan")
        )

        startActivity(intent)
    }

    private fun openPlants() {
        (activity as? MainActivity)
            ?.navigateToTab(R.id.itemPlants)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
