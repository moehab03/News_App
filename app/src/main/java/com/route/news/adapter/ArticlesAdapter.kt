package com.route.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.news.api.model.Article
import com.route.news.databinding.ArticleLayoutBinding
import com.squareup.picasso.Picasso

class ArticlesAdapter(
    private var articles: List<Article?>,
    private val onArticleClickListener: (article: Article?) -> Unit
) :
    Adapter<ArticlesAdapter.ArticleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =
            ArticleLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.apply {
            setDataInFields(article)
            itemView.setOnClickListener {
                onArticleClickListener(article)
            }
        }
    }

    fun updateArticles(articles: List<Article?>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    class ArticleViewHolder(private val binding: ArticleLayoutBinding) : ViewHolder(binding.root) {
        fun setDataInFields(article: Article?) {
            binding.article = article
        }
    }

}