package com.example.quiz.view

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quiz.R
import com.example.quiz.view_model.vm.QuestionViewModel
import kotlinx.android.synthetic.main.fragment_question.*

class QuestionFragment : Fragment() {

    private lateinit var viewModel: QuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity()).get(QuestionViewModel::class.java)

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

        Log.v("TAGGG", "${viewModel.questionList.value}")
    }
}