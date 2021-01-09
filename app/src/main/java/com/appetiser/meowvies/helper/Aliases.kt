package com.appetiser.meowvies.helper

/**
 * Use type aliases to provide an alternative name and easily reuse the callbacks using a single variable ( hassle-free!)
 */
typealias SuccessCallback = () -> Unit
typealias FailCallback = () -> Unit
typealias FailCallbackThrowable = (Throwable) -> Unit