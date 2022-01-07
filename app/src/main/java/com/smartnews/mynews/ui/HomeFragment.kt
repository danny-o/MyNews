package com.smartnews.mynews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smartnews.mynews.R
import com.smartnews.mynews.databinding.FragmentHomeBinding
import com.smartnews.mynews.ui.adapters.ArticlesRecyclerAdapter


class HomeFragment:Fragment() {


    lateinit var binding: FragmentHomeBinding

    lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentHomeBinding.inflate(inflater,container,false)
        val view=binding.root


        mainActivityViewModel= ViewModelProvider(requireActivity())[MainActivityViewModel::class.java]

        mainActivityViewModel.getArticles().observe(viewLifecycleOwner,{newsArticles->

            if(newsArticles.isEmpty()){
                binding.tvNoArticles.isVisible=true

                binding.btnCreateNewsArticle.isVisible=true

                binding.btnCreateNewsArticle.setOnClickListener {

                    requireActivity()
                        .supportFragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.container,NewNewsArticleFragment())
                        .commit()
                }
            }

            val articlesRecyclerAdapter=ArticlesRecyclerAdapter(newsArticles)

            binding.rvNewsArticles.adapter=articlesRecyclerAdapter


        })

        binding.fabNewNewsArticle.setOnClickListener {

            requireActivity()
                .supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container,NewNewsArticleFragment())
                .commit()
        }
        return view
    }
}