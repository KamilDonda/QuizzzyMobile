package com.example.quiz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.quiz.R
import com.example.quiz.view_model.vm.ResultViewModel
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment : Fragment() {

    private lateinit var viewModelResult: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {}
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        viewModelResult = ViewModelProvider(requireActivity()).get(ResultViewModel::class.java)

        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = viewModelResult.result;

        if (result < 4)
            textViewResultInfo.text = "Maybe next time.."
        if (result in 4..6)
            textViewResultInfo.text = "Not bad but could be better"
        if (result >= 7)
            textViewResultInfo.text = "Congratulations!"

        textViewResult.text = result.toString() + "/10"

        imageViewArrow.setOnClickListener {
            imageViewArrow.animate().rotationBy(imageViewArrow.rotation + 370).withEndAction {
                it.findNavController().navigate(R.id.action_resultFragment_to_categoryFragment)
            }.start()
        }
    }
}