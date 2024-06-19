package com.clone.scoutemove.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

val annotatedString= buildAnnotatedString {
    append("You don't have account? ")
    withStyle(SpanStyle(color = Color.Blue)){
        pushStringAnnotation("tag", "Sign Up")
        append("Sign Up")
    }
}