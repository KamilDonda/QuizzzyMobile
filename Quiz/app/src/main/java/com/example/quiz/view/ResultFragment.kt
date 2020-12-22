package com.example.quiz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(com.example.quiz.R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = 8;

        if(result < 4)
            textViewResultInfo.text = "Maybe next time.."
        if(result >= 4 && result < 7)
            textViewResultInfo.text = "Nice or bad result?"
        if(result >= 7)
            textViewResultInfo.text = "Confratulations!"

        textViewResult.text = result.toString() + "/10"

        imageViewArrow.setOnClickListener {
            imageViewArrow.animate().rotation(imageViewArrow.rotation + 360).start()
        }
    }

}