package com.kweku.armah.core.presentation.data

import androidx.compose.runtime.Immutable

@Immutable
data class FinalScoreUi(
    val finalScore: String = "",
    val isPassMark: Boolean = false,
    val remark: String = "Failed",
)