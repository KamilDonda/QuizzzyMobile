package com.example.quiz.view_model.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.view_model.vm.DifficultyLevelViewModel

class DifficultyLevelAdapter(val difficultyLevels: List<String>, val viewModel: DifficultyLevelViewModel)
    : RecyclerView.Adapter<DifficultyLevelAdapter.Holder>() {

    inner class Holder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.question_row, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = difficultyLevels.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val button = holder.itemView.findViewById<Button>(R.id.button_category)

        button.text = difficultyLevels.get(position)

        button.setOnClickListener {
            viewModel.setCurrentDifficultyLevel(difficultyLevels.get(position))
            it.findNavController().navigate(R.id.action_difficultyLevelFragment_to_questionFragment)
        }
    }

}