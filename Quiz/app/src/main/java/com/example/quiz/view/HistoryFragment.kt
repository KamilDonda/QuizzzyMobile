package com.example.quiz.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.model.Category
import com.example.quiz.view_model.adapters.HistoryAdapter
import com.example.quiz.view_model.vm.HistoryViewModel
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {

    private lateinit var viewModel: HistoryViewModel
    private lateinit var myadapter: HistoryAdapter
    private lateinit var mylayoutmanager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mylayoutmanager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(requireActivity()).get(HistoryViewModel::class.java)
        viewModel.allResults.observe(viewLifecycleOwner, Observer {
            viewModel.query.value
            myadapter.notifyDataSetChanged()
        })

        myadapter = HistoryAdapter(viewModel.allResults)
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView.setQuery(viewModel.query.value, false)
        searchView.isIconified = false
        searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    viewModel.setQuery(query)
                    val listOfIds = mutableListOf<Int>()
                    Category.listOfCategories.forEach {
                        if (it.name.contains(viewModel.query.value!!))
                            listOfIds.add(it.id ?: 0)
                    }
                    viewModel.setResultsWithFilters(listOfIds)
                    Log.v("TAGGG", listOfIds.toString())
                } else {
                    viewModel.resetResults()
                    viewModel.setQuery("")
                }
                return false
            }
        })

        recyclerView = recyclerView_history.apply {
            layoutManager = mylayoutmanager
            adapter = myadapter
        }
    }
}