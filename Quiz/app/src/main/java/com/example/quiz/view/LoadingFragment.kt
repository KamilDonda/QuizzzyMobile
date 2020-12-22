package com.example.quiz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quiz.R
import com.example.quiz.view_model.vm.CategoryViewModel
import com.example.quiz.view_model.vm.DifficultyLevelViewModel
import kotlinx.android.synthetic.main.fragment_difficulty_level.textViewSelectedCategory
import kotlinx.android.synthetic.main.fragment_loading.*

class LoadingFragment : Fragment() {

    private lateinit var viewModelLevel : DifficultyLevelViewModel
    private lateinit var viewModelCategory: CategoryViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        viewModelLevel = ViewModelProvider(requireActivity()).get(DifficultyLevelViewModel::class.java)
        viewModelCategory = ViewModelProvider(requireActivity()).get(CategoryViewModel::class.java)

        return inflater.inflate(R.layout.fragment_loading, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewSelectedLevel.text = viewModelLevel.currentDifficultyLevel.value!!
        textViewSelectedCategory.text = viewModelCategory.currentCategory.value!!.name
    }
}