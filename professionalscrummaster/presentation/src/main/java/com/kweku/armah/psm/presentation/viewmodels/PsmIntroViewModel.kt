package com.kweku.armah.psm.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kweku.armah.core.domain.repository.QuestionsRepository
import com.kweku.armah.core.domain.usecase.InsertAllQuestionsIntoDatabaseUseCase
import com.kweku.armah.core.domain.usecase.LoadQuestionsFromFileToDatabase
import com.kweku.armah.psm.domain.ProfessionalScrumMaster
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PsmIntroViewModel @Inject constructor(
    private val loadQuestionsFromFileToDatabase: LoadQuestionsFromFileToDatabase,
    private val insertAllQuestionsIntoDatabaseUseCase: InsertAllQuestionsIntoDatabaseUseCase,
    @ProfessionalScrumMaster private val questionsRepository: QuestionsRepository,
) :
    ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val questionsGroup =
                loadQuestionsFromFileToDatabase(com.kweku.armah.resources.R.raw.psm)
            insertAllQuestionsIntoDatabaseUseCase(
                questionsRepository = questionsRepository,
                questionsGroup = questionsGroup,
            )
        }
    }
}
