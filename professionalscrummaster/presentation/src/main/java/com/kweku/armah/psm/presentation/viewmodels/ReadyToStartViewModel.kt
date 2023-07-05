package com.kweku.armah.psm.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kweku.armah.core.domain.repository.QuestionsRepository
import com.kweku.armah.core.domain.repository.QuizQuestionsRepository
import com.kweku.armah.core.domain.usecase.GenerateQuizUseCase
import com.kweku.armah.core.domain.usecase.SetQuizStartTimeUseCase
import com.kweku.armah.psm.domain.ProfessionalScrumMaster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReadyToStartViewModel @Inject constructor(
    private val generateQuizUseCase: GenerateQuizUseCase,
    private val setQuizStartTimeUseCase: SetQuizStartTimeUseCase,
    @ProfessionalScrumMaster private val quizQuestionsRepository: QuizQuestionsRepository,
    @ProfessionalScrumMaster private val questionsRepository: QuestionsRepository,
) :
    ViewModel() {

    private val _isQuizReadyStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isQuizReadyStateFlow: StateFlow<Boolean> = _isQuizReadyStateFlow
    fun generateQuiz() {
        viewModelScope.launch(Dispatchers.IO) {
            _isQuizReadyStateFlow.value = generateQuizUseCase(
                quizQuestionsRepository = quizQuestionsRepository,
                questionsRepository = questionsRepository,
            ).first()
        }
    }

    fun startQuizTimer() {
        viewModelScope.launch(Dispatchers.IO) {
            setQuizStartTimeUseCase()
        }
    }
}
