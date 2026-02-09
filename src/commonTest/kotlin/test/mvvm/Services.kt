/*
 * Copyright 2026 Kazimierz Pogoda / Xemantic
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xemantic.kotlin.js.test.mvvm

/**
 * Authenticator interface to be mocked in test.
 *
 * In real life the Authenticator implementation would use for example
 * Ktor HTTP client:
 *
 * ```
 * class DefaultAuthenticator(
 *     private val dispatcher: CoroutineDispatcher
 * ): Authenticator {
 *
 *     private val client = HttpClient()
 *
 *     override suspend fun authenticate(
 *         username: String,
 *         password: String
 *     ): Boolean = withContext(dispatcher) {
 *         client.post("...")
 *     }
 *
 * }
 * ```
 *
 * Where in JVM/native it would be `Dispatcher.IO`.
 */
interface Authenticator {
    suspend fun authenticate(
        username: String,
        password: String
    ): Boolean
}

interface Navigator {
    fun goTo(location: String)
}
