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

import com.xemantic.kotlin.js.dom.aria.aria
import com.xemantic.kotlin.js.dom.aria.label
import com.xemantic.kotlin.js.dom.aria.role
import com.xemantic.kotlin.js.dom.html.*
import com.xemantic.kotlin.test.assert
import com.xemantic.kotlin.test.coroutines.should
import com.xemantic.kotlin.test.have
import com.xemantic.kotlin.test.sameAsHtml
import com.xemantic.markanywhere.js.toSemanticEvents
import com.xemantic.markanywhere.render.render
import kotlinx.browser.document
import kotlinx.coroutines.test.runTest
import org.w3c.dom.Element
import org.w3c.dom.Text
import org.w3c.dom.get
import kotlin.test.Test

class DomDslTest {

    @Test
    fun `should create HTML element`() = runTest {
        // when
        val html = node.html(lang = "en") {
            body {
                div("foo") {
                    button("large") {
                        aria.label = "Hello World"
                        dataset["myTest"] = "hello-button"
                        +"Hello World"
                    }
                    a(href = "https://example.com")
                    "my:component"("bar") {
                        it.id = "component-1"
                    }
                }
            }
        }

        // then
        html.toSemanticEvents().render() sameAsHtml """
            <html lang="en">
              <body>
                <div class="foo">
                  <button class="large" aria-label="Hello World" data-my-test="hello-button">Hello World</button><a href="https://example.com"></a>
                  <my:component class="bar" id="component-1">
                  </my:component>
                </div>
              </body>
            </html>
        """.trimIndent()
    }

    @Test
    fun `should create login form`() = runTest {
        // when
        val form = node.form("app-login") {
            aria.label = "Login"

            div("field label border round prefix") {
                icon("mail")
                input("large border", name = "username", type = "text") {
                    aria.label = "Username"
                }
                label { +"Username" }
            }

            div("field label border round prefix") {
                icon("key")
                input("large border", name = "password", type = "password") {
                    aria.label = "Password"
                }
                label { +"Password" }
            }

            nav("no-space") {
                button("large", type = "submit") {
                    aria.label = "Submit"
                    +"Submit"
                }
            }

            progress("circle") {
                role = "status"
                aria.label = "Loading"
                hidden = true
            }

            div("snackbar") {
                role = "alert"
                icon("warning")
            }

        }

        // then
        form.toSemanticEvents().render() sameAsHtml """
            <form class="app-login" aria-label="Login">
              <div class="field label border round prefix">
                <i aria-hidden="true">mail</i><input class="large border" name="username" type="text" aria-label="Username"/><label>Username</label>
              </div>
              <div class="field label border round prefix">
                <i aria-hidden="true">key</i><input class="large border" name="password" type="password" aria-label="Password"/><label>Password</label>
              </div>
              <nav class="no-space">
                <button class="large" type="submit" aria-label="Submit">Submit</button>
              </nav>
              <progress class="circle" role="status" aria-label="Loading" hidden=""></progress>
              <div class="snackbar" role="alert">
                <i aria-hidden="true">warning</i>
              </div>
            </form>
        """.trimIndent()
    }

    @Test
    fun `should append to existing HTML element`() = runTest {
        // given
        val body = node.body()

        // when
        body {
            div("foo") {
                +"Hello World"
            }
        }

        // then
        (body.lastChild as Element).toSemanticEvents().render() sameAsHtml """
            <div class="foo">
              Hello World
            </div>
        """.trimIndent()
    }

    @Test
    fun `should append to newly created HTML element`() = runTest {
        // given
        val element = document.createElement("article")

        // when
        element {
            div("foo") {
                +"Hello World"
            }
        }

        // then
        element.toSemanticEvents().render() sameAsHtml """
            <article>
              <div class="foo">
                Hello World
              </div>
            </article>
        """.trimIndent()
    }

    @Test
    fun `should set and get data attributes`() = runTest {
        // when
        val div = node.div {
            dataset["foo"] = "bar"
            dataset["otherAttribute"] = "value2"
        }

        // then
        div.toSemanticEvents().render() sameAsHtml """
          <div data-foo="bar" data-other-attribute="value2">
          </div>
        """.trimIndent()
    }

    @Test
    fun `should return null for non-existent data attribute`() = runTest {
        node.div {
            assert(dataset["nonexistent"] == null)
        }
    }

    @Test
    fun `should clear data attribute when set to null`() = runTest {
        val element = node.div {
            dataset["test"] = "hello"
            dataset["test"] = null
        }
        assert(element.dataset["test"] == null)
    }

    @Test
    fun `should clear camelCase data attribute when set to null`() = runTest {
        val element = node.div {
            dataset["testValue"] = "hello"
            dataset["testValue"] = null
        }
        assert(element.dataset["testValue"] == null)
    }

    @Test
    fun `should set and get hidden property`() = runTest {
        val element = node.div {
            hidden = true
        }
        assert(element.hidden)

        element {
            hidden = false
        }
        assert(!element.hidden)
    }

    @Test
    fun `should create a single Text node when multiple strings are added`() = runTest {
        // when
        val element = node.div {
            +"foo"
            +"bar"
            +"baz"
        }

        // then
        element should {
            have(childNodes.length == 1)
            have(firstChild is Text)
            have(element.textContent == "foobarbaz")
        }
    }

}
