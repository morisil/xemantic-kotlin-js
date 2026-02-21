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

import com.xemantic.kotlin.js.dom.NodeBuilder
import org.w3c.dom.svg.*

public inline fun <T : SVGElement> NodeBuilder.svgElement(
    name: String,
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(T) -> Unit = {}
): T = elementNs("http://www.w3.org/2000/svg", name, klass, id, block)

// Container / Structural

public inline fun NodeBuilder.svg(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGSVGElement) -> Unit = {}
): SVGSVGElement = svgElement("svg", klass, id, block)

public inline fun NodeBuilder.g(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGGElement) -> Unit = {}
): SVGGElement = svgElement("g", klass, id, block)

public inline fun NodeBuilder.defs(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGDefsElement) -> Unit = {}
): SVGDefsElement = svgElement("defs", klass, id, block)

public inline fun NodeBuilder.symbol(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGSymbolElement) -> Unit = {}
): SVGSymbolElement = svgElement("symbol", klass, id, block)

public inline fun NodeBuilder.use(
    klass: String? = null,
    id: String? = null,
    crossinline block: SVGUseElement.() -> Unit = {}
): SVGUseElement = svgElement("use", klass, id) {
    it.block()
}

public inline fun NodeBuilder.switch(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGSwitchElement) -> Unit = {}
): SVGSwitchElement = svgElement("switch", klass, id, block)

public inline fun NodeBuilder.foreignObject(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGForeignObjectElement) -> Unit = {}
): SVGForeignObjectElement = svgElement("foreignObject", klass, id, block)

// Shape

public inline fun NodeBuilder.circle(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGCircleElement) -> Unit = {}
): SVGCircleElement = svgElement("circle", klass, id, block)

public inline fun NodeBuilder.ellipse(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGEllipseElement) -> Unit = {}
): SVGEllipseElement = svgElement("ellipse", klass, id, block)

public inline fun NodeBuilder.line(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGLineElement) -> Unit = {}
): SVGLineElement = svgElement("line", klass, id, block)

public inline fun NodeBuilder.path(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGPathElement) -> Unit = {}
): SVGPathElement = svgElement("path", klass, id, block)

public inline fun NodeBuilder.polygon(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGPolygonElement) -> Unit = {}
): SVGPolygonElement = svgElement("polygon", klass, id, block)

public inline fun NodeBuilder.polyline(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGPolylineElement) -> Unit = {}
): SVGPolylineElement = svgElement("polyline", klass, id, block)

public inline fun NodeBuilder.rect(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGRectElement) -> Unit = {}
): SVGRectElement = svgElement("rect", klass, id, block)

// Text

public inline fun NodeBuilder.text(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGTextElement) -> Unit = {}
): SVGTextElement = svgElement("text", klass, id, block)

public inline fun NodeBuilder.tspan(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGTSpanElement) -> Unit = {}
): SVGTSpanElement = svgElement("tspan", klass, id, block)

public inline fun NodeBuilder.textPath(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGTextPathElement) -> Unit = {}
): SVGTextPathElement = svgElement("textPath", klass, id, block)

// Gradient / Paint

public inline fun NodeBuilder.linearGradient(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGLinearGradientElement) -> Unit = {}
): SVGLinearGradientElement = svgElement("linearGradient", klass, id, block)

public inline fun NodeBuilder.radialGradient(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGRadialGradientElement) -> Unit = {}
): SVGRadialGradientElement = svgElement("radialGradient", klass, id, block)

public inline fun NodeBuilder.stop(
    klass: String? = null,
    crossinline block: SVGStopElement.() -> Unit = {}
): SVGStopElement = svgElement("stop", klass) {
    it.block()
}

public inline fun NodeBuilder.pattern(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGPatternElement) -> Unit = {}
): SVGPatternElement = svgElement("pattern", klass, id, block)

public inline fun NodeBuilder.marker(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGMarkerElement) -> Unit = {}
): SVGMarkerElement = svgElement("marker", klass, id, block)

// Clipping / Masking

public inline fun NodeBuilder.clipPath(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGElement) -> Unit = {}
): SVGElement = svgElement("clipPath", klass, id, block)

public inline fun NodeBuilder.mask(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGElement) -> Unit = {}
): SVGElement = svgElement("mask", klass, id, block)

// Image

public inline fun NodeBuilder.image(
    klass: String? = null,
    id: String? = null,
    crossinline block: SVGImageElement.() -> Unit = {}
): SVGImageElement = svgElement("image", klass, id) {
    it.block()
}

// Link

public inline fun NodeBuilder.a(
    klass: String? = null,
    id: String? = null,
    href: String? = null,
    target: String? = null,
    crossinline block: NodeBuilder.(SVGAElement) -> Unit = {}
): SVGAElement = svgElement("a", klass, id) {
    if (href != null) { it.setAttribute("href", href) }
    if (target != null) { it.setAttribute("target", target) }
    block(it)
}

// Filter

