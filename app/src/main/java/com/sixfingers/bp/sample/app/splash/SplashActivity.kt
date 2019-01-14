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

import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import android.widget.Toast
import com.sixfingers.bp.sample.app.R
import com.sixfingers.bp.sample.app.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class SplashActivity : AppCompatActivity() {

    private lateinit var viewModel: SplashMvvm
    private lateinit var progressBar: ProgressBar
    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var progressSubject: PublishSubject<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        compositeDisposable = CompositeDisposable()
        progressSubject = prepareProgress()
        progressBar = findViewById(R.id.progressBar)
        viewModel = ViewModelProviders.of(this).get(SplashMvvm::class.java)
        compositeDisposable.add(viewModel.requestPermissionIfRequired(this, progressSubject).subscribe())

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            Utils.MY_PERMISSIONS_REQUEST_READ_CONTACTS -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    compositeDisposable.add(this.viewModel.copyAssetToStorage(this, progressSubject).subscribe())
                } else {
                    Toast.makeText(applicationContext, "Required permission not given to app", Toast.LENGTH_LONG).show()
                    finish()
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }
    }

    private fun prepareProgress(): PublishSubject<Int> {
        val pubSub: PublishSubject<Int> = PublishSubject.create()
        compositeDisposable.add(pubSub.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { t: Int ->
                    progressBar.progress = t
                    progressBar.invalidate()
                }
                .doOnError { t: Throwable ->
                    Toast.makeText(applicationContext, "There was problem in initializing app", Toast.LENGTH_LONG).show()
                }
                .doOnComplete {
                    progressBar.progress = 100
                    progressBar.invalidate()
                    viewModel.goToNextActivity(this)
                }
                .subscribe())
        return pubSub
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }
}
