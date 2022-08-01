package vtsen.hashnode.dev.coroutinescopedemo.ui.demo

import android.util.Log
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import kotlinx.coroutines.*
import vtsen.hashnode.dev.coroutinescopedemo.ui.common.tag

@Composable
fun LaunchedButton(
    coroutineScope: CoroutineScope,
    coroutineScopeInfo: String,
    setText: (String) -> Unit) {

    var job: Job? by remember { mutableStateOf(null) }

    Button(onClick = {
        job?.cancel()
        job = coroutineScope.launch {
            repeat(10000) { value ->
                delay(1000)
                Log.d(tag, "Set $coroutineScopeInfo text to $value")
                setText(value.toString())
            }
        }
    }) {
        Text("${coroutineScopeInfo}.launch()")
    }
}