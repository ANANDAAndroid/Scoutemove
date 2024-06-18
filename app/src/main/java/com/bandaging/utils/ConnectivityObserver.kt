package com.bandaging.utils

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe():Flow<Status>
}