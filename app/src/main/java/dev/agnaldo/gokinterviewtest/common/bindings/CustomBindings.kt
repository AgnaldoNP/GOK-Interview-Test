package dev.agnaldo.gokinterviewtest.common.bindings

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.agnaldo.gokinterviewtest.R

@BindingAdapter("app:src")
fun setImageView(imageView: ImageView, imageUrl: String) {
    if (imageUrl.isNotEmpty()) {
        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_baseline_error_outline_24)
            .into(imageView)
    } else {
        imageView.setImageResource(R.drawable.ic_baseline_error_outline_24)
    }
}

@BindingAdapter(
    value = [
        "app:textArg1",
        "app:textArg2",
        "app:textArg3"
    ],
    requireAll = false
)
fun setText(textView: TextView, arg1: String, arg2: String?, arg3: String?) {
    if (arg3 != null && arg2 != null) {
        textView.text = textView.text.toString().format(arg1, arg2, arg3)
    } else if (arg3 == null && arg2 != null) {
        textView.text = textView.text.toString().format(arg1, arg2)
    } else {
        textView.text = textView.text.toString().format(arg1)
    }
}

@BindingAdapter("app:adapter")
fun setRecyclerViewAdapter(
    recyclerView: RecyclerView,
    adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>
) {
    recyclerView.adapter = adapter
}
