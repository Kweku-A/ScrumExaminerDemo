package com.kweku.armah.pspo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kweku.armah.core.domain.repository.QuestionsRepository
import com.kweku.armah.core.domain.usecase.InsertAllQuestionsIntoDatabaseUseCase
import com.kweku.armah.core.domain.usecase.LoadQuestionsFromFileToDatabase
import com.kweku.armah.pspo.domain.ProfessionalScrumProductOwner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PspoIntroViewModel @Inject constructor(
    private val loadQuestionsFromFileToDatabase: LoadQuestionsFromFileToDatabase,
    private val insertAllQuestionsIntoDatabaseUseCase: InsertAllQuestionsIntoDatabaseUseCase,
    @ProfessionalScrumProductOwner private val questionsRepository: QuestionsRepository,
) : ViewModel() {

    init {
        viewModelScope.launch {
            val questionsGroup =
                loadQuestionsFromFileToDatabase(com.kweku.armah.resources.R.raw.psm)
            insertAllQuestionsIntoDatabaseUseCase(
                questionsRepository = questionsRepository,
                questionsGroup = questionsGroup,
            )
        }
    }
}
