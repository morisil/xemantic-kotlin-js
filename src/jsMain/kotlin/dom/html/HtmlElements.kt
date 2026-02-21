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

package com.xemantic.kotlin.js.dom.html

import com.xemantic.kotlin.js.dom.NodeBuilder
import com.xemantic.kotlin.js.dom.aria.aria
import com.xemantic.kotlin.js.dom.aria.hidden
import org.w3c.dom.*

// region Document structure

public inline fun NodeBuilder.html(
    klass: String? = null,
    lang: String? = null,
    crossinline block: NodeBuilder.(HTMLHtmlElement) -> Unit = {}
): HTMLHtmlElement = element("html", klass) {
    if (lang != null) { it.lang = lang }
    block(it)
}

public inline fun NodeBuilder.head(
    crossinline block: NodeBuilder.(HTMLHeadElement) -> Unit = {}
): HTMLHeadElement = element("head", block = block)

public inline fun NodeBuilder.body(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLBodyElement) -> Unit = {}
): HTMLBodyElement = element("body", klass, block = block)

public inline fun NodeBuilder.title(
    crossinline block: NodeBuilder.(HTMLTitleElement) -> Unit = {}
): HTMLTitleElement = element("title", block = block)

// endregion

// region Metadata and scripting

public inline fun NodeBuilder.base(
    crossinline block: HTMLBaseElement.() -> Unit = {}
): HTMLBaseElement = element("base") {
    it.block()
}

public inline fun NodeBuilder.link(
    href: String? = null,
    rel: String? = null,
    crossinline block: HTMLLinkElement.() -> Unit = {}
): HTMLLinkElement = element("link") {
    if (href != null) { it.href = href }
    if (rel != null) { it.rel = rel }
    it.block()
}

public inline fun NodeBuilder.meta(
    name: String? = null,
    content: String? = null,
    crossinline block: HTMLMetaElement.() -> Unit = {}
): HTMLMetaElement = element("meta") {
    if (name != null) { it.name = name }
    if (content != null) { it.content = content }
    it.block()
}

public inline fun NodeBuilder.style(
    crossinline block: NodeBuilder.(HTMLStyleElement) -> Unit = {}
): HTMLStyleElement = element("style", block = block)

public inline fun NodeBuilder.script(
    src: String? = null,
    crossinline block: NodeBuilder.(HTMLScriptElement) -> Unit = {}
): HTMLScriptElement = element("script") {
    if (src != null) { it.src = src }
    block(it)
}

