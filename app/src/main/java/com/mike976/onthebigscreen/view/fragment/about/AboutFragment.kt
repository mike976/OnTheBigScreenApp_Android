package com.mike976.onthebigscreen.view.fragment.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.onthebigscreen.BuildConfig
import com.example.onthebigscreen.R
import com.mike976.onthebigscreen.util.getProgressDrawable
import kotlinx.android.synthetic.main.fragment_about.*

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        hideActionBar()
        loadImages()

        val versionCode = BuildConfig.VERSION_CODE
        val versionName = BuildConfig.VERSION_NAME

        buildVersion.text = "On the Big Screen v$versionName build $versionCode"

    }

    private fun loadImages() {

        activity?.let {
            appIcon.setImageResource(R.drawable.ic_launcher_round)

            tmDbIcon.setImageResource(R.drawable.tmdb_logo)
            gitHubImage.setImageResource(R.drawable.github)
            gitHubImage.setOnClickListener {
                it.context.browse("https://github.com/mike976")
            }

            emailImage.setImageResource(R.drawable.email)
            emailImage.setOnClickListener {
                it.context.email("mikebullock976@gmail.com")
            }

            linkedInImage.setImageResource(R.drawable.linked_in)
            linkedInImage.setOnClickListener {
                it.context.browse("https://uk.linkedin.com/in/michaelbullock976")
            }

            shareImage.setImageResource(R.drawable.share)
            shareImage.setOnClickListener {
                it.context.share("Hey there, Check out the 'On the Big Screen app! (Android)'")
            }
        }

    }

    fun Context.share(text: String) =
        this.startActivity(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        })

    fun Context.browse(text: String) =
        this.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(text)))

    fun Context.email(emailAddr: String) =
        this.startActivity(Intent().apply {
            action = Intent.ACTION_SENDTO
            data = Uri.parse("mailto:$emailAddr")
        })

    fun hideActionBar() {
        try {
            val activity = view?.context as AppCompatActivity
            activity.supportActionBar!!.hide()
        } catch (e: Exception) {
        }
    }

    override fun onDestroyView() {
        this.hideActionBar()
        super.onDestroyView()
    }
}