package com.example.quiz.view

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // seconds
        val TIME = 20

        // progressBar as timer
        progressBar_timer.max = TIME * 100
        progressBar_timer.rotation = 180f
        ObjectAnimator.ofInt(progressBar_timer, "progress", TIME * 100)
                .setDuration(TIME * 1000L)
                .start()

        viewModel.quizList.observe(viewLifecycleOwner, Observer {
            textView_Question.text = Html.fromHtml(viewModel.getCurrentQuestion(), Html.FROM_HTML_MODE_LEGACY)
            Log.v("TAGGG", "${viewModel.getCurrentAnswers()}\n${viewModel.getCurrentCorrectAnswer()}\n${viewModel.currentQuizNumber}")

            // tymczasowo tutaj jest ta zmienna, trzeba będzie ją przenieść do onClicka w buttonie z odpowiedzią
            viewModel.currentQuizNumber++
        })
    }
}