public inline fun NodeBuilder.filter(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(SVGElement) -> Unit = {}
): SVGElement = svgElement("filter", klass, id, block)

public inline fun NodeBuilder.feBlend(
    klass: String? = null,
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feBlend", klass) {
    it.block()
}

public inline fun NodeBuilder.feColorMatrix(
    klass: String? = null,
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feColorMatrix", klass) {
    it.block()
}

public inline fun NodeBuilder.feComponentTransfer(
    klass: String? = null,
    crossinline block: NodeBuilder.(SVGElement) -> Unit = {}
): SVGElement = svgElement("feComponentTransfer", klass, block = block)

public inline fun NodeBuilder.feComposite(
    klass: String? = null,
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feComposite", klass) {
    it.block()
}

public inline fun NodeBuilder.feConvolveMatrix(
    klass: String? = null,
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feConvolveMatrix", klass) {
    it.block()
}

public inline fun NodeBuilder.feDiffuseLighting(
    klass: String? = null,
    crossinline block: NodeBuilder.(SVGElement) -> Unit = {}
): SVGElement = svgElement("feDiffuseLighting", klass, block = block)

public inline fun NodeBuilder.feDisplacementMap(
    klass: String? = null,
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feDisplacementMap", klass) {
    it.block()
}

public inline fun NodeBuilder.feDistantLight(
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feDistantLight") {
    it.block()
}

public inline fun NodeBuilder.feFlood(
    klass: String? = null,
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feFlood", klass) {
    it.block()
}

public inline fun NodeBuilder.feFuncA(
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feFuncA") {
    it.block()
}

public inline fun NodeBuilder.feFuncB(
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feFuncB") {
    it.block()
}

public inline fun NodeBuilder.feFuncG(
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feFuncG") {
    it.block()
}

public inline fun NodeBuilder.feFuncR(
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feFuncR") {
    it.block()
}

public inline fun NodeBuilder.feGaussianBlur(
    klass: String? = null,
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feGaussianBlur", klass) {
    it.block()
}

public inline fun NodeBuilder.feImage(
    klass: String? = null,
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feImage", klass) {
    it.block()
}

public inline fun NodeBuilder.feMerge(
    klass: String? = null,
    crossinline block: NodeBuilder.(SVGElement) -> Unit = {}
): SVGElement = svgElement("feMerge", klass, block = block)

public inline fun NodeBuilder.feMergeNode(
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feMergeNode") {
    it.block()
}

public inline fun NodeBuilder.feMorphology(
    klass: String? = null,
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feMorphology", klass) {
    it.block()
}

public inline fun NodeBuilder.feOffset(
    klass: String? = null,
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feOffset", klass) {
    it.block()
}

public inline fun NodeBuilder.fePointLight(
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("fePointLight") {
    it.block()
}

public inline fun NodeBuilder.feSpecularLighting(
    klass: String? = null,
    crossinline block: NodeBuilder.(SVGElement) -> Unit = {}
): SVGElement = svgElement("feSpecularLighting", klass, block = block)

public inline fun NodeBuilder.feSpotLight(
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feSpotLight") {
    it.block()
}

public inline fun NodeBuilder.feTile(
    klass: String? = null,
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feTile", klass) {
    it.block()
}

public inline fun NodeBuilder.feTurbulence(
    klass: String? = null,
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("feTurbulence", klass) {
    it.block()
}

// Animation

public inline fun NodeBuilder.animate(
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("animate") {
    it.block()
}

public inline fun NodeBuilder.animateMotion(
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("animateMotion") {
    it.block()
}

public inline fun NodeBuilder.animateTransform(
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("animateTransform") {
    it.block()
}

public inline fun NodeBuilder.set(
    crossinline block: SVGElement.() -> Unit = {}
): SVGElement = svgElement("set") {
    it.block()
}

// Descriptive

public inline fun NodeBuilder.title(
    klass: String? = null,
    crossinline block: NodeBuilder.(SVGTitleElement) -> Unit = {}
): SVGTitleElement = svgElement("title", klass, block = block)

public inline fun NodeBuilder.desc(
    klass: String? = null,
    crossinline block: NodeBuilder.(SVGDescElement) -> Unit = {}
): SVGDescElement = svgElement("desc", klass, block = block)

public inline fun NodeBuilder.metadata(
    klass: String? = null,
    crossinline block: NodeBuilder.(SVGMetadataElement) -> Unit = {}
): SVGMetadataElement = svgElement("metadata", klass, block = block)

// Style / Script / View

public inline fun NodeBuilder.style(
    crossinline block: NodeBuilder.(SVGStyleElement) -> Unit = {}
): SVGStyleElement = svgElement("style", block = block)

public inline fun NodeBuilder.script(
    crossinline block: NodeBuilder.(SVGScriptElement) -> Unit = {}
): SVGScriptElement = svgElement("script", block = block)

public inline fun NodeBuilder.view(
    id: String? = null,
    crossinline block: SVGViewElement.() -> Unit = {}
): SVGViewElement = svgElement("view", id = id) {
    it.block()
}
