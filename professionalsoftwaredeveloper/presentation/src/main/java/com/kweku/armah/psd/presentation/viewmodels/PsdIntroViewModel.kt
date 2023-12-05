package com.kweku.armah.psd.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kweku.armah.core.domain.IODispatcher
import com.kweku.armah.core.domain.repository.QuestionsRepository
import com.kweku.armah.core.domain.usecase.InsertAllQuestionsIntoDatabaseUseCase
import com.kweku.armah.core.domain.usecase.LoadQuestionsFromFileToDatabase
import com.kweku.armah.psd.domain.ProfessionalScrumDeveloper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PsdIntroViewModel @Inject constructor(
    private val loadQuestionsFromFileToDatabase: LoadQuestionsFromFileToDatabase,
    private val insertAllQuestionsIntoDatabaseUseCase: InsertAllQuestionsIntoDatabaseUseCase,
    @ProfessionalScrumDeveloper private val questionsRepository: QuestionsRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) :
    ViewModel() {

    init {
        viewModelScope.launch(dispatcher) {
            val questionsGroup =
                loadQuestionsFromFileToDatabase(com.kweku.armah.resources.R.raw.psd)
            insertAllQuestionsIntoDatabaseUseCase(
                questionsRepository = questionsRepository,
                questionsGroup = questionsGroup,
            )
        }
    }
}
