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

package com.xemantic.kotlin.js.dom.aria

import com.xemantic.kotlin.js.dom.NodeBuilder
import com.xemantic.kotlin.js.dom.element.get
import com.xemantic.kotlin.js.dom.element.set
import org.w3c.dom.Element

public class AriaBuilder(
    @PublishedApi
    internal val element: Element
) {

    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun get(
        name: String
    ): String? = element["aria-$name"]

    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun set(
        name: String,
        value: String?
    ) {
        element["aria-$name"] = value
    }

}

public inline val Element.aria: AriaBuilder get() = AriaBuilder(this)

public inline val NodeBuilder.aria: AriaBuilder get() = AriaBuilder(
    element = root.unsafeCast<Element>()
)
