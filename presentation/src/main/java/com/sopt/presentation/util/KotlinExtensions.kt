package com.sopt.presentation.util

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer

@OptIn(ExperimentalSerializationApi::class)
fun <T> KSerializer<T>.getSerialName(): String {
    return descriptor.serialName
}