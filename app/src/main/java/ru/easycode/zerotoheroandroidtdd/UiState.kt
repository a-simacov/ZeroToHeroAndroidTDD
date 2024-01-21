package ru.easycode.zerotoheroandroidtdd

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.io.Serializable

interface UiState : Serializable {

    @Composable
    fun Apply()

    fun enabled(): Boolean

    abstract class Abstract : UiState {

        override fun enabled() = true
    }

    object Init : Abstract() {
        @Composable
        override fun Apply() = Unit
    }

    object ShowProgress : Abstract() {

        @Composable
        override fun Apply() {
            CircularProgressIndicator(
                modifier = Modifier.addTag(id = R.string.id_progress),
            )
        }

        override fun enabled(): Boolean = false
    }

    data class ShowData(private val text: String) : Abstract() {

        @Composable
        override fun Apply() {
            Text(
                text = text,
                modifier = Modifier.addTag(id = R.string.id_text)
            )
        }
    }
}