# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
./gradlew build                    # Build and test all targets
./gradlew jsTest                   # Run JS tests only
./gradlew wasmJsTest               # Run WASM tests only
./gradlew jsBrowserTest            # Run JS browser tests
./gradlew jsNodeTest               # Run JS Node.js tests
./gradlew dependencyUpdates        # Check for dependency updates (use --no-parallel)
```

## Project Overview

This is a Kotlin multiplatform library (`com.xemantic.kotlin:xemantic-kotlin-js`) providing JavaScript utilities, targeting:
- **js** (browser + Node.js)
- **wasmJs** (browser + Node.js + d8)

The library requires browser DOM support, so platforms without DOM access (like wasmWasi, native) are not supported.

## Architecture

### DOM DSL (`src/jsMain/kotlin/`)

The core feature is a type-safe DSL for building DOM trees:

- **DomDsl.kt**: Core DSL infrastructure
  - `@DomDsl` marker annotation for scope control
  - `node` property returning a `NodeBuilder` on a `DocumentFragment` for creating detached DOM trees (e.g., `node.div { }`)
  - `Node.invoke()` extension to start building children on any existing node
  - `NodeBuilder` class with:
    - `String.invoke()` for arbitrary element names (e.g., `"my:component" { }`)
    - `element()` function for creating typed elements
    - `String.unaryPlus()` for text nodes (`+"Hello"`)
  - `NodeBuilder.dataset` extension with `DataBuilder` for `data-*` attributes (`dataset["key"] = "value"`, `dataset["key"] = null` to remove)
  - `NodeBuilder.hidden` property for toggling the `hidden` attribute

- **dom/aria/**: Type-safe ARIA attribute support
  - `AriaAttributes.kt`: ARIA attribute property definitions (`role`, `aria.label`, `aria.hidden`, `aria.disabled`, `aria.expanded`, `aria.selected`)
  - `AriaBuilder.kt`: DSL builder for ARIA attributes via `aria.label = "text"` syntax, with generic `aria["name"]` indexed access

- **dom/element/Elements.kt**: Low-level `Element` attribute extensions (`Element.get()`, `Element.set()`, `Element += "class"`, `Element -= "class"`)

- **dom/event/Events.kt**: Event handler helpers (`onClick { }`, `onSubmit { }`, `onInput { }`)

- **dom/html/HtmlElements.kt**: Type-safe HTML element functions (`div`, `button`, `span`, `a`, `icon`, etc.) that provide properly typed element receivers (e.g., `HTMLDivElement`, `HTMLButtonElement`)

- **dom/svg/SvgElements.kt**: Type-safe SVG element functions (`svg`, `rect`, `circle`, `path`, etc.)

### Usage Pattern

```kotlin
node.div("className") {
    button("btn") {
        aria.label = "Click me"
        dataset["action"] = "submit"
        onClick { }
        +"Click me"
    }
}
```

## Testing

Uses `xemantic-kotlin-test` with power-assert. Test assertions use `assert` and `have` functions from `com.xemantic.kotlin.test`.

## Code Style

- Explicit API mode enabled (`explicitApi()`)
- Kotlin context parameters and context-sensitive resolution enabled
- All public API requires explicit visibility modifiers