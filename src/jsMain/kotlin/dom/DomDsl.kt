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

package com.xemantic.kotlin.js.dom

import com.xemantic.kotlin.js.dom.element.set
import kotlinx.browser.document
import org.w3c.dom.*

@DslMarker
public annotation class DomDsl

public val node: NodeBuilder get() = NodeBuilder(
    root = document.createDocumentFragment()
)

public inline operator fun <T : Node> T.invoke(
    crossinline block: NodeBuilder.() -> Unit
): T {
    NodeBuilder(this).block()
    return this
}

@DomDsl
public class NodeBuilder(
    public val root: Node
) {

    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun String.invoke(
        klass: String? = null,
        id: String? = null,
        crossinline block: NodeBuilder.(Element) -> Unit = {}
    ): Element = element(name = this, klass, id, block)

    public inline fun <T : Element> element(
        name: String,
        klass: String? = null,
        id: String? = null,
        crossinline block: NodeBuilder.(T) -> Unit = {}
    ): T {
        val element = root.ownerDocument!!.createElement(name).unsafeCast<T>()
        if (klass != null) {
            element.className = klass
        }
        if (id != null) {
            element.id = id
        }
        NodeBuilder(root = element).block(element)
        root.appendChild(element)
        return element
    }

    public inline fun <T : Element> elementNs(
        namespace: String,
        name: String,
        klass: String? = null,
        id: String? = null,
        crossinline block: NodeBuilder.(T) -> Unit = {}
    ): T {

        val element = root.ownerDocument!!.createElementNS(
            namespace, name
        ).unsafeCast<T>()

        if (klass != null) {
            element["class"] = klass
        }
        if (id != null) {
            element.id = id
        }

        NodeBuilder(root = element).block(element)
        root.appendChild(element)
        return element
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun Node.unaryPlus() {
        root.appendChild(this)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun String.unaryPlus() {
        val lastChild = root.lastChild
        if (lastChild != null && lastChild is Text) {
            lastChild.appendData(this)
        } else {
            root.appendChild(
                root.ownerDocument!!.createTextNode(this)
            )
        }
    }

}

public inline val NodeBuilder.dataset: DataBuilder get() = DataBuilder(
    root.unsafeCast<HTMLElement>()
)

public class DataBuilder(
    @PublishedApi
    internal val element: HTMLElement
) {

    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun get(
        name: String
    ): String? = element.dataset[name]

    @Suppress("NOTHING_TO_INLINE")
    public inline operator fun set(
        name: String,
        value: String?
    ) {
        if (value != null) {
            element.dataset[name] = value
        } else {
            @Suppress("UNUSED_VARIABLE")
            val dataset = element.dataset
            js("delete dataset[name]")
        }
    }

}

public inline var NodeBuilder.hidden: Boolean
    get() = root.unsafeCast<HTMLElement>().hidden
    set(value) { root.unsafeCast<HTMLElement>().hidden = value }
