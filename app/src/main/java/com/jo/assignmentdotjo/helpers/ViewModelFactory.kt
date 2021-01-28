package com.jo.assignmentdotjo.helpers

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.VisibleForTesting
import com.jo.assignmentdotjo.home.MainViewModel

/**
 * Created by Saif M Jaradat on 1/28/2021.
 */
class ViewModelFactory private constructor(private val application: Application) :
        ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
            with(modelClass) {
                when {

                    isAssignableFrom(MainViewModel::class.java) ->
                        MainViewModel(application)
                    else ->
                        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
                }
            } as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
                INSTANCE
                        ?: synchronized(ViewModelFactory::class.java) {
                            INSTANCE
                                    ?: ViewModelFactory(application)
                                            .also { INSTANCE = it }
                        }

        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
