package com.kweku.armah.psm.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kweku.armah.core.domain.repository.QuizQuestionsRepository
import com.kweku.armah.core.domain.usecase.CalculateQuizResultsUseCase
import com.kweku.armah.core.domain.usecase.DeleteQuizUseCase
import com.kweku.armah.core.domain.usecase.GetQuizUseCase
import com.kweku.armah.core.domain.usecase.SetQuizOnOffUseCase
import com.kweku.armah.core.presentation.data.FinalScoreUi
import com.kweku.armah.psm.domain.ProfessionalScrumMaster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PsmResultScreenViewModel @Inject constructor(
    private val calculateQuizResultsUseCase: CalculateQuizResultsUseCase,
    private val deleteQuizUseCase: DeleteQuizUseCase,
    private val setQuizOnOffUseCase: SetQuizOnOffUseCase,
    private val getQuizUseCase: GetQuizUseCase,
    @ProfessionalScrumMaster private val quizQuestionsRepository: QuizQuestionsRepository,
) :
    ViewModel() {

    private val _finalScoreStateFlow = MutableStateFlow(FinalScoreUi())
    val finalScoreStateFlow = _finalScoreStateFlow.asStateFlow()

    private fun getFinalScore() {
        viewModelScope.launch {
            val quiz = getQuizUseCase(quizQuestionsRepository = quizQuestionsRepository)
            val result = calculateQuizResultsUseCase(quizList = quiz)
            _finalScoreStateFlow.value =
                FinalScoreUi(
                    finalScore = result.finalScore,
                    isPassMark = result.isPassScore,
                    remark = result.remark,
                )
        }
    }

    fun clearQuiz() {
        viewModelScope.launch {
            deleteQuizUseCase(quizQuestionsRepository = quizQuestionsRepository)
        }
    }

    fun resetOnGoingQuizFlag() {
        viewModelScope.launch {
            setQuizOnOffUseCase(isActive = false)
        }
    }

    init {
        getFinalScore()
    }
}
