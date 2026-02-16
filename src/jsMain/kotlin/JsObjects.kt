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

package com.xemantic.kotlin.js

public external interface JsObject

@Suppress("NOTHING_TO_INLINE")
public inline fun JsObject(): JsObject = js("{}")

@Suppress("NOTHING_TO_INLINE")
public inline operator fun <T> JsObject.get(
    key: String
): T? = asDynamic()[key]

@Suppress("NOTHING_TO_INLINE")
public inline operator fun JsObject.set(key: String, value: dynamic) {
    asDynamic()[key] = value
}

@Suppress("NOTHING_TO_INLINE")
public inline fun JsObject.isEmpty(): Boolean =
    js("Object").keys(this).length == 0

@Suppress("NOTHING_TO_INLINE")
public inline fun JsObject.isNotEmpty(): Boolean = !isEmpty()

@Suppress("NOTHING_TO_INLINE")
public inline fun JsObject?.isNullOrEmpty(): Boolean = this == null || isEmpty()
