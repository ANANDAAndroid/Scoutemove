package com.clone.scoutemove.utils

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    fun observe():Flow<Status>
}