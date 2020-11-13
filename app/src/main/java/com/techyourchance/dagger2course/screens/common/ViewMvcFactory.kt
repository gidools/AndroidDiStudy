package com.techyourchance.dagger2course.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsVewMvc
import com.techyourchance.dagger2course.screens.questionslist.QuestionListViewMvc

class ViewMvcFactory(private val inflater: LayoutInflater) {

    fun newQuestionListViewMvc(parent: ViewGroup?): QuestionListViewMvc {
        return QuestionListViewMvc(inflater, parent)
    }

    fun newQuestionDetailViewMvc(parent: ViewGroup?): QuestionDetailsVewMvc {
        return QuestionDetailsVewMvc(inflater, parent)
    }
}