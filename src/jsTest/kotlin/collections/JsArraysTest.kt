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

package com.xemantic.kotlin.js.collections

import com.xemantic.kotlin.test.assert
import kotlin.test.Test

class JsArraysTest {

    @Test
    fun `should create empty JsArray with jsArrayOf`() {
        // when
        val array = jsArrayOf<String>()

        // then
        assert(array.isEmpty())
        assert(array.length == 0)
    }

    @Test
    fun `should create JsArray with single value using jsArrayOf`() {
        // when
        val array = jsArrayOf("foo")

        // then
        assert(array.isNotEmpty())
        assert(array.length == 1)
        assert(array[0] == "foo")
    }

    @Test
    fun `should create JsArray with multiple values using jsArrayOf`() {
        // when
        val array = jsArrayOf(1, 2, 3)

        // then
        assert(array.length == 3)
        assert(array[0] == 1)
        assert(array[1] == 2)
        assert(array[2] == 3)
    }

    @Test
    fun `should push value to JsArray`() {
        // given
        val array = jsArrayOf<String>()

        // when
        array.push("foo")

        // then
        assert(array.length == 1)
        assert(array[0] == "foo")
    }

    @Test
    fun `should push multiple values to JsArray`() {
        // given
        val array = jsArrayOf<Int>()

        // when
        array.push(1)
        array.push(2)
        array.push(3)

        // then
        assert(array.length == 3)
        assert(array[0] == 1)
        assert(array[1] == 2)
        assert(array[2] == 3)
    }

    @Test
    fun `should add value with plusAssign operator`() {
        // given
        val array = jsArrayOf<String>()

        // when
        array += "bar"

        // then
        assert(array.length == 1)
        assert(array[0] == "bar")
    }

    @Test
    fun `should add multiple values with plusAssign operator`() {
        // given
        val array = jsArrayOf<String>()

        // when
        array += "a"
        array += "b"
        array += "c"

        // then
        assert(array.length == 3)
        assert(array[0] == "a")
        assert(array[1] == "b")
        assert(array[2] == "c")
    }

    @Test
    fun `should get JsArray element by index`() {
        // given
        val array = jsArrayOf("a", "b", "c")

        // then
        assert(array[0] == "a")
        assert(array[1] == "b")
        assert(array[2] == "c")
    }

    @Test
    fun `should set JsArray element by index`() {
        // given
        val array = jsArrayOf("a", "b", "c")

        // when
        array[1] = "x"

        // then
        assert(array.length == 3)
        assert(array[0] == "a")
        assert(array[1] == "x")
        assert(array[2] == "c")
    }

    @Test
    fun `should map JsArray elements`() {
        // given
        val array = jsArrayOf(1, 2, 3)

        // when
        val mapped = array.map { it * 2 }

        // then
        assert(mapped[0] == 2)
        assert(mapped[1] == 4)
        assert(mapped[2] == 6)
    }

    @Test
    fun `should map JsArray elements to different type`() {
        // given
        val array = jsArrayOf(10, 20)

        // when
        val mapped = array.map { "v$it" }

        // then
        assert(mapped[0] == "v10")
        assert(mapped[1] == "v20")
    }

    @Test
    fun `should map empty JsArray`() {
        // given
        val array = jsArrayOf<String>()

        // when
        val mapped = array.map { it.uppercase() }

        // then
        assert(mapped.isEmpty())
    }

    @Test
    fun `should join JsArray with default separator`() {
        // given
        val array = jsArrayOf("a", "b", "c")

        // then
        assert(array.join() == "a,b,c")
    }

    @Test
    fun `should join JsArray with custom separator`() {
        // given
        val array = jsArrayOf("a", "b", "c")

        // then
        assert(array.join(" - ") == "a - b - c")
    }

    @Test
    fun `should join empty JsArray`() {
        // given
        val array = jsArrayOf<String>()

        // then
        assert(array.join() == "")
    }

    @Test
    fun `should join single element JsArray`() {
        // given
        val array = jsArrayOf("only")

        // then
        assert(array.join(" | ") == "only")
    }

    @Test
    fun `should report empty JsArray as isEmpty`() {
        // when
        val array = jsArrayOf<String>()

        // then
        assert(array.isEmpty())
    }

    @Test
    fun `should report non-empty JsArray as not isEmpty`() {
        // given
        val array = jsArrayOf("foo")

        // then
        assert(!array.isEmpty())
    }

    @Test
    fun `should report empty JsArray as not isNotEmpty`() {
        // when
        val array = jsArrayOf<String>()

        // then
        assert(!array.isNotEmpty())
    }

    @Test
    fun `should report non-empty JsArray as isNotEmpty`() {
        // given
        val array = jsArrayOf("foo")

        // then
        assert(array.isNotEmpty())
    }

}
