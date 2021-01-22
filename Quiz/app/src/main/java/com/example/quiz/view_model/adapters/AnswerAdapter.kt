package com.example.quiz.view_model.adapters

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.media.MediaPlayer
import android.os.Build
import android.os.Handler
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.view_model.vm.QuestionViewModel
import com.example.quiz.view_model.vm.ResultViewModel
import com.google.android.material.button.MaterialButton
import java.util.*

// create list of all buttons in adapter/recyclerview
val Buttons = mutableListOf<MaterialButton>()

class AnswerAdapter(
    val answers: List<String>,
    val viewModel: QuestionViewModel,
    val viewModelResult: ResultViewModel,
    val correct: String,
    val context: Context,
    val viewLifecycleOwner: LifecycleOwner,
    val view: View,
    val animator: ValueAnimator,
    val category: Int,
    val difficulty: String,
    val timerSound: MediaPlayer)
    : RecyclerView.Adapter<AnswerAdapter.Holder>() {

    inner class Holder(view: View) : RecyclerView.ViewHolder(view)

    private val correctSound = MediaPlayer.create(context, R.raw.correct)
    private val incorrectSound = MediaPlayer.create(context, R.raw.incorrect)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.question_row, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = answers.size

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val button = holder.itemView.findViewById<MaterialButton>(R.id.button_in_row)

        // add button to list
        Buttons.add(button)

        button.text = Html.fromHtml(answers.get(position), Html.FROM_HTML_MODE_LEGACY)

        button.setOnClickListener {
            // if any button is clicked then all buttons become non-clickable
            for (b in Buttons) b.isClickable = false
            animator.cancel()

            // stop timer sound
            timerSound.release()

            // onClick sound effect
            if (button.text.toString() == correct)
                Handler().postDelayed({
                    correctSound.start()
                },500)
            else
                incorrectSound.start()

            Handler().postDelayed({
                if (button.text.toString() == correct) {
                    viewModelResult.IncrementResultNumber()
                    button.setTextColor(ContextCompat.getColor(context, R.color.correct_color))
                    button.strokeColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.correct_color))
                    button.setBackgroundColor(ContextCompat.getColor(context, R.color.correct_background_color))
                } else {
                    button.setTextColor(ContextCompat.getColor(context, R.color.incorrect_color))
                    button.strokeColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.incorrect_color))
                    button.setBackgroundColor(ContextCompat.getColor(context, R.color.incorrect_background_color))
                    Handler().postDelayed({
                        for (b in Buttons) {
                            if (b.text.toString() == correct) {
                                b.setTextColor(ContextCompat.getColor(context, R.color.correct_color))
                                b.strokeColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.correct_color))
                                b.setBackgroundColor(ContextCompat.getColor(context, R.color.correct_background_color))
                            }
                        }
                    }, 500)
                }
            }, 1000)

            val date = Date()

            Handler().postDelayed({
                if (viewModel.incrementQuizNumber())
                    it.findNavController().navigate(R.id.action_questionFragment_self)
                else {
                    viewModelResult.insert(date, category, difficulty, viewModelResult.result)
                    it.findNavController().navigate(R.id.action_questionFragment_to_resultFragment)
                }
            }, 2000)
        }

        viewModel.timeIsUp.observe(viewLifecycleOwner, Observer {
            if (viewModel.timeIsUp.value!!) {

                for (b in Buttons) {
                    b.isClickable = false
                    if (b.text.toString() == correct) {
                        b.setTextColor(ContextCompat.getColor(context, R.color.correct_color))
                        b.strokeColor = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.correct_color))
                        b.setBackgroundColor(ContextCompat.getColor(context, R.color.correct_background_color))
                    }
                }

                viewModel.setTimeIsUp(false)

                Handler().postDelayed({
                    if (viewModel.incrementQuizNumber())
                        view.findNavController().navigate(R.id.action_questionFragment_self)
                    else
                        view.findNavController().navigate(R.id.action_questionFragment_to_resultFragment)
                }, 2000)
            }
        })
    }
}