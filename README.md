# xemantic-kotlin-js

Kotlin Multiplatform library providing type-safe DSL for constructing HTML5 and SVG DOM trees, along with reactive web utilities, targeting JavaScript and WebAssembly runtimes.

[<img alt="Maven Central Version" src="https://img.shields.io/maven-central/v/com.xemantic.kotlin/xemantic-kotlin-js">](https://central.sonatype.com/artifact/com.xemantic.kotlin/xemantic-kotlin-js)
[<img alt="GitHub Release Date" src="https://img.shields.io/github/release-date/xemantic/xemantic-kotlin-js">](https://github.com/xemantic/xemantic-kotlin-js/releases)
[<img alt="license" src="https://img.shields.io/github/license/xemantic/xemantic-kotlin-js?color=blue">](https://github.com/xemantic/xemantic-kotlin-js/blob/main/LICENSE)

[<img alt="GitHub Actions Workflow Status" src="https://img.shields.io/github/actions/workflow/status/xemantic/xemantic-kotlin-js/build-main.yml">](https://github.com/xemantic/xemantic-kotlin-js/actions/workflows/build-main.yml)
[<img alt="GitHub branch check runs" src="https://img.shields.io/github/check-runs/xemantic/xemantic-kotlin-js/main">](https://github.com/xemantic/xemantic-kotlin-js/actions/workflows/build-main.yml)
[<img alt="GitHub commits since latest release" src="https://img.shields.io/github/commits-since/xemantic/xemantic-kotlin-js/latest">](https://github.com/xemantic/xemantic-kotlin-js/commits/main/)
[<img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/xemantic/xemantic-kotlin-js">](https://github.com/xemantic/xemantic-kotlin-js/commits/main/)

[<img alt="GitHub contributors" src="https://img.shields.io/github/contributors/xemantic/xemantic-kotlin-js">](https://github.com/xemantic/xemantic-kotlin-js/graphs/contributors)
[<img alt="GitHub commit activity" src="https://img.shields.io/github/commit-activity/t/xemantic/xemantic-kotlin-js">](https://github.com/xemantic/xemantic-kotlin-js/commits/main/)
[<img alt="GitHub code size in bytes" src="https://img.shields.io/github/languages/code-size/xemantic/xemantic-kotlin-js">]()
[<img alt="GitHub Created At" src="https://img.shields.io/github/created-at/xemantic/xemantic-kotlin-js">](https://github.com/xemantic/xemantic-kotlin-js/commits)
[<img alt="kotlin version" src="https://img.shields.io/badge/dynamic/toml?url=https%3A%2F%2Fraw.githubusercontent.com%2Fxemantic%2Fxemantic-kotlin-js%2Fmain%2Fgradle%2Flibs.versions.toml&query=versions.kotlin&label=kotlin">](https://kotlinlang.org/docs/releases.html)
[<img alt="discord users online" src="https://img.shields.io/discord/811561179280965673">](https://discord.gg/vQktqqN2Vn)
[![Bluesky](https://img.shields.io/badge/Bluesky-0285FF?logo=bluesky&logoColor=fff)](https://bsky.app/profile/xemantic.com)

## Why?

A general set of JavaScript utilities for Kotlin/JS and Kotlin/WASM, including idiomatic Kotlin extensions
for JavaScript collections (`JsArray`, `JsMap`, `JsSet`), plain JS objects, DOM utilities,
and a DOM tree builder DSL.

## Usage

In `build.gradle.kts` add:

```kotlin
dependencies {
    implementation("com.xemantic.kotlin:xemantic-kotlin-js:0.1")
}
```

## Features

### `globalThis` access

The [`globalThis`](src/jsMain/kotlin/JsGlobal.kt) property provides access to the JavaScript
[`globalThis`](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/globalThis)
object as a `dynamic` value:

```kotlin
val value = globalThis.someGlobalProperty
globalThis.myFlag = true
```

### `JsObject`

The [`JsObject`](src/jsMain/kotlin/JsObjects.kt) external interface represents a plain JavaScript
object (`{}`), with operator extensions for bracket access:

```kotlin
val obj = JsObject()
obj["name"] = "Alice"
obj["age"] = 30
val name: String = obj["name"]  // "Alice"

obj.isEmpty()       // false
obj.isNotEmpty()    // true
obj.isNullOrEmpty() // false
```

### JS Collections

Kotlin's experimental `JsArray`, `JsMap`, and `JsSet` types lack many common operations.
This library adds idiomatic Kotlin extensions for all three.

#### [`JsArray`](src/jsMain/kotlin/collections/JsArrays.kt)

```kotlin
val array = jsArrayOf(1, 2, 3)
array.length              // 3
array[0]                  // 1
array[1] = 10             // indexed set
array += 4                // push via += operator
array.push(5)             // push
array.map { it * 2 }      // JsArray(2, 20, 6, 8, 10)
array.join(", ")          // "1, 10, 3, 4, 5"
array.isEmpty()           // false
```

#### [`JsMap`](src/jsMain/kotlin/collections/JsMaps.kt)

```kotlin
val map = JsMap<String, Int>()
map["x"] = 1
map["y"] = 2
map["x"]           // 1
map.size            // 2
map.isEmpty()       // false
```

#### [`JsSet`](src/jsMain/kotlin/collections/JsSets.kt)

```kotlin
val set = jsSetOf("a", "b", "c")
"a" in set          // true (contains operator)
"z" in set          // false
set.size            // 3
set.isEmpty()       // false
```

### DOM: `ItemArrayLike.forEach`

The [`forEach`](src/jsMain/kotlin/dom/ItemArrayLikes.kt) extension on `ItemArrayLike<T>` enables
iteration over DOM collections like `NamedNodeMap` and `NodeList`:

```kotlin
element.attributes.forEach { attr ->
    println("${attr.name}=${attr.value}")
}
element.childNodes.forEach { node ->
    println(node.nodeName)
}
```

## MVVM Example

The test suite includes a full [MVVM (Model-View-ViewModel)](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) example demonstrating how to structure a Kotlin multiplatform application with a clear separation between platform-independent logic and browser-specific view code.

### Why split `commonTest` and `jsTest`?

The ViewModel and service interfaces live in `commonTest`, while the View lives in `jsTest`. This split is intentional:

- **`commonTest`** - The [`LoginViewModel`](src/commonTest/kotlin/test/mvvm/LoginViewModel.kt), service interfaces ([`Authenticator`](src/commonTest/kotlin/test/mvvm/Services.kt), [`Navigator`](src/commonTest/kotlin/test/mvvm/Services.kt)), and all [ViewModel tests](src/commonTest/kotlin/test/mvvm/MvvmTest.kt) are pure Kotlin with no DOM dependency. They can run on any platform (JVM, Native, JS, WASM), enabling fast feedback loops during development (e.g., running tests on JVM without a browser).
- **`jsTest`** - The [`LoginView`](src/jsTest/kotlin/test/mvvm/LoginView.kt) uses the DOM DSL to build the actual HTML tree and bind it to the ViewModel. It can only run in a browser environment.

This separation means the bulk of your business logic and its tests remain portable and fast to execute, while only the thin view layer requires a browser.

### [Service interfaces](src/commonTest/kotlin/test/mvvm/Services.kt) (`commonTest`)

```kotlin
interface Authenticator {
    suspend fun authenticate(username: String, password: String): Boolean
}

interface Navigator {
    fun goTo(location: String)
}
```

### [ViewModel](src/commonTest/kotlin/test/mvvm/LoginViewModel.kt) (`commonTest`)

The [`LoginViewModel`](src/commonTest/kotlin/test/mvvm/LoginViewModel.kt) exposes reactive state via `StateFlow` and delegates side effects to injected services. The `CoroutineDispatcher` is injected to decouple the ViewModel from any specific threading model:

| Platform | Dispatcher | Why |
|---|---|---|
| **Kotlin/JS & WASM** | `Dispatchers.Default` or `Dispatchers.Main` | JavaScript is single-threaded — both map to the same event-loop dispatcher. |
| **Android** | `Dispatchers.Main` (or `.immediate`) | `StateFlow` updates must be emitted on the UI thread to safely drive view updates. |
| **iOS (Kotlin/Native)** | `Dispatchers.Main` | Maps to the main dispatch queue, same reasoning as Android. |
| **Tests** | `UnconfinedTestDispatcher` | Executes coroutines eagerly and deterministically, without a real event loop. |

```kotlin
class LoginViewModel(
    dispatcher: CoroutineDispatcher,
    private val authenticator: Authenticator,
    private val navigator: Navigator
) {
    val submitEnabled: StateFlow<Boolean>
        field = MutableStateFlow(false)

    val error: StateFlow<String?>
        field = MutableStateFlow<String?>(null)

    val loading: StateFlow<Boolean>
        field = MutableStateFlow(false)

    fun onUsernameChanged(username: String) { /* ... */ }
    fun onPasswordChanged(password: String) { /* ... */ }
    fun onSubmit() { /* launches coroutine to authenticate */ }
    fun onCleared() { scope.cancel() }
}
```

### [ViewModel tests](src/commonTest/kotlin/test/mvvm/MvvmTest.kt) (`commonTest`)

Tests use [Mokkery](https://mokkery.dev/) for mocking and `kotlinx-coroutines-test` for deterministic coroutine execution. No browser needed:

```kotlin
@Test
fun `should log in on successful authentication`() = runTest {
    val dispatcher = UnconfinedTestDispatcher(testScheduler)
    val authenticator = mock<Authenticator> {
        everySuspend { authenticate("foo", "bar") } returns true
    }
    val navigator = mock<Navigator>(MockMode.autoUnit)
    viewModel = LoginViewModel(dispatcher, authenticator, navigator)

    viewModel.onUsernameChanged("foo")
    viewModel.onPasswordChanged("bar")
    viewModel.onSubmit()

    assert(!viewModel.submitEnabled.value)
    assert(!viewModel.loading.value)
    verifySuspend(VerifyMode.exhaustiveOrder) {
        authenticator.authenticate("foo", "bar")
        navigator.goTo("Home")
    }
}
```

### [View](src/jsTest/kotlin/test/mvvm/LoginView.kt) (`jsTest`)

The `LoginView` uses the DOM DSL to build HTML and binds ViewModel state flows directly to DOM properties:

```kotlin
fun loginView(viewModel: LoginViewModel) = node { form("app-login") {
    it.onsubmit = { event -> event.preventDefault() }
    div("field label border round prefix") {
        icon("mail")
        input("large border", name = "username", type = "text") { input ->
            input.oninput = {
                viewModel.onUsernameChanged(input.value)
            }
        }
        label { +"Username" }
    }
    // ...
    nav("no-space") {
        button("large", type = "submit") { button ->
            button.ariaLabel = "Submit"
            viewModel.submitEnabled.onEach { enabled ->
                button.disabled = !enabled
            }.launchIn(viewModel.scope)
            button.onclick = { viewModel.onSubmit() }
            +"Submit"
        }
    }
}}
```

The view function returns a DOM node that can be appended to the document. ViewModel `StateFlow`s are collected with `onEach { ... }.launchIn(viewModel.scope)` to reactively update DOM properties like `disabled` and `hidden`.

## Development

### Update all the dependencies to the latest versions

All the gradle dependencies are managed by the [libs.versions.toml](gradle/libs.versions.toml) file in the `gradle` dir.

It is easy to check for the latest version by running:

```shell
./gradlew dependencyUpdates --no-parallel
```
