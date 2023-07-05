package com.kweku.armah.core.presentation.composables.multiplechoice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kweku.armah.core.presentation.data.AnswerUi
import com.kweku.armah.core.presentation.data.QuestionsUi

@Composable
internal fun SingleAnswerSelection(
    shouldReview: Boolean = false,
    questionItem: QuestionsUi = QuestionsUi(),
    selectedAnswers: (List<AnswerUi>) -> Unit = {},
) {
    val answers = questionItem.answers

    var selectedAnswer by remember { mutableStateOf(questionItem.selectedAnswers.toList()) }

    val isSelectedItem: (AnswerUi) -> Boolean = { selectedAnswer.contains(it) }

    val onChangeState: (AnswerUi) -> Unit = {
        selectedAnswer = listOf(it)
        selectedAnswers(selectedAnswer)
    }

    val isCorrectAnswer: (AnswerUi) -> Boolean = {
        questionItem.correctAnswers.contains(it)
    }

    Column(Modifier) {
        answers.forEach { answer ->

            val answerBackground = if (shouldReview && isCorrectAnswer(answer)) {
                Color.Green.copy(alpha = 0.2f)
            } else {
                Color.Transparent
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = answerBackground)
                    .selectable(
                        selected = true,
                        enabled = !shouldReview,
                        onClick = { onChangeState(answer) },
                    )
                    .padding(8.dp),
            ) {
                RadioButton(
                    selected = isSelectedItem(answer),
                    onClick = null,
                    enabled = true,
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = answer.data,
                )
            }
        }
    }
}

@Composable
internal fun MultipleAnswersSelection(
    shouldReview: Boolean = false,
    questionItem: QuestionsUi = QuestionsUi(),
    selectedAnswers: (List<AnswerUi>) -> Unit = {},
) {
    var multipleCheckedAnswers by remember {
        mutableStateOf(questionItem.selectedAnswers.toSet())
    }
    val answers = questionItem.answers

    Column(modifier = Modifier.fillMaxWidth()) {
        answers.forEachIndexed { index, answer ->

            val answerBackground =
                if (shouldReview && questionItem.correctAnswers.contains(answer)) {
                    Color.Green.copy(alpha = 0.2f)
                } else {
                    Color.Transparent
                }

            val isSelected =
                multipleCheckedAnswers.contains(answer)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = answerBackground)
                    .padding(vertical = 7.dp)
                    .selectable(
                        selected = true,
                        enabled = !shouldReview,
                        onClick = { },
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(checked = isSelected, onCheckedChange = { isChecked ->
                    multipleCheckedAnswers = if (isChecked) {
                        multipleCheckedAnswers + answer
                    } else {
                        multipleCheckedAnswers - answer
                    }
                    selectedAnswers(multipleCheckedAnswers.toList())
                }, enabled = !shouldReview)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = answer.data)
            }
        }
    }
}

@Preview
@Composable
fun MultipleChoicePreview() {
    MaterialTheme {
        Column {
            SingleAnswerSelection()
            MultipleAnswersSelection()
        }
    }
}
