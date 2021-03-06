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
package com.fernandocejas.sample.core.navigation

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.FragmentActivity
import android.view.View
import android.widget.ImageView
import com.fernandocejas.sample.core.extension.empty
import com.fernandocejas.sample.features.generateQR.GenerateQRActivity
import com.fernandocejas.sample.features.generateQR.GenerateQRFragment
import com.fernandocejas.sample.features.injured.InjuredActivity
import com.fernandocejas.sample.features.movies.MovieDetailsActivity
import com.fernandocejas.sample.features.movies.MovieView
import com.fernandocejas.sample.features.movies.MoviesActivity
import com.fernandocejas.sample.features.onboarding.Authenticator
import com.fernandocejas.sample.features.onboarding.IsAnyInjuredActivity
import com.fernandocejas.sample.features.onboarding.OnBoardingActivity
import com.fernandocejas.sample.features.pointinmap.MapActivity
import com.fernandocejas.sample.features.signup.SignUpActivity
import com.fernandocejas.sample.features.thridpartyinformation.ThirdPartyActivity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Navigator
@Inject constructor(private val authenticator: Authenticator) {

    private fun showOnBoarding(context: Context) = context.startActivity(OnBoardingActivity.callingIntent(context))

    fun showMain(context: Context) {
        when (authenticator.userLoggedIn()) {
            true -> showMovies(context)
            false -> showOnBoarding(context)
        }
    }

    fun showIsAnyInjured(context: Context) = context.startActivity(IsAnyInjuredActivity.callingIntent(context))

    fun showInjured(context: Context) = context.startActivity(InjuredActivity.callingIntent(context))

    fun dialPhoneNumber(context: Context, phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply { data = Uri.parse("tel:$phoneNumber") }
        context.startActivity(intent)
    }

    fun showMap(context: Context) = context.startActivity(MapActivity.callingIntent(context))

    fun showThirdPartyInformation(context: Context) = context.startActivity(ThirdPartyActivity.callingIntent(context))

    fun showSignUp(context: Context) = context.startActivity(SignUpActivity.callingIntent(context))

    fun generateQR(context: Context) = context.startActivity(GenerateQRActivity.callingIntent(context))

    private fun showMovies(context: Context) = context.startActivity(MoviesActivity.callingIntent(context))

    fun showMovieDetails(activity: FragmentActivity, movie: MovieView, navigationExtras: Extras) {
        val intent = MovieDetailsActivity.callingIntent(activity, movie)
        val sharedView = navigationExtras.transitionSharedElement as ImageView
        val activityOptions = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, sharedView, sharedView.transitionName)
        activity.startActivity(intent, activityOptions.toBundle())
    }

    private val VIDEO_URL_HTTP = "http://www.youtube.com/watch?v="
    private val VIDEO_URL_HTTPS = "https://www.youtube.com/watch?v="

    fun openVideo(context: Context, videoUrl: String) {
        try {
            context.startActivity(createYoutubeIntent(videoUrl))
        } catch (ex: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl)))
        }
    }

    private fun createYoutubeIntent(videoUrl: String): Intent {
        val videoId = when {
            videoUrl.startsWith(VIDEO_URL_HTTP) -> videoUrl.replace(VIDEO_URL_HTTP, String.empty())
            videoUrl.startsWith(VIDEO_URL_HTTPS) -> videoUrl.replace(VIDEO_URL_HTTPS, String.empty())
            else -> videoUrl
        }

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
        intent.putExtra("force_fullscreen", true)

        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.M)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        return intent
    }

    class Extras(val transitionSharedElement: View)
}


