package spring.android.app

import android.app.Application

object App {


    lateinit var context: Application


    private val activityCallbacks: MutableList<ActivityCallback> by lazy {
        arrayListOf()
    }


    private val fragmentCallbacks: MutableList<FragmentCallback> by lazy {
        arrayListOf()
    }


    internal fun fragmentCallbacks(): List<FragmentCallback> = fragmentCallbacks

    internal fun activityCallbacks(): List<ActivityCallback> = activityCallbacks


    fun registerFragmentCallback(callback: FragmentCallback) {
        fragmentCallbacks.add(callback)
    }


    fun registerActivityCallback(callback: ActivityCallback) {
        activityCallbacks.add(callback)
    }

}