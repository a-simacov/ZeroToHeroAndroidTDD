package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    var showProgress by remember { mutableStateOf(false) }
    var showResult by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val onClick: () -> Unit = {
        coroutineScope.launch {
            showProgress = true
            delay(1000)
            showProgress = false
            showResult = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .addTag(id = R.string.id_column),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showProgress) {
            CircularProgressIndicator(
                modifier = Modifier
                    .addTag(id = R.string.id_progress),
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        if (showResult) {
            Text(
                text = "loading",
                modifier = Modifier.addTag(id = R.string.id_text)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }
        Button(
            onClick = onClick,
            enabled = !showProgress,
            modifier = Modifier.addTag(id = R.string.id_load_button)
        ) {
            Text(
                text = stringResource(id = R.string.btn_load_caption)
            )
        }
    }
}

private fun Modifier.addTag(@StringRes id: Int) = composed {
    this.testTag(stringResource(id = id))
}
