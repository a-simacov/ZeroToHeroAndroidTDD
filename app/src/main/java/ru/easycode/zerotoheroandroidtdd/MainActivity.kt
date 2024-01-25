package ru.easycode.zerotoheroandroidtdd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
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

@Composable
fun <T : Any> rememberMutableStateListOf(vararg elements: T): SnapshotStateList<T> {
    return rememberSaveable(
        saver = listSaver(
            save = { stateList ->
                if (stateList.isNotEmpty())
                    stateList.toList()
                else
                    emptyList<T>()
            },
            restore = { it.toMutableStateList() }
        )
    ) {
        elements.toList().toMutableStateList()
    }
}

@Composable
fun MainScreen() {

    val contentList = rememberMutableStateListOf<String>()
    var editText by rememberSaveable {
        mutableStateOf("")
    }
    val onChange: (String) -> Unit = {
        editText = it
    }
    val onClick = {
        contentList.add(editText)
        editText = ""
    }

    Column {
        AddListViews(
            editText = editText,
            onChange = onChange,
            onClick = onClick
        )
        DataList(contentList)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddListViews(
    editText: String,
    onChange: (String) -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .addTag(id = R.string.topLayout),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            value = editText,
            onValueChange = onChange,
            modifier = Modifier.addTag(id = R.string.inputEditText)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = onClick,
            modifier = Modifier.addTag(id = R.string.actionButton)
        ) {
            Text(
                text = stringResource(id = R.string.actionButtonCaption)
            )
        }
    }
}

@Composable
fun DataList(contentList: SnapshotStateList<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .addTag(id = R.string.contentLayout),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(contentList) {
            Text(
                text = it,
                modifier = Modifier.addTag(id = R.string.elementTextView)
            )
            Divider()
        }
    }
}

fun Modifier.addTag(@StringRes id: Int) = composed {
    this.testTag(stringResource(id = id))
}
