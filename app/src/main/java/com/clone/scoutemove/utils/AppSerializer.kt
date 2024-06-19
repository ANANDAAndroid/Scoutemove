package com.clone.scoutemove.utils

import androidx.datastore.core.Serializer
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object AppSerializer : Serializer<AppSettings> {
    private val cryptoManager = CryptoManager()
    override val defaultValue: AppSettings
        get() = AppSettings()

    override suspend fun readFrom(input: InputStream): AppSettings {
        return try {
            val string = cryptoManager.decrypt(input)
            Json.decodeFromString(
                deserializer = AppSettings.serializer(),
                string = string.decodeToString()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: AppSettings, output: OutputStream) {
        println("datastore2 $t")
        cryptoManager.encrypt(
            byteArray = Json.encodeToString(
                serializer = AppSettings.serializer(),
                value = t
            ).encodeToByteArray(),
            outputStream = output
        )

    }
}