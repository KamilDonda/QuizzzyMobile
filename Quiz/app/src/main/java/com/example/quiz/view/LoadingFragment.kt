package com.example.quiz.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.quiz.R
import com.example.quiz.view_model.vm.CategoryViewModel
import com.example.quiz.view_model.vm.DifficultyLevelViewModel
import com.example.quiz.view_model.vm.QuestionViewModel
import kotlinx.android.synthetic.main.fragment_difficulty_level.textViewSelectedCategory
import kotlinx.android.synthetic.main.fragment_loading.*

class LoadingFragment : Fragment() {

    private lateinit var viewModelLevel : DifficultyLevelViewModel
    private lateinit var viewModelCategory: CategoryViewModel
    private lateinit var viewModelQuestion: QuestionViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        viewModelLevel = ViewModelProvider(requireActivity()).get(DifficultyLevelViewModel::class.java)
        viewModelCategory = ViewModelProvider(requireActivity()).get(CategoryViewModel::class.java)
        viewModelQuestion = ViewModelProvider(requireActivity()).get(QuestionViewModel::class.java)

        viewModelQuestion.questionList.observe(viewLifecycleOwner, Observer {})

        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewSelectedLevel.text = viewModelLevel.currentDifficultyLevel.value!!
        textViewSelectedCategory.text = viewModelCategory.currentCategory.value!!.name

        // navigate to another fragment after X seconds
        Handler().postDelayed({
            findNavController().navigate(R.id.action_loadingFragment_to_questionFragment)
        }, 500)
    }
}