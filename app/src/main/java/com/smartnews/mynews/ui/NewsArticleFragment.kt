package com.smartnews.mynews.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smartnews.mynews.R
import com.smartnews.mynews.data.NewsArticle
import com.smartnews.mynews.databinding.FragmentNewsArticleBinding



class NewsArticleFragment :Fragment() {

    lateinit var binding:FragmentNewsArticleBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentNewsArticleBinding.inflate(inflater,container,false)

        val view=binding.root


        val newsArticle:NewsArticle?=arguments?.getParcelable(MainActivity.NEWS_ARTICLE)


        newsArticle?.let {
            binding.tvArticleTitle.text=it.title

            binding.tvDate.text=it.date

            binding.tvContent.text=it.content
        }

        binding.tvContent.movementMethod=ScrollingMovementMethod()
        val onclickListener= View.OnClickListener {
            requireActivity()
                .supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container,NewNewsArticleFragment::class.java,arguments)
                .commit()
        }

        onclickListener.apply {
            binding.tvEdit.setOnClickListener(this)
            binding.ivEdit.setOnClickListener(this)
        }

        return view
    }
}