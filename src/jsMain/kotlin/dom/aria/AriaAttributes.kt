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

public inline var NodeBuilder.role: String?
    get() = root.unsafeCast<Element>()["role"]
    set(value) { root.unsafeCast<Element>()["role"] = value }

public inline var AriaBuilder.label: String?
    get() = get("label")
    set(value) { set("label", value) }

public inline var AriaBuilder.hidden: Boolean?
    get() = get("hidden")?.toBooleanStrict()
    set(value) { set("hidden", value?.toString()) }

public inline var AriaBuilder.disabled: Boolean?
    get() = get("disabled")?.toBooleanStrict()
    set(value) { set("disabled", value?.toString()) }

public inline var AriaBuilder.expanded: Boolean?
    get() = get("expanded")?.toBooleanStrict()
    set(value) { set("expanded", value?.toString()) }

public inline var AriaBuilder.selected: Boolean?
    get() = get("selected")?.toBooleanStrict()
    set(value) { set("selected", value?.toString()) }
