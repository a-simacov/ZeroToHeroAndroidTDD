package ru.easycode.zerotoheroandroidtdd

import java.io.Serializable

interface Count : Serializable {

    fun increment(number: String): UiState

    fun result(): String

    data class Base(
        private val step: Int,
        private val max: Int
    ) : Count {

        private var result: String = "0"

        init {
            if (step <= 0)
                throw IllegalStateException(
                    "step should be positive, but was $step"
                )
            if (max <= 0)
                throw IllegalStateException(
                    "max should be positive, but was $max"
                )
            if (step > max)
                throw IllegalStateException(
                    "max should be more than step"
                )
        }

        override fun increment(number: String): UiState {
            val result = number.toInt() + step
            return if (result + step > max)
                UiState.Max(text = result.toString())
            else
                UiState.Base(text = result.toString())
        }

        override fun result(): String {
            return result
        }

    }
}