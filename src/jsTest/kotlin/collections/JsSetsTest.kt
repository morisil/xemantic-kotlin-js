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

class JsSetsTest {

    @Test
    fun `should create empty JsSet with jsSetOf`() {
        // when
        val set = jsSetOf<String>()

        // then
        assert(set.isEmpty())
        assert(set.size == 0)
    }

    @Test
    fun `should create JsSet with single value using jsSetOf`() {
        // when
        val set = jsSetOf("foo")

        // then
        assert(set.isNotEmpty())
        assert(set.size == 1)
        assert("foo" in set)
    }

    @Test
    fun `should create JsSet with multiple values using jsSetOf`() {
        // when
        val set = jsSetOf(1, 2, 3)

        // then
        assert(set.size == 3)
        assert(1 in set)
        assert(2 in set)
        assert(3 in set)
    }

    @Test
    fun `should deduplicate values in jsSetOf`() {
        // when
        val set = jsSetOf("a", "b", "a", "c", "b")

        // then
        assert(set.size == 3)
        assert("a" in set)
        assert("b" in set)
        assert("c" in set)
    }

    @Test
    fun `should report element not contained in set`() {
        // given
        val set = jsSetOf("foo", "bar")

        // then
        assert("baz" !in set)
    }

    @Test
    fun `should report empty JsSet as isEmpty`() {
        // when
        val set = jsSetOf<String>()

        // then
        assert(set.isEmpty())
    }

    @Test
    fun `should report non-empty JsSet as not isEmpty`() {
        // given
        val set = jsSetOf("foo")

        // then
        assert(!set.isEmpty())
    }

    @Test
    fun `should report empty JsSet as not isNotEmpty`() {
        // when
        val set = jsSetOf<String>()

        // then
        assert(!set.isNotEmpty())
    }

    @Test
    fun `should report non-empty JsSet as isNotEmpty`() {
        // given
        val set = jsSetOf("foo")

        // then
        assert(set.isNotEmpty())
    }

}
