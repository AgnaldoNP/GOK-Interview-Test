package dev.agnaldo.gokinterviewtest.common

import android.text.Spannable
import android.text.style.ForegroundColorSpan

class SpannableUtils {
    fun colorizeSecondWordAndSoOn(charSequence: CharSequence, color: Int): CharSequence =
        android.text.SpannableString(charSequence).apply {
            val split = charSequence.trim().split(" ")
            if (split.count() > 1) {
                setSpan(
                    ForegroundColorSpan(color),
                    split[0].length,
                    this.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
}
