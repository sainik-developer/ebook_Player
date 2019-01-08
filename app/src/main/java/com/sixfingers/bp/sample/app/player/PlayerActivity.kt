/*
 * Copyright 2019 sixfingers.com. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sixfingers.bp.sample.app.player

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.sixfingers.bp.player.BackHomeControlPowerBookDecorator
import com.sixfingers.bp.player.PlayPauseControlPowerBookDecorator
import com.sixfingers.bp.player.PowerEbookDecorator
import com.sixfingers.bp.player.image.TestImagePowerEbook
import com.sixfingers.bp.player.landscape.LandScapeControlPowerBookDecorator
import com.sixfingers.bp.player.porttrait.PortraitControlPowerBookDecorator
import com.sixfingers.bp.sample.app.R


class PlayerActivity : AppCompatActivity() {

    companion object {
        val BASE_EBOOK_KEY = "BASE_EBOOK_KEY"
        val BASE_EBOOK_IMAGE = "BASE_EBOOK_IMAGE"
        val DECORATOR_KEYS = "DECORATOR_KEYS"
        val PLAYER_ORIENTATION = "PLAYER_ORIENTATION"
        val FEATURE_PLAY_PAUSE = "FEATURE_PLAY_PAUSE"
        val FEATURE_BACK_HOME = "FEATURE_BACK_HOME"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = createView()
        addContentView(view!!.frameLayout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
        val orientation = intent?.extras?.getInt(PLAYER_ORIENTATION)
        requestedOrientation = orientation!!.or(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    }

    /***
     *
     */
    fun createView(): PowerEbookDecorator? {
        val baseType = intent?.extras?.getString(BASE_EBOOK_KEY)
        when (baseType) {
            BASE_EBOOK_IMAGE -> return decorateeViews(TestImagePowerEbook(this, R.drawable.test))
            else -> return null
        }
    }

    /***
     *
     */
    fun decorateeViews(view: ViewGroup): PowerEbookDecorator? {
        val others = intent?.extras?.getStringArrayList(DECORATOR_KEYS)
        var resultView: ViewGroup = view
        when (requestedOrientation) {
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT -> resultView = PortraitControlPowerBookDecorator(resultView)
            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE -> resultView = LandScapeControlPowerBookDecorator(resultView)
        }
        for (item in others.orEmpty()) {
            when (item!!) {
                FEATURE_PLAY_PAUSE -> resultView = PlayPauseControlPowerBookDecorator(resultView)
                FEATURE_BACK_HOME -> resultView = BackHomeControlPowerBookDecorator(resultView)
            }
        }
        return when (resultView) { is PowerEbookDecorator -> resultView
            else -> null
        }
    }
}
