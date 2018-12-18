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
package com.fernandocejas.sample.features.injured

import android.os.Bundle
import android.view.View
import com.fernandocejas.sample.R
import com.fernandocejas.sample.core.navigation.Navigator
import com.fernandocejas.sample.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_injured.*
import javax.inject.Inject

class InjuredFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    override fun layoutId() = R.layout.fragment_injured


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ambulanceLogo.setOnClickListener(View.OnClickListener { v ->  navigator.dialPhoneNumber(activity!!,getString(R.string.ambulance_phonenumber))})
        policeLogo.setOnClickListener(View.OnClickListener { v ->  navigator.dialPhoneNumber(activity!!,getString(R.string.police_phonenumber))})
        firemanLogo.setOnClickListener(View.OnClickListener { v ->  navigator.dialPhoneNumber(activity!!,getString(R.string.firedepartment_phonenumber))})

    }

}
