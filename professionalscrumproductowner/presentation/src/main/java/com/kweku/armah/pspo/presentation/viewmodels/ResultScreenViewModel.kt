package com.kweku.armah.pspo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kweku.armah.core.domain.repository.QuizQuestionsRepository
import com.kweku.armah.core.domain.usecase.CalculateQuizResultsUseCase
import com.kweku.armah.core.domain.usecase.DeleteQuizUseCase
import com.kweku.armah.core.domain.usecase.GetQuizUseCase
import com.kweku.armah.core.domain.usecase.SetQuizOnOffUseCase
import com.kweku.armah.core.presentation.data.FinalScoreUi
import com.kweku.armah.pspo.domain.ProfessionalScrumProductOwner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultScreenViewModel @Inject constructor(
    private val calculateQuizResultsUseCase: CalculateQuizResultsUseCase,
    private val deleteQuizUseCase: DeleteQuizUseCase,
    private val setQuizOnOffUseCase: SetQuizOnOffUseCase,
    private val getQuizUseCase: GetQuizUseCase,
    @ProfessionalScrumProductOwner private val quizQuestionsRepository: QuizQuestionsRepository,
) :
    ViewModel() {

    private val _finalScoreStateFlow = MutableStateFlow(FinalScoreUi())
    val finalScoreStateFlow = _finalScoreStateFlow.asStateFlow()

    private fun getFinalScore() {
        viewModelScope.launch(Dispatchers.IO) {
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
        viewModelScope.launch(Dispatchers.IO) {
            deleteQuizUseCase(quizQuestionsRepository = quizQuestionsRepository)
        }
    }

    fun resetOnGoingQuizFlag() {
        viewModelScope.launch(Dispatchers.IO) {
            setQuizOnOffUseCase(isActive = false)
        }
    }

    init {
        getFinalScore()
    }
}
