package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.activity.compose.setContent
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
    var text by rememberSaveable { mutableStateOf("Hello World!") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag(
                stringResource(id = R.string.id_column)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            modifier = Modifier.testTag(
                stringResource(id = R.string.id_text)
            )
        )
        Button(
            onClick = {
                text = "I am an Android Developer!"
            },
            modifier = Modifier.testTag(
                stringResource(id = R.string.id_changeButton)
            )
        ) {
            Text(
                text = stringResource(id = R.string.btn_caption)
            )
        }
    }
}