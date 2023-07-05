package com.kweku.armah.scrumexams

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kweku.armah.scrumexams.navigation.AppNavHost
import com.kweku.armah.scrumexams.ui.theme.ScrumExamsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScrumExamsTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    Scaffold {
                        val modifier: Modifier = Modifier.padding(it)
                        AppNavHost(modifier = modifier)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScrumExamsTheme {
        Greeting("Android")
    }
}
