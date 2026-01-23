# xemantic-kotlin-js

Kotlin multiplatform library providing JavaScript utilities

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

A general set of JavaScript utilities for Kotlin/JS and Kotlin/WASM, including a DOM tree builder DSL.

## Usage

In `build.gradle.kts` add:

```kotlin
dependencies {
    implementation("com.xemantic.kotlin:xemantic-kotlin-js:0.1")
}
```

## Development

From time to time, it is worth to:

### Update gradlew wrapper

```shell
./gradlew wrapper --gradle-version 9.3.0 --distribution-type bin
```

### Update all the dependencies to the latest versions

All the gradle dependencies are managed by the [libs.versions.toml](gradle/libs.versions.toml) file in the `gradle` dir.

It is easy to check for the latest version by running:

```shell
./gradlew dependencyUpdates --no-parallel
```