package com.kweku.armah.core.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kweku.armah.core.presentation.composables.dialogs.DualOptionDialog
import com.kweku.armah.core.presentation.composables.multiplechoice.MultipleAnswersSelection
import com.kweku.armah.core.presentation.composables.multiplechoice.SingleAnswerSelection
import com.kweku.armah.core.presentation.data.AnswerUi
import com.kweku.armah.core.presentation.data.QuestionsUi
import com.kweku.armah.core.presentation.data.fakes.fakeListOfQuestions
import com.kweku.armah.resources.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun QuizScreenBody(
    modifier: Modifier = Modifier,
    questionsCount: Int = 0,
    pagerState: PagerState = rememberPagerState { 0 },
    shouldReview: Boolean = false,
    timeLeft: () -> String = { "00:00" },
    listOfQuestions: List<QuestionsUi> = emptyList(),
    selectedAnswers: (List<AnswerUi>) -> Unit = {},
    navigateToHome: () -> Unit = {},
    onFinishQuiz: () -> Unit = {},
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        QuizTimer(
            shouldReview = shouldReview,
            timeLeft = timeLeft,
            modifier = Modifier
                .align(alignment = Alignment.End)
                .padding(bottom = 10.dp, end = 10.dp),
        )

        HorizontalPager(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.02f))
                .weight(9f)
                .fillMaxSize()
                .padding(12.dp),
            state = pagerState,
            userScrollEnabled = false,
        ) { count ->

            val questionItem = listOfQuestions[count]
            val questionNumber = count + 1

            LazyColumn(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                userScrollEnabled = true,
                modifier = Modifier.fillMaxSize(),
            ) {
                item {
                    Surface(
                        shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.4f),
                        modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 150.dp),
                    ) {
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = "$questionNumber. ${questionItem.question}",
                            style = TextStyle(
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start,
                            ),
                        )
                    }
                }

                item {
                    Surface(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 450.dp),
                    ) {
                        if (questionItem.correctAnswers.size > 1) {
                            MultipleAnswersSelection(
                                shouldReview = shouldReview,
                                questionItem = questionItem,
                                selectedAnswers = selectedAnswers,
                            )
                        } else {
                            SingleAnswerSelection(
                                shouldReview = shouldReview,
                                questionItem = questionItem,
                                selectedAnswers = selectedAnswers,
                            )
                        }
                    }
                }
            }
        }

        val shouldDisablePreviousButton: () -> Boolean = {
            pagerState.currentPage != 0
        }

        val shouldDisableNextButton: () -> Boolean = {
            pagerState.currentPage != questionsCount - 1
        }

        var isFinishDialogShown by remember {
            mutableStateOf(false)
        }

        val onFinishQuizClicked: () -> Unit = {
            isFinishDialogShown = true
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 10.dp),
            ) {
                val onClick = if (shouldReview) navigateToHome else onFinishQuizClicked
                val endQuizOrReviewText = if (shouldReview) {
                    stringResource(R.string.end_review)
                } else {
                    stringResource(
                        R.string.end_quiz,
                    )
                }

                TextButton(onClick = onClick) {
                    Text(text = endQuizOrReviewText)
                }

                val dialogMessage =
                    stringResource(R.string.this_will_submit_your_answers_and_give_final_score)
                val negativeButton = stringResource(R.string.cancel)
                val positiveButton = stringResource(R.string.finish)

                if (isFinishDialogShown) {
                    DualOptionDialog(
                        header = endQuizOrReviewText,
                        dialogMessage = dialogMessage,
                        isDialogShown = { isFinishDialogShown = false },
                        negativeButtonText = negativeButton,
                        onPositiveClick = onFinishQuiz,
                        positiveButtonText = positiveButton,
                    )
                }

                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Button(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .weight(1f),
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        },
                        enabled = shouldDisablePreviousButton(),
                    ) {
                        Text(text = stringResource(R.string.previous))
                    }

                    Spacer(modifier = Modifier.width(40.dp))

                    Button(
                        modifier = Modifier
                            .padding(top = 5.dp)
                            .weight(1f),
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        },
                        enabled = shouldDisableNextButton(),
                    ) {
                        Text(text = stringResource(R.string.next))
                    }
                }
            }
        }
    }
}

@Composable
private fun QuizTimer(
    shouldReview: Boolean,
    timeLeft: () -> String,
    modifier: Modifier = Modifier,
) {
    val isVisible: () -> Boolean = { !shouldReview && timeLeft().isNotEmpty() }
    AnimatedVisibility(visible = isVisible(), modifier = modifier) {
        Surface(
            color = MaterialTheme.colorScheme.tertiary,
            shape = RoundedCornerShape(10.dp),
        ) {
            val text = stringResource(R.string.time_left, timeLeft())

            Text(
                text = text,
                Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
                style = TextStyle(
                    textAlign = TextAlign.End,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Black,
                ),
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun QuizBodyPreview() {
    MaterialTheme {
        Surface(color = Color.White) {
            QuizScreenBody(
                listOfQuestions = fakeListOfQuestions,
                questionsCount = fakeListOfQuestions.size,
            )
        }
    }
}
