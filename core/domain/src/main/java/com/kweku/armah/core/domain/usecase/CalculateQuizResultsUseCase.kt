package com.kweku.armah.core.domain.usecase

import com.kweku.armah.core.domain.PASS_SCORE
import com.kweku.armah.core.domain.model.FinalScore
import com.kweku.armah.core.domain.model.Question
import javax.inject.Inject

class CalculateQuizResultsUseCase @Inject constructor() {

    operator fun invoke(quizList: List<Question>): FinalScore {
        val correctAnswers = quizList.filter {
            it.selectedAnswers.isNotEmpty() && it.correctAnswers.containsAll(it.selectedAnswers)
        }

        val score = ((correctAnswers.size.toDouble() / quizList.size.toDouble()) * 100).toInt()
        val isPass = score >= PASS_SCORE
        return FinalScore(
            finalScore = "$score%",
            isPassScore = isPass,
            remark = if (isPass) "Passed" else "Failed",
        )
    }
}
