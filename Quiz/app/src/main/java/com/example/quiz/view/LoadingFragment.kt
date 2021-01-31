package com.example.quiz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quiz.R
import com.example.quiz.model.Category
import com.example.quiz.view_model.vm.CategoryViewModel
import com.example.quiz.view_model.vm.DifficultyLevelViewModel
import com.example.quiz.view_model.vm.QuestionViewModel
import com.example.quiz.view_model.vm.ResultViewModel
import kotlinx.android.synthetic.main.fragment_difficulty_level.textViewSelectedCategory
import kotlinx.android.synthetic.main.fragment_loading.*
import java.util.*

class LoadingFragment : Fragment() {

    private lateinit var viewModelDifficulty: DifficultyLevelViewModel
    private lateinit var viewModelCategory: CategoryViewModel
    private lateinit var viewModelQuestion: QuestionViewModel
    private lateinit var viewModelResult: ResultViewModel
    private lateinit var category: Category
    private var difficulty: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {}
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        viewModelDifficulty =
            ViewModelProvider(requireActivity()).get(DifficultyLevelViewModel::class.java)
        viewModelCategory = ViewModelProvider(requireActivity()).get(CategoryViewModel::class.java)
        viewModelQuestion = ViewModelProvider(requireActivity()).get(QuestionViewModel::class.java)
        viewModelResult = ViewModelProvider(requireActivity()).get(ResultViewModel::class.java)

        category = viewModelCategory.currentCategory.value!!
        difficulty = viewModelDifficulty.currentDifficultyLevel.value!!

        viewModelQuestion.setCategoryAndDifficulty(category.id, difficulty.toLowerCase(Locale.ROOT))

        viewModelQuestion.quizList.observe(viewLifecycleOwner, Observer {
            if (viewModelQuestion.quizList.value != null)
                if (viewModelQuestion.quizList.value?.isNotEmpty()!!)
                    findNavController().navigate(R.id.action_loadingFragment_to_questionFragment)
                else {
                    viewModelQuestion.setCategoryAndDifficulty(category.id)
                    viewModelDifficulty.setCurrentDifficultyLevel("All")
                    Toast.makeText(context, getString(R.string.no_diff), Toast.LENGTH_LONG).show()
                }
        })

        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewSelectedLevel.text = difficulty
        textViewSelectedCategory.text = category.name

        viewModelQuestion.resetQuizNumber()
        viewModelResult.resetResultNumber()
    }
}