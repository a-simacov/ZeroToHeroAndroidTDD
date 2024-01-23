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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

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

    var text by rememberSaveable {
        mutableStateOf("Hello World!")
    }
    var editText by rememberSaveable {
        mutableStateOf("")
    }
    val enabled by remember {
        derivedStateOf {
            editText.length >= 3
        }
    }
    val onChange: (String) -> Unit = {
        editText = it
    }
    val onClick = {
        text = editText
        editText = ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .addTag(id = R.string.rootLayout),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = editText,
            onValueChange = onChange,
            modifier = Modifier.addTag(id = R.string.inputEditText)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = onClick,
            enabled = enabled,
            modifier = Modifier.addTag(id = R.string.actionButton)
        ) {
            Text(
                text = stringResource(id = R.string.actionButtonCaption)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            modifier = Modifier.addTag(id = R.string.titleTextView)
        )
    }
}

fun Modifier.addTag(@StringRes id: Int) = composed {
    this.testTag(stringResource(id = id))
}
