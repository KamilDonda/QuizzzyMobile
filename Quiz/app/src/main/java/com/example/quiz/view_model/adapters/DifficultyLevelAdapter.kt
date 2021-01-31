package com.example.quiz.view_model.adapters

import android.content.Context
import android.net.ConnectivityManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.view_model.vm.DifficultyLevelViewModel


@Suppress("DEPRECATION")
class DifficultyLevelAdapter(
    private val difficultyLevels: List<String>,
    private val viewModel: DifficultyLevelViewModel,
    private val activity: FragmentActivity,
    val context: Context
) : RecyclerView.Adapter<DifficultyLevelAdapter.Holder>() {

    inner class Holder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.button_row, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = difficultyLevels.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val button = holder.itemView.findViewById<Button>(R.id.button_in_row)

        button.text = difficultyLevels[position]

        button.setOnClickListener {

            viewModel.setCurrentDifficultyLevel(difficultyLevels[position])

            if (verifyAvailableNetwork(activity))
                it.findNavController()
                    .navigate(R.id.action_difficultyLevelFragment_to_loadingFragment)
            else
                Toast.makeText(context, context.getString(R.string.no_internet), Toast.LENGTH_SHORT)
                    .show()
        }
    }

    private fun verifyAvailableNetwork(activity: FragmentActivity): Boolean {
        val connectivityManager =
            activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}