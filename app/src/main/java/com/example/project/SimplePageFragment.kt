
package com.example.project

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment

class SimplePageFragment : Fragment() {

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_DESCRIPTION = "description"
        private const val ARG_SHOW_LOGOUT = "show_logout"
        private const val ARG_SHOW_LIST = "show_list"

        fun newInstance(
            title: String,
            description: String,
            showLogout: Boolean = false,
            showList: Boolean = false
        ): SimplePageFragment {
            return SimplePageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_DESCRIPTION, description)
                    putBoolean(ARG_SHOW_LOGOUT, showLogout)
                    putBoolean(ARG_SHOW_LIST, showList)
                }
            }
        }
    }

    private val educationItems = listOf(
        "Mengenal Jenis Tanaman",
        "Manfaat Tanaman bagi Lingkungan",
        "Cara Merawat Tanaman",
        "Tanaman Hias",
        "Tanaman Obat",
        "Tanaman Pangan",
        "Pelestarian Lingkungan",
        "Manfaat Pohon bagi Kehidupan"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_simple_page,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val tvTitle = view.findViewById<TextView>(
            R.id.tvTitle
        )

        val tvDescription = view.findViewById<TextView>(
            R.id.tvDescription
        )

        val listView = view.findViewById<ListView>(
            R.id.listSimpleItems
        )

        val btnLogout = view.findViewById<Button>(
            R.id.btnLogout
        )

        val args = requireArguments()

        tvTitle.text = args.getString(
            ARG_TITLE,
            "EcoPlant"
        )

        tvDescription.text = args.getString(
            ARG_DESCRIPTION,
            "Mengenal tanaman dan manfaatnya."
        )

        val showList = args.getBoolean(
            ARG_SHOW_LIST,
            false
        )

        val showLogout = args.getBoolean(
            ARG_SHOW_LOGOUT,
            false
        )

        listView.visibility =
            if (showList) View.VISIBLE else View.GONE

        btnLogout.visibility =
            if (showLogout) View.VISIBLE else View.GONE

        if (showList) {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                educationItems
            )

            listView.adapter = adapter
        }

        btnLogout.setOnClickListener {
            val sharedPref = requireActivity()
                .getSharedPreferences(
                    "user_pref",
                    android.content.Context.MODE_PRIVATE
                )

            sharedPref.edit().clear().apply()

            val intent = Intent(
                requireContext(),
                LoginActivity::class.java
            ).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            startActivity(intent)
        }
    }
}
