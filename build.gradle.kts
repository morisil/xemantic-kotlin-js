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

@file:OptIn(ExperimentalWasmDsl::class, ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jreleaser.model.Active

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.plugin.power.assert)
    alias(libs.plugins.kotlinx.binary.compatibility.validator)
    alias(libs.plugins.dokka)
    alias(libs.plugins.versions)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.jreleaser)
    alias(libs.plugins.xemantic.conventions)
    alias(libs.plugins.mokkery)
}

group = "com.xemantic.kotlin"

xemantic {
    description = "Kotlin multiplatform library providing JavaScript utilities"
    inceptionYear = "2026"
    applyAllConventions()
}

fun MavenPomDeveloperSpec.projectDevs() {
    developer {
        id = "morisil"
        name = "Kazik Pogoda"
        url = "https://github.com/morisil"
    }
}

val kotlinTarget = KotlinVersion.fromVersion(libs.versions.kotlinTarget.get())

kotlin {

    explicitApi()

    compilerOptions {
        apiVersion = kotlinTarget
        languageVersion = kotlinTarget
        freeCompilerArgs.addAll(
            "-Xcontext-parameters",
            "-Xcontext-sensitive-resolution",
            "-Xexplicit-backing-fields"
        )
        extraWarnings = true
        progressiveMode = true
        optIn.addAll(
            "kotlin.js.ExperimentalJsCollectionsApi"
        )
    }

    jvm() // needed only to verify non-JS tests

    js {
        browser()
        //nodejs()
        binaries.library()
    }

    wasmJs {
        browser()
        nodejs()
        d8()
        binaries.library()
    }

    sourceSets {

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.kotlinx.coroutines.test)
                implementation(libs.xemantic.kotlin.test)
            }
        }

        jsMain {
            dependencies {
                api(libs.kotlinx.coroutines.core)
            }
        }

        jsTest {
            dependencies {
                implementation(libs.markanywhere.render)
                implementation(libs.markanywhere.js)
            }
        }

    }

}

repositories {
    mavenCentral()
}

configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "org.ow2.asm") {
            useVersion(libs.versions.asm.get())
        }
    }
}

powerAssert {
    functions = listOf(
        "com.xemantic.kotlin.test.assert",
        "com.xemantic.kotlin.test.have"
    )
}

// https://kotlinlang.org/docs/dokka-migration.html#adjust-configuration-options
dokka {
    pluginsConfiguration.html {
        footerMessage = xemantic.copyright
    }
}

mavenPublishing {

    publishToMavenCentral(automaticRelease = true)
    signAllPublications()

    pom {

        name = rootProject.name
        description = xemantic.description
        inceptionYear = xemantic.inceptionYear
        url = "https://github.com/${xemantic.gitHubAccount}/${rootProject.name}"

        organization {
            name = xemantic.organization
            url = xemantic.organizationUrl
        }

        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }

        scm {
            url = "https://github.com/${xemantic.gitHubAccount}/${rootProject.name}"
            connection = "scm:git:git://github.com/${xemantic.gitHubAccount}/${rootProject.name}.git"
            developerConnection = "scm:git:ssh://git@github.com/${xemantic.gitHubAccount}/${rootProject.name}.git"
        }

        ciManagement {
            system = "GitHub"
            url = "https://github.com/${xemantic.gitHubAccount}/${rootProject.name}/actions"
        }

        issueManagement {
            system = "GitHub"
            url = "https://github.com/${xemantic.gitHubAccount}/${rootProject.name}/issues"
        }

        developers {
            projectDevs()
        }

    }

}

val releaseAnnouncementSubject = """🚀 ${rootProject.name} $version has been released!"""
val releaseAnnouncement = """
$releaseAnnouncementSubject

${xemantic.description}

${xemantic.releasePageUrl}
""".trim()

jreleaser {

    announce {
        webhooks {
            create("discord") {
                active = Active.ALWAYS
                message = releaseAnnouncement
                messageProperty = "content"
                structuredMessage = true
            }
        }
        linkedin {
            active = Active.ALWAYS
            subject = releaseAnnouncementSubject
            message = releaseAnnouncement
        }
        bluesky {
            active = Active.ALWAYS
            status = releaseAnnouncement
        }
    }

}
