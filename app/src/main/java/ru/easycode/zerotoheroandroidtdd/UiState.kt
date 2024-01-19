package ru.easycode.zerotoheroandroidtdd

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface UiState {

    @Composable
    fun Apply()

    fun enabled(): Boolean

    abstract class Abstract : UiState {

        override fun enabled() = true
    }

    object Init : Abstract() {
        @Composable
        override fun Apply() {

        }
    }

    object ShowProgress : Abstract() {

        @Composable
        override fun Apply() {
            CircularProgressIndicator(
                modifier = Modifier
                    .addTag(id = R.string.id_progress),
            )
        }

        override fun enabled(): Boolean = false
    }

    object ShowData : Abstract() {

        @Composable
        override fun Apply() {
            Text(
                text = "loaded",
                modifier = Modifier.addTag(id = R.string.id_text)
            )
        }
    }
}