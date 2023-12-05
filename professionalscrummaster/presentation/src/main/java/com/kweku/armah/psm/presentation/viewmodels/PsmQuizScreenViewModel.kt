package com.kweku.armah.psm.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kweku.armah.core.domain.IODispatcher
import com.kweku.armah.core.domain.model.Answer
import com.kweku.armah.core.domain.model.Question
import com.kweku.armah.core.domain.repository.QuizQuestionsRepository
import com.kweku.armah.core.domain.usecase.GetCurrentQuizTimeLeftUseCase
import com.kweku.armah.core.domain.usecase.GetQuizUseCase
import com.kweku.armah.core.domain.usecase.SetQuizOnOffUseCase
import com.kweku.armah.core.domain.usecase.UpdateQuizWithSelectedAnswersUseCase
import com.kweku.armah.core.presentation.data.AnswerUi
import com.kweku.armah.core.presentation.data.QuestionsUi
import com.kweku.armah.psm.domain.ProfessionalScrumMaster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PsmQuizScreenViewModel @Inject constructor(
    private val getQuizUseCase: GetQuizUseCase,
    private val setQuizOnOffUseCase: SetQuizOnOffUseCase,
    private val getCurrentQuizTimeLeftUseCase: GetCurrentQuizTimeLeftUseCase,
    private val updateQuizWithSelectedAnswersUseCase: UpdateQuizWithSelectedAnswersUseCase,
    @ProfessionalScrumMaster private val quizQuestionsRepository: QuizQuestionsRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher,

) :
    ViewModel() {

    private var listOfQuestions = listOf<QuestionsUi>()
    private val _timeLeftStateFlow = MutableStateFlow("")
    val timeLeftStateFlow = _timeLeftStateFlow.asStateFlow()

    private val _timeFinished = MutableStateFlow(false)
    val timeFinished = _timeFinished.asStateFlow()

    init {
        getQuestions()
        getTimeLeft()
    }

    private val _quizQuestionsStateFlow: MutableStateFlow<List<QuestionsUi>> =
        MutableStateFlow(emptyList())
    val quizQuestionsStateFlow = _quizQuestionsStateFlow.asStateFlow()

    private fun getQuestions() {
        viewModelScope.launch(dispatcher) {
            listOfQuestions =
                getQuizUseCase(quizQuestionsRepository = quizQuestionsRepository).map { question ->
                    QuestionsUi(
                        id = question.id,
                        question = question.question,
                        answers = question.answers.map { answer ->
                            AnswerUi(data = answer.data)
                        },
                        correctAnswers = question.correctAnswers.map { answer ->
                            AnswerUi(data = answer.data)
                        },
                        selectedAnswers = question.selectedAnswers.map { answer ->
                            AnswerUi(data = answer.data)
                        }.toMutableSet(),
                    )
                }
            _quizQuestionsStateFlow.value = listOfQuestions
        }
    }

    fun setAnswers(selectedAnswersUi: List<AnswerUi>, index: Int) {
        val questionUI = listOfQuestions[index]
        val question = Question(
            id = questionUI.id,
            question = questionUI.question,
            answers = questionUI.answers.map { answer ->
                Answer(data = answer.data)
            },
            correctAnswers = questionUI.correctAnswers.map { answer ->
                Answer(data = answer.data)
            },
            selectedAnswers = selectedAnswersUi.map {
                Answer(data = it.data)
            },
        )
        viewModelScope.launch(dispatcher) {
            updateQuizWithSelectedAnswersUseCase(
                quizQuestionsRepository = quizQuestionsRepository,
                question = question,
            )
        }
    }

    fun setQuizStateOnOff(shouldReview: Boolean) {
        viewModelScope.launch(dispatcher) {
            setQuizOnOffUseCase(isActive = !shouldReview)
        }
    }

    private fun getTimeLeft() {
        viewModelScope.launch(dispatcher) {
            getCurrentQuizTimeLeftUseCase().collectLatest {
                val left = String.format(
                    "%02d: %02d",
                    it.minutes,
                    it.seconds,
                )
                Log.d("TIMER_TASK", "getTimeLeft: $it")
                _timeLeftStateFlow.value = left
                _timeFinished.value = it.isEnded
            }
        }
    }
}
