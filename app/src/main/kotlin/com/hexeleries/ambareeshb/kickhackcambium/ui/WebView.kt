package com.hexeleries.ambareeshb.kickhackcambium.ui

import android.content.Context
import android.util.AttributeSet
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * Created by ambareeshb on 28/10/17.
 */

class WebView : android.webkit.WebView {
    private lateinit var listener: Listener

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }


    fun init() {
        setWebViewClient(client)
    }

    /**
     * Setting on finished listner object[Listener]
     */
    fun setListener(listener:Listener){
        this.listener = listener
        webChromeClient = object : WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if(newProgress > 60){// Loading finished more than half
                    listener.urlLoadFinished()
                    webChromeClient = null
                }
            }
        }

    }


    /**
     * New web view client
     */
    object client : WebViewClient()


    interface Listener{
        fun urlLoadFinished()
    }
}
