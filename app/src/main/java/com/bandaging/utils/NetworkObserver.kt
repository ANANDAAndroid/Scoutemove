package com.bandaging.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class NetworkObserver(context: Context) : ConnectivityObserver {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    internal fun isActuallyConnected() = connectivityManager.activeNetwork != null

    override fun observe(): Flow<Status> {
        return callbackFlow {
            val callback = object : NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(Status.AVAILABLE) }
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    launch { send(Status.UNAVAILABLE) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(Status.LOST) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    launch { send(Status.LOSING) }
                }

            }
            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
    }
}