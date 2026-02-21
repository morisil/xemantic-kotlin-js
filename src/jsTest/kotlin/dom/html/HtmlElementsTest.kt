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

import com.xemantic.kotlin.js.dom.element.set
import com.xemantic.kotlin.js.dom.node
import com.xemantic.kotlin.test.sameAsHtml
import com.xemantic.markanywhere.js.toSemanticEvents
import com.xemantic.markanywhere.render.render
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

class HtmlElementsTest {

    // region Document structure

    @Test
    fun `should create document structure elements - html head body title`() = runTest {
        // when
        val html = node.html(lang = "en") {
            head {
                title { +"Page Title" }
            }
            body {
                div { +"Content" }
            }
        }

        // then
        html.toSemanticEvents().render() sameAsHtml """
            <html lang="en">
              <head>
                <title>
                  Page Title
                </title>
              </head>
              <body>
                <div>
                  Content
                </div>
              </body>
            </html>
        """.trimIndent()
    }

    // endregion

    // region Metadata and scripting

    @Test
    fun `should create metadata elements - base link meta`() = runTest {
        // when
        val head = node.head {
            base { href = "https://example.com/" }
            link(rel = "stylesheet", href = "style.css")
            meta(name = "viewport", content = "width=device-width")
        }

        // then
        head.toSemanticEvents().render() sameAsHtml """
            <head>
              <base href="https://example.com/"/>
              <link href="style.css" rel="stylesheet"/>
              <meta name="viewport" content="width=device-width"/>
            </head>
        """.trimIndent()
    }

    @Test
    fun `should create scripting elements - style script noscript`() = runTest {
        // when
        val body = node.body {
            style { +"body { color: red; }" }
            script { +"console.log('hello')" }
            script(src = "app.js")
            noscript {
                p { +"JavaScript is required." }
            }
        }

        // then
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <style>
            body { color: red; }
              </style>
              <script>
            console.log('hello')
              </script>
              <script src="app.js">
              </script>
              <noscript>
                <p>
                  JavaScript is required.
                </p>
              </noscript>
            </body>
        """.trimIndent()
    }

    // endregion

    // region Sections

    @Test
    fun `should create section elements - header footer main nav`() = runTest {
        // when
        val body = node.body {
            header("site-header") {
                nav("main-nav") {
                    +"Navigation"
                }
            }
            main("content") {
                +"Main content"
            }
            footer("site-footer") {
                +"Footer content"
            }
        }

        // then
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <header class="site-header">
                <nav class="main-nav">
                  Navigation
                </nav>
              </header>
              <main class="content">
                Main content
              </main>
              <footer class="site-footer">
                Footer content
              </footer>
            </body>
        """.trimIndent()
    }

    @Test
    fun `should create section elements - section article aside address`() = runTest {
        // when
        val body = node.body {
            section("main-section") {
                article("blog-post") {
                    +"Article content"
                }
                aside("sidebar") {
                    +"Sidebar content"
                }
            }
            address("contact", id = "contact-info") {
                +"Contact us at: "
                this.a(href = "mailto:contact@example.com") {
                    +"contact@example.com"
                }
            }
        }

        // then
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <section class="main-section">
                <article class="blog-post">
                  Article content
                </article>
                <aside class="sidebar">
                  Sidebar content
                </aside>
              </section>
              <address class="contact" id="contact-info">
                Contact us at: <a href="mailto:contact@example.com">contact@example.com</a>
              </address>
            </body>
        """.trimIndent()
    }

    @Test
    fun `should create section elements - hgroup search`() = runTest {
        // when
        val body = node.body {
            hgroup(id = "title-group") {
                h1 { +"Main Title" }
                p { +"Subtitle" }
            }
            search("site-search", id = "search-form") {
                form(
                    action = "/search",
                    method = "GET"
                ) {
                    input(type = "search", name = "q", placeholder = "Search...")
                    button(type = "submit") {
                        +"Search"
                    }
                }
            }
        }

        // then
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <hgroup id="title-group">
                <h1>
                  Main Title
                </h1>
                <p>
                  Subtitle
                </p>
              </hgroup>
              <search class="site-search" id="search-form">
                <form action="/search" method="GET">
                  <input name="q" type="search" placeholder="Search..."/><button type="submit">Search</button>
                </form>
              </search>
            </body>
        """.trimIndent()
    }

