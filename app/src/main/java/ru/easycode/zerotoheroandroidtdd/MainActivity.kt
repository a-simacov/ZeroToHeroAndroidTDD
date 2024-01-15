package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var textState: TextState by rememberSaveable { mutableStateOf(TextState.Initial) }
    val btnOnClick = {
        textState = TextState.Removed
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .addTag(id = R.string.id_column),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        textState.ShowText()
        Button(
            onClick = btnOnClick,
            enabled = textState.btnEnabled(),
            modifier = Modifier.addTag(id = R.string.id_remove_button)
        ) {
            Text(
                text = stringResource(id = R.string.btn_remove_caption)
            )
        }
    }
}

interface TextState : Serializable {

    @Composable
    fun ShowText()

    fun btnEnabled(): Boolean

    object Initial : TextState {

        @Composable
        override fun ShowText() {
            Text(
                text = stringResource(id = R.string.hello_world),
                modifier = Modifier.addTag(id = R.string.id_text)
            )
        }

        override fun btnEnabled() = true
    }

    object Removed : TextState {

        @Composable
        override fun ShowText() = Unit

        override fun btnEnabled() = false
    }
}

private fun Modifier.addTag(@StringRes id: Int) = composed {
    this.testTag(stringResource(id = id))
}
