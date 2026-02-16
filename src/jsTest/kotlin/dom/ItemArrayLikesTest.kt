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

import com.xemantic.kotlin.js.dom.html.div
import com.xemantic.kotlin.js.dom.html.li
import com.xemantic.kotlin.js.dom.html.ul
import com.xemantic.kotlin.test.assert
import com.xemantic.kotlin.test.have
import com.xemantic.kotlin.test.should
import kotlinx.browser.document
import kotlin.test.Test

class ItemArrayLikesTest {

    @Test
    fun `should iterate over all items in ItemArrayLike`() {
        // given
        val element = node {
            div {
                it["id"] = "test"
                it["class"] = "foot"
                it["data-value"] = "bar"
            }
        }

        // when
        val names = mutableListOf<String>()
        element.attributes.forEach { attr ->
            names.add(attr.name)
        }

        // then
        assert(names == listOf("id", "class", "data-value"))
    }

    @Test
    fun `should not invoke action for empty ItemArrayLike`() {
        // given
        val element = document.createElement("div")

        // when
        var count = 0
        element.attributes.forEach { count++ }

        // then
        assert(count == 0)
    }

    @Test
    fun `should iterate over child nodes`() {
        // given
        val parent = node {
            ul {
                li()
                li()
                li()
            }
        }

        // when
        val tagNames = mutableListOf<String>()
        parent.childNodes.forEach { node ->
            tagNames.add(node.nodeName)
        }

        // then
        tagNames should {
            have(size == 3)
            have(all { it == "LI" })
        }
    }

    @Test
    fun `should iterate over single item`() {
        // given
        val element = node {
            div {
                it["id"] = "only"
            }
        }

        // when
        val values = mutableListOf<String>()
        element.attributes.forEach { attr ->
            values.add(attr.value)
        }

        // then
        assert(values.size == 1)
        assert(values[0] == "only")
    }

}
