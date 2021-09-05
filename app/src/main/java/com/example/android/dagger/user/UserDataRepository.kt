/*
 * Copyright (C) 2020 The Android Open Source Project
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

package com.example.android.dagger.user

import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

/**
 * UserDataRepository contains user-specific data such as username and unread notifications.
 */
@Singleton
class UserDataRepository @Inject constructor() {

    var userName: String? = null
        private set

    var unreadNotifications: Int = -1
        private set

    fun initData(userName: String) {
        this.userName = userName
        this.unreadNotifications = 0
    }

    fun refreshUnreadNotifications() {
        unreadNotifications = randomInt()
    }

    fun cleanUp() {
        this.userName = null
        this.unreadNotifications = -1
    }
    
    private fun randomInt(): Int = Random.nextInt(until = 100)
}