package com.smartnews.mynews.ui.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smartnews.mynews.R
import com.smartnews.mynews.data.NewsArticle
import com.smartnews.mynews.databinding.ItemArticleBinding
import com.smartnews.mynews.ui.MainActivity
import com.smartnews.mynews.ui.NewNewsArticleFragment
import com.smartnews.mynews.ui.NewsArticleFragment

class ArticlesRecyclerAdapter(val articles:List<NewsArticle>):RecyclerView.Adapter<ArticlesRecyclerAdapter.NewsArticleViewHolder>() {

    private lateinit var binding: ItemArticleBinding




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemArticleBinding.inflate(inflater, parent, false)
        return NewsArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
       holder.setData(articles[position])

        holder.itemView.setOnClickListener {

            val bundle= Bundle()

            bundle.putParcelable(MainActivity.NEWS_ARTICLE,articles[position])
            (holder.itemView.context as MainActivity)
                .supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container,NewsArticleFragment::class.java,bundle)
                .commit()
        }

    }

    override fun getItemCount(): Int = articles.size


    class NewsArticleViewHolder(private val binding: ItemArticleBinding):RecyclerView.ViewHolder(binding.root){

        fun setData(newsArticle: NewsArticle){

            newsArticle.run {

                binding.tvArticleTitle.text=title
                binding.tvDescription.text=content

                binding.tvArticleDate.text=date



            }


        }

    }
}