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

import com.xemantic.kotlin.js.dom.html.*
import com.xemantic.kotlin.js.dom.node
import com.xemantic.kotlin.test.have
import com.xemantic.kotlin.test.sameAsHtml
import com.xemantic.kotlin.test.should
import com.xemantic.markanywhere.js.toSemanticEvents
import com.xemantic.markanywhere.render.render
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class AriaAttributesTest {

    // region role

    @Test
    fun `should set role attribute on element`() = runTest {
        // when
        val div = node.div {
            role = "alert"
            +"Error message"
        }

        // then
        div.toSemanticEvents().render() sameAsHtml """
            <div role="alert">
              Error message
            </div>
        """.trimIndent()
    }

    // endregion

    // region aria-label

    @Test
    fun `should set aria-label attribute on element`() = runTest {
        // when
        val button = node.button {
            aria.label = "Close dialog"
            +"X"
        }

        // then
        button.toSemanticEvents().render() sameAsHtml """
            <button aria-label="Close dialog">X</button>
        """.trimIndent()
    }

    // endregion

    // region aria-hidden

    @Test
    fun `should set aria-hidden attribute to true`() = runTest {
        // when
        val span = node.span {
            aria.hidden = true
            +"decorative"
        }

        // then
        span.toSemanticEvents().render() sameAsHtml """
            <span aria-hidden="true">decorative</span>
        """.trimIndent()
    }

    @Test
    fun `should set aria-hidden attribute to false`() = runTest {
        // when
        val span = node.span {
            aria.hidden = false
            +"visible"
        }

        // then
        span.toSemanticEvents().render() sameAsHtml """
            <span aria-hidden="false">visible</span>
        """.trimIndent()
    }

    // endregion

    // region aria-disabled

    @Test
    fun `should set aria-disabled attribute to true`() = runTest {
        // when
        val button = node.button {
            aria.disabled = true
            +"Save"
        }

        // then
        button.toSemanticEvents().render() sameAsHtml """
            <button aria-disabled="true">Save</button>
        """.trimIndent()
    }

    @Test
    fun `should set aria-disabled attribute to false`() = runTest {
        // when
        val button = node.button {
            aria.disabled = false
            +"Save"
        }

        // then
        button.toSemanticEvents().render() sameAsHtml """
            <button aria-disabled="false">Save</button>
        """.trimIndent()
    }

    // endregion

    // region aria-expanded

    @Test
    fun `should set aria-expanded attribute to true`() = runTest {
        // when
        val button = node.button {
            aria.expanded = true
            +"Menu"
        }

        // then
        button.toSemanticEvents().render() sameAsHtml """
            <button aria-expanded="true">Menu</button>
        """.trimIndent()
    }

    @Test
    fun `should set aria-expanded attribute to false`() = runTest {
        // when
        val button = node.button {
            aria.expanded = false
            +"Menu"
        }

        // then
        button.toSemanticEvents().render() sameAsHtml """
            <button aria-expanded="false">Menu</button>
        """.trimIndent()
    }

    // endregion

    // region aria-selected

    @Test
    fun `should set aria-selected attribute to true`() = runTest {
        // when
        val li = node.li {
            role = "tab"
            aria.selected = true
            +"Active Tab"
        }

        // then
        li.toSemanticEvents().render() sameAsHtml """
            <li role="tab" aria-selected="true">
              Active Tab
            </li>
        """.trimIndent()
    }

    @Test
    fun `should set aria-selected attribute to false`() = runTest {
        // when
        val li = node.li {
            role = "tab"
            aria.selected = false
            +"Inactive Tab"
        }

        // then
        li.toSemanticEvents().render() sameAsHtml """
            <li role="tab" aria-selected="false">
              Inactive Tab
            </li>
        """.trimIndent()
    }

    // endregion

    // region clearing attributes with null

    @Test
    fun `should remove aria-label when set to null`() = runTest {
        // given
        val button = node.button {
            aria.label = "Close"
        }

        // when
        button.aria.label = null

        // then
        button.toSemanticEvents().render() sameAsHtml """
            <button></button>
        """.trimIndent()
    }

    @Test
    fun `should remove aria-hidden when set to null`() = runTest {
        // given
        val span = node.span {
            aria.hidden = true
            +"content"
        }

        // when
        span.aria.hidden = null

        // then
        span should {
            have(aria.hidden == null)
        }
        span.toSemanticEvents().render() sameAsHtml """
            <span>content</span>
        """.trimIndent()
    }

    @Test
    fun `should remove arbitrary aria attribute when set to null`() = runTest {
        // given
        val div = node.div {
            aria["live"] = "polite"
            +"content"
        }

        // when
        div.aria["live"] = null

        // then
        div.toSemanticEvents().render() sameAsHtml """
            <div>
              content
            </div>
        """.trimIndent()
    }

    // endregion

    // region generic aria attributes

    @Test
    fun `should set arbitrary aria attributes via indexed access`() = runTest {
        // when
        val div = node.div {
            aria["live"] = "assertive"
            aria["atomic"] = "true"
            +"Dynamic content"
        }

        // then
        div.toSemanticEvents().render() sameAsHtml """
            <div aria-live="assertive" aria-atomic="true">
              Dynamic content
            </div>
        """.trimIndent()
    }

    // endregion

    // region combined usage

    @Test
    fun `should combine role and multiple aria attributes`() = runTest {
        // when
        val nav = node.nav {
            role = "tablist"
            aria.label = "Main navigation"
            button {
                role = "tab"
                aria.selected = true
                aria.label = "Home"
                +"Home"
            }
            button {
                role = "tab"
                aria.selected = false
                aria.disabled = true
                aria.label = "Settings"
                +"Settings"
            }
        }

        // then
        nav.toSemanticEvents().render() sameAsHtml """
            <nav role="tablist" aria-label="Main navigation">
              <button role="tab" aria-selected="true" aria-label="Home">Home</button><button role="tab" aria-selected="false" aria-disabled="true" aria-label="Settings">Settings</button>
            </nav>
        """.trimIndent()
    }

    // endregion

}
