package com.techyourchance.dagger2course.screens.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

open class BaseViewMvc<LISTENER>(layoutInflater: LayoutInflater,
                                 parent: ViewGroup?,
                                 @LayoutRes layoutResId: Int) {

    val rootView: View = layoutInflater.inflate(layoutResId, parent, false)

    protected val listeners = HashSet<LISTENER>()

    fun <T : View>findViewById(resId: Int): T {
        return rootView.findViewById(resId)
    }

    fun registerListener(lister: LISTENER) {
        listeners.add(lister)
    }

    fun unregisterListener(listener: LISTENER) {
        listeners.remove(listener)
    }

}