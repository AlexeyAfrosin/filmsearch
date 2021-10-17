package com.afrosin.filmsearch.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(
    text: String,
    actionText: String?,
    action: ((View) -> Unit)?,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    Snackbar.make(this, text, length).apply {
        if (actionText != null && action != null) {
            setAction(actionText, action)
        }
    }.show()
}

fun View.showSnackBar(
    textId: Int,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    this.showSnackBar(
        context.resources.getString(textId),
        null,
        null,
        length
    )
}

fun View.showSnackBar(
    textId: Int,
    actionTextId: Int,
    action: (View) -> Unit,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    this.showSnackBar(
        context.resources.getString(textId),
        context.resources.getString(actionTextId),
        action,
        length
    )
}

fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

fun View.hide(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

fun View.click(click: () -> Unit) = setOnClickListener { click() }

@Suppress("IMPLICIT_CAST_TO_ANY")
fun ImageView.setImageFromUrl(url: String?, placeholder: Int = 0) {

    if (url != "") {
        val glideUrl = if (url!!.isEmpty()) placeholder else GlideUrl(url)
        Glide.with((context))
            .load(glideUrl)
            .placeholder(placeholder)
            .into(this)
    }
}