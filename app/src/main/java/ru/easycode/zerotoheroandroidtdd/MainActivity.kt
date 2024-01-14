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
    var showText by rememberSaveable { mutableStateOf(true) }
    val btnOnClick = { showText = false }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .addTag(id = R.string.id_column),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (showText)
            Text(
                text = stringResource(id = R.string.hello_world),
                modifier = Modifier.addTag(id = R.string.id_text)
            )
        Button(
            onClick = btnOnClick,
            modifier = Modifier.addTag(id = R.string.id_hide_button)
        ) {
            Text(
                text = stringResource(id = R.string.btn_hide_caption)
            )
        }
    }
}

private fun Modifier.addTag(@StringRes id: Int) = composed {
    this.testTag(stringResource(id = id))
}
