package com.kweku.armah.scrumexams.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kweku.armah.core.domain.usecase.GetActiveQuizRouteUseCase
import com.kweku.armah.core.domain.usecase.SetActiveQuizRouteUseCase
import com.kweku.armah.core.domain.usecase.SetQuizOnOffUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavHostViewModel @Inject constructor(
    private val setQuizOnOffUseCase: SetQuizOnOffUseCase,
    private val setActiveQuizRouteUseCase: SetActiveQuizRouteUseCase,
    getActiveQuizRouteUseCase: GetActiveQuizRouteUseCase,
) :
    ViewModel() {

    val activeQuizRoute = getActiveQuizRouteUseCase().stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = "",
    )

    fun setActiveQuizState(isActive: Boolean = false, route: String = "") {
        viewModelScope.launch {
            when (isActive) {
                true -> {
                    setActiveQuizRouteUseCase(activeQuizRoute = route)
                }

                false -> {
                    setActiveQuizRouteUseCase(activeQuizRoute = route)
                }
            }
            setQuizOnOffUseCase(isActive = isActive)
        }
    }
}
