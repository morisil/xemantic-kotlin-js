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

import kotlinx.browser.document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.Text

@DslMarker
public annotation class DomDsl

/**
 * Builds a single DOM node. It returns the node returned by the [block].
 * Note: if multiple elements were created, only the last one will be returned.
 *
 * @param T the type of [Node].
 * @param block the node builder.
 * @return the node instance of type [T]
 */
@Suppress("UNCHECKED_CAST")
public inline fun <T : Node> node(
    crossinline block: NodeBuilder.() -> T
): T = NodeBuilder(
    root = document.createDocumentFragment()
).block()

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
