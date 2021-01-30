package com.example.quiz.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quiz.R
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/KamilDonda/Quiz"))
            startActivity(intent)
        }

        linearPolsl.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.polsl.pl/Strony/Witamy.aspx"))
            startActivity(intent)
        }

        linearFoAM.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ms.polsl.pl"))
            startActivity(intent)
        }

        textViewApi.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://opentdb.com"))
            startActivity(intent)
        }

        textViewKD.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/KamilDonda"))
            startActivity(intent)
        }

        textViewRK.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Kwolik"))
            startActivity(intent)
        }

        textViewRD.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/curiosis"))
            startActivity(intent)
        }
    }
}