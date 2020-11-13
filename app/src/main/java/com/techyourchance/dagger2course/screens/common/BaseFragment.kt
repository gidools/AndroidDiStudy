package com.techyourchance.dagger2course.screens.common

import androidx.fragment.app.Fragment
import com.techyourchance.dagger2course.di.Injector
import com.techyourchance.dagger2course.di.PresentationCompositionRoot

abstract class BaseFragment: Fragment() {

    private val compositionRoot by lazy {
        PresentationCompositionRoot((requireActivity() as BaseActivity).activityCompositionRoot)
    }

    protected val injector by lazy {
        Injector(compositionRoot)
    }
}