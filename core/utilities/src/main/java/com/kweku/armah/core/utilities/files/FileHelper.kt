package com.kweku.armah.core.utilities.files

import androidx.annotation.RawRes
import com.kweku.armah.core.domain.model.Question

fun interface FileHelper {
    suspend fun getQuestionsFromFile(@RawRes resourceId: Int): List<Question>
}
