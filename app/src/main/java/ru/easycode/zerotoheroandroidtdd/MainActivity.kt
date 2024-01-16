package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp

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
    val textCount: Count by rememberSaveable {
        mutableStateOf(Count.Base(step = 2, max = 4))
    }
    var uiState: UiState by rememberSaveable {
        mutableStateOf(UiState.Base(text = "0"))
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .addTag(id = R.string.id_row),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                uiState = textCount.increment(number = uiState.text())
            },
            enabled = uiState.enabled(),
            modifier = Modifier.addTag(id = R.string.id_increment_button)
        ) {
            Text(
                text = stringResource(id = R.string.btn_increment_caption)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = uiState.text(),
            modifier = Modifier.addTag(id = R.string.id_text)
        )
    }
}

private fun Modifier.addTag(@StringRes id: Int) = composed {
    this.testTag(stringResource(id = id))
}
