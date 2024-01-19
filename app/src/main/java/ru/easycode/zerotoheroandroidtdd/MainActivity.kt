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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel(LiveDataWrapper.Base(), Repository.Base())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(viewModel = viewModel)
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {

    val uiState by viewModel.liveData().observeAsState(UiState.Init)
    val onClick: () -> Unit = {
        viewModel.load()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .addTag(id = R.string.id_column),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        uiState.Apply()
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = onClick,
            enabled = uiState.enabled(),
            modifier = Modifier.addTag(id = R.string.id_load_button)
        ) {
            Text(
                text = stringResource(id = R.string.btn_load_caption)
            )
        }
    }
}

fun Modifier.addTag(@StringRes id: Int) = composed {
    this.testTag(stringResource(id = id))
}
