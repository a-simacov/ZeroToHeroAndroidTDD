package ru.easycode.zerotoheroandroidtdd

import java.io.Serializable

interface UiState : Serializable {

    fun text(): String

    fun enabled(): Boolean

    data class Base(
        private val text: String
    ) : UiState {

        override fun text(): String {
            return text
        }

        override fun enabled(): Boolean {
            return true
        }
    }

    data class Max(
        private val text: String
    ) : UiState {

        override fun text(): String {
            return text
        }

        override fun enabled(): Boolean {
            return false
        }
    }
}