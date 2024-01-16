package ru.easycode.zerotoheroandroidtdd

import java.io.Serializable

interface UiState : Serializable {

    fun text(): String

    fun enabled(): Boolean

    abstract class Abstract(
        private val text: String
    ) : UiState {

        override fun text(): String {
            return text
        }
    }

    data class Base(
        private val text: String
    ) : Abstract(text) {

        override fun enabled(): Boolean {
            return true
        }
    }

    data class Max(
        private val text: String
    ) : Abstract(text) {

        override fun enabled(): Boolean {
            return false
        }
    }
}