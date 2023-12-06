package com.kweku.armah.scrumexams.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kweku.armah.core.domain.repository.QuizQuestionsRepository
import com.kweku.armah.core.domain.usecase.DeleteQuizUseCase
import com.kweku.armah.core.domain.usecase.IsAnyQuizOngoingUseCase
import com.kweku.armah.psd.domain.ProfessionalScrumDeveloper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val isAnyQuizOngoingUseCase: IsAnyQuizOngoingUseCase,
    private val deleteQuizUseCase: DeleteQuizUseCase,
    @ProfessionalScrumDeveloper private val quizQuestionsRepository: QuizQuestionsRepository,
) :
    ViewModel() {

    private val _homeState =
        MutableStateFlow(HomeState(isLoading = true, isQuizOnGoing = false))
    val homeState: StateFlow<HomeState> = _homeState

    private fun getQuizState() {
        viewModelScope.launch {
            val isActiveQuiz = isAnyQuizOngoingUseCase().first()
            if (!isActiveQuiz) {
                deleteQuizUseCase(quizQuestionsRepository = quizQuestionsRepository)
            }
            _homeState.update {
                it.copy(isLoading = false, isQuizOnGoing = isActiveQuiz)
            }
        }
    }

    init {
        getQuizState()
    }
}

data class HomeState(val isLoading: Boolean = false, val isQuizOnGoing: Boolean = false)