    // endregion

    // region Headings

    @Test
    fun `should create heading elements - h1 through h6`() = runTest {
        // when
        val body = node.body {
            h1("title") { +"Heading 1" }
            h2("subtitle") { +"Heading 2" }
            h3 { +"Heading 3" }
            h4 { +"Heading 4" }
            h5 { +"Heading 5" }
            h6 { +"Heading 6" }
        }

        // then
        body.toSemanticEvents().render() sameAsHtml /* language=html */ """
            <body>
              <h1 class="title">
                Heading 1
              </h1>
              <h2 class="subtitle">
                Heading 2
              </h2>
              <h3>
                Heading 3
              </h3>
              <h4>
                Heading 4
              </h4>
              <h5>
                Heading 5
              </h5>
              <h6>
                Heading 6
              </h6>
            </body>
        """.trimIndent()
    }

    // endregion

    // region Grouping content

    @Test
    fun `should create grouping content elements - div p pre blockquote`() = runTest {
        // when
        val body = node.body {
            div("container") {
                p("intro") {
                    +"This is a paragraph."
                }
                blockquote("quote", cite = "https://example.com") {
                    +"A famous quote"
                }
                pre("code-block") {
                    code {
                        +"val x = 42"
                    }
                }
            }
        }

        // then
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <div class="container">
                <p class="intro">
                  This is a paragraph.
                </p>
                <blockquote class="quote" cite="https://example.com">
                  A famous quote
                </blockquote>
                <pre class="code-block">
            <code>val x = 42</code>
                </pre>
              </div>
            </body>
        """.trimIndent()
    }

    @Test
    fun `should create figure figcaption and hr elements`() = runTest {
        // when
        val body = node.body {
            figure("image-figure") {
                img(src = "image.png", alt = "An image")
                figcaption {
                    +"Figure caption"
                }
            }
            hr("divider")
        }

        // then
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <figure class="image-figure">
                <img src="image.png" alt="An image"/>
                <figcaption>
                  Figure caption
                </figcaption>
              </figure>
              <hr class="divider"/>
            </body>
        """.trimIndent()
    }

    // endregion

    // region Lists

    @Test
    fun `should create list elements - ul ol li`() = runTest {
        // when
        val body = node.body {
            ul("menu") {
                li { +"Item 1" }
                li("active") { +"Item 2" }
                li { +"Item 3" }
            }
            ol("steps") {
                it.start = 1
                li { +"First step" }
                li { +"Second step" }
            }
        }

        // then
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <ul class="menu">
                <li>
                  Item 1
                </li>
                <li class="active">
                  Item 2
                </li>
                <li>
                  Item 3
                </li>
              </ul>
              <ol class="steps" start="1">
                <li>
                  First step
                </li>
                <li>
                  Second step
                </li>
              </ol>
            </body>
        """.trimIndent()
    }

    @Test
    fun `should create definition list and menu elements - dl dt dd menu`() = runTest {
        // when
        val body = node.body {
            dl("glossary") {
                dt { +"Term 1" }
                dd { +"Definition 1" }
                dt { +"Term 2" }
                dd { +"Definition 2" }
            }
            menu("context-menu") {
                li { button { +"Cut" } }
                li { button { +"Copy" } }
                li { button { +"Paste" } }
            }
        }

        // then
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <dl class="glossary">
                <dt>
                  Term 1
                </dt>
                <dd>
                  Definition 1
                </dd>
                <dt>
                  Term 2
                </dt>
                <dd>
                  Definition 2
                </dd>
              </dl>
              <menu class="context-menu">
                <li>
                  <button>Cut</button>
                </li>
                <li>
                  <button>Copy</button>
                </li>
                <li>
                  <button>Paste</button>
                </li>
              </menu>
            </body>
        """.trimIndent()
    }

