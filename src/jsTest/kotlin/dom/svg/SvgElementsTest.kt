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

package com.xemantic.kotlin.js.dom.svg

import com.xemantic.kotlin.js.dom.element.set
import com.xemantic.kotlin.js.dom.node
import com.xemantic.kotlin.test.sameAs
import com.xemantic.markanywhere.js.toSemanticEvents
import com.xemantic.markanywhere.render.render
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class SvgElementsTest {

    @Test
    fun `should create SVG element tree with all possible SVG tags`() = runTest {
        // when
        val svg = node.svg("main-image") { svg ->
            svg["viewBox"] = "0 0 800 600"
            svg["width"] = "800"
            svg["height"] = "600"
            title { +"Landscape Scene" }
            desc { +"An SVG landscape with mountains, trees, and sky" }
            metadata { +"Created with xemantic-kotlin-js" }
            style { +".ground { fill: #567d46; }" }
            script { +"document.querySelector('svg').dataset.loaded = 'true'" }
            view(id = "detail") {
                this["viewBox"] = "200 150 400 300"
            }
            defs {
                linearGradient(id = "skyGradient") {
                    it["x1"] = "0"
                    it["y1"] = "0"
                    it["x2"] = "0"
                    it["y2"] = "1"
                    stop {
                        this["offset"] = "0%"
                        this["stop-color"] = "#87CEEB"
                    }
                    stop {
                        this["offset"] = "100%"
                        this["stop-color"] = "#E0F0FF"
                    }
                }
                radialGradient {
                    it["id"] = "sunGlow"
                    it["cx"] = "0.5"
                    it["cy"] = "0.5"
                    it["r"] = "0.5"
                    stop {
                        this["offset"] = "0%"
                        this["stop-color"] = "#FFD700"
                    }
                    stop {
                        this["offset"] = "100%"
                        this["stop-color"] = "transparent"
                    }
                }
                pattern(id = "grass") {
                    it["width"] = "10"
                    it["height"] = "10"
                    it["patternUnits"] = "userSpaceOnUse"
                    rect {
                        it["width"] = "10"
                        it["height"] = "10"
                        it["fill"] = "#567d46"
                    }
                    circle {
                        it["cx"] = "5"
                        it["cy"] = "5"
                        it["r"] = "2"
                        it["fill"] = "#4a6b3c"
                    }
                }
                marker(id = "arrowhead") {
                    it["markerWidth"] = "10"
                    it["markerHeight"] = "7"
                    it["refX"] = "10"
                    it["refY"] = "3.5"
                    it["orient"] = "auto"
                    path {
                        it["d"] = "M 0 0 L 10 3.5 L 0 7 Z"
                        it["fill"] = "#333"
                    }
                }
                clipPath(id = "sceneClip") {
                    rect {
                        it["width"] = "800"
                        it["height"] = "600"
                        it["rx"] = "20"
                    }
                }
                mask(id = "fadeMask") {
                    ellipse {
                        it["cx"] = "400"
                        it["cy"] = "300"
                        it["rx"] = "350"
                        it["ry"] = "250"
                        it["fill"] = "white"
                    }
                }
                filter(id = "shadow") {
                    feGaussianBlur {
                        this["in"] = "SourceAlpha"
                        this["stdDeviation"] = "3"
                        this["result"] = "blur"
                    }
                    feOffset {
                        this["in"] = "blur"
                        this["dx"] = "2"
                        this["dy"] = "2"
                        this["result"] = "offsetBlur"
                    }
                    feFlood {
                        this["flood-color"] = "rgba(0,0,0,0.4)"
                        this["result"] = "flood"
                    }
                    feComposite {
                        this["in"] = "flood"
                        this["in2"] = "offsetBlur"
                        this["operator"] = "in"
                        this["result"] = "shadow"
                    }
                    feBlend {
                        this["in"] = "SourceGraphic"
                        this["in2"] = "shadow"
                        this["mode"] = "normal"
                    }
                    feColorMatrix {
                        this["type"] = "saturate"
                        this["values"] = "1.2"
                    }
                    feComponentTransfer {
                        feFuncR {
                            this["type"] = "linear"
                            this["slope"] = "1.1"
                        }
                        feFuncG {
                            this["type"] = "linear"
                            this["slope"] = "1.1"
                        }
                        feFuncB {
                            this["type"] = "linear"
                            this["slope"] = "0.9"
                        }
                        feFuncA {
                            this["type"] = "identity"
                        }
                    }
                    feConvolveMatrix {
                        this["order"] = "3"
                        this["kernelMatrix"] = "0 -1 0 -1 5 -1 0 -1 0"
                    }
                    feDiffuseLighting {
                        it["surfaceScale"] = "5"
                        it["diffuseConstant"] = "1"
                        feDistantLight {
                            this["azimuth"] = "235"
                            this["elevation"] = "40"
                        }
                    }
                    feSpecularLighting {
                        it["surfaceScale"] = "5"
                        it["specularConstant"] = "0.75"
                        it["specularExponent"] = "20"
                        fePointLight {
                            this["x"] = "150"
                            this["y"] = "60"
                            this["z"] = "200"
                        }
                        feSpotLight {
                            this["x"] = "400"
                            this["y"] = "0"
                            this["z"] = "300"
                            this["pointsAtX"] = "400"
                            this["pointsAtY"] = "300"
                            this["pointsAtZ"] = "0"
                        }
                    }
                    feDisplacementMap {
                        this["in"] = "SourceGraphic"
                        this["in2"] = "turbulence"
                        this["scale"] = "10"
                    }
                    feTurbulence {
                        this["type"] = "fractalNoise"
                        this["baseFrequency"] = "0.02"
                        this["numOctaves"] = "3"
                        this["result"] = "turbulence"
                    }
                    feImage {
                        this["href"] = "texture.png"
                        this["result"] = "texture"
                    }
                    feMorphology {
                        this["operator"] = "dilate"
                        this["radius"] = "2"
                    }
                    feMerge {
                        feMergeNode {
                            this["in"] = "offsetBlur"
                        }
                        feMergeNode {
                            this["in"] = "SourceGraphic"
                        }
                    }
                    feTile {
                        this["in"] = "texture"
                    }
                }
                symbol(id = "tree") {
                    it["viewBox"] = "0 0 40 80"
                    polyline {
                        it["points"] = "20,80 20,30 10,30 20,5 30,30 20,30"
                        it["fill"] = "none"
                        it["stroke"] = "#228B22"
                        it["stroke-width"] = "2"
                    }
                }
            }
            g("scene") {
                it["clip-path"] = "url(#sceneClip)"
                rect("sky") {
                    it["width"] = "800"
                    it["height"] = "400"
                    it["fill"] = "url(#skyGradient)"
                }
                circle {
                    it["cx"] = "650"
                    it["cy"] = "100"
                    it["r"] = "50"
                    it["fill"] = "url(#sunGlow)"
                    animate {
                        this["attributeName"] = "r"
                        this["values"] = "48;52;48"
                        this["dur"] = "3s"
                        this["repeatCount"] = "indefinite"
                    }
                }
                path {
                    it["d"] = "M 0 350 Q 200 250 400 300 T 800 280 L 800 600 L 0 600 Z"
                    it["fill"] = "url(#grass)"
                }
                line {
                    it["x1"] = "0"
                    it["y1"] = "400"
                    it["x2"] = "800"
                    it["y2"] = "400"
                    it["stroke"] = "#333"
                    it["stroke-width"] = "1"
                }
                polygon {
                    it["points"] = "300,400 400,200 500,400"
                    it["fill"] = "#8B7355"
                }
                polyline {
                    it["points"] = "0,380 200,360 400,350 600,340 800,350"
                    it["fill"] = "none"
                    it["stroke"] = "#333"
                    it["stroke-width"] = "2"
                    it["marker-end"] = "url(#arrowhead)"
                }
                ellipse {
                    it["cx"] = "400"
                    it["cy"] = "420"
                    it["rx"] = "200"
                    it["ry"] = "20"
                    it["fill"] = "rgba(0,0,0,0.1)"
                }
                image {
                    this["href"] = "cloud.png"
                    this["x"] = "100"
                    this["y"] = "50"
                    this["width"] = "120"
                    this["height"] = "60"
                }
                use {
                    this["href"] = "#tree"
                    this["x"] = "150"
                    this["y"] = "300"
                    this["width"] = "40"
                    this["height"] = "80"
                }
                a(href = "https://xemantic.com", target = "_blank") {
                    text {
                        it["x"] = "400"
                        it["y"] = "560"
                        it["text-anchor"] = "middle"
                        it["font-family"] = "sans-serif"
                        it["font-size"] = "16"
                        tspan {
                            it["font-weight"] = "bold"
                            +"Landscape "
                        }
                        tspan {
                            it["fill"] = "#666"
                            +"Illustration"
                        }
                    }
                }
                text("path-label") {
                    textPath {
                        it["href"] = "#curvePath"
                        +"Text along a curved path"
                    }
                }
                foreignObject {
                    it["x"] = "620"
                    it["y"] = "460"
                    it["width"] = "160"
                    it["height"] = "80"
                }
                switch {
                    g {
                        it["requiredFeatures"] = "http://www.w3.org/TR/SVG11/feature#Shape"
                        rect {
                            it["x"] = "10"
                            it["y"] = "10"
                            it["width"] = "100"
                            it["height"] = "40"
                            it["fill"] = "#eee"
                        }
                    }
                }
            }
            animateMotion {
                this["dur"] = "5s"
                this["repeatCount"] = "indefinite"
                this["path"] = "M 0 0 L 100 0"
            }
            animateTransform {
                this["attributeName"] = "transform"
                this["type"] = "rotate"
                this["from"] = "0 400 300"
                this["to"] = "360 400 300"
                this["dur"] = "60s"
                this["repeatCount"] = "indefinite"
            }
            set {
                this["attributeName"] = "visibility"
                this["to"] = "visible"
                this["begin"] = "2s"
            }
        }

        // then
        svg.toSemanticEvents().render() sameAs /* language=html */ """
            <svg class="main-image" viewBox="0 0 800 600" width="800" height="600">
              <title>
                Landscape Scene
              </title>
              <desc>
                An SVG landscape with mountains, trees, and sky
              </desc>
              <metadata>
                Created with xemantic-kotlin-js
              </metadata>
              <style>
            .ground { fill: #567d46; }
              </style>
              <script>
            document.querySelector('svg').dataset.loaded = 'true'
              </script>
              <view id="detail" viewBox="200 150 400 300"/>
              <defs>
                <linearGradient id="skyGradient" x1="0" y1="0" x2="0" y2="1">
                  <stop offset="0%" stop-color="#87CEEB"/>
                  <stop offset="100%" stop-color="#E0F0FF"/>
                </linearGradient>
                <radialGradient id="sunGlow" cx="0.5" cy="0.5" r="0.5">
                  <stop offset="0%" stop-color="#FFD700"/>
                  <stop offset="100%" stop-color="transparent"/>
                </radialGradient>
                <pattern id="grass" width="10" height="10" patternUnits="userSpaceOnUse">
                  <rect width="10" height="10" fill="#567d46"/>
                  <circle cx="5" cy="5" r="2" fill="#4a6b3c"/>
                </pattern>
                <marker id="arrowhead" markerWidth="10" markerHeight="7" refX="10" refY="3.5" orient="auto">
                  <path d="M 0 0 L 10 3.5 L 0 7 Z" fill="#333"/>
                </marker>
                <clipPath id="sceneClip">
                  <rect width="800" height="600" rx="20"/>
                </clipPath>
                <mask id="fadeMask">
                  <ellipse cx="400" cy="300" rx="350" ry="250" fill="white"/>
                </mask>
                <filter id="shadow">
                  <feGaussianBlur in="SourceAlpha" stdDeviation="3" result="blur"/>
                  <feOffset in="blur" dx="2" dy="2" result="offsetBlur"/>
                  <feFlood flood-color="rgba(0,0,0,0.4)" result="flood"/>
                  <feComposite in="flood" in2="offsetBlur" operator="in" result="shadow"/>
                  <feBlend in="SourceGraphic" in2="shadow" mode="normal"/>
                  <feColorMatrix type="saturate" values="1.2"/>
                  <feComponentTransfer>
                    <feFuncR type="linear" slope="1.1"/>
                    <feFuncG type="linear" slope="1.1"/>
                    <feFuncB type="linear" slope="0.9"/>
                    <feFuncA type="identity"/>
                  </feComponentTransfer>
                  <feConvolveMatrix order="3" kernelMatrix="0 -1 0 -1 5 -1 0 -1 0"/>
                  <feDiffuseLighting surfaceScale="5" diffuseConstant="1">
                    <feDistantLight azimuth="235" elevation="40"/>
                  </feDiffuseLighting>
                  <feSpecularLighting surfaceScale="5" specularConstant="0.75" specularExponent="20">
                    <fePointLight x="150" y="60" z="200"/>
                    <feSpotLight x="400" y="0" z="300" pointsAtX="400" pointsAtY="300" pointsAtZ="0"/>
                  </feSpecularLighting>
                  <feDisplacementMap in="SourceGraphic" in2="turbulence" scale="10"/>
                  <feTurbulence type="fractalNoise" baseFrequency="0.02" numOctaves="3" result="turbulence"/>
                  <feImage href="texture.png" result="texture"/>
                  <feMorphology operator="dilate" radius="2"/>
                  <feMerge>
                    <feMergeNode in="offsetBlur"/>
                    <feMergeNode in="SourceGraphic"/>
                  </feMerge>
                  <feTile in="texture"/>
                </filter>
                <symbol id="tree" viewBox="0 0 40 80">
                  <polyline points="20,80 20,30 10,30 20,5 30,30 20,30" fill="none" stroke="#228B22" stroke-width="2"/>
                </symbol>
              </defs>
              <g class="scene" clip-path="url(#sceneClip)">
                <rect class="sky" width="800" height="400" fill="url(#skyGradient)"/>
                <circle cx="650" cy="100" r="50" fill="url(#sunGlow)">
                  <animate attributeName="r" values="48;52;48" dur="3s" repeatCount="indefinite"/>
                </circle>
                <path d="M 0 350 Q 200 250 400 300 T 800 280 L 800 600 L 0 600 Z" fill="url(#grass)"/>
                <line x1="0" y1="400" x2="800" y2="400" stroke="#333" stroke-width="1"/>
                <polygon points="300,400 400,200 500,400" fill="#8B7355"/>
                <polyline points="0,380 200,360 400,350 600,340 800,350" fill="none" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
                <ellipse cx="400" cy="420" rx="200" ry="20" fill="rgba(0,0,0,0.1)"/>
                <image href="cloud.png" x="100" y="50" width="120" height="60"/>
                <use href="#tree" x="150" y="300" width="40" height="80"/>
                <a href="https://xemantic.com" target="_blank">
                  <text x="400" y="560" text-anchor="middle" font-family="sans-serif" font-size="16">
                    <tspan font-weight="bold">Landscape </tspan><tspan fill="#666">Illustration</tspan>
                  </text>
                </a>
                <text class="path-label">
                  <textPath href="#curvePath">Text along a curved path</textPath>
                </text>
                <foreignObject x="620" y="460" width="160" height="80"/>
                <switch>
                  <g requiredFeatures="http://www.w3.org/TR/SVG11/feature#Shape">
                    <rect x="10" y="10" width="100" height="40" fill="#eee"/>
                  </g>
                </switch>
              </g>
              <animateMotion dur="5s" repeatCount="indefinite" path="M 0 0 L 100 0"/>
              <animateTransform attributeName="transform" type="rotate" from="0 400 300" to="360 400 300" dur="60s" repeatCount="indefinite"/>
              <set attributeName="visibility" to="visible" begin="2s"/>
            </svg>
        """.trimIndent()
    }

}