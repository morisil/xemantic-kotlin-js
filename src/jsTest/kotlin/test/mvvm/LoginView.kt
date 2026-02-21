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

package com.xemantic.kotlin.js.test.mvvm

import com.xemantic.kotlin.js.dom.aria.aria
import com.xemantic.kotlin.js.dom.aria.label
import com.xemantic.kotlin.js.dom.aria.role
import com.xemantic.kotlin.js.dom.element.minusAssign
import com.xemantic.kotlin.js.dom.element.plusAssign
import com.xemantic.kotlin.js.dom.event.onClick
import com.xemantic.kotlin.js.dom.event.onInput
import com.xemantic.kotlin.js.dom.event.onSubmit
import com.xemantic.kotlin.js.dom.hidden
import com.xemantic.kotlin.js.dom.html.*
import com.xemantic.kotlin.js.dom.node
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun loginView(
    viewModel: LoginViewModel
) = node.form("app-login") {
    aria.label = "Login"
    onSubmit { it.preventDefault() }

    div("field label border round prefix") {
        icon("mail")
        input("large border", name = "username", type = "text") { input ->
            aria.label = "Username"
            onInput { viewModel.onUsernameChanged(input.value) }
        }
        label { +"Username" }
    }

    div("field label border round prefix") {
        icon("key")
        input("large border", name = "password", type = "password") { input ->
            aria.label = "Password"
            onInput { viewModel.onPasswordChanged(input.value) }
        }
        label { +"Password" }
    }

    nav("no-space") {
        button("large", type = "submit") { button ->
            aria.label = "Submit"
            onClick { viewModel.onSubmit() }
            viewModel.submitEnabled.onEach { enabled ->
                button.disabled = !enabled
            }.launchIn(viewModel.scope)
            +"Submit"
        }
    }

    progress("circle") {
        role = "status"
        aria.label = "Loading"
        hidden = true
        viewModel.loading.onEach { loading ->
            hidden = !loading
        }.launchIn(viewModel.scope)
    }

    div("snackbar") { snackbar ->
        role = "alert"
        icon("warning")
        val errorSpan = span()
        viewModel.error.onEach { error ->
            if (error != null) {
                snackbar += "active"
                errorSpan.textContent = error
            } else {
                snackbar -= "active"
            }
        }.launchIn(viewModel.scope)
    }

}
