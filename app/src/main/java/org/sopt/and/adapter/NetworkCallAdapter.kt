package org.sopt.and.adapter

import kotlinx.serialization.json.JsonObject
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) return null

        val responseType = (returnType as? ParameterizedType)?.actualTypeArguments?.firstOrNull()
            ?: return null

        return object : CallAdapter<Any, Call<Any>> {
            override fun responseType(): Type = responseType

            override fun adapt(call: Call<Any>): Call<Any> {
                return ResultCall(call)
            }
        }
    }
}

private class ResultCall(
    private val delegate: Call<Any>
) : Call<Any> by delegate {

    override fun enqueue(callback: Callback<Any>) = delegate.enqueue(
        object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                val result = (response.body() as? JsonObject)?.get("result")
                callback.onResponse(this@ResultCall, Response.success(result))
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                callback.onFailure(call, t)
            }
        }
    )
}
