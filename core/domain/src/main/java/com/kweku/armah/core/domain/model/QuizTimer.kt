package com.kweku.armah.core.domain.model

data class QuizTimer(val hours: Int, val minutes: Int, val seconds: Int, val isEnded: Boolean = false)
