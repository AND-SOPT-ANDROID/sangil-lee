package org.sopt.and.adapter

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.serializer
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class ResultConverter<T>(
    private val serializer: KSerializer<T>,
    private val json: Json
) : Converter<ResponseBody, T> {
    override fun convert(responseBody: ResponseBody): T? {
        return responseBody.use {
            val jsonObject = json.parseToJsonElement(responseBody.string()).jsonObject
            val result = jsonObject["result"] ?: return null
            json.decodeFromJsonElement(serializer, result)
        }
    }
}

class ResultConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val json = Json { ignoreUnknownKeys = true }
        val resultSerializer = json.serializersModule.serializer(type)
        return ResultConverter(resultSerializer, json)
    }
}