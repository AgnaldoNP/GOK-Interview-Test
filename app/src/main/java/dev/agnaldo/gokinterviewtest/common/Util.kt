package dev.agnaldo.gokinterviewtest.common

import android.content.Context

fun Context.screenWidth() =
    this.resources.displayMetrics.widthPixels
