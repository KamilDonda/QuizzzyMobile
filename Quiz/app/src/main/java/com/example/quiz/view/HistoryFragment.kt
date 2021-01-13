package com.example.quiz.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.view_model.adapters.HistoryAdapter
import com.example.quiz.view_model.vm.HistoryViewModel
import com.example.quiz.view_model.vm.ResultViewModel
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {

    private lateinit var viewModel: HistoryViewModel
    private lateinit var myadapter: HistoryAdapter
    private lateinit var mylayoutmanager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mylayoutmanager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(requireActivity()).get(HistoryViewModel::class.java)
        viewModel.history.observe(viewLifecycleOwner, Observer { myadapter.notifyDataSetChanged() })
        viewModel.history.value

        myadapter = HistoryAdapter(viewModel.history)
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = recyclerView_history.apply {
            layoutManager = mylayoutmanager
            adapter = myadapter
        }

    }
}