    // endregion

    // region Tables

    @Test
    fun `should create table elements - table caption colgroup col thead tbody tfoot tr th td`() = runTest {
        // when
        val body = node.body {
            table("data-table") {
                caption { +"Monthly Sales" }
                colgroup {
                    col(span = 1)
                    col(span = 2)
                }
                thead {
                    tr {
                        th(scope = "col") { +"Month" }
                        th(scope = "col") { +"Sales" }
                        th(scope = "col" ) { +"Profit" }
                    }
                }
                tbody {
                    tr {
                        td { +"January" }
                        td { +"$1000" }
                        td { +"$200" }
                    }
                    tr {
                        td { +"February" }
                        td { +"$1500" }
                        td { +"$300" }
                    }
                }
                tfoot {
                    tr {
                        td { +"Total" }
                        td { +"$2500" }
                        td { +"$500" }
                    }
                }
            }
        }

        // then
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <table class="data-table">
                <caption>
                  Monthly Sales
                </caption>
                <colgroup>
                  <col span="1"/><col span="2"/>
                </colgroup>
                <thead>
                  <tr>
                    <th scope="col">
                      Month
                    </th>
                    <th scope="col">
                      Sales
                    </th>
                    <th scope="col">
                      Profit
                    </th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>
                      January
                    </td>
                    <td>
                      $1000
                    </td>
                    <td>
                      $200
                    </td>
                  </tr>
                  <tr>
                    <td>
                      February
                    </td>
                    <td>
                      $1500
                    </td>
                    <td>
                      $300
                    </td>
                  </tr>
                </tbody>
                <tfoot>
                  <tr>
                    <td>
                      Total
                    </td>
                    <td>
                      $2500
                    </td>
                    <td>
                      $500
                    </td>
                  </tr>
                </tfoot>
              </table>
            </body>
        """.trimIndent()
    }

    // endregion

    // region Text-level semantics

    @Test
    fun `should create text semantic elements - span a strong em b i u s small mark`() = runTest {
        // when
        val body = node.body {
            p {
                span("highlight") { +"Span text" }
                +" "
                a("link", href = "https://example.com", target = "_blank") {
                    it.name = "example"
                    +"Link"
                }
            }
            p {
                strong { +"Important" }
                +" and "
                em { +"emphasized" }
                +" text with "
                mark { +"highlighted" }
                +" and "
                small { +"small" }
                +" parts."
            }
            p {
                b { +"Bold" }
                +" "
                i { +"Italic" }
                +" "
                u { +"Underlined" }
                +" "
                s { +"Strikethrough" }
            }
        }

        // then
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <p>
                <span class="highlight">Span text</span> <a class="link" href="https://example.com" target="_blank" name="example">Link</a>
              </p>
              <p>
                <strong>Important</strong> and <em>emphasized</em> text with <mark>highlighted</mark> and <small>small</small> parts.
              </p>
              <p>
                <b>Bold</b> <i>Italic</i> <u>Underlined</u> <s>Strikethrough</s>
              </p>
            </body>
        """.trimIndent()
    }

