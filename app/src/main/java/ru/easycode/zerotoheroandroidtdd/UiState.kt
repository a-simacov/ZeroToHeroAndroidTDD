package ru.easycode.zerotoheroandroidtdd

import java.io.Serializable

interface UiState : Serializable {

    fun text(): String

    fun enabledInc(): Boolean

    fun enabledDec(): Boolean

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

        override fun enabledInc(): Boolean {
            return true
        }

        override fun enabledDec(): Boolean {
            return true
        }
    }

    data class Max(
        private val text: String
    ) : Abstract(text) {

        override fun enabledInc(): Boolean {
            return false
        }

        override fun enabledDec(): Boolean {
            return true
        }
    }

    data class Min(
        private val text: String
    ) : Abstract(text) {

        override fun enabledInc(): Boolean {
            return true
        }

        override fun enabledDec(): Boolean {
            return false
        }
    }
}