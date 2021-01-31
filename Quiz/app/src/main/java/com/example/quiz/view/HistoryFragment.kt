package com.example.quiz.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.children
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.model.Category
import com.example.quiz.view_model.adapters.HistoryAdapter
import com.example.quiz.view_model.vm.HistoryViewModel
import com.google.android.material.chip.Chip
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
                    viewModel.setResultsWithCategories(listOfIds)
                } else {
                    viewModel.resetResults()
                    viewModel.setQuery("")
                }
                return false
            }
        })

        chipGroup.children.forEach {
            (it as Chip).setOnCheckedChangeListener { _, isChecked ->
                if (isChecked)
                    viewModel.diffs.add(it.text.toString())
                else
                    viewModel.diffs.remove(it.text.toString())
                viewModel.setResultsWithLevels(viewModel.diffs.toList())
            }
        }

        recyclerView = recyclerView_history.apply {
            layoutManager = mylayoutmanager
            adapter = myadapter
        }
    }
}