package com.example.quiz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.model.Category
import com.example.quiz.view_model.CategoryAdapter
import com.example.quiz.view_model.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment : Fragment() {

    private lateinit var viewModel: CategoryViewModel
    private lateinit var myAdapter: CategoryAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val categories = listOfCategories

        viewModel = ViewModelProvider(requireActivity()).get(CategoryViewModel::class.java)
        myAdapter = CategoryAdapter(categories, viewModel)

        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = recyclerView_category.apply {
            adapter = myAdapter
        }
    }

    val listOfCategories = listOf(
            Category(null,"All"),
            Category(9,"General Knowledge"),
            Category(10,"Books"),
            Category(11,"Film"),
            Category(12,"Music"),
            Category(13,"Musicals & Theatres"),
            Category(14,"Television"),
            Category(15,"Video Games"),
            Category(16,"Board Games"),
            Category(17,"Science & Nature"),
            Category(18,"Computers"),
            Category(19,"Mathematics"),
            Category(20,"Mythology"),
            Category(21,"Sports"),
            Category(22,"Geography"),
            Category(23,"History"),
            Category(24,"Politics"),
            Category(25,"Art"),
            Category(26,"Celebrities"),
            Category(27,"Animals"),
            Category(28,"Vehicles"),
            Category(29,"Comics"),
            Category(30,"Gadgets"),
            Category(31,"Anime & Manga"),
            Category(32,"Cartoon & Animations")
    )
}