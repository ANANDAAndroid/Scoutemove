package com.clone.scoutemove.utils

import kotlinx.serialization.Serializable


@Serializable
data class AppSettings(val name: String? = null, val age: Int? = null)
