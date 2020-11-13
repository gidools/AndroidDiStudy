package com.techyourchance.dagger2course.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.techyourchance.dagger2course.networking.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.screens.common.BaseActivity
import com.techyourchance.dagger2course.screens.common.dialogs.DialogNavigator
import kotlinx.coroutines.*

class QuestionDetailsActivity : BaseActivity(), QuestionDetailsVewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    private lateinit var questionId: String

    private lateinit var questionDetailsViewMvc: QuestionDetailsVewMvc

    private lateinit var questionDetailsUseCase: FetchQuestionDetailsUseCase

    private lateinit var dialogNavigator: DialogNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        questionDetailsViewMvc = activityCompositionRoot.viewMvcFactory.newQuestionDetailViewMvc(null)

        questionDetailsViewMvc = QuestionDetailsVewMvc(LayoutInflater.from(this), null)

        setContentView(questionDetailsViewMvc.rootView)

        // retrieve question ID passed from outside
        questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!

        questionDetailsUseCase = compositionRoot.fetchQuestionDetailsUseCase

        dialogNavigator = compositionRoot.dialogNavigator
    }

    override fun onStart() {
        super.onStart()

        questionDetailsViewMvc.registerListener(this)
        fetchQuestionDetails()
    }

    override fun onStop() {
        super.onStop()

        questionDetailsViewMvc.unregisterListener(this)
        coroutineScope.coroutineContext.cancelChildren()
    }

    private fun fetchQuestionDetails() {
        coroutineScope.launch {
            questionDetailsViewMvc.showProgressIndication()
            try {
                val result = questionDetailsUseCase.questionDetails(questionId)
                when(result) {
                    is FetchQuestionDetailsUseCase.Result.Success -> {
                        questionDetailsViewMvc.bindQuestion(result.questionWithBody.body)
                    }
                    is FetchQuestionDetailsUseCase.Result.Failure -> {
                        onFetchFailed()
                    }
                }
            } finally {
                questionDetailsViewMvc.hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        dialogNavigator.showServerErrorDialog()
    }

    override fun onBackClicked() {
        onBackPressed()
    }

    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

}