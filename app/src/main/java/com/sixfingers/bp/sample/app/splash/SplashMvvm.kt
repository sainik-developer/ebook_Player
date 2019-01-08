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

class SplashMvvm : ViewModel() {

    fun copyAssetToStorage(appContext: Context, pubSub: PublishSubject<Int>): Observable<Any> {
        return Observable.just("book_data.zip").map {
            val inputStream = appContext.assets.open(it)
            Log.i("SplashMvvm", "the file path is : " + appContext.filesDir.toString())
            Utils.unzip(inputStream, appContext.getExternalFilesDir(null)!!.toString(), pubSub)
        }
    }

    fun requestPermissionIfRequired(thisActivity: Activity) {
        if (ContextCompat.checkSelfPermission(thisActivity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(thisActivity,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        Utils.MY_PERMISSIONS_REQUEST_READ_CONTACTS)
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            goToNextActivity(thisActivity)
        }
    }

    fun goToNextActivity(thisActivity: Activity) {
        thisActivity.startActivity(Intent(thisActivity, AlbumActivity::class.java))
        thisActivity.finish()
    }
}