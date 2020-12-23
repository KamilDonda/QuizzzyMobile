package com.example.quiz.view

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.quiz.R
import kotlinx.android.synthetic.main.fragment_question.*

class QuestionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val TIME = 15
        progressBar_timer.max = TIME * 100
        progressBar_timer.rotation = 180f
        ObjectAnimator.ofInt(progressBar_timer, "progress", TIME * 100)
                .setDuration(TIME * 1000L)
                .start()

        textView_Question.text = "Which is the chemical name of H20?"
    }
}