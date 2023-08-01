package com.kweku.armah.core.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kweku.armah.core.domain.NUMBER_OF_QUIZ_QUESTIONS
import com.kweku.armah.core.domain.PASS_SCORE
import com.kweku.armah.core.domain.QUIZ_TIME_IN_MINUTES
import com.kweku.armah.resources.R

@Composable
fun ExamIntroCard(
    modifier: Modifier = Modifier,
    introHeader: String = "",
    navigateTo: () -> Unit = {},
    navigateBack: () -> Unit = {},
) {
    Card(
        modifier = Modifier.padding(20.dp).fillMaxSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Text(
            modifier = modifier,
            text = introHeader.uppercase(),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            ),
        )

        val bullet = stringResource(R.string.bulletin)
        val instructionList = listOf(
            stringResource(R.string.number_of_questions, NUMBER_OF_QUIZ_QUESTIONS),
            stringResource(R.string.has_a_time_limit_of, QUIZ_TIME_IN_MINUTES),
            stringResource(R.string.must_be_finished_in_one_sitting_you_cannot_save_and_finish_later),
            stringResource(R.string.questions_displayed_per_page_1),
            stringResource(R.string.will_allow_you_to_go_back_and_change_your_answers),
            /*"Will not let you finish with any questions unattempted.",*/
            stringResource(R.string.has_a_pass_mark_of, PASS_SCORE),
        )

        LazyColumn(
            modifier = modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))
            item {
                Text(
                    text = stringResource(R.string.intro_instructions),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                    ),
                )
            }

            items(instructionList) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = buildAnnotatedString {
                        withStyle(style = paragraphStyle) {
                            append(bullet)
                            append("\t\t")
                            append(it)
                        }
                    },
                )
            }

            item {
                Button(
                    modifier = Modifier.padding(top = 20.dp).width(300.dp).testTag("continue_button"),
                    onClick = navigateTo,
                ) {
                    Text(text = stringResource(R.string.btn_continue))
                }
            }

            item {
                TextButton(
                    modifier = Modifier.padding(top = 10.dp).width(300.dp).testTag("back_to_home"),
                    onClick = navigateBack,
                ) {
                    Text(text = stringResource(R.string.back_to_home))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExamCardPreview() {
    MaterialTheme {
        ExamIntroCard()
    }
}
