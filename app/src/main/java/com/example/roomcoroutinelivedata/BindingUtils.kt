package com.example.roomcoroutinelivedata

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.roomcoroutinelivedata.database.User


@BindingAdapter("nameFormatted")
    fun TextView.setNameFormatted(item: User?) {
    item?.let {
        text = item.firstName + " " + item.lastName
    }
    }
@BindingAdapter("idDisplay")
fun TextView.setIdDisplay(item: User?) {
    item?.let {
        text = "ID:${item.id}"
    }
}