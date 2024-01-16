package ru.easycode.zerotoheroandroidtdd

import java.io.Serializable

    interface Count : Serializable {

    fun increment(number: String): String

    fun result(): String

    data class Base(
        private val step: Int
    ) : Count {

        private var result: String = "0"

        init {
            if (step <= 0)
                throw IllegalStateException(
                    "step should be positive, but was $step"
                )
        }

        override fun increment(number: String): String {
            result = (step + number.toInt()).toString()
            return result
        }

        override fun result(): String {
            return result
        }

    }
}