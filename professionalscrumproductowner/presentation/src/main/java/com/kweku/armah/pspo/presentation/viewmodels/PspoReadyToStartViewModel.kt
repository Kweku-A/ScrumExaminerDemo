package com.kweku.armah.pspo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kweku.armah.core.domain.repository.QuestionsRepository
import com.kweku.armah.core.domain.repository.QuizQuestionsRepository
import com.kweku.armah.core.domain.usecase.GenerateQuizUseCase
import com.kweku.armah.core.domain.usecase.SetQuizStartTimeUseCase
import com.kweku.armah.pspo.domain.ProfessionalScrumProductOwner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PspoReadyToStartViewModel @Inject constructor(
    private val generateQuizUseCase: GenerateQuizUseCase,
    private val setQuizStartTimeUseCase: SetQuizStartTimeUseCase,
    @ProfessionalScrumProductOwner private val quizQuestionsRepository: QuizQuestionsRepository,
    @ProfessionalScrumProductOwner private val questionsRepository: QuestionsRepository,
) :
    ViewModel() {

    private val _isQuizReadyStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isQuizReadyStateFlow: StateFlow<Boolean> = _isQuizReadyStateFlow
    fun generateQuiz() {
        viewModelScope.launch {
            _isQuizReadyStateFlow.value = generateQuizUseCase(
                quizQuestionsRepository = quizQuestionsRepository,
                questionsRepository = questionsRepository,
            ).first()
        }
    }

    fun startQuizTimer() {
        viewModelScope.launch {
            setQuizStartTimeUseCase()
        }
    }
}
