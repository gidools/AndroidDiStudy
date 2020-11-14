package com.techyourchance.dagger2course.screens.common

import androidx.fragment.app.Fragment
import com.techyourchance.dagger2course.di.DaggerPresentationComponent
import com.techyourchance.dagger2course.di.Injector
import com.techyourchance.dagger2course.di.PresentationComponent
import com.techyourchance.dagger2course.di.PresentationModule

abstract class BaseFragment: Fragment() {

    private val compositionRoot by lazy {
        DaggerPresentationComponent.builder()
                .presentationModule(PresentationModule((requireActivity() as BaseActivity)
                        .activityCompositionRoot))
                .build()
    }

    protected val injector by lazy {
        Injector(compositionRoot)
    }
}