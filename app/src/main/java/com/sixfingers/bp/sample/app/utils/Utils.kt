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

package com.sixfingers.bp.sample.app.utils

import io.reactivex.subjects.PublishSubject
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

/****
 *
 */
object Utils {

    val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1

    fun unzip(inputStream: InputStream, location: String,publishSubject: PublishSubject<Int>) {
        val zin = ZipInputStream(inputStream)
        var ze: ZipEntry? = null
        var progress: Int = 0
        do {
            ze = zin.nextEntry
            when (ze?.isDirectory) {
                true -> tryCreateDir(location + "/" + ze.name)
                false -> {
                    progress += 10
                    publishSubject.onNext(progress)
                    copyZipEntry(zin, location, ze)
                }
            }
        } while (ze != null)
        publishSubject.onComplete()
        zin.close()
    }

    private fun copyZipEntry(zipInputStream: ZipInputStream, location: String, ze: ZipEntry) {
        copyInoutStreamToPath(zipInputStream, location + File.separator + ze.name)
        zipInputStream.closeEntry()
    }

    private fun copyInoutStreamToPath(inputStream: InputStream, path: String) {
        val fout = FileOutputStream(path)
        val buffer = ByteArray(16192)
        var length = inputStream.read(buffer)
        while (length != -1) {
            fout.write(buffer, 0, length)
            length = inputStream.read(buffer)
        }
        fout.close()
    }

    private fun tryCreateDir(dir: String) {
        val f = File(dir)
        if (!f.isDirectory) {
            f.mkdirs()
        }
    }
}