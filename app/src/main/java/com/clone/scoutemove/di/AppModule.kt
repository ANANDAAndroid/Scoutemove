package com.clone.scoutemove.di

import android.content.Context
import com.clone.scoutemove.utils.CryptoManager
import com.clone.scoutemove.utils.NetworkObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun networkObserver(@ApplicationContext applicationContext: Context): NetworkObserver =
        NetworkObserver(applicationContext)

}