    @Test
    fun `should create technical text elements - sub sup abbr cite code dfn kbd samp var`() = runTest {
        // when
        val body = node.body {
            p {
                +"H"
                sub { +"2" }
                +"O and E=mc"
                sup { +"2" }
            }
            p {
                abbr(title = "HyperText Markup Language") {
                    +"HTML"
                }
                +" as described in "
                cite { +"The Book" }
            }
            p {
                +"Use the "
                code { +"println()" }
                +" function, defined as "
                dfn { +"a standard output function" }
                +"."
            }
            p {
                +"Press "
                kbd { +"Ctrl" }
                +" + "
                kbd { +"C" }
            }
            p {
                +"Output: "
                samp { +"Hello, World!" }
            }
            p {
                +"The variable "
                `var` { +"x" }
                +" is an integer."
            }
        }

        // then
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <p>
                H<sub>2</sub>O and E=mc<sup>2</sup>
              </p>
              <p>
                <abbr title="HyperText Markup Language">HTML</abbr> as described in <cite>The Book</cite>
              </p>
              <p>
                Use the <code>println()</code> function, defined as <dfn>a standard output function</dfn>.
              </p>
              <p>
                Press <kbd>Ctrl</kbd> + <kbd>C</kbd>
              </p>
              <p>
                Output: <samp>Hello, World!</samp>
              </p>
              <p>
                The variable <var>x</var> is an integer.
              </p>
            </body>
        """.trimIndent()
    }

    @Test
    fun `should create quotation and editing elements - q time data ins del`() = runTest {
        // when
        val body = node.body {
            p {
                q(cite = "https://example.com") {
                    +"An inline quote"
                }
            }
            p {
                +"Published on "
                time(dateTime = "2026-01-15") {
                    +"January 15, 2026"
                }
            }
            p {
                data(value = "42") {
                    +"Forty-two"
                }
            }
            p {
                del(cite = "reason.html", dateTime = "2026-01-15") {
                    +"Old text"
                }
                ins(cite = "reason.html", dateTime = "2026-01-15") {
                    +"New text"
                }
            }
        }

        // then
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <p>
                <q cite="https://example.com">An inline quote</q>
              </p>
              <p>
                Published on <time datetime="2026-01-15">January 15, 2026</time>
              </p>
              <p>
                <data value="42">Forty-two</data>
              </p>
              <p>
                <del cite="reason.html" datetime="2026-01-15">Old text</del><ins cite="reason.html" datetime="2026-01-15">New text</ins>
              </p>
            </body>
        """.trimIndent()
    }

    @Test
    fun `should create internationalization elements - ruby rt rp bdi bdo`() = runTest {
        // when
        val body = node.body {
            ruby {
                +"明日"
                rp { +"(" }
                rt { +"あした" }
                rp { +")" }
            }
            p {
                +"User: "
                bdi { +"إيان" }
                +" has 3 posts."
            }
            p {
                bdo(dir = "rtl") {
                    +"This text will be reversed."
                }
            }
        }

        // then
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <ruby>明日<rp>(</rp><rt>あした</rt><rp>)</rp></ruby>
              <p>
                User: <bdi>إيان</bdi> has 3 posts.
              </p>
              <p>
                <bdo dir="rtl">This text will be reversed.</bdo>
              </p>
            </body>
        """.trimIndent()
    }

    @Test
    fun `should create line break elements - br wbr`() = runTest {
        // when
        val body = node.body {
            p {
                +"Line 1"
                br()
                +"Line 2"
            }
            p {
                +"super"
                wbr()
                +"cali"
                wbr()
                +"fragilistic"
            }
        }

        // then
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <p>
                Line 1<br/>Line 2
              </p>
              <p>
                super<wbr/>cali<wbr/>fragilistic
              </p>
            </body>
        """.trimIndent()
    }

    // endregion

    // region Forms