public inline fun NodeBuilder.noscript(
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("noscript", block = block)

// endregion

// region Sections

public inline fun NodeBuilder.header(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("header", klass, id, block)

public inline fun NodeBuilder.footer(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("footer", klass, id, block)

public inline fun NodeBuilder.main(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("main", klass, id, block)

public inline fun NodeBuilder.nav(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("nav", klass, id, block)

public inline fun NodeBuilder.section(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("section", klass, id, block)

public inline fun NodeBuilder.article(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("article", klass, id, block)

public inline fun NodeBuilder.aside(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("aside", klass, id, block)

public inline fun NodeBuilder.address(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("address", klass, id, block)

public inline fun NodeBuilder.hgroup(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("hgroup", klass, id, block)

public inline fun NodeBuilder.search(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("search", klass, id, block)

// endregion

// region Headings

public inline fun NodeBuilder.h1(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLHeadingElement) -> Unit = {}
): HTMLHeadingElement = element("h1", klass, id, block)

public inline fun NodeBuilder.h2(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLHeadingElement) -> Unit = {}
): HTMLHeadingElement = element("h2", klass, id, block)

public inline fun NodeBuilder.h3(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLHeadingElement) -> Unit = {}
): HTMLHeadingElement = element("h3", klass, id, block)

public inline fun NodeBuilder.h4(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLHeadingElement) -> Unit = {}
): HTMLHeadingElement = element("h4", klass, id, block)

public inline fun NodeBuilder.h5(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLHeadingElement) -> Unit = {}
): HTMLHeadingElement = element("h5", klass, id, block)

public inline fun NodeBuilder.h6(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLHeadingElement) -> Unit = {}
): HTMLHeadingElement = element("h6", klass, id, block)

// endregion

// region Grouping content

public inline fun NodeBuilder.div(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLDivElement) -> Unit = {}
): HTMLDivElement = element("div", klass, id, block)

public inline fun NodeBuilder.p(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLParagraphElement) -> Unit = {}
): HTMLParagraphElement = element("p", klass, block = block)

public inline fun NodeBuilder.pre(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLPreElement) -> Unit = {}
): HTMLPreElement = element("pre", klass, block = block)

public inline fun NodeBuilder.blockquote(
    klass: String? = null,
    cite: String? = null,
    crossinline block: NodeBuilder.(HTMLQuoteElement) -> Unit = {}
): HTMLQuoteElement = element("blockquote", klass) {
    if (cite != null) { it.cite = cite }
    block(it)
}

public inline fun NodeBuilder.figure(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("figure", klass, block = block)

public inline fun NodeBuilder.figcaption(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("figcaption", klass, block = block)

public inline fun NodeBuilder.hr(
    klass: String? = null,
    crossinline block: HTMLHRElement.() -> Unit = {}
): HTMLHRElement = element("hr", klass) {
    it.block()
}

// endregion

// region Lists

public inline fun NodeBuilder.ul(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLUListElement) -> Unit = {}
): HTMLUListElement = element("ul", klass, block = block)

public inline fun NodeBuilder.ol(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLOListElement) -> Unit = {}
): HTMLOListElement = element("ol", klass, block = block)

public inline fun NodeBuilder.li(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLLIElement) -> Unit = {}
): HTMLLIElement = element("li", klass, block = block)

public inline fun NodeBuilder.dl(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("dl", klass, block = block)

public inline fun NodeBuilder.dt(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("dt", klass, block = block)

public inline fun NodeBuilder.dd(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("dd", klass, block = block)

public inline fun NodeBuilder.menu(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("menu", klass, block = block)

// endregion

// region Tables

public inline fun NodeBuilder.table(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLTableElement) -> Unit = {}
): HTMLTableElement = element("table", klass, id, block)

public inline fun NodeBuilder.caption(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLTableCaptionElement) -> Unit = {}
): HTMLTableCaptionElement = element("caption", klass, block = block)

public inline fun NodeBuilder.thead(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLTableSectionElement) -> Unit = {}
): HTMLTableSectionElement = element("thead", klass, block = block)

public inline fun NodeBuilder.tbody(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLTableSectionElement) -> Unit = {}
): HTMLTableSectionElement = element("tbody", klass, block = block)

public inline fun NodeBuilder.tfoot(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLTableSectionElement) -> Unit = {}
): HTMLTableSectionElement = element("tfoot", klass, block = block)

public inline fun NodeBuilder.tr(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLTableRowElement) -> Unit = {}
): HTMLTableRowElement = element("tr", klass, block = block)

public inline fun NodeBuilder.th(
    klass: String? = null,
    scope: String? = null,
    crossinline block: NodeBuilder.(HTMLTableCellElement) -> Unit = {}
): HTMLTableCellElement = element("th", klass) {
    if (scope != null) { it.scope = scope }
    block(it)
}

public inline fun NodeBuilder.td(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLTableCellElement) -> Unit = {}
): HTMLTableCellElement = element("td", klass, block = block)

public inline fun NodeBuilder.colgroup(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLTableColElement) -> Unit = {}
): HTMLTableColElement = element("colgroup", klass, block = block)

public inline fun NodeBuilder.col(
    klass: String? = null,
    span: Int? = null,
    crossinline block: HTMLTableColElement.() -> Unit = {}
): HTMLTableColElement = element("col", klass) {
    if (span != null) { it.span = span }
    it.block()
}

// endregion

// region Text-level semantics

public inline fun NodeBuilder.span(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLSpanElement) -> Unit = {}
): HTMLSpanElement = element("span", klass, block = block)

public inline fun NodeBuilder.a(
    klass: String? = null,
    href: String? = null,
    target: String? = null,
    crossinline block: NodeBuilder.(HTMLAnchorElement) -> Unit = {}
): HTMLAnchorElement = element("a", klass) {
    if (href != null) { it.href = href }
    if (target != null) { it.target = target }
    block(it)
}

