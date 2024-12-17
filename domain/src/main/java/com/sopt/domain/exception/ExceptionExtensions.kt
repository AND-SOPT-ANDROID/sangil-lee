package com.sopt.domain.exception

import kotlin.coroutines.cancellation.CancellationException

suspend fun <R> runCatchingExceptCancellation(
    vararg exceptions: CommonError,
    block: suspend () -> R
): Result<R> {
    return try {
        Result.success(block())
    } catch (e: NetworkError) {
        exceptions.find {
            e.errorCode == it.code
        }?.let { Result.failure(it) } ?: Result.failure(e)
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        Result.failure(e)
    }
}

suspend fun <R> runCatchingExceptCancellation(
    block: suspend () -> R
): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        Result.failure(e)
    }
}