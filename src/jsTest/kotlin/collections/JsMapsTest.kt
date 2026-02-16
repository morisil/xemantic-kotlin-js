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
import kotlin.js.collections.JsMap
import kotlin.test.Test

class JsMapsTest {

    @Test
    fun `should set and get value by string key`() {
        // given
        val map = JsMap<String, Int>()

        // when
        map["foo"] = 42

        // then
        assert(map.isNotEmpty())
        assert(map.size == 1)
        assert(map["foo"] == 42)
    }

    @Test
    fun `should overwrite existing value`() {
        // given
        val map = JsMap<String, String>()
        map["key"] = "old"

        // when
        map["key"] = "new"

        // then
        assert(map["key"] == "new")
    }

    @Test
    fun `should set and get multiple values`() {
        // given
        val map = JsMap<String, String>()

        // when
        map["a"] = "alpha"
        map["b"] = "beta"
        map["c"] = "gamma"

        // then
        assert(map.size == 3)
        assert(map["a"] == "alpha")
        assert(map["b"] == "beta")
        assert(map["c"] == "gamma")
    }

    @Test
    fun `should report empty JsMap as isEmpty`() {
        // when
        val map = JsMap<String, String>()

        // then
        assert(map.isEmpty())
        assert(map.size == 0)
    }

    @Test
    fun `should report non-empty JsMap as not isEmpty`() {
        // given
        val map = JsMap<String, Int>()
        map["foo"] = 1

        // then
        assert(!map.isEmpty())
    }

    @Test
    fun `should report empty JsMap as not isNotEmpty`() {
        // given
        val map = JsMap<String, String>()

        // then
        assert(!map.isNotEmpty())
    }

    @Test
    fun `should report non-empty JsMap as isNotEmpty`() {
        // given
        val map = JsMap<String, Int>()
        map["foo"] = 1

        // then
        assert(map.isNotEmpty())
    }

}
