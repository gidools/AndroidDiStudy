package com.techyourchance.dagger2course.screens.questionslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techyourchance.dagger2course.networking.FetchQuestionListUseCase
import com.techyourchance.dagger2course.screens.common.BaseFragment
import com.techyourchance.dagger2course.screens.common.dialogs.DialogNavigator
import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import kotlinx.coroutines.*

class QuestionListFragment: BaseFragment(), QuestionListViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var isDataLoaded = false

    private lateinit var questListViewMvc: QuestionListViewMvc
    private lateinit var fetchQuestionListUseCase: FetchQuestionListUseCase
    private lateinit var dialogNavigator: DialogNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fetchQuestionListUseCase = compositionRoot.fetchQuestionListUseCase

        dialogNavigator = compositionRoot.dialogNavigator
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        questListViewMvc = compositionRoot.viewMvcFactory.newQuestionListViewMvc(container)
        return questListViewMvc.rootView
    }

    override fun onStart() {
        super.onStart()

        questListViewMvc.registerListener(this)

        if (!isDataLoaded) {
            fetchQuestions()
        }
    }

    override fun onStop() {
        super.onStop()
        coroutineScope.coroutineContext.cancelChildren()

        questListViewMvc.unregisterListener(this)
    }

    private fun fetchQuestions() {
        coroutineScope.launch {
            questListViewMvc.showProgressIndication()
            try {
                val result = fetchQuestionListUseCase.lastActiveQuestions(20)
                when(result) {
                    is FetchQuestionListUseCase.Result.Success -> {
                        questListViewMvc.bindData(result.questions)
                    }
                    is FetchQuestionListUseCase.Result.Failure -> onFetchFailed()
                }
            } finally {
                questListViewMvc.hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        dialogNavigator.showServerErrorDialog()
    }

    override fun onRefreshClicked() {
        fetchQuestions()
    }

    override fun onQuestionClicked(questionId: String) {
        QuestionDetailsActivity.start(requireActivity(), questionId)
    }
}