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

package com.sixfingers.bp.sample.app.splash

import android.Manifest
import android.app.Activity
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.sixfingers.bp.sample.app.album.AlbumActivity
import com.sixfingers.bp.sample.app.utils.Utils
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SplashMvvm : ViewModel() {

    fun copyAssetToStorage(appContext: Context, pubSub: PublishSubject<Int>): Observable<Any> {
        return Observable.just("book_data.zip").map {
            val inputStream = appContext.assets.open(it)
            Log.i("SplashMvvm", "the file path is : " + appContext.filesDir.toString())
            Utils.unzip(inputStream, appContext.getExternalFilesDir(null)!!.toString(), pubSub)

        }
    }

    fun requestPermissionIfRequired(thisActivity: Activity, progressSub: PublishSubject<Int>): Observable<Any> {
        return Observable.just(thisActivity)
                .map { progressSub.onNext(10) }
                .delay(700, TimeUnit.MILLISECONDS)
                .map {
                    if (ContextCompat.checkSelfPermission(thisActivity.applicationContext,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        // No explanation needed, we can request the permission.
                        ActivityCompat.requestPermissions(thisActivity,
                                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                                Utils.MY_PERMISSIONS_REQUEST_READ_CONTACTS)
                    } else {
                        // Permission has already been granted
                        progressSub.onNext(100)
                        goToNextActivity(thisActivity)
                    }
                }
    }

    fun goToNextActivity(thisActivity: Activity) {
        thisActivity.startActivity(Intent(thisActivity, AlbumActivity::class.java))
        thisActivity.finish()
    }
}