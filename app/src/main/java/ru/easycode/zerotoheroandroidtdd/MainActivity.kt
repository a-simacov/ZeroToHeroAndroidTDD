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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (application as App).viewModel

        setContent {
            MainScreen(viewModel = viewModel)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.save(BundleWrapper.Base(outState))
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        viewModel.restore(BundleWrapper.Base(savedInstanceState))
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {

    val contentList by viewModel.liveData().observeAsState()//rememberMutableStateListOf<String>()
    var editText by rememberSaveable {
        mutableStateOf("")
    }
    val onChange: (String) -> Unit = {
        editText = it
    }
    val onClick = {
        viewModel.add(editText)
        editText = ""
    }

    Column {
        AddListViews(
            editText = editText,
            onChange = onChange,
            onClick = onClick
        )
        DataList(contentList!!)
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
fun DataList(contentList: List<CharSequence>) {
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxWidth()
            .addTag(id = R.string.contentLayout),
        verticalArrangement = Arrangement.spacedBy(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        itemsIndexed(
            items = contentList,
            key = { index, _ -> index }
        ) { _, item ->
            Column {
                Text(
                    text = item.toString(),
                    fontSize = 40.sp,
                    modifier = textItemModifier
                )
            }
        }
    }
}

// вынос модификатора для элемента списка - нет рекомпозиции при изменении списка
private val textItemModifier = Modifier.addTag(id = R.string.elementTextView)

fun Modifier.addTag(@StringRes id: Int) = composed {
    this.testTag(stringResource(id = id))
}
