package vtsen.hashnode.dev.coroutinescopedemo.ui.demo

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import vtsen.hashnode.dev.coroutinescopedemo.ui.common.tag

class DemoViewModel : ViewModel() {
    var job: Job? = null
    var text by mutableStateOf("")

    val customCoroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCleared() {
        super.onCleared()
        Log.d(tag, "[ViewModel]: destroyed")
    }

    fun launchCoroutine(coroutineScope: CoroutineScope, info: String) {
        job?.cancel()
        job = coroutineScope.launch {
            repeat(10000) { value ->
                delay(1000)
                Log.d(tag, "Set $info text to $value")
                text = value.toString()
            }
        }
    }
}