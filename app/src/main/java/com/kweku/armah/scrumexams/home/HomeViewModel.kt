package com.kweku.armah.scrumexams.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kweku.armah.core.domain.IODispatcher
import com.kweku.armah.core.domain.repository.QuizQuestionsRepository
import com.kweku.armah.core.domain.usecase.DeleteQuizUseCase
import com.kweku.armah.core.domain.usecase.IsAnyQuizOngoingUseCase
import com.kweku.armah.core.domain.usecase.SetQuizOnOffUseCase
import com.kweku.armah.psd.domain.ProfessionalScrumDeveloper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    isAnyQuizOngoingUseCase: IsAnyQuizOngoingUseCase,
    private val setQuizOnOffUseCase: SetQuizOnOffUseCase,
    private val deleteQuizUseCase: DeleteQuizUseCase,
    @ProfessionalScrumDeveloper private val quizQuestionsRepository: QuizQuestionsRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) :
    ViewModel() {

    val isQuizOnGoing: StateFlow<Boolean> =
        isAnyQuizOngoingUseCase().stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun deleteAllQuiz() {
        viewModelScope.launch(dispatcher) {
            setQuizOnOffUseCase(isActive = false)
            deleteQuizUseCase(quizQuestionsRepository = quizQuestionsRepository)
        }
    }
}
