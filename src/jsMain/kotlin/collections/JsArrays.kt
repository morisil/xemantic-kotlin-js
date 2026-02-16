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

package com.xemantic.kotlin.js.collections

import kotlin.js.collections.JsArray

@Suppress("NOTHING_TO_INLINE")
public inline fun <T> jsArrayOf(): JsArray<T> = js("[]").unsafeCast<JsArray<T>>()

public fun <T> jsArrayOf(
    vararg values: T
): JsArray<T> = jsArrayOf<T>().apply {
    for (value in values) {
        push(value)
    }
}

public inline val JsArray<*>.length: Int get() = asDynamic().length

public inline fun <T, R> JsArray<T>.map(
    crossinline block: (T) -> R
): JsArray<R> = asDynamic().map {
    value -> block(value)
}

@Suppress("NOTHING_TO_INLINE")
public inline fun JsArray<*>.join(
    separator: String = ","
): String = asDynamic().join(separator)

@Suppress("NOTHING_TO_INLINE")
public inline operator fun <T> JsArray<T>.get(
    index: Int
): T = asDynamic()[index]

@Suppress("NOTHING_TO_INLINE")
public inline operator fun <T> JsArray<T>.set(
    index: Int,
    value: T
) {
    asDynamic()[index] = value
}

@Suppress("NOTHING_TO_INLINE")
public inline operator fun <T> JsArray<T>.plusAssign(
    value: T
) {
    push(value)
}

@Suppress("NOTHING_TO_INLINE")
public inline fun <T> JsArray<T>.push(value: T) {
    asDynamic().push(value)
}

@Suppress("NOTHING_TO_INLINE")
public inline fun JsArray<*>.isEmpty(): Boolean = length == 0

@Suppress("NOTHING_TO_INLINE")
public inline fun JsArray<*>.isNotEmpty(): Boolean = !isEmpty()
