package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.unit.dp
import java.io.Serializable

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    var uiState: UiState by rememberSaveable { mutableStateOf(UiState.Init) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .addTag(id = R.string.rootLayout),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = uiState.editText(),
            onValueChange = {
                uiState = UiState.OnChange(it, uiState.text())
            },
            modifier = Modifier.addTag(id = R.string.inputEditText)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = { uiState = UiState.OnClick(uiState.editText()) },
            enabled = uiState.enabled(),
            modifier = Modifier.addTag(id = R.string.actionButton)
        ) {
            Text(
                text = stringResource(id = R.string.actionButtonCaption)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = uiState.text(),
            modifier = Modifier.addTag(id = R.string.titleTextView)
        )
    }
}

private interface UiState : Serializable {

    fun enabled(): Boolean

    fun editText(): String

    fun text(): String

    object Init : UiState {

        override fun enabled() = false

        override fun editText() = ""

        override fun text() = "Hello World!"

    }

    class OnChange(private val editText: String, private val prevText: String) : UiState {

        override fun enabled() = editText.length >= 3

        override fun editText() = editText

        override fun text() = prevText

    }

    class OnClick(private val editText: String) : UiState {

        override fun enabled() = false

        override fun editText() = ""

        override fun text() = editText

    }
}


fun Modifier.addTag(@StringRes id: Int) = composed {
    this.testTag(stringResource(id = id))
}