public inline fun NodeBuilder.strong(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("strong", klass, block = block)

public inline fun NodeBuilder.em(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("em", klass, block = block)

public inline fun NodeBuilder.b(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("b", klass, block = block)

public inline fun NodeBuilder.i(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("i", klass, block = block)

public inline fun NodeBuilder.u(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("u", klass, block = block)

public inline fun NodeBuilder.s(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("s", klass, block = block)

public inline fun NodeBuilder.small(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("small", klass, block = block)

public inline fun NodeBuilder.mark(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("mark", klass, block = block)

public inline fun NodeBuilder.sub(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("sub", klass, block = block)

public inline fun NodeBuilder.sup(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("sup", klass, block = block)

public inline fun NodeBuilder.abbr(
    klass: String? = null,
    title: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("abbr", klass) {
    if (title != null) { it.title = title }
    block(it)
}

public inline fun NodeBuilder.cite(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("cite", klass, block = block)

public inline fun NodeBuilder.code(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("code", klass, block = block)

public inline fun NodeBuilder.dfn(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("dfn", klass, block = block)

public inline fun NodeBuilder.kbd(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("kbd", klass, block = block)

public inline fun NodeBuilder.samp(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("samp", klass, block = block)

public inline fun NodeBuilder.`var`(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("var", klass, block = block)

public inline fun NodeBuilder.q(
    klass: String? = null,
    cite: String? = null,
    crossinline block: NodeBuilder.(HTMLQuoteElement) -> Unit = {}
): HTMLQuoteElement = element("q", klass) {
    if (cite != null) { it.cite = cite }
    block(it)
}

public inline fun NodeBuilder.time(
    klass: String? = null,
    dateTime: String? = null,
    crossinline block: NodeBuilder.(HTMLTimeElement) -> Unit = {}
): HTMLTimeElement = element("time", klass) {
    if (dateTime != null) { it.dateTime = dateTime }
    block(it)
}

public inline fun NodeBuilder.data(
    klass: String? = null,
    value: String? = null,
    crossinline block: NodeBuilder.(HTMLDataElement) -> Unit = {}
): HTMLDataElement = element("data", klass) {
    if (value != null) { it.value = value }
    block(it)
}

public inline fun NodeBuilder.ins(
    klass: String? = null,
    cite: String? = null,
    dateTime: String? = null,
    crossinline block: NodeBuilder.(HTMLModElement) -> Unit = {}
): HTMLModElement = element("ins", klass) {
    if (cite != null) { it.cite = cite }
    if (dateTime != null) { it.dateTime = dateTime }
    block(it)
}

public inline fun NodeBuilder.del(
    klass: String? = null,
    cite: String? = null,
    dateTime: String? = null,
    crossinline block: NodeBuilder.(HTMLModElement) -> Unit = {}
): HTMLModElement = element("del", klass) {
    if (cite != null) { it.cite = cite }
    if (dateTime != null) { it.dateTime = dateTime }
    block(it)
}

public inline fun NodeBuilder.ruby(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("ruby", klass, block = block)

public inline fun NodeBuilder.rt(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("rt", klass, block = block)

public inline fun NodeBuilder.rp(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("rp", klass, block = block)

public inline fun NodeBuilder.bdi(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("bdi", klass, block = block)

public inline fun NodeBuilder.bdo(
    klass: String? = null,
    dir: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("bdo", klass) {
    if (dir != null) { it.dir = dir }
    block(it)
}

public inline fun NodeBuilder.br(
    crossinline block: HTMLBRElement.() -> Unit = {}
): HTMLBRElement = element("br") {
    it.block()
}

public inline fun NodeBuilder.wbr(
    crossinline block: HTMLElement.() -> Unit = {}
): HTMLElement = element("wbr") {
    it.block()
}

// endregion

// region Forms

public inline fun NodeBuilder.form(
    klass: String? = null,
    id: String? = null,
    action: String? = null,
    method: String? = null,
    crossinline block: NodeBuilder.(HTMLFormElement) -> Unit = {}
): HTMLFormElement = element("form", klass, id) {
    if (action != null) { it.action = action }
    if (method != null) { it.method = method }
    block(it)
}

public inline fun NodeBuilder.fieldset(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLFieldSetElement) -> Unit = {}
): HTMLFieldSetElement = element("fieldset", klass, block = block)

public inline fun NodeBuilder.legend(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLLegendElement) -> Unit = {}
): HTMLLegendElement = element("legend", klass, block = block)

public inline fun NodeBuilder.label(
    klass: String? = null,
    htmlFor: String? = null,
    crossinline block: NodeBuilder.(HTMLLabelElement) -> Unit = {}
): HTMLLabelElement = element("label", klass) {
    if (htmlFor != null) { it.htmlFor = htmlFor }
    block(it)
}

public inline fun NodeBuilder.input(
    klass: String? = null,
    id: String? = null,
    name: String? = null,
    type: String? = null,
    placeholder: String? = null,
    value: String? = null,
    crossinline block: NodeBuilder.(HTMLInputElement) -> Unit = {}
): HTMLInputElement = element("input", klass, id) {
    if (name != null) { it.name = name }
    if (type != null) { it.type = type }
    if (placeholder != null) { it.placeholder = placeholder }
    if (value != null) { it.value = value }
    block(it)
}

public inline fun NodeBuilder.button(
    klass: String? = null,
    id: String? = null,
    type: String? = null,
    crossinline block: NodeBuilder.(HTMLButtonElement) -> Unit = {}
): HTMLButtonElement = element("button", klass, id) {
    if (type != null) { it.type = type }
    block(it)
}

public inline fun NodeBuilder.select(
    klass: String? = null,
    id: String? = null,
    name: String? = null,
    crossinline block: NodeBuilder.(HTMLSelectElement) -> Unit = {}
): HTMLSelectElement = element("select", klass, id) {
    if (name != null) { it.name = name }
    block(it)
}

public inline fun NodeBuilder.option(
    klass: String? = null,
    value: String? = null,
    crossinline block: NodeBuilder.(HTMLOptionElement) -> Unit = {}
): HTMLOptionElement = element("option", klass) {
    if (value != null) { it.value = value }
    block(it)
}

public inline fun NodeBuilder.optgroup(
    klass: String? = null,
    label: String? = null,
    crossinline block: NodeBuilder.(HTMLOptGroupElement) -> Unit = {}
): HTMLOptGroupElement = element("optgroup", klass) {
    if (label != null) { it.label = label }
    block(it)
}

public inline fun NodeBuilder.textarea(
    klass: String? = null,
    id: String? = null,
    name: String? = null,
    placeholder: String? = null,
    crossinline block: NodeBuilder.(HTMLTextAreaElement) -> Unit = {}
): HTMLTextAreaElement = element("textarea", klass, id) {
    if (name != null) { it.name = name }
    if (placeholder != null) { it.placeholder = placeholder }
    block(it)
}

public inline fun NodeBuilder.output(
    klass: String? = null,
    id: String? = null,
    name: String? = null,
    crossinline block: NodeBuilder.(HTMLOutputElement) -> Unit = {}
): HTMLOutputElement = element("output", klass, id) {
    if (name != null) { it.name = name }
    block(it)
}

public inline fun NodeBuilder.datalist(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLDataListElement) -> Unit = {}
): HTMLDataListElement = element("datalist", klass, id, block)

public inline fun NodeBuilder.meter(
    klass: String? = null,
    value: Double? = null,
    min: Double? = null,
    max: Double? = null,
    crossinline block: NodeBuilder.(HTMLMeterElement) -> Unit = {}
): HTMLMeterElement = element("meter", klass) {
    if (value != null) { it.value = value }
    if (min != null) { it.min = min }
    if (max != null) { it.max = max }
    block(it)
}

public inline fun NodeBuilder.progress(
    klass: String? = null,
    value: Double? = null,
    max: Double? = null,
    crossinline block: NodeBuilder.(HTMLProgressElement) -> Unit = {}
): HTMLProgressElement = element("progress", klass) {
    if (value != null) { it.value = value }
    if (max != null) { it.max = max }
    block(it)
}

// endregion

// region Embedded content

public inline fun NodeBuilder.img(
    klass: String? = null,
    id: String? = null,
    src: String? = null,
    alt: String? = null,
    crossinline block: HTMLImageElement.() -> Unit = {}
): HTMLImageElement = element("img", klass, id) {
    if (src != null) { it.src = src }
    if (alt != null) { it.alt = alt }
    it.block()
}

public inline fun NodeBuilder.picture(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLPictureElement) -> Unit = {}
): HTMLPictureElement = element("picture", klass, block = block)

public inline fun NodeBuilder.source(
    crossinline block: HTMLSourceElement.() -> Unit = {}
): HTMLSourceElement = element("source") {
    it.block()
}

public inline fun NodeBuilder.iframe(
    klass: String? = null,
    id: String? = null,
    src: String? = null,
    crossinline block: HTMLIFrameElement.() -> Unit = {}
): HTMLIFrameElement = element("iframe", klass, id) {
    if (src != null) { it.src = src }
    it.block()
}

public inline fun NodeBuilder.embed(
    klass: String? = null,
    src: String? = null,
    type: String? = null,
    crossinline block: HTMLEmbedElement.() -> Unit = {}
): HTMLEmbedElement = element("embed", klass) {
    if (src != null) { it.src = src }
    if (type != null) { it.type = type }
    it.block()
}

public inline fun NodeBuilder.`object`(
    klass: String? = null,
    data: String? = null,
    type: String? = null,
    crossinline block: NodeBuilder.(HTMLObjectElement) -> Unit = {}
): HTMLObjectElement = element("object", klass) {
    if (data != null) { it.data = data }
    if (type != null) { it.type = type }
    block(it)
}

public inline fun NodeBuilder.video(
    klass: String? = null,
    id: String? = null,
    src: String? = null,
    crossinline block: NodeBuilder.(HTMLVideoElement) -> Unit = {}
): HTMLVideoElement = element("video", klass, id) {
    if (src != null) { it.src = src }
    block(it)
}

public inline fun NodeBuilder.audio(
    klass: String? = null,
    id: String? = null,
    src: String? = null,
    crossinline block: NodeBuilder.(HTMLAudioElement) -> Unit = {}
): HTMLAudioElement = element("audio", klass, id) {
    if (src != null) { it.src = src }
    block(it)
}

public inline fun NodeBuilder.track(
    crossinline block: HTMLTrackElement.() -> Unit = {}
): HTMLTrackElement = element("track") {
    it.block()
}

public inline fun NodeBuilder.canvas(
    klass: String? = null,
    id: String? = null,
    crossinline block: HTMLCanvasElement.() -> Unit = {}
): HTMLCanvasElement = element("canvas", klass, id) {
    it.block()
}

public inline fun NodeBuilder.map(
    klass: String? = null,
    id: String? = null,
    name: String? = null,
    crossinline block: NodeBuilder.(HTMLMapElement) -> Unit = {}
): HTMLMapElement = element("map", klass, id) {
    if (name != null) { it.name = name }
    block(it)
}

public inline fun NodeBuilder.area(
    crossinline block: HTMLAreaElement.() -> Unit = {}
): HTMLAreaElement = element("area") {
    it.block()
}

// endregion

// region Interactive

public inline fun NodeBuilder.details(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLDetailsElement) -> Unit = {}
): HTMLDetailsElement = element("details", klass, id, block)

public inline fun NodeBuilder.summary(
    klass: String? = null,
    crossinline block: NodeBuilder.(HTMLElement) -> Unit = {}
): HTMLElement = element("summary", klass, block = block)

public inline fun NodeBuilder.dialog(
    klass: String? = null,
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLDialogElement) -> Unit = {}
): HTMLDialogElement = element("dialog", klass, id, block)

// endregion

// region Template and slots

public inline fun NodeBuilder.template(
    id: String? = null,
    crossinline block: NodeBuilder.(HTMLTemplateElement) -> Unit = {}
): HTMLTemplateElement = element("template", id = id, block = block)

public inline fun NodeBuilder.slot(
    klass: String? = null,
    name: String? = null,
    crossinline block: NodeBuilder.(HTMLSlotElement) -> Unit = {}
): HTMLSlotElement = element("slot", klass) {
    if (name != null) { it.name = name }
    block(it)
}

// endregion

// region Helpers

@Suppress("NOTHING_TO_INLINE")
public inline fun NodeBuilder.icon(
    name: String
): HTMLElement = i {
    aria.hidden = true
    +name
}

// endregion
