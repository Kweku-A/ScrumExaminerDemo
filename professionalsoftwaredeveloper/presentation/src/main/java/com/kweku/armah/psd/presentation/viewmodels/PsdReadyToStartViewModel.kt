package com.kweku.armah.psd.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kweku.armah.core.domain.IODispatcher
import com.kweku.armah.core.domain.repository.QuestionsRepository
import com.kweku.armah.core.domain.repository.QuizQuestionsRepository
import com.kweku.armah.core.domain.usecase.GenerateQuizUseCase
import com.kweku.armah.core.domain.usecase.SetQuizStartTimeUseCase
import com.kweku.armah.psd.domain.ProfessionalScrumDeveloper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PsdReadyToStartViewModel @Inject constructor(
    private val generateQuizUseCase: GenerateQuizUseCase,
    private val setQuizStartTimeUseCase: SetQuizStartTimeUseCase,
    @ProfessionalScrumDeveloper private val quizQuestionsRepository: QuizQuestionsRepository,
    @ProfessionalScrumDeveloper private val questionsRepository: QuestionsRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) :
    ViewModel() {

    private val _isQuizReadyStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isQuizReadyStateFlow: StateFlow<Boolean> = _isQuizReadyStateFlow
    fun generateQuiz() {
        viewModelScope.launch(dispatcher) {
            _isQuizReadyStateFlow.value = generateQuizUseCase(
                quizQuestionsRepository = quizQuestionsRepository,
                questionsRepository = questionsRepository,
            ).first()
        }
    }

    fun startQuizTimer() {
        viewModelScope.launch(dispatcher) {
            setQuizStartTimeUseCase()
        }
    }
}
