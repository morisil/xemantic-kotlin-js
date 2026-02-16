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

package com.xemantic.kotlin.js

import com.xemantic.kotlin.test.assert
import kotlin.test.Test

class JsObjectsTest {

    @Test
    fun `should create empty JsObject`() {
        // when
        val obj = JsObject()

        // then
        assert(obj.isEmpty())
    }

    @Test
    fun `should set and get value by string key`() {
        // given
        val obj = JsObject()

        // when
        obj["foo"] = 42

        // then
        val value: Int = obj["foo"]!!
        assert(value == 42)
    }

    @Test
    fun `should overwrite existing value`() {
        // given
        val obj = JsObject()
        obj["key"] = "old"

        // when
        obj["key"] = "new"

        // then
        assert(obj["key"] as? String == "new")
    }

    @Test
    fun `should set and get multiple values`() {
        // given
        val obj = JsObject()

        // when
        obj["a"] = "alpha"
        obj["b"] = 2
        obj["c"] = true

        // then
        val a: String = obj["a"]!!
        assert(a == "alpha")
        assert(obj["b"] as? Int == 2)
        assert(obj["c"] as? Boolean == true)
    }

    @Test
    fun `should report empty JsObject as isEmpty`() {
        // when
        val obj = JsObject()

        // then
        assert(obj.isEmpty())
    }

    @Test
    fun `should report non-empty JsObject as not isEmpty`() {
        // given
        val obj = JsObject()
        obj["foo"] = 1

        // then
        assert(!obj.isEmpty())
    }

    @Test
    fun `should report empty JsObject as not isNotEmpty`() {
        // when
        val obj = JsObject()

        // then
        assert(!obj.isNotEmpty())
    }

    @Test
    fun `should report non-empty JsObject as isNotEmpty`() {
        // given
        val obj = JsObject()
        obj["foo"] = 1

        // then
        assert(obj.isNotEmpty())
    }

    @Test
    fun `should report null as isNullOrEmpty`() {
        // given
        val obj: JsObject? = null

        // then
        assert(obj.isNullOrEmpty())
    }

    @Test
    fun `should report empty JsObject as isNullOrEmpty`() {
        // given
        @Suppress("RedundantNullableReturnType")
        val obj: JsObject? = JsObject()

        // then
        assert(obj.isNullOrEmpty())
    }

    @Test
    fun `should report non-empty JsObject as not isNullOrEmpty`() {
        // given
        @Suppress("RedundantNullableReturnType")
        val obj: JsObject? = JsObject()
        obj!!["foo"] = 1

        // then
        assert(!obj.isNullOrEmpty())
    }

}