    @Test
    fun `should create form structure elements - form fieldset legend label input button`() = runTest {
        val body = node.body {
            form("login-form", action = "/login", method = "POST") {
                fieldset {
                    legend { +"Login" }
                    label(htmlFor = "username") {
                        +"Username:"
                    }
                    input(type = "text", id = "username", name = "username", value = "admin")
                    br()
                    label(htmlFor = "password") {
                        +"Password:"
                    }
                    input(type = "password", id = "password", name = "password")
                }
                button("submit-btn", type = "submit") {
                    +"Login"
                }
            }
        }
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <form class="login-form" action="/login" method="POST">
                <fieldset>
                  <legend>
                    Login
                  </legend>
                  <label for="username">Username:</label><input id="username" name="username" type="text"/><br/><label for="password">Password:</label><input id="password" name="password" type="password"/>
                </fieldset>
                <button class="submit-btn" type="submit">Login</button>
              </form>
            </body>
        """.trimIndent()
    }

    @Test
    fun `should create form selection elements - select option optgroup textarea datalist`() = runTest {
        val body = node.body {
            form {
                textarea(
                    "description",
                    name = "description",
                    placeholder = "Enter description"
                ) {
                    it.rows = 4
                    it.cols = 50
                }
                br()
                select("country-select", name = "country") {
                    optgroup(label = "Europe") {
                        option(value = "de") { +"Germany" }
                        option(value = "fr") { +"France" }
                    }
                    optgroup(label = "Asia") {
                        option(value = "jp") { +"Japan" }
                        option(value = "kr") { +"Korea" }
                    }
                }
                br()
                input(id = "browser-input") {
                    it["list"] = "browsers"
                }
                datalist(id = "browsers") {
                    option(value = "Chrome")
                    option(value = "Firefox")
                }
            }
        }
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <form>
                <textarea class="description" name="description" placeholder="Enter description" rows="4" cols="50"></textarea><br/><select class="country-select" name="country"><optgroup label="Europe"><option value="de">Germany</option><option value="fr">France</option></optgroup><optgroup label="Asia"><option value="jp">Japan</option><option value="kr">Korea</option></optgroup></select><br/><input id="browser-input" list="browsers"/><datalist id="browsers"><option value="Chrome"></option><option value="Firefox"></option></datalist>
              </form>
            </body>
        """.trimIndent()
    }

    @Test
    fun `should create form output elements - output meter progress`() = runTest {
        val body = node.body {
            form {
                output(name = "result") {
                    it["for"] = "a b"
                    +"0"
                }
            }
            progress(value = 70.0, max = 100.0) {
                +"70%"
            }
            meter(value = 0.7, min = 0.0, max = 1.0) {
                it.low = 0.3
                it.high = 0.7
                it.optimum = 0.8
                +"70%"
            }
        }
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <form>
                <output name="result" for="a b">0</output>
              </form>
              <progress value="70" max="100">70%</progress><meter value="0.7" min="0" max="1" low="0.3" high="0.7" optimum="0.8">70%</meter>
            </body>
        """.trimIndent()
    }

    // endregion

    // region Embedded content

    @Test
    fun `should create image elements - img picture source`() = runTest {
        val body = node.body {
            img(src = "photo.jpg", alt = "A photo") {
                width = 300
                height = 200
            }
            picture("responsive-image") {
                source { srcset = "photo-large.jpg"; media = "(min-width: 800px)" }
                source { srcset = "photo-small.jpg"; media = "(max-width: 799px)" }
                img(src = "photo.jpg", alt = "A photo")
            }
        }
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <img src="photo.jpg" alt="A photo" width="300" height="200"/><picture class="responsive-image"><source srcset="photo-large.jpg" media="(min-width: 800px)"/><source srcset="photo-small.jpg" media="(max-width: 799px)"/><img src="photo.jpg" alt="A photo"/></picture>
            </body>
        """.trimIndent()
    }

