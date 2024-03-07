package com.route.news.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.route.news.Constant
import com.route.news.R
import com.route.news.api.model.Article
import com.route.news.databinding.ActivityViewArticleBinding

class ViewArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewArticleBinding
    private var article: Article? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_article)
        getArticle()
        initAppBar()
    }

    private fun sendArticleToXML() {
        binding.article = article
        binding.viewFullArticleBtn.setOnClickListener {
            openArticle(article?.url)
        }
    }

    private fun openArticle(url: String?) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url!!))
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    @Suppress("DEPRECATION")
    private fun getArticle() {
        article = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(Constant.ARTICLE, Article::class.java)
        } else {
            intent.getSerializableExtra(Constant.ARTICLE) as Article
        }!!
        sendArticleToXML()
    }

    private fun initAppBar() {
        binding.appBar.apply {
            appTitle.setText(R.string.news_app)
            menuIcon.isVisible = false
            searchIcon.isVisible = false
        }
    }
}