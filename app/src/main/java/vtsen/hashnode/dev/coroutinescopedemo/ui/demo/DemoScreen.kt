package vtsen.hashnode.dev.coroutinescopedemo.ui.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import vtsen.hashnode.dev.coroutinescopedemo.ui.common.tag

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun DemoScreen() {

    val viewModel:DemoViewModel = viewModel()

    val lifeCycleScope = LocalLifecycleOwner.current.lifecycleScope
    val rememberCoroutineScope = rememberCoroutineScope()

    Column {
        TextWidget(title = "[Text]", text = viewModel.text , tag = tag)

        Button(onClick = {
            viewModel.launchCoroutine(GlobalScope, "GlobalScope")
        }) {
            Text("GlobalScope.launch()")
        }

        Button(onClick = {
            viewModel.launchCoroutine(viewModel.viewModelScope, "viewModelScope")

        }) {
            Text("viewModelScope.launch()")
        }

        Button(onClick = {
            viewModel.launchCoroutine(lifeCycleScope, "lifeCycleScope")
        }) {
            Text("lifeCycleScope.launch()")
        }

        Button(onClick = {
            viewModel.launchCoroutine(rememberCoroutineScope, "rememberCoroutineScope")

        }) {
            Text("rememberCoroutineScope.launch()")
        }

        Button(onClick = {
            viewModel.launchCoroutine(viewModel.customCoroutineScope, "customCoroutineScope")

        }) {
            Text("customCoroutineScope.launch()")
        }
    }

    /* Notes:
    1. calling GlobalScope.cancel() is not allowed
    2. after lifeCycleScope.cancel() is called, calling lifeCycleScope.launch() won't work anymore
    */
}

