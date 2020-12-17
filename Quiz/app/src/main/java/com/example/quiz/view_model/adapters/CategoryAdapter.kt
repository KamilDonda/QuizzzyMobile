package com.example.quiz.view_model.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.model.Category
import com.example.quiz.view_model.vm.CategoryViewModel

class CategoryAdapter(val categories: List<Category>, val viewModel: CategoryViewModel)
    : RecyclerView.Adapter<CategoryAdapter.Holder>() {

    inner class Holder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_row, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val button = holder.itemView.findViewById<Button>(R.id.button_category)

        button.text = categories.get(position).name

        button.setOnClickListener { viewModel.setCurrentCategory(categories.get(position)) }
    }

}