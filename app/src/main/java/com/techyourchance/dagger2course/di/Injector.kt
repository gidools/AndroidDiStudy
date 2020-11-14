package com.techyourchance.dagger2course.di

import com.techyourchance.dagger2course.networking.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.networking.FetchQuestionListUseCase
import com.techyourchance.dagger2course.screens.common.ViewMvcFactory
import com.techyourchance.dagger2course.screens.common.dialogs.DialogNavigator
import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import java.lang.reflect.Field

class Injector(private val component: PresentationComponent) {

    fun inject(client: Any) {
        val allFields = getAllFields(client)
        for (field in allFields) {
            if (isAnnotatedForInjection(field)) {
                injectField(field, client)
            }
        }
    }

    private fun injectField(field: Field, client: Any) {
        val fieldAccessibility = field.isAccessible
        field.isAccessible = true
        field.set(client, getServiceForClass(field.type))
        field.isAccessible = fieldAccessibility
    }

    private fun getAllFields(client: Any): Array<out Field> {
        val clientClass = client::class.java
        return clientClass.declaredFields
    }

    private fun isAnnotatedForInjection(field: Field): Boolean {
        val fieldAnnotations = field.annotations
        for (fieldAnnotation in fieldAnnotations) {
            if (fieldAnnotation is Service) {
                return true
            }
        }

        return false
    }

    private fun getServiceForClass(type: Class<*>): Any {
        when (type) {
            DialogNavigator::class.java -> {
                return component.dialogNavigator()
            }
            FetchQuestionListUseCase::class.java -> {
                return component.fetchQuestionsListUseCase()
            }
            FetchQuestionDetailsUseCase::class.java -> {
                return component.fetchQuestionDetailsUseCase()
            }
            ViewMvcFactory::class.java -> {
                return component.viewMvcFactory()
            }
            else -> {
                throw Exception("unsupported service type: $type")
            }
        }
    }

}