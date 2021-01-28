package com.jo.assignmentdotjo.helpers

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.jo.assignmentdotjo.base.BaseViewModel

fun <T : BaseViewModel> Fragment.obtainViewModelForActivity(viewModelClass: Class<T>) =
        ViewModelProviders.of(this.activity!!, ViewModelFactory.getInstance(activity!!.application))
                .get(viewModelClass)