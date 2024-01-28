package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import android.os.Bundle

interface BundleWrapper {

    interface Save {

        fun save(list: ArrayList<CharSequence>)
    }

    interface Restore {

        fun restore(): List<CharSequence>
    }

    interface Mutable : Save, Restore

    class Base(private val bundle: Bundle) : Mutable {
        override fun save(list: ArrayList<CharSequence>) {
            bundle.putCharSequenceArrayList(KEY, list)
        }

        override fun restore(): List<CharSequence> {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getCharSequenceArrayList(KEY) ?: ArrayList()
            } else {
                bundle.getSerializable(KEY) as List<CharSequence>
            }
        }

        companion object {
            private const val KEY = "listKey"
        }

    }
}