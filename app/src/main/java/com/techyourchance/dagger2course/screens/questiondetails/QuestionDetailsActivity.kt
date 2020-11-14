package com.techyourchance.dagger2course.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.techyourchance.dagger2course.di.Service
import com.techyourchance.dagger2course.networking.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.screens.common.BaseActivity
import com.techyourchance.dagger2course.screens.common.ViewMvcFactory
import com.techyourchance.dagger2course.screens.common.dialogs.DialogNavigator
import kotlinx.coroutines.*
import javax.inject.Inject

class QuestionDetailsActivity : BaseActivity(), QuestionDetailsVewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    @Inject
    lateinit var questionDetailsUseCase: FetchQuestionDetailsUseCase

    @Inject
    lateinit var dialogNavigator: DialogNavigator
    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    private lateinit var questionDetailsViewMvc: QuestionDetailsVewMvc
    private lateinit var questionId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injector.inject(this)

        questionDetailsViewMvc = viewMvcFactory.newQuestionDetailViewMvc(null)

        setContentView(questionDetailsViewMvc.rootView)

        // retrieve question ID passed from outside
        questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!
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
                when (result) {
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