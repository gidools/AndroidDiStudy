package com.techyourchance.dagger2course.screens.common

import androidx.fragment.app.Fragment
import com.techyourchance.dagger2course.di.PresentationCompositionRoot

abstract class BaseFragment: Fragment() {

    protected val compositionRoot by lazy {
        PresentationCompositionRoot((requireActivity() as BaseActivity).activityCompositionRoot)
    }
}