package com.example.ntmchau.loanapp.binding

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

object BindingAdapters {

    @BindingAdapter("entries", "layout")
    @JvmStatic
    fun <T> setEntries(appCompatSpinner: AppCompatSpinner, entries: MutableList<T>?, layoutId: Int) {
        entries?.let {

            val adapter = ArrayAdapter<T>(
                appCompatSpinner.context,
                layoutId,
                it
            )

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            appCompatSpinner.adapter = adapter
        }
    }

    @BindingAdapter(value = ["selectedValue", "selectedValueAttrChanged"], requireAll = false)
    @JvmStatic
    fun bindSpinnerData(
        appCompatSpinner: AppCompatSpinner,
        newSelectedValue: String?,
        newTextAttrChanged: InverseBindingListener
    ) {
        appCompatSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                newTextAttrChanged.onChange()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        if (newSelectedValue != null) {
            val pos = (appCompatSpinner.adapter as ArrayAdapter<String>).getPosition(newSelectedValue)
            appCompatSpinner.setSelection(pos, true)
        }
    }

    @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
    @JvmStatic
    fun captureSelectedValue(appCompatSpinner: AppCompatSpinner): String? {
        return appCompatSpinner.selectedItem as String
    }
}