package com.example.android.dagger.di

import com.example.android.dagger.user.UserManager
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@EntryPoint
interface UserManagerEntryPoint {
	fun userManager(): UserManager
}