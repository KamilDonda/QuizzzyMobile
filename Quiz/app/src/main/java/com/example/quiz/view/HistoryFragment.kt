package com.example.quiz.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quiz.R
import com.example.quiz.view_model.vm.ResultViewModel

class HistoryFragment : Fragment() {

    private lateinit var viewModel: ResultViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(requireActivity()).get(ResultViewModel::class.java)

        viewModel.results.observe(viewLifecycleOwner, Observer {
            Log.v("TAGGG", it.toString())
        })

        return inflater.inflate(R.layout.fragment_history, container, false)
    }
}