    @Test
    fun `should create media elements - audio video track`() = runTest {
        val body = node.body {
            audio("player") {
                it.controls = true
                source { src = "audio.mp3"; type = "audio/mpeg" }
                source { src = "audio.ogg"; type = "audio/ogg" }
                +"Your browser does not support audio."
            }
            video("video-player") {
                it.controls = true
                it.width = 640
                it.height = 480
                source { src = "video.mp4"; type = "video/mp4" }
                track { src = "subtitles.vtt"; kind = "subtitles"; srclang = "en"; label = "English" }
                +"Your browser does not support video."
            }
        }
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <audio class="player" controls=""><source src="audio.mp3" type="audio/mpeg"/><source src="audio.ogg" type="audio/ogg"/>Your browser does not support audio.</audio><video class="video-player" controls="" width="640" height="480"><source src="video.mp4" type="video/mp4"/><track src="subtitles.vtt" kind="subtitles" srclang="en" label="English"/>Your browser does not support video.</video>
            </body>
        """.trimIndent()
    }

    @Test
    fun `should create embedded content elements - iframe embed object canvas map area`() = runTest {
        // when
        val body = node.body {
            iframe("embedded", src = "https://example.com") {
                width = "600"
                height = "400"
                title = "Example"
            }
            embed(src = "plugin.swf", type = "application/x-shockwave-flash") {
                width = "400"
                height = "300"
            }
            `object`("media-object", data = "movie.swf", type = "application/x-shockwave-flash") {
                p { +"Fallback content" }
            }
            canvas("drawing", id = "myCanvas") {
                width = 400
                height = 300
            }
            map("image-map", name = "workmap") {
                area {
                    shape = "rect"
                    coords = "34,44,270,350"
                    alt = "Computer"
                    href = "computer.html"
                }
                area {
                    shape = "circle"
                    coords = "337,300,44"
                    alt = "Phone"
                    href = "phone.html"
                }
            }
        }

        // then
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <iframe class="embedded" src="https://example.com" width="600" height="400" title="Example"></iframe><embed src="plugin.swf" type="application/x-shockwave-flash" width="400" height="300"/>
              <object class="media-object" data="movie.swf" type="application/x-shockwave-flash">
                <p>
                  Fallback content
                </p>
              </object>
              <canvas class="drawing" id="myCanvas" width="400" height="300"></canvas><map class="image-map" name="workmap"><area shape="rect" coords="34,44,270,350" alt="Computer" href="computer.html"/><area shape="circle" coords="337,300,44" alt="Phone" href="phone.html"/></map>
            </body>
        """.trimIndent()
    }

    // endregion

    // region Interactive

    @Test
    fun `should create interactive elements - details summary dialog`() = runTest {
        val body = node.body {
            details("collapsible") {
                it.open = true
                summary { +"Click to expand" }
                p { +"Hidden content revealed!" }
            }
            dialog("modal", id = "myDialog") {
                h2 { +"Dialog Title" }
                p { +"Dialog content" }
                button(id = "close") { +"Close" }
            }
        }
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <details class="collapsible" open="">
                <summary>
                  Click to expand
                </summary>
                <p>
                  Hidden content revealed!
                </p>
              </details>
              <dialog class="modal" id="myDialog">
                <h2>
                  Dialog Title
                </h2>
                <p>
                  Dialog content
                </p>
                <button id="close">Close</button>
              </dialog>
            </body>
        """.trimIndent()
    }

    // endregion

    // region Template and slots

    @Test
    fun `should create template and slot elements`() = runTest {
        val body = node.body {
            template(id = "row-template") {
                tr {
                    td("name")
                    td("value")
                }
            }
            div {
                slot(name ="header") {
                    +"Default header"
                }
                slot(name = "content") {
                    +"Default content"
                }
            }
        }
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <template id="row-template">
                <tr>
                  <td class="name">
                  </td>
                  <td class="value">
                  </td>
                </tr>
              </template>
              <div>
                <slot name="header">Default header</slot><slot name="content">Default content</slot>
              </div>
            </body>
        """.trimIndent()
    }

    // endregion

    // region Helpers

    @Test
    fun `should create icon helper element`() = runTest {
        val body = node.body {
            icon("home")
            icon("settings")
        }
        body.toSemanticEvents().render() sameAsHtml """
            <body>
              <i aria-hidden="true">home</i><i aria-hidden="true">settings</i>
            </body>
        """.trimIndent()
    }

    // endregion

}