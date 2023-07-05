package com.kweku.armah.core.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kweku.armah.core.presentation.data.FinalScoreUi
import com.kweku.armah.resources.R

@Composable
fun ResultScreenBody(
    modifier: Modifier = Modifier,
    finalScore: FinalScoreUi = FinalScoreUi(),
    resetQuizFlag: () -> Unit = {},
    navigateToReview: () -> Unit = {},
    clearFinishedQuiz: () -> Unit = {},
    navigateToHome: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = modifier,
                text = stringResource(R.string.your_final_score_is).uppercase(),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                ),
            )

            val scoreColor = if (finalScore.isPassMark) Color.Green else Color.Red

            Text(
                text = finalScore.finalScore,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = scoreColor,
                ),
            )

            Text(
                text = finalScore.remark,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = scoreColor,
                ),
            )

            Button(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(300.dp),
                onClick = {
                    resetQuizFlag()
                    navigateToReview()
                },
            ) {
                Text(text = stringResource(R.string.review_quiz))
            }

            TextButton(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .width(300.dp),
                onClick = {
                    clearFinishedQuiz()
                    navigateToHome()
                },
            ) {
                Text(text = stringResource(R.string.return_to_home))
            }
        }
    }
}

@Preview
@Composable
fun ResultsBodyPreview() {
    MaterialTheme {
        ResultScreenBody()
    }
}
