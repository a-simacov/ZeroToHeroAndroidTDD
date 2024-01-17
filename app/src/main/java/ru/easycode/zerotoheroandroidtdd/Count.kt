package ru.easycode.zerotoheroandroidtdd

import java.io.Serializable

interface Count : Serializable {

    fun initial(number: String): UiState

    fun increment(number: String): UiState

    fun decrement(number: String): UiState

    data class Base(
        private val step: Int,
        private val max: Int,
        private val min: Int
    ) : Count {

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
            if (min > max)
                throw IllegalStateException(
                    "max should be more than min"
                )
        }

        override fun initial(number: String): UiState {
            val numberInt = number.toInt()
            return when (numberInt) {
                min -> UiState.Min(number)
                max -> UiState.Max(number)
                else -> UiState.Base(number)
            }
        }

        override fun increment(number: String): UiState {
            val result = number.toInt() + step
            return if (result + step > max)
                UiState.Max(text = result.toString())
            else
                UiState.Base(text = result.toString())
        }

        override fun decrement(number: String): UiState {
            val result = number.toInt() - step
            return if (result - step < min)
                UiState.Min(text = result.toString())
            else
                UiState.Base(text = result.toString())
        }
    }
}