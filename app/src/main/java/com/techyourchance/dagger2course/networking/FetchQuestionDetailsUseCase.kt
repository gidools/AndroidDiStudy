package com.techyourchance.dagger2course.networking

import com.techyourchance.dagger2course.questions.QuestionWithBody
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchQuestionDetailsUseCase(private val stackoverflowApi: StackoverflowApi) {

    sealed class Result {
        class Success(val questionWithBody: QuestionWithBody) : Result()
        object Failure : Result()
    }

    suspend fun questionDetails(questionId: String): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = stackoverflowApi.questionDetails(questionId)
                if (response.isSuccessful && response.body() != null) {
                    val questionBody = response.body()!!.question
                    return@withContext Result.Success(questionBody)
                } else {
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw t
                }
            }

        }
    }
}