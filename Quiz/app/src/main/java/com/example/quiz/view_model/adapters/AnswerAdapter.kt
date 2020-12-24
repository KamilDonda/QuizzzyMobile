package com.example.quiz.view_model.adapters

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.view_model.vm.QuestionViewModel

class AnswerAdapter(val answers: List<String>, val viewModel: QuestionViewModel, val correct: String)
    : RecyclerView.Adapter<AnswerAdapter.Holder>() {

    inner class Holder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.question_row, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = answers.size

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val button = holder.itemView.findViewById<Button>(R.id.button_in_row)

        button.text = Html.fromHtml(answers.get(position), Html.FROM_HTML_MODE_LEGACY)

        button.setOnClickListener {}
    }
}