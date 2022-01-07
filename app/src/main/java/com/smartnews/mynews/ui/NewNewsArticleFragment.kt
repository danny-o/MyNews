package com.smartnews.mynews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.smartnews.mynews.R
import com.smartnews.mynews.data.NewsArticle
import com.smartnews.mynews.databinding.FragmentNewNewsArticleBinding
import kotlinx.coroutines.launch


class NewNewsArticleFragment :Fragment() {


    lateinit var binding: FragmentNewNewsArticleBinding


    lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentNewNewsArticleBinding.inflate(inflater,container,false)

        val view=binding.root

        mainActivityViewModel= ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)

        binding.btnSave.setOnClickListener {

            lifecycleScope.launch {

                    if(binding.etArticleTitle.text.isEmpty()){

                        binding.etArticleTitle.error=getString(R.string.article_title_cannot_be_empty)


                        return@launch
                    }

                    if(binding.etArticleDate.text.isEmpty()){

                        binding.etArticleDate.error=getString(R.string.article_date_cannot_be_empty)

                        return@launch
                    }



                    val articleTitle=binding.etArticleTitle.text.toString()

                    val articleDate=binding.etArticleDate.text.toString()

                    val articleContent=binding.etArticleContent.text.toString()






                    if(arguments==null){

                        val newsArticle=NewsArticle(title = articleTitle,date = articleDate,content = articleContent)

                        mainActivityViewModel.insertNewsArticle(newsArticle)
                    }
                    else{

                        val newsArticle:NewsArticle=arguments?.getParcelable<NewsArticle>(MainActivity.NEWS_ARTICLE)
                            ?.copy(title = articleTitle,date = articleDate,content = articleContent)!!


                        arguments?.putParcelable(MainActivity.NEWS_ARTICLE,newsArticle)

                        mainActivityViewModel.updateNewsArticle(newsArticle)
                    }

                    requireActivity().supportFragmentManager.popBackStack()
            }


        }
        arguments?.getParcelable<NewsArticle>(MainActivity.NEWS_ARTICLE)?.let {newsArticle->


            binding.tvCreateNewsArticle.text=getString(R.string.edit_news_article)

            binding.ivDelete.visibility=View.VISIBLE

            binding.etArticleContent.setText(newsArticle.content)

            binding.etArticleTitle.setText(newsArticle.title)

            binding.etArticleDate.setText(newsArticle.date)

            binding.ivDelete.setOnClickListener {

                lifecycleScope.launch {


                    mainActivityViewModel.deleteNewsArticle(newsArticle)
                    requireActivity()
                        .supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container,HomeFragment())
                        .commit()
                }
            }
        }

        return view
    }
}