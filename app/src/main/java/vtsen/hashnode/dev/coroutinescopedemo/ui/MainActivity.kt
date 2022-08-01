package vtsen.hashnode.dev.coroutinescopedemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import vtsen.hashnode.dev.coroutinescopedemo.ui.demo.CommonScreen
import vtsen.hashnode.dev.coroutinescopedemo.ui.demo.DemoScreen
import vtsen.hashnode.dev.coroutinescopedemo.ui.theme.CoroutineScopeDemoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(useSystemUIController: Boolean = true) {
    CoroutineScopeDemoAppTheme(useSystemUIController = useSystemUIController) {

        CommonScreen {
            DemoScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen(useSystemUIController = false)
}