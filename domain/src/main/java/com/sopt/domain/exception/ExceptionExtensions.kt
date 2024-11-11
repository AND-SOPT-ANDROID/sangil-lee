package com.sopt.domain.exception

import kotlin.coroutines.cancellation.CancellationException

suspend fun <T, R> T.runCatchingByCode(
    vararg exceptions: Pair<Int, Throwable>,
    block: suspend T.() -> R
): Result<R> {
    return try {
        Result.success(block())
    } catch (e: NetworkError) {
        exceptions.find {
            e.errorCode == it.first
        }?.let { Result.failure(it.second) } ?: Result.failure(e)
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        Result.failure(e)
    }
}

suspend fun <T, R> T.runSuspendCatching(
    block: suspend T.() -> R
): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Throwable) {
        Result.failure(e)
    }
}