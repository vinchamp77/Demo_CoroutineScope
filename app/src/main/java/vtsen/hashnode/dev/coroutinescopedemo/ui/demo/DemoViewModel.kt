package vtsen.hashnode.dev.coroutinescopedemo.ui.demo

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import vtsen.hashnode.dev.coroutinescopedemo.ui.common.tag
import java.lang.Thread.sleep

class DemoViewModel: ViewModel() {
    var job: Job? = null
    var text by mutableStateOf("")

    val customCoroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCleared() {
        super.onCleared()
        Log.d(tag, "[ViewModel]: destroyed")
    }

    fun launchCoroutine(coroutineScope: CoroutineScope, info: String) {
        job?.cancel()
        job = coroutineScope.launch(Dispatchers.Default) {
            simulateCoroutineTask(info)
        }
    }

    fun launchCoroutineNonCancellable(coroutineScope: CoroutineScope, info: String) {
        job?.cancel()
        job = coroutineScope.launch(Dispatchers.Default) {
            simulateCoroutineTask(info, blocking = true)
        }
    }

    fun launchWhenCreated(lifeCycleScope: LifecycleCoroutineScope, info: String) {
        job?.cancel()
        job = lifeCycleScope.launchWhenCreated {
            simulateCoroutineTask(info)
        }
    }

    fun launchWhenStarted(lifeCycleScope: LifecycleCoroutineScope, info: String) {
        job?.cancel()
        job = lifeCycleScope.launchWhenStarted {
            simulateCoroutineTask(info)
        }
    }

    fun launchWhenResumed(lifeCycleScope: LifecycleCoroutineScope, info: String) {
        job?.cancel()
        job = lifeCycleScope.launchWhenResumed {
            simulateCoroutineTask(info)
        }
    }

    private suspend fun simulateCoroutineTask(info: String, blocking: Boolean = false) {
        repeat(10000) { value ->
            if(blocking) {
                sleep(1000)
            } else {
                delay(1000)
            }
            Log.d(tag, "Set $info text to $value")
            text = value.toString()
        }
    }
}