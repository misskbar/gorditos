/**
 * Copyright (C) 2018 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.sample.features.onboarding

import android.os.Bundle
import android.view.View
import com.fernandocejas.sample.R
import com.fernandocejas.sample.core.dataBase.DataBaseHelper
import com.fernandocejas.sample.core.navigation.Navigator
import com.fernandocejas.sample.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_is_any_injured.*
import javax.inject.Inject

class IsAnyInjuredFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    var dbHandler: DataBaseHelper? = null

    override fun layoutId() = R.layout.fragment_is_any_injured


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbHandler = DataBaseHelper(context!!)

        possitiveButton.setOnClickListener(View.OnClickListener { v -> navigator.showInjured(activity!!)})

        negativeButton.setOnClickListener(View.OnClickListener {
            if(dbHandler!!.existsContacts()){
                navigator.showSendSMS(activity!!)
            }else{
                navigator.showMap(activity!!)
            }

        })
    }

}
