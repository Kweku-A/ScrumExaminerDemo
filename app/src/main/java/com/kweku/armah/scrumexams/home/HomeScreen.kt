package com.kweku.armah.scrumexams.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kweku.armah.scrumexams.home.enums.HomeButtons

@Composable
fun HomeScreenRoute(
    navigateTo: (HomeButtons) -> Unit,
    navigateToQuiz: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val isQuizOnGoing by viewModel.isQuizOnGoing.collectAsStateWithLifecycle()

    if (!isQuizOnGoing) {
        viewModel.deleteAllQuiz()
    }

    HomeScreen(
        isQuizOnGoing = isQuizOnGoing,
        navigateTo = navigateTo,
        navigateToQuiz = navigateToQuiz,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(isQuizOnGoing: Boolean, navigateTo: (HomeButtons) -> Unit, navigateToQuiz: () -> Unit) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(listOf(HomeButtons.PSM, HomeButtons.PSD,HomeButtons.PSPO)) {
            Card(
                onClick = {navigateTo(it)},
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth().padding(10.dp).height(150.dp),
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        it.toString(),
                        style = TextStyle(
                            fontWeight = FontWeight.Black,
                            fontSize = 24.sp,

                        ),
                    )
                }
            }
        }
    }

    if (isQuizOnGoing) {
        AlertDialog(onDismissRequest = { }, modifier = Modifier) {
            Surface(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(10.dp)) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(text = "You have an onGoing quiz. You must finish it before you can proceed anywhere")
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        TextButton(onClick = { navigateToQuiz() }) {
                            Text(text = "Ok, proceed")
                        }
                    }
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(true, {}, {})
    }
}
