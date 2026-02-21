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

package com.xemantic.kotlin.js.dom.event

import com.xemantic.kotlin.js.dom.html.button
import com.xemantic.kotlin.js.dom.html.div
import com.xemantic.kotlin.js.dom.html.form
import com.xemantic.kotlin.js.dom.html.input
import com.xemantic.kotlin.js.dom.node
import com.xemantic.kotlin.test.assert
import org.w3c.dom.events.Event
import org.w3c.dom.events.MouseEvent
import kotlin.test.Test

class EventsTest {

    @Test
    fun `should handle onClick event`() {
        // given
        var clicked = false
        val button = node.button {
            onClick { clicked = true }
            +"Click me"
        }

        // when
        button.click()

        // then
        assert(clicked)
    }

    @Test
    fun `should receive MouseEvent in onClick handler`() {
        // given
        var receivedEvent: MouseEvent? = null
        val button = node.button {
            onClick { receivedEvent = it }
            +"Click me"
        }

        // when
        button.click()

        // then
        assert(receivedEvent!!.type == "click")
    }

    @Test
    fun `should handle onSubmit event`() {
        // given
        var submitted = false
        val form = node.form {
            onSubmit {
                it.preventDefault()
                submitted = true
            }
        }

        // when
        form.dispatchEvent(Event("submit"))

        // then
        assert(submitted)
    }

    @Test
    fun `should handle onInput event`() {
        // given
        var inputReceived = false
        val input = node.input(type = "text") {
            onInput { inputReceived = true }
        }

        // when
        input.dispatchEvent(Event("input"))

        // then
        assert(inputReceived)
    }

    @Test
    fun `should handle onInput event on parent element`() {
        // given
        var inputValue = ""
        val div = node.div {
            input(type = "text", name = "test") { input ->
                onInput { inputValue = input.value }
            }
        }

        // when
        val input = div.querySelector("input")!!
        js("input.value = 'hello'")
        input.dispatchEvent(Event("input"))

        // then
        assert(inputValue == "hello")
    }

}
