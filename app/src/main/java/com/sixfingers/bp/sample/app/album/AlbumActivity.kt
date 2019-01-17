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

package com.sixfingers.bp.sample.app.album

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import com.sixfingers.bp.sample.app.R
import com.sixfingers.bp.sample.app.player.PlayerActivity
import kotlinx.android.synthetic.main.activity_album.*
import kotlinx.android.synthetic.main.app_bar_album.*

class AlbumActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)
        setSupportActionBar(toolbar)
        findViewById<ImageButton>(R.id.port).setOnClickListener {
            startActivity(createIntent(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT, Type.IMAGE))
        }
        findViewById<ImageButton>(R.id.land).setOnClickListener {
            startActivity(createIntent(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE, Type.IMAGE))
        }
        findViewById<ImageButton>(R.id.playerland).setOnClickListener {
            startActivity(createIntent(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE, Type.PLAYER))
        }
        findViewById<ImageButton>(R.id.playerport).setOnClickListener {
            startActivity(createIntent(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT, Type.PLAYER))
        }
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }


    private fun createIntent(orientation: Int, type: Type): Intent {
        val intent = Intent(this, PlayerActivity::class.java)
        when (type) {
            Type.IMAGE -> {
                intent.putExtra(PlayerActivity.BASE_EBOOK_KEY, PlayerActivity.BASE_EBOOK_IMAGE)
                val features: Array<String> = arrayOf(PlayerActivity.FEATURE_BACK_HOME,
                        PlayerActivity.FEATURE_PLAY_PAUSE,
                        PlayerActivity.FEATURE_THUMBNAIL)
                intent.putExtra(PlayerActivity.DECORATOR_KEYS, features)
            }
            Type.PLAYER -> {
                intent.putExtra(PlayerActivity.BASE_EBOOK_KEY, PlayerActivity.BASE_EBOOK_PLAYER)
            }
        }
        intent.putExtra(PlayerActivity.PLAYER_ORIENTATION, orientation)
        return intent
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    enum class Type {
        IMAGE, PLAYER
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.album, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        when (item.itemId) {
//            R.id.action_settings -> return true
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
