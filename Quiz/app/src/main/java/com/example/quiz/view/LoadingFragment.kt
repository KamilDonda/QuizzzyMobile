package com.example.quiz.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quiz.R
import com.example.quiz.model.Category
import com.example.quiz.view_model.vm.CategoryViewModel
import com.example.quiz.view_model.vm.DifficultyLevelViewModel
import com.example.quiz.view_model.vm.QuestionViewModel
import kotlinx.android.synthetic.main.fragment_difficulty_level.textViewSelectedCategory
import kotlinx.android.synthetic.main.fragment_loading.*

class LoadingFragment : Fragment() {

    private lateinit var viewModelDifficulty : DifficultyLevelViewModel
    private lateinit var viewModelCategory: CategoryViewModel
    private lateinit var viewModelQuestion: QuestionViewModel
    private lateinit var category: Category
    private var difficulty: String = ""

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        viewModelDifficulty = ViewModelProvider(requireActivity()).get(DifficultyLevelViewModel::class.java)
        viewModelCategory = ViewModelProvider(requireActivity()).get(CategoryViewModel::class.java)
        viewModelQuestion = ViewModelProvider(requireActivity()).get(QuestionViewModel::class.java)

        category = viewModelCategory.currentCategory.value!!
        difficulty = viewModelDifficulty.currentDifficultyLevel.value!!

        viewModelQuestion.setCategoryAndDifficulty(category.id, difficulty.toLowerCase())

        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewSelectedLevel.text = difficulty
        textViewSelectedCategory.text = category.name

        // navigate to another fragment after X seconds
        Handler().postDelayed({
            findNavController().navigate(R.id.action_loadingFragment_to_questionFragment)
        }, 500)
    }
}