package com.example.kotlinpractice.activity

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinpractice.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {
    /**
     * private variable
     */
    private val TAG = "WebViewActivity"
    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebviewBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        val webView: WebView = binding.webview
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true

        webView.loadUrl("https://www.yahoo.co.jp")
    }
}