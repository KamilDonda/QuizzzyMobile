package com.example.quiz.view_model.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz.R
import com.example.quiz.model.Category
import com.example.quiz.model.entities.Result
import java.text.SimpleDateFormat

class HistoryAdapter(var history: LiveData<List<Result>>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {

    inner class HistoryHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_row, parent, false)
        return HistoryHolder(view)
    }

    override fun getItemCount(): Int {
        return history.value?.size ?: 0
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        val date = holder.itemView.findViewById<TextView>(R.id.textView_date)
        val category = holder.itemView.findViewById<TextView>(R.id.textView_category)
        val difficulty = holder.itemView.findViewById<TextView>(R.id.textView_difficultylevel)
        val result = holder.itemView.findViewById<TextView>(R.id.textView_result)

        val dateFromBDatabase = history.value?.get(position)?.date
        val format =
            SimpleDateFormat("dd\t\u200B\t/\t\u200B\tMM\t\u200B\t/\t\u200B\tyyyy  HH﹕mm﹕ss")
        val newDate = format.format(dateFromBDatabase)

        var catId = history.value?.get(position)?.category
        catId = if (catId == 0) null else catId

        date.text = newDate
        category.text = Category.listOfCategories.find { c -> c.id == catId }?.name
        difficulty.text = history.value?.get(position)?.difficulty
        result.text = history.value?.get(position)?.result.toString() + "\t\u200B\t/\t\u200B\t10"
    }
}