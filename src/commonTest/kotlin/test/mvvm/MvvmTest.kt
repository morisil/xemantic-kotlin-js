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

import com.xemantic.kotlin.test.assert
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verify.VerifyMode
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.Test

/**
 * Tests for the [LoginViewModel], demonstrating the
 * [Model–View–ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) pattern.
 *
 * These tests live in `commonTest`, meaning they have no dependency on the browser DOM
 * and can run on any Kotlin target (JVM, Native, JS, WASM). This is the key advantage
 * of MVVM — all business logic and state management is verified here, independently of
 * the view layer, resulting in fast and reliable tests.
 *
 * Service dependencies ([Authenticator], [Navigator]) are mocked with
 * [Mokkery](https://mokkery.dev/), and coroutines are driven by [UnconfinedTestDispatcher]
 * so that `launch` blocks execute eagerly within each test, without needing a real event loop.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MvvmTest {

    private lateinit var viewModel: LoginViewModel

    @AfterTest
    fun tearDown() {
        viewModel.onCleared()
    }

    @Test
    fun `should have initial state with submit disabled and no error`() = runTest {
        // given
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val authenticator = mock<Authenticator>()
        val navigator = mock<Navigator>(MockMode.autoUnit)

        // when
        viewModel = LoginViewModel(dispatcher, authenticator, navigator)

        // then
        assert(!viewModel.submitEnabled.value)
        assert(!viewModel.loading.value)
        assert(viewModel.error.value == null)
    }

    @Test
    fun `should log in on successful authentication`() = runTest {
        // given
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val authenticator = mock<Authenticator> {
            everySuspend { authenticate("foo", "bar") } returns true
        }
        val navigator = mock<Navigator>(MockMode.autoUnit)
        viewModel = LoginViewModel(dispatcher, authenticator, navigator)

        // when
        viewModel.onUsernameChanged("foo")

        // then
        assert(!viewModel.submitEnabled.value)

        // when
        viewModel.onPasswordChanged("bar")

        // then
        assert(viewModel.submitEnabled.value)

        // when
        viewModel.onSubmit()

        // then
        assert(!viewModel.submitEnabled.value)
        assert(!viewModel.loading.value)
        verifySuspend(VerifyMode.exhaustiveOrder) {
            authenticator.authenticate("foo", "bar")
            navigator.goTo("Home")
        }
    }

    @Test
    fun `should re-enable submit on failed authentication`() = runTest {
        // given
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val authenticator = mock<Authenticator> {
            everySuspend { authenticate("foo", "bar") } returns false
        }
        val navigator = mock<Navigator>(MockMode.autoUnit)
        viewModel = LoginViewModel(dispatcher, authenticator, navigator)
        viewModel.onUsernameChanged("foo")
        viewModel.onPasswordChanged("bar")

        // when
        viewModel.onSubmit()

        // then
        assert(viewModel.submitEnabled.value)
        assert(!viewModel.loading.value)
        assert(viewModel.error.value == "Invalid credentials")
        verifySuspend(VerifyMode.exhaustiveOrder) {
            authenticator.authenticate("foo", "bar")
        }
    }

    @Test
    fun `should disable submit when field is cleared`() = runTest {
        // given
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val authenticator = mock<Authenticator>()
        val navigator = mock<Navigator>(MockMode.autoUnit)
        viewModel = LoginViewModel(dispatcher, authenticator, navigator)

        // when
        viewModel.onUsernameChanged("foo")
        viewModel.onPasswordChanged("bar")

        // then
        assert(viewModel.submitEnabled.value)

        // when
        viewModel.onUsernameChanged("")

        // then
        assert(!viewModel.submitEnabled.value)
    }

    @Test
    fun `should show error and re-enable submit on authenticator exception`() = runTest {
        // given
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val authenticator = mock<Authenticator> {
            everySuspend { authenticate("foo", "bar") } throws RuntimeException("Network error")
        }
        val navigator = mock<Navigator>(MockMode.autoUnit)
        viewModel = LoginViewModel(dispatcher, authenticator, navigator)
        viewModel.onUsernameChanged("foo")
        viewModel.onPasswordChanged("bar")

        // when
        viewModel.onSubmit()

        // then
        assert(viewModel.submitEnabled.value)
        assert(!viewModel.loading.value)
        assert(viewModel.error.value == "Something went wrong: Network error")
    }

    @Test
    fun `should clear error on typing`() = runTest {
        // given
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val authenticator = mock<Authenticator> {
            everySuspend { authenticate("foo", "bar") } returns false
        }
        val navigator = mock<Navigator>(MockMode.autoUnit)
        viewModel = LoginViewModel(dispatcher, authenticator, navigator)
        viewModel.onUsernameChanged("foo")
        viewModel.onPasswordChanged("bar")
        viewModel.onSubmit()
        assert(viewModel.error.value == "Invalid credentials")

        // when
        viewModel.onUsernameChanged("foo2")

        // then
        assert(viewModel.error.value == null)
    }

}
