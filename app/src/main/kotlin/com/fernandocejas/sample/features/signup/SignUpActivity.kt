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
package com.fernandocejas.sample.features.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.fernandocejas.sample.core.platform.BaseActivity

class SignUpActivity : BaseActivity() {

    var callingActivity = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        callingActivity = intent!!.getIntExtra(CALLING_ACTIVITY,0)
    }

    companion object {

        private val CALLING_ACTIVITY = "calling_activity"

        fun callingIntent(context: Context, callingActivity: Int): Intent{
            val intent = Intent(context, SignUpActivity::class.java)
            intent.putExtra(CALLING_ACTIVITY, callingActivity)
            return intent
        }

        fun callingIntent(context: Context) = Intent(context, SignUpActivity::class.java)

    }

    override fun fragment() = SignUpFragment()
}
