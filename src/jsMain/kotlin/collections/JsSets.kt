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

import kotlin.js.collections.JsSet

public fun <T> jsSetOf(
    vararg values: T
): JsSet<T> = JsSet<T>().also { set: dynamic ->
    values.forEach { set.add(it) }
}

public inline val JsSet<*>.size: Int get() = asDynamic().size

@Suppress("NOTHING_TO_INLINE")
public inline operator fun <T> JsSet<T>.contains(
    element: T
): Boolean = this.asDynamic().has(element)

@Suppress("NOTHING_TO_INLINE")
public inline fun JsSet<*>.isEmpty(): Boolean = size == 0

@Suppress("NOTHING_TO_INLINE")
public inline fun JsSet<*>.isNotEmpty(): Boolean = !isEmpty()
