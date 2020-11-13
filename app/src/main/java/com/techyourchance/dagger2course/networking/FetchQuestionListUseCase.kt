package com.techyourchance.dagger2course.networking

import com.techyourchance.dagger2course.questions.Question
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchQuestionListUseCase(private val stackoverflowApi: StackoverflowApi) {

    sealed class Result {
        class Success(val questions: List<Question>): Result()
        object Failure : Result()
    }

    suspend fun lastActiveQuestions(pageSize: Int): Result {
        return withContext(Dispatchers.IO) {
            try {
                val response = stackoverflowApi.lastActiveQuestions(pageSize)
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!.questions)
                } else {
                    return@withContext Result.Failure
                }
            } catch (t : Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw t
                }
            }
        }
    }

}