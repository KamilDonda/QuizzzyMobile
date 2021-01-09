package com.example.quiz.view

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.view_model.adapters.AnswerAdapter
import com.example.quiz.view_model.vm.QuestionViewModel
import com.example.quiz.view_model.vm.ResultViewModel
import kotlinx.android.synthetic.main.fragment_question.*

class QuestionFragment : Fragment() {

    private lateinit var viewModel: QuestionViewModel
    private lateinit var viewModelResult: ResultViewModel
    private lateinit var myAdapter: AnswerAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // in here you can do logic when backPress is clicked
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity()).get(QuestionViewModel::class.java)
        viewModelResult = ViewModelProvider(requireActivity()).get(ResultViewModel::class.java)

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
            myAdapter = AnswerAdapter(
                    viewModel.getCurrentAnswers(),
                    viewModel,
                    viewModelResult,
                    Html.fromHtml(viewModel.getCurrentCorrectAnswer(), Html.FROM_HTML_MODE_LEGACY).toString(),
                    requireContext())
            myAdapter.notifyDataSetChanged()

            textView_Question.text = Html.fromHtml(viewModel.getCurrentQuestion(), Html.FROM_HTML_MODE_LEGACY)
//            Log.v("TAGGG", "${viewModel.getCurrentAnswers()}\n${viewModel.getCurrentCorrectAnswer()}\n${viewModel.currentQuizNumber}")
            textView_currentQuestion.text = "${getString(R.string.question)} ${viewModel.currentQuizNumber + 1}"
            textViewCategory.text = Html.fromHtml(viewModel.getCurrentCategory(), Html.FROM_HTML_MODE_LEGACY)

            recyclerView = recyclerView_answer.apply {
                adapter = myAdapter
            }
        })
    }
}