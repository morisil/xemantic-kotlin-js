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

package com.xemantic.kotlin.js.dom.element

import org.w3c.dom.Element

@Suppress("NOTHING_TO_INLINE")
public inline operator fun Element.get(
    name: String,
): String? = getAttribute(name)

@Suppress("NOTHING_TO_INLINE")
public inline operator fun Element.set(
    name: String,
    value: String?
) {
    if (value != null) setAttribute(name, value)
    else removeAttribute(name)
}

@Suppress("NOTHING_TO_INLINE")
public inline operator fun Element.plusAssign(klass: String) {
    classList.add(klass)
}

@Suppress("NOTHING_TO_INLINE")
public inline operator fun Element.minusAssign(klass: String) {
    classList.remove(klass)
}
