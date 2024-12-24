package com.gotneb.honki.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookFormatsModel(
    @SerialName("application/epub+zip")
    val epubZip: String? = null,
    @SerialName("application/octet-stream")
    val octetStream: String? = null,
    @SerialName("application/pdf")
    val pdf: String? = null,
    @SerialName("application/prs.tex")
    val prsTex: String? = null,
    @SerialName("application/rdf+xml")
    val rdfXml: String = "",
    @SerialName("x-mobipocket-ebook")
    val mobiPocketEbook: String? = null,
    @SerialName("audio/mp4")
    val mp4: String? = null,
    @SerialName("audio/mpeg")
    val mpeg: String? = null,
    @SerialName("audio/ogg")
    val ogg: String? = null,
    @SerialName("image/jpeg")
    val image: String = "",
    @SerialName("text/html")
    val html: String? = null,
    @SerialName("text/html; charset=utf-8")
    val htmlUTF8: String? = null,
    @SerialName("text/plain")
    val plainText: String? = null,
    @SerialName("text/plain; charset=us-ascii")
    val plainTextAscii: String? = null,
)